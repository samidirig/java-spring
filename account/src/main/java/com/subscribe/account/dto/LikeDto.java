package com.subscribe.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeDto {
    private Long id;
    private Long userId;
    private Long postId;
}
