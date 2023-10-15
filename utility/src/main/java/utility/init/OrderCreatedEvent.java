package utility.init;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private final Long orderId;

    private final Long userId;

    private final List<OrderItemInfo> orderItems;

    @Getter
    @ToString
    @AllArgsConstructor
    public static class OrderItemInfo {
        private final Long productId;
        private final int count;
    }
}