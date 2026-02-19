package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.repository.ModelStageHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelStageHistoryService {

    private final ModelStageHistoryRepository modelStageHistoryRepository;

    public ModelStageHistory createStageHistory(ModelStageHistory stageHistory) {
        stageHistory.preUpdate();
        return modelStageHistoryRepository.save(stageHistory);
    }

    public ModelStageHistory getStageHistoryById(Long id) {
        return modelStageHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage history not found: " + id));
    }

    public List<ModelStageHistory> findAll() {
        return modelStageHistoryRepository.findAll();
    }

    public List<ModelStageHistory> findByModelId(Long modelId) {
        return modelStageHistoryRepository.findByModel_IdOrderByCompletedAtDesc(modelId);
    }

    public List<ModelStageHistory> findByStage(Stage stage) {
        return modelStageHistoryRepository.findByStage(stage);
    }

    public List<ModelStageHistory> findByAssignedUser(User user) {
        return modelStageHistoryRepository.findByAssignedUserOrderByCompletedAtDesc(user);
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
}