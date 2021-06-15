package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.noticeDTO;


public class noticeDAO {
	private static noticeDAO instance;
	private static DataSource ds;
	public noticeDAO() {
		
	}
	public static noticeDAO getInstacne() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
				ds = (DataSource) context.lookup("java:comp/env/jdbc/MySQL");
				System.out.println("db���� ����!");
				return instance = new noticeDAO();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}
					
	// �� ��ȣ�� ������ ��ȸ(�� ���뺸��)
	public ArrayList<noticeDTO> getNotice(int notice_id) {
		
		ArrayList<noticeDTO> freeList = new ArrayList<noticeDTO>();
		
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null;  
        
        noticeDTO free =null;
	        try {

	       	 	String SQL = "SELECT * FROM notice WHERE ID = '"+notice_id+"'" ;
	       	 	 conn = ds.getConnection();
	              pstm = conn.prepareStatement(SQL);
	              rs = pstm.executeQuery();

	          while(rs.next()) {	
	        	  int id = rs.getInt("ID");
	        	  String Title = rs.getString("TITLE");
	        	  String Contents = rs.getString("CONTENTS");
	        	  String writer = rs.getString("writer");
	        	  LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
	        	  	        	  
	        	  free = new noticeDTO(id,Title,Contents,writer,regdate);
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
	
	//��ü �Խù� ����
	public int getAllcount()
    {
		int listcount = 0;
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null; 
        
       try {
    	   
    	   String SQL = "SELECT count(*) FROM notice";
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
	public ArrayList<noticeDTO> selectPage(int start, int pageCnt){
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null; 
		
		String SQL = "SELECT * FROM notice limit ?, ?";
		ArrayList<noticeDTO> v = new ArrayList<noticeDTO>();
		
		try{
    	 	conn = ds.getConnection();
			pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, start);
			pstm.setInt(2, pageCnt);
			rs = pstm.executeQuery();
			while(rs.next()){
				noticeDTO dto = new noticeDTO();
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