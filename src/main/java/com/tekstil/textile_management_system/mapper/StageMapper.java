package com.tekstil.textile_management_system.mapper;


import com.tekstil.textile_management_system.dto.StageRequestDTO;
import com.tekstil.textile_management_system.dto.StageResponseDTO;
import com.tekstil.textile_management_system.entity.Stage;

public class StageMapper {

    public static Stage toEntity(StageRequestDTO dto){
        Stage stage = new Stage();

        stage.setActive(dto.getActive());
        stage.setDescription(dto.getDescription());
        stage.setDisplayName(dto.getDisplayName());
        stage.setEstimatedDurationHours(dto.getEstimatedDurationHours());
        stage.setName(dto.getName());
        stage.setOrderIndex(dto.getOrderIndex());
        stage.setRequiredChecklist(dto.getRequiredChecklist());
        return stage;
    }

    public static StageResponseDTO toResponseDTO(Stage stage){
        StageResponseDTO dto = new StageResponseDTO();

        dto.setActive(stage.getActive());
        dto.setDescription(stage.getDescription());
        dto.setDisplayName(stage.getDisplayName());
        dto.setId(stage.getId());
        dto.setEstimatedDurationHours(stage.getEstimatedDurationHours());
        dto.setOrderIndex(stage.getOrderIndex());
        dto.setRequiredChecklist(stage.getRequiredChecklist());
        dto.setName(stage.getName());

        return dto;
    }

}
