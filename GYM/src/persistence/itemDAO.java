package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.itemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.itemDTO;

public class itemDAO {
   private static itemDAO instance;
   private static DataSource ds;
   public itemDAO() {
      
   }
   public static itemDAO getInstacne() {
      if(instance==null) {
         try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/MySQL");
            System.out.println("db연결 성공!");
            return instance = new itemDAO();
         } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return instance;      
   }
    //주문하기
     public static void itemOrder(itemDTO dto) {
      
         Connection conn = null; 
           PreparedStatement pstm = null; 
           ResultSet rs = null;  

           try {
               // INSERT INTO 테이블명(필드명,,) VALUES('추가할값',,,)
               String sql = "INSERT INTO item(memberID, chickenCnt, waterCnt) VALUES (?,?,?)";
               conn = ds.getConnection();
               pstm = conn.prepareStatement(sql);
               pstm.setString(1, dto.getMemberID());
               pstm.setInt(2, dto.getChickenCnt());
               pstm.setInt(3, dto.getWaterCnt());
               
               pstm.executeUpdate();
     
           } catch (SQLException sqle) {
              System.out.println("SELECT문에서 예외 발생");
               sqle.printStackTrace();      
           }finally{
               // DB 연결을 종료한다.
               try{
                   if ( rs != null ){rs.close();}   
                   if ( pstm  != null ){pstm .close();}   
                   if ( conn != null ){conn.close(); }
               }catch(Exception e){
                   throw new RuntimeException(e.getMessage());
             }  
           }
     }
}