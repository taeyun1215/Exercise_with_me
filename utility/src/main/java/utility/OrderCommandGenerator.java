package utility;

import order.cqrs.domain.command.CreateOrderCommand;
import order.cqrs.domain.event.OrderCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderCommandGenerator {

    private static CommandGateway commandGateway;
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Command generation and sending logic should be here
        for (int i = 1; i <= 1000; i++) {
            List<OrderCreatedEvent.OrderItemInfo> orderItemInfos = generateRandomOrderItemInfos();

            // Constructing a command with example data
            CreateOrderCommand command = CreateOrderCommand.builder()
                    .orderId((long) i)
                    .userId(100L + i)
                    .receiverName("Receiver " + i)
                    .receiverPhone("010-1234-56" + i)
                    .receiverAddress("Some Address " + i)
                    .orderItemInfos(orderItemInfos)
                    .build();

            // The actual command sending logic should be here
             commandGateway.sendAndWait(command);
        }
    }

    private static List<OrderCreatedEvent.OrderItemInfo> generateRandomOrderItemInfos() {
        int numOfItems = random.nextInt(5) + 1; // Random number between 1 and 5
        List<OrderCreatedEvent.OrderItemInfo> orderItemInfos = new ArrayList<>();

        for (int i = 0; i < numOfItems; i++) {
            long productId = random.nextInt(50) + 1; // Assuming product IDs are between 1 and 50
            int quantity = random.nextInt(5) + 1; // Random quantity between 1 and 5
            orderItemInfos.add(new OrderCreatedEvent.OrderItemInfo(productId, quantity));
        }

        return orderItemInfos;
    }
}
