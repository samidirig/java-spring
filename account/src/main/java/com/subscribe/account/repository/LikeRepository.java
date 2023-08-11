package com.subscribe.account.repository;

import com.subscribe.account.dto.LikeDto;
import com.subscribe.account.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<LikeDto> findByUserId(Long commentId);
}
