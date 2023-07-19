package global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

    private Long productId;
    private String name;
    private int price;
    private String description;

    private Long categoryId;
    private Long userId;

}
