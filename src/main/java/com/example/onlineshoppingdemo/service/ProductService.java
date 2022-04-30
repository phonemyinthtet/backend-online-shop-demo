package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.dao.CategoryDao;
import com.example.onlineshoppingdemo.dao.ProductDao;
import com.example.onlineshoppingdemo.ds.Product;
import com.example.onlineshoppingdemo.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    public List<Product> getAllProduct(){
        return productDao.findAll();
    }


    public void addProduct(ProductDto product) {
        var category = categoryDao.findById(product.getCategoryId()).get();
        productDao.save(new Product(product.getId(),product.getName(),product.getPrice(),product.getDescription(),product.getImage(),category));
    }

    public void removeProductById(int id){
        productDao.deleteById(id);
    }
    public Optional<Product> getProdcutById(int id){
        return productDao.findById(id);
    }
    public List<Product> getAllProdcutsByCategoryId(int id){
        return productDao.findAllByCategoryId(id);
    }


}
