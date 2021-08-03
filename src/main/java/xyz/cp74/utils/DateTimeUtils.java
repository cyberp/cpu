package xyz.cp74.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * DateTimeUtils
 * 
 * @author Christian Paul
 *
 */
public class DateTimeUtils {

	final static SimpleDateFormat RFC2822  = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

	/**
	 * Get RFC2822 conform string from @param date
	 * @return RFC2822 string
	 */
    public static String rfc2822(Date date) {
        return RFC2822.format(date);
    }
	
}
