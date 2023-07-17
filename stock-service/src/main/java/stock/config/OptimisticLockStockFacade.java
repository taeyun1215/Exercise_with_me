package stock.config;

import global.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import stock.application.port.in.ReduceStockUseCase;

@Component
@RequiredArgsConstructor
public class OptimisticLockStockFacade {

    private final ReduceStockUseCase reduceStockUseCase;
    private final PlatformTransactionManager platformTransactionManager;

    public void reduceStock(OrderItemDto orderItemDto) throws InterruptedException {
        try {
            TransactionStatus status = platformTransactionManager.getTransaction(null);
            reduceStockUseCase.reduceStock(orderItemDto);
            platformTransactionManager.commit(status);
        } catch (Exception e) {
            Thread.sleep(50);
        }
    }
}
