package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.dto.StageHistoryRequestDTO;
import com.tekstil.textile_management_system.dto.StageHistoryResponseDTO;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.service.ModelStageHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/stage-histories")
@RequiredArgsConstructor
public class ModelStageHistoryController {

    private final ModelStageHistoryService modelStageHistoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<StageHistoryResponseDTO>> createStageHistory(@Valid @RequestBody StageHistoryRequestDTO dto) {

        boolean exists = modelStageHistoryService.existsByModelIdAndStageId(
                dto.getModelId(),
                dto.getStageId()
        );
        if(exists){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(BaseResponse.error(HttpStatus.CONFLICT.value(), "for this model stage is already exist"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(HttpStatus.CREATED.value(), "Stage History created", modelStageHistoryService.createStageHistory(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<StageHistoryResponseDTO>> getStageHistoryById(@PathVariable Long id) {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Stage history Fetched", modelStageHistoryService.getStageHistoryById(id)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<StageHistoryResponseDTO>>> getAllStageHistories() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(),"All stages fetched", modelStageHistoryService.findAll()));
    }

    @GetMapping("/by-model/{modelId}")
    public ResponseEntity<BaseResponse<List<StageHistoryResponseDTO>>> findByModelId(@PathVariable Long modelId) {
        List<StageHistoryResponseDTO> modelStageHistories = modelStageHistoryService.findByModelId(modelId);
            if (modelStageHistories.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(BaseResponse.success(HttpStatus.OK.value(), "there is no stage history with this id ",modelStageHistories));
            }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(),"All stages fetched",modelStageHistories));
    }

    @GetMapping("/by-stage/{stageId}")
    public ResponseEntity<BaseResponse<List<StageHistoryResponseDTO>>> findByStage(@PathVariable Long stageId) {

        List<StageHistoryResponseDTO> modelStageHistory = modelStageHistoryService.findByStage(stageId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "models by stages",modelStageHistory));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<BaseResponse<List<StageHistoryResponseDTO>>> findByAssignedUser(@PathVariable String userName) {

        List<StageHistoryResponseDTO> modelStageHistories = modelStageHistoryService.findByAssignedUser(userName);

        if (modelStageHistories.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "no user with this name ",modelStageHistories));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "models by assigned users",modelStageHistories));
    }
    @GetMapping("/by-status")
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByStatus( @RequestParam ModelStatus status) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "models by status",modelStageHistoryService.findByStatus(status)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<StageHistoryResponseDTO>> updateStageHistory(@PathVariable Long id,@Valid @RequestBody StageHistoryRequestDTO stageHistory) {

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