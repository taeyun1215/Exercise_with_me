package unitTest.mockist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.adapter.in.request.LoginUserRequest;
import user.application.port.in.query.CheckUsernameQuery;
import user.application.service.LoginUserService;
import user.domain.User;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("LoginUserService 단위 테스트")
public class LoginUserServiceTest {

    @Mock
    private CheckUsernameQuery checkUsernameQuery;

    @InjectMocks
    private LoginUserService loginUserService;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = User.builder()
                .username("testUsername")
                .password("testPassword")
                .build();
    }

    @Test
    @DisplayName("로그인 요청에 대한 성공 응답 반환")
    public void shouldLoginSuccessfullyWhenPasswordMatches() {
        // given
        LoginUserRequest loginUserRequest = new LoginUserRequest("testUsername", "testPassword");
        when(checkUsernameQuery.checkUsername("testUsername")).thenReturn(mockUser);

        // when
        User result = loginUserService.loginUser(loginUserRequest);

        // then
        verify(checkUsernameQuery, times(1)).checkUsername("testUsername");
        assertEquals(mockUser, result);
    }

    @Test
    @DisplayName("로그인 요청에 대한 실패 응답 반환")
    public void shouldThrowExceptionWhenPasswordDoesNotMatch() {
        // given
        LoginUserRequest loginUserRequest = new LoginUserRequest("testUsername", "wrongPassword");
        when(checkUsernameQuery.checkUsername("testUsername")).thenReturn(mockUser);

        // when
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            loginUserService.loginUser(loginUserRequest);
        });

        // then
        assertEquals("일치하는 정보가 없습니다.", exception.getMessage());
    }
}
