package unitTest.controller;

import global.utils.ReturnObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.adapter.in.request.LoginUserRequest;
import user.adapter.in.web.UserLoginController;
import user.adapter.out.persistence.UserJpaRepo;
import user.adapter.out.persistence.UserResponseMapper;
import user.adapter.out.response.LoginUserResponse;
import user.application.port.in.usecase.LoginUserUseCase;
import user.domain.User;
import user.domain.constant.Role;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserLoginController 단위 테스트")
public class UserLoginControllerTest {

    @Mock
    private LoginUserUseCase loginUserUseCase;

    @Mock
    private UserResponseMapper userResponseMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private UserLoginController userLoginController;

    LoginUserRequest loginUserRequest;
    User mockUser;
    LoginUserResponse mockResponse;

    @BeforeEach
    public void init() {
        // LoginUserRequest 객체 생성
        loginUserRequest = new LoginUserRequest(
                "testuser",
                "test123!!!"
        );

        // User 객체 생성
        mockUser = User.builder()
                .userId(1L)
                .username("testuser")
                .password("test123!!!")
                .nickname("testNickname")
                .phone("010-1234-5678")
                .email("testuser@example.com")
                .role(Role.USER)
                .build();

        // LoginUserResponse 객체 생성
        mockResponse = LoginUserResponse.builder()
                .username("testuser")
                .build();
    }

    @Test
    @DisplayName("로그인 요청에 대한 성공 응답 반환")
    public void shouldLoginUser() throws Exception {
        // given
        when(loginUserUseCase.loginUser(any())).thenReturn(mockUser);
        when(userResponseMapper.mapToLoginUserResponse(any())).thenReturn(mockResponse);
        when(request.getSession()).thenReturn(session);

        // when
        ResponseEntity<ReturnObject> response = userLoginController.loginUser(loginUserRequest, request);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody().getData());
        verify(loginUserUseCase, times(1)).loginUser(any());
        verify(userResponseMapper, times(1)).mapToLoginUserResponse(any());
        verify(session, times(1)).setAttribute(any(), any());
    }

    @Test
    @DisplayName("로그인 정보가 잘못되었을 때 실패 응답 반환")
    public void shouldReturnBadRequestOnInvalidLoginDetails() throws Exception {
        // given
        when(loginUserUseCase.loginUser(any())).thenThrow(new EntityNotFoundException("일치하는 정보가 없습니다."));

        // when
        Exception exception = null;

        try {
            userLoginController.loginUser(loginUserRequest, request);
        } catch (Exception e) {
            exception = e;
        }

        // then
        assertNotNull(exception);
        assertTrue(exception instanceof EntityNotFoundException);
        assertEquals("일치하는 정보가 없습니다.", exception.getMessage());
        verify(loginUserUseCase, times(1)).loginUser(any());
    }

}
