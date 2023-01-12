package dev.ewm.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

    User findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);

}
