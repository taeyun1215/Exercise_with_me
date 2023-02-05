package dev.ewm.domain.matePost.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class MatePostModifyRequest {

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

}
