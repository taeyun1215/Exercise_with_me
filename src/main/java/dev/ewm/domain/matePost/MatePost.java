package dev.ewm.domain.matePost;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.ewm.domain.base.BaseTimeEntity;
import dev.ewm.domain.mate.Mate;
import dev.ewm.domain.matePost.request.MatePostModifyRequest;
import dev.ewm.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatePost extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Column(nullable = false)
    private String content; // 게시글 내용

    @Column(nullable = false)
    private String gym; // 헬스장 이름

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view = 0; // 조회수

    @JsonBackReference
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToMany(mappedBy = "matePost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Mate> mateList;

    public void updateMatePost(MatePostModifyRequest matePostModifyRequest) {
        this.title = matePostModifyRequest.getTitle();
        this.content = matePostModifyRequest.getContent();
        this.gym = matePostModifyRequest.getGym();
        this.startTime = matePostModifyRequest.getStartTime();
        this.endTime = matePostModifyRequest.getEndTime();
    }

}
