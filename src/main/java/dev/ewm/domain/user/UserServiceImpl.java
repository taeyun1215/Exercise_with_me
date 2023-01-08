package dev.ewm.domain.user;

import dev.ewm.domain.user.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    @Transactional
    public User registerUser(UserRegisterRequest userRegisterRequest) {
        User user = userRegisterRequest.toEntity();
        userRepo.save(user);
        return user;
    }

    @Override
    @Transactional
    public boolean checkUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    @Transactional
    public boolean checkNickname(String nickname) {
        return userRepo.existsByNickname(nickname);
    }

}
