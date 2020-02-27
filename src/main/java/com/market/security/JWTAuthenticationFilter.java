package com.market.security;

import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.Filter;

public class JWTAuthenticationFilter implements Filter {
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    }
}
