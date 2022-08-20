package tibame.tga102.yokult.fundraising.common;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

public class HtmlDatetimeLocalToSQLDateTimeUtil {
	public static java.sql.Timestamp parseDateTime(String DateTimefromHTML) {
		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
		    
	    	String[] stringList = DateTimefromHTML.split("T");
		    String reorganization = stringList[0] + " " + stringList[1];
		    java.util.Date date = null;
			try {
				date = formatter.parse(reorganization);
			} catch (ParseException e) {}
			java.sql.Timestamp startTimestamp = new java.sql.Timestamp(date.getTime());
			
			return startTimestamp;
			
		} catch (Exception e) {}
		return null;
	}
	
	public static java.sql.Date parseDate(String DateTimefromHTML) {
		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		    
//	    	String[] stringList = DateTimefromHTML.split("T");
//		    String reorganization = stringList[0];
		    java.util.Date date = null;
			try {
				date = formatter.parse(DateTimefromHTML);
			} catch (ParseException e) {}
			java.sql.Date startTimestamp = new java.sql.Date(date.getTime());
			
			return startTimestamp;
			
		} catch (Exception e) {}
		return null;
	}
}
