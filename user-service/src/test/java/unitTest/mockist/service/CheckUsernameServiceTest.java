package unitTest.mockist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.application.port.out.LoadUserPort;
import user.application.service.CheckUsernameService;
import user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CheckUsernameService 단위 테스트")
public class CheckUsernameServiceTest {

    @Mock
    private LoadUserPort loadUserPort;

    @InjectMocks
    private CheckUsernameService checkUsernameService;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = User.builder()
                .username("testUsername")
                // 여기에 다른 필드들도 초기화 할 수 있습니다.
                .build();
    }

    @Test
    @DisplayName("중복 아이디 확인 요청에 대한 성공 응답 반환")
    public void shouldReturnUserWhenUsernameExists() {
        // given
        String username = "testUsername";
        when(loadUserPort.findByUsername(username)).thenReturn(mockUser);

        // when
        User result = checkUsernameService.checkUsername(username);

        // then
        verify(loadUserPort, times(1)).findByUsername(username);
        assertEquals(mockUser, result);
    }

    @Test
    @DisplayName("중복 아이디 확인 요청에 대한 실패 응답 반환")
    public void shouldReturnNullWhenUsernameDoesNotExist() {
        // given
        String username = "nonExistentUsername";
        when(loadUserPort.findByUsername(username)).thenReturn(null);

        // when
        User result = checkUsernameService.checkUsername(username);

        // then
        verify(loadUserPort, times(1)).findByUsername(username);
        assertNull(result);
    }
}