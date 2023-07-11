package user.application.port.in.query;

import user.domain.User;

public interface CheckNicknameQuery {

    User checkNickname(String nickname);

}
