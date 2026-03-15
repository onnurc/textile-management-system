package com.tekstil.textile_management_system.mapper;

import com.tekstil.textile_management_system.dto.ModelRequestDTO;
import com.tekstil.textile_management_system.dto.ModelResponseDTO;
import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.enums.ModelStatus;

import java.time.LocalDateTime;

public class ModelMapper {

    public static Model toEntity(ModelRequestDTO dto){
        Model model = new Model();
        model.setModelName(dto.getModelName());
        model.setBrand(dto.getBrand());
        model.setSeason(dto.getSeason());
        model.setCategory(dto.getCategory());
        model.setDescription(dto.getDescription());
        model.setPriority(dto.getPriority());
        model.setSizeRange(dto.getSizeRange());
        model.setDeadline(dto.getDeadline());
        model.setNotes(dto.getNotes());
        model.setStatus(ModelStatus.IN_DESIGN);
        model.setCreatedAt(LocalDateTime.now());
        return model;
    }
    public static ModelResponseDTO toResponseDTO(Model model) {
        ModelResponseDTO dto = new ModelResponseDTO();
        dto.setId(model.getId());
        dto.setModelName(model.getModelName());
        dto.setBrand(model.getBrand());
        dto.setSeason(model.getSeason());
        dto.setCategory(model.getCategory());
        dto.setDescription(model.getDescription());
        dto.setStatus(model.getStatus());
        dto.setPriority(model.getPriority());
        dto.setSizeRange(model.getSizeRange());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        dto.setDeadline(model.getDeadline());
        dto.setNotes(model.getNotes());
        if (model.getAssignedTo() != null) {
            dto.setAssignedToUserId(model.getAssignedTo().getId());
            dto.setAssignedToFullName(model.getAssignedTo().getFullName());
        }
        return dto;
    }




}
