package com.rest.rest.controller.impl;
import com.rest.rest.controller.UserController;
import com.rest.rest.pojo.UserPojo;
import com.rest.rest.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserServiceImpl userService;
    public ResponseEntity<?> createUser(@RequestBody UserPojo userPojo) {
        try {
            return ResponseEntity.ok().body(userService.createUser(userPojo));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(500).body("Error");
        }
    }
    public List<Map<String, Object>> getAllUsers() {
        return  userService.getAllUsers();
    }

    public Map<String,Object> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    public String deleteAllUser() {
        userService.deleteAllUser();
        return "All users have been deleted successfully.";
    }
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return id + " is deleted";
    }

}
