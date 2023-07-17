package order.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.adapter.in.request.OrderItemRegisterRequest;
import order.adapter.in.request.OrderRegisterRequest;
import order.application.port.in.RegisterOrderUseCase;
import order.application.port.out.SaveOrderItemPort;
import order.application.port.out.SaveOrderPort;
import order.domain.Order;
import order.domain.OrderItem;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterOrderService implements RegisterOrderUseCase {

    private final ReduceStockUseCase reduceStockUseCase; // todo : kafka producer
    private final SaveOrderPort saveOrderPort;
    private final SaveOrderItemPort saveOrderItemPort;

    @Override
    public void registerOrder(Long userId, OrderRegisterRequest orderRegisterRequest) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRegisterRequest orderItemRegisterRequest : orderRegisterRequest.getOrderItemRegisterRequests()) {
            OrderItem orderItem = orderItemRegisterRequest.toEntity();
            orderItemRegisterRequest.getProductId();
            orderItems.add(orderItem);
        }
        Order order = orderRegisterRequest.toEntity(userId);

        for (OrderItem orderItem : orderItems) {
            reduceStockUseCase.reduceStock(orderItem);
        }
        save(order, orderItems);
    }

    private void save(Order order, List<OrderItem> orderItems) {
        saveOrderPort.saveOrder(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getOrderId());
            saveOrderItemPort.saveOrderItem(orderItem);
        }
    }

}
