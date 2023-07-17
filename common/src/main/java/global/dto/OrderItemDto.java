package global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long orderItemId;
    private int count;
    private Long orderId;
    private Long productId;

}