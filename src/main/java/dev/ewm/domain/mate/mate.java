package dev.ewm.domain.mate;

import dev.ewm.domain.matePost.matePost;
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
public class mate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = matePost.class, fetch = FetchType.LAZY) // 실제로 요청하는 순간 가져오기 위해 LAZY로 사용함.
//    @JoinColumn(name = "id")
    private matePost matePost;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY) // 실제로 요청하는 순간 가져오기 위해 LAZY로 사용함.
    @JoinColumn(name = "username")
    private User user;

}
