package mate.adapter.in.web.mate;

import lombok.RequiredArgsConstructor;
import mate.application.port.in.usecase.JoinMateUseCase;
import mate.global.utils.ReturnObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mate")
public class MateJoinController {

    private final JoinMateUseCase joinMateUseCase;

    @GetMapping("/join/{matePostId}")
    public ResponseEntity<ReturnObject> joinMate(
            HttpServletRequest httpServletRequest,
            @PathVariable("matePostId") Long matePostId
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));
        joinMateUseCase.joinMate(matePostId, userId);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("운동에 조인하셨습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }


}
