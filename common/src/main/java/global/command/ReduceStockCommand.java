package global.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReduceStockCommand {

    @TargetAggregateIdentifier
    private Long orderId;

    private List<OrderItem> items;

    @Data
    @AllArgsConstructor
    public static class OrderItem {
        private Long productId;
        private Integer count;
    }

}
