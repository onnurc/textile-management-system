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
}
