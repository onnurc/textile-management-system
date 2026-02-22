package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelComments;
import com.tekstil.textile_management_system.entity.ModelStageHistory;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.service.ModelCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/model-comments")
@RequiredArgsConstructor
public class ModelCommentController {
    private final ModelCommentService modelCommentService;

    @PostMapping
    public ResponseEntity<ModelComments> createComment(@RequestBody ModelComments comment) {
        ModelComments created = modelCommentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ModelComments>> findAll() {
        List<ModelComments> comments = modelCommentService.findAll();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<ModelComments>> findByModel(@PathVariable Long modelId) {
        Model model = new Model();
        model.setId(modelId);
        List<ModelComments> comments = modelCommentService.findByModel(model);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/stage-history/{stageHistoryId}")
    public ResponseEntity<List<ModelComments>> findByStageHistory(@PathVariable Long stageHistoryId) {
        ModelStageHistory stageHistory = new ModelStageHistory();
        stageHistory.setId(stageHistoryId);
        List<ModelComments> comments = modelCommentService.findByStageHistory(stageHistory);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ModelComments>> findByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<ModelComments> comments = modelCommentService.findByUser(user);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ModelComments>> findByCommentTextContains(@PathVariable String keyword) {
        List<ModelComments> comments = modelCommentService.findByCommentTextContains(keyword);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/before")
    public ResponseEntity<List<ModelComments>> findByCreatedAtBefore(@RequestParam LocalDateTime localDateTime) {
        List<ModelComments> comments = modelCommentService.findByCreatedAtBeforeOrderByCreatedAtDesc(localDateTime);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelComments> updateComment(@PathVariable Long id, @RequestBody ModelComments modelComments) {
        ModelComments updated = modelCommentService.updateComment(id, modelComments);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        modelCommentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}