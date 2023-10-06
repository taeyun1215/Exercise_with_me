package user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByUsername(String username);
    Optional<UserJpaEntity> findByNickname(String nickname);

    @Query(value = "SELECT id FROM user WHERE address = :address", nativeQuery = true)
    List<Long> findUserIdByAddress(@Param("address") String address);

}
