package tibame.tga102.yokult.fundraising.common;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class HtmlPostJsonGetter {
	
	private JSONObject jsonObj;
	
	public HtmlPostJsonGetter(HttpServletRequest req) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {}
		String jbString = jb.toString().replace("[", "").replace("]", "");
	    System.out.println(jbString);
		jsonObj = new JSONObject(jbString);
	}
	
	public String getString(String key) {
	    return jsonObj.getString(key);
	}
	
	public Integer getIntger(String key) {
	    return jsonObj.getInt(key);
	}
}
