package com.tekstil.textile_management_system.service;


import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.exception.AlreadyExistsException;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.repository.ModelRepository;
import com.tekstil.textile_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            model.setAssignedTo(user);
        }
        if (modelRepository.existsByModelName(model.getModelName())){
            throw new AlreadyExistsException("Model name already exist: " + model.getModelName());
        }
        if(model.getStatus() == null){
            model.setStatus(ModelStatus.IN_DESIGN);
        }
        return modelRepository.save(model);
    }
    public List <Model> findAll(){
        List<Model> models = modelRepository.findAll();

        if (models.isEmpty()){
            throw new ResourceNotFoundException("No model found ");
        }

        return models;

    }


    public Model getModelByModelName(String name) {
        return modelRepository.findByModelName(name)
                .orElseThrow(() -> new ResourceNotFoundException("No model found with this name: " + name));
    }

    public List<Model> getModelByBrand(String brand){
        return modelRepository.findByBrand(brand);
    }


    public void deleteModel(Long id) {
        if (!   modelRepository.existsById(id)){
            throw new ResourceNotFoundException("Model not found: " + id);
    }
        modelRepository.deleteById(id);
    }

    public Model changeStatus(Long id, ModelStatus status){
        Model existing = modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Model not found: " + id));
        if(existing.getStatus() == status){
            throw new AlreadyExistsException("Status already: " + status);
        }
        existing.setStatus(status);
        return modelRepository.save(existing);
    }

    public Model updateModel(Long id, Model updatedModel){
        Model existing = modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Model not found" + id));

        if (updatedModel.getAssignedTo() != null) {
            User user = userRepository.findById(updatedModel.getAssignedTo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
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

    public Model findById(Long id) {

        return modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No model with this id"));
    }

    public Optional<Model> findByModelName(String modelName) {
        return modelRepository.findByModelName(modelName);
    }

    public List<Model> findByStatus(ModelStatus status) {
        List<Model> models = modelRepository.findByStatus(status);
        if (models.isEmpty()){
            throw new ResourceNotFoundException("No model found for status: " + status);
        }
        return models;

    }
}
