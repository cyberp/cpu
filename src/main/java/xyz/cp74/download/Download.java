package xyz.cp74.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.tinylog.Logger;
import xyz.cp74.utils.UrlUtils;

/**
 * 
 * Simple downloader
 * 
 * @author Christian Paul
 *
 */
public class Download  {

	// properties
	private String url;
	private int timeout;
	private String userAgent;

	/**
	 * Constructor with @param url
	 */
	public Download(String url) {
		this.url = url;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * Set the @param timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Set the @param userAgent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Execute download and @return Response
	 */
	public Response execute() {

		// create response
		Response r = new Response();
		
		// create http client
		CloseableHttpClient http = null;
		CloseableHttpResponse response = null;
		
		// measure response time
		long startResponse = System.currentTimeMillis();
		long endResponse = System.currentTimeMillis();
		try {
			
			// default request config
			RequestConfig config = RequestConfig.custom()
					  .setConnectTimeout(getTimeout())
					  .setConnectionRequestTimeout(getTimeout())
					  .setSocketTimeout(getTimeout()).build();
			
			// create http client
			http = HttpClients
					.custom()
					//.disableRedirectHandling()
				    .setDefaultRequestConfig(config)
					.setRedirectStrategy(new LaxRedirectStrategy())
					.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
				    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
					.build();
			
			// create request
			// and execute it
			String encodedUrl = UrlUtils.encodeUrl(getUrl());
			HttpUriRequest get = RequestBuilder.get()
					  .setUri(encodedUrl)
					  .setHeader(HttpHeaders.USER_AGENT, getUserAgent())
					  //.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
					  .build();
			HttpClientContext context = HttpClientContext.create();
			startResponse = System.currentTimeMillis();
			response = http.execute(get, context);
			endResponse = System.currentTimeMillis();
			r.setResponseTime(endResponse-startResponse);
			
			// response parameters
			// status code and message
			r.setStatusCode(response.getStatusLine().getStatusCode());
			r.setStatusMessage(response.getStatusLine().getReasonPhrase());
			// locale
			r.setLocale(response.getLocale());
			// content-type
			if (response.containsHeader("Content-Type"))
				r.setContentType(response.getFirstHeader("Content-Type").getValue());
			// content-length
			if (response.containsHeader("Content-Length")) {
				try {
					r.setContentLength(Long.valueOf(response.getFirstHeader("Content-Length").getValue()));
				}
				catch (Exception e) {}
			}
			// content-encoding
			if (response.containsHeader("Content-Encoding"))
				r.setContentEncoding(response.getFirstHeader("Content-Encoding").getValue());
			// last-modified
			if (response.containsHeader("Last-Modified"))
				r.setLastModified(response.getFirstHeader("Last-Modified").getValue());

			// content-disposition
			if (response.containsHeader("Content-Disposition")) {
				String contentDisposition = response.getFirstHeader("Content-Disposition").getValue();
				if (contentDisposition.contains("filename="))
					r.setTargetFileName(contentDisposition.substring(contentDisposition.indexOf("filename=")+9).replaceAll("\"", ""));
			}
			// redirection and location
			try {
				if (context.getRedirectLocations()!=null && !context.getRedirectLocations().isEmpty()) {
					r.setRedirected(true);
					URI locationUri = URIUtils.resolve(get.getURI(), context.getTargetHost(), context.getRedirectLocations());
					if (locationUri!=null) {
						r.setLocation(locationUri.toString());
						r.setStatusCode(302);
						r.setStatusMessage("Redirected: "+locationUri.toString());
					}
				}
			}
			catch (Exception e) {
				Logger.error(e, "error while analyzing redirection");
			}
			
			// get entity
			HttpEntity entity = null;
			entity = response.getEntity();
			if (r.getStatusCode()>=200 && r.getStatusCode()<400 && entity!=null) {

				InputStream is = entity.getContent();
				StringBuilder textBuilder = new StringBuilder();
			    try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
			        int c = 0;
			        while ((c = reader.read()) != -1) {
			            textBuilder.append((char) c);
			        }
			    }
			    r.setContent(textBuilder.toString());
			    
			}
			else {
				Logger.error("download not possible [{}] - response {} - {} ", getUrl(), r.getStatusCode(), r.getStatusMessage());
			}
            
		} 
		catch (Exception e) {
			endResponse = System.currentTimeMillis();
			r.setResponseTime(endResponse-startResponse);
			Logger.error(e, "cant copy file from [{}] to []", getUrl());
		}
		finally {
			if (response!=null)
				try {
					response.close();
				} catch (IOException e) {}
			if (http!=null) {
				try {
					http.close();
				} catch (IOException e) {}
			}
		}
		return r;
	}
	
	/**
	 * Get file extension of @param contentType
	 * @return file extension
	 */
	protected String getExtension(String contentType) {
		if (contentType!=null) {
			switch(contentType.toLowerCase().trim()) {
				// images
				case "image/jpeg": return "jpg";
				case "image/svg+xml": return "svg";
				case "image/tiff": return "tiff";
				case "image/bmp": return "bmp";
				case "image/x-icon": return "ico";
				case "image/png": return "png";
				case "image/gif": return "gif";
				case "image/webp": return "webp";
				default:
					return contentType.toLowerCase().trim().replace("/", ".").replace("+", ".");
			}
		}
		return null;
	}

	
}
