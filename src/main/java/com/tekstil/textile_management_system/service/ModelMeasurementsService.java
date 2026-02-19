package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelMeasurements;
import com.tekstil.textile_management_system.repository.ModelMeasurementsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelMeasurementsService {

    private final ModelMeasurementsRepository modelMeasurementsRepository;

    public ModelMeasurements createMeasurement(ModelMeasurements measurement) {
        return modelMeasurementsRepository.save(measurement);
    }

    public ModelMeasurements getMeasurementById(Long id) {
        return modelMeasurementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Measurement not found: " + id));
    }

    public List<ModelMeasurements> findAll() {
        return modelMeasurementsRepository.findAll();
    }

    public List<ModelMeasurements> findByModel(Model model) {
        return modelMeasurementsRepository.findByModel(model);
    }

    public List<ModelMeasurements> findBySize(String size) {
        return modelMeasurementsRepository.findBySize(size);
    }
    public List<ModelMeasurements> findByNotesContainingIgnoreCase(String keyword){
        return modelMeasurementsRepository.findByNotesContainingIgnoreCase(keyword);
    }
    public Optional<ModelMeasurements> findByModelAndSize(Model model, String size){
        return modelMeasurementsRepository.findByModelAndSize(model,size);
    }

    public ModelMeasurements updateMeasurement(Long id, ModelMeasurements updated) {
        ModelMeasurements existing = modelMeasurementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Measurement not found: " + id));

        existing.setSize(updated.getSize());
        existing.setFrontArmHole(updated.getFrontArmHole());
        existing.setBlackArmhole(updated.getBlackArmhole());
        existing.setNeckline(updated.getNeckline());
        existing.setShoulder(updated.getShoulder());
        existing.setChest(updated.getChest());
        existing.setWaist(updated.getWaist());
        existing.setHip(updated.getHip());
        existing.setTotalLength(updated.getTotalLength());
        existing.setSleeveLength(updated.getSleeveLength());
        existing.setSkirtLength(updated.getSkirtLength());
        existing.setTrouserLength(updated.getTrouserLength());
        existing.setNotes(updated.getNotes());

        return modelMeasurementsRepository.save(existing);
    }

    public void deleteMeasurement(Long id) {
        modelMeasurementsRepository.deleteById(id);
    }
}