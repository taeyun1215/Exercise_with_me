package product.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import product.application.port.in.SearchProductUseCase;
import product.application.port.in.query.SearchProductsByQuery;
import product.application.port.out.LoadProductPort;
import product.domain.Product;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class SearchProductService implements SearchProductUseCase {

    private final LoadProductPort loadProductPort;

    @Override
    public List<Product> searchProductByQuery(SearchProductsByQuery query) {
        return null;
    }

}
