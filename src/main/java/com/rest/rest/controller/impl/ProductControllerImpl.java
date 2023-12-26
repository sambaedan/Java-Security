package com.rest.rest.controller.impl;

import com.rest.rest.controller.ProductController;
import com.rest.rest.model.product.Product;
import com.rest.rest.pojo.ProductPojo;
import com.rest.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class ProductControllerImpl implements ProductController {
   @Autowired
   private ProductService productservice;
   public ResponseEntity<?> createProduct(@RequestBody ProductPojo productPojo) {
        try {
            return ResponseEntity.ok().body(productservice.createProduct(productPojo));
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.status(500).body("Error");
        }
   }
   public List<Product> getAllProducts(){
       return productservice.getAllProducts();
   }

    public Product getProductById(@PathVariable Long id) {
        return productservice.getProductById(id);
    }
    public String deleteAllProducts() {
        productservice.deleteAllProducts();
        return "All users have been deleted successfully.";
    }

    public void deleteProduct(@PathVariable Long id) {
        productservice.deleteProduct(id);
    }

    
}
