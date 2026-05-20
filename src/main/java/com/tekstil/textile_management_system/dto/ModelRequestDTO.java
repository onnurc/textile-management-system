package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.enums.Priority;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ModelRequestDTO {

    @Size(min = 5, max = 30,message = "Model name must be 5-30 characters")
    @NotBlank(message = "Model name cannot be empty")
    private String modelName;

    @NotBlank(message = "Brand name cannot be empty")
    @Size(max = 50)
    private String brand;

    @NotBlank(message = "Season name cannot be empty")
    @Size(min = 5, max = 50,message = "Season name must be 5-50 characters")
    private String season;

    @NotBlank(message = "Category name cannot be empty")
    @Size(min = 2, max = 50,message = "Season name must be 5-50 characters")
    private String category;

    @Size(max = 2000, message = "Description cannot be longer than 2000 characters")
    private String description;

    private Priority priority = Priority.NORMAL;

    @NotBlank(message = "Size range cannot be empty")
    private String sizeRange;

    @Future(message = "Deadline must be a future date" )
    private LocalDateTime deadline;

    @Size(max = 2000, message = "note cannot be longer than 2000 characters")
    private String notes;

    private ModelStatus status;

    @Min(value = 1, message = "Assigned user ID must be valid")
    private Long assignedToUserId;

}
