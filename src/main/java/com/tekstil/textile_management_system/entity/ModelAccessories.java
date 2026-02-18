package com.tekstil.textile_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "model_accessories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelAccessories{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(nullable = false)
    private String accessoryType;

    @Column(nullable = false)
    private String description;

    private String color;
    private Integer quantityPerUnit; //  required amount for single sample
    private Integer totalRequired; // total order amount

    @Column(nullable = false)
    private String supplier;










}
