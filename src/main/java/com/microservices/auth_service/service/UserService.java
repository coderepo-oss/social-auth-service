package com.microservices.auth_service.service;

import com.microservices.auth_service.config.JwtUtil;
import com.microservices.auth_service.dto.loginuser.UserLoginRequestDto;
import com.microservices.auth_service.dto.loginuser.UserLoginResponseDto;
import com.microservices.auth_service.dto.registeruser.UserRegisterRequestDto;
import com.microservices.auth_service.dto.registeruser.UserRegisterResponseDto;
import com.microservices.auth_service.entity.Users;
import com.microservices.auth_service.exception.AuthServiceException;
import com.microservices.auth_service.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo repo;
    private final JwtUtil jwtUtil;

    public UserService(PasswordEncoder passwordEncoder, UserRepo repo, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto userDto) {
        Users user = new Users();
        if(repo.findByUsername(userDto.getUsername()) !=null){
            throw new AuthServiceException("Username already exists..");
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        repo.save(user);
        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setAcknowledgment("Registered Successfully");
        responseDto.setUsername(userDto.getUsername());
        return responseDto;
    }

    public boolean isValidUser(UserLoginRequestDto userDto) {
        Users user = repo.findByUsername(userDto.getUsername());
        if(user == null){
            throw new AuthServiceException("Invalid Username ..");
        }
        return passwordEncoder.matches((userDto.getPassword()), user.getPassword());
    }

    public UserLoginResponseDto generateJwt(UserLoginRequestDto user){
        UserLoginResponseDto response = new UserLoginResponseDto();
        if(!isValidUser(user)){
            throw new AuthServiceException("Invalid User ..");
        }
        response.setToken(jwtUtil.generateJwtToken(user));
        return response;
    }
}
