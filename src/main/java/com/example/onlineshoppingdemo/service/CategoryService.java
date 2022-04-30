package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.dao.CategoryDao;
import com.example.onlineshoppingdemo.dao.ProductDao;
import com.example.onlineshoppingdemo.ds.Category;
import com.example.onlineshoppingdemo.ds.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ProductDao productDao;
    private final CategoryDao categoryDao;

    public void initCreatData() {
        Category category = new Category();
        category.setId(1);
        category.setName("Book");
        Category category1 = new Category();
        category.setId(2);
        category.setName("CD");
        Category category2 = new Category();
        category.setId(3);
        category.setName("DVD");
        categoryDao.save(category);
        categoryDao.save(category1);
        categoryDao.save(category2);
        Product product = new Product();
        product.setId(4);
        product.setName("Java");
        product.setDescription("Good For Begin");
        product.setCategory(category);
        product.setImage("SomeTHing");
        product.setPrice(2000);

        Product product3 = new Product();
        product.setId(3);
        product.setName("Python");
        product.setDescription("Good For Begin");
        product.setCategory(category);
        product.setImage("SomeTHing");
        product.setPrice(2000);

        Product product1 = new Product();
        product.setId(1);
        product.setName("BTS");
        product.setDescription("Good For Begin");
        product.setCategory(category1);
        product.setImage("SomeTHing");
        product.setPrice(2000);
        Product product2 = new Product();
        product.setId(2);
        product.setName("Avenger");
        product.setDescription("Good For Begin");
        product.setCategory(category2);
        product.setImage("SomeTHing");
        product.setPrice(2000);
        productDao.save(product);
        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);
    }

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAllCategory() {
        return categoryDao.findAll();
    }

    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryDao.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryDao.findById(id);
    }
}
