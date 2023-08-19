package global.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockReducedEvent {

    private Long productId;  // 감소된 제품의 ID
    private Integer count;   // 감소된 수량
    private Long orderId;

}
