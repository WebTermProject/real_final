package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.itemDTO;
import service.itemService;

public class purchaseController extends HttpServlet implements Controller {
   private final itemService itemservice = new itemService();

   public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
         throws ServletException, IOException {
      ModelAndView modelAndView = new ModelAndView();
      Cookie[] c = request.getCookies();
      HttpSession session = request.getSession();

      if (c != null) {
         for (Cookie cf : c) {
            if (cf.getName().equals("sessionID")) {
               String ids = cf.getValue();
               session.setAttribute("sessionID", ids);
            }
         }
      }

      if (url.equals("/purchase/purchaseBoard")) {
         if (request.getMethod().equals("GET")) {
            modelAndView.setViewName("purchase/purchaseBoard");
         } else if (request.getMethod().equals("POST")) {
            itemDTO item = new itemDTO();
            item.setMemberID(request.getParameter("MemberID"));
            for(int count=0; count<2; count++) {
            	if(request.getParameterValues("item")[count].equals("´ß°¡½¿»ì"))
            	{
            		item.setChickenCnt(Integer.parseInt(request.getParameterValues("count")[count]));
            	}
            	else {
            		item.setWaterCnt(Integer.parseInt(request.getParameterValues("count")[count]));
            	}
            	}
            	itemservice.enrollment(item);
            	modelAndView.setViewName("purchase/purchaseBoard");
         	}
      } else {
         modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
      return modelAndView;
   }
}