package order.application.saga;

import lombok.extern.slf4j.Slf4j;
import order.application.port.in.command.CompleteOrderCommand;
import global.command.ReduceStockCommand;
import global.event.StockReducedEvent;
import global.event.StockSoldOutEvent;
import order.domain.events.OrderCompletedEvent;
import order.domain.events.OrderCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        // 주문 생성 후 재고 감소 커맨드 전송
        for (OrderCreatedEvent.OrderItemInfo orderItemInfo : event.getOrderItems()) {
            // 각 주문 아이템에 대한 재고 감소 커맨드 전송
            commandGateway.send(new ReduceStockCommand(orderItemInfo.getProductId(), orderItemInfo.getCount(), event.getOrderId()));
        }
    }

    @SagaEventHandler(associationProperty = "productId")
    public void handle(StockReducedEvent event) {
        // 재고가 성공적으로 줄어들면 주문 완료 커맨드 전송
        commandGateway.send(new CompleteOrderCommand(event.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "productId")
    public void handle(StockSoldOutEvent event) {
        // 재고 부족 시 주문 취소 커맨드 전송
        commandGateway.send(new CancelOrderCommand(event.getOrderId()));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCompletedEvent event) {
        log.info("Order with ID: " + event.getOrderId() + " has been successfully completed.");
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCancelledEvent event) {
        // 주문이 취소되면 Saga 종료
    }
}
