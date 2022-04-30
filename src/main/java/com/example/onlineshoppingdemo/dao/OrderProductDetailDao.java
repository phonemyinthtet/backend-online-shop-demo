package com.example.onlineshoppingdemo.dao;

import com.example.onlineshoppingdemo.ds.OrderProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductDetailDao extends JpaRepository<OrderProductDetail,Integer> {

}
