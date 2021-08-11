package xyz.cp74.download;

import java.util.Locale;

/**
 * 
 * Response of download
 * 
 * @author Christian Paul
 *
 */
public class Response {

	// properties
	private int statusCode;
	private String statusMessage;
	private long responseTime;
	private Locale locale;
	private long contentLength;
	private String contentType;
	private String contentEncoding;
	private String lastModified;
	private String location;
	private String targetFileName;
	private String content;
	private boolean redirected;
	
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}
	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	/**
	 * @return the responseTime
	 */
	public long getResponseTime() {
		return responseTime;
	}
	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	/**
	 * @return the contentLength
	 */
	public long getContentLength() {
		return contentLength;
	}
	/**
	 * @param contentLength the contentLength to set
	 */
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the contentEncoding
	 */
	public String getContentEncoding() {
		return contentEncoding;
	}
	/**
	 * @param contentEncoding the contentEncoding to set
	 */
	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}
	/**
	 * @return the lastModified
	 */
	public String getLastModified() {
		return lastModified;
	}
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the targetFileName
	 */
	public String getTargetFileName() {
		return targetFileName;
	}
	/**
	 * @param targetFileName the targetFileName to set
	 */
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	
	/**
	 * @return the redirected
	 */
	public boolean isRedirected() {
		return redirected;
	}
	/**
	 * @param redirected the redirected to set
	 */
	public void setRedirected(boolean redirected) {
		this.redirected = redirected;
	}
	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", responseTime="
				+ responseTime + ", locale=" + locale + ", contentLength=" + contentLength + ", contentType="
				+ contentType + ", contentEncoding=" + contentEncoding + ", lastModified=" + lastModified
				+ ", location=" + location + ", targetFileName=" + targetFileName + ", redirected=" + redirected + "]";
	}
	
	
}
