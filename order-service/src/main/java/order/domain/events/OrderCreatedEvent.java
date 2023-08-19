package order.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private final Long orderId;

    private final List<OrderItemInfo> orderItems;

    @Data
    @AllArgsConstructor
    public static class OrderItemInfo {
        private final Long productId;
        private final int count;
    }

}
