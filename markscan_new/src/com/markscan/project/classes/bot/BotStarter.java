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
	
	
	public String botRun() throws SQLException {
		//this.urlDetails=urlDetails;
		//System.out.println(" STR VALUE IS ************ "+str);
		
		HttpHeaders headers = new HttpHeaders();
		int appRunningCount =0;
		prop = new Properties();
		configFile = "/details.properties";
		
		input = getClass().getResourceAsStream(configFile);
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
			 //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","root","root");
			 
			 //con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan"); 
			 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			
			 
			 stmt=con.createStatement();  
			 rs=stmt.executeQuery("SELECT DISTINCT ip_address from markscan_machine1 where status=0 and port='8089' LIMIT 1");
			 System.out.println("**********************************************Query Executed*******************");
			while(rs.next())
			{
				SServer1IP=	rs.getString(1);
				
			}
			
			// codebase block 05.04.2020------------------------
				/*if(SServer1IP==null)
				{
					
				}
				else
				{
				prop.load(input);
				
				logger.info("IP Address........................."+SServer1IP);
				URL_Server_PREFIX = "http://" + SServer1IP + ":" + prop.getProperty("SmServer1PORT")
				+ "/BOTSE2/check";
				stmt.executeUpdate("UPDATE markscan_machine1 SET status=1 WHERE ip_address='"+SServer1IP+"' and port='8089'");
				logger.info("IP Address............22222222222222222............."+URL_Server_PREFIX);
				
				}*/
			// codebase block 05.04.2020------------------------
		 }
			catch (Exception e) {
				e.printStackTrace();
		} finally {
			configFile = null;
			input = null;
			prop = null;
			serverPORT = null;
			if(con != null){con.close();}
			if(stmt != null){stmt.close();}
			if(rs != null){rs.close();}
		}
		
		// codebase block 05.04.2020------------------------
		/*logger.info(URL_Server_PREFIX);
			try {
				int count =1;
	
				//String input_Data_json = "";
				//input_Data_json = input_Data_json + "" + str + ",";
				headers.set("Authorization", str);
				headers.set("SServer1IP", SServer1IP);
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> response = restTemplate.exchange(URL_Server_PREFIX, //
						HttpMethod.GET, entity, String.class);
	
				String result = response.getBody();
				//System.out.println("ResponseEntity response value is ------------->"+result);
				imageNotCapture =result; 
				
				appRunningCount =appRunningCount-1;
			} catch (Exception e) 
			{
				e.printStackTrace();
			} finally 
			{
	
	
			}*/
		// codebase block 05.04.2020------------------------
		
		return SServer1IP;
	}
	
	
// New method introduce 02.04.2020	
	
	
	public int checkBotFree() throws SQLException{
		int k = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			stmt=con.createStatement();  
			rs=stmt.executeQuery("SELECT count(*) as count from markscan_machine1 where status in (1,2) and port='8089'");
			if(rs.next()){
				k = rs.getInt("count");
			}	
			
		}catch (Exception e) {
		} finally {
			if(con != null){con.close();}
			if(stmt != null){stmt.close();}
			if(rs != null){rs.close();}
		}
		
		return k;
	}
	
	
	public void insertManualBotlogs(int projectId,String server_ip,String port) throws SQLException{
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			stmt=con.createStatement();  
			String sql = "insert into manual_bot_logs(project_id,server_ip,server_port) values ("+projectId+",'"+server_ip+"','"+port+"')";
			System.out.println("-------------------->"+sql);
			stmt.executeUpdate(sql);			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){con.close();}
			if(stmt != null){stmt.close();}
			if(rs != null){rs.close();}
		}
		
		
	}
	
	public int checkProjectExistOrNot(int projectId) throws SQLException{
		int k = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			stmt=con.createStatement();  
			String sql = "select id from manual_bot_logs where project_id="+projectId+" and completed_flag='N'";
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				k = rs.getInt("id");
			}
			
		}catch (Exception e) {
		} finally {
			if(con != null){con.close();}
			if(stmt != null){stmt.close();}
			if(rs != null){rs.close();}
		}
		
		return k;
	}
	
	
	public int cheked(String server_ip) throws SQLException{
		int k = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			stmt=con.createStatement();  
			String sql = "select id from manual_bot_logs where server_ip='"+server_ip+"' and completed_flag='N'";
			System.out.println("-------------------->"+sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()){k = rs.getInt("id");}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){con.close();}
			if(stmt != null){stmt.close();}
			if(rs != null){rs.close();}
		}
	  return k; 	
	}
	
	
	public String getProjectNm(String server_ip) throws SQLException{
		String project_nm = "";
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			stmt=con.createStatement();  
			String sql = "select project_name from project_info where id in (select project_id from manual_bot_logs where server_ip='"+server_ip+"' and completed_flag='N')";
			System.out.println("-------------------->"+sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()){project_nm = rs.getString("project_name");}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){con.close();}
			if(stmt != null){stmt.close();}
			if(rs != null){rs.close();}
		}
	  return project_nm; 	
	}
	
	
	public void UpdateBOTMachine(String server_ip) throws SQLException{
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo","testuser","M@123rkscan"); 
			stmt=con.createStatement();  
			String sql = "UPDATE markscan_machine1 SET status='2' WHERE ip_address='"+server_ip+"'";
			System.out.println("-------------------->"+sql);
			stmt.executeUpdate(sql);			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){con.close();}
			if(stmt != null){stmt.close();}
			if(rs != null){rs.close();}
		}
		
		
	}
	
}
