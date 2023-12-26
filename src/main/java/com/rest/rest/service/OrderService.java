package com.rest.rest.service;

import com.rest.rest.pojo.OrderPojo;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderPojo createOrder(OrderPojo orderPojo) throws Exception;
    List<Map<String, Object>> getAllOrder();
    Map<String, Object> getOrderById(long id);
    void deleteAllOrder();
    void deleteOrderById(long id);
}
