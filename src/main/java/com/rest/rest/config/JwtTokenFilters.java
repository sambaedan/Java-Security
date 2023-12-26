package com.rest.rest.config;

import com.rest.rest.model.user.MyUserDetails;
import com.rest.rest.service.impl.MyUserDetailsService;
import com.rest.rest.service.jwtService.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component

public class JwtTokenFilters extends OncePerRequestFilter  {
@Autowired
    private  MyUserDetailsService userDetailsService;
@Autowired
    private  JwtUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            MyUserDetails userDetails;
            userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
         filterChain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }




}
