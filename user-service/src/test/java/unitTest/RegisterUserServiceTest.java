package unitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.out.SaveUserPort;
import user.application.service.RegisterUserService;
import user.domain.User;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RegisterUserService 단위 테스트")
public class RegisterUserServiceTest {

    private RegisterUserService registerUserService;
    private SaveUserPort saveUserPort;

    @BeforeEach
    void setUp() {
        saveUserPort = new SaveUserPortFake();
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

        assertEquals("testuser", user.getUsername());
        assertTrue(((SaveUserPortFake) saveUserPort).isUserSaved("testuser"));
    }

    @Test
    @DisplayName("유저 등록 실패 - 비밀번호 불일치")
    void testRegisterUserPasswordMismatch() {
        RegisterUserCommand command = RegisterUserCommand.builder()
                .username("testuser")
                .password("testpassword")
                .confirmPassword("wrongpassword")
                .build();

        assertThrows(RuntimeException.class, () -> registerUserService.registerUser(command));
    }

    private static class SaveUserPortFake implements SaveUserPort {
        private Map<String, User> userStorage = new HashMap<>();

        @Override
        public void saveUser(User user) {
            userStorage.put(user.getUsername(), user);
        }

        public boolean isUserSaved(String username) {
            return userStorage.containsKey(username);
        }
    }
}