package dev.ewm.domain.user;

import dev.ewm.domain.jwt.JwtUtil;
import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final HttpServletResponse response;

    @Override
    @Transactional
    public User registerUser(UserRegisterRequest userRegisterRequest) {
        if (!Objects.equals(userRegisterRequest.getPassword(), userRegisterRequest.getConfirmPassword())) {
            throw new RuntimeException("두개의 비밀번호가 맞지 않습니다.");
        }

        User user = userRegisterRequest.toEntity(passwordEncoder);
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

    @Override
    @Transactional
    public User loginUser(UserLoginRequest userLoginRequest) {
        User user = userRepo.findByUsername(userLoginRequest.getUsername())
        		.orElseThrow(() -> new EntityNotFoundException("일치하는 아이디가 없습니다."));
        
        if(user!=null) {
        	if(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
//        		log.info("login: {} {} {}", user.getNickname(), user.getPhone(), user.getUsername());
//        		jwtService.createToken(userLoginRequest);
//        		StringBuilder sb = new StringBuilder("ewm ");
//        		String token = jwtUtil.createToken(userLoginRequest);
//        		log.info("jwt: {}", token);
//        		response.addHeader("Authorization", sb.append(token).toString());
        		StringBuilder sb = new StringBuilder("ewm ").append(jwtUtil.createToken(userLoginRequest));
        		
        		response.addHeader("Authorization", sb.toString());
//        		response.addHeader("Authorization", new StringBuilder("ewm ").append(jwtUtil.createToken(userLoginRequest)).toString());
        		log.info("jwt: {}", "1");
        		
        		
        	} else {
        		throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        	}
        }

        return user;
    }

}
