package user.application.port.in.usecase;

import user.domain.User;

public interface UpdateUsernameUseCase {

    void updateUsername(User user, String username);

}
