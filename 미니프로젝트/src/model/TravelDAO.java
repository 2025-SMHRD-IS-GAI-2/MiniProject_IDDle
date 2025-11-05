package model;

import model.TravelVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelDAO {
   Connection conn = null;
   PreparedStatement psmt = null;
   ResultSet rs = null;

   private void getConn() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         String URL = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:XE";
         String USER = "campus_25IS_GA2_P1_4";
         String PASSWORD = "smhrd4";

         conn = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void getClose() {
      try {
         if (rs != null)
            rs.close();
         if (psmt != null)
            psmt.close();
         if (conn != null)
            conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public List<TravelVO> getTravelInfo(String location, String theme) {
      List<TravelVO> list = new ArrayList<>();

      try {
         getConn();

         String sql = "SELECT THEME, LOCATION_NAME, STORE_NAME, ADDRESS, DESCRIPTION, RECOMMEND_MENU "
               + "FROM PLACES WHERE LOCATION_NAME = ? AND THEME = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, location);
         psmt.setString(2, theme);
         rs = psmt.executeQuery();

         while (rs.next()) {
            TravelVO tvo = new TravelVO(rs.getString("THEME"), rs.getString("LOCATION_NAME"),
                  rs.getString("STORE_NAME"), rs.getString("ADDRESS"), rs.getString("DESCRIPTION"),
                  rs.getString("RECOMMEND_MENU"));
            list.add(tvo);
         }

      } catch (Exception e) {
         System.out.println("⚠ 여행지 정보를 불러오는 중 오류 발생");
         e.printStackTrace();
      } finally {
         getClose();
      }

      return list;
   }
   

}
