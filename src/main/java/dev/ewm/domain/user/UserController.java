package dev.ewm.domain.user;

import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import dev.ewm.domain.user.response.UserLoginResponse;
import dev.ewm.domain.user.response.UserRegisterResponse;
import dev.ewm.global.respone.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(
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
            @Validated UserLoginRequest userLoginRequest,
            HttpServletRequest request
    ) {
        User user = userService.loginUser(userLoginRequest);
        UserLoginResponse response = UserLoginResponse.from(user);

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

}
