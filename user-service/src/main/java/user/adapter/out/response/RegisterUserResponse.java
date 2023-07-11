package user.adapter.out.response;

import lombok.Builder;
import lombok.Getter;
import user.domain.constant.Role;

@Getter
@Builder
public class RegisterUserResponse {

    private String username;
    private String nickname;
    private String phone;
    private String email;
    private Role role;

}
