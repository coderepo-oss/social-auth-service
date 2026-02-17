package com.microservices.auth_service.dto.registeruser;

import com.microservices.auth_service.dto.registeruser.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRegisterRequestDto {

    @NotBlank(message = "missing username..")
    String username;
    @NotBlank(message="missing email..")
    @Email(message = "invalid email..")
    String email;
    @ValidPassword
    String password;
}
