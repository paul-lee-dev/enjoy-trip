package com.ssafy.enjoyTrip.user.model.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class User {
    private int userId;
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String phoneNumber;
    private String status;
}
