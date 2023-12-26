package com.rest.rest.service.impl;

import com.rest.rest.model.product.Product;
import com.rest.rest.pojo.ProductPojo;
import com.rest.rest.repository.ProductRepo;
import com.rest.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product checkIt(Long id) throws Exception {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent())
            return optionalProduct.get();
        throw new Exception("Id not found");
    }
  public  ProductPojo createProduct(ProductPojo productPojo) throws Exception {
      Product product;
      if (productPojo.getId() != null)
          product = checkIt(productPojo.getId());
      else
          product = new Product();
      product.setCategory(productPojo.getCategory());
      product.setDescription(product.getDescription());
      Product product1 = productRepo.save(product);
      productPojo.setId(product1.getId());
      return productPojo;
    }

  public List<Product> getAllProducts(){
      return productRepo.findAll();
  }

  public Product getProductById(Long id){
      Optional<Product> optionalProduct = productRepo.findById(id);
      return optionalProduct.orElse(null);
  }

  public void deleteProduct(long id)
  {
      productRepo.deleteById(id);
  }

  public void deleteAllProducts() {
      productRepo.deleteAll();
    }
}
