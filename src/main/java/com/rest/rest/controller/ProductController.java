package com.rest.rest.controller;


import com.rest.rest.model.product.Product;
import com.rest.rest.pojo.ProductPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/product")
public interface ProductController {

    @PostMapping("/")
    public ResponseEntity<?> createProduct(@RequestBody ProductPojo productPojo);

    @GetMapping("/")
    public List<Product> getAllProducts();
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id);
    @DeleteMapping("/")
    public String deleteAllProducts();
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id);

}
