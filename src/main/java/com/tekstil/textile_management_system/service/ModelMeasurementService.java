package com.tekstil.textile_management_system.service;
import com.tekstil.textile_management_system.dto.MeasurementDTO;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelMeasurement;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.repository.ModelMeasurementRepository;
import com.tekstil.textile_management_system.repository.ModelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelMeasurementService {

    private final ModelMeasurementRepository measurementRepository;
    private final ModelRepository modelRepository;

    public List<MeasurementDTO> getMeasurements(Long modelId) {
        return measurementRepository.findByModelId(modelId)
                .stream()
                .map(m -> {
                    MeasurementDTO dto = new MeasurementDTO();
                    dto.setTableLabel(m.getTableLabel());
                    dto.setRowLabel(m.getRowLabel());
                    dto.setColLabel(m.getColLabel());
                    dto.setValue(m.getValue());
                    return dto;
                })
                .toList();
    }

    public void saveMeasurements(Long modelId, List<MeasurementDTO> dtos) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found: " + modelId));

        measurementRepository.deleteByModelId(modelId);

        List<ModelMeasurement> entities = dtos.stream()
                .filter(dto -> dto.getValue() != null && !dto.getValue().isBlank())
                .map(dto -> {
                    ModelMeasurement m = new ModelMeasurement();
                    m.setModel(model);
                    m.setRowLabel(dto.getRowLabel());
                    m.setColLabel(dto.getColLabel());
                    m.setValue(dto.getValue());
                    m.setTableLabel(dto.getTableLabel());
                    return m;
                })
                .toList();

        measurementRepository.saveAll(entities);
    }
}