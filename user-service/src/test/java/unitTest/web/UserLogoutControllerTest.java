package unitTest.web;

import global.utils.ReturnObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.adapter.in.web.UserLogoutController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserLogoutController 단위 테스트")
public class UserLogoutControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private UserLogoutController userLogoutController;

    @BeforeEach
    public void init() {
        when(request.getSession(false)).thenReturn(session);
    }

    @Test
    @DisplayName("로그아웃 요청에 세션이 존재하는 경우 성공 응답 반환")
    public void shouldLogoutUserWhenSessionExists() {
        // given
        when(request.getSession(false)).thenReturn(session);

        // when
        ResponseEntity<ReturnObject> response = userLogoutController.logoutUser(request);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        verify(session, times(1)).invalidate();
    }

    @Test
    @DisplayName("로그아웃 요청에 세션이 존재하지 않는 경우 실패 응답 반환")
    public void shouldHandleLogoutRequestWhenSessionDoesNotExist() {
        // given
        when(request.getSession(false)).thenReturn(null);

        // when
        ResponseEntity<ReturnObject> response = userLogoutController.logoutUser(request);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        verify(session, times(0)).invalidate();
    }
}