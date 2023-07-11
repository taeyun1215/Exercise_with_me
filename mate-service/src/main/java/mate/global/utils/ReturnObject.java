package mate.global.utils;

import lombok.Builder;
import lombok.Data;
import mate.global.error.ErrorCode;

@Builder
@Data
public class ReturnObject {

    private final boolean success; // 성공 여부

    private final Object data; // 결과값

    private final ErrorCode errorCode;

}
