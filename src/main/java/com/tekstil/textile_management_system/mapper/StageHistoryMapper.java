package com.tekstil.textile_management_system.mapper;

import com.tekstil.textile_management_system.dto.StageHistoryRequestDTO;
import com.tekstil.textile_management_system.dto.StageHistoryResponseDTO;
import com.tekstil.textile_management_system.entity.ModelStageHistory;

public class StageHistoryMapper {

    public static ModelStageHistory toEntity(StageHistoryRequestDTO dto){
        ModelStageHistory history = new ModelStageHistory();
        history.setChecklistProgress(dto.getChecklistProgress());
        history.setEstimatedEndDate(dto.getEstimatedEndDate());
        history.setStatus(dto.getStatus());
        history.setWaitingReason(dto.getWaitingReason());
        history.setNotes(dto.getNotes());

        return history;
    }
    public static StageHistoryResponseDTO toResponseDTO(ModelStageHistory history){
        StageHistoryResponseDTO dto = new StageHistoryResponseDTO();
        dto.setId(history.getId());
        dto.setModelId(history.getModel().getId());
        dto.setStageId(history.getStage().getId());
        dto.setAssignedUserId(history.getAssignedUser().getId());
        dto.setStatus(history.getStatus());
        dto.setCompletedAt(history.getCompletedAt());
        dto.setEstimatedEndDate(history.getEstimatedEndDate());
        dto.setWaitingReason(history.getWaitingReason());
        dto.setChecklistProgress(history.getChecklistProgress());
        dto.setNotes(history.getNotes());
        return dto;
    }

}
