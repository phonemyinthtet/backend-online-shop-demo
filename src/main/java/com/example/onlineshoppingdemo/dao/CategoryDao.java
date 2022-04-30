package com.example.onlineshoppingdemo.dao;

import com.example.onlineshoppingdemo.ds.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {
}
