package com.example.onlineshoppingdemo.dao;

import com.example.onlineshoppingdemo.ds.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {
}
