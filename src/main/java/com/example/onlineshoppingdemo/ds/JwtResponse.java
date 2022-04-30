package com.example.onlineshoppingdemo.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private ApplicationUser user;
    private String jwtToken;

}
