package stock.adapter.in.web;

import global.annotation.LoginUser;
import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.adapter.in.request.AddStockRequest;
import stock.application.port.in.AddStockUseCase;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockAddController {

    private final AddStockUseCase addStockUseCase;

    @PostMapping("/add/{productId}")
    public ResponseEntity<ReturnObject> AddStock(
            HttpServletRequest httpServletRequest,
            @PathVariable("productId") Long productId,
            @RequestBody AddStockRequest addStockRequest
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));
        addStockUseCase.AddStock(productId, addStockRequest);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("상품의 재고를 추가하였습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
