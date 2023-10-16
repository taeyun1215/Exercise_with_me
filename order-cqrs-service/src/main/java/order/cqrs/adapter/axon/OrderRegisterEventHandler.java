package order.cqrs.adapter.axon;

import global.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import order.cqrs.application.port.out.GetUserPort;
import order.cqrs.application.port.out.InsertOrderCntStatePort;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderRegisterEventHandler {

    @EventHandler
    public void handler(
            OrderCreatedEvent event,
            GetUserPort getUserPort,
            InsertOrderCntStatePort insertOrderCntStatePort
    ) {
        // 고객의 주소 정보(기존에는 고객 정보를 다 가져와서 청크 크기를 지정해서 Query를 날렸음.)
        String address = getUserPort.getAddressByUserId(event.getUserId());

        // RDB Insert & Update
        insertOrderCntStatePort.InsertOrderCntByAddress(address, event.getOrderItems().size());
    }
}
