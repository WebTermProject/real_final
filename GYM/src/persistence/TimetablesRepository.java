package persistence;

import domain.BookLists;
import domain.Timetables;

import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;

public class TimetablesRepository extends HttpServlet{
	private static TimetablesRepository instance;
	private static DataSource ds;
	
	public static TimetablesRepository getInstance()
	{
		if(instance==null)	//처음 호출되면 instance는 null
		{
			try 
			{
				Context context=new InitialContext();
				ds=(DataSource) context.lookup("java:comp/env/jdbc/MySQL");
				return instance=new TimetablesRepository();
			}
			catch(NamingException e)
			{
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	//id로 인원 조회
	public int findPersonsById(Long ttId)
	{
		int persons=0;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		String SQL="SELECT PERSONS FROM TIME_TABLES WHERE TIMETABLE_ID=?";
		try
		{
			conn=ds.getConnection();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			pstmt=conn.prepareStatement(SQL);
			pstmt.setLong(1, ttId);
			rs=pstmt.executeQuery();

			while(rs.next()) 
			{
				persons=rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
		
		}
		return persons;
	}
	
	//id로 예약가능상태 조회
	public String findPossibleById(Long ttId)
	{
		String possible=null;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		String SQL="SELECT POSSIBLE FROM TIME_TABLES WHERE TIMETABLE_ID=?";
		try
		{
			conn=ds.getConnection();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			pstmt=conn.prepareStatement(SQL);
			pstmt.setLong(1, ttId);
			rs=pstmt.executeQuery();

			while(rs.next()) 
			{
				possible=rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
		
		}
		return possible;
	}
	
	public ArrayList<Timetables> read()
	{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		ArrayList<Timetables> tts = new ArrayList<>();
	
		try
		{
			conn=ds.getConnection();
			System.out.println(1);
			String SQL="SELECT * FROM TIME_TABLES";
			System.out.println(2);
			stmt=conn.createStatement();
			System.out.println(3);
			rs=stmt.executeQuery(SQL);
			System.out.println(4);
			while(rs.next()) 
			{
				System.out.println(5);
				Timetables dto=new Timetables(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
				System.out.println(6);
				tts.add(dto);
				System.out.println(7);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
		
		}
		System.out.println(8);
		return tts;
	}
	
	
    //id로 날짜, 시간 조회
	//전체조회
	public String[] getTime()
	{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String [] times = null;
		int i = 0;
	
		String SQL="SELECT TIME FROM TIME_TABLES";
		try
		{
			conn=ds.getConnection();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(SQL);
			
			while(rs.next()) 
			{
				times[i++]=rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
		
		}
		return times;
	}
	
	public Timetables timetableInfoById(Long ttId)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Timetables tt=new Timetables();
	
		String SQL="SELECT * FROM TIME_TABLES WHERE TIMETABLE_ID=?";
		try
		{
			conn=ds.getConnection();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			pstmt=conn.prepareStatement(SQL);
			pstmt.setLong(1, ttId);
			rs=pstmt.executeQuery();
			
			while(rs.next()) 
			{
				tt.setTimetable_id(rs.getLong(1));
				tt.setMonth(rs.getString(2));
				tt.setDay(rs.getString(3));
				tt.setTime(rs.getString(4));
				tt.setPersons(rs.getInt(5));
				tt.setPossible(rs.getString(6));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
		
		}
		return tt;
	}
	

    //예약가능 상태 변경(T->F)
	public static void updatePossible(Long ttId)
    {
        PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="UPDATE TIME_TABLES SET POSSIBLE='N' WHERE TIMETABLE_ID=?";

        try
        {
        	conn=ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setLong(1, ttId);
            pstmt.executeUpdate();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs!=null)
                {
                    rs.close();
                }
                if(pstmt!=null)
                {
                    pstmt.close();
                }
                if(conn!=null)
                {
                    conn.close();
                }

            }
            catch (Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
	
	
	public ArrayList<Timetables> select() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board";
		ArrayList<Timetables> articles = new ArrayList<Timetables>();
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Long id = rs.getLong(1);
				String time = rs.getString("time");
				int persons = rs.getInt("persons");
				String possible = rs.getString("possible");
				
				Timetables dto = new Timetables(id, time, persons, possible);
				articles.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}
	
    //id로 시간표 정보 조회
    public static ArrayList<Timetables> timetableInfo(int timeTableId)
    {
        ArrayList<Timetables> timeTableList= new ArrayList<>();

        PreparedStatement pstmt = null;
        Connection conn=null; 
        ResultSet rs=null;

        String SQL="SELECT * FROM TIME_TABLES WHERE ID=?";

        try
        {
            conn= ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setInt(1,timeTableId);

            rs=pstmt.executeQuery();

            while (rs.next())
            {
            	Long id=rs.getLong("id");
            	String time=rs.getString("time");
            	int persons=rs.getInt("persons");
            	String possible=rs.getString("possible");

                Timetables dto=new Timetables(id, time, persons, possible);
                timeTableList.add(dto);
            }

        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();;
        }
        finally
        {
            try
            {
                if(rs!=null)
                {
                    rs.close();
                }
                if(pstmt!=null)
                {
                    pstmt.close();
                }
                if(conn!=null)
                {
                    conn.close();
                }

            }
            catch (Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }
        return timeTableList;
    }


}
