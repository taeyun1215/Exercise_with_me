package user.application.port.in.usecase;

import user.application.port.in.command.RegisterUserCommand;
import user.domain.User;

public interface RegisterUserUseCase {

    User registerUser(RegisterUserCommand command);

}
