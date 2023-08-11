package com.subscribe.account.service;

import com.subscribe.account.dto.CommentDto;
import com.subscribe.account.dto.converter.CommentDtoConverter;
import com.subscribe.account.exception.UserNotFoundException;
import com.subscribe.account.model.Comment;
import com.subscribe.account.model.Post;
import com.subscribe.account.model.User;
import com.subscribe.account.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository,
                          CommentDtoConverter commentDtoConverter,
                          UserService userService,
                          PostService postService){
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.userService = userService;
        this.postService = postService;
    }

    public CommentDto createComment(CommentDto newComment) {
        User user = userService.findUserById(newComment.getUserId());
        Post post = postService.findPostById(newComment.getPostId());
        if(user == null && post == null) return null;

        Comment comment = new Comment();
        comment.setId(newComment.getId());
        comment.setText(newComment.getText());
        comment.setUser(user);
        comment.setPost(post);

        return commentDtoConverter.convertToCommentDto(commentRepository.save(comment));
    }

    public List<CommentDto> getAllComments(Optional<Long> userId) {
        if (userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }
        return commentRepository.findAll()
                .stream()
                .map(commentDtoConverter::convertToCommentDto)
                .collect(Collectors.toList());
    }

    protected Comment findCommentById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Comment could not find by id: " + id));
    }

    public CommentDto getCommentById(Long commentId) {
        return commentDtoConverter.convertToCommentDto(findCommentById(commentId));
    }

    public Comment updateComment(Long commentId, CommentDto newComment) {
        Comment updatedComment = isCommentExist(commentId);
        updatedComment.setText(newComment.getText());
        return commentRepository.save(updatedComment);
    }

    public Comment deleteComment(Long commentId) {
        Comment comment = isCommentExist(commentId);
        commentRepository.delete(comment);
        return comment;
    }

    private Comment isCommentExist(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (Objects.isNull(comment)){
            throw new UserNotFoundException("Comment could not find by id: " + commentId);
        }
        return comment;
    }

}









