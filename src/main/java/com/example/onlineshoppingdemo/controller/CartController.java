package com.example.onlineshoppingdemo.controller;


import com.example.onlineshoppingdemo.ds.*;

import com.example.onlineshoppingdemo.service.CartProcessService;
import com.example.onlineshoppingdemo.service.OrderProcess;
import com.example.onlineshoppingdemo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderProcess orderProcess;
    @Autowired
    private CartProcessService cartProcessService;

    //http://localhost:8080/cart_add/1/quantity/2
    @GetMapping("/cart_add/{id}/quantity/{quantity}")
    public Product addToCart(@PathVariable int id, @PathVariable int quantity) {
        cartProcessService.addProduct(id, quantity);
        return productService.getProdcutById(id).get();
    }

    @GetMapping("cart/deleteAll")
    public void removeAllCart() {
        cartProcessService.isEmpty();
    }

    //http://localhost:8080/cart
    @GetMapping("/cart")
    public List<CartItem> cartProductList() {
        return cartProcessService.showALlItem();
    }

    //http://localhost:8080/removeitem/1
    @GetMapping("/cart/delete_item/{id}")
    public void removeCartProduct(@PathVariable int id) {
        cartProcessService.removeWithId(id);
    }
    //http://localhost:8080/checkout
    @GetMapping("/checkout")
    public void checkout(){
        orderProcess.checkout();
    }
    @GetMapping("/order_list")
    public List<Order> orderList(){
        return orderProcess.orderList();
    }
}
