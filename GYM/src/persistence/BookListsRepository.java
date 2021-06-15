package persistence;

import domain.BookLists;
import domain.Timetables;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookListsRepository {
	private static BookListsRepository instance;
	private static DataSource ds;
	private BookListsRepository() {}	//private�� �ٸ� ���ø����̼ǿ��� new�� ���� �Ұ�
	
	public static BookListsRepository getInstance()
	{
		if(instance==null)	//ó�� ȣ��Ǹ� instance�� null
		{
			try 
			{
				Context context=new InitialContext();
				ds=(DataSource) context.lookup("java:comp/env/jdbc/MySQL");
				return instance=new BookListsRepository();
			}
			catch(NamingException e)
			{
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	//������� ���
	public static void insertBookList(BookLists book)
    {
		PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="INSERT INTO BOOK_LISTS (USER_ID, TIMETABLE_ID) VALUES(?, ?)";

        try
        {
        	conn=ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, book.getUserId());
            pstmt.setLong(2, book.getTimetableId());
            
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
	
	//userId�� ������� ��ȸ
	public BookLists findById(String userId)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BookLists booklist=new BookLists();
	
		String SQL="SELECT * FROM BOOK_LISTS WHERE USER_ID=?";
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
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()==false)
			{
				return null;
			}
			while(rs.next()) 
			{
				booklist.setBookListId(rs.getLong(1));
				booklist.setUserId(rs.getString(2));
				booklist.setTimetableId(rs.getLong(3));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
		
		}
		return booklist;
	}
	
    //�������id�� ������� ���� ��ȸ
    public static ArrayList<BookLists> bookLists(int booklistId)
    {
        ArrayList<BookLists> bookList= new ArrayList<>();

        PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="SELECT * FROM BOOK_LISTS WHERE ID=?";

        try
        {
            conn= ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setInt(1,booklistId);

            rs=pstmt.executeQuery();

            while (rs.next())
            {
                BookLists dto=new BookLists(rs.getLong(1),rs.getString(2),rs.getLong(3));
                bookList.add(dto);
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
        return bookList;
    }

    //�ð�ǥ id�� ������� ���� ��ȸ
    public static ArrayList<BookLists> bookListInfo(int timeTableId)
    {
        ArrayList<BookLists> bookList= new ArrayList<>();

        PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="SELECT * FROM BOOK_LISTS WHERE TIMETABLE_ID=?";

        try
        {
            conn= ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setInt(1,timeTableId);

            rs=pstmt.executeQuery();

            while (rs.next())
            {
                BookLists dto=new BookLists(rs.getLong(1),rs.getString(2), rs.getLong(3));
                bookList.add(dto);
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
        return bookList;
    }

    //�ð�ǥ id�� ������ �ο� ���ϱ�
    public static int getPerson(Long timeTableId)
    {
        int bookedPersons=0;

        PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="SELECT COUNT(*) FROM BOOK_LISTS WHERE timetable_id=?";

        try
        {
            conn= ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setLong(1,timeTableId);

            rs=pstmt.executeQuery();

            while (rs.next())
            {
               bookedPersons=rs.getInt(1);
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
        return bookedPersons;
    }

    //�ð�ǥ id�� ������ ȸ�� id ���ϱ�
    /*
    public static ArrayList<UsersDTO>(int timeTableId)
    {
        ArrayList<UsersDTO userList>=new ArrayLists<>;

        PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="SELECT USER_ID FROM book_lists WHERE  TIMETABLE_ID=?";

        try
        {
            conn= connDatabase.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setInt(1,timeTableId);

            rs=pstmt.executeQuery();

            while (rs.next())
            {
               UsersDTO dto=new UsersDTO(rs.getInt(1));
               userList.add(dto);
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
        return userList;
    }
     */
    
    //userId�� ������� Ȯ��
    public static BookLists findByUserId(String userId)
    {
    	BookLists booked=new BookLists();

        PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="SELECT * FROM BOOK_LISTS WHERE USER_ID=?";

        try
        {
            conn= ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1,userId);

            rs=pstmt.executeQuery();

            while (rs.next())
            {
                booked.setBookListId(rs.getLong(1));
                booked.setUserId(rs.getString(2));
                booked.setTimetableId(rs.getLong(3));
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
        return booked;
    }

    //userID�� ������ �ð�ǥ ����
    public static ArrayList<Timetables> bookListInfo(String userId)
    {
        ArrayList<Timetables> timeTableList= new ArrayList<>();

        PreparedStatement pstmt = null;
        Connection conn=null;
        ResultSet rs=null;

        String SQL="SELECT TIMETABLE_ID FROM BOOK_LISTS WHERE USER_ID=?";

        try
        {
            conn= ds.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1,userId);

            rs=pstmt.executeQuery();

            while (rs.next())
            {
                //Timetables dto=new Timetables(rs.getLong(1), rs.getString(2), rs.getInt(3));
               // timeTableList.add(dto);
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