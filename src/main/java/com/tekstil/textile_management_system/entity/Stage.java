package com.tekstil.textile_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String displayName;

    //asama sirasi
    @Column(nullable = false)
    private Integer orederIndex;

    @Column(length = 1000)
    private String description;

    @Column(name = "required_checklist",columnDefinition = "TEXT")
    private String requiredChecklist;

    @Column(name = "estimated_duration_hours")
    private Integer estimatedDurationHours;

    @Column(nullable = false)
    private boolean active = true;

}
