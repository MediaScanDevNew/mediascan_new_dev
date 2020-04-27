package com.markscan.utility;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BOTStarterJob implements Job {
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private PreparedStatement ps1 = null;
	private ResultSet rs1 = null;
	private PreparedStatement ps2 = null;
	private String sport="9080"; // change port by Pentation/M 01-02-2020 (here we define BOTSE application running port here....)

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date=new Date();
	    String cdate = simpleDateFormat.format(date);
	    
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan"); 
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo?useSSL=false","testuser","M@123rkscan"); 
			if(con != null){
				
				String sql = "select count(id) countVal  from stored_project_setup1 sps where completed in (0,2) "
						     /*+ "and (sps.created_on "
							 + "between '"+cdate+" 00:00:00' and '"+cdate+" 23:59:59')"*/;
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next()){
					 String sql1 = "SELECT  ip_address,id from markscan_machine1 where status=? and port=? limit 1";
					 ps1 = con.prepareStatement(sql1);
					 ps1.setInt(1, 0);
					 ps1.setString(2, "8083");
					 rs1 = ps1.executeQuery();
					 if(rs1.next()){
						 	String SServer1IP=	rs1.getString(1); 
						 	//String SServer1IP=	"127.0.0.1";
						 	String otpurl = "http://" + SServer1IP + ":" + sport+ "/BOTSE/check";;
							System.out.println(otpurl);
							URL url = new URL(otpurl);
							HttpURLConnection conn = (HttpURLConnection) url.openConnection();
							conn.setRequestMethod("GET");
							int respnse_val = conn.getResponseCode();
							System.out.println("response value is -------->"+respnse_val);
							/*if(respnse_val == 200){
								String sql2 = "UPDATE markscan_machine1 SET status=? WHERE ip_address=? and port=?";
								ps2 = con.prepareStatement(sql2);
								ps2.setInt(1, 1);
								ps2.setString(2, SServer1IP);
								ps2.setString(3, "8083");
								ps2.executeUpdate();
							}*/
						 
					 }
				}
			}
			System.out.println("BOT request send successfully..............");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("BOT request send failure..............");
		}finally{
			try{
				if(con !=null){con.close();}
				if(ps !=null){ps.close();}
				if(ps1 !=null){ps1.close();}
				if(ps2 !=null){ps2.close();}
				if(rs !=null){rs.close();}
				if(rs1 !=null){rs1.close();}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
