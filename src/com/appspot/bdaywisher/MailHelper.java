package com.appspot.bdaywisher;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

import com.appspot.bdaywisher.datastore.Birthday;
import com.appspot.bdaywisher.util.MailUtil;
import com.appspot.bdaywisher.util.TemplateUtil;


/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */

public class MailHelper {
	public static String getAttachmentFileName(){
		return "gift/"+(new File("gift")).list()[0];	
	}

	public static void sendBirthdayWishMail(Birthday birthday) throws Exception {
		InternetAddress from = new InternetAddress("aaadith@gmail.com",  "Shravana Aadith");
		InternetAddress to =  new InternetAddress(birthday.getEmail(), birthday.getName());
		
		String subject=TemplateUtil.getWishMailSubject(birthday);
		String content=TemplateUtil.getWishMailContent(birthday);
		content=content.replaceAll("\n", "<br>");
		content="<i>"+content+"</i>";
		
		String attachmentFileName = getAttachmentFileName();
		
		MailUtil.sendMailWithAttachment(from, to, subject, content, attachmentFileName);			
	}
}
