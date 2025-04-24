/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fid.crud_backend.repository;

import com.fid.crud_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author flesc
 */
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    
}
