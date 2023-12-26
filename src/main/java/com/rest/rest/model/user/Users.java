package com.rest.rest.model.user;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
@Component
public class Users  {

    @Id
    @Column( name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name="user_type")
    private String userType;

    @Column(name="email")
    private String emailId;

    @Column(name = "contact_number")
    private String phone;

    @Column(name="rating")
    private Integer rating;

    @Column(name="dor")
    private Date dateOfRegistration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}

