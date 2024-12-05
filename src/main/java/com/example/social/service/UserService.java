package com.example.social.service;

import com.example.social.dto.UserDto;
import com.example.social.dto.request.UpdateRequest;
import com.example.social.dto.response.ApiResponse;
import com.example.social.entity.User;
import com.example.social.exception.NotFoundException;
import com.example.social.mapper.UserMapper;
import com.example.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("Chưa đăng nhập"));
    }

    public ApiResponse getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = users
                .stream()
                .map(userMapper::toUserDto)
                .toList();

        return ApiResponse.builder()
                .status(200)
                .message("Lấy danh sách người dùng thành công")
                .userList(userDtoList)
                .build();
    }

    public ApiResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng có email: " + email));

        return ApiResponse.builder()
                .status(200)
                .message("Lấy thông tin người dùng thành công")
                .user(userMapper.toUserDto(user))
                .build();
    }

    public ApiResponse updateUser(UpdateRequest request) {
        User user = getMyInfo();

        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setBio(request.getBio());

        userRepository.save(user);

        return ApiResponse.builder()
                .status(200)
                .message("Cập nhật thông tin người dùng thành công")
                .user(userMapper.toUserDto(user))
                .build();
    }

    public ApiResponse delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng có id: " + id));

        userRepository.delete(user);

        return ApiResponse.builder()
                .status(200)
                .message("Xóa người dùng thành công")
                .build();
    }

}
