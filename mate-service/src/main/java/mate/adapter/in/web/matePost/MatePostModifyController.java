package mate.adapter.in.web.matePost;

import lombok.RequiredArgsConstructor;
import mate.adapter.in.request.ModifyMatePostRequest;
import mate.application.port.in.usecase.ModifyMatePostUseCase;
import mate.global.utils.ReturnObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostModifyController {

    private final ModifyMatePostUseCase modifyMatePostUseCase;

    @PutMapping("/modify/{matePostId}")
    public ResponseEntity<ReturnObject> modifyMatePost(
            HttpServletRequest httpServletRequest,
            @PathVariable("matePostId") Long matePostId,
            @RequestBody ModifyMatePostRequest modifyMatePostRequest
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));
        modifyMatePostUseCase.modifyMatePost(modifyMatePostRequest, matePostId, userId);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("수정이 완료 되었습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }


}
