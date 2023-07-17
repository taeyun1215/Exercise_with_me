package product.application.port.in;

import product.domain.Product;

public interface ViewProductUseCase {

    Product viewProduct(Long productId);

}
