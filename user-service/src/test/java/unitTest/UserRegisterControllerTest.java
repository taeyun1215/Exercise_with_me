package unitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.adapter.in.request.RegisterUserRequest;
import user.adapter.out.persistence.UserResponseMapper;
import user.adapter.out.response.LoginUserResponse;
import user.adapter.out.response.RegisterUserResponse;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.in.usecase.RegisterUserUseCase;
import user.domain.User;
import user.adapter.in.web.UserRegisterController;
import global.utils.ReturnObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("UserRegisterController 단위 테스트")
public class UserRegisterControllerTest {

    private UserRegisterController userRegisterController;
    private RegisterUserUseCase registerUserUseCaseFake;
    private UserResponseMapper userResponseMapperFake;

    @BeforeEach
    void setUp() {
        registerUserUseCaseFake = new RegisterUserUseCaseFake();
        userResponseMapperFake = new UserResponseMapperFake();
        userRegisterController = new UserRegisterController(registerUserUseCaseFake, userResponseMapperFake);
    }

    @Test
    @DisplayName("회원가입 요청에 대한 성공 응답 반환")
    void testRegisterUserSuccess() {
        RegisterUserRequest request = new RegisterUserRequest(
                "testuser123",
                "Test@12345",
                "Test@12345",
                "testNickname",
                "010-1234-5678",
                "test@example.com"
        );

        ResponseEntity<ReturnObject> responseEntity = userRegisterController.registerUser(request);

        // 응답 상태 및 바디 검증
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(true, responseEntity.getBody().isSuccess());
        assertEquals(request.getUsername(), ((RegisterUserResponse) responseEntity.getBody().getData()).getUsername());
    }

    private static class RegisterUserUseCaseFake implements RegisterUserUseCase {

        @Override
        public User registerUser(RegisterUserCommand command) {
            return User.builder()
                    .username(command.getUsername())
                    .build();
        }
    }

    private static class UserResponseMapperFake extends UserResponseMapper {

        @Override
        public RegisterUserResponse mapToRegisterUserResponse(User user) {
            return RegisterUserResponse.builder()
                    .username(user.getUsername())
                    .build();
        }

        // UserRegisterController에서는 사용하지 않는 메소드라 구현 X
        @Override
        public LoginUserResponse mapToLoginUserResponse(User user) {
            return null;
        }
    }
}
