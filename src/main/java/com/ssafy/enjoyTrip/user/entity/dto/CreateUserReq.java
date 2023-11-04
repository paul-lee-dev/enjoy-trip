package com.ssafy.enjoyTrip.user.entity.dto;

import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
public class CreateUserReq {

    @ApiParam(value="이메일 ID", required = true, example = "testEmail@gmail.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String emailId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$", message = "비밀번호는 8자 이상의 영문,숫자,특수문자 조합으로 구성해주세요.")
    private String password;

    // 일치여부 체크는 service에서
    @NotBlank(message = "확인 비밀번호를 입력해주세요.")
    private String passwordCheck;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z가-힣]{1,20}$" , message = "이름에 특수문자, 숫자는 사용할 수 없습니다.")
    private String firstName;

    @NotBlank(message = "성을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z가-힣]{1,20}$" , message = "이름에 특수문자, 숫자는 사용할 수 없습니다.")
    private String lastName;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9._-]{2,20}$" , message = "닉네임은 2~20자의 한글, 영어 대/소문자, 숫자, -, _로 구성되어야 합니다.")
    private String nickname;

    @ApiParam(value = "-를 포함한 전화번호", example = "010-1234-1234")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNumber;
}
