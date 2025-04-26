/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.security.service;

import com.fid.crud_backend.security.dto.NewUserDto;
import com.fid.crud_backend.security.jwt.JwtUtil;
import com.fid.crud_backend.security.repository.IRoleRepository;
import com.fid.crud_backend.security.entity.Role;
import com.fid.crud_backend.security.entity.User;
import com.fid.crud_backend.security.enums.RoleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author flesc
 */

@Service
public class AuthService {

    private final UserService userService;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    
    @Autowired
    public AuthService(UserService userService, IRoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }
    
    public String authenticate(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authResult = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        return jwtUtil.generateToken(authResult);
    }
    
    public void registerUser(NewUserDto newUserDto){
        if (userService.existsByUsername(newUserDto.getUsername())){
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        
        Role roleUser = roleRepository.findByName(RoleList.ROLE_USER).orElseThrow(()->new RuntimeException("Rol no encontrado"));
        User user = new User(newUserDto.getUsername(), passwordEncoder.encode(newUserDto.getPassword()) , roleUser);
        userService.save(user);
    }
}
