package order.aggregation.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.aggregation.application.port.in.OrderCntSumByAddressUseCase;
import order.aggregation.application.port.in.command.OrderCntSumByAddressCommand;
import order.aggregation.application.port.out.GetOrderPort;
import order.aggregation.application.port.out.GetUserPort;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class OrderAmountSumByAddressService implements OrderCntSumByAddressUseCase {

    private final GetUserPort getUserPort;
    private final GetOrderPort getOrderPort;

    @Override
    public int OrderCntSumByAddress(OrderCntSumByAddressCommand command) {

        List<Long> userIds = getUserPort.getUserIdByAddress(command.getAddress());

        List<List<Long>> userPartitionList = null;
        if (userIds.size() > 50) {
            userPartitionList = partitionList(userIds, 50);
        }

        List<CompletableFuture<Integer>> futures = new ArrayList<>();

        for (List<Long> partitionedList : userPartitionList) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                List<Integer> orderCntList = getOrderPort.getOrderCntByUserIds(partitionedList);
                return orderCntList.stream().mapToInt(Integer::intValue).sum();
            });
            futures.add(future);
        }

        // Collect and sum the results of all asynchronous computations
        int orderCntSum = futures.stream()
                .map(CompletableFuture::join) // wait for each future to complete
                .mapToInt(Integer::intValue)
                .sum();

        return orderCntSum;
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
