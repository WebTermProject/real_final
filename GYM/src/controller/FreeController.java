package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.FreeDTO;
import domain.PagingDTO;
import persistence.FreeDAO;
import service.FreeService;

public class FreeController implements Controller{

	private final FreeService freeService = new FreeService();
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {		
		ModelAndView modelAndView = new ModelAndView();
		Cookie[] c = request.getCookies();
		HttpSession session = request.getSession();
		
		if (c != null) {
			for (Cookie cf : c) {
				if (cf.getName().equals("sessionID")){
					String ids = cf.getValue();
					session.setAttribute("sessionID", ids);
				}
			}
		}
		if(url.equals("/free/freeBoard")) {
			
		    FreeDAO dao = new FreeDAO();
		   
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
			modelAndView.setViewName("free/freeBoard");
			modelAndView.getModel().put("free", list);
		}
		//글쓰기()
		else if(url.equals("/free/writeForm")) {
			if(request.getMethod().equals("GET")) {
				modelAndView.setViewName("free/writeForm");
			}
			else if(request.getMethod().equals("POST")) {
				FreeDTO Free = new FreeDTO();
				Free.setTitle(request.getParameter("title"));
				Free.setWriter(request.getParameter("writer"));
				Free.setContents(request.getParameter("contents"));
				freeService.write(Free);
				modelAndView.setViewName("free/freeBoard");
			}
		}
		//글 자세히 보기()
		else if(url.equals("/free/viewDetail")) {
			ArrayList<FreeDTO> detail = freeService.read(Integer.parseInt(request.getParameter("id")));
			modelAndView.setViewName("free/viewDetail");
			modelAndView.getModel().put("detail", detail);
		}
		else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return modelAndView;
	}
}


