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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<List<Long>> userPartitionList = null;
        if (userIds.size() > 100) {
            userPartitionList = partitionList(userIds, 100);
        }

        int orderSum = 0;
        for (List<Long> partitionedList : userPartitionList) {
            // 100 개씩 요청해서, 값을 계산하기로 설계.
            List<Integer> orderMoneys = getOrderPort.getMoneyByUserIds(partitionedList);

            for (Integer orderMoney : orderMoneys) {
                orderSum += orderMoney;
            }
        }
        return orderSum;
    }

    // List 를 n개씩 묶어서 List<List<T>>로 만드는 메서드
    private static <T> List<List<T>> partitionList(List<T> list, int partitionSize) {
        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(index -> index / partitionSize))
                .values()
                .stream()
                .map(indices -> indices.stream().map(list::get).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

}
