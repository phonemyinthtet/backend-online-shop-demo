package com.example.onlineshoppingdemo.controller;

import com.example.onlineshoppingdemo.ds.Category;
import com.example.onlineshoppingdemo.ds.Product;
import com.example.onlineshoppingdemo.dto.ProductDto;
import com.example.onlineshoppingdemo.service.CategoryService;
import com.example.onlineshoppingdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @PostConstruct
    public void iniCreateProductAndCategory(){
        categoryService.initCreatData();
    }
        /*CATEGORY*/
    //http://localhost:8080/api/admin/categories
    @GetMapping("/categories")

    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    //http://localhost:8080/api/admin/add/category
    @PostMapping("/add/category")

    public void process(@RequestBody Category category){
        categoryService.addCategory(category);
    }
    //http://localhost:8080/api/admin/
    @GetMapping("/delete/category/{id}")
    public void deleteCategoryById(@PathVariable int id){
        categoryService.deleteCategoryById(id);
    }
    //http://localhost:8080/api/admin/update/category/1
    @PatchMapping("/update/category/{id}")
    public void updateCategoryById(@PathVariable int id,@RequestBody Category updateCategory){
        var oldCategory = categoryService.getCategoryById(id);
            updateCategory.setId(oldCategory.get().getId());
            categoryService.addCategory(updateCategory);
    }
        /*PRODUCT SECTION*/
    //http://localhost:8080/api/admin/products
    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    //http://localhost:8080/api/admin/add/product
    @PostMapping("/add/product")
    public void process(@RequestBody ProductDto product){
        productService.addProduct(product);
    }
    //http://localhost:8080/api/admin/delete/product/1
    @DeleteMapping("/delete/product/{id}")
    public void deleteProductById(int id){
        productService.removeProductById(id);
    }
    @PatchMapping("/update/product/{id}")
    public void updateProductById(@PathVariable int id ,@RequestBody ProductDto product){
        var oldProduct = productService.getProdcutById(id);
        product.setId(oldProduct.get().getId());
        productService.addProduct(product);
    }
}
