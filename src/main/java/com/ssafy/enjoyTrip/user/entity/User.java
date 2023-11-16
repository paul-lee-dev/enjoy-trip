package com.ssafy.enjoyTrip.user.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class User {
    private int userId;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String phoneNumber;
    private String status;
    private String role;
    private String profileImgUrl;
}
