package product.application.port.out;

import product.application.port.in.query.SearchProductsByQuery;
import product.domain.Product;

import java.util.List;

public interface LoadProductPort {

    Product loadProduct(Long productId);

    List<Product> loadProductByQuery(SearchProductsByQuery query);

}
