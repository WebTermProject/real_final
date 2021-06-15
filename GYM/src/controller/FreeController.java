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
		    paging.setPageNo(page); //get����� parameter������ ���� page����, ���� ������ ��ȣ
		    paging.setPageSize(10); // ���������� �ҷ��� �Խù��� ���� ����
		    paging.setTotalCount(totalCount);
		     
		    page = (page - 1) * 10; //select�ؿ��� ������ ���Ѵ�.
		     
		    list = dao.selectPage(page, paging.getPageSize());
		     
		    request.setAttribute("list", list);
		    request.setAttribute("paging", paging);
			modelAndView.setViewName("free/freeBoard");
			modelAndView.getModel().put("free", list);
		}
		//�۾���()
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
		//�� �ڼ��� ����()
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

