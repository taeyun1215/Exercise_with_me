package unitTest.classicist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.out.LoadUserPort;
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
    private LoadUserPort loadUserPort;

    @BeforeEach
    void setUp() {
        saveUserPort = new SaveUserPortFake();
        loadUserPort = new LoadUserPortFake();

        User fakeUser = User.builder()
                .username("existingUser")
                .nickname("existingNickname")
                .build();
        ((LoadUserPortFake) loadUserPort).addFakeUser(fakeUser);

        registerUserService = new RegisterUserService(saveUserPort, loadUserPort);
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
        assertTrue(((SaveUserPortFake) saveUserPort).isUserSaved(user.getUserId()));
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

    @Test
    @DisplayName("유저 등록 실패 - 사용자 이름 중복")
    void testRegisterUserDuplicateUsername() {
        RegisterUserCommand command = RegisterUserCommand.builder()
                .username("existingUser")  // 중복 사용자 이름
                .password("testpassword")
                .confirmPassword("testpassword")
                .nickname("newNickname")
                .build();

        assertThrows(RuntimeException.class, () -> registerUserService.registerUser(command));
    }

    @Test
    @DisplayName("유저 등록 실패 - 닉네임 중복")
    void testRegisterUserDuplicateNickname() {
        RegisterUserCommand command = RegisterUserCommand.builder()
                .username("newUser")
                .password("testpassword")
                .confirmPassword("testpassword")
                .nickname("existingNickname")  // 중복 닉네임
                .build();

        assertThrows(RuntimeException.class, () -> registerUserService.registerUser(command));
    }

    private static class SaveUserPortFake implements SaveUserPort {
        private Map<Long, User> userStorage = new HashMap<>();
        private Long idCounter = 1L;

        @Override
        public void saveUser(User user) {
            user.updateUserId(idCounter);
            userStorage.put(idCounter, user);
            idCounter++;
        }

        public boolean isUserSaved(Long userId) {
            return userStorage.containsKey(userId);
        }
    }

    private static class LoadUserPortFake implements LoadUserPort {

        private Map<String, User> usernameStorage = new HashMap<>();
        private Map<String, User> nicknameStorage = new HashMap<>();

        public void addFakeUser(User user) {
            usernameStorage.put(user.getUsername(), user);
            nicknameStorage.put(user.getNickname(), user);
        }

        @Override
        public User findByUsername(String username) {
            return usernameStorage.get(username);
        }

        @Override
        public User findByNickname(String nickname) {
            return nicknameStorage.get(nickname);
        }
    }
}