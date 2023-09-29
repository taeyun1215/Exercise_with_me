package integrationTest.controller.document;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import user.adapter.in.request.RegisterUserRequest;

import javax.ws.rs.core.MediaType;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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

        mockMvc.perform(RestDocumentationRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentationWrapper.document("user/register-user",
                        requestFields(
                                PayloadDocumentation.fieldWithPath("username").type(JsonFieldType.STRING).description("사용자 아이디"),
                                PayloadDocumentation.fieldWithPath("password").type(JsonFieldType.STRING).description("사용자 비밀번호"),
                                PayloadDocumentation.fieldWithPath("confirmPassword").type(JsonFieldType.STRING).description("비밀번호 확인"),
                                PayloadDocumentation.fieldWithPath("nickname").type(JsonFieldType.STRING).description("사용자 닉네임"),
                                PayloadDocumentation.fieldWithPath("phone").type(JsonFieldType.STRING).description("사용자 전화번호"),
                                PayloadDocumentation.fieldWithPath("email").type(JsonFieldType.STRING).description("사용자 이메일 주소")
                        ),
                        responseFields(
                                PayloadDocumentation.fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("작업 성공 여부"),
                                PayloadDocumentation.fieldWithPath("data").description("등록된 사용자 정보"),
                                PayloadDocumentation.fieldWithPath("data.username").type(JsonFieldType.STRING).description("사용자 아이디"),
                                PayloadDocumentation.fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("사용자 닉네임"),
                                PayloadDocumentation.fieldWithPath("data.phone").type(JsonFieldType.STRING).description("사용자 전화번호"),
                                PayloadDocumentation.fieldWithPath("data.email").type(JsonFieldType.STRING).description("사용자 이메일 주소"),
                                PayloadDocumentation.fieldWithPath("data.role").type(JsonFieldType.STRING).description("사용자 역할"),
                                PayloadDocumentation.fieldWithPath("errorCode").type(JsonFieldType.STRING).description("에러 코드 (있는 경우)").optional()
                        )
                ));
    }
}
