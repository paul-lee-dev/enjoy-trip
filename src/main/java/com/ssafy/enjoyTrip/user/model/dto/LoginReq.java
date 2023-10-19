package com.ssafy.enjoyTrip.user.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
public class LoginReq {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String emailId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
