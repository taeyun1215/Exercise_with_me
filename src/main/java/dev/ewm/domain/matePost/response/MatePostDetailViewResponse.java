package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatePostDetailViewResponse {

    private Long id;
    private String title;
    private int view;
    private User user;

    public static MatePostDetailViewResponse from(MatePost matePost) {
        return MatePostDetailViewResponse.builder()
                .id(matePost.getId())
                .title(matePost.getTitle())
                .view(matePost.getView())
                .build();
    }
}
