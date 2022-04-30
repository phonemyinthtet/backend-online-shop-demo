package com.example.onlineshoppingdemo.controller;

import com.example.onlineshoppingdemo.ds.JwtRequest;
import com.example.onlineshoppingdemo.ds.JwtResponse;
import com.example.onlineshoppingdemo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtService jwtService;
    @PostMapping("/users/login")
    public JwtResponse login(@RequestBody JwtRequest request){
        return jwtService.createJwtToken(request);
    }

}
