package review.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepo extends JpaRepository<ReviewJpaEntity, Long> {

}
