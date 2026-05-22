package com.tekstil.textile_management_system.service;


import com.tekstil.textile_management_system.dto.ModelRequestDTO;
import com.tekstil.textile_management_system.dto.ModelResponseDTO;
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
import com.tekstil.textile_management_system.mapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelService {

    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    public ModelResponseDTO createModel(ModelRequestDTO dto) {
        if (modelRepository.existsByModelName(dto.getModelName())) {
            throw new AlreadyExistsException("Model already exists: " + dto.getModelName());
        }

        Model model = ModelMapper.toEntity(dto);

        if (dto.getAssignedToUserId() != null) {
            User user = userRepository.findById(dto.getAssignedToUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found: " + dto.getAssignedToUserId()));
            model.setAssignedTo(user);
        }

        return ModelMapper.toResponseDTO(modelRepository.save(model));
    }

    public List<ModelResponseDTO> findAll(){
        return modelRepository.findAll()
                .stream()
                .map(ModelMapper::toResponseDTO)
                .toList();
    }


    public ModelResponseDTO getModelByModelName(String name) {
        return modelRepository.findByModelName(name)
                .map(ModelMapper::toResponseDTO)
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

    public ModelResponseDTO changeStatus(Long id, ModelStatus status){
        Model existing = modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Model not found: " + id));
        if(existing.getStatus() == status){
            throw new AlreadyExistsException("Status already: " + status);
        }
        existing.setStatus(status);
        return ModelMapper.toResponseDTO(modelRepository.save(existing));
    }

    public ModelResponseDTO updateModel(Long id, ModelRequestDTO updatedModel){
        Model existing = modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Model not found" + id));

        if (updatedModel.getAssignedToUserId() != null) {
            User user = userRepository.findById(updatedModel.getAssignedToUserId())
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

        return ModelMapper.toResponseDTO(modelRepository.save(existing)); // ✅

    }

    public ModelResponseDTO findById(Long id) {

        return modelRepository.findById(id)
                .map(ModelMapper::toResponseDTO)
                .orElseThrow(()-> new ResourceNotFoundException("No model with this id"));
    }

    public Optional<Model> findByModelName(String modelName) {
        return modelRepository.findByModelName(modelName);
    }

    public List<ModelResponseDTO> findByStatus(ModelStatus status) {
        List<Model> models = modelRepository.findByStatus(status);
        if (models.isEmpty()){
            throw new ResourceNotFoundException("No model found for status: " + status);
        }
        return models
                .stream()
                .map(ModelMapper::toResponseDTO)
                .toList();

    }
}
