package stock.adapter.in.commandhandler;

import global.command.ReduceStockCommand;
import global.event.StockSoldOutEvent;
import global.event.StockReducedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import stock.application.port.in.ReduceStockHandlerUseCase;

@Slf4j
@Component
@AllArgsConstructor
@Transactional
public class StockCommandHandler {

    @Autowired
    private final EventGateway eventGateway;

    private final ReduceStockHandlerUseCase reduceStockHandlerUseCase;

    @CommandHandler
    public void handle(ReduceStockCommand command) {
        try {
            log.info("여기까지 옴1.");

            boolean allSuccess = true;
            for (ReduceStockCommand.OrderItem orderItem : command.getItems()) {
                try {
                    // 재고 감소 로직
                    reduceStockHandlerUseCase.reduceStock(orderItem);
                } catch (RuntimeException e) {
                    allSuccess = false;
                    log.info("재고 감소 실패: " + orderItem.getProductId());
                    // 다른 처리 로직 (예: 실패한 상품에 대한 정보 저장)을 여기에 추가할 수 있습니다.
                    break;
                }
            }

            if (allSuccess) {
                // 재고 감소에 성공하면 StockReducedEvent 발행
                eventGateway.publish(new StockReducedEvent(command.getOrderId()));
            } else {
                // 재고 부족시 StockSoldOutEvent 발행
                eventGateway.publish(new StockSoldOutEvent(command.getOrderId()));
            }
        } catch (RuntimeException e) {
            log.error("Unexpected error", e);
        }
    }
}