package order.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompleteOrderCommand {

    private final long orderId;

}
