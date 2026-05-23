package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.ImageDTO;
import com.tekstil.textile_management_system.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/models/{modelId}/images")
@RestController
@PreAuthorize("hasAnyRole('COMPANY_MANAGER','STYLIST','MODELIST','OPERATOR','CUTTER','TRIM_SPECIALIST','FASON','PACKAGING_SPECIALIST')")
public class ImageController {

    @Autowired
    private ImageService modelImageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageDTO> upload(
            @PathVariable Long modelId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "false") Boolean isPrimary) {

        return ResponseEntity.ok(modelImageService.upload(modelId, file, isPrimary));
    }

    @GetMapping()
    public ResponseEntity<List<ImageDTO>> getImages(@PathVariable Long modelId) {
        return ResponseEntity.ok(modelImageService.getImages(modelId));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long modelId,
            @PathVariable Long imageId) {
        modelImageService.delete(modelId, imageId);
        return ResponseEntity.noContent().build();
    }
}