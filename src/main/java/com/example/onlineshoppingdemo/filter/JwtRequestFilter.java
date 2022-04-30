package com.example.onlineshoppingdemo.filter;

import com.example.onlineshoppingdemo.service.JwtService;
import com.example.onlineshoppingdemo.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var header = request.getHeader("Authorization");
        var jwtToken = (String)null;
        var email = (String)null;
        if (header != null && header.startsWith("Bearer ")){
            jwtToken = header.substring(7);
            try{
                email = jwtUtil.getUserEmailFromToken(jwtToken);
            }catch (IllegalArgumentException e){
                System.out.println("IllegalArgumentException");
                throw new IllegalArgumentException("Unable Jwt Token");

            }
            catch(ExpiredJwtException e){
                System.out.println("ExpiredJwtException");
            }
        }
        else{
            //Jwt Token does not start with Bearer
        }
        //Email shi pye and Authentication ma shi yin
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            var userDetails = jwtService.loadUserByUsername(email);
            if (jwtUtil.validationToken(jwtToken,userDetails)){
                var userpasswordauthtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                userpasswordauthtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userpasswordauthtoken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
