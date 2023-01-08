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


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";

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
            @Validated @ModelAttribute("loginForm") UserLoginRequest userLoginRequest,
            HttpServletRequest request
    ) {
        User user = userService.loginUser(userLoginRequest);

        if (member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");

            return "login/loginForm";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();

        // 세션에 로그인 회원 정보 보관
        session.setAttribute(LOGIN_MEMBER, member);

        return "redirect:/";
    }

}
