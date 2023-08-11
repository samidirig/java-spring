package com.subscribe.account.dto.converter;

import com.subscribe.account.dto.CommentDto;
import com.subscribe.account.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoConverter {
    public CommentDto convertToCommentDto(Comment from){
        return new CommentDto(
                from.getId(),
                from.getText(),
                from.getUser().getId(),
                from.getPost().getId()
        );
    }
}
