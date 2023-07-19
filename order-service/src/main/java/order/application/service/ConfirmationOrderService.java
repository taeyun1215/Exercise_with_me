package order.application.service;

import global.annotation.UseCase;
import global.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.application.port.in.ConfirmationOrderUseCase;
import order.application.port.out.LoadOrderItemPort;
import order.application.port.out.LoadOrderPort;
import order.domain.Order;
import order.domain.OrderConfirmation;
import order.domain.OrderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class ConfirmationOrderService implements ConfirmationOrderUseCase {

    private final LoadOrderPort loadOrderPort;
    private final LoadOrderItemPort loadOrderItemPort;

    private final RestTemplate restTemplate;

    @Override
    public List<OrderConfirmation> confirmationOrder(Long userId) {
        List<OrderConfirmation> orderConfirmations = new ArrayList<>();
        List<OrderConfirmation.OrderItemProduct> orderItemProducts = new ArrayList<>();
        List<Order> orders = loadOrderPort.loadOrderByUser(userId);
        for (Order order : orders) {
            List<OrderItem> orderItems = loadOrderItemPort.loadOrderItemByOrderId(order.getOrderId());
            for (OrderItem orderItem : orderItems) {
                String url = "http://43.201.144.113:8000/product-service/products/" + orderItem.getProductId();
                ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class); // 동기로 처리하기 위함.
                ProductDto productDto = response.getBody();

                OrderConfirmation.OrderItemProduct orderItemProduct = new OrderConfirmation.OrderItemProduct(productDto.getName(), orderItem.getCount() * productDto.getPrice());
                orderItemProducts.add(orderItemProduct);
            }
            orderConfirmations.add(new OrderConfirmation(order, orderItemProducts));
        }

        return orderConfirmations;
    }

}
