package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.management.RuntimeErrorException;

import domain.FreeDTO;
import domain.PagingDTO;
import persistence.FreeDAO;


public class FreeService {
	private final FreeDAO FreeDao = FreeDAO.getInstacne();
	public FreeService() {
		
	}
	public void write(FreeDTO board) {		
		FreeDao.insertFree(board);
	}
	public ArrayList<FreeDTO> read(int findId) {		
		return FreeDao.getNotice(findId);
	}
//	public void update(FreeDTO board,Long id) {		
//		FreeDao.modify(board,id);
//	}
//	public void delete(Long deleteId) {		
//		FreeDao.delete(deleteId);
//	}

	public ArrayList<FreeDTO> selectPage(int start, int pageCnt) {
        return FreeDao.selectPage(start, pageCnt);
    }
	
	public int allCount() {		
		return FreeDao.getAllcount();
	}
}
