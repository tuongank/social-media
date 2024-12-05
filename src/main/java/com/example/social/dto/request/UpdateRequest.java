package com.example.social.dto.request;

import lombok.Data;

@Data
public class UpdateRequest {
    private String fullName;
    private String password;
    private String bio;
    private String address;
}
