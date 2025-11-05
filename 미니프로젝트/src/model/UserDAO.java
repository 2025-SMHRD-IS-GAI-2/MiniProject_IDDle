package model;

import java.sql.*;

public class UserDAO {
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;

    public void getConn() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:XE"; 
            String user = "campus_25IS_GA2_P1_4"; 
            String password = "smhrd4"; 
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getClose() {
        try {
            if(psmt != null) psmt.close();
            if(conn != null) conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public UserVO registerUser(UserVO user) {
        getConn();
        String sql = "INSERT INTO MEMBERS(id, pw, name, age) VALUES(?, ?, ?, ?)";
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1, user.getId());
            psmt.setString(2, user.getPw());
            psmt.setString(3, user.getName());
            psmt.setInt(4, user.getAge());
            int result = psmt.executeUpdate();
            if(result > 0) {
                return user; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
        }
        return null; 
    }

    public UserVO login(String id, String pw) {
        getConn();
        String sql = "SELECT * FROM MEMBERS WHERE id = ? AND pw = ?";
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1, id);
            psmt.setString(2, pw);
            rs = psmt.executeQuery();
            if(rs.next()) {
                return new UserVO(
                        rs.getString("id"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
        }
        return null; 
    }
}
