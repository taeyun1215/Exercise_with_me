package user.application.port.out;

import user.domain.User;

public interface LoadUserPort {

    User findByUsername(String username);
    User findByNickname(String nickname);

}
