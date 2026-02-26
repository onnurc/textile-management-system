package com.tekstil.textile_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ModelStageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "model_id",nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user_id", foreignKey = @ForeignKey(name = "FK_HISTORY_USER"),nullable = false)
    private User assignedUser;

    private LocalDateTime completedAt;

    private LocalDateTime estimatedEndDate;

    private String status = "IN_PROGRESS";

    @Size(max = 1000, message = "waiting reason cannot be longer than 1000 characters")
    @Column(length = 1000)
    private String waitingReason;

    @Column(columnDefinition = "TEXT")
    private String checklistProgress;

    @Size(max = 2000, message = "note cannot be longer than 2000 characters")
    @Column(length = 2000)
    private String notes;

    public void preUpdate(){
        if ("COMPLETED".equals(this.status) && this.completedAt == null){
            this.completedAt = LocalDateTime.now();
        }
    }

}
