package global.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class ReduceStockCommand {

    private long productId;
    private int count;

    @TargetAggregateIdentifier
    private String orderId;

}
