package dev.ewm.domain.user;

import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(UserRegisterRequest userRegisterRequest) {
        if (!Objects.equals(userRegisterRequest.getPassword(), userRegisterRequest.getConfirmPassword())) {
            throw new RuntimeException("두개의 비밀번호가 맞지 않습니다.");
        }

        User user = userRegisterRequest.toEntity(passwordEncoder);
        userRepo.save(user);

        log.info("회원가입한 아이디 : ", user.getUsername());
        return user;
    }

    @Override
    @Transactional
    public User checkUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    @Transactional
    public User checkNickname(String nickname) {
        return userRepo.findByNickname(nickname);
    }

    @Override
    @Transactional
    public User loginUser(UserLoginRequest userLoginRequest) {
        User findUser = userRepo.findByUsername(userLoginRequest.getUsername());

        if (passwordEncoder.matches(userLoginRequest.getPassword(), findUser.getPassword())) {
            log.info("로그인한 아이디 : ", findUser.getUsername());
            return findUser;
        }
        else throw new EntityNotFoundException("일치하는 정보가 없습니다.");

    }

}
