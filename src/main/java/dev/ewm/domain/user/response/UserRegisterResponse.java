package dev.ewm.domain.user.response;

import dev.ewm.domain.user.constant.Role;
import dev.ewm.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserRegisterResponse {

    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static UserRegisterResponse from(User user) {
        return UserRegisterResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
