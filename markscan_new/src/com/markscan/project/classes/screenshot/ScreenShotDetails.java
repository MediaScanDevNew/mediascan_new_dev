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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.markscan.project.beans.Screenshot_machines;
import com.markscan.project.classes.ActionPerform;
import com.markscan.project.dao.Screenshot_machinesDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
//@Path("/screenshot")
public class ScreenShotDetails implements Runnable {
	
	public ScreenShotDetails() {}
	
	public ScreenShotDetails(String uri, HttpEntity<String> httpEntity) {				//KK
		this.uri = uri;
		this.httpEntity = httpEntity;
	}
	
	private String uri;
	private HttpEntity<String> httpEntity;
	private String response = null;
	
	ClientHttpRequestFactory requestFactory	= new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
    RestTemplate screenshotRestCall = new RestTemplate(requestFactory);
    
    ExecutorService service	= null; 
    
	List<String> urlDetails=null;
	ArrayList<String> screenshotMachine = null;																	// KK
	ArrayList<String> SCREENSHOT_SERVER_AVAILABLE = null;														// KK
	String configFile = null;
	
	String SServer1IP;
	//String SServer2IP = null;
	String serverPORT = null;
	Properties prop = null;
	InputStream input = null;
	String URL_Server_PREFIX=null;
	private String imageNotCapture=null;
	Connection con= null;
	Statement stmt=null;
	ResultSet rs=null;
	Screenshot_machinesDao smd=null;
	//Screenshot_machines sm=null;
	BeanFactory factory = null;
	List lst=null;
	Object obj[]=null;
	
	public String getImageNotCapture() {
		return imageNotCapture;
	}

	private static final Logger logger = Logger.getLogger(ScreenShotDetails.class);
	
	
	public void setUrlList(List<String> urlDetails) throws SQLException {
		
		/*
		
		Screenshot_machines sm =new Screenshot_machines();
		HttpHeaders headers = new HttpHeaders();
		int appRunningCount =0;
		prop = new Properties();
		configFile = "/details.properties";
		try
		{
		
		input = getClass().getResourceAsStream(configFile);
		factory = ActionPerform.getFactory();
		System.out.println("=====================harry===============");
		smd =(Screenshot_machinesDao)factory.getBean("d38");
		lst =smd.viewRecord("SELECT sm.ip  from Screenshot_machines as sm where s_status=0");
		System.out.println("=====================harry1===============");
		//for(int i=0;i<=)
		//SServer1IP =(String)sm_details.get(0);
		
		System.out.println("=====================harry index==============="+lst.indexOf("172.168.1.25"));
		
		   
			obj = (Object[]) lst.get(0);
			sm.setIp((String)obj[0]);
			System.out.println("=====================harry index==============="+obj.toString());
			SServer1IP =sm.getIp();
			//System.out.println("=====================harry index==============="+);
			//obj = null;
			System.out.println("=====================SServer1IP==============="+SServer1IP);
		   obj=null;
		   sm=null;
		
		while(SServer1IP==null)
		{
			Thread.sleep(150000);
			setUrlList(urlDetails);
		
		}
		
			prop.load(input);
			
				System.out.println("*******************************************First Machine***************** "+SServer1IP);
				
			logger.info("IP Address........................."+SServer1IP);
			
			URL_Server_PREFIX = "http://" + SServer1IP + ":" + prop.getProperty("SmServer2PORT")
			+ "/check";
			smd.customUpdate("UPDATE Screenshot_machines SET s_status=1 WHERE ip='"+SServer1IP+"'");
			
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
			
			
			e.printStackTrace();
			
		} finally 
		{
			smd.customUpdate("UPDATE Screenshot_machines SET s_status=1 WHERE ip='"+SServer1IP+"'");
			System.out.println("Updating query..............................");
			SServer1IP=null;
			 serverPORT = null;
			 prop = null;
			 URL_Server_PREFIX=null;
			 imageNotCapture=null;
			
			smd=null;
			// sm=null;
			factory = null;
			//sm_details=null;
			obj=null;
			 obj=null;
			  sm=null;
		
			
		}
	}
	
		 
		*/ 
		 
		
		
		//this.urlDetails=urlDetails;
//		HttpHeaders headers = new HttpHeaders();
//		int appRunningCount = 0;
		
		prop = new Properties();
		configFile = "/details.properties";
		
		input = getClass().getResourceAsStream(configFile);
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","root","root"); 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","root","Pent@1234"); 
//			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","root","Pent@1234"); 
			 System.out.println("*******************************connection created..........................................");
			//here sonoo is database name, root is username and password  
		      stmt=con.createStatement();  
//			 rs=stmt.executeQuery("SELECT DISTINCT * from screenshot_machines where s_status=0 LIMIT 1");	// commented By KK
			 rs=stmt.executeQuery("SELECT DISTINCT * from screenshot_machines where s_status=0");
			 System.out.println("**********************************************Query Executed*******************");
			 
			 screenshotMachine = new ArrayList<String>();
			while(rs.next()) {
				logger.info("available screenShot VM : "+ rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				System.out.println("available screenShot VM : "+rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
//				SServer1IP=	rs.getString(2);
				screenshotMachine.add(rs.getString("ip").toString().trim());
			}
			
/*			while (SServer1IP == null) {						// commented by KK for new Functionality
				Thread.sleep(150000);
				rs = stmt.executeQuery("SELECT DISTINCT * from screenshot_machines where s_status=0 LIMIT 1");
				System.out.println(
						"**********************************************Query Executed in While Loop*******************");

				while (rs.next()) {
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
					SServer1IP = rs.getString(2);
				}

			} */
			
				while (screenshotMachine == null || screenshotMachine.size() == 0) {
					Thread.sleep(150000);
					rs = stmt.executeQuery("SELECT DISTINCT * from screenshot_machines where s_status=0");
					System.out.println("**********************************Query Executed in While Loop*******************");

					while (rs.next()) {
						logger.info("waited for available screenShot VM : "+ rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
						System.out.println("waited for available screenShot VM : "+rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
						screenshotMachine.add(rs.getString("ip").toString().trim());
					}

				}
			prop.load(input);
			
//				System.out.println("*******************************************First Machine***************** "+SServer1IP);
				
//			logger.info("IP Address........................."+SServer1IP);
			logger.info("Available IP Address : "+screenshotMachine);
			System.out.println("Available IP Address : "+ screenshotMachine);
			
//			URL_Server_PREFIX = "http://" + SServer1IP + ":" + prop.getProperty("SmServer2PORT") + "/check";
//			stmt.executeUpdate("UPDATE screenshot_machines SET s_status=1 WHERE ip='"+SServer1IP+"'");
			
			int temp = 1;
			SCREENSHOT_SERVER_AVAILABLE	= new ArrayList<String>();
			for(String ip : screenshotMachine) {
				URL_Server_PREFIX = "http://" + ip +":"+ prop.getProperty("SmServer2PORT") + "/check";
				SCREENSHOT_SERVER_AVAILABLE.add(URL_Server_PREFIX);
				int status = stmt.executeUpdate("UPDATE screenshot_machines SET s_status=1 WHERE ip='"+ip+"'");
				logger.info("Screenshot Update Status "+ip +" : "+ status);
				
				logger.info("IP Address : "+URL_Server_PREFIX);
				System.out.println("IP Address : "+URL_Server_PREFIX);
			}
			System.out.println("SCREENSHOT_SERVER_AVAILABLE : "+ SCREENSHOT_SERVER_AVAILABLE);
		}
			catch (Exception e) {
		} finally {
			configFile = null;
			input = null;
			prop = null;
		   serverPORT = null;
		   URL_Server_PREFIX = null;
		}
		
		logger.info("SCREENSHOT_SERVER_URL : "+SCREENSHOT_SERVER_AVAILABLE);

		try {
//			int count =1;
//			String input_Data_json = "";
//			Iterator<String> videoId_with_id =urlDetails.iterator();
				
			List<List<String>> jobsForScreenshot = chopIntoParts(urlDetails, SCREENSHOT_SERVER_AVAILABLE.size());
			System.out.println("JOBSFORSCREENSHOT : "+ jobsForScreenshot);
			logger.info("JOBSFORSCREENSHOT : "+ jobsForScreenshot);
			
			String screenshotJobs 			= "";
			HttpHeaders head	  			= null;
			HttpEntity<String> httpEntity 	= null;
			ArrayList<String> domainsList	= new ArrayList<String>();
			
			for(int i = 0, listSize = jobsForScreenshot.size(); i < listSize; i++) {
	        	
	        	for(String domain : jobsForScreenshot.get(i)) {
	        		screenshotJobs = screenshotJobs + domain+",";
	        	}
	        	
	        	screenshotJobs = screenshotJobs.substring(0, screenshotJobs.length()-1);
	        	
	        	System.out.println("Link for screenshot : "+ screenshotJobs);
	        	logger.info("Link for screenshot : "+ screenshotJobs);	 
	        	
	        	domainsList.add(screenshotJobs);
	        	screenshotJobs = "";
			}
			System.out.println("DomainList : "+ domainsList);
			logger.info("DomainList : "+ domainsList + " : Size :: " + domainsList.size());
			
	        int count 	= 0;
	        head 		= new HttpHeaders();
	        
	        service		= Executors.newWorkStealingPool();
	        
			for(String domain : domainsList) {
				try {

					System.out.println("Domain : "+ domain + " >> Count : "+ count );
					logger.info("Domain : "+ domain + " >> Count : "+ count);
					
					head.set("Authorization", domain);
					head.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
					head.setContentType(MediaType.APPLICATION_JSON);
					
					httpEntity = new HttpEntity<String>(head);
					
					System.out.println("SCREENSHOT_MACHINE : "+ SCREENSHOT_SERVER_AVAILABLE.get(count));
					
					try {
						service.execute(new ScreenShotDetails(SCREENSHOT_SERVER_AVAILABLE.get(count++), httpEntity));
						
					} catch(IllegalThreadStateException itse) {
						System.out.println("IllegalThreadStateException : "+ itse);
						logger.error("IllegalThreadStateException : "+ itse);

					} catch(Exception ex) {
						System.out.println("Exception Occured : "+ ex);
						logger.error("Exception occured : "+ ex); 
					}					
					
				} catch(HttpServerErrorException hsee) {
					logger.info("HttpServerErrorException : ", hsee);
					hsee.printStackTrace();
					
				} catch(Exception ex) {
					logger.info("Exception : ", ex);
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.info("Exception Occured in ScreenShotDetails : ", e);
			
		}
		
		finally {
			try {				
				for(String ip : screenshotMachine) {
					stmt.executeUpdate("UPDATE screenshot_machines SET s_status=0 WHERE ip='"+ip+"'");
					System.out.println("SCREENSHOT_MACHINE SERVER : " + ip + " STARVING !!");
					logger.info("SCREENSHOT_MACHINE SERVER : " + ip + " IS FREE & STARVING FOR JOB !!");
				}
				
				SServer1IP=null;
				screenshotMachine.clear();
				screenshotMachine = null;
				
				if(!service.isTerminated()) {
					service.shutdown();
					logger.info("Worker Threads been shutdown !! ");
				}
				
				stmt.close();
				con.close();
			} catch(Exception ex) {
				logger.info("Exception Occured in ScreenShotDetails Finally Block : "+ ex);
			}
		}
	}
	
	public static <T>List<List<T>> chopIntoParts( final List<T> ls, final int iParts) {
        final List<List<T>> lsParts = new ArrayList<List<T>>();
        final int iChunkSize 		= ls.size() / iParts;
        int iLeftOver 				= ls.size() % iParts;
        int iTake 					= iChunkSize;

        for( int i = 0, iT = ls.size(); i < iT; i += iTake ) {
            if( iLeftOver > 0 ) {
                iLeftOver--;

                iTake = iChunkSize + 1;
            }
            else {
                iTake = iChunkSize;
            }

            lsParts.add( new ArrayList<T>( ls.subList( i, Math.min( iT, i + iTake ) ) ) );
        }

        return lsParts;
    }

	@Override
	public void run() {
		try {
			 response = screenshotRestCall.postForObject(this.uri, this.httpEntity, String.class);
			 
			System.out.println("Response back : "+ response);
			logger.info("Response back : "+ response);
			
			imageNotCapture = response;
			
		} catch (NoSuchMethodError nsme) {
			System.err.println("NoSuchMethodError : "+ nsme +" :: Thread Name : "+ Thread.currentThread().getName());
			logger.error("NoSuchMethodError : "+ nsme +" :: Thread Name : "+ Thread.currentThread().getName());
			
		} catch (Exception ex) {
			System.out.println("Exception Occured in Run Method : "+ ex +" :: Thread Name : "+ Thread.currentThread().getName());
			logger.error("Exception Occured in Run Method : "+ ex +" :: Thread Name : "+ Thread.currentThread().getName());
		}
	}

}
