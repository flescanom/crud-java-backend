/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fid.crud_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author flesc
 * @param <T>
 */

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean ok;
    private String message;
    private T data;
}
