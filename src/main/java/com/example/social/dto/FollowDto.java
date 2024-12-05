package com.example.social.dto;

import lombok.Data;

@Data
public class FollowDto {
    private Long id;
    private UserDto follower;
    private UserDto followed;
}
