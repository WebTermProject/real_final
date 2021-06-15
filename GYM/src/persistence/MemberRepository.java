package persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.sql.Connection;

public class MemberRepository {
	private static MemberRepository instance;
	private static DataSource ds;
	private MemberRepository() {
		
	}
	public static MemberRepository getInstacne() {
		if(instance==null) {
			try {
				//Context context = new InitialContext();
				
				Hashtable table = new Hashtable();

				table.put("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");

				ds = (DataSource)new InitialContext(table).lookup("java:comp/env/jdbc/MySQL");
				//ds = (DataSource) context.lookup("java:comp/env/jdbc/MySQL");
				return instance = new MemberRepository();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}
	
	 //회원가입
	  public static int memberJoin(Member dto) {
		  int re = 0;
			Connection conn = null; 
	        PreparedStatement pstm = null; 
	        ResultSet rs = null;  

	        try {
	            // INSERT INTO 테이블명(필드명,,) VALUES('추가할값',,,)
	            String sql = "INSERT INTO MEMBER VALUES (?,?,?,?)";
	            conn = ds.getConnection();
	            pstm = conn.prepareStatement(sql);
	           pstm.setString(1, dto.getMemberID());
	            pstm.setString(2, dto.getPassword());
	           pstm.setString(3,dto.getName());
	            pstm.setString(4, dto.getPhone());
	            
	            pstm.executeUpdate();
	            re = 1;
	        } catch (SQLException sqle) {
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
	        return re;
	  }
	  
	  
	//회원 아이디 , 비밀번호 로그인 체크
	 public int userCheck(String userID, String userPW)throws Exception{
		 Connection conn= null;
		 PreparedStatement pstm = null;
		 ResultSet rs =null;
		
	     String dbpasswd="";
	     int x = -1;
  
	     try{	
	    	 	conn = ds.getConnection();
	          	String sql ="select password from MEMBER where memberID = ?";
	          
	          	pstm =conn.prepareStatement(sql);
	          	pstm.setString(1, userID);
	          	rs=pstm.executeQuery();
	           
	          	if(rs.next())
	          	{
	                 dbpasswd =rs.getString("password");
	            
	               if(dbpasswd.equals(userPW))
	                   x=1; //로그인 성공
	               else
	                   x=0; //비밀번호 틀림
	            }
	          	else
	            	 x=-1; //해당 아이디 없음
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
     return x;
	     
	 }
}
