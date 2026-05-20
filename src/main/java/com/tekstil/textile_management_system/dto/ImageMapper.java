package com.tekstil.textile_management_system.dto;

import com.tekstil.textile_management_system.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public static ImageDTO toDto(Image image, String presignedUrl) {
        return ImageDTO.builder()
                .id(image.getId())
                .imageUrl(presignedUrl)
                .originalName(image.getOriginalName())
                .isPrimary(image.getIsPrimary())
                .uploadedAt(image.getUploadedAt())
                .build();
    }
}