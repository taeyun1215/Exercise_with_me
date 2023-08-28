package order.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.adapter.in.request.OrderItemRegisterRequest;
import order.adapter.in.request.OrderRegisterRequest;
import order.application.kafka.KafkaProducer;
import order.application.port.in.RegisterOrderUseCase;
import order.application.port.in.command.CreateOrderCommand;
import order.application.port.out.LoadOrderPort;
import order.application.port.out.SaveOrderItemPort;
import order.application.port.out.SaveOrderPort;
import order.domain.Order;
import order.domain.OrderItem;
import order.domain.constant.OrderStatus;
import order.domain.events.OrderCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterOrderService implements RegisterOrderUseCase {

    private final SaveOrderPort saveOrderPort;
    private final SaveOrderItemPort saveOrderItemPort;
    private final LoadOrderPort loadOrderPort;

    @Autowired
    private EventGateway eventGateway;

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void registerOrder(CreateOrderCommand command) {
        Order order = Order.builder()
                .receiverName(command.getReceiverName())
                .receiverPhone(command.getReceiverPhone())
                .receiverAddress(command.getReceiverAddress())
                .userId(command.getUserId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .build();

        saveOrderPort.saveOrder(order);

        List<OrderCreatedEvent.OrderItemInfo> orderItemInfos = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderCreatedEvent.OrderItemInfo orderItemInfo : command.getOrderItemInfos()) {
            OrderItem orderItem = OrderItem.builder()
                    .productId(orderItemInfo.getProductId())
                    .count(orderItemInfo.getCount())
                    .build();

            orderItem.setOrderId(order.getOrderId());
            orderItems.add(orderItem);
            saveOrderItemPort.saveOrderItem(orderItem);

            orderItemInfos.add(new OrderCreatedEvent.OrderItemInfo(orderItem.getProductId(), orderItem.getCount()));
        }

        // 주문 생성 이벤트 발행
//        eventGateway.publish(new OrderCreatedEvent(order.getOrderId(), orderItemInfos));

        // Command 발행
//        command.setOrderId(UUID.randomUUID().getMostSignificantBits());
        command.setOrderId(1L);
        commandGateway.sendAndWait(command);
    }

    @Override
    public void completeOrder(Long orderId) {
        Order order = loadOrderPort.loadOrder(orderId);
        order.complete();
        saveOrderPort.saveOrder(order);
    }
}
