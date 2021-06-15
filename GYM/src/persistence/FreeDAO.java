package persistence;

import domain.FreeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FreeDAO {

	private static FreeDAO instance;
	private static DataSource ds;
	public FreeDAO() {
		
	}
	public static FreeDAO getInstacne() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
				ds = (DataSource) context.lookup("java:comp/env/jdbc/MySQL");
				System.out.println("db���� ����!");
				return instance = new FreeDAO();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}
					
	// �� ��ȣ�� ������ ��ȸ(�� ���뺸��)
	public ArrayList<FreeDTO> getNotice(int free_id) {
		
		ArrayList<FreeDTO> freeList = new ArrayList<FreeDTO>();
		
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null;  
        
        FreeDTO free =null;
	        try {

	       	 	String SQL = "SELECT * FROM FREEBOARD WHERE ID = '"+free_id+"'" ;
	       	 	 conn = ds.getConnection();
	              pstm = conn.prepareStatement(SQL);
	              rs = pstm.executeQuery();

	          while(rs.next()) {	
	        	  int id = rs.getInt("ID");
	        	  String Title = rs.getString("TITLE");
	        	  String Contents = rs.getString("CONTENTS");
	        	  String writer = rs.getString("writer");
	        	  LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
	        	  	        	  
	        	  free = new FreeDTO(id,Title,Contents,writer,regdate);
	        	  freeList.add(free);
	        	  
	              }
	            
	        } catch (SQLException sqle) {
	            System.out.println("SELECT������ ���� �߻�");
	            sqle.printStackTrace();
	            
	        }finally{
	            try{
	                if ( rs != null ){rs.close();}   
	                if ( pstm  != null ){pstm .close();}   
	                if ( conn != null ){conn.close(); }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	            
	        }
	    return freeList;
	}
	
	//�� ���
	public void insertFree(FreeDTO dto)
    {
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null; 
        
       try {
    	   
    	   String SQL = "INSERT INTO FREEBOARD(TITLE, CONTENTS, writer, REGDATE) VALUES(?, ?, ?, now())";
     	 	 conn = ds.getConnection();
             pstm = conn.prepareStatement(SQL);

             pstm.setString(1, dto.getTitle());
             pstm.setString(2, dto.getContents());
             pstm.setString(3, dto.getWriter());
             pstm.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("SELECT������ ���� �߻�");
            sqle.printStackTrace();
            
        }finally{
            try{
                if ( pstm  != null ){pstm .close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            }
        }
	
	//��ü �Խù� ����
	public int getAllcount()
    {
		int listcount = 0;
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null; 
        
       try {
    	   
    	   String SQL = "SELECT count(*) FROM FREEBOARD";
     	 	 conn = ds.getConnection();
             pstm = conn.prepareStatement(SQL);
             rs = pstm.executeQuery();
             if(rs.next()) {
            	 listcount = rs.getInt(1);
             }
        } catch (SQLException sqle) {
            System.out.println("SELECT������ ���� �߻�");
            sqle.printStackTrace();
        }finally{
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm  != null ){pstm .close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            }
       return listcount;
        }
	
	//�������� ���� ����Ʈ ���
	public ArrayList<FreeDTO> selectPage(int start, int pageCnt){
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null; 
		
		String SQL = "SELECT * FROM FREEBOARD limit ?, ?";
		ArrayList<FreeDTO> v = new ArrayList<FreeDTO>();
		
		try{
    	 	conn = ds.getConnection();
			pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, start);
			pstm.setInt(2, pageCnt);
			rs = pstm.executeQuery();
			while(rs.next()){
				FreeDTO dto = new FreeDTO();
				dto.setId(rs.getInt("id"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setRegdate( rs.getTimestamp("regdate").toLocalDateTime());
				v.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally{
			try { 
				if(rs != null){
					rs.close();
				}
				if(pstm != null){
					pstm.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return v;
	}

}
    