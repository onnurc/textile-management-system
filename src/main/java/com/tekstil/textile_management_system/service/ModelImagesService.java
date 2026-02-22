package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelImages;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.repository.ModelImagesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ModelImagesService {
    private final ModelImagesRepository modelImagesRepository;

    public ModelImages createImage(ModelImages modelImage){
        return modelImagesRepository.save(modelImage);
    }
    public List<ModelImages> findAll(){
        return modelImagesRepository.findAll();
    }
    public void deleteImage(Long id){
         modelImagesRepository.deleteById(id);
    }
    public List<ModelImages> findByModel(Long modelId){
        Model model = new Model();
        model.setId(modelId);
        return modelImagesRepository.findByModel(model);
    }
    public List<ModelImages> findByImageType(String imageType){
        return modelImagesRepository.findByImageType(imageType);
    }
    public List<ModelImages> findByUploadedBy(Long userId){
        User user = new User();
        user.setId(userId);
        return modelImagesRepository.findByUploadedBy(user);
    }
    public List<ModelImages> findByUploadedAtBeforeOrderByUploadedAtAsc(LocalDateTime localDateTime){
        return modelImagesRepository.findByUploadedAtBeforeOrderByUploadedAtAsc(localDateTime);
    }
    public List<ModelImages> findByDescriptionContains(String keyword){
        return modelImagesRepository.findByDescriptionContains(keyword);
    }

    public ModelImages updateImage(Long id, ModelImages modelImages) {
        ModelImages existing = modelImagesRepository.findById(id).orElseThrow(()-> new RuntimeException("Image not found : " + id));
        existing.setImageType(modelImages.getImageType());
        existing.setDescription(modelImages.getDescription());
        existing.setUploadedBy(modelImages.getUploadedBy());
        return modelImagesRepository.save(existing);
    }
}
