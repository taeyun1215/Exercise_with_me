package product.application.port.in.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductsByQuery {

    @NotBlank(message = "검색 쿼리는 비어 있을 수 없습니다.")
    @Size(min = 3, max = 50, message = "검색 쿼리는 최소 3자 이상, 최대 50자 이하이어야 합니다.")
    private String query;

}
