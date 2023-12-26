package com.rest.rest.repository;

import com.rest.rest.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepo extends JpaRepository<Product,Long> {



}

