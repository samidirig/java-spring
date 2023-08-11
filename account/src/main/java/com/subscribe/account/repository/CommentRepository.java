package com.subscribe.account.repository;

import com.subscribe.account.dto.CommentDto;
import com.subscribe.account.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<CommentDto> findByUserId(Long commentId);
}
