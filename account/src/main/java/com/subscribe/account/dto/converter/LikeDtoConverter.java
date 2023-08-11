package com.subscribe.account.dto.converter;

import com.subscribe.account.dto.LikeDto;
import com.subscribe.account.model.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeDtoConverter {
    public LikeDto convertToLikeDto(Like from){
        return new LikeDto(
                from.getId(),
                from.getUser().getId(),
                from.getPost().getId()
        );
    }
}
