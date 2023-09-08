package order.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class OrderCancelledEvent {

    @TargetAggregateIdentifier
    private final Long orderId;

}
