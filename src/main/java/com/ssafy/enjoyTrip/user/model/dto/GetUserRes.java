package com.ssafy.enjoyTrip.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@Builder
public class GetUserRes {
    private int userId;
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDateTime createdAt;
    private String phoneNumber;
    private String status;
}
