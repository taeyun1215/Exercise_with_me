//package controller;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import user.adapter.out.persistence.UserJpaEntity;
//import user.adapter.out.persistence.UserJpaRepo;
//import user.domain.constant.Role;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class UserCheckControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    UserJpaRepo userJpaRepo;
//
//    @BeforeEach
//    public void init() {
//        UserJpaEntity user = UserJpaEntity.builder()
//                .username("test111")
//                .password("비밀번호486!")
//                .nickname("test")
//                .phone("010-2415-6806")
//                .email("test@naver.com")
//                .role(Role.USER)
//                .build();
//
//        userJpaRepo.save(user);
//    }
//
//    @AfterEach
//    public void clear() {
//        // 테스트용 게시글 데이터 삭제
//        userJpaRepo.deleteAll();
//    }
//
//    @Test
//    @DisplayName("nickname 중복 테스트")
//    public void checkNicknameTest() throws Exception {
//        // Given
//        String nickname = "운동하자";
//
//        // When
//        final ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/user-service/users/nickname/"+nickname+"/exists")
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        // then
//        resultActions
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("username 중복 테스트")
//    public void checkUsernameTest() throws Exception {
//        // Given
//        String username = "taeyun1215";
//
//        // When
//        final ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/user-service/users/username/"+username+"/exists")
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        // then
//        resultActions
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Nested
//    @DisplayName("실패 테스트")
//    class FailCases {
//        @Nested
//        @DisplayName("nickname")
//        class nickname {
//            @Test
//            @DisplayName("null")
//            void failUsername() throws Exception {
//                // Given
//                String nickname = null;
//
//                // When
//                final ResultActions resultActions = mockMvc.perform(
//                        MockMvcRequestBuilders.get("/user-service/users/nickname/"+nickname+"/exists")
//                                .contentType(MediaType.APPLICATION_JSON)
//                );
//
//                // then
//                resultActions
//                        .andDo(print())
//                        .andExpect(status().isBadRequest());
//            }
//        }
//
//        @Nested
//        @DisplayName("username")
//        class username {
//            @Test
//            @DisplayName("null")
//            void failUsername() throws Exception {
//                // Given
//                String username = null;
//
//                // When
//                final ResultActions resultActions = mockMvc.perform(
//                        MockMvcRequestBuilders.get("/user-service/users/username/"+username+"/exists")
//                                .contentType(MediaType.APPLICATION_JSON)
//                );
//
//                // then
//                resultActions
//                        .andDo(print())
//                        .andExpect(status().isBadRequest());
//            }
//        }
//    }
//}
