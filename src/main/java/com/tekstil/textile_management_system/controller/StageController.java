package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;

    @PostMapping
    public ResponseEntity<?> createStage(@RequestBody Stage stage) {
         Optional <Stage> stage1 = stageService.findStageByName(stage.getName());
         if (stage1.isPresent()) {

                 return ResponseEntity
                         .status(HttpStatus.CONFLICT)
                         .body(BaseResponse.error(HttpStatus.CONFLICT.value(), "stage already taken"));
             }
             return ResponseEntity.status(HttpStatus.CREATED).body(stageService.createStage(stage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.getStageById(id));
    }

    @GetMapping("/by-name")
    public ResponseEntity<Stage> getStageByName(@RequestParam ModelStatus name) {
        return ResponseEntity.ok(stageService.getStageByName(name));
    }

    @GetMapping
    public ResponseEntity<List<Stage>> getAllStages() {
        return ResponseEntity.ok(stageService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Stage>> getActiveStages() {
        return ResponseEntity.ok(stageService.findActiveStages());
    }

    @GetMapping("/active/ordered")
    public ResponseEntity<List<Stage>> getActiveStagesOrdered() {
        return ResponseEntity.ok(stageService.findActiveStagesOrdered());
    }

    @GetMapping("/search/checklist")
    public ResponseEntity<List<Stage>> findByChecklist(@RequestParam String keyword) {
        return ResponseEntity.ok(stageService.findByChecklist(keyword));
    }

    @GetMapping("/search/duration")
    public ResponseEntity<List<Stage>> findByDurationLessThan(@RequestParam Integer hours) {
        return ResponseEntity.ok(stageService.findByEstimatedDurationLessThan(hours));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody Stage stage) {
        return ResponseEntity.ok(stageService.updateStage(id, stage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }
}