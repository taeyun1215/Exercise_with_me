package dev.ewm.domain.matePost.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
public class MatePostSearchRequireRequest {

    private String title;
    private String gym;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

}
