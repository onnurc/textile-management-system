package com.tekstil.textile_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "please enter a valid email")
    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password ;

}
