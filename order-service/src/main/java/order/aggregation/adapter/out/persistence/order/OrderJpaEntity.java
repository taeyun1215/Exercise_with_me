package order.aggregation.adapter.out.persistence.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import order.domain.constant.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"order\"")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
