package com.example.social.dto.response;

import com.example.social.dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private String role;
    private String expireTime;

    private UserDto user;
    private List<UserDto> userList;

    private PostDto post;
    private List<PostDto> postList;

    private LikeDto like;
    private List<LikeDto> likeList;

    private NotificationDto notification;
    private List<NotificationDto> notificationList;

    private FollowDto follow;
    private List<FollowDto> followList;

    private CommentDto comment;
    private List<CommentDto> commentList;
}
