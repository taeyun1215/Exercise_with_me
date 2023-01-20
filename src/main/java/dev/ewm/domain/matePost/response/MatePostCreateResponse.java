package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.user.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class MatePostCreateResponse {

    private String title;
    private String content;
    private String gym;
    private String startTime;
    private String endTime;
    private User user;

    public static MatePostCreateResponse from(MatePost matePost) {
        return MatePostCreateResponse.builder()
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .startTime(matePost.getStartTime())
                .endTime(matePost.getEndTime())
                .user(matePost.getUser())
                .build();
    }
}
