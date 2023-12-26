package com.rest.rest.service;

import com.rest.rest.model.product.Product;
import com.rest.rest.pojo.ProductPojo;

import java.util.List;

public interface ProductService {
    Product checkIt(Long id) throws Exception;
    ProductPojo createProduct(ProductPojo productPojo) throws Exception;
    List<Product> getAllProducts();
    Product getProductById(Long id);

    void deleteProduct(long id);

    void deleteAllProducts();
}
