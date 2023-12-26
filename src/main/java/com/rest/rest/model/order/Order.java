package com.rest.rest.model.order;

import com.rest.rest.model.product.Product;
import com.rest.rest.model.user.Users;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    Users user;
}
