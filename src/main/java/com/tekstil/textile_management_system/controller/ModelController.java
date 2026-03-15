package com.tekstil.textile_management_system.controller;
import com.tekstil.textile_management_system.dto.ModelRequestDTO;
import com.tekstil.textile_management_system.dto.ModelResponseDTO;
import com.tekstil.textile_management_system.entity.Model;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tekstil.textile_management_system.service.ModelService;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    //CREATE: POST /api/models
    @PostMapping
    public ResponseEntity<BaseResponse<ModelResponseDTO>> createModel(@RequestBody @Valid ModelRequestDTO dto) {
        ModelResponseDTO created = modelService.createModel(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(HttpStatus.CREATED.value(), "Model created", created));
    }

    //READ ALL: GET /api/models
    @GetMapping
    public ResponseEntity<BaseResponse<List<ModelResponseDTO>>> getAllModels() {
        List<ModelResponseDTO> models = modelService.findAll();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "All models retrieved", models));
    }

    // READ ONE: GET /api/models/5
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ModelResponseDTO>> getModelById(@PathVariable Long id) {


        ModelResponseDTO model = modelService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Models by Id",model));
    }

    // READ BY MODEL NAME: GET/api/models/by-model-name/M8306
    @GetMapping("/by-name/{modelName}")
    public ResponseEntity<BaseResponse<ModelResponseDTO>> getByModelName(@PathVariable String modelName) {
        ModelResponseDTO model1 = modelService.getModelByModelName(modelName);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Models by name",model1));


    }
    // READ BY STATUS: GET /api/models/status/
    @GetMapping("/status/{status}")
    public ResponseEntity<BaseResponse<List<ModelResponseDTO>>> getByModelStatus(@PathVariable ModelStatus status) {
        List <ModelResponseDTO> model1 = modelService.findByStatus(status);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Models by status", model1));

    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ModelResponseDTO>> updateModel(@PathVariable Long id, @RequestBody ModelRequestDTO dto) {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "model updated", modelService.updateModel(id, dto)));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteModel(@PathVariable Long id) {
            modelService.deleteModel(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Model deleted", null));

    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<BaseResponse<ModelResponseDTO>> changeStatus(@PathVariable Long id, @RequestParam ModelStatus status) {

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(BaseResponse.success(HttpStatus.OK.value(), "model status updated", modelService.changeStatus(id, status)));

    }
}
