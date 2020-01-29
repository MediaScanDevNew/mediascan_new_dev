package com.pradeep.pj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class PageSourceBot {

	public PageSourceBot() {
		// TODO Auto-generated constructor stub
	}

	// public static String getPageSource(String uurl) {
	// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+uurl);
	//
	// String responseBody = "";
	// CloseableHttpClient httpclient = HttpClients.createDefault();
	// try {
	// HttpGet httpget = new HttpGet(uurl.trim());
	//
	// System.out.println("Executing request " + httpget.getRequestLine());
	//
	// // Create a custom response handler
	// ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	//
	// public String handleResponse(final HttpResponse response) throws
	// ClientProtocolException, IOException {
	// int status = response.getStatusLine().getStatusCode();
	// if (status >= 200 && status < 300) {
	// HttpEntity entity = response.getEntity();
	// return entity != null ? EntityUtils.toString(entity) : null;
	// } else {
	// throw new ClientProtocolException("Unexpected response status: " +
	// status);
	// }
	// }
	//
	// };
	// responseBody = httpclient.execute(httpget, responseHandler);
	// return responseBody;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return responseBody;
	// } finally {
	// try {
	// httpclient.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	public static String getPageSource(String url) throws IOException {
		URL urlObject = new URL(url);
		URLConnection urlConnection = urlObject.openConnection();
		System.out.println("page source test 1");
		//Mozilla/5.0 (X11; Linux x86_64; rv:66.0) Gecko/20100101 Firefox/66.0
		/*
		urlConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) "
				+ "Chrome/23.0.1271.95 Safari/537.11");
				*/
		urlConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64; rv:66.0) Gecko/20100101 Firefox/68.0.1");
		System.out.println("page source test 2");
		urlConnection.setConnectTimeout(-1);
		return toString(urlConnection.getInputStream());
	}

	private static String toString(InputStream inputStream) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
			String inputLine;
			System.out.println("page source test 3");
			StringBuilder stringBuilder = new StringBuilder();
			int count =0;
			while ((inputLine = bufferedReader.readLine()) != null) {
				System.out.println("page source test 4"+count);
				if(count==10)
					break;
				stringBuilder.append(inputLine);
				count=count+1;
			}

			return stringBuilder.toString();
		}
	}

}
