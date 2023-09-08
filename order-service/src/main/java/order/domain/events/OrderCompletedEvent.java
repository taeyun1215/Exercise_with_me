package order.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class OrderCompletedEvent {

    @TargetAggregateIdentifier
    private final String orderId;

}
