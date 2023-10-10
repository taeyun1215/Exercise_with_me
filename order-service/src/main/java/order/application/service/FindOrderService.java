package order.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.application.port.in.FindOrderQuery;
import order.application.port.out.LoadOrderItemPort;
import order.application.port.out.LoadOrderPort;
import order.domain.Order;
import order.domain.OrderItem;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class FindOrderService implements FindOrderQuery {

    private final LoadOrderPort loadOrderPort;
    private final LoadOrderItemPort loadOrderItemPort;

    @Override
    public List<Integer> findOrderCntListByUserIds(List<Long> userIds) {
        List<Integer> orderCntByUserId = new ArrayList<>();

        for (Long userId : userIds) {
            List<Order> orders = loadOrderPort.loadOrderByUser(userId);

            for (Order order : orders) {
                List<OrderItem> orderItems = loadOrderItemPort.loadOrderItemByOrderId(order.getOrderId());
                orderCntByUserId.add(orderItems.size());
            }
        }
        return orderCntByUserId;
    }
}
