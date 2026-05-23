package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelStageHistoryRepository extends JpaRepository<ModelStageHistory, Long> {

    boolean existsByAssignedUser(User user);

    List<ModelStageHistory> findByModel_IdOrderByCompletedAtDesc(Long modelId);
    List<ModelStageHistory> findByStage_Id(Long stageId);
    List<ModelStageHistory> findByAssignedUser_EmailOrderByCompletedAtDesc(String email);


    List<ModelStageHistory> findByStatus(ModelStatus status);

    boolean existsByModelIdAndStageId(Long id, Long id1);

    @Query("SELECT DISTINCT h.model.id FROM ModelStageHistory h WHERE h.assignedUser.id = :userId")
    List<Long> findModelIdsByUserId(@Param("userId") Long userId);


}

