package com.microservices.auth_service.dto.errorresponse;

import lombok.Data;

import java.util.List;

@Data
public class ErrorDto {
    List<String> errorMessages;
}
