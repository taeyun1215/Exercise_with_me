package mate.adapter.out.persistence.mate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mate.domain.constant.Type;
import mate.global.baseEntity.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "mate")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MateJpaEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "mate_post_id")
    private Long matePostId;

}
