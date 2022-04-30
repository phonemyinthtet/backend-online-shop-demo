package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.dao.CartItemDetailDao;
import com.example.onlineshoppingdemo.ds.CartItemDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemDetailService {
    @Autowired
    private CartItemDetailDao cartItemDetailDao;


    public List<CartItemDetail> findAll(){
        return cartItemDetailDao.findAll();
    }
    public void delete(CartItemDetail cartItemDetail){
        cartItemDetailDao.delete(cartItemDetail);
    }


    public CartItemDetail save(CartItemDetail cartItemDetail){
       return cartItemDetailDao.save(cartItemDetail);
    }
    public void deleteById(int id) {
        cartItemDetailDao.deleteById(id);
    }

}
