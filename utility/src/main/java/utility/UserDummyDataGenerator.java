package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class UserDummyDataGenerator {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/user-db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "taeyun1215";
    private static final String[] ADDRESSES = {"중랑구", "서초구", "강남구"};
    private static final String[] ROLES = {"USER", "ADMIN", "SOCIAL"};

    public static void main(String[] args) {
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 더미 데이터 생성
            generateDummyUserData(conn);

            // 연결 종료
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void generateDummyUserData(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO user (username, password, nickname, phone, email, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Random random = new Random();

        // 데이터 생성을 위한 PreparedStatement 준비
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            // 더미 데이터 개수 (여기서는 10개로 가정)
            int numberOfDummyData = 1000;

            for (int i = 1; i <= numberOfDummyData; i++) {
                pstmt.setString(1, "user" + i); // username
                pstmt.setString(2, "pass" + i); // password
                pstmt.setString(3, "nickname" + i); // nickname
                pstmt.setString(4, "010-1000-100" + i); // phone
                pstmt.setString(5, "user" + i + "@example.com"); // email
                pstmt.setString(6, ADDRESSES[random.nextInt(ADDRESSES.length)]); // address (랜덤하게 선택)
                pstmt.setString(7, ROLES[random.nextInt(ROLES.length)]);
                pstmt.executeUpdate();
            }
        }
    }
}
