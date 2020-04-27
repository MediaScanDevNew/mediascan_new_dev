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

public class IWLProcess implements Job{
	
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
	    	con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan"); 
			 //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo?useSSL=false","testuser","M@123rkscan"); 
			 if(con != null){
				 String sql = "select * from stored_project_setup1 where iwl_process=2";
				 System.out.println(sql);
				 ps = con.prepareStatement(sql);
				 rs = ps.executeQuery();
				 if(!rs.next()){
					 
					 String sql1 = "select id,projectId from stored_project_setup1 where completed = 1 and iwl_process=0 order by projectId limit 1";
					 System.out.println(sql);
					 ps1 = con.prepareStatement(sql1);
					 rs1 = ps1.executeQuery();
					 if(rs1.next()){
						 int id = rs1.getInt("id");
						 String otpurl = "http://172.168.1.13:8080/Crawler4SingleSite/startIWLNew?keyphaseId="+id;
						 System.out.println("Server URL is --->"+otpurl);
						 URL url = new URL(otpurl);
						 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						 conn.setRequestMethod("GET");
						 System.out.println("Server Response is --->"+conn.getResponseCode());
					 }
					 
				 }
				 
			 }
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IWL Process Start Failure..............");
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
