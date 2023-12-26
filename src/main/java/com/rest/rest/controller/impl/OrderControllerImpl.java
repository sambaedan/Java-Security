package com.rest.rest.controller.impl;

import com.rest.rest.controller.OrderController;
import com.rest.rest.pojo.OrderPojo;
import com.rest.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderControllerImpl implements OrderController {
    @Autowired
    private  OrderService orderservice;

    public ResponseEntity<?>createOrder(@RequestBody OrderPojo orderpojo) {
        try {
            return ResponseEntity.ok().body(orderservice.createOrder(orderpojo));
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.status(500).body("Error");
        }
    }

    public List<Map<String,Object>> getAllOrder()
    {
        return orderservice.getAllOrder();
    }

    public Map<String,Object> getOrderById(@PathVariable long id)
    {
        return orderservice.getOrderById(id);
    }

    @DeleteMapping("/")
    public String deleteAllOrder()
    {  orderservice.deleteAllOrder();
        return "All orders have been deleted successfully";
    }

    public String deleteOrderBYId(@PathVariable long id)
    {
        orderservice.deleteOrderById(id);
        return id + "deleted successfully";
    }

}
