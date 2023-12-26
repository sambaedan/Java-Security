package com.rest.rest.controller;


import com.rest.rest.pojo.UserPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/user")
public interface UserController {
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserPojo userPojo);
    @GetMapping("/")
    public List<Map<String, Object>> getAllUsers();
    @GetMapping("/{id}")
    public Map<String,Object> getUserById(@PathVariable Long id);
    @DeleteMapping("/")
    public String deleteAllUser();
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id);
}
