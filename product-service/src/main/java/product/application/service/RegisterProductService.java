package product.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import product.adapter.in.request.RegisterProductRequest;
import product.application.port.in.RegisterProductUseCase;
import product.application.port.out.SaveProductPort;
import product.domain.Product;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;

    @Override
    public void registerProduct(RegisterProductRequest registerProductRequest, Long userId) {
        Product product = registerProductRequest.toEntity(userId);
        saveProductPort.saveProduct(product);
    }
}
