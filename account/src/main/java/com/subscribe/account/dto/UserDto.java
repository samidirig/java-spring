package com.subscribe.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String name;
    private String surname;
}
