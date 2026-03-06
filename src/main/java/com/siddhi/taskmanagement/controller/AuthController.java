package com.siddhi.taskmanagement.controller;

import com.siddhi.taskmanagement.dto.AuthRequest;
import com.siddhi.taskmanagement.dto.AuthResponse;
import com.siddhi.taskmanagement.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/auth/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            String role = authentication.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();

            String token = jwtUtil.generateToken(request.getEmail(), role);

            AuthResponse response = new AuthResponse();
            response.setToken(token);
            return response;


        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
