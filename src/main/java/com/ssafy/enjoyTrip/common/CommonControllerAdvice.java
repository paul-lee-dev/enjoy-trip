package com.ssafy.enjoyTrip.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<?> baseExceptionHandler(BaseException e) {
        return ResponseEntity
                .badRequest()
                .body(new BaseResponse<>(e));
    }

    // @Valid에서 발생할 예외
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> validationExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity
                .badRequest()
                .body(new BaseResponse<>(e));
    }
}
