package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.dto.ImageDTO;
import com.tekstil.textile_management_system.dto.ImageMapper;
import com.tekstil.textile_management_system.entity.Image;
import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.repository.ImageRepository;
import com.tekstil.textile_management_system.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Service s3Service;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final ModelRepository modelRepository;


    public ImageDTO upload(Long modelId, MultipartFile file, Boolean isPrimary) {

        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Model cannot found"));


        //S3
        String key = s3Service.uploadImage(file, modelId);
        //DB
        Image image = Image.builder()
                .imageKey(key)
                .originalName(file.getOriginalFilename())
                .isPrimary(isPrimary)
                .uploadedAt(LocalDateTime.now())
                .model(model)
                .build();

        Image saved = imageRepository.save(image);


        String presignedUrl = s3Service.generatePresignedUrl(saved.getImageKey());
        return ImageMapper.toDto(saved, presignedUrl);

    }

    @Nullable
    public List<ImageDTO> getImages(Long modelId) {
        return imageRepository.findByModelId(modelId)
                .stream()
                .map(img -> ImageMapper.toDto(
                        img,
                        s3Service.generatePresignedUrl(img.getImageKey())
                )).toList();
    }


    public void delete(Long modelId, Long imageId) {

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("No image found with this Id"));

        //S3
        s3Service.deleteImage(image.getImageKey());
        //DB
        imageRepository.delete(image);

    }
}
