package order.cqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_cnt_sum")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCntSumByAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCntSumId;

    private String address;

    private Long orderCnt;

    public Long getOrderCnt() {
        return this.orderCnt;
    }
}
