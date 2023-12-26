package com.rest.rest.model.product;


import com.rest.rest.model.enums.ProductCategoryEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Product {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ProductCategoryEnum category;

    @Column(name = "description")
    private String description;

    @Column(name = "price", columnDefinition = "decimal (10,2)")
    private BigDecimal price;

}
