package service;

import domain.BookLists;
import persistence.BookListsRepository;

public class BookListsService {
	private final BookListsRepository BLRepository= BookListsRepository.getInstance();
	
	//����
	public void Booking(BookLists book)
	{
		//������ܿ� ���
		BookListsRepository.insertBookList(book);
	}
	
	/*
	//�ߺ�����˻�
	public static boolean DuplicationBook(BookLists booklist, Memeber member)
	{
		BookLists findBook=BookListsRepository.findByUserId(member.getUserID());
		if(findBook!=null)
		{
			throw new IllegalArgumentException("�̹� ����Ǿ����ϴ�.");
			
			return false;
		}
		else
		{
			return true;
		}
	}
	*/
}