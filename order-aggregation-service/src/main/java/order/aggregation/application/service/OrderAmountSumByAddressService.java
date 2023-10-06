package order.aggregation.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.aggregation.application.port.in.OrderAmountSumByAddressUseCase;
import order.aggregation.application.port.in.command.OrderAmountSumByAddressCommand;
import order.aggregation.application.port.out.GetOrderPort;
import order.aggregation.application.port.out.GetUserPort;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class OrderAmountSumByAddressService implements OrderAmountSumByAddressUseCase {

    private final GetUserPort getUserPort;
    private final GetOrderPort getOrderPort;

    @Override
    public int OrderAmountSumByAddress(OrderAmountSumByAddressCommand command) {

        List<Long> userIds = getUserPort.getUserIdByAddress(command.getAddress());

        // todo : 일일이 다 가져오는 것 보단 파티션을 나눠서 가져오는 편이 좋으므로 청크를 지정.

        return 0;
    }

}
