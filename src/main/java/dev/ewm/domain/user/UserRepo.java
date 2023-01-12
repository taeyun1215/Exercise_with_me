package dev.ewm.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByNickname(String nickname);

    Optional<User> findByEmail(String email);

}
