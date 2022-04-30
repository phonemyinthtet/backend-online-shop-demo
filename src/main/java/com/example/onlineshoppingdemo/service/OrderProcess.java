package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.ds.CartItem;
import com.example.onlineshoppingdemo.ds.CartItemDetail;
import com.example.onlineshoppingdemo.ds.Order;
import com.example.onlineshoppingdemo.ds.OrderProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderProcess {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserService userService;
    private Map<Integer, List<OrderProductDetail>> orderProdcutWithUser = new HashMap<>();

    public void checkout(){
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userId = userService.findUserByEmail(user.getUsername()).getId();
        var cartItem = cartItemService.findCartItemByUserId(userId);
        orderProdcutWithUser.put(userId,new ArrayList<>());

        Order order = new Order();
        for (CartItemDetail cartItemDetail:cartItem.getCartItemDetails()){
            OrderProductDetail orderProductDetail = new OrderProductDetail();
            orderProductDetail.setProduct(cartItemDetail.getProduct());
            orderProductDetail.setQuantity(cartItemDetail.getQuantity());
            orderProdcutWithUser.get(userId).add(orderProductDetail);
            orderProductService.save(orderProductDetail);
        }

        order.setApplicationUser( cartItem.getUser());
        order.setDateCreated(LocalDate.now());
       order.setOrderProductDetails(orderProdcutWithUser.get(userId));
        orderService.save(order);
       //        System.out.println(order.getApplicationUser().getEmail());

        orderProdcutWithUser.remove(userId);

    }
    public List<Order> orderList(){
        return orderService.findAll();
    }
}
