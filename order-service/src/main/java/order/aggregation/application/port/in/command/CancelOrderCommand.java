package order.aggregation.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private final Long orderId;

}
