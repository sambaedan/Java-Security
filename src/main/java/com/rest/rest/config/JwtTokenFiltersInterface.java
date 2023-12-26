package com.rest.rest.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface JwtTokenFiltersInterface {
    void doFilterInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
