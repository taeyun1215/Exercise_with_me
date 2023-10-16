package utility.init;

import java.util.List;

public class CreateOrderCommand {

    private Long orderId;
    private Long userId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private List<OrderCreatedEvent.OrderItemInfo> orderItemInfos;

    public CreateOrderCommand(Long orderId, Long userId, String receiverName, String receiverPhone,
                              String receiverAddress, List<OrderCreatedEvent.OrderItemInfo> orderItemInfos) {
        this.orderId = orderId;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.orderItemInfos = orderItemInfos;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<OrderCreatedEvent.OrderItemInfo> getOrderItemInfos() {
        return orderItemInfos;
    }

    public void setOrderItemInfos(List<OrderCreatedEvent.OrderItemInfo> orderItemInfos) {
        this.orderItemInfos = orderItemInfos;
    }

    @Override
    public String toString() {
        return "CreateOrderCommand{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", orderItemInfos=" + orderItemInfos +
                '}';
    }
}
