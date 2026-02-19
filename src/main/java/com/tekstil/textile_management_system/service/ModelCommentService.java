package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelComments;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.repository.ModelCommentsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelCommentService {
    private final ModelCommentsRepository modelCommentsRepository;

    public ModelComments createComment(ModelComments comment){
        return  modelCommentsRepository.save(comment);
    }
    public void deleteComment(Long id){
         modelCommentsRepository.deleteById(id);
    }
    public ModelComments updateComment(Long id, ModelComments modelComments){
        ModelComments existing = modelCommentsRepository.findById(id).orElseThrow(()-> new RuntimeException("Model Comment Not Found " + id));

        existing.setCommentText(modelComments.getCommentText());

        return modelCommentsRepository.save(existing);
    }
    public List<ModelComments> findAll(){
        return modelCommentsRepository.findAll();
    }


    public List<ModelComments> findByModel(Model model){
        return modelCommentsRepository.findByModel(model);
    }
    public List<ModelComments> findByStageHistory(ModelStageHistory modelStageHistory){
        return modelCommentsRepository.findByStageHistory(modelStageHistory);

    }
    public List<ModelComments> findByUser(User user){
        return modelCommentsRepository.findByUser(user);
    }
    public List<ModelComments> findByCommentTextContains(String keyword){
        return modelCommentsRepository.findByCommentTextContains(keyword);
    }
    public List<ModelComments> findByCreatedAtBeforeOrderByCreatedAtDesc(LocalDateTime localDateTime){
       return modelCommentsRepository.findByCreatedAtBeforeOrderByCreatedAtDesc(localDateTime);
    }


}
