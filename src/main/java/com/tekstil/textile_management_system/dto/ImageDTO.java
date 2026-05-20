package com.tekstil.textile_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO {
    private Long id;
    private String imageUrl;
    private String originalName;
    private Boolean isPrimary;
    private LocalDateTime uploadedAt;

}
