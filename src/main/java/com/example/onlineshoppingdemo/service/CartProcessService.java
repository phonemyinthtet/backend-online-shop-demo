package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.ds.CartItem;
import com.example.onlineshoppingdemo.ds.CartItemDetail;
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
public class CartProcessService {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemDetailService cartItemDetailService;
    @Autowired
    private CartItemService cartItemService;

    private final Map<Integer, List<CartItemDetail>> userIdWithCartDetail = new HashMap<>();
    private final Map<Integer, CartItem> cartWithUser = new HashMap<>();
    private final List<CartItemDetail> details = new ArrayList<>();


    public CartItemDetail findById(int id) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userIdWithCartDetail.containsKey(userService.findUserByEmail(user.getUsername()).getId())) {
            for (CartItemDetail detail : userIdWithCartDetail.get(userService.findUserByEmail(user.getUsername()).getId())) {
                if (detail.getProduct().getId() == id) {
                    return detail;
                }

            }
        } else {
            userIdWithCartDetail.put(userService.findUserByEmail(user.getUsername()).getId(), new ArrayList<>());
            for (CartItemDetail detail : userIdWithCartDetail.get(userService.findUserByEmail(user.getUsername()).getId())) {
                if (detail.getProduct().getId() == id) {
                    return detail;
                }

            }
        }

        return null;
    }

    public void addProduct(int id, int quantity) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userid = userService.findUserByEmail(user.getUsername()).getId();
        var product = productService.getProdcutById(id).get();
        CartItemDetail detail = this.findById(product.getId());

        if (userIdWithCartDetail.containsKey(userService.findUserByEmail(user.getUsername()).getId())) {
            if (detail == null) {
                detail = new CartItemDetail();
                detail.setQuantity(quantity);
                detail.setProduct(product);
                userIdWithCartDetail.get(userService.findUserByEmail(user.getUsername()).getId()).add(detail);

            } else {
                int newQuantity = detail.getQuantity() + quantity;
                if (newQuantity <= 0) {
                    this.details.remove(detail);

                    cartItemDetailService.delete(detail);
                } else {
                    detail.setQuantity(newQuantity);
                }
            }
        }

        cartItemDetailService.save(detail);

    }

    public List<CartItem> showALlItem() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userid = userService.findUserByEmail(user.getUsername()).getId();
        var cat = userIdWithCartDetail.get(userid);
        if (userIdWithCartDetail.get(userid) != null) {
            if (cartWithUser.containsKey(userid)) {
                cartWithUser.get(userid).setUser(userService.findUserByEmail(user.getUsername()));
                cartWithUser.get(userid).setLocalDate(LocalDate.now());
                cartWithUser.get(userid).setCartItemDetails(cat);
            } else {
                cartWithUser.put(userid, new CartItem());
                cartWithUser.get(userid).setUser(userService.findUserByEmail(user.getUsername()));
                cartWithUser.get(userid).setLocalDate(LocalDate.now());
                cartWithUser.get(userid).setCartItemDetails(cat);
            }
            cartItemService.save(cartWithUser.get(userid));
        }
        return cartItemService.findByUserId(userService.findUserByEmail(user.getUsername()).getId());
    }

    @Transactional
    public List<CartItemDetail> isEmpty() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userid = userService.findUserByEmail(user.getUsername()).getId();
        for (CartItemDetail cartItD : cartItemService.findCartItemByUserId(userid).getCartItemDetails()) {
            cartItemDetailService.deleteById(cartItD.getId());
        }
        cartWithUser.remove(userid);
        userIdWithCartDetail.remove(userid);
        cartItemService.deleteAll();
        return cartItemDetailService.findAll();
    }

    public void removeWithId(int id) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userid = userService.findUserByEmail(user.getUsername()).getId();
        if (userIdWithCartDetail.get(userid) == null) {
            throw new RuntimeException("Check Item list First");
        } else {
            userIdWithCartDetail.get(userid).removeIf(cartItemDetail -> cartItemDetail.getProduct().getId() == id);
        }

    }


}
