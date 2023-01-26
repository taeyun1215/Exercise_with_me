package dev.ewm.domain.matePost;

import dev.ewm.domain.matePost.request.MatePostCreateRequest;
import dev.ewm.domain.matePost.response.MatePostCreateResponse;
import dev.ewm.domain.user.User;
import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostController {

    private final MatePostService matePostService;

    @PostMapping("/create")
    public ResponseEntity<ReturnObject> createMatePost(
            @LoginUser User user,
            @Validated @RequestBody MatePostCreateRequest matePostCreateRequest
    ) {
        MatePost matePost = matePostService.createMatePost(matePostCreateRequest, user);
        MatePostCreateResponse response = MatePostCreateResponse.from(matePost);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
