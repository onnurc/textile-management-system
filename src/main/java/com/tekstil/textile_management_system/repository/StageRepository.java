package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Stages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StageRepository extends JpaRepository<Stages,Long> {


    Optional <Stages> findByName(String name);

    List<Stages> findByActiveTrue();

    List<Stages> findByActiveTrueOrderByOrderIndexAsc();



}
