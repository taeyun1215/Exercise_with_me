package order.aggregation.adapter.out.persistence.order;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import order.aggregation.application.port.out.LoadOrderPort;
import order.aggregation.application.port.out.SaveOrderPort;
import order.domain.Order;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements
        SaveOrderPort, LoadOrderPort {

    private final OrderJpaRepo orderJpaRepo;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order saveOrder(Order order) {
        return orderPersistenceMapper.mapToDomainEntity(orderJpaRepo.save(orderPersistenceMapper.mapToJpaEntity(order)));
    }

    @Override
    public Order loadOrder(Long orderId) {
        return orderPersistenceMapper.mapToDomainEntity(orderJpaRepo.findById(orderId).get());
    }

    @Override
    public List<Order> loadOrderByUser(Long userId) {
        return orderPersistenceMapper.mapToDomainEntities(orderJpaRepo.findByUserId(userId));
    }
}
