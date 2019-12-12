package com.markscan.project.classes.screenshot;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
//@Path("/screenshot")
public class ScreenShotDetails 
{
	List<String> urlDetails=null;
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

	

	private static final Logger logger = Logger.getLogger(ScreenShotDetails.class);
	//List <String> urls=new ArrayList<String>();
	
	/*
	public static void main(String q[]) throws SQLException
	{
		List<String> als =new ArrayList<String>();
		als.add("/https://www.youtube.com/watch?v=lrJUEsowuBc");
		//als.add("https://www.facebook.com/kiyas.cool.7/videos/1427126064220805/") ;
		//als.add("https://www.facebook.com/kiyas.cool.7/videos/1426431524290259/8");
		//als.add("https://www.youtube.com/watch?v=FyQ3tOumRew/");
		//als.add("12323");
		ScreenShotDetails ssd = new ScreenShotDetails();
		ssd.setUrlList(als);
	}
	

	public void getURLDetails(Crawle_url2 crawle_url2)
	{
		logger.info("****************************Screensot code***************************");
		logger.info(crawle_url2.getId());
		logger.info(crawle_url2.getCrawle_url2());
		logger.info(crawle_url2.getDomain_name());
		logger.info(crawle_url2.getCreated_on());
		logger.info(crawle_url2.getW_list());
		logger.info(crawle_url2.getIs_valid());
		logger.info(crawle_url2.getLink_logger());
		logger.info(crawle_url2.getLink_type());
		logger.info(crawle_url2.getNote1());
		logger.info(crawle_url2.getNote2());
		logger.info(crawle_url2.getProject_id());
		urls.add(crawle_url2.getCrawle_url2());
	}
	
	
	public void setUrlList(List <String> url2)
	{
		urls.addAll(url2);
	}
	*/
	/*
	public List<String> getUrlList()
	{
		Response rs=null;
		
		logger.info("****************************Screensot code***************************");
		logger.info(urls);
		return urls;
	}
	
	*/
	
	/*
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUrlList()
	{
		//Response rs=null;
		
		logger.info("****************************Screensot code***************************");
		logger.info(urls);
		//return urls;
		 return Response.status(200).entity(urls).build();
	}
	*/
	
	public void setUrlList(List<String> urlDetails) throws SQLException {
		//this.urlDetails=urlDetails;
		HttpHeaders headers = new HttpHeaders();
		int appRunningCount =0;
		prop = new Properties();
		configFile = "/details.properties";
		
		input = getClass().getResourceAsStream(configFile);
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","root","root"); 
			// con=DriverManager.getConnection("jdbc:mysql://172.168.1.54:3306/webinforcement_demo","root","Markscan@1234"); 
			 //con=DriverManager.getConnection("jdbc:mysql://172.168.1.6:3306/webinforcement_demo","myuser","P@$$@123++pj"); 
			 System.out.println("*******************************connection created..........................................");
			//here sonoo is database name, root is username and password  
		      stmt=con.createStatement();  
			 rs=stmt.executeQuery("SELECT DISTINCT * from screenshot_machines where s_status=0 LIMIT 1");
			 System.out.println("**********************************************Query Executed*******************");
			while(rs.next())
			{
				//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
				SServer1IP=	rs.getString(2);
			}
			while(SServer1IP==null)
			{
				Thread.sleep(150000);
				rs=stmt.executeQuery("SELECT DISTINCT * from screenshot_machines where s_status=0 LIMIT 1");
				 System.out.println("**********************************************Query Executed in While Loop*******************");
				
				while(rs.next())
				{
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
					SServer1IP=	rs.getString(2);
				}
			
			}
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
			URL_Server_PREFIX = "http://" + SServer1IP + ":" + prop.getProperty("SmServer2PORT")
			+ "/check";
			stmt.executeUpdate("UPDATE screenshot_machines SET s_status=1 WHERE ip='"+SServer1IP+"'");
					
			//}
			/*
			else
			{
				System.out.println("*******************************************Second Machine***************** ");
				//prop.load(input);
				SServer2IP= prop.getProperty("SmServer1IP");
				logger.info("IP Address2........................."+SServer2IP);
				// serverIP = prop.getProperty("SmServerIP");
				// serverPORT = prop.getProperty("SmServerPORT");
				//URL_Server_PREFIX = "http://" + prop.getProperty("SmServer2IP") + ":" + prop.getProperty("SmServer1PORT")
					//+ "/check";
				URL_Server_PREFIX = "http://" + SServer2IP + ":" + prop.getProperty("SmServer1PORT")
				+ "/check";
			//}
			 * 
			 */
			logger.info("IP Address........................."+URL_Server_PREFIX);
			//logger.info("IP Address2........................."+SServer2IP);
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
			Iterator<String> videoId_with_id =urlDetails.iterator();
			while(videoId_with_id.hasNext())
			{
				input_Data_json = input_Data_json + "" + videoId_with_id.next() + ",";
				count =count+1;
			}
			
			input_Data_json = input_Data_json.substring(0, input_Data_json.length() - 1);
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
			System.out.println(result);
			imageNotCapture =result; 
			
			appRunningCount =appRunningCount-1;
			//con.close();
		} catch (Exception e) 
		{
			//appRunningCount =appRunningCount+1;
			//this.setUrlList(urlDetails);
			
		//	*************************Other ip ********************************* 
			
			
			e.printStackTrace();
			/*
			try {
				prop.load(input);
				// serverIP = prop.getProperty("SmServerIP");
				// serverPORT = prop.getProperty("SmServerPORT");
				URL_Server_PREFIX = "http://" + prop.getProperty("SmServer1IP") + ":" + prop.getProperty("SmServer1PORT")
						+ "/check";
			} catch (Exception e1) {
			} 
			
		}
			finally {
				configFile = null;
				input = null;
				prop = null;
				serverIP = null;
				serverPORT = null;
			}
			logger.info(URL_Server_PREFIX);

			try {
				int count =1;

				String input_Data_json = "";
				//for (Iterator<String> videoId_with_id : urlDetails.iterator()) {
				//	input_Data_json = input_Data_json + count + ":" + videoId_with_id + ",";
				//}
				Iterator<String> videoId_with_id =urlDetails.iterator();
				while(videoId_with_id.hasNext())
				{
					input_Data_json = input_Data_json + "" + videoId_with_id.next() + ",";
					count =count+1;
				}
				
				input_Data_json = input_Data_json.substring(0, input_Data_json.length() - 1);
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
				System.out.println(result);

			}
			catch(Exception e2)
			{
				
			}
			
			// TODO: handle exception
			 * 
			 */
		} finally 
		{
			stmt.executeUpdate("UPDATE screenshot_machines SET s_status=0 WHERE ip='"+SServer1IP+"'");
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
