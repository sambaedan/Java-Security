package com.rest.rest.service;

import com.rest.rest.model.user.Users;
import com.rest.rest.pojo.UserPojo;

import java.util.List;
import java.util.Map;

public interface UserService {

     Users checkIt(Long id) throws Exception;
     UserPojo createUser(UserPojo userPojo) throws Exception;
    List<Map<String,Object>> getAllUsers();
    Map<String,Object> getUserById(Long id);
    void deleteUser(Long id);
    void deleteAllUser();
}
