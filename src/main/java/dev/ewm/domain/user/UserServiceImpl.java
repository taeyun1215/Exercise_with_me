package dev.ewm.domain.user;

import dev.ewm.domain.user.dto.UserLoginDto;
import dev.ewm.domain.user.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    // DB 유저 정보 저장
    @Override
    @Transactional
    public User registerUser(UserRegisterDto userRegisterDto) {

    }

    // 유저 로그인
    @Override
    @Transactional
    public User loginUser(UserLoginDto userLoginDto) {

    }


}
