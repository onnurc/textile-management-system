package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelComments;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ModelCommentsRepository extends JpaRepository<ModelComments, Long> {

    List<ModelComments> findByModel(Model model);
    List<ModelComments> findByStageHistory(ModelStageHistory modelStageHistory);
    List<ModelComments> findByUser(User user);
    List<ModelComments> findByCommentTextContains(String keyword);
    List<ModelComments> findByCreatedAtBeforeOrderByCreatedAtDesc(LocalDateTime localDateTime);


}
