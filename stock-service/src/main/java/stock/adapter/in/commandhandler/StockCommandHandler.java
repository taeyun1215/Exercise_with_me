package stock.adapter.in.commandhandler;

import global.command.ReduceStockCommand;
import global.event.StockSoldOutEvent;
import global.event.StockReducedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stock.application.port.in.ReduceStockHandlerUseCase;

@Component
@AllArgsConstructor
public class StockCommandHandler {

    @Autowired
    private EventGateway eventGateway;

    private final ReduceStockHandlerUseCase reduceStockHandlerUseCase;

    @CommandHandler
    public void handle(ReduceStockCommand command) {
        try {
            // 재고 감소 로직
            reduceStockHandlerUseCase.reduceStock(command);

            // 재고 감소에 성공하면 StockReducedEvent 발행
            eventGateway.publish(new StockReducedEvent(command.getProductId(), command.getCount(), command.getOrderId()));

        } catch (RuntimeException e) {
            // 재고 부족시 StockSoldOutEvent 발행
            eventGateway.publish(new StockSoldOutEvent(command.getProductId()));
        }
    }
}