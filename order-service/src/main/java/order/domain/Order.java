package order.domain;

import lombok.*;
import order.adapter.out.persistence.order.OrderJpaEntity;
import order.domain.constant.OrderStatus;

@Getter
@Setter // todo : 수정 예정 테스트용으로 넣어둠.
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

    public void complete() {
        orderStatus = OrderStatus.ORDER_COMPLETE;
    }

    public void cancelOrder() {
        orderStatus = OrderStatus.ORDER_CANCEL;
    }
}
