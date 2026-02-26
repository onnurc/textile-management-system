package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BaseResponse<Model>> createModel(@RequestBody Model model) {
        Optional<Model> model1 = modelService.findByModelName(model.getModelName());
        if (model1.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(BaseResponse.error(HttpStatus.CONFLICT.value(), "This name is already taken"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(HttpStatus.CREATED.value(), "model created", modelService.createModel(model)));

    }

    //READ ALL: GET /api/models

    @GetMapping
    public ResponseEntity<BaseResponse<List<Model>>> getAllModels() {
        List<Model> models = modelService.findAll();

        String message = models.isEmpty() ? "No models found in database" : "All models retrieved successfully";

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), message, models));

    }

    // READ ONE: GET /api/models/5

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Model>> getModelById(@PathVariable Long id) {

        Optional<Model> model1  = modelService.findById(id);

        if (model1.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No content in database"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Models by Id",model1.get()));
    }

    // READ BY MODEL NAME: GET/api/models/by-model-name/M8306
    @GetMapping("/by-name/{modelName}")
    public ResponseEntity<BaseResponse<Model>> getByModelName(@PathVariable String modelName) {
        Model model1 = modelService.getModelByModelName(modelName);
        if (model1 == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No models exists with this name"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Models by name",model1));

        }
    }

    // READ BY STATUS: GET /api/models/status/
    @GetMapping("/status/{status}")
    public ResponseEntity<BaseResponse<List<Model>>> getByModelStatus(@PathVariable ModelStatus status) {
        List <Model> model1 = modelService.findByStatus(status);
        if (model1.isEmpty()) {
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No models with tihs status "));
        }else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Models by status", model1));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Model>> updateModel(@PathVariable Long id, @RequestBody Model model) {
        Optional<Model> model2 = modelService.findById(id);
        if (model2.isEmpty()){
           return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No mode with this id"));
        }else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "model updated", modelService.updateModel(id, model)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteModel(@PathVariable Long id) {
        Model model = modelService.getModelById(id);
        if (model == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus
                            .NOT_FOUND.value(), "no model exists with this id"));
        } else {
            modelService.deleteModel(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.success(HttpStatus.OK.value(), "Model deleted", null));
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<BaseResponse<Model>> changeStatus(@PathVariable Long id, @RequestParam ModelStatus status) {
        Optional<Model> model = modelService.findById(id);
        if (model.isPresent()) {
            if (model.get().getStatus() == status) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(BaseResponse.error(HttpStatus.BAD_REQUEST.value(), "status already : " + status));
            } else {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(BaseResponse.success(HttpStatus.OK.value(), "model status updated", modelService.changeStatus(id, status)));
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "model not found"));
    }
}
