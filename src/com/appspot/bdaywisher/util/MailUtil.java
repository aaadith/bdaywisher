package com.appspot.bdaywisher.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


import com.appspot.bdaywisher.datastore.Birthday;

/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */




public class MailUtil {
	
	private static final Logger log = Logger.getLogger(MailUtil.class.getName());
	
	public static void sendMailWithAttachment(InternetAddress from, InternetAddress to, String subject, String content, String attachmentFileName) throws IOException{
        Properties props = new Properties();
        //Session session = Session.getDefaultInstance(props, null);
        Session session = Session.getInstance(props, null);
       

        try {
        	
            Message msg = new MimeMessage(session);
            
            
            msg.setFrom(from);
            msg.addRecipient(Message.RecipientType.TO, to);            
            
            msg.setSubject(subject);
   
            String htmlBody=content;
            
                        
            Multipart mp = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html");
            mp.addBodyPart(htmlPart);

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setFileName(attachmentFileName);
            
            
            byte[] attachmentData=getAttachmentData(attachmentFileName);              
            String mimeType = getMimeType(attachmentFileName);

            
    		DataSource src = new ByteArrayDataSource(attachmentData, mimeType); 
    		attachment.setDataHandler(new DataHandler(src)); 
            mp.addBodyPart(attachment);

            msg.setContent(mp);
            
            
            msg.saveChanges();
                 
            Transport.send(msg);
            log.info("sent mail");
            
            
        } catch (AddressException e) {
        	log.info(e.getMessage());
            
        } catch (MessagingException e) {
        	log.info(e.getMessage());
        }
		
	}
	
	
	public static String getMimeType(String fileName) {
		String extension=fileName.substring(fileName.indexOf(".")+1);
		if(extension.equalsIgnoreCase("pdf"))
			return "application/pdf";
		if(extension.equalsIgnoreCase("html")||extension.equalsIgnoreCase("htm"))
			return "text/html";
		if(extension.equalsIgnoreCase("txt"))
			return "text/plain";
		return null;
	}
	
	
	static byte[] getAttachmentData(String attachmentFileName){
		File file= new File(attachmentFileName);
		FileInputStream fin = null; 
		try {
        byte[] attachmentData;  
                    
		fin = new FileInputStream(file); 
        attachmentData = new byte[(int)file.length()]; 
		fin.read(attachmentData);
		return attachmentData;
		}
		catch(FileNotFoundException fne) {
			fne.printStackTrace();			
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	public static void main(String[] args) {
	}
	
	public static void sendMail(InternetAddress from, InternetAddress to, String subject, String content) throws IOException{
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        //String msgBody = req.getParameter("msg");


        try {
            Message msg = new MimeMessage(session);
            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setFrom(new InternetAddress("aaadith@gmail.com", "श्रावण आदित|Shravana Aadith"));
            msg.addRecipient(Message.RecipientType.TO, to);
            msg.setSubject("श्रावण आदित|Shravana Aadith");
            msg.setText(content);
            Transport.send(msg);
    
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
		
		
	}
}
