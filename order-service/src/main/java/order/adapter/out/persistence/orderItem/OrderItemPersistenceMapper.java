package order.adapter.out.persistence.orderItem;

import order.domain.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemPersistenceMapper {

    public OrderItemJpaEntity mapToJpaEntity(OrderItem orderItem) {
        return orderItem.toJpaEntity();
    }

    public static OrderItem mapToDomainEntity(OrderItemJpaEntity orderItemJpaEntity) {
        return OrderItem.builder()
                .orderItemId(orderItemJpaEntity.getOrderId())
                .productId(orderItemJpaEntity.getProductId())
                .count(orderItemJpaEntity.getCount())
                .orderId(orderItemJpaEntity.getOrderId())
                .build();
    }

    public List<OrderItem> mapToDomainEntities(List<OrderItemJpaEntity> orderItemJpaEntities) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemJpaEntity orderItemJpaEntity : orderItemJpaEntities) {
            orderItems.add(mapToDomainEntity(orderItemJpaEntity));
        }
        return orderItems;
    }

}
