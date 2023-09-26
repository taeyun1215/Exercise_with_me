package unitTest.classicist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.application.port.in.query.CheckUsernameQuery;
import user.application.port.in.usecase.LoginUserUseCase;
import user.application.service.LoginUserService;
import user.adapter.in.request.LoginUserRequest;
import user.domain.User;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("LoginUserService 단위 테스트")
public class LoginUserServiceTest {

    private LoginUserService loginUserService;

    @BeforeEach
    void setUp() {
        CheckUsernameQuery checkUsernameQuery = new CheckUsernameQueryFake();
        loginUserService = new LoginUserService(checkUsernameQuery);
    }

    @Test
    @DisplayName("로그인 요청에 대한 성공 응답 반환")
    void testLoginUserSuccess() {
        LoginUserRequest loginUserRequest = new LoginUserRequest("existingUsername", "testpassword");
        User user = loginUserService.loginUser(loginUserRequest);
        assertEquals("existingUsername", user.getUsername());
    }

    @Test
    @DisplayName("로그인 청에 대한 실패 응답 반환")
    void testLoginUserFailurePasswordMismatch() {
        LoginUserRequest loginUserRequest = new LoginUserRequest("existingUsername", "wrongpassword");
        assertThrows(EntityNotFoundException.class, () -> loginUserService.loginUser(loginUserRequest));
    }

    private static class CheckUsernameQueryFake implements CheckUsernameQuery {

        @Override
        public User checkUsername(String username) {
            if ("existingUsername".equals(username)) {
                return User.builder()
                        .userId(1L)
                        .username("existingUsername")
                        .password("testpassword")
                        .nickname("testNickname")
                        .phone("123456789")
                        .email("test@test.com")
                        .build();
            }
            return null;
        }
    }
}
