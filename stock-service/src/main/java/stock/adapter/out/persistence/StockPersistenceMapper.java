package stock.adapter.out.persistence;

import org.springframework.stereotype.Component;
import stock.domain.Stock;

@Component
public class StockPersistenceMapper {

    public Stock mapToDomainEntity(StockJpaEntity stockJpaEntity) {
        return Stock.builder()
                .stockId(stockJpaEntity.getId())
                .productId(stockJpaEntity.getProductId())
                .quantity(stockJpaEntity.getQuantity())
                .build();
    }

    public StockJpaEntity mapToJpaEntity(Stock stock) {
        return stock.toJpaEntity();
    }

}
