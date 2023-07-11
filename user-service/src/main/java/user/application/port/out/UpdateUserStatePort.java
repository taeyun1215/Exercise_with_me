package user.application.port.out;

import user.domain.User;

public interface UpdateUserStatePort {

    void updateUsername(User user, String username);

}
