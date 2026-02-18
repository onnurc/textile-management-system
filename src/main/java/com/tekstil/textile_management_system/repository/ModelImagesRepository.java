package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelImages;
import com.tekstil.textile_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ModelImagesRepository extends JpaRepository<ModelImages, Long> {

    List<ModelImages> findByModel(Model model);
    List<ModelImages> findByImageType(String imageType);
    List<ModelImages> findByUploadedBy(User user);
    List<ModelImages> findByUploadedAtBeforeOrderByUploadedAtAsc(LocalDateTime localDateTime);
    List<ModelImages> findByDescriptionContains(String keyword);

}
