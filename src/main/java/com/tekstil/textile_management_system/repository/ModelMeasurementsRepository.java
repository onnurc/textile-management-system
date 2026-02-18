package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelMeasurementsRepository extends JpaRepository<ModelMeasurements, Long> {

    List<ModelMeasurements> findByModel(Model model);

    Optional<ModelMeasurements> findByModelAndSize(Model model, String size);

    List<ModelMeasurements> findBySize(String size);

    List<ModelMeasurements> findByNotesContainingIgnoreCase(String keyword);
}