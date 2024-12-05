package com.example.social.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private LocalDate createdAt = LocalDate.now();
    private UserDto author;
    private PostDto post;
}
