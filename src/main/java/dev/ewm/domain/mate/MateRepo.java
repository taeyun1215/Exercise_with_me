package dev.ewm.domain.mate;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateRepo extends JpaRepository<Mate, Long> {

    Optional<Mate> findByMatePostAndUser(MatePost matePost, User user);

    List<Mate> findByMatePost(MatePost matePost);

}
