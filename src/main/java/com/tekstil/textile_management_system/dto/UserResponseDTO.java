package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {

    private Long id ;
    private String email;
    private String fullName;
    private Role role;
    private Boolean active;
    private LocalDate createdAt;



}
