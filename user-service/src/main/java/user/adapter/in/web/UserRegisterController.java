package user.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.adapter.in.request.RegisterUserRequest;
import user.adapter.out.persistence.UserResponseMapper;
import user.adapter.out.response.RegisterUserResponse;
import user.application.port.in.command.RegisterUserCommand;
import user.application.port.in.usecase.RegisterUserUseCase;
import user.domain.User;
import user.global.utils.ReturnObject;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRegisterController {

    private final RegisterUserUseCase registerUserUseCase;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerUser(
            @RequestBody RegisterUserRequest registerUserRequest
    ) {
        RegisterUserCommand command = RegisterUserCommand.builder()
                .username(registerUserRequest.getUsername())
                .password(registerUserRequest.getPassword())
                .confirmPassword(registerUserRequest.getConfirmPassword())
                .nickname(registerUserRequest.getNickname())
                .phone(registerUserRequest.getPhone())
                .email(registerUserRequest.getEmail())
                .build();

        User user = registerUserUseCase.registerUser(command);
        RegisterUserResponse response = userResponseMapper.mapToRegisterUserResponse(user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
