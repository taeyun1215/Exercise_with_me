package stock.application.service;

import global.annotation.UseCase;
import global.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import stock.application.port.in.IncreaseStockUseCase;
import stock.application.port.out.LoadStockPort;
import stock.application.port.out.SaveStockPort;
import stock.domain.Stock;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class IncreaseStockService implements IncreaseStockUseCase {

    private final LoadStockPort loadStockPort;
    private final SaveStockPort saveStockPort;

    @Override
    public void increaseStock(OrderItemDto orderItemDto) {
        Stock stock = loadStockPort.loadStock(orderItemDto.getProductId());
//        Stock stock = stockPersistenceMapper.mapToDomainEntity(stockJpaRepo.findByProductIdWithOptimisticLock(orderItem.getProductId()));
//        Stock stock = stockPersistenceMapper.mapToDomainEntity(stockJpaRepo.findByProductIdWithPessimisticLock(orderItem.getProductId()));

        if (stock == null) {
            throw new EntityNotFoundException("상품이 없습니다.");
        }

        stock.increaseStock(orderItemDto.getCount());
        saveStockPort.saveStock(stock);
    }

}
