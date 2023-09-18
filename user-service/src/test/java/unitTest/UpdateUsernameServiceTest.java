package unitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.application.port.out.UpdateUserStatePort;
import user.application.service.UpdateUsernameService;
import user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("UpdateUsernameService 단위 테스트")
public class UpdateUsernameServiceTest {

    private UpdateUsernameService updateUsernameService;
    private UpdateUserStatePortFake updateUserStatePortFake;

    @BeforeEach
    void setUp() {
        updateUserStatePortFake = new UpdateUserStatePortFake();
        updateUsernameService = new UpdateUsernameService(updateUserStatePortFake);
    }

    @Test
    @DisplayName("유저네임 업데이트 요청에 대한 성공 응답 반환")
    void testUpdateUsernameSuccess() {
        User user = User.builder()
                .userId(1L)
                .username("oldUsername")
                .password("password")
                .email("email@example.com")
                .nickname("nickname")
                .phone("phone")
                .build();

        String newUsername = "newUsername";

        updateUsernameService.updateUsername(user, newUsername);

        assertEquals(newUsername, updateUserStatePortFake.getUpdatedUsername());
    }

    private static class UpdateUserStatePortFake implements UpdateUserStatePort {
        private String updatedUsername;

        @Override
        public void updateUsername(User user, String newUsername) {
            user.updateUsername(newUsername);
            this.updatedUsername = newUsername;
        }

        public String getUpdatedUsername() {
            return updatedUsername;
        }
    }
}