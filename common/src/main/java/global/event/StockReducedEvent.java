package global.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockReducedEvent {

    private final Long productId;  // 감소된 제품의 ID
    private final Integer count;   // 감소된 수량
    private final Long orderId;

}
