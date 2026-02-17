package com.microservices.auth_service.dto.loginuser;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank(message = "missing username..")
    String username;
    @NotBlank(message = "missing password..")
    String password;
}
