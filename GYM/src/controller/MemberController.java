package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.MemberService;



public class MemberController implements Controller{
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) {
		ModelAndView modelAndView = new ModelAndView();
		//GET,POST
		if(url.equals("/member/login")) {
			if(request.getMethod().equals("GET")) {
				HttpSession session = request.getSession();
		        session.invalidate();
		        
		     // 로그인 유지 쿠키 삭제
		        Cookie[] c = request.getCookies();
		        if (c != null) {
		        	for (Cookie cf : c) {
		        		if (cf.getName().equals("sessionID")) {
		        			cf.setMaxAge(0);
		        			cf.setPath("/");
		        			response.addCookie(cf);
		        		}
		        	}
		        }
				modelAndView.setViewName("member/login");
			}
			else if(request.getMethod().equals("POST")) {
				String userID =request.getParameter("userID");
				String userPW = request.getParameter("userPW");

				int loginResult = -3;

					
				try {
					loginResult = MemberService.login(userID, userPW);
					System.out.println(loginResult);
					if (loginResult == 1) {		// 로그인 성공
						request.setAttribute("loginResult", loginResult);
						HttpSession session = request.getSession();
						session.setAttribute("sessionID", userID);
						
						Cookie c = new Cookie("sessionID", userID);
						c.setMaxAge(60 * 2);
						c.setPath("/");
						response.addCookie(c);
					
						modelAndView.setViewName("");
						
					} else {
						request.setAttribute("loginResult", loginResult);
						modelAndView.setViewName("member/login");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else if(url.equals("/member/join")) {
			if(request.getMethod().equals("GET")) {
				modelAndView.setViewName("member/join");
			}
			else if(request.getMethod().equals("POST")) {
				Member member = new Member();
				member.setMemberID(request.getParameter("userID"));
				member.setPassword(request.getParameter("userPW"));
				member.setName(request.getParameter("userName"));
				member.setPhone(request.getParameter("userPhone1")+request.getParameter("userPhone2")+request.getParameter("userPhone3"));
				
				int joinResult = MemberService.join(member);
				
				if(joinResult == 1) {
					modelAndView.setViewName("");
				}
				else {
					modelAndView.setViewName("member/join");
				}
				
				
			}
		}
		else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}		
		return modelAndView;
	}
}