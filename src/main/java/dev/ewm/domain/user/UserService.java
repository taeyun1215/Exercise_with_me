package dev.ewm.domain.user;

import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface UserService {

    // DB 유저 정보 저장
    User registerUser(UserRegisterRequest userRegisterRequest);

    // 아이디 중복 체크
    User checkUsername(String username);

    // 닉네임 중복 체크
    boolean checkNickname(String nickname);

    // 유저 로그인
    User loginUser(UserLoginRequest userLoginRequest);

}