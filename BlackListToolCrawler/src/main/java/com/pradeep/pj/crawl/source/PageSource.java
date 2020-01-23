/**
 * 
 */
package com.pradeep.pj.crawl.source;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author pradeep
 *
 */
@Service
public class PageSource {
	private static final Logger logger = LoggerFactory.getLogger(PageSource.class);
	/**
	 * 
	 */
	HttpGet httpget = null;

	public PageSource() {
		// TODO Auto-generated constructor stub
	}

	public String html2Doc(String uurl) {
		logger.info("---- link is ---" + uurl);
		String pageSource = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			httpget = new HttpGet(uurl.trim());

			logger.info("Executing request " + httpget.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};
			pageSource = httpclient.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			pageSource = "Unexpected response status: 404";
		} catch (Exception e) {
			httpget = null;
			e.printStackTrace();

		} finally {
			httpget = null;
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}return pageSource;
	}

}
