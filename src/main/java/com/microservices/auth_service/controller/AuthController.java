package com.microservices.auth_service.controller;

import com.microservices.auth_service.config.JwtUtil;
import com.microservices.auth_service.dto.loginuser.UserLoginRequestDto;
import com.microservices.auth_service.dto.loginuser.UserLoginResponseDto;
import com.microservices.auth_service.dto.registeruser.UserRegisterRequestDto;
import com.microservices.auth_service.dto.registeruser.UserRegisterResponseDto;
import com.microservices.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/testconnection")
    public ResponseEntity<String> testConnection() {
        return ResponseEntity.ok("Hi !!!");
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@Valid @RequestBody UserRegisterRequestDto user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/auth/current-user")
    public String getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto user){
        return ResponseEntity.ok(userService.generateJwt(user));
    }
}
