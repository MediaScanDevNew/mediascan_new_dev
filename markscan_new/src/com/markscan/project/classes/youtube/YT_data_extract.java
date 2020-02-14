/**
 * 
 */
package com.markscan.project.classes.youtube;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author pradeep
 *
 */
public class YT_data_extract extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(YT_data_extract.class);

	public YT_data_extract() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		YT_data_extract yt = new YT_data_extract();
		Map<String, Integer> ytData = new HashMap<>();
		ytData.put("ejdfpvIW7Zc", 123);
		ytData.put("-xxS-udrgKI", 45);
		ytData.put("be4cMyVB-4U", 78);
		ytData.put("omKo5FSpNE8", 74);
		ytData.put("ChYXfnmyXZU", 77);
		// yt.serverURLHeaderAuth(ytData);

		// ==========php test=========

		Map<String, String> phpData = new HashMap<>();
		phpData.put("date", "2018-04-23");
		phpData.put("domain", "youtube.com");
		phpData.put("ip", "172.168.1.25");
		phpData.put("usr", "myuser");
		phpData.put("passwd", "pass@123");
		phpData.put("db", "webinforcement_demo");

		yt.serverURLHeaderAuthPHPTest(phpData);

	}

	String URL_Server_PREFIX = "http://172.168.1.3:8082/check";

	// == header authontication=====

	String configFile = null;
	String serverIP = null;
	String serverPORT = null;
	Properties prop = null;
	InputStream input = null;

	public void serverURLHeaderAuth(Map<String, Integer> ytData) {
		HttpHeaders headers = new HttpHeaders();
		prop = new Properties();
		configFile = "/details.properties";
		input = getClass().getResourceAsStream(configFile);
		try {
			prop.load(input);
			// serverIP = prop.getProperty("SmServerIP");
			// serverPORT = prop.getProperty("SmServerPORT");
			URL_Server_PREFIX = "http://" + prop.getProperty("SmServerIP") + ":" + prop.getProperty("SmServerPORT")
					+ "/check";
		} catch (Exception e) {
		} finally {
			configFile = null;
			input = null;
			prop = null;
			serverIP = null;
			serverPORT = null;
		}
		logger.info(URL_Server_PREFIX);

		try {

			String input_Data_json = "";
			for (Map.Entry<String, Integer> videoId_with_id : ytData.entrySet()) {
				input_Data_json = input_Data_json + videoId_with_id.getKey() + ":" + videoId_with_id.getValue() + ",";
			}
			input_Data_json = input_Data_json.substring(0, input_Data_json.length() - 1);

			headers.set("Authorization", input_Data_json);

			// headers.set("Authorization",
			// "{\"pj\":\"1\",\"pj1\":\"2\",\"pj3\":\"3\",\"pj4\":\"4\",\"pj5,\":\"5\"}");
			//
			//
			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			// Request to return JSON format
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("my_other_key", "my_other_value");

			// HttpEntity<String>: To get result as String.
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			// RestTemplate
			RestTemplate restTemplate = new RestTemplate();

			// Send request with GET method, and Headers.
			ResponseEntity<String> response = restTemplate.exchange(URL_Server_PREFIX, //
					HttpMethod.GET, entity, String.class);

			String result = response.getBody();
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

		}
	}

	public String YT_data_extractJSP() {
		session2 = ServletActionContext.getRequest().getSession();
		// logger.info("-- session 2==" + session2);

		if (session2 == null || session2.getAttribute("login") == null) {
			// logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			return SUCCESS;
		}
	}
	
	public String zip_data_extractJSP() {
		session2 = ServletActionContext.getRequest().getSession();
		// logger.info("-- session 2==" + session2);

		if (session2 == null || session2.getAttribute("login") == null) {
			// logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			return SUCCESS;
		}
	}
	
	

	public String serverURLHeaderAuthPHPTest(Map<String, String> ytData) {
		HttpHeaders headers = new HttpHeaders();

		prop = new Properties();
		configFile = "/details.properties";
		input = getClass().getResourceAsStream(configFile);
		try {
			prop.load(input);
			URL_Server_PREFIX = "http://172.168.1.25/zip1/index.php";

			database = prop.getProperty("webinforcement_demo");
			databaseIP = prop.getProperty("phpDBip");
			// databasePort = prop.getProperty("phpDBPort");
			databaseusr = prop.getProperty("phpDBusr");
			databasepasswd = prop.getProperty("phpDBPasswd");

		} catch (Exception e) {
		} finally {
			configFile = null;
			input = null;
			prop = null;
			serverIP = null;
			serverPORT = null;
		}
		logger.info(URL_Server_PREFIX);

		try {

			String input_Data_json = "";
			for (Map.Entry<String, String> videoId_with_id : ytData.entrySet()) {
				input_Data_json = input_Data_json + videoId_with_id.getKey() + ":" + videoId_with_id.getValue() + ",";
			}
			System.out.println(input_Data_json);

			input_Data_json = input_Data_json.substring(0, input_Data_json.length() - 1);

			headers.set("Authorization", input_Data_json);

			// headers.set("Authorization",
			// "{\"pj\":\"1\",\"pj1\":\"2\",\"pj3\":\"3\",\"pj4\":\"4\",\"pj5,\":\"5\"}");
			//
			//
			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			// Request to return JSON format
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("usr", databaseusr);
			headers.set("passwd", databasepasswd);
			headers.set("ip", databaseIP);
			headers.set("db", database);
			headers.set("date", "2018-04-23");
			headers.set("domain", "yahoo.com");

			// HttpEntity<String>: To get result as String.
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			// RestTemplate
			RestTemplate restTemplate = new RestTemplate();

			// Send request with GET method, and Headers.
			ResponseEntity<String> response = restTemplate.exchange(URL_Server_PREFIX, //
					HttpMethod.GET, entity, String.class);

			String result = response.getBody();
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			
			return "error";
		} finally {

		}
		return SUCCESS;
	}

	String database = null;
	String databaseIP = null;
	String databasePort = null;
	String databaseusr = null;
	String databasepasswd = null;
	HttpSession session2 = null;
}
