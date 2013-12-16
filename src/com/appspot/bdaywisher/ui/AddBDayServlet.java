package com.appspot.bdaywisher.ui;

import java.io.IOException;
import java.util.HashMap;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;




import com.appspot.bdaywisher.datastore.Birthday;
import com.appspot.bdaywisher.datastore.PMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */


@SuppressWarnings("serial")
public class AddBDayServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name=req.getParameter("name");
		String nickname=req.getParameter("nickname");
		String email=req.getParameter("email");
		int birthDate=Integer.parseInt(req.getParameter("birthdate"));
		int birthMonth=Integer.parseInt(req.getParameter("birthmonth"));
		
		Birthday birthday=new Birthday();
		Key key = KeyFactory.createKey(Birthday.class.getSimpleName(), email);
		
		birthday.setKey(key);
		birthday.setName(name);
		birthday.setNickname(nickname);
		birthday.setEmail(email);
		birthday.setBirthDate(birthDate);
		birthday.setBirthMonth(birthMonth);
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
		pm.makePersistent(birthday);
		}
		finally {
		pm.close();
		}

		
		resp.sendRedirect("/browse.jsp");


	}
}
