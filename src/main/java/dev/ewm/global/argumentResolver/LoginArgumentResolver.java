package dev.ewm.global.argumentResolver;

import dev.ewm.domain.user.User;
import dev.ewm.domain.user.UserRepo;
import dev.ewm.global.annotation.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";
    private final UserRepo userRepo;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        final String username = webRequest.getHeader(LOGIN_MEMBER);
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new EntityNotFoundException("회원이 존재하지 않습니다.");
        }

        return user;
    }
}
