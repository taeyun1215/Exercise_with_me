package mate.adapter.out.persistence.matePost;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class MatePostJpaRepoImpl implements MatePostJpaRepoCustom {

    private final JPAQueryFactory queryFactory;
    private final QMatePostJpaEntity qMatePostJpaEntity = QMatePostJpaEntity.matePostJpaEntity;

    @Override
    public List<MatePostJpaEntity> searchAll(MatePostJpaEntity matePostJpaEntity) {

        return queryFactory
                .selectFrom(qMatePostJpaEntity)
                .where(
                        eqTitle(matePostJpaEntity.getTitle()),
                        eqGym(matePostJpaEntity.getGym()),
                        betweenTime(matePostJpaEntity.getStartTime(), matePostJpaEntity.getEndTime())
                )
                .orderBy(qMatePostJpaEntity.createdDate.desc())
                .fetch();
    }

    // 제목 검색
    private BooleanExpression eqTitle(String searchTitle) {
        return searchTitle == null ? null : qMatePostJpaEntity.title.contains(searchTitle);
    }

    // 헬스장 이름 검색
    private BooleanExpression eqGym(String searchGym) {
        return searchGym == null ? null : qMatePostJpaEntity.gym.contains(searchGym);
    }

    // 시간 대 검색
    private BooleanExpression betweenTime(LocalTime start, LocalTime end) {

        if (start == null & end == null) {
            return null;
        } else if (end == null) {
            return qMatePostJpaEntity.startTime.goe(start);
        } else if (start == null) {
            return qMatePostJpaEntity.endTime.loe(end);
        } else {
            return qMatePostJpaEntity.startTime.between(start, end);
        }
    }

}
