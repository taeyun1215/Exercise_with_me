package dev.ewm.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ;

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    };

}
