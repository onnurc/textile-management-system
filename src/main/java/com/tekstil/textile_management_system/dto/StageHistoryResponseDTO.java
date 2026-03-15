package com.tekstil.textile_management_system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StageHistoryResponseDTO {


    private Long id;
    private Long modelId;
    private Long stageId;
    private Long assignedUserId;
    private LocalDateTime completedAt;
    private LocalDateTime estimatedEndDate;
    private String status;
    private String waitingReason;
    private String checklistProgress;
    private String notes;

}
