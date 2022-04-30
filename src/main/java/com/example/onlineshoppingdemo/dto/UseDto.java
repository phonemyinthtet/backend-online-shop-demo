package com.example.onlineshoppingdemo.dto;


import lombok.Data;

@Data
public class UseDto {
    private Integer id;

    private String firstName;
    private String lastName;

    private String email;

    private String password;

    private int roles;
}
