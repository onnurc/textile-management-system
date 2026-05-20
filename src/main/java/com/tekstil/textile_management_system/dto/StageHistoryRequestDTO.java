package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.entity.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StageHistoryRequestDTO {

    @NotNull(message = "Model ID cannot be empty")
    @Positive(message = "Model ID must be a positive number")
    private Long modelId;

    @NotNull(message = "Stage ID cannot be empty")
    @Positive(message = "Stage ID must be a positive number")
    private Long stageId;

    @Positive(message = "Assigned User ID must be a positive number")
    private Long assignedUserId;

    @Future(message = "Estimated end date must be in the future")
    private LocalDateTime estimatedEndDate;

    @NotNull(message = "Status cannot be null")
    private String status;

    @Size(max = 1000,message = "Waiting reason cannot be longer than 1000 characters")
    private String waitingReason;

    @Size(max = 5000, message = "Checklist progress too long")
    private String checklistProgress;

    @Size(max = 1000,message = "Note cannot be longer than 1000 characters")
    private String notes;

}
