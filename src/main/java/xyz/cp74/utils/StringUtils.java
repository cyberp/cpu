package xyz.cp74.utils;

/**
 * 
 * StringUtils
 * 
 * Utils for manipulating strings
 * 
 * @author Christian Paul
 *
 */
public class StringUtils {

	/**
	 * Remove last char of a string @param s 
	 * @return tailed string
	 */
	public static String removeLastChar(String s) {
		if (s==null || s.length()==0)
			return s;
		else
			return s.substring(0, s.length()-1);
			
	}
	
}
