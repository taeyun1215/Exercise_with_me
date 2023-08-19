package global.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockSoldOutEvent {

    private Long productId;  // 품절된 제품의 ID

}
