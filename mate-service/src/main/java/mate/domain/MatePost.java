package mate.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mate.adapter.in.request.ModifyMatePostRequest;
import mate.adapter.out.persistence.matePost.MatePostJpaEntity;
import mate.global.baseEntity.BaseTimeEntity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatePost extends BaseTimeEntity implements Serializable {

    private Long matePostId;
    private String title;
    private String content;
    private String gym;
    private String writer;
    private int view = 0;
    private LocalTime startTime;
    private LocalTime endTime;

    private Long userId;
    private List<Long> mateIds;

    public MatePostJpaEntity toJpaEntity() {
        return MatePostJpaEntity.builder()
                .id(matePostId)
                .title(title)
                .content(content)
                .gym(gym)
                .writer(writer)
                .view(view)
                .startTime(startTime)
                .endTime(endTime)
                .userId(userId)
                .build();
    }

    public void updateMatePost(ModifyMatePostRequest modifyMatePostRequest) {
        this.title = modifyMatePostRequest.getTitle();
        this.content = modifyMatePostRequest.getContent();
        this.gym = modifyMatePostRequest.getGym();
        this.startTime = modifyMatePostRequest.getStartTime();
        this.endTime = modifyMatePostRequest.getEndTime();
    }

}
