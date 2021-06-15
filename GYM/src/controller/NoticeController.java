package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FreeDTO;
import domain.PagingDTO;
import domain.noticeDTO;
import persistence.FreeDAO;
import persistence.noticeDAO;
import service.NoticeService;

public class NoticeController extends HttpServlet implements Controller {
	
	private final NoticeService noticeService = new NoticeService();
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {		
		ModelAndView modelAndView = new ModelAndView();
		if(url.equals("/notice/noticeBoard")) {
		    noticeDAO dao = new noticeDAO();
		    ArrayList list;
		    int totalCount = dao.getAllcount();
		    int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		     
		    PagingDTO paging = new PagingDTO();
		    paging.setPageNo(page); //get����� parameter������ ���� page����, ���� ������ ��ȣ
		    paging.setPageSize(10); // ���������� �ҷ��� �Խù��� ���� ����
		    paging.setTotalCount(totalCount);
		     
		    page = (page - 1) * 10; //select�ؿ��� ������ ���Ѵ�.
		     
		    list = dao.selectPage(page, paging.getPageSize());
		     
		    request.setAttribute("list", list);
		    request.setAttribute("paging", paging);
			modelAndView.setViewName("notice/noticeBoard");
			modelAndView.getModel().put("notice", list);
		}
	//�� �ڼ��� ����()
	else if(url.equals("/notice/noticeDetail")) {
		ArrayList<noticeDTO> detail = noticeService.read(Integer.parseInt(request.getParameter("id")));
		modelAndView.setViewName("notice/noticeDetail");
		modelAndView.getModel().put("detail", detail);
	}
	else {
		modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	return modelAndView;
	}
}