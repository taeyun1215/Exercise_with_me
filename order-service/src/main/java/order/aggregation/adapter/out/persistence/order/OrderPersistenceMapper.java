package order.aggregation.adapter.out.persistence.order;

import order.domain.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPersistenceMapper {

    public OrderJpaEntity mapToJpaEntity(Order order) {
        return order.toJpaEntity();
    }

    public Order mapToDomainEntity(OrderJpaEntity orderJpaEntity) {
        return Order.builder()
                .orderId(orderJpaEntity.getId())
                .receiverName(orderJpaEntity.getReceiverName())
                .receiverPhone(orderJpaEntity.getReceiverPhone())
                .receiverAddress(orderJpaEntity.getReceiverAddress())
                .userId(orderJpaEntity.getUserId())
                .orderStatus(orderJpaEntity.getOrderStatus())
                .build();

    }

    public List<Order> mapToDomainEntities(List<OrderJpaEntity> orderJpaEntities) {
        List<Order> orders = new ArrayList<>();
        for (OrderJpaEntity orderJpaEntity : orderJpaEntities) {
            orders.add(mapToDomainEntity(orderJpaEntity));
        }
        return orders;
    }

}
