package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.enums.ModelStatus;
import com.tekstil.textile_management_system.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional <Model>findByModelName(String modelName);
    List <Model>findByBrand(String brand);

    List <Model>findByPriority(Priority priority);
    List<Model> findByCategory(String category);



    @Query("SELECT m FROM Model m WHERE m.status = :status AND m.deadline < :now") //select*from model
    List <Model>findOverdueModels(@Param("status") ModelStatus status,@Param("now") LocalDateTime now);

    boolean existsByModelName(String modelName);


    List<Model> findByStatus(ModelStatus status);
}
