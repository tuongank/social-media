package com.example.social.service;

import com.example.social.dto.request.LoginRequest;
import com.example.social.dto.response.ApiResponse;
import com.example.social.entity.User;
import com.example.social.enums.ERole;
import com.example.social.mapper.UserMapper;
import com.example.social.repository.UserRepository;
import com.example.social.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // dang ky nguoi dung
    public ApiResponse register(String fullName,
                                String email,
                                String password,
                                MultipartFile imageUrl,
                                String address,
                                String bio) throws IOException {

        // kiem tra email da ton tai chua
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email đã được đăng ký");
        }
        ERole role = ERole.USER;
        String image = cloudinaryService.uploadImage(imageUrl);

        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .imageUrl(image)
                .address(address)
                .bio(bio)
                .role(role)
                .build();

        userRepository.save(user);

        return ApiResponse.builder()
                .status(200)
                .message("Đăng ký thành công")
                .user(userMapper.toUserDto(user))
                .build();
    }

    // dang nhap
    public ApiResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không chính xác");
        }

        String token = jwtUtils.generateToken(user);

        return ApiResponse.builder()
                .status(200)
                .message("Đăng nhập thành công")
                .token(token)
                .role(user.getRole().name())
                .build();
    }
}
