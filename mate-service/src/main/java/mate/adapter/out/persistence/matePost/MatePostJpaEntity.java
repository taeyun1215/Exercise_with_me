package mate.adapter.out.persistence.matePost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mate.global.baseEntity.BaseTimeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "mate_post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatePostJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Column(nullable = false)
    private String content; // 게시글 내용

    @Column(nullable = false)
    private String gym; // 헬스장 이름

    @Column(nullable = false)
    private String writer; // 작성자

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view = 0; // 조회수

    @Column(name = "user_id")
    private Long userId;

    @ElementCollection
    @CollectionTable(name = "mate_post_mate",
            joinColumns = @JoinColumn(name = "mate_post_id"))
    @Column(name = "mate_ids")
    private List<Long> mateIds;

}
