package com.rest.rest.pojo;

import com.rest.rest.model.enums.ProductCategoryEnum;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductPojo {
    private Long id;
    private ProductCategoryEnum category;
    private String description;
    private BigDecimal price;

}
