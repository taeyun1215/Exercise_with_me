package mate.adapter.out.persistence.mate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateJpaRepo extends JpaRepository<MateJpaEntity, Long> {

    Optional<MateJpaEntity> findByMatePostIdAndUserId(Long matePostId, Long userId);

//    List<MateJpaEntity> findByMatePost(MatePost matePost);

}
