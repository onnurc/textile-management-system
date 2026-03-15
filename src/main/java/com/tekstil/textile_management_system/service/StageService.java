package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.dto.StageRequestDTO;
import com.tekstil.textile_management_system.dto.StageResponseDTO;
import com.tekstil.textile_management_system.dto.UserResponseDTO;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.exception.AlreadyExistsException;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.mapper.StageMapper;
import com.tekstil.textile_management_system.repository.StageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StageService {



    private final StageRepository stageRepository;

    public StageResponseDTO createStage(StageRequestDTO dto) {
        if (stageRepository.findByName(dto.getName()).isPresent()){
            throw new AlreadyExistsException("Stage already taken");
        }

        Stage stage = StageMapper.toEntity(dto);
        Stage saved = stageRepository.save(stage);
        return StageMapper.toResponseDTO(saved);

    }

    public Stage getStageById(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found: " + id));
    }

    public StageResponseDTO getStageByName(ModelStatus name) {
        return stageRepository.findByName(name)
                .map(StageMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found: " + name));
    }

    public List<StageResponseDTO> findAll() {

        return stageRepository.findAll()
                .stream()
                .map(StageMapper::toResponseDTO)
                .toList();
    }

    public List<StageResponseDTO> findActiveStages() {

        return stageRepository.findByActiveTrue()
                .stream()
                .map(StageMapper::toResponseDTO)
                .toList();
    }

    public List<StageResponseDTO> findActiveStagesOrdered() {
        return stageRepository.findByActiveTrueOrderByOrderIndexAsc()
                .stream()
                .map(StageMapper::toResponseDTO)
                .toList();
    }

    public List<StageResponseDTO> findByChecklist(String keyword) {
        return stageRepository.findByRequiredChecklistContainingIgnoreCase(keyword)
                .stream()
                .map(StageMapper::toResponseDTO)
                .toList();
    }

    public List<StageResponseDTO> findByEstimatedDurationLessThan(Integer hours) {
        return stageRepository.findByEstimatedDurationHoursLessThan(hours)
                .stream()
                .map(StageMapper::toResponseDTO)
                .toList();
    }

    public StageResponseDTO updateStage(Long id, StageRequestDTO updated) {
        Stage existing = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found: " + id));

        existing.setName(updated.getName());
        existing.setDisplayName(updated.getDisplayName());
        existing.setOrderIndex(updated.getOrderIndex());
        existing.setDescription(updated.getDescription());
        existing.setRequiredChecklist(updated.getRequiredChecklist());
        existing.setEstimatedDurationHours(updated.getEstimatedDurationHours());
        existing.setActive(updated.getActive());

        return StageMapper.toResponseDTO(stageRepository.save(existing));
    }

    public void deleteStage(Long id) {
        if (!stageRepository.existsById(id)){
            throw new ResourceNotFoundException("Stage not found with id : " +id);
        }
        stageRepository.deleteById(id);
    }

    public StageResponseDTO findById(Long id) {

        return stageRepository.findById(id)
                .map(StageMapper::toResponseDTO)
                .orElseThrow(()-> new ResourceNotFoundException("No stage with this id: " + id));

    }

    public Optional<StageResponseDTO> findStageByName(ModelStatus name) {
        return stageRepository.findByName(name)
                .map(StageMapper::toResponseDTO);
    }
}