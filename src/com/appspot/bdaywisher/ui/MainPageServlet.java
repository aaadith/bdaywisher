package com.appspot.bdaywisher.ui;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */

@SuppressWarnings("serial")
public class MainPageServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		resp.setContentType("text/html");
		resp.getWriter().println("called"+System.currentTimeMillis());
		UserService userService = UserServiceFactory.getUserService();
	        User user = userService.getCurrentUser();

	        if (user != null) {
	        	
	        	if(user.getEmail().equals("aaadith@gmail.com")) {
	        		resp.sendRedirect("/browse.jsp");
	        	}
	        	else {
	        		resp.getWriter().println("Hello, " + user.getNickname()+"<br>");
	        		resp.getWriter().println("Thanks for your interest. But this application is still not available to everybody.");
	        		resp.getWriter().println("<a href="+UserServiceFactory.getUserService().createLogoutURL(userService.createLoginURL(req.getRequestURI()))+">Log out</a>");
	        	}
	        } else {
	            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
	        }		
	}
}
