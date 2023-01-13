package dev.ewm.domain.user;

import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import dev.ewm.domain.user.response.UserLoginResponse;
import dev.ewm.domain.user.response.UserRegisterResponse;
import dev.ewm.global.respone.ApiResponse;
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
    public ResponseEntity<UserRegisterResponse> loginUser(
            @Validated @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        User user = userService.registerUser(userRegisterRequest);
        UserRegisterResponse response = UserRegisterResponse.from(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/username/{username}/exists")
    public ResponseEntity<?> checkUsername(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(userService.checkUsername(username)));
    }

    @GetMapping("/nickname/{nickname}/exists")
    public ResponseEntity<?> checkNickname(
            @PathVariable("nickname") String nickname
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(userService.checkNickname(nickname)));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(
    		@Validated @RequestBody UserLoginRequest userLoginRequest
//    		@Validated @ModelAttribute("loginForm") UserLoginRequest userLoginRequest,
    ) {
        UserLoginResponse response = UserLoginResponse.from(userService.loginUser(userLoginRequest));
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
