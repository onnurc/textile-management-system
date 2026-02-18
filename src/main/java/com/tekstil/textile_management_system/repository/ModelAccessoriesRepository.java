package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelAccessories;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelAccessoriesRepository extends JpaRepository <ModelAccessories,Long> {
    List<ModelAccessories> findByAccessoryType(String accessoryType);
    List<ModelAccessories> findByColor(String color);
    List<ModelAccessories> findAllByOrderByQuantityPerUnitAsc(Integer quantityPerUnit);
    List<ModelAccessories> findByTotalRequired(Integer totalRequired);
    List<ModelAccessories> findBySupplier(String supplier);

}
