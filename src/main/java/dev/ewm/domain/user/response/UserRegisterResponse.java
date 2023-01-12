package dev.ewm.domain.user.response;

import dev.ewm.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegisterResponse {

    private String username;
    private String password;
    private String nickname;
    private String phone;

    public static UserRegisterResponse from(User user) {
        return UserRegisterResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .build();
    }
}
