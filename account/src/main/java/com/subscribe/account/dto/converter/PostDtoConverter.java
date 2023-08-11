package com.subscribe.account.dto.converter;

import com.subscribe.account.dto.PostDto;
import com.subscribe.account.model.Post;
import org.springframework.stereotype.Component;


@Component
public class PostDtoConverter {
    public PostDto convertToPostDto(Post from){
        return new PostDto(
                from.getId(),
                from.getTitle(),
                from.getText(),
                from.getUser().getId()
        );
    }
}