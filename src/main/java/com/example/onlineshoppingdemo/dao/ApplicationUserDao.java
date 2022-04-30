package com.example.onlineshoppingdemo.dao;

import com.example.onlineshoppingdemo.ds.ApplicationUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserDao extends JpaRepository<ApplicationUser,Integer> {

    Optional<ApplicationUser> findUserByEmail(String email);

}
