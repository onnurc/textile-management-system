package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.User;
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
public interface ModelRepository extends JpaRepository {

    Optional findByModelNo(String model_no);
    List findByBrand(String brand);
    List findByStatus(ModelStatus status);
    List findByPriority(Priority priority);

//    List findByUser(User user);


    List <Model>findByStatusAndPriority(ModelStatus status, Priority priority);
    List <Model>findByBrandAndSeason(String brand,String season);


    List <Model>findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List <Model>findByDeadlineBefore(LocalDateTime date); // deadline expired

    List <Model>findByAssignedToId(User userId);
    List <Model>findByCreatedById(User userId);

    @Query("SELECT m FROM MOdel m WHERE m.status = :status AND m.deadline < :now") //select*from model
    List <Model>findOverdueModels(@Param("status") ModelStatus status,@Param("now") LocalDateTime now);

    List <Model>CountByStatus(ModelStatus modelStatus);
    List <Model>CountByPriority(Priority priority);

    boolean existByModelNo (String modelNo);


}
