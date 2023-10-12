package com.ssafy.enjoyTrip.user.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer userId;
    private String emailId;
    private String password;
    private String nickname;
}
