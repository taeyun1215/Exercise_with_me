package user.application.port.in.usecase;

import user.adapter.in.request.LoginUserRequest;
import user.domain.User;

public interface LoginUserUseCase {

    User loginUser(LoginUserRequest loginUserCommand);

}
