package service;

import java.util.ArrayList;

import domain.BookLists;
import domain.Timetables;
import persistence.TimetablesRepository;
import persistence.BookListsRepository;

public class TimetablesService {
	private final static TimetablesRepository TTRepository= TimetablesRepository.getInstance();
	
	public TimetablesService() {};
	
	public static ArrayList<Timetables> read()
	{
		ArrayList<Timetables> lists=TTRepository.read();
		
		return lists;
	}
	
	public static int readBookedPersons(Timetables tt)
	{
		BookListsRepository BLRepository=BookListsRepository.getInstance();
		
		int count = BLRepository.getPerson(tt.getTimetable_id());
		
		return count;
	}
	
	//예약가능 여부 확인
	public static void checkPossible(Timetables tt)
	{
		BookListsRepository BLRepository=BookListsRepository.getInstance();
		
		//예약한 인원 조회
		int bookedPersons=BLRepository.getPerson(tt.getTimetable_id());
		
		//시가표 인원 조회
		int possiblePersons=TTRepository.findPersonsById(tt.getTimetable_id());
		
		//비교 후 예약가능여부 바꾸기
		if(bookedPersons==possiblePersons)
		{
			changePossible(tt);
		}
	}
	
	
	//possible 변경
	public static void changePossible (Timetables tt)
	{
		//possible 변경
		TimetablesRepository.updatePossible(tt.getTimetable_id());
	}
}
