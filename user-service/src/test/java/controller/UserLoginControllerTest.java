package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import user.adapter.in.request.LoginUserRequest;
import user.adapter.out.persistence.UserJpaEntity;
import user.adapter.out.persistence.UserJpaRepo;
import user.domain.constant.Role;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserLoginControllerTest {

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

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginUserTest() throws Exception {
        // Given
        LoginUserRequest request = new LoginUserRequest(
                "test111",
                "비밀번호486!"
        );

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user-service/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

}
