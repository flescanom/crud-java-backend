/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author flesc
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Backend CRUD",
                version = "1.0.0",
                description = "Backend CRUD test with Spring boot"
        )
)
public class OpenApiConfig { }
