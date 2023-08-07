package com.tutorial.springtutorial.dto.converter;

import com.tutorial.springtutorial.dto.UserDto;
import com.tutorial.springtutorial.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convertToUserDto(User from){
        return new UserDto(
                from.getId(),
                from.getName(),
                from.getSurname());
    }
}
