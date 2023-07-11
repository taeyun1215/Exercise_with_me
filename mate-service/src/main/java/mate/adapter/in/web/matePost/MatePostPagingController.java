package mate.adapter.in.web.matePost;

import lombok.RequiredArgsConstructor;
import mate.adapter.out.persistence.matePost.response.PagingMatePostResponse;
import mate.application.port.in.query.PagingMatePostQuery;
import mate.domain.MatePost;
import mate.global.utils.ReturnObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostPagingController {

    private final PagingMatePostQuery pagingMatePostQuery;

    @GetMapping("/all")
    public ResponseEntity<ReturnObject> pagingMatePost(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MatePost> matePosts = pagingMatePostQuery.pageMatePostList(pageable);
        List<PagingMatePostResponse> response = PagingMatePostResponse.from(matePosts);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
