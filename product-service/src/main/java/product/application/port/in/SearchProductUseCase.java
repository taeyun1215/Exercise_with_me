package product.application.port.in;

import product.application.port.in.query.SearchProductsByQuery;
import product.domain.Product;

import java.util.List;

public interface SearchProductUseCase {

    List<Product> searchProductByQuery(SearchProductsByQuery query);

}
