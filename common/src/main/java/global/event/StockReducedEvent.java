package global.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockReducedEvent {

    private final Long orderId;

}
