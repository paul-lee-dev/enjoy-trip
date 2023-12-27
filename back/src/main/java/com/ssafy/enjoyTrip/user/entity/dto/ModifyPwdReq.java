package com.ssafy.enjoyTrip.user.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
public class ModifyPwdReq {

    @NotNull(message = "userId is null")
    private Integer userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

    // 일치여부 체크는 service에서
    @NotBlank(message = "확인 비밀번호를 입력해주세요.")
    private String passwordCheck;
}
