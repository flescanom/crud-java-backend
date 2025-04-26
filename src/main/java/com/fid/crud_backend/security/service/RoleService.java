/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.security.service;

import com.fid.crud_backend.security.repository.IRoleRepository;
import com.fid.crud_backend.security.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flesc
 */

@Service
@Transactional
public class RoleService {
    
    @Autowired
    IRoleRepository roleRespository;
    
    public void save(Role role){
        roleRespository.save(role);
    }
    
}
