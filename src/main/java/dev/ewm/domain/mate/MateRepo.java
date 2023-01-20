package dev.ewm.domain.mate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateRepo extends JpaRepository<Mate, Long> {
}
