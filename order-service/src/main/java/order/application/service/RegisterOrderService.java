package order.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.adapter.in.request.OrderItemRegisterRequest;
import order.adapter.in.request.OrderRegisterRequest;
import order.application.kafka.KafkaProducer;
import order.application.port.in.RegisterOrderUseCase;
import order.application.port.out.LoadOrderPort;
import order.application.port.out.SaveOrderItemPort;
import order.application.port.out.SaveOrderPort;
import order.domain.Order;
import order.domain.OrderItem;
import order.domain.events.OrderCreatedEvent;
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

    private final KafkaProducer kafkaProducer;

    @Autowired
    private EventGateway eventGateway;

    @Override
    public void registerOrder(Long userId, OrderRegisterRequest orderRegisterRequest) {
        Order order = orderRegisterRequest.toEntity(userId);
        order.setOrderId(UUID.randomUUID().getMostSignificantBits());
        saveOrderPort.saveOrder(order);

        List<OrderCreatedEvent.OrderItemInfo> orderItemInfos = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRegisterRequest orderItemRegisterRequest : orderRegisterRequest.getOrderItemRegisterRequests()) {
            OrderItem orderItem = orderItemRegisterRequest.toEntity();
            orderItem.setOrderId(order.getOrderId());
            orderItems.add(orderItem);
            saveOrderItemPort.saveOrderItem(orderItem);

            orderItemInfos.add(new OrderCreatedEvent.OrderItemInfo(orderItem.getProductId(), orderItem.getCount()));
        }

        // 주문 생성 이벤트 발행 (주문 아이템 정보 포함)
        eventGateway.publish(new OrderCreatedEvent(order.getOrderId(), orderItemInfos));
    }

    @Override
    public void completeOrder(Long orderId) {
        Order order = loadOrderPort.loadOrder(orderId);
        order.complete();
        saveOrderPort.saveOrder(order);
    }
}
