package dev.ewm.domain.matePost;

import dev.ewm.domain.mate.Mate;
import dev.ewm.domain.mate.response.MateJoinResponse;
import dev.ewm.domain.matePost.request.MatePostCreateRequest;
import dev.ewm.domain.matePost.response.MatePostCreateResponse;
import dev.ewm.domain.matePost.response.MatePostPagingResponse;
import dev.ewm.domain.user.User;
import dev.ewm.global.annotation.LoginUser;
import dev.ewm.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostController {

    private final MatePostService matePostService;

    @GetMapping("/all")
    public ResponseEntity<ReturnObject> pagingMatePost(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MatePost> matePosts = matePostService.pageMatePostList(pageable);
        MatePostPagingResponse response = MatePostPagingResponse.from(matePosts);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

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

    @GetMapping("join/{matePostId}")
    public ResponseEntity<ReturnObject> joinMate(
            @LoginUser User user,
            @PathVariable("matePostId") Long matePostId
    ) {
        List<Mate> matePosts = matePostService.joinMate(matePostId, user);
        MateJoinResponse response = MateJoinResponse.from(matePosts);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
