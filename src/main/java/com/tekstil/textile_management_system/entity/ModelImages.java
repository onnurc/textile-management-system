package com.tekstil.textile_management_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.Banner;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "model_images")
@NoArgsConstructor
@AllArgsConstructor
public class ModelImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String imageType; // front view, back view

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by_user_id")
    private User uploadedBy;

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @Column(length = 500)
    private String description;


}
