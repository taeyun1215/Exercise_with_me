package order.cqrs.adapter.out;

import order.cqrs.domain.OrderCntSumByAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderCntSumByAddressRepo extends JpaRepository<OrderCntSumByAddress, Long> {

    Optional<OrderCntSumByAddress> findByAddress(String address);

}
