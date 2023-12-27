package com.ssafy.enjoyTrip.user.entity.dto;

import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateUserReq {

    private String email;

    private String password;

    private String passwordCheck;

    private String nickname;

    private String phoneNumber;
}
