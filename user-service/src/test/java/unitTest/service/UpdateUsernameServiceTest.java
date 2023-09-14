package unitTest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.application.service.UpdateUsernameService;
import user.application.port.out.UpdateUserStatePort;
import user.domain.User;
import user.domain.constant.Role;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("UpdateUsernameService 단위 테스트")
public class UpdateUsernameServiceTest {

    @Mock
    private UpdateUserStatePort updateUserStatePort;

    @InjectMocks
    private UpdateUsernameService updateUsernameService;

    private User mockUser;
    private String newUsername;

    @BeforeEach
    public void setUp() {
        // User 객체 생성
        mockUser = User.builder()
                .userId(1L)
                .username("testuser")
                .password("test111!!!")
                .nickname("testNickname")
                .phone("010-1234-5678")
                .email("testuser@example.com")
                .role(Role.USER)
                .build();

        newUsername = "newUsername";
    }

    @Test
    @DisplayName("유저 이름 업데이트 요청에 대한 성공 응답 반환")
    public void shouldCallUpdateUsernameMethodOfUpdateUserStatePort() {
        // when
        updateUsernameService.updateUsername(mockUser, newUsername);

        // then
        verify(updateUserStatePort, times(1)).updateUsername(mockUser, newUsername);
    }
}
