package com.appspot.bdaywisher.cron;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import com.appspot.bdaywisher.MailHelper;
import com.appspot.bdaywisher.datastore.Birthday;
import com.appspot.bdaywisher.datastore.PMF;
import com.appspot.bdaywisher.util.TemplateUtil;

/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */

@SuppressWarnings("serial")
public class CronServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		List<Birthday> todaysBirthdays=getTodaysBirthdays();
		
		
		sendBirthdayWishMail(todaysBirthdays);
		

	}
	
	
	
	private List<Birthday> getTodaysBirthdays(){
		Calendar calendar= Calendar.getInstance();
		
		//app-engine server time in UTC. it has to be adjusted to IST before using it
		//IST = UTC + 5:30
		calendar.add(Calendar.MINUTE, 30);
		calendar.add(Calendar.HOUR, 5);
		
		
		int todaysMonth = calendar.get(Calendar.MONTH);
		int todaysDate = calendar.get(Calendar.DAY_OF_MONTH);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Birthday.class);
		query.setFilter("birthMonth == todaysMonth && birthDate == todaysDate");
		query.declareParameters("Integer todaysMonth,  Integer todaysDate");

		List<Birthday> birthdays = (List<Birthday>) query.execute(todaysMonth, todaysDate);
		
		birthdays=(birthdays==null)?new ArrayList<Birthday>(0):birthdays;
		
		return birthdays;
		
	}
	
	
	
	private void sendBirthdayWishMail(List<Birthday> birthdays){
		
		for(Birthday birthday : birthdays){
			//HashMap<String, String> variables=new HashMap<String,String>(1);
			//variables.put("name", birthday.getNickname());
			try {
			//	String wishMailContent = TemplateUtil.getWishMailContent(variables);
				MailHelper.sendBirthdayWishMail(birthday);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
}
