package stock.application.port.out;

import stock.domain.Stock;

public interface LoadStockPort {

    Stock loadStock(Long productId);

}
