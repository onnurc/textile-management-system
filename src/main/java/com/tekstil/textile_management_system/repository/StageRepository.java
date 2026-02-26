package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.enums.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage,Long> {


    Optional <Stage> findByName(ModelStatus name);

    List<Stage> findByActiveTrue();

    List<Stage> findByActiveTrueOrderByOrderIndexAsc();
    List<Stage> findByRequiredChecklistContainingIgnoreCase(String keyword);//
    List<Stage> findByEstimatedDurationHoursLessThan(Integer estimatedDurationHours);


}
