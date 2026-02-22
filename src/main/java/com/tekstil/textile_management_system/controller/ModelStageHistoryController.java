package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.service.ModelStageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stage-histories")
@RequiredArgsConstructor
public class ModelStageHistoryController {

    private final ModelStageHistoryService modelStageHistoryService;

    @PostMapping
    public ResponseEntity<ModelStageHistory> createStageHistory(@RequestBody ModelStageHistory stageHistory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelStageHistoryService.createStageHistory(stageHistory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelStageHistory> getStageHistoryById(@PathVariable Long id) {
        return ResponseEntity.ok(modelStageHistoryService.getStageHistoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<ModelStageHistory>> getAllStageHistories() {
        return ResponseEntity.ok(modelStageHistoryService.findAll());
    }

    @GetMapping("/by-model/{modelId}")
    public ResponseEntity<List<ModelStageHistory>> findByModelId(@PathVariable Long modelId) {
        return ResponseEntity.ok(modelStageHistoryService.findByModelId(modelId));
    }

    @PostMapping("/by-stage")
    public ResponseEntity<List<ModelStageHistory>> findByStage(@RequestBody Stage stage) {
        return ResponseEntity.ok(modelStageHistoryService.findByStage(stage));
    }

    @PostMapping("/by-user")
    public ResponseEntity<List<ModelStageHistory>> findByAssignedUser(@RequestBody User user) {
        return ResponseEntity.ok(modelStageHistoryService.findByAssignedUser(user));
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<ModelStageHistory>> findByStatus(@RequestParam String status) {
        return ResponseEntity.ok(modelStageHistoryService.findByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelStageHistory> updateStageHistory(@PathVariable Long id, @RequestBody ModelStageHistory stageHistory) {
        return ResponseEntity.ok(modelStageHistoryService.updateStageHistory(id, stageHistory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStageHistory(@PathVariable Long id) {
        modelStageHistoryService.deleteStageHistory(id);
        return ResponseEntity.noContent().build();
    }
}