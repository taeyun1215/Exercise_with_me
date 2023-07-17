package product.application.port.in;


import product.adapter.in.request.RegisterProductRequest;

public interface RegisterProductUseCase {

    void registerProduct(RegisterProductRequest registerProductRequest, Long userId);

}
