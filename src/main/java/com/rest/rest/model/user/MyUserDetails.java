package com.rest.rest.model.user;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Component
public class MyUserDetails implements UserDetails {

   @Autowired
    private final Users user;

    public MyUserDetails(Users user) {
        this.user = user;
        System.out.println("user in userdetails implementation" + " " + user.getUsername() + " " + user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grant = new ArrayList<>();
        grant.add(new SimpleGrantedAuthority("ROLE_" + this.user.getRole().getRoleName().toUpperCase()));
        grant.forEach(a -> System.out.println(a.getAuthority()));
        return grant;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
