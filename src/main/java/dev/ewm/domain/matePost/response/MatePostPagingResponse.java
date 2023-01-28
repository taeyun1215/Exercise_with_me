package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class MatePostPagingResponse {

    private Page<MatePost> matePosts;

    public static MatePostPagingResponse from(Page<MatePost> matePosts) {
        return MatePostPagingResponse.builder()
                .matePosts(matePosts)
                .build();
    }

}
