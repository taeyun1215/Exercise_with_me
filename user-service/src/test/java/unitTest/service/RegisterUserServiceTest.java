package unitTest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.out.LoadUserPort;
import user.application.port.out.SaveUserPort;
import user.application.service.RegisterUserService;
import user.domain.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RegisterUserService 단위 테스트")
public class RegisterUserServiceTest {

    @Mock
    private SaveUserPort saveUserPort;

    @Mock
    private LoadUserPort loadUserPort;

    @InjectMocks
    private RegisterUserService registerUserService;

    private RegisterUserCommand registerUserCommand;

    @BeforeEach
    public void setUp() {
        registerUserCommand = RegisterUserCommand.builder()
                .username("testUsername")
                .password("testPassword")
                .confirmPassword("testPassword")
                .nickname("testNickname")
                .phone("1234567890")
                .email("test@test.com")
                .build();
    }

    @Test
    @DisplayName("회원가입 요청에 대한 성공 응답 반환")
    public void shouldRegisterUserSuccessfullyWhenPasswordsMatch() {
        // given
        User expectedUser = registerUserCommand.toEntity();
        doNothing().when(saveUserPort).saveUser(any(User.class));
        when(loadUserPort.findByUsername(anyString())).thenReturn(null);
        when(loadUserPort.findByNickname(anyString())).thenReturn(null );

        // when
        User actualUser = registerUserService.registerUser(registerUserCommand);

        // then
        verify(saveUserPort, times(1)).saveUser(any(User.class));
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getNickname(), actualUser.getNickname());
        assertEquals(expectedUser.getPhone(), actualUser.getPhone());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getRole(), actualUser.getRole());
    }

    @Test
    @DisplayName("회원가입 요청에 비밀번호가 일치하지 않는 경우 실패 응답 반환")
    public void shouldThrowExceptionWhenPasswordsDoNotMatch() {
        // given
        registerUserCommand = registerUserCommand.builder()
                .password("testPassword")
                .confirmPassword("wrongPassword")
                .build();

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> {
            registerUserService.registerUser(registerUserCommand);
        });

        // then
        assertEquals("두 개의 비밀번호가 일치하지 않습니다.", exception.getMessage());
        verify(saveUserPort, never()).saveUser(any(User.class));
        assertTrue(exception instanceof RuntimeException);
    }
}
