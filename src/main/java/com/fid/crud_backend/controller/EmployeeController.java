/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.controller;

import com.fid.crud_backend.dto.ApiResponse;
import com.fid.crud_backend.dto.EmployeeDto;
import com.fid.crud_backend.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author flesc
 */

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees resource")
public class EmployeeController {
    
    private final IEmployeeService employeeService;
    
    @Operation(summary = "Get a list employees" )
    @GetMapping()
    public ResponseEntity<ApiResponse<List<EmployeeDto>>> getEmployees() {
        List<EmployeeDto> employeesDto = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse<>(true, "Listado de empleados obtenido correctamente" ,employeesDto));
    }
    
    @Operation(summary = "Get a information employee given a identification" )
    @GetMapping("{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Empleado obtenido correctamente ", employeeDto));
    }

    @Operation(summary = "Save a new employee given the necessary data" )
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Empleado guardado correctamente", savedEmployee));
    }
    
}
