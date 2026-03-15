package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StageHistoryRequestDTO {

    private Long modelId;
    private Long stageId;
    private Long assignedUserId;
    private LocalDateTime estimatedEndDate;
    private String status;
    private String waitingReason;
    private String checklistProgress;
    private String notes;

}
