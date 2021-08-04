package xyz.cp74.utils;

import org.tinylog.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * JsonUtils
 * 
 * Read and Write JSON files.
 * 
 * @author Christian Paul
 *
 */
public class JsonUtils {

    
	/**
	 * Write @param object to @return JSON
	 */
	public static String write(Object data) {
		if (data!=null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
	    	try {
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			} 
	    	catch (Exception e) {
	    		Logger.error(e, "error converting to json");
	    	}
		}
		return "{}";
	}
	
}
