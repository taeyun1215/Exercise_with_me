package product.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import product.application.port.in.ViewProductUseCase;
import product.application.port.out.LoadProductPort;
import product.domain.Product;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class ViewProductService implements ViewProductUseCase {

    private final LoadProductPort loadProductPort;

    @Override
    public Product viewProduct(Long productId) {
        return loadProductPort.loadProduct(productId);
    }

}
