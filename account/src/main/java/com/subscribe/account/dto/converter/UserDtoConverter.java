package com.subscribe.account.dto.converter;

import com.subscribe.account.dto.UserDto;
import com.subscribe.account.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto convertToUserDto(User from){
        return new UserDto(
                from.getId(),
                from.getUserName(),
                from.getName(),
                from.getSurname());
    }
}
