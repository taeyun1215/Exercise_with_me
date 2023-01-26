package dev.ewm.domain.matePost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatePostRepo extends JpaRepository<MatePost, Long> {

    Optional<MatePost> findById(Long postId);

}
