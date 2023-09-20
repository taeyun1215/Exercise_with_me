package integrationTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import user.UserServiceApplication;
import user.adapter.in.request.RegisterUserRequest;
import user.adapter.out.persistence.UserJpaEntity;
import user.adapter.out.persistence.UserJpaRepo;
import user.domain.constant.Role;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = UserServiceApplication.class)
@DisplayName("UserRegisterControllerTest 통합 테스트")
public class UserRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private DataSource dataSource;

    @Test
    public void checkDatabaseType() throws SQLException {
        System.out.println(dataSource.getConnection().getMetaData().getDatabaseProductName());
    }

    @BeforeEach
    public void setup() {
        UserJpaEntity userEntity1 = new UserJpaEntity();
        userEntity1.setUsername("username");
        userEntity1.setPassword("Test@1234");
        userEntity1.setNickname("uniqueNickname");
        userEntity1.setPhone("010-1234-5670");
        userEntity1.setEmail("uniqueEmail@example.com");
        userEntity1.setRole(Role.USER);

        UserJpaEntity userEntity2 = new UserJpaEntity();
        userEntity2.setUsername("uniqueUsername");
        userEntity2.setPassword("Test@1234");
        userEntity2.setNickname("nickname");
        userEntity2.setPhone("010-1234-5671");
        userEntity2.setEmail("uniqueEmail2@example.com");
        userEntity2.setRole(Role.USER);

        userJpaRepo.save(userEntity1);
        userJpaRepo.save(userEntity2);
    }

    @Test
    @DisplayName("회원가입 요청에 대한 성공 응답 반환")
    public void registerUserTest() throws Exception {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "testuser01",
                "Test@1234",
                "Test@1234",
                "testnickname",
                "010-1234-5678",
                "testuser01@example.com"
        );

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("회원가입 요청에 사용자 이름이 중복될 경우 실패 응답 반환")
    public void registerUserTest_SamePassword() throws Exception {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "testusername",
                "password@1",
                "password@2",
                "testnickname",
                "010-1234-5678",
                "testuser01@example.com"
        );

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원가입 요청에 사용자 이름이 중복될 경우 실패 응답 반환")
    public void registerUserTest_UsernameDuplicate() throws Exception {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "username", // 중복 사용자 이름
                "Test@1234",
                "Test@1234",
                "testnickname",
                "010-1234-5678",
                "testuser01@example.com"
        );

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("회원가입 요청에 닉네임이 중복될 경우 실패 응답 반환")
    public void registerUserTest_NicknameDuplicate() throws Exception {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "testuser02",
                "Test@1234",
                "Test@1234",
                "nickname", // 중복 닉네임
                "010-1234-5678",
                "testuser02@example.com"
        );

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

}
