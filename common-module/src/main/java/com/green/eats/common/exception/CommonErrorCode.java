package com.green.eats.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
      INVALID_INPUT_VALUE("C001", "잘못된 입력값입니다.",HttpStatus.BAD_REQUEST)
    , INTERNAL_SERVER_ERROR("C002", "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
