package com.example.onlineshoppingdemo.service;



import com.example.onlineshoppingdemo.dao.CartItemDao;
import com.example.onlineshoppingdemo.ds.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class CartItemService {
    @Autowired
    private CartItemDao cartItemDao;


    public CartItem save(CartItem cartItem){
       return cartItemDao.save(cartItem);
    }

    public List<CartItem> findByUserId(int id){
        return cartItemDao.findByUserId(id);
    }
    public   CartItem findCartItemByUserId(int id){
        return cartItemDao.findCartItemByUserId(id);
    }
    public void deleteAll(){
        cartItemDao.deleteAll();
    }
//    private final Map<Integer, List<CartItemDetail>> userIdWithCartDetail = new HashMap<>();
//    private final Map<Integer, CartItem> cartWithUser = new HashMap<>();
//    private final List<CartItemDetail> details = new ArrayList<>();
//
//
//    public CartItemDetail findById(int id) {
//        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (userIdWithCartDetail.containsKey(userDao.findUserByEmail(user.getUsername()).get().getId())) {
//            for (CartItemDetail detail : userIdWithCartDetail.get(userDao.findUserByEmail(user.getUsername()).get().getId())) {
//                if (detail.getProduct().getId() == id) {
//                    return detail;
//                }
//
//            }
//        } else {
//            userIdWithCartDetail.put(userDao.findUserByEmail(user.getUsername()).get().getId(), new ArrayList<>());
//            for (CartItemDetail detail : userIdWithCartDetail.get(userDao.findUserByEmail(user.getUsername()).get().getId())) {
//                if (detail.getProduct().getId() == id) {
//                    return detail;
//                }
//
//            }
//        }
//
//        return null;
//    }
//
//    public void addProduct(int id, int quantity) {
//        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var userid = userDao.findUserByEmail(user.getUsername()).get().getId();
//        var product = productService.getProdcutById(id).get();
//        CartItemDetail detail = this.findById(product.getId());
//
//        if (userIdWithCartDetail.containsKey(userDao.findUserByEmail(user.getUsername()).get().getId())) {
//            if (detail == null) {
//                detail = new CartItemDetail();
//                detail.setQuantity(quantity);
//                detail.setProduct(product);
//                userIdWithCartDetail.get(userDao.findUserByEmail(user.getUsername()).get().getId()).add(detail);
//
//            } else {
//                int newQuantity = detail.getQuantity() + quantity;
//                if (newQuantity <= 0) {
//                    this.details.remove(detail);
//
//                    cartItemDetailDao.delete(detail);
//                } else {
//                    detail.setQuantity(newQuantity);
//                }
//            }
//        }
//
//        cartItemDetailDao.save(detail);
//
//    }
//
//    public List<CartItem> showALlItem() {
//        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var userid = userDao.findUserByEmail(user.getUsername()).get().getId();
//        var cat = userIdWithCartDetail.get(userid);
//        if (userIdWithCartDetail.get(userid) != null) {
//            if (cartWithUser.containsKey(userid)) {
//                cartWithUser.get(userid).setUser(userDao.findUserByEmail(user.getUsername()).get());
//                cartWithUser.get(userid).setLocalDate(LocalDate.now());
//                cartWithUser.get(userid).setCartItemDetails(cat);
//            } else {
//                cartWithUser.put(userid, new CartItem());
//                cartWithUser.get(userid).setUser(userDao.findUserByEmail(user.getUsername()).get());
//                cartWithUser.get(userid).setLocalDate(LocalDate.now());
//                cartWithUser.get(userid).setCartItemDetails(cat);
//            }
//            cartItemDao.save(cartWithUser.get(userid));
//        }
//        return cartItemDao.findByUserId(userDao.findUserByEmail(user.getUsername()).get().getId());
//    }
//
//    @Transactional
//    public List<CartItemDetail> isEmpty() {
//        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var userid = userDao.findUserByEmail(user.getUsername()).get().getId();
//        for (CartItemDetail cartItD : cartItemDao.findCartItemByUserId(userid).getCartItemDetails()) {
//            cartItemDetailDao.deleteById(cartItD.getId());
//        }
//        cartWithUser.remove(userid);
//        userIdWithCartDetail.remove(userid);
//        cartItemDao.deleteAll();
//        return cartItemDetailDao.findAll();
//    }
//
//    public void removeWithId(int id) {
//        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var userid = userDao.findUserByEmail(user.getUsername()).get().getId();
//        if (userIdWithCartDetail.get(userid) == null){
//            throw new RuntimeException("Check Item list First");
//        }
//        else {
//
//            userIdWithCartDetail.get(userid).removeIf(cartItemDetail -> cartItemDetail.getProduct().getId() == id);
//        }
//
//    }
//
//    public List<CartItemDetail> showCartDetail() {
//        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var userid = userDao.findUserByEmail(user.getUsername()).get().getId();
//        return userIdWithCartDetail.get(userid);
//    }
}
