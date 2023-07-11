package user.adapter.out.persistence;

import org.springframework.stereotype.Component;
import user.adapter.out.response.LoginUserResponse;
import user.adapter.out.response.RegisterUserResponse;
import user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserResponseMapper {

    // 회원가입 리스폰
    public RegisterUserResponse mapToRegisterUserResponse(User user) {
        return RegisterUserResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // 로그인 리스폰
    public LoginUserResponse mapToLoginUserResponse(User user) {
        return LoginUserResponse.builder()
                .username(user.getUsername())
                .build();
    }

}
