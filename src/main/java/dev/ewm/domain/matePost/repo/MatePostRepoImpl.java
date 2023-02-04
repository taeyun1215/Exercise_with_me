package dev.ewm.domain.matePost.repo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.matePost.QMatePost;
import dev.ewm.domain.matePost.request.MatePostSearchRequireRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class MatePostRepoImpl implements MatePostRepoCustom {

    private final JPAQueryFactory queryFactory;
    private final QMatePost matePost = QMatePost.matePost;

    @Override
    public List<MatePost> searchAll(MatePostSearchRequireRequest matePostSearchRequireRequest) {

        return queryFactory
                .selectFrom(matePost)
                .where(
                        eqTitle(matePostSearchRequireRequest.getTitle()),
                        eqGym(matePostSearchRequireRequest.getGym()),
                        betweenTime(matePostSearchRequireRequest.getStartTime(), matePostSearchRequireRequest.getEndTime())
                )
                .orderBy(matePost.createdDate.desc())
                .fetch();
    }

    // 제목 검색
    private BooleanExpression eqTitle(String searchTitle) {
        return searchTitle == null ? null : matePost.title.contains(searchTitle);
    }

    // 헬스장 이름 검색
    private BooleanExpression eqGym(String searchGym) {
        return searchGym == null ? null : matePost.gym.contains(searchGym);
    }

    // 시간 대 검색
    private BooleanExpression betweenTime(LocalTime start, LocalTime end) {

        if (end == null) {
            return matePost.startTime.goe(LocalDateTime.from(start).withNano(0));
        } else if (start == null) {
            return matePost.startTime.loe(LocalDateTime.from(end).withNano(0));
        } else {
            return matePost.startTime.between(LocalDateTime.from(start), LocalDateTime.from(end));
        }
    }
}
