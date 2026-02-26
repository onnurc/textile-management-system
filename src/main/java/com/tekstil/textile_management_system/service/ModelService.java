package com.tekstil.textile_management_system.service;


import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.repository.ModelRepository;
import com.tekstil.textile_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelService {



    private final UserRepository userRepository;
    private final ModelRepository modelRepository;


    public Model createModel(Model model){

        if (model.getAssignedTo() != null) {
            User user = userRepository.findById(model.getAssignedTo().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            model.setAssignedTo(user);
        }

        if (modelRepository.existsByModelName(model.getModelName())){
            throw new ResourceNotFoundException("Model name already exist: " + model.getModelName());
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




    public Model changeStatus(Long id, ModelStatus status){
        Model existing = modelRepository.findById(id).orElseThrow(()-> new RuntimeException("Model not found: " + id));
        existing.setStatus(status);
        return modelRepository.save(existing);
    }

    public Model updateModel(Long id, Model updatedModel){
        Model existing = modelRepository.findById(id).orElseThrow(()-> new RuntimeException("Model not found" + id));

        if (updatedModel.getAssignedTo() != null) {
            User user = userRepository.findById(updatedModel.getAssignedTo().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setAssignedTo(user);
        }

        existing.setModelName(updatedModel.getModelName());
        existing.setBrand(updatedModel.getBrand());
        existing.setCategory(updatedModel.getCategory());
        existing.setDescription(updatedModel.getDescription());
        existing.setPriority(updatedModel.getPriority());
        existing.setSeason(updatedModel.getSeason());
        existing.setSizeRange(updatedModel.getSizeRange());
        existing.setNotes(updatedModel.getNotes());
        existing.setDeadline(updatedModel.getDeadline());
        existing.setStatus(updatedModel.getStatus());

        return modelRepository.save(existing);

    }


    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }


    public Optional<Model> findByModelName(String modelName) {
        return modelRepository.findByModelName(modelName);
    }


    public List<Model> findByStatus(ModelStatus status) {
        return modelRepository.findByStatus(status);
    }
}
