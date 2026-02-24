package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.service.ModelStageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tekstil.textile_management_system.dto.BaseResponse.success;

@RestController
@RequestMapping("/api/stage-histories")
@RequiredArgsConstructor
public class ModelStageHistoryController {

    private final ModelStageHistoryService modelStageHistoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<ModelStageHistory>> createStageHistory(@RequestBody ModelStageHistory stageHistory) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(HttpStatus.CREATED.value(), "Stage History created", modelStageHistoryService.createStageHistory(stageHistory)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ModelStageHistory>> getStageHistoryById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(),"Stage history Fetched",modelStageHistoryService.getStageHistoryById(id)));
                 }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> getAllStageHistories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(),"All stages fetched", modelStageHistoryService.findAll()));
    }

    @GetMapping("/by-model/{modelId}")
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByModelId(@PathVariable Long modelId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(),"All stages fetched",modelStageHistoryService.findByModelId(modelId)));
    }

    @GetMapping("/by-stage/{stage}")
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByStage(@PathVariable Long stageid) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "models by stages",modelStageHistoryService.findByStage(stageid)));
    }

    @GetMapping("/by-user/{user}")
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByAssignedUser(@RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "models by assigned users",modelStageHistoryService.findByAssignedUser(user)));
    }

    @GetMapping("/by-status")
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByStatus(@RequestParam String status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "models by status",modelStageHistoryService.findByStatus(status)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ModelStageHistory>> updateStageHistory(@PathVariable Long id, @RequestBody ModelStageHistory stageHistory) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage history updated",modelStageHistoryService.updateStageHistory(id,stageHistory)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteStageHistory(@PathVariable Long id) {
        modelStageHistoryService.deleteStageHistory(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(),
                        "Stage deleted",
                        null));
    }
}