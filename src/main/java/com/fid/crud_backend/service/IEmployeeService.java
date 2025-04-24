/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fid.crud_backend.service;

import com.fid.crud_backend.dto.EmployeeDto;
import java.util.List;

/**
 *
 * @author flesc
 */
public interface IEmployeeService {
    
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long employeeId);
    
}
