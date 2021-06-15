package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="frontController", urlPatterns = "/front/*")
public class FrontController extends HttpServlet{
	
	private Map<String, Controller> controllerMap = new HashMap<>();
	
	public FrontController() {
		controllerMap.put("member", new MemberController());
		controllerMap.put("free", new FreeController());
		controllerMap.put("notice", new NoticeController());
		controllerMap.put("purchase", new purchaseController());
		controllerMap.put("appointment", new BookListsController());
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		conPath+="/front";
		String com = uri.substring(conPath.length());
		String [] tokens;
		if(com.equals("/")) {
			com = "/notice/noticeBoard";
		}
			tokens = com.split("/");
			String domain = tokens[1];
			Controller controller = controllerMap.get(domain);

			//404처리 페이지
			if(controller == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			ModelAndView mv = controller.process(request, response, com);
			if(request.getMethod().equals("POST")) {
				String viewPath =postViewResolver(mv.getViewName());
				View view = new View(viewPath);
				view.doPost(mv.getModel(), request, response);
			} else {
				String viewPath =viewResolver(mv.getViewName());
				View view = new View(viewPath);
				view.doGet(mv.getModel(), request, response);
			}
		}
	
	
	private String viewResolver(String viewName) {
		StringBuilder view = new StringBuilder();
		view.append("/WEB-INF/view/");
		view.append(viewName);
		view.append(".jsp");
		String viewpath = view.toString();
		return viewpath;
	}
	
	private String postViewResolver(String viewName) {
		StringBuilder view = new StringBuilder();
		view.append("/GYM/front/");
		view.append(viewName);
		String viewpath = view.toString();
		return viewpath;
	}
	
}
