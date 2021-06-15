package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.BookLists;
import domain.Timetables;
import service.BookListsService;
import service.TimetablesService;

public class BookListsController implements Controller{
	private final BookListsService bookService=new BookListsService();
	
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
		
		//예약
		if(url.equals("/appointment/appointment"))
		{
			if(request.getMethod().contentEquals("GET"))	//이동할 데가 있나?
			{
				ArrayList<Timetables> timetables = TimetablesService.read();
	
				modelAndView.setViewName("appointment/appointment");		
				
				modelAndView.getModel().put("timetable", timetables);
			}
			else if(request.getMethod().contentEquals("POST"))	//DB에 넣기
			{

				
				BookLists booklist = new BookLists();
				booklist.setUserId(request.getParameter("user"));
			
				booklist.setTimetableId(Long.parseLong(request.getParameter("ttId")));
		
				bookService.Booking(booklist);				
				modelAndView.setViewName("appointment/appointment");
				

			}
		}
		else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return modelAndView;
	}
}
