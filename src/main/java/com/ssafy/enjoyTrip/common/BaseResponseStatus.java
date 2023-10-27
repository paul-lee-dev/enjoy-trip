package com.ssafy.enjoyTrip.common;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(1000, "요청에 성공하였습니다."),

    /**
     * 2xxx : 공통, DB
     */

    // @Valid 발생 예외 Code : 2000
//    INVALID_PARAM(2000, "입력값이 올바르지 않습니다."),

    // DB
    DB_ERROR(2400, "DB 에러"),


//    // null
//    NULL_EMAIL(2000, "이메일을 입력해주세요."),
//    NULL_PASSWORD(2001, "비밀번호를 입력해주세요."),
//    NULL_NICKNAME(2002, "nickname을 입력해주세요."),
//    NULL_FIRSTNAME(2003, "first name을 입력해주세요."),
//    NULL_LASTNAME(2004, "last name을 입력해주세요."),
//    NULL_PHONENUM(2005, "휴대폰 번호를 입력해주세요."),
//    NULL_ID(2006, "유저 ID가 없습니다."), // 프론트 ~ DB에서만 사용되는 ID값
//
//    // invalid
//    INVALID_EMAIL(2020, "이메일 형식이 올바르지 않습니다."),
//    INVALID_PASSWORD(2021, "비밀번호 형식이 올바르지 않습니다."),
//    INVALID_PHONENUM(2022, "휴대폰 번호 형식이 올바르지 않습니다."),

    // mismatch
    WRONG_EMAIL(3030, "유저 정보가 존재하지 않습니다."),
    WRONG_PASSWORD(3031, "잘못된 비밀번호입니다."),
    WRONG_PASSCHECK(3032, "재입력 비밀번호가 일치하지 않습니다."),
    WRONG_ID(3032, "유저 정보가 존재하지 않습니다."),

    // [회원가입] 중복
    DUPLICATED_EMAIL(3040, "이미 가입된 ID입니다."),
    DUPLICATED_NICKNAME(3041, "중복된 nickname입니다");


    /**
     * 4xxx : attraction
     */


    /**
     * 5xxx : board
     */
    /**
     * 56xxx : plan
     */




    private final int code;
    private final String message;

    private BaseResponseStatus(int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.code = code;
        this.message = message;
    }
}