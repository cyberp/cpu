package xyz.cp74.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * CastUtils
 * 
 * Cast different things to different things
 * 
 * @author Christian Paul
 *
 */
public class CastUtils {

	/**
	 * Cast object to integer
	 * @param o object to cast
	 * @return integer
	 */
	public static Integer toInteger(Object o) {
		return toInteger(o, 0);
	}

	/**
	 * Cast object to integer
	 * @param o object to cast
	 * @param defaultValue default return value, if object is not castable
	 * @return integer
	 */
	public static Integer toInteger(Object o, int defaultValue) {
		if (o!=null) {
			try {
				return Integer.parseInt(o.toString());
			}
			catch (Exception e) {}
		}
		return defaultValue;
	}
	
	/**
	 * Cast object to long
	 * @param o object to cast
	 * @return long
	 */
	public static Long toLong(Object o) {
		return toLong(o, 0);
	}

	/**
	 * Cast object to long
	 * @param o object to cast
	 * @param defaultValue default return value, if object is not castable
	 * @return long
	 */
	public static Long toLong(Object o, long defaultValue) {
		if (o!=null) {
			try {
				return Long.parseLong(o.toString());
			}
			catch (Exception e) {}
		}
		return defaultValue;
	}
	
	/**
	 * Cast object to double
	 * @param o object to cast
	 * @return double
	 */
	public static double toDouble(Object o) {
		return toDouble(o, 0.0);
	}

	/**
	 * Cast object to double
	 * @param o object to cast
	 * @param defaultValue default reurn value, if object is not castable
	 * @return double
	 */
	public static double toDouble(Object o, double defaultValue) {
		if (o!=null) {
			try {
				return Double.parseDouble(o.toString());
			}
			catch (Exception e) {}
		}
		return defaultValue;
	}
	
	/**
	 * Cast object to string
	 * @param o object to cast
	 * @return string
	 */
	public static String toString(Object o) {
		return toString(o, null);
	}

	/**
	 * Cast object to string
	 * @param o object to cast
	 * @param defaultValue default return value, if object is null
	 * @return string
	 */
	public static String toString(Object o, String defaultValue) {
		if (o!=null)
			return o.toString();
		else
			return defaultValue;
	}
	
	/**
	 * Cast object to a list of strings
	 * @param o object to cast
	 * @return list of strings
	 */
	public static List<String> toListOfStrings(Object o) {
		List<String> list = new ArrayList<String>();
		// object is list
		if (o instanceof List<?>)
			for (Object oo: (List<?>)o)
				list.add(CastUtils.toString(oo));
		// object is collection
		else if (o instanceof Collection<?>)
			for (Object oo: (Collection<?>)o)
				list.add(CastUtils.toString(oo));
		// object is primitive data
		else
			list.add(CastUtils.toString(o));
		
		return list;
	}
	
	/**
	 * Cast object to a set of strings
	 * @param o object to cast
	 * @return set of strings
	 */
	public static Set<String> toSetOfStrings(Object o) {
		Set<String> set = new HashSet<String>();
		// object is list
		if (o instanceof List<?>)
			for (Object oo: (List<?>)o)
				set.add(CastUtils.toString(oo));
		// object is collection
		else if (o instanceof Collection<?>)
			for (Object oo: (Collection<?>)o)
				set.add(CastUtils.toString(oo));
		// object is primitive data
		else
			set.add(CastUtils.toString(o));
		
		return set;
	}

}
