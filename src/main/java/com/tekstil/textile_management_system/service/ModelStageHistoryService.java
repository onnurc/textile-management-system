package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.dto.StageHistoryRequestDTO;
import com.tekstil.textile_management_system.dto.StageHistoryResponseDTO;
import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.mapper.StageHistoryMapper;
import com.tekstil.textile_management_system.repository.ModelRepository;
import com.tekstil.textile_management_system.repository.ModelStageHistoryRepository;
import com.tekstil.textile_management_system.repository.StageRepository;
import com.tekstil.textile_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelStageHistoryService {

    private final ModelStageHistoryRepository modelStageHistoryRepository;

    private final ModelRepository modelRepository;
    private final StageRepository stageRepository;
    private final UserRepository userRepository;



    public StageHistoryResponseDTO createStageHistory(StageHistoryRequestDTO dto) {

        Model model = modelRepository.findById(dto.getModelId())
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        Stage stage = stageRepository.findById(dto.getStageId())
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found"));

        User user = userRepository.findById(dto.getAssignedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ModelStageHistory history = StageHistoryMapper.toEntity(dto);
        history.setModel(model);
        history.setStage(stage);
        history.setAssignedUser(user);

        ModelStageHistory saved = modelStageHistoryRepository.save(history);
        return StageHistoryMapper.toResponseDTO(saved);
    }
    public StageHistoryResponseDTO getStageHistoryById(Long id) {
        return StageHistoryMapper.toResponseDTO(
                modelStageHistoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Stage history not found: " + id))
        );
    }

    public List<StageHistoryResponseDTO> findAll() {
        return modelStageHistoryRepository.findAll()
                .stream()
                .map(StageHistoryMapper::toResponseDTO)
                .toList();

    }
    public List<StageHistoryResponseDTO> findByModelId(Long modelId) {
        return modelStageHistoryRepository.findByModel_IdOrderByCompletedAtDesc(modelId)
                .stream()
                .map(StageHistoryMapper::toResponseDTO)
                .toList();
    }

    public List<StageHistoryResponseDTO> findByStage(Long stageId) {
        return modelStageHistoryRepository.findByStage_Id(stageId)
                .stream()
                .map(StageHistoryMapper::toResponseDTO)
                .toList();
    }

    public List<StageHistoryResponseDTO> findByAssignedUser(String userName) {
        return modelStageHistoryRepository.findByAssignedUserOrderByCompletedAtDesc(userName)
                .stream()
                .map(StageHistoryMapper::toResponseDTO)
                .toList();
    }
    public List<ModelStageHistory> findByStatus(String status) {
        return modelStageHistoryRepository.findByStatus(status);
    }

    public StageHistoryResponseDTO updateStageHistory(Long id, StageHistoryRequestDTO updated) {
        ModelStageHistory existing = modelStageHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage history not found: " + id));

        Stage stage = stageRepository.findById(updated.getStageId())
                .orElseThrow(()-> new ResourceNotFoundException("No stage found with this id: " + id));

        User user = userRepository.findById(updated.getAssignedUserId())
                        .orElseThrow(()-> new ResourceNotFoundException("No user found with this id: " + id));

        existing.setStage(stage);
        existing.setAssignedUser(user);
        existing.setStatus(updated.getStatus());
        existing.setEstimatedEndDate(updated.getEstimatedEndDate());
        existing.setWaitingReason(updated.getWaitingReason());
        existing.setChecklistProgress(updated.getChecklistProgress());
        existing.setNotes(updated.getNotes());
        existing.preUpdate();

        return StageHistoryMapper.toResponseDTO(modelStageHistoryRepository.save(existing));
    }
    public void deleteStageHistory(Long id) {
        modelStageHistoryRepository.deleteById(id);
    }

    public boolean existsByModelIdAndStageId(Long modelId, Long stageId) {
        return modelStageHistoryRepository.existsByModelIdAndStageId(modelId,stageId);
    }
}