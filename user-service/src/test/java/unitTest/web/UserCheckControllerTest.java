package unitTest.web;

import global.error.ErrorCode;
import global.utils.ReturnObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.adapter.in.web.UserCheckController;
import user.application.port.in.query.CheckNicknameQuery;
import user.application.port.in.query.CheckUsernameQuery;
import user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserCheckController 단위 테스트")
public class UserCheckControllerTest {

    @Mock
    private CheckUsernameQuery checkUsernameQuery;

    @Mock
    private CheckNicknameQuery checkNicknameQuery;

    @InjectMocks
    private UserCheckController userCheckController;

    private String testUsername = "testuser";
    private String testNickname = "testNickname";
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = User.builder()
                .userId(1L)
                .username(testUsername)
                .nickname(testNickname)
                .build();
    }

    @Test
    @DisplayName("유저 이름이 존재하지 않을 때 성공 응답 반환")
    public void shouldReturnSuccessWhenUsernameDoesNotExist() {
        // given
        when(checkUsernameQuery.checkUsername(testUsername)).thenReturn(null);

        // when
        ResponseEntity<ReturnObject> response = userCheckController.checkUsername(testUsername);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUsername, response.getBody().getData());
        verify(checkUsernameQuery, times(1)).checkUsername(testUsername);
    }

//    @Test
//    @DisplayName("유저 이름이 이미 존재할 때 실패 응답 반환")
//    public void shouldReturnFailureWhenUsernameExists() {
//        // given
//        when(checkUsernameQuery.checkUsername(testUsername)).thenReturn(mockUser);
//
//        // when
//        ResponseEntity<ReturnObject> response = userCheckController.checkUsername(testUsername);
//
//        // then
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(ErrorCode.ALREADY_REGISTERED_MEMBER, response.getBody().getErrorCode());
//        verify(checkUsernameQuery, times(1)).checkUsername(testUsername);
//    }

    @Test
    @DisplayName("닉네임이 존재하지 않을 때 성공 응답 반환")
    public void shouldReturnSuccessWhenNicknameDoesNotExist() {
        // given
        when(checkNicknameQuery.checkNickname(testNickname)).thenReturn(null);

        // when
        ResponseEntity<ReturnObject> response = userCheckController.checkNickname(testNickname);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testNickname, response.getBody().getData());
        verify(checkNicknameQuery, times(1)).checkNickname(testNickname);
    }

    @Test
    @DisplayName("닉네임이 이미 존재할 때 실패 응답 반환")
    public void shouldReturnFailureWhenNicknameExists() {
        // given
        when(checkNicknameQuery.checkNickname(testNickname)).thenReturn(mockUser);

        // when
        ResponseEntity<ReturnObject> response = userCheckController.checkNickname(testNickname);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.ALREADY_REGISTERED_MEMBER, response.getBody().getErrorCode());
        verify(checkNicknameQuery, times(1)).checkNickname(testNickname);
    }
}