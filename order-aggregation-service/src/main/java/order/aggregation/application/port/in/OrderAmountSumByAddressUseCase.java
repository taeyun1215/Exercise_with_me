package order.aggregation.application.port.in;

import order.aggregation.application.port.in.command.OrderAmountSumByAddressCommand;

public interface OrderAmountSumByAddressUseCase {

    int OrderAmountSumByAddress(OrderAmountSumByAddressCommand command);

}
