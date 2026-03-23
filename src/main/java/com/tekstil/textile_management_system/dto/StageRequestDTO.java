package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.enums.ModelStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StageRequestDTO {

    @NotNull(message = "Stage name cannot be empty")
    private ModelStatus name;

    @NotBlank(message = "Display name cannot be empty")
    @Size(min = 2, max = 50,message = "Display name must be 2-50 characters")
    private String displayName;

    @NotNull(message = "Order Index cannot be empty")
    @Min(value = 0, message = "Order index cannot be less than 0")
    private Integer orderIndex;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 1000, message = "Description must be 10-1000 characters")
    private String description;

    @Size(max = 5000, message = "Checklist cannot exceed 5000 characters")
    private String requiredChecklist;

    @Min(value = 1, message = "Estimated duration must be at least 1 hour")
    @Max(value = 720, message = "Estimated duration cannot exceed 720 hours")
    private Integer estimatedDurationHours;

    private Boolean active = true;

}
