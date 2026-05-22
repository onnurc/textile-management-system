package com.tekstil.textile_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "model_measurements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "model_id",nullable = false)
    private Model model;

    @Column(nullable = false)
    private String rowLabel;

    @Column(nullable = false)
    private String colLabel;

    private String value;


    @Column
    private String tableLabel;


}
