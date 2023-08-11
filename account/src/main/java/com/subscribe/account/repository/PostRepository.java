package com.subscribe.account.repository;

import com.subscribe.account.dto.PostDto;
import com.subscribe.account.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<PostDto> findByUserId(Long userId);
}
