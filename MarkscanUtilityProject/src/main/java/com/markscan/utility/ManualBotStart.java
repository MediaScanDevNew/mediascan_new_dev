package com.markscan.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ManualBotStart implements Job{
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private PreparedStatement ps1 = null;
	private ResultSet rs1 = null;
	private PreparedStatement ps2 = null;
	private PreparedStatement ps3 = null;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan"); 
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo?useSSL=false","testuser","M@123rkscan"); 
				if(con != null){
					
					String sql = "select project_id,server_ip,server_port from manual_bot_logs where completed_flag='N'";
					System.out.println(sql);
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()){
						//System.out.println("11111111111111111111111111111111");
						String server_ip = rs.getString("server_ip");
						int project_id = rs.getInt("project_id");
						String server_port = rs.getString("server_port");
						
						String sql1 = "SELECT id FROM manual_bot_logs where project_id ="+project_id+" and process_flag='N'";
						System.out.println(sql1);
						ps1 = con.prepareStatement(sql1);
						rs1 = ps1.executeQuery();
						if(rs1.next()){
							 //System.out.println("11111111111111111111111111111111");
							 
							 String sql2 = "Update manual_bot_logs set process_flag='Y' where project_id ="+project_id+"";
							 ps3 = con.prepareStatement(sql2);
							 ps3.executeUpdate();
							 
							 String otpurl = "http://"+server_ip+":9080/BOTSE2/check?pId="+project_id+"&SServer1IP="+server_ip;
							 System.out.println("Server URL is --->"+otpurl);
							 URL url = new URL(otpurl);
							 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
							 conn.setRequestMethod("GET");
							 System.out.println("Server Response is --->"+conn.getResponseCode());
						}
					}
					
				}
			
		  System.out.println("Manual BOT request start successfully..............");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Manual BOT request start failure..............");
		}finally{
			try{
				if(con !=null){con.close();}
				if(ps !=null){ps.close();}
				if(ps1 !=null){ps1.close();}
				if(ps2 !=null){ps2.close();}
				if(ps3 !=null){ps3.close();}
				if(rs !=null){rs.close();}
				if(rs1 !=null){rs1.close();}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}

}
