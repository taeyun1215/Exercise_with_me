package stock.application.port.in;

import global.dto.OrderItemDto;

public interface ReduceStockUseCase {

    void reduceStock(OrderItemDto orderItemDto);

}
