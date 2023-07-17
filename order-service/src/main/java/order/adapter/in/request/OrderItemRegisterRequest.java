package order.adapter.in.request;

import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;
import order.domain.OrderItem;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderItemRegisterRequest extends SelfValidating<OrderItemRegisterRequest> {

    private Long productId;
    private int count;

    public OrderItemRegisterRequest(Long productId, int count) {
        this.productId = productId;
        this.count = count;
        this.validateSelf();
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .productId(productId)
                .count(count)
                .build();
    }

}
