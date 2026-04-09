package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.enums.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ModelResponseDTO {

    private Long id;
    private String modelName;
    private String brand;
    private String season;
    private String category;
    private String description;
    private ModelStatus status;
    private Priority priority;
    private String sizeRange;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deadline;
    private String notes;
    private Long assignedToUserId;
    private String assignedToFullName;

}


