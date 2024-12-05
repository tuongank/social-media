package com.example.social.config;

import com.example.social.entity.User;
import com.example.social.enums.ERole;
import com.example.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationInit implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String email;

    @Value("${admin.password}")
    private String password;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ERole role = ERole.ADMIN;

        if (userRepository.findByEmail(email).isEmpty()) {
            log.info("Tạo tài khoản admin mặc định là: {}", email);
            userRepository.save(User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .build());
        }
    }
}
