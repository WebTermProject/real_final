package domain;

public class Timetables {
	Long timetable_id;
    String month;
    String day;
    String time;
    int persons;
    String possible;
    
    public Timetables() {}
    
    public Timetables(Long id, String month, String day, String time, int person, String possible)
    {
    	this.timetable_id=id;
    	this.month=month;
    	this.day=day;
    	this.time=time;
    	this.persons=person;
    	this.possible=possible;
    }

    public Timetables(Long timetable_id,String month, int persons, String possible)
    {
        this.timetable_id=timetable_id;
        this.month=month;
        this.persons=persons;
        this.possible=possible;
    }

    public Long getTimetable_id() {
		return timetable_id;
	}

	public void setTimetable_id(Long timetable_id) {
		this.timetable_id = timetable_id;
	}

    public String getMonth() {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month=month;
    }
    
    public String getDay()
    {
    	return day;
    	
    }
    
    public void setDay(String day)
    {
    	this.day=day;
    }

    public String getTime()
    {
    	return time;
    }
    
    public void setTime(String time)
    {
    	this.time=time;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons)
    {
        this.persons=persons;
    }
   
    public String getPossible()
    {
    	return possible;
    }
    
    public void setPossible(String possible)
    {
    	this.possible=possible;
    }
}
