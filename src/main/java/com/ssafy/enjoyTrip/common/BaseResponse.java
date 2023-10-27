package com.ssafy.enjoyTrip.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private int Status;
    private String message;
    private T body;

    // 요청에 성공한 경우
    public BaseResponse(T result) {
        this.Status = 200;
        this.message = "요청 성공";
        this.body = result;
    }

    // 요청에 실패한 경우
    public BaseResponse(BaseException e) {
        BaseResponseStatus status = e.getStatus();
        this.Status = status.getCode();
        this.message = status.getMessage();
    }

    // @Valid 예외
    public BaseResponse(MethodArgumentNotValidException e) {
        this.Status = 2000;
        this.message = e.getMessage();
    }
}
