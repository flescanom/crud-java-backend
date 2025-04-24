/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.service.impl;

import com.fid.crud_backend.dto.EmployeeDto;
import com.fid.crud_backend.entity.Employee;
import com.fid.crud_backend.exception.ResourceNotFoundException;
import com.fid.crud_backend.mapper.EmployeeMapper;
import com.fid.crud_backend.repository.IEmployeeRepository;
import com.fid.crud_backend.service.IEmployeeService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author flesc
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    
    private IEmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> 
                        new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));
        
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        
        List<Employee> employees = employeeRepository.findAll();
         
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }
    
}
