package integrationTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import user.UserServiceApplication;
import user.adapter.in.request.RegisterUserRequest;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@AutoConfigureMockMvc
//@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
//        "spring.profiles.active=test"
//})
@ActiveProfiles("test")
@SpringBootTest(classes = UserServiceApplication.class)
@DisplayName("UserRegisterControllerTest 통합 테스트")
public class UserRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    public void yourTestMethod() throws SQLException {
        System.out.println(dataSource.getConnection().getMetaData().getURL());
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
}
