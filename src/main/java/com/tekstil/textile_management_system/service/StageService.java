package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.repository.StageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StageService {



    private final StageRepository stageRepository;

    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Stage getStageById(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found: " + id));
    }

    public Stage getStageByName(ModelStatus name) {
        return stageRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Stage not found: " + name));
    }

    public List<Stage> findAll() {
        return stageRepository.findAll();
    }

    public List<Stage> findActiveStages() {
        return stageRepository.findByActiveTrue();
    }

    public List<Stage> findActiveStagesOrdered() {
        return stageRepository.findByActiveTrueOrderByOrderIndexAsc();
    }

    public List<Stage> findByChecklist(String keyword) {
        return stageRepository.findByRequiredChecklistContainingIgnoreCase(keyword);
    }

    public List<Stage> findByEstimatedDurationLessThan(Integer hours) {
        return stageRepository.findByEstimatedDurationHoursLessThan(hours);
    }

    public Stage updateStage(Long id, Stage updated) {
        Stage existing = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found: " + id));

        existing.setName(updated.getName());
        existing.setDisplayName(updated.getDisplayName());
        existing.setOrderIndex(updated.getOrderIndex());
        existing.setDescription(updated.getDescription());
        existing.setRequiredChecklist(updated.getRequiredChecklist());
        existing.setEstimatedDurationHours(updated.getEstimatedDurationHours());
        existing.setActive(updated.getActive());

        return stageRepository.save(existing);
    }

    public void deleteStage(Long id) {
        stageRepository.deleteById(id);
    }

    public Stage findStageById(Long id) {
        return stageRepository.findStageById(id);
    }

    public Optional<Stage> findStageByName(ModelStatus name) {
        return stageRepository.findByName(name);
    }
}