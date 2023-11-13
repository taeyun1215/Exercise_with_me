package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class ProductDummyDataGenerator {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/product-db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "taeyun1215";

    private static final String[] STATUS = {"BEING_PREPARED", "AVAILABLE", "UNAVAILABLE"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 더미 데이터 생성
            generateDummyProductData(conn);

            // 연결 종료
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void generateDummyProductData(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO product (name, price, status, description, category_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            int numberOfDummyData = 100000;

            for (int i = 1; i <= numberOfDummyData; i++) {
                pstmt.setString(1, "Product" + i);
                pstmt.setInt(2, (random.nextInt(50) + 1) * 1000);
                pstmt.setString(3, STATUS[random.nextInt(STATUS.length)]);
                pstmt.setString(4,"Description" + i);
                pstmt.setLong(5, random.nextInt(10) + 1);
                pstmt.setLong(6, random.nextInt(50) + 1);

                pstmt.executeUpdate();
            }
        }
    }
}
