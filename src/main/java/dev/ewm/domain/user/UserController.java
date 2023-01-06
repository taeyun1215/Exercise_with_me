package dev.ewm.domain.user;

import dev.ewm.domain.user.dto.UserLoginDto;
import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ReturnObject> loginUser(
            @Validated @ModelAttribute("loginForm") UserLoginDto userLoginDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            ReturnObject returnObject = ReturnObject.builder()
                    .msg()
                    .type(bindingResult.)
                    .build();

            return ResponseEntity.badRequest().body(returnObject);
        }

        // Member member = loginService.login(form.getLoginId(), form.getPassword());

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

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
