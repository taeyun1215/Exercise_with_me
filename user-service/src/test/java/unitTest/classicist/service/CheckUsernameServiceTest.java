package unitTest.classicist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.application.port.out.LoadUserPort;
import user.application.service.CheckUsernameService;
import user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("CheckUsernameService 단위 테스트")
public class CheckUsernameServiceTest {

    private CheckUsernameService checkUsernameService;

    @BeforeEach
    void setUp() {
        LoadUserPort loadUserPort = new LoadUserPortFake();
        checkUsernameService = new CheckUsernameService(loadUserPort);
    }

    @Test
    @DisplayName("중복 사용자 이름 확인 요청에 대한 성공 응답 반환")
    void testCheckUsernameExistingUser() {
        User user = checkUsernameService.checkUsername("existingUsername");
        assertEquals("existingUsername", user.getUsername());
    }

    @Test
    @DisplayName("중복 사용자 이름 확인 요청에 대한 실패 응답 반환")
    void testCheckUsernameNonExistingUser() {
        User user = checkUsernameService.checkUsername("nonExistingUsername");
        assertNull(user);
    }

    private static class LoadUserPortFake implements LoadUserPort {

        @Override
        public User findByUsername(String username) {
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

        @Override
        public User findByNickname(String nickname) {
            return null; // 본 테스트에서는 사용되지 않음
        }
    }
}
