package com.rest.rest.pojo;

import com.rest.rest.model.user.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UserPojo {
    private Long id;
    private String user_name;
    private String password;
    private String user_type;
    private String email;
    private Integer rating;
    private Date dor;
    private Role role;
    private String contact_number;
}
