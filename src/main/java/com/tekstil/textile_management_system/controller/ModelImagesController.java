package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelImages;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.service.ModelImagesService;
import com.tekstil.textile_management_system.service.ModelService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model-images")
public class ModelImagesController {

    private final ModelImagesService modelImagesService;
    @PostMapping
    public ResponseEntity<ModelImages> createImage(@RequestBody ModelImages modelImages){
        ModelImages created = modelImagesService.createImage(modelImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<ModelImages>> findByModel(@PathVariable Long modelId){
        List<ModelImages> modelImages = modelImagesService.findByModel(modelId);
        return ResponseEntity.ok(modelImages);
    }
    @GetMapping()
    public ResponseEntity<List<ModelImages>> findAll(){
        List<ModelImages> modelImages = modelImagesService.findAll();
        return ResponseEntity.ok(modelImages);
    }

    @GetMapping("/model-image-type/{imageType}")
    public ResponseEntity<List<ModelImages>> findByImageType(@PathVariable String imageType){
        List<ModelImages> modelImages = modelImagesService.findByImageType(imageType);
        return ResponseEntity.ok(modelImages);
    }

    @GetMapping("/uploaded-by/{userId}")
    public ResponseEntity<List<ModelImages>> findByUploadedBy(@PathVariable Long userId){
        List<ModelImages> modelImages = modelImagesService.findByUploadedBy(userId);
        return ResponseEntity.ok(modelImages);
    }
    @GetMapping("/uploaded-at/{localDateTime}")
    public ResponseEntity<List<ModelImages>> findByUploadedAtBeforeOrderByUploadedAtAsc(@RequestParam LocalDateTime localDateTime){
        List<ModelImages>  modelImages = modelImagesService.findByUploadedAtBeforeOrderByUploadedAtAsc(localDateTime);
        return ResponseEntity.ok(modelImages);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ModelImages>> findByUploadedAtBeforeOrderByUploadedAtAsc(@PathVariable String keyword){
        List<ModelImages>  modelImages = modelImagesService.findByDescriptionContains(keyword);
        return ResponseEntity.ok(modelImages);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <ModelImages> deleteImage(@PathVariable Long id){
        modelImagesService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelImages> updateImage(@PathVariable Long id, @RequestBody ModelImages modelImages){
        ModelImages updated = modelImagesService.updateImage(id, modelImages);
        return ResponseEntity.ok(updated);
    }



//    @PutMapping("/{id}")
//    public ResponseEntity<Model> updateModel(@PathVariable Long id, @RequestBody Model model) {
//        Model updated = modelService.updateModel(id, model);
//        return ResponseEntity.ok(updated);
//    }


}
