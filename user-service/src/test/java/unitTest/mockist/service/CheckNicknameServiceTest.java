package unitTest.mockist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.application.port.out.LoadUserPort;
import user.application.service.CheckNicknameService;
import user.domain.User;
import user.domain.constant.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CheckNicknameService 단위 테스트")
public class CheckNicknameServiceTest {

    @Mock
    private LoadUserPort loadUserPort;

    @InjectMocks
    private CheckNicknameService checkNicknameService;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = User.builder()
                .userId(1L)
                .username("testuser")
                .password("test111!!!")
                .nickname("testNickname")
                .phone("010-1234-5678")
                .email("testuser@example.com")
                .role(Role.USER)
                .build();
    }

    @Test
    @DisplayName("중복 닉네임 확인 요청에 대한 성공 응답 반환")
    public void shouldReturnUserWhenNicknameExists() {
        // given
        String nickname = "testNickname";
        when(loadUserPort.findByNickname(nickname)).thenReturn(mockUser);

        // when
        User result = checkNicknameService.checkNickname(nickname);

        // then
        verify(loadUserPort, times(1)).findByNickname(nickname);
        assertEquals(mockUser, result);
    }

    @Test
    @DisplayName("중복 닉네임 확인 요청에 대한 실패 응답 반환")
    public void shouldReturnNullWhenNicknameDoesNotExist() {
        // given
        String nickname = "nonExistentNickname";
        when(loadUserPort.findByNickname(nickname)).thenReturn(null);

        // when
        User result = checkNicknameService.checkNickname(nickname);

        // then
        verify(loadUserPort, times(1)).findByNickname(nickname);
        assertNull(result);
    }
}