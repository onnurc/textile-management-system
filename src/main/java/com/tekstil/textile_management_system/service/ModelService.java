package com.tekstil.textile_management_system.service;


import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.repository.ModelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelService {
    private final ModelRepository modelRepository;


    public Model createModel(Model model){
        if (modelRepository.existsByModelName(model.getModelName())){
            throw new RuntimeException("Model name already exist: " + model.getModelName());
        }
        if(model.getStatus() == null){
            model.setStatus(ModelStatus.IN_DESIGN);
        }
        return modelRepository.save(model);
    }

    public Model getModelById(Long id){
        return modelRepository.findById(id).orElseThrow(() -> new RuntimeException("Model can't find" + id));
    }
    public Model getModelByModelName(String name){
        return modelRepository.findByModelName(name).orElseThrow(() -> new RuntimeException("No such model with : " + name));
    }
    public List<Model> getModelByBrand(String brand){
        return modelRepository.findByBrand(brand);
    }
    public List <Model> findAll(){
        return modelRepository.findAll();
    }

    public void deleteModel(Long id){
        modelRepository.deleteById(id);
    }

    public Model updateModel(Long id, Model updatedModel){
        Model existing = modelRepository.findById(id).orElseThrow(()-> new RuntimeException("Model not found" + id));

        existing.setModelName(updatedModel.getModelName());
        existing.setBrand(updatedModel.getBrand());
        existing.setCategory(updatedModel.getCategory());
        existing.setDescription(updatedModel.getDescription());
        existing.setPriority(updatedModel.getPriority());
        existing.setSeason(updatedModel.getSeason());
        existing.setSizeRange(updatedModel.getSizeRange());
        existing.setNotes(updatedModel.getNotes());
        existing.setAssignedTo(updatedModel.getAssignedTo());
        existing.setDeadline(updatedModel.getDeadline());
        existing.setStatus(updatedModel.getStatus());

        return modelRepository.save(existing);
    }






}
