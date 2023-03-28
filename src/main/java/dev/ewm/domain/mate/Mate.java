package dev.ewm.domain.mate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.ewm.domain.base.BaseTimeEntity;
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
public class Mate extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @JsonBackReference
    @ManyToOne(targetEntity = MatePost.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "matePostId")
    private MatePost matePost;

}
