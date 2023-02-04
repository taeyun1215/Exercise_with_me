package dev.ewm.domain.matePost.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class MatePostSearchRequireRequest {

    private String title;
    private String gym;
    private LocalTime startTime;
    private LocalTime endTime;

}
