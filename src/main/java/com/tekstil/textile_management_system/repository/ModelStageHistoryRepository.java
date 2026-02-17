package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.Stage;
import com.tekstil.textile_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ModelStageHistoryRepository extends JpaRepository<ModelStageHistory, Long> {

    List<ModelStageHistory> findByModel_IdOrderByCompletedAtDesc(Long modelId);
    List<ModelStageHistory> findByStage(Stage stage);
    List<ModelStageHistory> findByAssignedUserOrderByCompletedAtDesc(User user);
    List<ModelStageHistory> findByStatus(String status);

}

