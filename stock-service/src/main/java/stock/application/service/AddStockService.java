package stock.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import stock.adapter.in.request.AddStockRequest;
import stock.application.port.in.AddStockUseCase;
import stock.application.port.out.SaveStockPort;
import stock.domain.Stock;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class AddStockService implements AddStockUseCase {

    private final SaveStockPort saveStockPort;

    @Override
    public void AddStock(Long productId, AddStockRequest addStockRequest) {
        Stock stock = addStockRequest.toEntity(productId);
        saveStockPort.saveStock(stock);
    }
}
