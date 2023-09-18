package unitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.out.SaveUserPort;
import user.application.service.RegisterUserService;
import user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("RegisterUserService 단위 테스트")
public class RegisterUserServiceTest {

    private RegisterUserService registerUserService;
    private SaveUserPort saveUserPort;

    @BeforeEach
    void setUp() {
        saveUserPort = mock(SaveUserPort.class);
        registerUserService = new RegisterUserService(saveUserPort);
    }

    @Test
    @DisplayName("유저 등록 성공")
    void testRegisterUserSuccess() {
        RegisterUserCommand command = RegisterUserCommand.builder()
                .username("testuser")
                .password("testpassword")
                .confirmPassword("testpassword")
                .build();

        User user = registerUserService.registerUser(command);

        verify(saveUserPort).saveUser(any(User.class));
        assertEquals("testuser", user.getUsername());
    }

    @Test
    @DisplayName("유저 등록 실패 - 비밀번호 불일치")
    void testRegisterUserPasswordMismatch() {
        // Given
        RegisterUserCommand command = RegisterUserCommand.builder()
                .username("testuser")
                .password("testpassword")
                .confirmPassword("wrongpassword")
                .build();

        // When & Then
        assertThrows(RuntimeException.class, () -> registerUserService.registerUser(command));
    }
}
