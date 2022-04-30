package com.example.onlineshoppingdemo.controller;

import com.example.onlineshoppingdemo.dao.CategoryDao;
import com.example.onlineshoppingdemo.dao.ProductDao;
import com.example.onlineshoppingdemo.ds.Product;
import com.example.onlineshoppingdemo.service.CategoryService;
import com.example.onlineshoppingdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tep")
public class ShopController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    //show all Product With Using Category;
    //http://localhost:8080/tep/category/products/1
    @GetMapping("/category/product/{id}")
    public List<Product> showProductsByCategoryId(@PathVariable int id){
        return productService.getAllProdcutsByCategoryId(id);
    }
    //http://localhost:8080/tep/products/product/1
    @GetMapping("/products/product/{id}")
    public Product showProductById(@PathVariable int id){
        return productService.getProdcutById(id).get();
    }
}
