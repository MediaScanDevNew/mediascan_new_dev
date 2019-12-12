package com.markscan.project.classes.bot;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//import com.markscan.project.classes.screenshot.ScreenShotDetails;

public class BotStarter {

	public BotStarter() {

		// TODO Auto-generated constructor stub
	}

	//List<String> urlDetails=null;
	String configFile = null;
	String serverIP = null;
	String SServer1IP = null;
	String SServer2IP = null;
	String serverPORT = null;
	Properties prop = null;
	InputStream input = null;
	String URL_Server_PREFIX=null;
	private String imageNotCapture=null;
	Connection con= null;
	Statement stmt=null;
	ResultSet rs=null;
	public String getImageNotCapture() {
		return imageNotCapture;
	}

	

	private static final Logger logger = Logger.getLogger(BotStarter.class);
	
	
	public void botRun(String str) throws SQLException {
		//this.urlDetails=urlDetails;
		System.out.println(" STR VALUE IS ************ "+str);
		HttpHeaders headers = new HttpHeaders();
		int appRunningCount =0;
		prop = new Properties();
		configFile = "/details.properties";
		
		input = getClass().getResourceAsStream(configFile);
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
			 //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","root","root");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			 //con=DriverManager.getConnection("jdbc:mysql://172.168.1.6:3306/webinforcement_demo","myuser","P@$$@123++pj"); 
			 System.out.println("*******************************connection created..........................................");
			//here sonoo is database name, root is username and password  
		      stmt=con.createStatement();  
			 rs=stmt.executeQuery("SELECT DISTINCT ip_address from markscan_machine1 where status=0 LIMIT 1");
			 System.out.println("**********************************************Query Executed*******************");
			while(rs.next())
			{
				//System.out.println(rs.getInt(1)); 
				SServer1IP=	rs.getString(1);
				
			}
			/*
			while(SServer1IP==null)
			{
				Thread.sleep(150000);
				rs=stmt.executeQuery("SELECT DISTINCT ip_address from markscan_machine where status=0 LIMIT 1");
				 System.out.println("**********************************************Query Executed in While Loop*******************");
				
				while(rs.next())
				{
					System.out.println(rs.getInt(1));
					SServer1IP=	rs.getString(1);
				}
			
			}
			*/
			if(SServer1IP==null)
			{
				System.out.println("*******************************************Bot Server are bussy***************** "+SServer1IP);
			}
			else
			{
			prop.load(input);
			//if(SServer1IP==null)
			//{
				System.out.println("*******************************************First Machine***************** "+SServer1IP);
				//SServer2IP="172.168.1.9";
			//SServer1IP= prop.getProperty("SmServer2IP");
			logger.info("IP Address........................."+SServer1IP);
			// serverIP = prop.getProperty("SmServerIP");
			// serverPORT = prop.getProperty("SmServerPORT");
			//URL_Server_PREFIX = "http://" + prop.getProperty("SmServer2IP") + ":" + prop.getProperty("SmServer1PORT")
				//+ "/check";
			URL_Server_PREFIX = "http://" + SServer1IP + ":" + prop.getProperty("SmServer1PORT")
			+ "/check";
			stmt.executeUpdate("UPDATE markscan_machine1 SET status=1 WHERE ip_address='"+SServer1IP+"'");
					
		    System.out.println("IP Address............11111111111............."+URL_Server_PREFIX);
			logger.info("IP Address............22222222222222222............."+URL_Server_PREFIX);
			//logger.info("IP Address2........................."+SServer2IP);
			}
		}
			catch (Exception e) {
		} finally {
			configFile = null;
			input = null;
			prop = null;
			
			//serverIP = null;
			
			serverPORT = null;
		}
		
		logger.info(URL_Server_PREFIX);

		try {
			int count =1;

			String input_Data_json = "";
			//for (Iterator<String> videoId_with_id : urlDetails.iterator()) {
			//	input_Data_json = input_Data_json + count + ":" + videoId_with_id + ",";
			//}
			
		
				input_Data_json = input_Data_json + "" + str + ",";
				//count =count+1;
						
			//input_Data_json = input_Data_json.substring(0, input_Data_json.length() - 1);
			System.out.println("JSON Data................."+input_Data_json);

			headers.set("Authorization", input_Data_json);

			// headers.set("Authorization",
			// "{\"pj\":\"1\",\"pj1\":\"2\",\"pj3\":\"3\",\"pj4\":\"4\",\"pj5,\":\"5\"}");
			//
			//
			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			// Request to return JSON format
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.set("my_other_key", "my_other_value");

			// HttpEntity<String>: To get result as String.
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			// RestTemplate
			RestTemplate restTemplate = new RestTemplate();

			// Send request with GET method, and Headers.
			
			ResponseEntity<String> response = restTemplate.exchange(URL_Server_PREFIX, //
					HttpMethod.GET, entity, String.class);

			String result = response.getBody();
			System.out.println("ResponseEntity response value is ------------->"+result);
			imageNotCapture =result; 
			
			appRunningCount =appRunningCount-1;
		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			stmt.executeUpdate("UPDATE markscan_machine1 SET status=0 WHERE ip_address='"+SServer1IP+"'");
			System.out.println("Updating query..............................");
			SServer1IP=null;
			stmt.close();
			con.close();
			/*
			if(SServer1IP!=null)
				SServer1IP=null;
			else
				SServer2IP=null;
				
				*/

		}
	}
	

}
