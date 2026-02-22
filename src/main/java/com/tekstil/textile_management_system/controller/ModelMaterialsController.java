package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.entity.ModelMaterials;
import com.tekstil.textile_management_system.service.ModelMaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model-materials")
@RequiredArgsConstructor
public class ModelMaterialsController {

    private final ModelMaterialsService modelMaterialsService;

    @PostMapping
    public ResponseEntity<ModelMaterials> createMaterial(@RequestBody ModelMaterials modelMaterials) {
        ModelMaterials created = modelMaterialsService.createMaterial(modelMaterials);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ModelMaterials>> findAll() {
        List<ModelMaterials> materials = modelMaterialsService.findAll();
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelMaterials> findById(@PathVariable Long id) {
        ModelMaterials material = modelMaterialsService.findById(id);
        return ResponseEntity.ok(material);
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<ModelMaterials>> findByModel(@PathVariable Long modelId) {
        List<ModelMaterials> materials = modelMaterialsService.findByModel(modelId);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/material-type/{materialType}")
    public ResponseEntity<List<ModelMaterials>> findByMaterialType(@PathVariable String materialType) {
        List<ModelMaterials> materials = modelMaterialsService.findByMaterialType(materialType);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/supplier/{supplier}")
    public ResponseEntity<List<ModelMaterials>> findBySupplier(@PathVariable String supplier) {
        List<ModelMaterials> materials = modelMaterialsService.findBySupplier(supplier);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ModelMaterials>> findByStatus(@PathVariable String status) {
        List<ModelMaterials> materials = modelMaterialsService.findByStatus(status);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<ModelMaterials>> findByColor(@PathVariable String color) {
        List<ModelMaterials> materials = modelMaterialsService.findByColor(color);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ModelMaterials>> findByNotes(@PathVariable String keyword) {
        List<ModelMaterials> materials = modelMaterialsService.findByNotes(keyword);
        return ResponseEntity.ok(materials);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelMaterials> updateMaterial(@PathVariable Long id, @RequestBody ModelMaterials modelMaterials) {
        ModelMaterials updated = modelMaterialsService.updateMaterial(id, modelMaterials);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        modelMaterialsService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}