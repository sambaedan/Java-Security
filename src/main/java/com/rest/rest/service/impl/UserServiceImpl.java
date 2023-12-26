package com.rest.rest.service.impl;
import com.rest.rest.model.user.Users;
import com.rest.rest.pojo.UserPojo;
import com.rest.rest.repository.UserRepo;
import com.rest.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
   private PasswordEncoder passwordEncoder;
    boolean status = true;

    public Users checkIt(Long id) throws Exception {
        Optional<Users> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent())
            return optionalUser.get();
        throw new Exception("User Not Found");
    }

    public UserPojo createUser(UserPojo userPojo) throws Exception {
        Users user;
        if(userPojo.getId() != null)
            user = checkIt(userPojo.getId());
        else
            user = new Users();
        user.setUsername(userPojo.getUser_name());
        user.setRating(user.getRating());
        user.setPhone(userPojo.getContact_number());
        user.setPassword(passwordEncoder.encode(userPojo.getPassword()));
        user.setRole(userPojo.getRole());
        user.setDateOfRegistration(userPojo.getDor());
        user.setEmailId(userPojo.getEmail());
        user.setUserType(userPojo.getUser_type());
        Users savedUser = userRepo.save(user);
        userPojo.setId(savedUser.getId());
        return userPojo;
    }

    public List<Map<String,Object>> getAllUsers()
    {
        return userRepo.getAllUser();
    }
    public Map<String,Object> getUserById(Long id){
        return userRepo.getUserById(id);
    }
    public void deleteUser(Long id)
    {
        status = false;
    }
    public void deleteAllUser()
    {
        status = false;
    }

}
