package utility;

import java.sql.*;
import java.util.Random;

public class OrderDummyDataGenerator {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/order-db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "taeyun1215";
    private static final String[] ADDRESSES = {"중랑구", "서초구", "강남구"};
    private static final String[] ORDER_STATUS = {"ORDER_CREATED", "ORDER_COMPLETE", "BEING_PREPARED"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 더미 데이터 생성
            generateDummyOrderData(conn);

            // 연결 종료
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void generateDummyOrderData(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO `order` (receiver_name, receiver_phone, receiver_address, user_id, order_status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            int numberOfDummyData = 10000;

            for (int i = 1; i <= numberOfDummyData; i++) {
                pstmt.setString(1, "Receiver" + i);
                pstmt.setString(2, "010-2000-300" + i);
                pstmt.setString(3, ADDRESSES[random.nextInt(ADDRESSES.length)]);
                pstmt.setLong(4, (long) random.nextInt(1000) + 1);
                pstmt.setString(5, ORDER_STATUS[random.nextInt(ORDER_STATUS.length)]);

                pstmt.executeUpdate();
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long orderId = generatedKeys.getLong(1);
                        generateDummyOrderItemData(conn, orderId, random.nextInt(5) + 1);
                    }
                }
            }
        }
    }

    private static void generateDummyOrderItemData(Connection conn, long orderId, int itemCount) throws SQLException {
        String insertQuery = "INSERT INTO order_item (count, order_id, product_id) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            for (int i = 0; i < itemCount; i++) {
                pstmt.setInt(1, random.nextInt(3) + 1);
                pstmt.setLong(2, orderId);
                pstmt.setLong(3, (long) random.nextInt(50) + 1);

                pstmt.executeUpdate();
            }
        }
    }
}
