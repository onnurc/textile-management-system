package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ModelRequestDTO {

    @NotBlank(message = "Model name cannot be empty")
    private String modelName;

    @NotBlank(message = "Brand name cannot be empty")
    private String brand;

    @NotBlank(message = "Season name cannot be empty")
    private String season;
    @NotBlank(message = "Category name cannot be empty")
    private String category;

    @Size(max = 2000, message = "Description cannot be longer than 2000 characters")
    private String description;

    private Priority priority = Priority.NORMAL;

    @NotBlank(message = "Size range cannot be empty")

    private String sizeRange;

    private LocalDateTime deadline;

    @Size(max = 2000, message = "Note cannot be longer than 2000 characters")
    private String notes;

    private ModelStatus status;

    private Long assignedToUserId;

}
