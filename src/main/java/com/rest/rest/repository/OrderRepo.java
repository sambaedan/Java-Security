package com.rest.rest.repository;

import com.rest.rest.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query(value = "select a.id as order_id, b.id as product_id ,b.description,b.price , " +
                   "c.user_id as user_id , c.user_name from orders a LEFT JOIN " +
                   "product b on a.product_id = b.id LEFT join users c on a" +
                   ".id = c.user_id ",nativeQuery = true)
    List<Map<String, Object>> getAllOrder();

    @Query(value = "select a.id as order_id, b.id as product_id ,b.description,b.price , c.user_id as user_id , c.username" +
            " from " + "orders a LEFT JOIN product b on a.product_id = b.id " +
            "LEFT join users c on a.id = c.user_id WHERE a.id = ?1",nativeQuery = true)
    Map<String, Object> getOrderById(long id);
}
