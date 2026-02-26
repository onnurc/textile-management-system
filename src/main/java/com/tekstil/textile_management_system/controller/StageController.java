package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.service.ModelStageHistoryService;
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
    private final ModelStageHistoryService modelStageHistoryService;

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
    public ResponseEntity<BaseResponse<Stage>> getStageById(@PathVariable Long id) {
        Optional<Stage> stage = stageService.findById(id);

        if (stage.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No stage found with id: " + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage found", stage.get()));
    }

    @GetMapping("/by-name")
    public ResponseEntity<BaseResponse<Stage>> getStageByName(@RequestParam ModelStatus name) {
        Stage stage = stageService.getStageByName(name);

        if (stage == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No stage found with name: " + name));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage found", stage));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<Stage>>> getAllStages() {
        List<Stage> stages = stageService.findAll();

        if (stages.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No stages found", stages));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stages listed", stages));
    }

    @GetMapping("/active")
    public ResponseEntity<BaseResponse<List<Stage>>> getActiveStages() {
        List<Stage> stages = stageService.findActiveStages();

        if (stages.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No active stages found", stages));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Active stages listed", stages));
    }

    @GetMapping("/active/ordered")
    public ResponseEntity<BaseResponse<List<Stage>>> getActiveStagesOrdered() {
        List<Stage> stages = stageService.findActiveStagesOrdered();

        if (stages.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No active stages found", stages));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Active stages listed (ordered)", stages));
    }


    @GetMapping("/search/checklist")
    public ResponseEntity<BaseResponse<List<Stage>>> findByChecklist(@RequestParam String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST.value(), "Keyword cannot be empty"));
        }

        List<Stage> stages = stageService.findByChecklist(keyword);

        if (stages.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No stages found for keyword: " + keyword, stages));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stages found for keyword: " + keyword, stages));
    }

    @GetMapping("/search/duration")
    public ResponseEntity<BaseResponse<List<Stage>>> findByDurationLessThan(@RequestParam Integer hours) {
        if (hours == null || hours <= 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST.value(), "Hours must be greater than 0"));
        }
        List<Stage> stages = stageService.findByEstimatedDurationLessThan(hours);

        if (stages.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No stages found under " + hours + " hours", stages));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stages with duration less than " + hours + " hours", stages));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Stage>> updateStage(@PathVariable Long id, @RequestBody Stage stage) {
        Optional<Stage> existing = stageService.findById(id);

        if (existing.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No stage found with id: " + id));
        }
        Stage updated = stageService.updateStage(id, stage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteStage(@PathVariable Long id) {
        Optional<Stage> existing = stageService.findById(id);

        if (existing.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No stage found with id: " + id));
        }

        stageService.deleteStage(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage deleted successfully", null));
    }
}