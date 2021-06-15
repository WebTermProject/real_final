package service;

import domain.BookLists;
import persistence.BookListsRepository;

public class BookListsService {
	private final BookListsRepository BLRepository= BookListsRepository.getInstance();
	
	//예약
	public void Booking(BookLists book)
	{
		//예약명단에 등록
		BookListsRepository.insertBookList(book);
	}
	
	/*
	//중복예약검사
	public static boolean DuplicationBook(BookLists booklist, Memeber member)
	{
		BookLists findBook=BookListsRepository.findByUserId(member.getUserID());
		if(findBook!=null)
		{
			throw new IllegalArgumentException("이미 예약되었습니다.");
			
			return false;
		}
		else
		{
			return true;
		}
	}
	*/
}
