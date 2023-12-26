package com.rest.rest.controller;

import com.rest.rest.pojo.OrderPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/order")
public interface OrderController {
    @PostMapping("/")
    ResponseEntity<?> createOrder(@RequestBody OrderPojo orderpojo);
    @GetMapping("/")
     List<Map<String,Object>> getAllOrder();
    @GetMapping("/{id}")
     Map<String,Object> getOrderById(@PathVariable long id);
    @DeleteMapping("/{id}")
     String deleteOrderBYId(@PathVariable long id);
}
