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
            System.out.println("db���� ����!");
            return instance = new itemDAO();
         } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return instance;      
   }
    //�ֹ��ϱ�
     public static void itemOrder(itemDTO dto) {
      
         Connection conn = null; 
           PreparedStatement pstm = null; 
           ResultSet rs = null;  

           try {
               // INSERT INTO ���̺���(�ʵ��,,) VALUES('�߰��Ұ�',,,)
               String sql = "INSERT INTO item(memberID, chickenCnt, waterCnt) VALUES (?,?,?)";
               conn = ds.getConnection();
               pstm = conn.prepareStatement(sql);
               pstm.setString(1, dto.getMemberID());
               pstm.setInt(2, dto.getChickenCnt());
               pstm.setInt(3, dto.getWaterCnt());
               
               pstm.executeUpdate();
     
           } catch (SQLException sqle) {
              System.out.println("SELECT������ ���� �߻�");
               sqle.printStackTrace();      
           }finally{
               // DB ������ �����Ѵ�.
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