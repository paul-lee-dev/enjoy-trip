package com.ssafy.enjoyTrip.user.entity.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserReq {

    @NotNull(message = "userId is null")
    private Integer userId;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z가-힣]{1,20}$" , message = "이름에 특수문자, 숫자는 사용할 수 없습니다.")
    private String name;

    private String profileImgUrl;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9._-]{2,20}$" , message = "닉네임은 2~20자의 한글, 영어 대/소문자, 숫자, -, _로 구성되어야 합니다.")
    private String nickname;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNumber;
}
