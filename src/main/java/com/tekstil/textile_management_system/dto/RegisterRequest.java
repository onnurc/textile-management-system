package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @Email(message = "Enter an email")
    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 64, message = "password must be 8-64 character")
    private String password;

    @NotBlank(message = "name-surname cannot be empty")
    private String fullName;

}
