package user.application.service;

import lombok.RequiredArgsConstructor;
import user.application.port.in.query.CheckUsernameQuery;
import user.application.port.out.LoadUserPort;
import user.domain.User;
import user.global.annotation.UseCase;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CheckUsernameService implements CheckUsernameQuery {

    private final LoadUserPort loadUserPort;

    @Override
    @Transactional
    public User checkUsername(String username) {
        return loadUserPort.findByUsername(username);
    }

}
