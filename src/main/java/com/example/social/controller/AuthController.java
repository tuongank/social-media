package com.example.social.controller;

import com.example.social.dto.request.LoginRequest;
import com.example.social.dto.response.ApiResponse;
import com.example.social.service.AuthService;
import com.example.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestParam String fullName,
                                                @RequestParam String email,
                                                @RequestParam String password,
                                                @RequestParam(required = false) MultipartFile image,
                                                @RequestParam(required = false) String address,
                                                @RequestParam(required = false) String bio) throws IOException {

        return ResponseEntity.ok(authService.register(fullName, email, password, image, address, bio));
    }

    @PostMapping("/log-in")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
