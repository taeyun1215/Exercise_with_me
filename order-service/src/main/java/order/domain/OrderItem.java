package order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import order.adapter.out.persistence.orderItem.OrderItemJpaEntity;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderItem {

    private Long orderItemId;
    private int count;
    private Long orderId;
    private Long productId;

    public OrderItemJpaEntity toJpaEntity() {
        return OrderItemJpaEntity.builder()
                .id(orderItemId)
                .productId(productId)
                .count(count)
                .orderId(orderId)
                .build();
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
