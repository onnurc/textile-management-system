package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.dto.MeasurementDTO;
import com.tekstil.textile_management_system.service.ModelMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models/{modelId}/measurements")
@RequiredArgsConstructor
public class ModelMeasurementController {

    private final ModelMeasurementService measurementService;


    @GetMapping
    public ResponseEntity<BaseResponse<List<MeasurementDTO>>> getMeasurements(@PathVariable Long modelId){

        List<MeasurementDTO> measurements = measurementService.getMeasurements(modelId);
        return ResponseEntity.ok(BaseResponse.success(200,"Measurements listed",measurements));

    }
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> saveMeasurements(
            @PathVariable Long modelId,
            @RequestBody List<MeasurementDTO> dtos){

        measurementService.saveMeasurements(modelId,dtos);
        return ResponseEntity.ok(BaseResponse.success(200,"Measurements Saved",null));
    }
}
