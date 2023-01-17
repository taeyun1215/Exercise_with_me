package dev.ewm.domain.user.request;

import dev.ewm.domain.user.User;
import dev.ewm.domain.user.constant.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserRegisterRequest {

    @Pattern(
            regexp = "^[a-z0-9]{4,20}$",
            message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다."
    )
    @NotBlank(message = "아이디은 필수 입력 값입니다.")
    private String username;

    @Pattern(
            regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 숫자,문자,특수문자를 포함한 6~18로 입력해주세요."
    )
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String confirmPassword;

    @Size(min = 2, max = 12, message = "아이디는 2 ~ 12 사이로 입력해주세요.")
    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    @Pattern(
            regexp = "^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$",
            message = "전화번호 형식이 올바르지 않습니다."
    )
    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    private String phone;

    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;


    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
