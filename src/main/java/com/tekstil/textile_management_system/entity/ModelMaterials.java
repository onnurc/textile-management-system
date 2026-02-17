package com.tekstil.textile_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "model_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ModelMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;


    @Column(nullable = false)
    private String materialType; // kumas, tela, marsel
    private String description; // normally it is not nullable, it is my decision right now
    private String color;
    @Column(nullable = false)// every item has a supplier
    private String supplier;
    private double metrajPerUnit; // required amount of material for single sample
    private Integer totalRequired; // total order amount
    @Column(nullable = false)
    private String status;
    @Column(length = 1000)
    private String notes;

}
