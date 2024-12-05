package com.example.social.mapper;

import com.example.social.dto.UserDto;
import com.example.social.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .address(user.getAddress())
                .bio(user.getBio())
                .role(user.getRole().name())
                .build();
    }
}
