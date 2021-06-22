package xyz.cp74.utils;

/**
 * Utils for manipulating strings
 * 
 * @author Christian Paul
 *
 */
public class StringUtils {

	/**
	 * Remove last char of a string
	 * @param s 
	 * @return 
	 */
	public static String removeLastChar(String s) {
		if (s==null || s.length()==0)
			return s;
		else
			return s.substring(0, s.length()-1);
			
	}
	
}
