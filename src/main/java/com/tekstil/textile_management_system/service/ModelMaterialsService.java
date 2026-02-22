package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelMaterials;
import com.tekstil.textile_management_system.repository.ModelMaterialsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ModelMaterialsService {

    private final ModelMaterialsRepository modelMaterialsRepository;

    public ModelMaterials createMaterial(ModelMaterials modelMaterial) {
        return modelMaterialsRepository.save(modelMaterial);
    }

    public List<ModelMaterials> findAll() {
        return modelMaterialsRepository.findAll();
    }

    public ModelMaterials findById(Long id) {
        return modelMaterialsRepository.findById(id).orElseThrow(() -> new RuntimeException("Material not found: " + id));
    }

    public List<ModelMaterials> findByModel(Long modelId) {
        Model model = new Model();
        model.setId(modelId);
        return modelMaterialsRepository.findByModel(model);
    }

    public List<ModelMaterials> findByMaterialType(String materialType) {
        return modelMaterialsRepository.findByMaterialType(materialType);
    }

    public List<ModelMaterials> findBySupplier(String supplier) {
        return modelMaterialsRepository.findBySupplier(supplier);
    }

    public List<ModelMaterials> findByStatus(String status) {
        return modelMaterialsRepository.findByStatus(status);
    }

    public List<ModelMaterials> findByColor(String color) {
        return modelMaterialsRepository.findByColorIgnoreCase(color);
    }

    public List<ModelMaterials> findByNotes(String keyword) {
        return modelMaterialsRepository.findByNotesContainingIgnoreCase(keyword);
    }

    public ModelMaterials updateMaterial(Long id, ModelMaterials updateMaterials) {
        ModelMaterials existing = modelMaterialsRepository.findById(id).orElseThrow(() -> new RuntimeException("Material not found: " + id));

        existing.setColor(updateMaterials.getColor());
        existing.setDescription(updateMaterials.getDescription());
        existing.setMaterialType(updateMaterials.getMaterialType());
        existing.setSupplier(updateMaterials.getSupplier());
        existing.setMetrajPerUnit(updateMaterials.getMetrajPerUnit());
        existing.setTotalRequired(updateMaterials.getTotalRequired());
        existing.setStatus(updateMaterials.getStatus());
        existing.setNotes(updateMaterials.getNotes());

        return modelMaterialsRepository.save(existing);
    }

    public void deleteMaterial(Long id) {
        modelMaterialsRepository.deleteById(id);
    }
}