package service;

import java.util.ArrayList;

import domain.noticeDTO;
import persistence.noticeDAO;



public class NoticeService {
	private final noticeDAO noticeDao = noticeDAO.getInstacne();
	public NoticeService() {
		
	}
	public ArrayList<noticeDTO> read(int findId) {		
		return noticeDao.getNotice(findId);
	}
	public ArrayList<noticeDTO> selectPage(int start, int pageCnt) {
        return noticeDao.selectPage(start, pageCnt);
    }
	
	public int allCount() {		
		return noticeDao.getAllcount();
	}
}
