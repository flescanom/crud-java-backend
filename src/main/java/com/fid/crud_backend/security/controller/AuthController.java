/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.security.controller;

import com.fid.crud_backend.dto.ApiResponse;
import com.fid.crud_backend.security.dto.LoginUserDto;
import com.fid.crud_backend.security.dto.NewUserDto;
import com.fid.crud_backend.security.service.AuthService;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author flesc
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            log.error("Error en los campos durante el inicio de sesion");
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                String rejectedValue = String.valueOf(error.getRejectedValue());
                // Process the error information as needed
                log.error("Field: " + fieldName + ", Error: " + errorMessage + ", Rejected Value: " + rejectedValue);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Revise sus credenciales", Arrays.toString(bindingResult.getFieldErrors().toArray())));
        }
        try {
            log.info("Usuario {} iniciando sesion", loginUserDto.getUsername());
            String jwt = authService.authenticate(loginUserDto.getUsername(), loginUserDto.getPassword());
            return ResponseEntity.ok(new ApiResponse<>(true, "Inicio de sesion correcto", jwt));
        } catch (Exception e) {
            log.error("Error durante el inicio de sesion: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error al iniciar sesion", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Error en los campos durante el registro");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Revise los campos", null));
        }
        try {
            log.info("Registrando usuario: {}", newUserDto.getUsername());
            authService.registerUser(newUserDto);
            return ResponseEntity.ok(new ApiResponse<>(true, "User registrado correctamente", null));
        } catch (IllegalArgumentException e) {
            log.error("Error durante el registro: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error en el registro", e.getMessage()));
        }
    }

    @GetMapping("/check-auth")
    public ResponseEntity<ApiResponse<String>> checkAuth() {
        log.info("Validando token");
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuario autenticado", null));
    }

}
