package service;

import domain.itemDTO;
import persistence.itemDAO;

public class itemService {
	private final itemDAO itemDao = itemDAO.getInstacne();
	public itemService() {
		
	}
	public void enrollment(itemDTO item) {		
		itemDao.itemOrder(item);
	}
}
