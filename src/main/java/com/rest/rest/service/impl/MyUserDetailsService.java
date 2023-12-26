package com.rest.rest.service.impl;

import com.rest.rest.model.user.MyUserDetails;
import com.rest.rest.model.user.Users;
import com.rest.rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
@Autowired
    private UserRepo userrepo;
    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userrepo.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException(username + "not found");
        }
        return new MyUserDetails(user);
    }
}
