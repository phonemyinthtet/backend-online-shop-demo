package com.example.onlineshoppingdemo.controller;

import com.example.onlineshoppingdemo.ds.ApplicationUser;
import com.example.onlineshoppingdemo.ds.JwtRequest;
import com.example.onlineshoppingdemo.ds.JwtResponse;
import com.example.onlineshoppingdemo.dto.UseDto;
import com.example.onlineshoppingdemo.service.JwtService;
import com.example.onlineshoppingdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initCreateUserAndAdmin(){
        userService.initCreateUserAndRole();
    }

    //http://localhost:8080/users/create
    @PostMapping("/users/create")
    public ApplicationUser createUser(@RequestBody UseDto useDto){
        return userService.saveUser(useDto);
    }

        @GetMapping("/users/list")
    public List<ApplicationUser> showAllUsers(){
        return userService.getAllList();
    }

    @GetMapping("/users/user/{id}")
    public ApplicationUser showUserById(@PathVariable int id){
        return userService.findUserById(id).get();
    }


}
