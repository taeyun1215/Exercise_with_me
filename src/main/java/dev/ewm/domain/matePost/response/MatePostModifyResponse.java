package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatePostModifyResponse {

    private Long id;
    private String title;
    private String gym;
    private User user;

    public static MatePostModifyResponse from(MatePost matePost) {
        return MatePostModifyResponse.builder()
                .id(matePost.getId())
                .title(matePost.getTitle())
                .gym(matePost.getGym())
                .build();
    }
}
