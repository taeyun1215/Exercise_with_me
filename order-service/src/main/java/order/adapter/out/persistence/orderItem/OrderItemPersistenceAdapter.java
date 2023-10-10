package order.adapter.out.persistence.orderItem;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import order.application.port.out.LoadOrderItemPort;
import order.application.port.out.SaveOrderItemPort;
import order.domain.OrderItem;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderItemPersistenceAdapter implements
        SaveOrderItemPort, LoadOrderItemPort {

    private final OrderItemJpaRepo orderItemJpaRepo;
    private final OrderItemPersistenceMapper orderItemPersistenceMapper;

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemJpaRepo.save(orderItemPersistenceMapper.mapToJpaEntity(orderItem));
    }

    @Override
    public List<OrderItem> loadOrderItemByOrderId(Long orderId) {
        return orderItemPersistenceMapper.mapToDomainEntities(orderItemJpaRepo.findByOrderId(orderId));
    }
}
