package stock.application.port.in;

import global.dto.OrderItemDto;

public interface IncreaseStockUseCase {

    void increaseStock(OrderItemDto orderItemDto);

}
