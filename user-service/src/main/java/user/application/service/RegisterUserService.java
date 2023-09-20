package user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.in.usecase.RegisterUserUseCase;
import user.application.port.out.LoadUserPort;
import user.application.port.out.SaveUserPort;
import user.domain.User;
import global.annotation.UseCase;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;

    @Override
    @Transactional
    public User registerUser(RegisterUserCommand registerUserCommand) {
        if (!Objects.equals(registerUserCommand.getPassword(), registerUserCommand.getConfirmPassword())) {
            throw new RuntimeException("두개의 비밀번호가 맞지 않습니다.");
        }

        if (loadUserPort.findByUsername(registerUserCommand.getUsername()) != null) {
            throw new RuntimeException("사용자 이름이 이미 존재합니다.");
        }

        if (loadUserPort.findByNickname(registerUserCommand.getNickname()) != null) {
            throw new RuntimeException("닉네임이 이미 존재합니다.");
        }

        User saveUser = registerUserCommand.toEntity();
        saveUserPort.saveUser(saveUser);

        return saveUser;
    }

}
