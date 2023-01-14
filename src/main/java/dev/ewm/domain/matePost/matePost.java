package dev.ewm.domain.matePost;

import dev.ewm.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class matePost {

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
    private String startTime; // 운동 시작 시간

    @Column(nullable = false)
    private String endTime; // 운동 끝나는 시간

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view = 0; // 조회수

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY) // 실제로 요청하는 순간 가져오기 위해 LAZY로 사용함.
    @JoinColumn(name = "username")
    private User user;

}
