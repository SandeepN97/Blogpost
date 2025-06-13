package com.sandeep.blog.blogapplication.controller;

import com.sandeep.blog.blogapplication.jwt.JwtUtils;
import com.sandeep.blog.blogapplication.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateTokenFromUsername(userDetails);
            Map<String, String> body = new HashMap<>();
            body.put("token", token);
            return ResponseEntity.ok(body);
        } catch (AuthenticationException e) {
            logger.warn("Login failed for user {}: {}", request.getUsername(), e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}
