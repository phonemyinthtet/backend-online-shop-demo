package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.dao.ApplicationUserDao;
import com.example.onlineshoppingdemo.ds.ApplicationUser;
import com.example.onlineshoppingdemo.ds.JwtRequest;
import com.example.onlineshoppingdemo.ds.JwtResponse;
import com.example.onlineshoppingdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ApplicationUserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtResponse createJwtToken(JwtRequest request){
        authenticate(request.getEmail(),request.getPassword());
        return new JwtResponse(userDao.findUserByEmail(request.getEmail()).get(),jwtUtil.generateToken(loadUserByUsername(request.getEmail())));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       var user = userDao.findUserByEmail(email).get();
        if(user != null){
            return  new User(user.getEmail(),user.getPassword(),userRole(user)) ;
        }
        else throw new UsernameNotFoundException("email not found");

    }
    private Set userRole(ApplicationUser user){
        Set authorization = new HashSet();
        user.getRoles().forEach(role->{
            authorization.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        });
        return authorization;
    }
    private void authenticate(String email,String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (DisabledException e){
            throw  new DisabledException("user is disabled");
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("invalid email and password");
        }
    }

}
