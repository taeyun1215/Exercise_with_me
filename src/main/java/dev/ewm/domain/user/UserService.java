package dev.ewm.domain.user;

import dev.ewm.domain.user.dto.UserLoginDto;
import dev.ewm.domain.user.dto.UserRegisterDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface UserService {

    // DB 유저 정보 저장
    User registerUser(UserRegisterDto userRegisterDto);

    // 유저 로그인
    User loginUser(UserLoginDto userLoginDto);

}