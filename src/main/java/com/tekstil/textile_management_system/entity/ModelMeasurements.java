package com.tekstil.textile_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.smartcardio.ATR;

@Entity
@Table(name = "model_measurements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelMeasurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;


    @Column(nullable = false)
    private String size;

    private double frontArmHole; // ön kol evi
    private double blackArmhole; // arka kol evi
    private double neckline; //yaka
    private double shoulder; // omuz
    private double chest;// goğüs
    private double waist;// bel
    private double hip;// Basen
    private double totalLength; // ürüm boyu
    private double sleeveLength;// kol boyu
    private double skirtLength;//etek boyu
    private double trouserLength;// pantolon boyu

    @Column(length = 1000)
    private String notes;


}
