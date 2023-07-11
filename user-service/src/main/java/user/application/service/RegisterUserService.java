package user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.in.usecase.RegisterUserUseCase;
import user.application.port.out.SaveUserPort;
import user.domain.User;
import user.global.annotation.UseCase;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;

    @Override
    @Transactional
    public User registerUser(RegisterUserCommand registerUserCommand) {
        if (!Objects.equals(registerUserCommand.getPassword(), registerUserCommand.getConfirmPassword())) {
            throw new RuntimeException("두개의 비밀번호가 맞지 않습니다.");
        }

        User saveUser = registerUserCommand.toEntity();
        saveUserPort.saveUser(saveUser);

        return saveUser;
    }

}
