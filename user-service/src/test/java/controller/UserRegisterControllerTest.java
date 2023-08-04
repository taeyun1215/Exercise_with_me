package controller;

import global.utils.ReturnObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.adapter.in.request.RegisterUserRequest;
import user.adapter.in.web.UserRegisterController;
import user.adapter.out.persistence.UserResponseMapper;
import user.adapter.out.response.RegisterUserResponse;
import user.application.port.in.usecase.RegisterUserUseCase;
import user.domain.User;
import user.domain.constant.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRegisterControllerTest {

    @Mock
    private RegisterUserUseCase registerUserUseCase;

    @Mock
    private UserResponseMapper userResponseMapper;

    @InjectMocks
    private UserRegisterController userRegisterController;

    RegisterUserRequest request;
    User mockUser;
    RegisterUserResponse mockResponse;

    @BeforeEach
    public void setUp() {
        // RegisterUserRequest 객체 생성
        request = new RegisterUserRequest(
                "testuser",
                "test111!!!",
                "test111!!!",
                "testNickname",
                "010-1234-5678",
                "testuser@example.com"
        );

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

        // RegisterUserResponse 객체 생성
        mockResponse = RegisterUserResponse.builder()
                .username("testuser")
                .nickname("testNickname")
                .phone("010-1234-5678")
                .email("testuser@example.com")
                .role(Role.USER)
                .build();
    }

    @Test
    public void shouldRegisterUser() throws Exception {
        /*
        * given
        * UserRegisterController에 영향을 미치는 즉, 의존성을 주입하는 메소드만 받아서 사용하면 된다.
        */
        when(registerUserUseCase.registerUser(any())).thenReturn(mockUser);
        when(userResponseMapper.mapToRegisterUserResponse(any())).thenReturn(mockResponse);

        /*
        * when
        * 테스트하려는 메소드를 실행하는 단계이다.
        */
        ResponseEntity<ReturnObject> response = userRegisterController.registerUser(request);

        /*
        * then
        * 테스트한 메소드에 대한 결과를 검증한다.
        */
        // 응답 상태 코드가 OK(200)인지 확인한다.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // 응답 본문의 데이터가 우리가 mocking한 response 객체와 동일한지 확인한다.
        assertEquals(mockResponse, response.getBody().getData());
        // UserRegisterController에서 registerUserUseCase.registerUser()가 1번 실행됐는지 확인하기.
        verify(registerUserUseCase, times(1)).registerUser(any());
        // UserRegisterController에서 userResponseMapper.mapToRegisterUserResponse()가 1번 실행됐는지 확인하기.
        verify(userResponseMapper, times(1)).mapToRegisterUserResponse(any());
    }
}



