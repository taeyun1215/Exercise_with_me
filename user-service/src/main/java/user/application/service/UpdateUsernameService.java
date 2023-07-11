package user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import user.application.port.in.usecase.UpdateUsernameUseCase;
import user.application.port.out.UpdateUserStatePort;
import user.domain.User;
import user.global.annotation.UseCase;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateUsernameService implements UpdateUsernameUseCase {

    private final UpdateUserStatePort updateUserStatePort;

    @Override
    public void updateUsername(User user, String username) {
        updateUserStatePort.updateUsername(user, username);
    }
}
