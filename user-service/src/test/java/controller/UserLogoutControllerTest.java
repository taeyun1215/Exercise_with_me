package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import user.adapter.out.persistence.UserJpaEntity;
import user.adapter.out.persistence.UserJpaRepo;
import user.domain.constant.Role;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserLogoutControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserJpaRepo userJpaRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        UserJpaEntity user = UserJpaEntity.builder()
                .username("test111")
                .password(passwordEncoder.encode("비밀번호486!"))
                .nickname("test")
                .phone("010-2415-6806")
                .email("test@naver.com")
                .role(Role.USER)
                .build();

        userJpaRepo.save(user);
    }

    @AfterEach
    public void clear() {
        // 테스트용 게시글 데이터 삭제
        userJpaRepo.deleteAll();
    }

}
