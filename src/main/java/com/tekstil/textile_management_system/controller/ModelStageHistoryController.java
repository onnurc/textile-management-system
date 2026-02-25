package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.service.ModelStageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stage-histories")
@RequiredArgsConstructor
public class ModelStageHistoryController {

    private final ModelStageHistoryService modelStageHistoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<ModelStageHistory>> createStageHistory(@RequestBody ModelStageHistory stageHistory) {

        boolean exists = modelStageHistoryService.existsByModelIdAndStageId(
                stageHistory.getModel().getId(),
                stageHistory.getStage().getId()
        );
        if(exists){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(BaseResponse.error(HttpStatus.CONFLICT.value(), "for this model stage is already exist"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(HttpStatus.CREATED.value(), "Stage History created", modelStageHistoryService.createStageHistory(stageHistory)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ModelStageHistory>> getStageHistoryById(@PathVariable Long id) {
        Optional <ModelStageHistory> modelStageHistory = modelStageHistoryService.getStageHistoryById(id);

        if(modelStageHistory.isPresent()){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Stage history Fetched", modelStageHistory.get()));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "no stage history with this id: " +id));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> getAllStageHistories() {
       List <ModelStageHistory> modelStageHistory = modelStageHistoryService.findAll();

       if (modelStageHistory.isEmpty()){
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(BaseResponse.success(HttpStatus.OK.value(), "there is no stage history right now ",modelStageHistory));
       }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(),"All stages fetched", modelStageHistory));
    }

    @GetMapping("/by-model/{modelId}")
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByModelId(@PathVariable Long modelId) {
        List<ModelStageHistory> modelStageHistories = modelStageHistoryService.findByModelId(modelId);
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
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByStage(@PathVariable Long stageId) {

        List<ModelStageHistory> modelStageHistory = modelStageHistoryService.findByStage(stageId);

        if (modelStageHistory.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "there is no stage with this id ",modelStageHistory));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "models by stages",modelStageHistory));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<BaseResponse<List<ModelStageHistory>>> findByAssignedUser(@PathVariable String userName) {

        List<ModelStageHistory> modelStageHistories = modelStageHistoryService.findByAssignedUser(userName);

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