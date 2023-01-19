package dev.ewm.domain.matePost.request;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MatePostCreateRequest {

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String title; // 게시글 제목

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    @NotBlank(message = "헬스장은 필수 입력 값입니다.")
    private String gym;

    @NotBlank(message = "운동 시작하는 시간은 필수 입력 값입니다.")
    private String startTime;

    @NotBlank(message = "운동 끝나는 시간은 필수 입력 값입니다.")
    private String endTime;

    public MatePost toEntity(User user) {
        return MatePost.builder()
                .title(title)
                .content(content)
                .gym(gym)
                .startTime(startTime)
                .endTime(endTime)
                .user(user)
                .build();
    }
}
