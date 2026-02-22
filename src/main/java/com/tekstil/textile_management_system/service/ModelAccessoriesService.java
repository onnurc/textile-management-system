package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.ModelAccessories;
import com.tekstil.textile_management_system.repository.ModelAccessoriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelAccessoriesService {
    private final ModelAccessoriesRepository modelAccessoriesRepository;


    public List<ModelAccessories> findByAccessoryType(String accessoryType){
        return modelAccessoriesRepository.findByAccessoryType(accessoryType);
    }
    public List<ModelAccessories> findByColor(String color){
        return modelAccessoriesRepository.findByColor(color);
    }
    public List<ModelAccessories> findAllByOrderByQuantityPerUnitAsc(Integer quantityPerUnit){
        return modelAccessoriesRepository.findAllByOrderByQuantityPerUnitAsc(quantityPerUnit);
    }
    public List<ModelAccessories> findByTotalRequired(Integer totalRequired){
        return modelAccessoriesRepository.findByTotalRequired(totalRequired);
    }
    public List<ModelAccessories> findBySupplier(String supplier){
        return modelAccessoriesRepository.findBySupplier(supplier);
    }

    public List<ModelAccessories> findAllAccessories() {
        return modelAccessoriesRepository.findAll();
    }
    public ModelAccessories updateAccessory(Long id, ModelAccessories updateAccessory ){
        ModelAccessories updated = modelAccessoriesRepository.findById(id).orElseThrow(()->new RuntimeException("Accessory not found : " + id));

        updated.setAccessoryType(updateAccessory.getAccessoryType());
        updated.setColor(updateAccessory.getColor());
        updated.setDescription(updateAccessory.getDescription());
        updated.setModel(updateAccessory.getModel());
        updated.setQuantityPerUnit(updateAccessory.getQuantityPerUnit());
        updated.setSupplier(updateAccessory.getSupplier());
        updated.setTotalRequired(updateAccessory.getTotalRequired());

        return modelAccessoriesRepository.save(updated);
    }

    public void deleteById(Long id) {
        modelAccessoriesRepository.deleteById(id);
    }

    public ModelAccessories createAccessory(ModelAccessories modelAccessories) {
        return modelAccessoriesRepository.save(modelAccessories);
    }
}
