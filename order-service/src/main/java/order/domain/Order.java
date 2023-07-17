package order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import order.adapter.out.persistence.order.OrderJpaEntity;
import order.domain.constant.OrderStatus;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private Long userId;
    private OrderStatus orderStatus;

    public OrderJpaEntity toJpaEntity() {
        return OrderJpaEntity.builder()
                .id(orderId)
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .userId(userId)
                .orderStatus(orderStatus)
                .build();
    }

    public void cancelOrder() {
        orderStatus = OrderStatus.ORDER_CANCEL;
    }
}
