package mate.adapter.in.request;

import lombok.EqualsAndHashCode;
import lombok.Value;
import mate.domain.MatePost;
import mate.global.common.SelfValidating;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;


@Value
@EqualsAndHashCode(callSuper = false)
public class SearchRequireMatePostRequest extends SelfValidating<SearchRequireMatePostRequest> {

    private String title;
    private String gym;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    public MatePost toEntity() {
        return MatePost.builder()
                .title(title)
                .gym(gym)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}
