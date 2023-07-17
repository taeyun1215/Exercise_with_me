package stock.adapter.out.persistence;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import stock.application.port.out.LoadStockPort;
import stock.application.port.out.SaveStockPort;
import stock.domain.Stock;

@RequiredArgsConstructor
@PersistenceAdapter
public class StockPersistenceAdapter implements
        SaveStockPort, LoadStockPort {

    private final StockJpaRepo stockJpaRepo;
    private final StockPersistenceMapper stockPersistenceMapper;

    @Override
    public void saveStock(Stock stock) {
        stockJpaRepo.save(stockPersistenceMapper.mapToJpaEntity(stock));
    }

    @Override
    public Stock loadStock(Long productId) {
        return stockPersistenceMapper.mapToDomainEntity(stockJpaRepo.findByProductId(productId));
    }

}
