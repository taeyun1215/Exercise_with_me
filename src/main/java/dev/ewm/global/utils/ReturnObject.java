package dev.ewm.global.utils;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Builder
@Data
public class ReturnObject {

    @Builder.Default
    private String msg = ""; // 에러 메세지

    @Builder.Default
    private String type = ""; // 에러 타입

    @Builder.Default
    private Object data = new HashMap<>(); // 결과값

}
