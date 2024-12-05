package com.example.social.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class NotificationDto {
    private Long id;
    private String message;
    private LocalDate createdAt = LocalDate.now();
    private UserDto recipient;
}
