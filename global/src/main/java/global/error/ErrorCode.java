package global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 유저
    ALREADY_REGISTERED_MEMBER(400, "이미 가입된 정보 있습니다."),
    MISMATCHED_PASSWORD(401, "패스워드가 일치하지 않습니다."),
    LOGIN_ERROR(401, "아이디 또는 비밀번호를 확인해주세요"),

    // 게시글
    MEMBER_NOT_EXISTS(400, "해당 회원은 존재하지 않습니다.");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    };

}
