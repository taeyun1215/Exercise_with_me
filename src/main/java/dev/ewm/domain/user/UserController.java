package dev.ewm.domain.user;

import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import dev.ewm.domain.user.response.UserLoginResponse;
import dev.ewm.domain.user.response.UserRegisterResponse;
import dev.ewm.global.error.ErrorCode;
import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpSession;

//@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerUser(
            @Validated @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        User user = userService.registerUser(userRegisterRequest);
        UserRegisterResponse response = UserRegisterResponse.from(user);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @GetMapping("/username/{username}/exists")
    public ResponseEntity<ReturnObject> checkUsername(
            @PathVariable("username") String username
    ) {
        User user = userService.checkUsername(username);
        if (user == null) {
            ReturnObject returnObject = ReturnObject.builder()
                    .success(true)
                    .data(username)
                    .build();

            ResponseEntity.status(HttpStatus.OK).body(returnObject);
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(false)
                .errorCode(ErrorCode.ALREADY_REGISTERED_MEMBER)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
    }

    @GetMapping("/nickname/{nickname}/exists")
    public ResponseEntity<ReturnObject> checkNickname(
            @PathVariable("nickname") String nickname
    ) {
        User user = userService.checkNickname(nickname);
        if (user == null) {
            ReturnObject returnObject = ReturnObject.builder()
                    .success(true)
                    .data(nickname)
                    .build();

            ResponseEntity.status(HttpStatus.OK).body(returnObject);
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(false)
                .errorCode(ErrorCode.ALREADY_REGISTERED_MEMBER)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
    }

    @PostMapping("/login")
    public ResponseEntity<ReturnObject> loginUser(
    		@Validated @RequestBody UserLoginRequest userLoginRequest
//    		@Validated @ModelAttribute("loginForm") UserLoginRequest userLoginRequest,
    ) {
        UserLoginResponse response = UserLoginResponse.from(userService.loginUser(userLoginRequest));
        
        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();
        
        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

    @PostMapping("/logout")
    public ResponseEntity<ReturnObject> logoutUser() {
    	userService.logout();
    	
    	ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .build();
    	
    	return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
