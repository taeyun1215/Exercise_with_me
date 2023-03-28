package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MatePostSearchRequireResponse {

    private List<MatePost> matePosts;

    public static MatePostSearchRequireResponse from(List<MatePost> matePosts) {
        return MatePostSearchRequireResponse.builder()
                .matePosts(matePosts)
                .build();
    }

}
