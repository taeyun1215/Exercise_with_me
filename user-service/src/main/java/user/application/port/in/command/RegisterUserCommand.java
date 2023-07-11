package user.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import user.domain.User;
import user.domain.constant.Role;

@Builder
@Data
public class RegisterUserCommand {

    private String username;
    private String password;
    private String confirmPassword;
    private String nickname;
    private String phone;
    private String email;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .role(Role.USER)
                .build();
    }

}
