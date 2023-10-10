package order.aggregation.application.saga;

import lombok.extern.slf4j.Slf4j;
import order.aggregation.application.port.in.command.CancelOrderCommand;
import order.aggregation.application.port.in.command.CompleteOrderCommand;
import global.command.ReduceStockCommand;
import global.event.StockReducedEvent;
import global.event.StockSoldOutEvent;
import order.domain.events.OrderCancelledEvent;
import order.domain.events.OrderCompletedEvent;
import order.domain.events.OrderCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Saga
@Slf4j
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent received for Order ID: " + event.getOrderId() + ". Reducing stock for order items.");

        List<ReduceStockCommand.OrderItem> items = event.getOrderItems().stream()
                .map(orderItemInfo -> new ReduceStockCommand.OrderItem(orderItemInfo.getProductId(), orderItemInfo.getCount()))
                .collect(Collectors.toList());

        // 주문 생성 후 재고 감소 커맨드 전송
        commandGateway.send(new ReduceStockCommand(event.getOrderId(), items));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(StockReducedEvent event) {
        log.info("Stock successfully reduced for Order ID: " + event.getOrderId() + ". Completing the order.");
        // 재고가 성공적으로 줄어들면 주문 완료 커맨드 전송
        commandGateway.send(new CompleteOrderCommand(event.getOrderId ()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(StockSoldOutEvent event) {
        log.info("Stock fail sold out for Order ID: " + event.getOrderId() + ". Cancelling the order.");
        // 재고 부족 시 주문 취소 커맨드 전송
        commandGateway.send(new CancelOrderCommand(event.getOrderId()));
    }

    @EndSaga // saga에 생명주기가 끝났음을 나타냄.
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCompletedEvent event) {
        log.info("Order with ID: " + event.getOrderId() + " has been successfully completed.");
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCancelledEvent event) {
        log.info("Order with ID: " + event.getOrderId() + " has been cancel completed.");
    }
}
