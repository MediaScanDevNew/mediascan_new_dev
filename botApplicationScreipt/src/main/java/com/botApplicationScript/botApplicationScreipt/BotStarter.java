package com.botApplicationScript.botApplicationScreipt;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

import com.botApplicationScript.datasource.DataSource;

//import com.markscan.project.classes.screenshot.ScreenShotDetails;

public class BotStarter extends Thread {

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
	String sport="8080";
	String cdate=null;
	
	public void run()
	{
		
		try {
			current_date();
			DataSource d =new DataSource();
			d.getCon();
			
			
		      stmt=con.createStatement(); 
		     String query1 ="select sps.keyphrase  from stored_project_setup1 sps where completed=0 and (sps.created_on between '"+cdate+" 00:00:00' and '"+cdate+" 23:59:59')";
		     System.out.println("******************************query:  "+query1);
			 rs=stmt.executeQuery("select sps.keyphrase "
			+ " from stored_project_setup1 sps where completed=0 and (sps.created_on between '"+cdate+" 00:00:00' and '"+cdate+" 23:59:59')");
			 System.out.println("**********************************************Query Executed*******************");
		     if(rs.next()==false)
		     {
		    	 System.out.println("******************************No query Available*************************");
		    	 rs.close();
		    	 stmt.close();
		    	 con.close();
		    	 rs=null;
		    	 stmt=null;
		    	 con=null;
		    	 System.exit(0);
		     }
		    
	    	 
			String result= botRun("1");
			System.out.println("*****************************"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
			 
		}
		
		
	}
	public  void current_date()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date=new Date();
	    cdate = simpleDateFormat.format(date);
	    
		System.out.println(cdate);
	}
	

	//private static final Logger logger = Logger.getLogger(BotStarter.class);
	
	
	synchronized public String botRun(String str) throws SQLException {
		System.out.println("==============Run Method==============");
		// this.urlDetails=urlDetails;
		HttpHeaders headers = new HttpHeaders();
		int appRunningCount = 0;
		prop = new Properties();
		configFile = "/details.properties";

		input = getClass().getResourceAsStream(configFile);

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Thread.sleep(7000);
			// con=DriverManager.getConnection("jdbc:mysql://172.168.1.25:3306/webinforcement_demo","root","root");

			con = DriverManager.getConnection("jdbc:mysql://172.168.1.6:3306/webinforcement_demo", "myuser",
					"P@$$@123++pj");
			System.out.println(
					"*******************************connection created..........................................");
			// here sonoo is database name, root is username and password
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT  ip_address from markscan_machine where status=0");
			System.out.println("**********************************************Query Executed*******************");
			if (rs.next() == false) {
				System.out.println("===========IF Condition Execute==========");
				con.close();
				System.exit(0);
			}
			while (rs.next()) {
				System.out.println(rs.getString(1));
				SServer1IP = rs.getString(1);

			}

			stmt.executeUpdate("UPDATE markscan_machine SET status=1 WHERE ip_address='" + SServer1IP + "'");
			URL_Server_PREFIX = "http://" + SServer1IP + ":" + sport + "/check";

		} catch (Exception e) {

		} finally {
			configFile = null;
			input = null;
			prop = null;
			serverPORT = null;
		}

		try {
			int count = 1;

			String input_Data_json = "";
			input_Data_json = input_Data_json + "" + str + ",";
			headers.set("Authorization", input_Data_json);
			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			// RestTemplate
			RestTemplate restTemplate = new RestTemplate();

			// Send request with GET method, and Headers.

			ResponseEntity<String> response = restTemplate.exchange(URL_Server_PREFIX, //
					HttpMethod.GET, entity, String.class);

			String result = response.getBody();
			System.out.println(result);
			imageNotCapture = result;
			stmt.executeUpdate("UPDATE markscan_machine SET status=0 WHERE ip_address='" + SServer1IP + "'");
			// con.close();

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			try {
				con.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				con.close();
			}
			SServer1IP = null;

		}
		return "success";
	}

}
