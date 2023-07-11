package mate.adapter.in.request;

import lombok.EqualsAndHashCode;
import lombok.Value;
import mate.domain.MatePost;
import mate.global.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateMatePostRequest extends SelfValidating<CreateMatePostRequest> {

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    @NotBlank(message = "헬스장은 필수 입력 값입니다.")
    private String gym;

    private String writer;

//    @NotNull(message = "운동 시작하는 시간은 필수 입력 값입니다.")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    private LocalTime startTime;
//
//    @NotNull(message = "운동 끝나는 시간은 필수 입력 값입니다.")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    private LocalTime endTime;

    public CreateMatePostRequest(
            String title,
            String content,
            String gym,
            String writer,
            LocalTime startTime,
            LocalTime endTime
    ) {
        this.title = title;
        this.content = content;
        this.gym = gym;
        this.writer = writer;
//        this.startTime = startTime;
//        this.endTime = endTime;
        this.validateSelf();
    }

    public MatePost toEntity(Long userId) {
        return MatePost.builder()
                .title(title)
                .content(content)
                .gym(gym)
                .writer(writer)
//                .startTime(startTime)
//                .endTime(endTime)
                .userId(userId)
                .build();
    }
}
