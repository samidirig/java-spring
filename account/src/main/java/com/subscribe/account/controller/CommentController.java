package com.subscribe.account.controller;

import com.subscribe.account.dto.CommentDto;
import com.subscribe.account.model.Comment;
import com.subscribe.account.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(@RequestParam Optional<Long> userId){
        return ResponseEntity.ok(commentService.getAllComments(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto newComment) {
        return ResponseEntity.ok(commentService.createComment(newComment));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("commentId") Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @PutMapping("/edit/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(commentId, commentDto));
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("commentId") Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

}
