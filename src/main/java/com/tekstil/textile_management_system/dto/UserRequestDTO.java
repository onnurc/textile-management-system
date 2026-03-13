package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @Email(message = "Enter a valid Email")
    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 6 ,message ="Password must be at least 6 characters long." )
    private String password;

    @NotBlank(message = "Name cannot be empty")
    private String fullName;

    @NotNull(message = "Role cannot be empty")
    private Role role ;



}
