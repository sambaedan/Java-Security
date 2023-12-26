package com.rest.rest.controller;

import com.rest.rest.model.authentication.AuthenticationRequest;
import com.rest.rest.model.authentication.AuthenticationResponse;
import com.rest.rest.model.user.MyUserDetails;
import com.rest.rest.model.user.Role;
import com.rest.rest.service.impl.MyUserDetailsService;
//import com.rest.rest.service.jwtService.jwtUtil;
import com.rest.rest.service.jwtService.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.FetchType;


@RestController
@RequestMapping(value = "/")
public class AuthenticationController {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping(value = "authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("Received request for authentication");
        try {
            authenticationRequest.setUser_name(authenticationRequest.getUser_name());
            authenticationRequest.setPassword((authenticationRequest.getPassword()));


        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials: " + e.getMessage());
            throw new Exception("Incorrect Username or password", e);
        }

        final MyUserDetails myUserDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUser_name());
        final String accessToken = jwtTokenUtil.generateToken(myUserDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(myUserDetails);
        System.out.println("Returning access token: " + accessToken);
        System.out.println("Returning refresh token: " + refreshToken);
        return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));
    }
    @PostMapping(value ="/refresh-token")
    public ResponseEntity<?> createNewAccessToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("Received request for authentication");
        try {
            authenticationRequest.setUser_name(authenticationRequest.getUser_name());
            authenticationRequest.setPassword((authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials: " + e.getMessage());
            throw new Exception("Incorrect Username or password", e);
        }
        final MyUserDetails myUserDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUser_name());
        final String accessToken = jwtTokenUtil.generateToken(myUserDetails);
        System.out.println("Returning access token: " + accessToken);
        String refreshToken = null;
        return ResponseEntity.ok(new AuthenticationResponse(accessToken,refreshToken));
    }

}
