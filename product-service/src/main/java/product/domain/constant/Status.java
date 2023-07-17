package product.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    BEING_PREPARED("상품 준비중"),
    AVAILABLE("상품 구매 가능"),
    UNAVAILABLE("상품 구매 불가능");

    private final String value;
}
