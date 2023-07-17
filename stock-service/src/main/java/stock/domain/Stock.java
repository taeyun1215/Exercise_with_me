package stock.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import stock.adapter.out.persistence.StockJpaEntity;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock {

    private Long stockId;

    private Long productId;
    private int quantity;

    public StockJpaEntity toJpaEntity() {
        return StockJpaEntity.builder()
                .id(stockId)
                .productId(productId)
                .quantity(quantity)
                .build();
    }

    public void reduceStock(int quantity) {
        this.quantity -= quantity;
    }

}
