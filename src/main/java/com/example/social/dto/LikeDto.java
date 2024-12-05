package com.example.social.dto;

import lombok.Data;

@Data
public class LikeDto {
    private Long id;
    private PostDto post;
    private UserDto user;
}
