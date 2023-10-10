package order.aggregation.adapter.out.persistence.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemJpaRepo extends JpaRepository<OrderItemJpaEntity, Long> {

    List<OrderItemJpaEntity> findByOrderId(Long orderId);

}
