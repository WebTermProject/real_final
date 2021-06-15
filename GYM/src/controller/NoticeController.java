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
		    paging.setPageNo(page); //get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
		    paging.setPageSize(10); // 한페이지에 불러낼 게시물의 개수 지정
		    paging.setTotalCount(totalCount);
		     
		    page = (page - 1) * 10; //select해오는 기준을 구한다.
		     
		    list = dao.selectPage(page, paging.getPageSize());
		     
		    request.setAttribute("list", list);
		    request.setAttribute("paging", paging);
			modelAndView.setViewName("notice/noticeBoard");
			modelAndView.getModel().put("notice", list);
		}
	//글 자세히 보기()
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