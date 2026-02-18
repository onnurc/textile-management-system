package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelMaterialsRepository extends JpaRepository<ModelMaterials, Long> {

    List<ModelMaterials> findByModel(Model model);

    List<ModelMaterials> findByMaterialType(String materialType);

    List<ModelMaterials> findBySupplier(String supplier);

    List<ModelMaterials> findByStatus(String status);

    List<ModelMaterials> findByColorIgnoreCase(String color);

    List<ModelMaterials> findByNotesContainingIgnoreCase(String keyword);

}
