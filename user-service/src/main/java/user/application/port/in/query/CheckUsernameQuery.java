package user.application.port.in.query;

import user.domain.User;

public interface CheckUsernameQuery {

    User checkUsername(String username);

}
