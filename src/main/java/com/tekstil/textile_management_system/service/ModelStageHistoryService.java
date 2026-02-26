package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.repository.ModelRepository;
import com.tekstil.textile_management_system.repository.ModelStageHistoryRepository;
import com.tekstil.textile_management_system.repository.StageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelStageHistoryService {

    private final ModelStageHistoryRepository modelStageHistoryRepository;

    private final ModelRepository modelRepository;
    private final StageRepository stageRepository;

    public ModelStageHistory createStageHistory(ModelStageHistory stageHistory) {
        Model model = modelRepository.findById(stageHistory.getModel().getId())
                .orElseThrow(() -> new RuntimeException("Model not found"));
        Stage stage = stageRepository.findById(stageHistory.getStage().getId())
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        stageHistory.setModel(model);
        stageHistory.setStage(stage);
        stageHistory.preUpdate();

        return modelStageHistoryRepository.save(stageHistory);
    }

    public Optional<ModelStageHistory> getStageHistoryById(Long id) {
        return Optional.of(modelStageHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage history not found: " + id)));
    }

    public List<ModelStageHistory> findAll() {
        return modelStageHistoryRepository.findAll();
    }

    public List<ModelStageHistory> findByModelId(Long modelId) {
        return modelStageHistoryRepository.findByModel_IdOrderByCompletedAtDesc(modelId);
    }

    public List<ModelStageHistory> findByStage(Long stageId) {
        return modelStageHistoryRepository.findByStage_Id(stageId);
    }

    public List<ModelStageHistory> findByAssignedUser(String userName) {
        return modelStageHistoryRepository.findByAssignedUserOrderByCompletedAtDesc(userName);
    }

    public List<ModelStageHistory> findByStatus(String status) {
        return modelStageHistoryRepository.findByStatus(status);
    }

    public ModelStageHistory updateStageHistory(Long id, ModelStageHistory updated) {
        ModelStageHistory existing = modelStageHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage history not found: " + id));

        existing.setStage(updated.getStage());
        existing.setAssignedUser(updated.getAssignedUser());
        existing.setStatus(updated.getStatus());
        existing.setEstimatedEndDate(updated.getEstimatedEndDate());
        existing.setWaitingReason(updated.getWaitingReason());
        existing.setChecklistProgress(updated.getChecklistProgress());
        existing.setNotes(updated.getNotes());
        existing.preUpdate();

        return modelStageHistoryRepository.save(existing);
    }

    public void deleteStageHistory(Long id) {
        modelStageHistoryRepository.deleteById(id);
    }

    public boolean existsByModelIdAndStageId(Long id, Long id1) {
        return modelStageHistoryRepository.existsByModelIdAndStageId(id,id1);
    }



}