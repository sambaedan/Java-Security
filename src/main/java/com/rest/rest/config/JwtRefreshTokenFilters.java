package com.rest.rest.config;

import com.rest.rest.model.authentication.AuthenticationRequest;
import com.rest.rest.model.authentication.AuthenticationResponse;
import com.rest.rest.model.user.MyUserDetails;
import com.rest.rest.service.impl.MyUserDetailsService;
import com.rest.rest.service.jwtService.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping(value = "/")
@Component
public class JwtRefreshTokenFilters implements JwtTokenFiltersInterface {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        {
            String refreshToken = resolveToken(request);
            if (refreshToken != null && jwtTokenUtil.validateToken(refreshToken)) {
                MyUserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(refreshToken));
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}
