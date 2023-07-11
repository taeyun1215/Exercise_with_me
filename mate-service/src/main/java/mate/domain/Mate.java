package mate.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mate.adapter.out.persistence.mate.MateJpaEntity;
import mate.domain.constant.Type;
import mate.global.baseEntity.BaseTimeEntity;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mate extends BaseTimeEntity implements Serializable {

    private Long mateId;
    private Type type;

    private Long userId;
    private Long matePostId;

    public MateJpaEntity toJpaEntity(MatePost matePost, Long userId) {
        return MateJpaEntity.builder()
                .id(mateId)
                .type(type)
                .userId(userId)
                .matePostId(matePost.getMatePostId())
                .build();
    }

}