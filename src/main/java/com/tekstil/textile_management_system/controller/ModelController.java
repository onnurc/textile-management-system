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

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    //CREATE: POST /api/models
    @PostMapping
    public ResponseEntity<Model> createModel(@RequestBody Model model){
        Model created = modelService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    //READ ALL: GET /api/models

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels(){
        List <Model> models = modelService.findAll();
        return ResponseEntity.ok(models);
    }

    // READ ONE: GET /api/models/5

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id){
        Model model = modelService.getModelById(id);
        return ResponseEntity.ok(model);
    }
   // READ BY MODEL NAME:  GET /api/models/by-model-name/M8306
   @GetMapping("/by-name/{modelName}")
    public ResponseEntity<Model> getByModelName(@PathVariable String modelName){
        Model model = modelService.getModelByModelName(modelName);
        return ResponseEntity.ok(model);
    }
    // READ BY STATUS: GET /api/models/status/

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Model>> getByStatus(@PathVariable ModelStatus status){
        List <Model> models = modelService.modelStatus(status);
        return ResponseEntity.ok(models);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Long id, @RequestBody Model model) {
        Model updated = modelService.updateModel(id, model);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteModel(@PathVariable Long id){
        boolean hasModel = true;
        try {

            modelService.getModelById(id);
        }catch (Exception e){
            hasModel = false;
        }
        if (hasModel){
            modelService.deleteModel(id);
            return ResponseEntity.ok().build();

        }else {
            BaseResponse baseResponse = new BaseResponse<>(true,HttpStatus.NO_CONTENT.value(),"bu id'ye ait model bulunamadı",null);
            return ResponseEntity.ok(baseResponse);
        }
//        modelService.deleteModel(id);
//        return  ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Model> changeStatus(@PathVariable Long id, @RequestParam ModelStatus status) {
        Model updated = modelService.changeStatus(id, status);
        return ResponseEntity.ok(updated);
    }


}
