package dev.ewm.domain.matePost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatePostRepo extends JpaRepository<MatePost, Long> {
}
