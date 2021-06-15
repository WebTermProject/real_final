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
	
	//���డ�� ���� Ȯ��
	public static void checkPossible(Timetables tt)
	{
		BookListsRepository BLRepository=BookListsRepository.getInstance();
		
		//������ �ο� ��ȸ
		int bookedPersons=BLRepository.getPerson(tt.getTimetable_id());
		
		//�ð�ǥ �ο� ��ȸ
		int possiblePersons=TTRepository.findPersonsById(tt.getTimetable_id());
		
		//�� �� ���డ�ɿ��� �ٲٱ�
		if(bookedPersons==possiblePersons)
		{
			changePossible(tt);
		}
	}
	
	
	//possible ����
	public static void changePossible (Timetables tt)
	{
		//possible ����
		TimetablesRepository.updatePossible(tt.getTimetable_id());
	}
}