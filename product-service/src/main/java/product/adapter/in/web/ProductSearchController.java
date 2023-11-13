package product.adapter.in.web;

import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import product.application.port.in.SearchProductUseCase;
import product.application.port.in.query.SearchProductsByQuery;
import product.domain.Product;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductSearchController {

    private final SearchProductUseCase searchProductUseCase;

    @GetMapping("/search")
    public ResponseEntity<ReturnObject> searchProduct(
            @RequestParam("searchQuery") String searchQuery
    ) {
        SearchProductsByQuery query = SearchProductsByQuery.builder()
                .productName(searchQuery)
                .build();

        List<Product> products = searchProductUseCase.searchProductByQuery(query);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(products)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}