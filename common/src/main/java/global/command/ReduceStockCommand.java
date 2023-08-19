package global.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReduceStockCommand {

    private final long productId;
    private final int count;
    private final long orderId;

}
