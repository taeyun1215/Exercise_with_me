package dev.ewm.domain.matePost.response;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Builder
public class MatePostCreateResponse {

    private Long id;
    private String title;
    private String content;
    private String gym;
    private LocalTime startTime;
    private LocalTime endTime;
    private User user;

    public static MatePostCreateResponse from(MatePost matePost) {
        return MatePostCreateResponse.builder()
                .id(matePost.getId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .startTime(matePost.getStartTime())
                .endTime(matePost.getEndTime())
                .user(matePost.getUser())
                .build();
    }
}
