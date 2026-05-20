package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.enums.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ModelStageHistoryRepository extends JpaRepository<ModelStageHistory, Long> {

    List<ModelStageHistory> findByModel_IdOrderByCompletedAtDesc(Long modelId);
    List<ModelStageHistory> findByStage_Id(Long stageId);
    List<ModelStageHistory> findByAssignedUserOrderByCompletedAtDesc(String userName);
    List<ModelStageHistory> findByStatus(ModelStatus status);

    boolean existsByModelIdAndStageId(Long id, Long id1);

}

