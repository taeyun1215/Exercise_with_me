package stock.application.port.in;

import global.command.ReduceStockCommand;

public interface ReduceStockHandlerUseCase {

    void reduceStock(ReduceStockCommand.OrderItem orderItem);

}
