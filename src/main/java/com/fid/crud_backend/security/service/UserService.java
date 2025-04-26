/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.security.service;

import com.fid.crud_backend.security.entity.User;
import com.fid.crud_backend.security.repository.IUserRepository;
import java.util.Collections;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author flesc
 */

@NoArgsConstructor
@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private IUserRepository userRepository;    
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().toString());
        
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(authority)
        );
    }
    
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public void save(User user){
        userRepository.save(user);
    }
    
}
