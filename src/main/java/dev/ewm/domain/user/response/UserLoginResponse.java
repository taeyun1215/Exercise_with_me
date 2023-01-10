package dev.ewm.domain.user.response;

import dev.ewm.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponse {

    private String username;

    public static UserLoginResponse from(User user) {
        return UserLoginResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
