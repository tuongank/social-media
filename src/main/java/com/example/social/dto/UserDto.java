package com.example.social.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String imageUrl;
    private String bio;
    private String address;
    private String role;
    private LocalDate createdAt = LocalDate.now();
    private List<PostDto> post;
    private List<CommentDto> comment;
    private List<FollowDto> following;
    private List<FollowDto> followers;
    private List<NotificationDto> notification;
}
