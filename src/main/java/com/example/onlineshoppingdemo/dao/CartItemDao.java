package com.example.onlineshoppingdemo.dao;

import com.example.onlineshoppingdemo.ds.ApplicationUser;
import com.example.onlineshoppingdemo.ds.CartItem;
import com.example.onlineshoppingdemo.ds.CartItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDao extends JpaRepository<CartItem,Integer> {

    List<CartItem> findByUserId(int id);

    CartItem findCartItemByUserId(int id);

    void deleteAllByUser(ApplicationUser user);



}
