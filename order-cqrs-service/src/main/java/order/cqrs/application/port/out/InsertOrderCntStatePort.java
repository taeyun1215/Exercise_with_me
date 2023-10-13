package order.cqrs.application.port.out;

public interface InsertOrderCntStatePort {

    void InsertOrderCntByAddress(String addressName, int increaserderCnt);

}
