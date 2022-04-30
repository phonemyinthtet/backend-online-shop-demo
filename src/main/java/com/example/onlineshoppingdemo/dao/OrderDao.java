package com.example.onlineshoppingdemo.dao;

import com.example.onlineshoppingdemo.ds.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {


}
