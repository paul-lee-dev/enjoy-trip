package com.ssafy.enjoyTrip.user.entity.dto;

import com.ssafy.enjoyTrip.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserRes {
    private int userId;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
    private String phoneNumber;
    private String status;
    private String role;
    private String profileImgUrl;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .email(email)
                .nickname(nickname)
                .createdAt(createdAt)
                .updatedAt(null)
                .phoneNumber(phoneNumber)
                .status(status)
                .role(role)
                .profileImgUrl(profileImgUrl)
                .build();
    }
}
