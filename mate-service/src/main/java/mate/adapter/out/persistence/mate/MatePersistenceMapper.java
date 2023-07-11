package mate.adapter.out.persistence.mate;

import mate.domain.Mate;
import mate.domain.MatePost;
import org.springframework.stereotype.Component;

@Component
public class MatePersistenceMapper {

    public Mate mapToDomainEntity(MateJpaEntity mateJpaEntity) {
        return Mate.builder()
                .mateId(mateJpaEntity.getId())
                .type(mateJpaEntity.getType())
                .userId(mateJpaEntity.getUserId())
                .matePostId(mateJpaEntity.getMatePostId())
                .build();
    }

    public MateJpaEntity mapToJpaEntity(Mate mate, MatePost matePost, Long userId) {
        return mate.toJpaEntity(matePost, userId);
    }

}
