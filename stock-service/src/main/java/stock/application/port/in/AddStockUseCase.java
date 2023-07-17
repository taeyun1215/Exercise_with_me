package stock.application.port.in;

import stock.adapter.in.request.AddStockRequest;

public interface AddStockUseCase {

    void AddStock(Long productId, AddStockRequest addStockRequest);

}
