package stock.application.service;

import global.annotation.UseCase;
import global.command.ReduceStockCommand;
import global.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import stock.adapter.out.persistence.StockJpaRepo;
import stock.adapter.out.persistence.StockPersistenceMapper;
import stock.application.port.in.ReduceStockHandlerUseCase;
import stock.application.port.in.ReduceStockUseCase;
import stock.application.port.out.LoadStockPort;
import stock.application.port.out.SaveStockPort;
import stock.domain.Stock;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class ReduceStockService implements ReduceStockUseCase, ReduceStockHandlerUseCase {

    private final LoadStockPort loadStockPort;
    private final SaveStockPort saveStockPort;

    private final StockJpaRepo stockJpaRepo;
    private final StockPersistenceMapper stockPersistenceMapper;

    @Override
    public void reduceStock(OrderItemDto orderItemDto) {
        Stock stock = loadStockPort.loadStock(orderItemDto.getProductId());
//        Stock stock = stockPersistenceMapper.mapToDomainEntity(stockJpaRepo.findByProductIdWithOptimisticLock(orderItem.getProductId()));
//        Stock stock = stockPersistenceMapper.mapToDomainEntity(stockJpaRepo.findByProductIdWithPessimisticLock(orderItem.getProductId()));

        if (stock == null) {
            throw new EntityNotFoundException("상품이 없습니다.");
        }
        if (stock.getQuantity() < orderItemDto.getCount()) {
            throw new RuntimeException("수량이 없습니다.");
        } else {
            stock.reduceStock(orderItemDto.getCount());
            saveStockPort.saveStock(stock);
        }
    }

    @Override
    public void reduceStock(ReduceStockCommand.OrderItem orderItem) {
        Stock stock = loadStockPort.loadStock(orderItem.getProductId());

        if (stock == null) {
            throw new EntityNotFoundException("상품이 없습니다.");
        }
        if (stock.getQuantity() < orderItem.getCount()) {
            throw new RuntimeException("수량이 없습니다.");
        } else {
            stock.reduceStock(orderItem.getCount());
            saveStockPort.saveStock(stock);
            log.info("재고 감소 성공: " + orderItem.getProductId());
        }
    }
}
