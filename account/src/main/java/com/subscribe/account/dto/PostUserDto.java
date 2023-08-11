package com.subscribe.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostUserDto {
    private Long id;
    private String name;
    private String surname;
}
