package order.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private final String orderId;

    private final String productId;
    private final int count;

}
