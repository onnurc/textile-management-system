package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @Email(message = "Enter a valid Email")
    @NotBlank(message = "Email cannot be null")
    @Size(max = 50, message = "email cannot exceed 50 characters")
    private String email;

    @Size(min = 8, max = 64, message = "Password must be at least 8-64 characters long.")
    private String password;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 5, max = 100, message = "Name must be 2-100 characters")
    private String fullName;

    @NotNull(message = "Role cannot be empty")
    private Role role;

    private Boolean active;
}
