package xyz.cp74.utils;

import java.io.File;
import java.util.Map;
import org.tinylog.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

/**
 * 
 * JsonUtils.
 * Read and Write JSON files.
 * 
 * @author Christian Paul
 *
 */
public class JsonUtils {

    
	/**
	 * Write object to JSON String
	 * @param object
	 * @return JSON 
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
