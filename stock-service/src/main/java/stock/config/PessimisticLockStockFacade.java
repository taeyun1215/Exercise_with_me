package stock.config;

import global.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import stock.application.port.in.ReduceStockUseCase;

@Component
@RequiredArgsConstructor
public class PessimisticLockStockFacade {

    private final ReduceStockUseCase reduceStockUseCase;

    public void reduceStock(OrderItemDto orderItemDto) throws InterruptedException {
        while (true) {
            try {
                reduceStockUseCase.reduceStock(orderItemDto);
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
}
