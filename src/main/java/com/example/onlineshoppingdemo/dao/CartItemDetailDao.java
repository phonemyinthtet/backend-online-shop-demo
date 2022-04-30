package com.example.onlineshoppingdemo.dao;


import com.example.onlineshoppingdemo.ds.CartItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemDetailDao extends JpaRepository<CartItemDetail,Integer> {

}
