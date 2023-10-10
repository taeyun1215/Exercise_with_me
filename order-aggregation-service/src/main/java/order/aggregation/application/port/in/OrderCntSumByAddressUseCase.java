package order.aggregation.application.port.in;

import order.aggregation.application.port.in.command.OrderCntSumByAddressCommand;

public interface OrderCntSumByAddressUseCase {

    int OrderCntSumByAddress(OrderCntSumByAddressCommand command);

}
