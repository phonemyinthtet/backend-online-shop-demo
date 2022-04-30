package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.dao.OrderDao;
import com.example.onlineshoppingdemo.ds.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;


    public Order save(Order order){
        return orderDao.save(order);
    }

    public List<Order> findAll(){
        return orderDao.findAll();
    }

    public Order findById(int id){
        return orderDao.findById(id).get();
    }

}
