package user.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import user.adapter.in.request.LoginUserRequest;
import user.application.port.in.query.CheckUsernameQuery;
import user.application.port.in.usecase.LoginUserUseCase;
import user.domain.User;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class LoginUserService implements LoginUserUseCase {

    private final CheckUsernameQuery checkUsernameQuery;

    @Override
    @Transactional
    public User loginUser(LoginUserRequest loginUserRequest) {
        User findUser = checkUsernameQuery.checkUsername(loginUserRequest.getUsername());

        if (loginUserRequest.getPassword() == findUser.getPassword()) {
            log.info("로그인한 아이디 : ", findUser.getUsername());
            return findUser;
        }

        else throw new EntityNotFoundException("일치하는 정보가 없습니다.");
    }

}
