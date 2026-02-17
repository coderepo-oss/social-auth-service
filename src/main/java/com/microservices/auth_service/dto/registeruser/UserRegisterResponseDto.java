package com.microservices.auth_service.dto.registeruser;

import lombok.Data;

@Data
public class UserRegisterResponseDto {
    String username;
    String acknowledgment;
}
