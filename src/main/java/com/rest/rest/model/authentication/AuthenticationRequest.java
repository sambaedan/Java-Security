package com.rest.rest.model.authentication;

import com.rest.rest.model.user.Role;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class AuthenticationRequest {
    private String user_name;
    private String password;
}

