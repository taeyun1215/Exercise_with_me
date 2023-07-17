package stock.application.port.out;

import stock.domain.Stock;

public interface SaveStockPort {

    void saveStock(Stock stock);

}
