package order.aggregation.adapter.out.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepo extends JpaRepository<OrderJpaEntity, Long> {

    List<OrderJpaEntity> findByUserId(Long userId);

}
