package com.appspot.bdaywisher.util;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.appspot.bdaywisher.datastore.Birthday;

import sun.tools.tree.ThisExpression;

/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */

public class TemplateUtil {
	  public static String LOGGER_NAME = TemplateUtil.class.getSimpleName();

	
	public static String getWishMailContent(Birthday birthday) throws Exception{
		HashMap variables=new HashMap();
		variables.put("nickname", birthday.getNickname());
		return getStringFromTemplate(variables, "template/bdaywishmail.vm");
	}

	public static String getWishMailSubject(Birthday birthday) throws Exception{
		HashMap variables=new HashMap();
		variables.put("nickname", birthday.getNickname());
		return getStringFromTemplate(variables, "template/bdaywishsubject.vm");
	}

	
	
	static String getStringFromTemplate(HashMap variables, String templateName) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        
        ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute");
        
        //ve.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute" );
        //ve.setProperty("runtime.log.logsystem.log4j.logger", LOGGER_NAME);
        
        ve.init();
        
        Template t = ve.getTemplate( templateName );
        
        VelocityContext context = new VelocityContext();
        
        for(Object key: variables.keySet()){
        	context.put(key.toString(), variables.get(key));
        }
        
        
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        
        return writer.toString();  		
		
	}
	
	public static void main(String[] args) throws Exception {
		Birthday birthday=new Birthday("a","b","a@b",1,2);
		System.out.println(getWishMailContent(birthday));
	}
}
