package com.example.social.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String content;
    private String imageUrl;
    private LocalDate createdAt = LocalDate.now();
    private UserDto author;
    private List<CommentDto> comment;
    private List<LikeDto> like;
}
