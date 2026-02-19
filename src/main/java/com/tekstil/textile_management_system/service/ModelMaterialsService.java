package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.ModelMaterials;
import com.tekstil.textile_management_system.repository.ModelMaterialsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class ModelMaterialsService {

    private final ModelMaterialsRepository modelMaterialsRepository;

    public ModelMaterials createModel(ModelMaterials modelMaterial){
        return modelMaterialsRepository.save(modelMaterial);
    }

    public ModelMaterials updateMaterials(Long id , ModelMaterials updateMaterials){

        ModelMaterials existing = modelMaterialsRepository.findById(id).orElseThrow(() -> new RuntimeException("Material not found: " + id ));

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


}
