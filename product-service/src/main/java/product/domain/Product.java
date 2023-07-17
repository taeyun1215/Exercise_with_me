package product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import product.adapter.out.persistence.ProductJpaEntity;
import product.domain.constant.Status;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    private Long productId;
    private String name;
    private int price;
    private Status status;
    private String description;

    private Long categoryId;
    private Long userId;

    public ProductJpaEntity toJpaEntity() {
        return ProductJpaEntity.builder()
                .id(productId)
                .name(name)
                .price(price)
                .status(status)
                .description(description)
                .categoryId(categoryId)
                .userId(userId)
                .build();
    }
}
