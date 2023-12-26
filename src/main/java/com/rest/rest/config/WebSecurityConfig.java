package com.rest.rest.config;


import com.rest.rest.service.impl.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenFilters jwtTokenFilters;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/order/**").hasRole("ADMIN")
                .antMatchers("/product/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/refresh-token/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/authenticate").permitAll()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .exceptionHandling().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtTokenFilters, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

}
