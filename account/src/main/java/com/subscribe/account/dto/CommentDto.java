package com.subscribe.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private Long userId;
    private Long postId;
}
