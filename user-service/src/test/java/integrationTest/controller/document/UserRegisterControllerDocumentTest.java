package integrationTest.controller.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import user.adapter.in.request.RegisterUserRequest;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원 가입 API 명세서")
public class UserRegisterControllerDocumentTest extends ControllerTest {

    @Test
    @DisplayName("회원 가입 API 성공 시나리오 문서화")
    public void testRegisterUser() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest(
                "testuser111",
                "test111!!!",
                "test111!!!",
                "testNickname",
                "010-1234-5678",
                "testuser@example.com"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document("user/register-user",
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("username").description("User's username"),
                                PayloadDocumentation.fieldWithPath("password").description("User's password"),
                                PayloadDocumentation.fieldWithPath("confirmPassword").description("User's confirmed password"),
                                PayloadDocumentation.fieldWithPath("nickname").description("User's nickname"),
                                PayloadDocumentation.fieldWithPath("phone").description("User's phone number"),
                                PayloadDocumentation.fieldWithPath("email").description("User's email address")
                        )));
    }
}