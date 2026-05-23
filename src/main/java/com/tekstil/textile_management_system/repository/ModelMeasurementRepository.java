package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.ModelMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelMeasurementRepository extends JpaRepository<ModelMeasurement,Long> {

    List<ModelMeasurement> findByModelId(long modelId);

    void deleteByModelId(long modelId);

    void deleteAllByModelId(long id);
}
