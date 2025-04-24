/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fid.crud_backend.repository;

import com.fid.crud_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author flesc
 */
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
