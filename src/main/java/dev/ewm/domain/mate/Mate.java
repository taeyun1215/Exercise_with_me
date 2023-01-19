package dev.ewm.domain.mate;

import dev.ewm.domain.mate.constant.Type;
import dev.ewm.domain.matePost.MatePost;
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
public class Mate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = MatePost.class, fetch = FetchType.LAZY) // 실제로 요청하는 순간 가져오기 위해 LAZY로 사용함.
//    @JoinColumn(name = "id")
    private MatePost matePost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY) // 실제로 요청하는 순간 가져오기 위해 LAZY로 사용함.
    @JoinColumn(name = "username")
    private User user;

}
