package xyz.cp74.utils;

import java.net.URI;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.client.utils.URIUtils;

/**
 * 
 * UrlUtils
 * 
 * Do some things with URLs
 * 
 * @author Christian Paul
 *
 */
public class UrlUtils {
	
	/**
	 * Get domain of @param url
	 * @return domain
	 */
	public static String getDomain(String url) {
		String host = getHost(url);
		if (host!=null) {
			String protocoll = getProtocoll(host);
			if (protocoll!=null)
				return host.replace(protocoll+"://", "");
			else
				return host;
		}
		return url;
	}
	
	/**
	 * Get host from url
	 * @param url the url
	 * @return host
	 */
	public static String getHost(String url) {
		return getHost(url, true);
	}
	
	/**
	 * Get host from url
	 * @param url the url
	 * @param toLowerCase lower case the host
	 * @return host
	 */
	public static String getHost(String url, boolean toLowerCase) {
		if (url!=null && !url.trim().isEmpty()) {
			int start = 0;
			int i = url.indexOf("://");
			if (i!=-1) 
				start = i+3;
			else 
				return null;
			int j = url.indexOf("/", start);
			String host = null;
			if (j==-1)
				return url.toLowerCase();
			else 
				host = url.substring(0, j);
			if (host.endsWith("/"))
				host = host.substring(0, host.length()-1);
			if (toLowerCase)
				return host.toLowerCase();
			else
				return host;
		}
		return null;
	}
	
	/**
	 * Remove host from @param url
	 * @return absolute path
	 */
	public static String removeHost(String url) {
		if (url!=null && !url.trim().isEmpty()) {
			String host = getHost(url, false);
			String path = null;
			if (host!=null)
				path = url.replace(host, "");
			else
				path = url;
			if (path==null || path.isEmpty())
				path = "/";
			if (!path.startsWith("/"))
				path = "/"+path;
			return path;
		}
		return null;
	}
	
	/**
	 * Resolve @param baseUrl with @param url
	 * @return resolved URL
	 */
	public static String resolve(String baseUrl, String url) {
		url = encodeUrl(url);
		try {
			return URIUtils.resolve(new URI(baseUrl), url).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Check if @param url is relative
	 * @return true, if relative
	 */
	public static boolean isRelative(String url) {
		return !url.startsWith("http");
	}
	
	/**
	 * Encode @param url
	 * @return encoded URL
	 */
	public static String encodeUrl(String url) {
		// Includes the blank/empty space and " < > # % { } | \ ^ ~ [ ] `
		// see https://de.wikipedia.org/wiki/URL-Encoding
		if (url!=null)
			return url
					.replace(" ", "%20")
					.replace("<", "%3C")
					.replace(">", "%3E")
					.replace("{", "%7B")
					.replace("}", "%7D")
					.replace("|", "%7C")
					.replace("\"", "%22")
					.replace("^", "%5E")
					.replace("~", "%7E")
					.replace("\\", "%5C")
					.replace("[", "%5B")
					//.replace("%", "%25")  // doppelt codierung
					.replace("]", "%5E");
		else
			return null;
	}
	
	/**
	 * Encode @param url
	 * @return encoded URL
	 */
	public static String encodeUrl2(String url) {
		if (url!=null) {
			URLCodec codec = new URLCodec("UTF-8");
			try {
				return codec.encode(url);
			} catch (EncoderException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Check @param url and @return protocoll
	 */
	public static String getProtocoll(String url) {
		if (url!=null) {
			int i = url.indexOf("://");
			if (i!=-1) {
				return url.substring(0, i).toLowerCase();
			}
		}
		return null;
	}
	
}
