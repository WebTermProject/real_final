package domain;

public class BookLists {
	private Long bookListId;
    private String userId;
    private Long timetableId;
    
    public BookLists() {}

    public BookLists(Long appointmentListId, String userID, Long timetableID)
    {
        this.bookListId=appointmentListId;
        this.userId=userID;
        this.timetableId=timetableID;
    }

    public Long getBookListId() {
        return bookListId;
    }

    public void setBookListId(Long appointmentListId)
    {
        this.bookListId=appointmentListId;
    }

    public Long getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Long timetableID)
    {
        this.timetableId=timetableID;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userID)
    {
        this.userId=userID;
    }
}
