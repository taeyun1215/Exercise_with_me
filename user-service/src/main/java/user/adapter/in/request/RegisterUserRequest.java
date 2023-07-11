package user.adapter.in.request;

import lombok.EqualsAndHashCode;
import lombok.Value;
import user.global.common.SelfValidating;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterUserRequest extends SelfValidating<RegisterUserRequest> {

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

    public RegisterUserRequest(
            String username,
            String password,
            String confirmPassword,
            String nickname,
            String phone,
            String email
    ) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.validateSelf();
    }

}
