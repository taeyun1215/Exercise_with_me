package mate.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {

    LEADER("헬스 메이트 리더"),
    PARTICIPANT("헬스 메이트 팀원");

    private final String value;
}
