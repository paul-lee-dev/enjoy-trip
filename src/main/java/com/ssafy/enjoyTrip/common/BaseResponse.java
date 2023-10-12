package com.ssafy.enjoyTrip.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private Header header;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;

    // 요청에 성공한 경우
    public BaseResponse(T result) {
        header = new Header();
        header.setResultCode(200);
        header.setResultMsg("요청 성공");
        this.body = result;
    }

    // 요청에 실패한 경우
    public BaseResponse(Exception e) {
        header = new Header();
        header.setResultCode(-1); // 나중에 만들기
        header.setResultMsg(e.getMessage());
    }

    @Getter
    @Setter
    public class Header{
        private String resultMsg;
        private int resultCode;
    }
}
