package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.dto.StageRequestDTO;
import com.tekstil.textile_management_system.dto.StageResponseDTO;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.mapper.StageMapper;
import com.tekstil.textile_management_system.service.ModelStageHistoryService;
import com.tekstil.textile_management_system.service.StageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('COMPANY_MANAGER','STYLIST','MODELIST','OPERATOR','CUTTER','TRIM_SPECIALIST','FASON','PACKAGING_SPECIALIST')")
public class StageController {

    private final StageService stageService;
    private final ModelStageHistoryService modelStageHistoryService;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY_MANAGER')")
    public ResponseEntity<BaseResponse<StageResponseDTO>> createStage(@Valid @RequestBody StageRequestDTO stage) {
        StageResponseDTO created = stageService.createStage(stage);
             return ResponseEntity
                     .status(HttpStatus.CREATED)
                     .body(BaseResponse.success(HttpStatus.CREATED.value(), "Stage created",created));
    }


    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<StageResponseDTO>> getStageById(@PathVariable Long id) {
     StageResponseDTO stage = stageService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage found", stage));
    }

    @GetMapping("/by-name")
    public ResponseEntity<BaseResponse<StageResponseDTO>> getStageByName(@RequestParam ModelStatus name) {
        StageResponseDTO stage = stageService.getStageByName(name);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage found", stage));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<StageResponseDTO>>> getAllStages() {

        List<StageResponseDTO> stages = stageService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stages listed", stages));
    }

    @GetMapping("/active")
    public ResponseEntity<BaseResponse<List<StageResponseDTO>>> getActiveStages() {
        List<StageResponseDTO> stages = stageService.findActiveStages();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Active stages listed", stages));
    }

    @GetMapping("/active/ordered")
    public ResponseEntity<BaseResponse<List<StageResponseDTO>>> getActiveStagesOrdered() {

        List<StageResponseDTO> stages = stageService.findActiveStagesOrdered();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Active stages listed (ordered)", stages));
    }


    @GetMapping("/search/checklist")
    public ResponseEntity<BaseResponse<List<StageResponseDTO>>> findByChecklist(
            @RequestParam String keyword) {
        List<StageResponseDTO> stages = stageService.findByChecklist(keyword);
        return ResponseEntity.ok(BaseResponse.success(200, "Stages found for keyword: " + keyword, stages));
    }

    @GetMapping("/search/duration")
    public ResponseEntity<BaseResponse<List<StageResponseDTO>>> findByDurationLessThan(@RequestParam @Positive Integer hours) {

        List<StageResponseDTO> stages = stageService.findByEstimatedDurationLessThan(hours);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stages with duration less than " + hours + " hours", stages));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY_MANAGER')")
    public ResponseEntity<BaseResponse<StageResponseDTO>> updateStage( @PathVariable Long id,@Valid @RequestBody StageRequestDTO dto) {

        StageResponseDTO updated = stageService.updateStage(id, dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage updated", updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY_MANAGER')")
    public ResponseEntity<BaseResponse<Void>> deleteStage(@PathVariable Long id) {

        stageService.deleteStage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Stage deleted successfully", null));
    }
}