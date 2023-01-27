package dev.ewm.domain.mate.response;

import dev.ewm.domain.mate.Mate;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MateJoinResponse {

    private List<Mate> mates;

    public static MateJoinResponse from(List<Mate> mates) {
        return MateJoinResponse.builder()
                .mates(mates)
                .build();
    }
}
