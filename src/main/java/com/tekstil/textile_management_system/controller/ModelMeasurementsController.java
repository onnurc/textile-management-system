package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelMeasurements;
import com.tekstil.textile_management_system.service.ModelMeasurementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/model-measurements")
@RequiredArgsConstructor
public class ModelMeasurementsController {

    private final ModelMeasurementsService modelMeasurementsService;

    @PostMapping
    public ResponseEntity<ModelMeasurements> createMeasurement(@RequestBody ModelMeasurements measurement) {
        ModelMeasurements created = modelMeasurementsService.createMeasurement(measurement);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ModelMeasurements>> findAll() {
        List<ModelMeasurements> measurements = modelMeasurementsService.findAll();
        return ResponseEntity.ok(measurements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelMeasurements> getMeasurementById(@PathVariable Long id) {
        ModelMeasurements measurement = modelMeasurementsService.getMeasurementById(id);
        return ResponseEntity.ok(measurement);
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<ModelMeasurements>> findByModel(@PathVariable Long modelId) {
        Model model = new Model();
        model.setId(modelId);
        List<ModelMeasurements> measurements = modelMeasurementsService.findByModel(model);
        return ResponseEntity.ok(measurements);
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<ModelMeasurements>> findBySize(@PathVariable String size) {
        List<ModelMeasurements> measurements = modelMeasurementsService.findBySize(size);
        return ResponseEntity.ok(measurements);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ModelMeasurements>> findByNotes(@PathVariable String keyword) {
        List<ModelMeasurements> measurements = modelMeasurementsService.findByNotesContainingIgnoreCase(keyword);
        return ResponseEntity.ok(measurements);
    }

    @GetMapping("/model/{modelId}/size/{size}")
    public ResponseEntity<ModelMeasurements> findByModelAndSize(@PathVariable Long modelId, @PathVariable String size) {
        Model model = new Model();
        model.setId(modelId);
        Optional<ModelMeasurements> measurement = modelMeasurementsService.findByModelAndSize(model, size);
        return measurement.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelMeasurements> updateMeasurement(@PathVariable Long id, @RequestBody ModelMeasurements measurement) {
        ModelMeasurements updated = modelMeasurementsService.updateMeasurement(id, measurement);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeasurement(@PathVariable Long id) {
        modelMeasurementsService.deleteMeasurement(id);
        return ResponseEntity.noContent().build();
    }
}