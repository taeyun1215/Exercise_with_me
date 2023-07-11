package mate.adapter.in.request;

import lombok.EqualsAndHashCode;
import lombok.Value;
import mate.global.common.SelfValidating;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = false)
public class ModifyMatePostRequest extends SelfValidating<ModifyMatePostRequest> {

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    @NotBlank(message = "헬스장은 필수 입력 값입니다.")
    private String gym;

    @NotBlank(message = "운동 시작하는 시간은 필수 입력 값입니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @NotBlank(message = "운동 끝나는 시간은 필수 입력 값입니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    public ModifyMatePostRequest(
            String title,
            String content,
            String gym,
            LocalTime startTime,
            LocalTime endTime
    ) {
        this.title = title;
        this.content = content;
        this.gym = gym;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validateSelf();
    }

}
