package global.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReduceStockCommand {

    private long productId;
    private int count;
    private String orderId;

}
