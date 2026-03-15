package com.tekstil.textile_management_system.entity;

import com.tekstil.textile_management_system.enums.ModelStatus;
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
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private ModelStatus name;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(length = 1000)
    private String description;

    @Column(name = "required_checklist",columnDefinition = "TEXT")
    private String requiredChecklist;

    @Column(name = "estimated_duration_hours")
    private Integer estimatedDurationHours;

    @Column(nullable = false)
    private Boolean active = true;

}
