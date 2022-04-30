package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.dao.ApplicationUserDao;
import com.example.onlineshoppingdemo.dao.RoleDao;
import com.example.onlineshoppingdemo.ds.ApplicationUser;
import com.example.onlineshoppingdemo.ds.Role;
import com.example.onlineshoppingdemo.dto.UseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private ApplicationUserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser saveUser(UseDto user){
        return userDao.save(new ApplicationUser(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(), passwordEncoder.encode(user.getPassword()),Set.of(roleDao.findById(user.getRoles()).get())));
    }
    public List<ApplicationUser> getAllList(){
        return userDao.findAll();
    }

    public Optional<ApplicationUser> findUserById(int id){
        return userDao.findById(id);
    }

    public ApplicationUser findUserByEmail(String email){
        return userDao.findUserByEmail(email).get();
    }


    public void initCreateUserAndRole(){
        Role role1=new Role();
        role1.setName("Admin");
        roleDao.save(role1);
        Role role2 =new Role();
        role2.setName("User");
        roleDao.save(role2);

        Set<Role> user1Role = new HashSet<>();
        user1Role.add(role1);

        var user1 = new ApplicationUser();
        user1.setEmail("user@email.com");
        user1.setFirstName("user");
        user1.setLastName("user");
        user1.setPassword(passwordEncoder.encode("user"));
        user1.setRoles(user1Role);
        userDao.save(user1);

        Set<Role> user2Role = new HashSet<>();
        user1Role.add(role2);

        var user2 = new ApplicationUser();
        user2.setEmail("admin@email.com");
        user2.setFirstName("admin");
        user2.setLastName("admin");
        user2.setPassword(passwordEncoder.encode("admin"));
        user2.setRoles(user2Role);
        userDao.save(user2);



    }

}

