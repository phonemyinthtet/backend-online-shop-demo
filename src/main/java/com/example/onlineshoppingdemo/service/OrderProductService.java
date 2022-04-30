package com.example.onlineshoppingdemo.service;


import com.example.onlineshoppingdemo.dao.OrderProductDetailDao;
import com.example.onlineshoppingdemo.ds.OrderProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderProductService {
    @Autowired
    private OrderProductDetailDao  orderProductDetailDao;

    public OrderProductDetail save(OrderProductDetail orderProductDetail){
        return orderProductDetailDao.save(orderProductDetail);
    }
    public List<OrderProductDetail> findAll(){
        return orderProductDetailDao.findAll();
    }
    public OrderProductDetail findById(int id){
        return orderProductDetailDao.findById(id).get();
    }

}
