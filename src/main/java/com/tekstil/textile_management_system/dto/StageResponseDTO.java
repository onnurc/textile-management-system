package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.enums.ModelStatus;
import lombok.Data;

@Data
public class StageResponseDTO {

    private Long id;
    private ModelStatus name;
    private String displayName;
    private Integer orderIndex;
    private String description;
    private String requiredChecklist;
    private Integer estimatedDurationHours;
    private Boolean active;






}
