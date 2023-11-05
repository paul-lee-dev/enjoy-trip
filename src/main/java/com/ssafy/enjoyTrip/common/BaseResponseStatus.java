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
     * 2xxx : 공통, 시스템
     */

    DB_ERROR(2400, "DB 에러"),
    SESSION_ERROR(2420, "세션이 이미 만료되었습니다."),


    // mismatch
    WRONG_EMAIL(3030, "유저 정보가 존재하지 않습니다."),
    WRONG_PASSWORD(3031, "잘못된 비밀번호입니다."),
    WRONG_PASSCHECK(3032, "재입력 비밀번호가 일치하지 않습니다."),
    WRONG_ID(3032, "유저 정보가 존재하지 않습니다."),

    // [회원가입] 중복
    DUPLICATED_EMAIL(3040, "이미 가입된 ID입니다."),
    DUPLICATED_NICKNAME(3041, "중복된 nickname입니다");

    // login
    NEED_LOGIN(3050, "로그인이 필요합니다."),


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