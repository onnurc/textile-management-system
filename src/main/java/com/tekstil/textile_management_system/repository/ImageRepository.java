package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Image;
import com.tekstil.textile_management_system.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

List<Image> findByModelId(Long modelId);

}
