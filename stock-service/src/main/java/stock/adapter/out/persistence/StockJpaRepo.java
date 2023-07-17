package stock.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface StockJpaRepo extends JpaRepository<StockJpaEntity, Long> {
    StockJpaEntity findByProductId(Long productId);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from StockJpaEntity s where s.productId = :productId")
    StockJpaEntity findByProductIdWithPessimisticLock(Long productId);

    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from StockJpaEntity s where s.productId = :productId")
    StockJpaEntity findByProductIdWithOptimisticLock(Long productId);
}
