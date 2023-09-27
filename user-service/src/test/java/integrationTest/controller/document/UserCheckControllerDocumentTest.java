package integrationTest.controller.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import user.adapter.out.persistence.UserJpaEntity;
import user.adapter.out.persistence.UserJpaRepo;
import user.domain.constant.Role;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("usernmae, nickname 중복 체크 API 명세서")
public class UserCheckControllerDocumentTest extends ControllerTest {

    @Autowired
    private UserJpaRepo userJpaRepo;

    @BeforeEach
    public void setup() {
        UserJpaEntity userEntity = new UserJpaEntity();
        userEntity.setUsername("username");
        userEntity.setPassword("Test@1234");
        userEntity.setNickname("nickname");
        userEntity.setPhone("010-1111-2222");
        userEntity.setEmail("email1@example.com");
        userEntity.setRole(Role.USER);

        userJpaRepo.save(userEntity);
    }

    @Test
    @DisplayName("username 중복 체크 API 성공 시나리오 문서화")
    public void testCheckUsernameAvailable() throws Exception {
        String availableUsername = "existUsername";

        mockMvc.perform(MockMvcRequestBuilders.get("/users/username/" + availableUsername + "/exists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document("user/check-username-available",
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("Operation successful or not"),
                                PayloadDocumentation.fieldWithPath("data").description("Checked username"),
                                PayloadDocumentation.fieldWithPath("errorCode").description("Error code if any").optional()
                        )));
    }

    @Test
    @DisplayName("username 중복 체크 API 실패 시나리오 문서화")
    public void testCheckUsernameTaken() throws Exception {
        String takenUsername = "username"; // 중복 사용자 이름

        mockMvc.perform(MockMvcRequestBuilders.get("/users/username/" + takenUsername + "/exists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document("user/check-username-taken",
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("Operation successful or not"),
                                PayloadDocumentation.fieldWithPath("data").description("Checked username").optional(),
                                PayloadDocumentation.fieldWithPath("errorCode").description("Error code")
                        )));
    }

    @Test
    @DisplayName("nickname 중복 체크 API 성공 시나리오 문서화")
    public void testCheckNicknameAvailable() throws Exception {
        String availableNickname = "existNickname";

        mockMvc.perform(MockMvcRequestBuilders.get("/users/nickname/" + availableNickname + "/exists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document("user/check-nickname-available",
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("Operation successful or not"),
                                PayloadDocumentation.fieldWithPath("data").description("Checked nickname"),
                                PayloadDocumentation.fieldWithPath("errorCode").description("Error code if any").optional()
                        )));
    }

    @Test
    @DisplayName("nickname 중복 체크 API 실패 시나리오 문서화")
    public void testCheckNicknameTaken() throws Exception {
        String takenNickname = "nickname"; // 중복 닉네임

        mockMvc.perform(MockMvcRequestBuilders.get("/users/nickname/" + takenNickname + "/exists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document("user/check-nickname-taken",
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("Operation successful or not"),
                                PayloadDocumentation.fieldWithPath("data").description("Checked nickname").optional(),
                                PayloadDocumentation.fieldWithPath("errorCode").description("Error code")
                        )));
    }
}