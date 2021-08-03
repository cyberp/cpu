package xyz.cp74.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * CommandLineUtils
 * 
 * Parse command line arguments
 * 
 * @author Christian Paul
 *
 */
public class CommandLineUtils {

	/**
	 * Clean @param value from quotation marks
	 * @return String 
	 */
	private static String clean(String value) {
		if (value.startsWith("\"") && value.endsWith("\""))
			value = value.substring(1, value.length()-1);
		return value;
	}
	
	/**
	 * Get @param args from command line
	 * @return Map 
	 */
	public static Map<String, String> get(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		if (args!=null) {
			
			String key = null;
			List<String> keylessValues = new ArrayList<String>();
			
			for(String arg: args) {
				
				// key found
				if (arg.startsWith("-")) {
					key = arg.substring(1);
					params.put(key, null);
				}
				// value found
				else {
					if (key!=null) {
						params.put(key, clean(arg));
						key = null;
					}
					else {
						keylessValues.add(clean(arg));
					}
				}
				
			}
			
			// add keyless values
			if (keylessValues.size()>0)
				params.put(null, String.join(",", keylessValues));
		}
		
		return params;
	}

}
