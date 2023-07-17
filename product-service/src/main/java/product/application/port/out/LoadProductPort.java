package product.application.port.out;

import product.domain.Product;

public interface LoadProductPort {

    Product loadProduct(Long productId);

}
