package stock.config;

import global.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import stock.application.port.in.ReduceStockUseCase;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {

    private final RedissonClient redissonClient;
    private final ReduceStockUseCase reduceStockUseCase;
    private final PlatformTransactionManager platformTransactionManager;

    public void reduceStock(OrderItemDto orderItemDto) throws InterruptedException {

        RLock lock = redissonClient.getLock(orderItemDto.getProductId().toString());
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("lock 획득에 실패하였습니다");
                return;
            }

            TransactionStatus status = platformTransactionManager.getTransaction(null);
            reduceStockUseCase.reduceStock(orderItemDto);
            platformTransactionManager.commit(status);
        } finally {
            lock.unlock();
        }
    }
}
