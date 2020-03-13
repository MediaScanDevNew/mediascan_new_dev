package com.markscan.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CreateKeyPhaseForTV implements Job {

	private String cdate;
	private String ctime;
	private Connection con = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private int projectId;
	private String project_name;
	private String start_date;
	private String end_date;
	private String ttime;
	private String bttime;
	private String bttime1;
	private String bttime2;
	private String afttime;
	private String keyphrase;

	private Date sDate;
	private Date eDate;
	private Date date;
	private String days = "";

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			current_date();
			Class.forName("com.mysql.jdbc.Driver");
	    	//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan"); 
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo?useSSL=false","testuser","M@123rkscan"); 
			 
			String query = "select DISTINCT pif.id, pif.project_name, pif.start_date, pif.end_date, pif.ttime from project_info pif, tv_content_tdays tvc "
					+ "where pif.id=tvc.projectId and pif.ttime is not null and pif.closed=0 and pif.id not in "
					+ "(SELECT distinct projectId FROM webinforcement_demo.stored_project_setup1 where "
					+ "date_format(created_on,'%d-%-m-%y') = date_format(now(),'%d-%-m-%y'))";
			//System.out.println("============>" + query);
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				projectId = rs.getInt(1);
				project_name = rs.getString(2);
				start_date = rs.getString(3);
				end_date = rs.getString(4);
				ttime = rs.getString(5);
				//System.out.println("====1111111111111111111111111111========>" );
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMMM-yyyy");
				String curdate = sdf1.format(date);
				keyphrase = project_name + " episode " + curdate;
				String query1 = "select telecast_days from  tv_content_tdays where projectId =" + projectId;
				//System.out.println("query1========>"+query1 );
				ps1 = con.prepareStatement(query1);
				rs1 = ps1.executeQuery();
				while (rs1.next()) {
					String day = rs1.getString(1);
					//System.out.println("day is ------->"+day);
					//System.out.println("days is ------->"+days);
					if (day.equals(days)) {
						//System.out.println("Time............." + ttime);
						setTelecastTime(ttime,con);
					}
				}
				
			}

		} catch (Exception e) {
            e.printStackTrace();
			//System.out.println(e);

		} finally {
			try {
				if(con != null){con.close();}
				if(ps1 != null){ps1.close();}
				if(rs != null){rs.close();}
				if(rs1 != null){rs1.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		} // end finally

	}// end execute job here....

	private void setTelecastTime(String myTime,Connection con) {
		try {
			Date d = new Date();

			if (myTime.contains("pm")) {
				SimpleDateFormat df = new SimpleDateFormat("HH:mm");
				d = df.parse(myTime);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				cal.add(Calendar.HOUR, 12);
				myTime = df.format(cal.getTime());

			}

			int cthour = hoursConvertor(ctime);
			int cthour1 = cthour + 1;
			int getHour = hoursConvertor(myTime);
			int getHour1 = getHour + 2;
			int getHour2 = getHour + 4;
			System.out.println("getHour1 value is ----------------->"+getHour1);
			System.out.println("getHour1 value is ----------------->"+cthour);
			if (getHour1 == cthour) {
				setQueryToProjectSetup(projectId, keyphrase,con);
			}

		} catch (Exception e) {

		} finally {

		}
	}

	private int hoursConvertor(String time) {
		String time1[] = time.split(":");
		int hour = Integer.parseInt(time1[0]);
		return hour;
	}

	private void setHoursTime(String time) {
		String time1[] = time.split(":");
		int hour = Integer.parseInt(time1[0]);
		int min = Integer.parseInt(time1[1]);
		hour = hour - 24;
		String time2 = hour + ":" + min;
	}

	private void setQueryToProjectSetup(int projectId, String pname,Connection con) {
		try {
			String query2 = "insert into stored_project_setup1(keyphrase,userId,projectId) values (?,?,?)";
			psmt = con.prepareStatement(query2);
			psmt.setString(1, pname);
			psmt.setInt(2, 1);
			psmt.setInt(3, projectId);
			psmt.executeUpdate();
			// con.close();
		} catch (Exception e) {
			//System.out.println(e);

		} finally {
			if(psmt != null){try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}}
		}
	}
	
	
	private  void current_date()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    date=new Date();
	    cdate = simpleDateFormat.format(date);
	    
		System.out.println(cdate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
		 LocalTime time = LocalTime.now();
		 ctime= time.format(formatter);
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		switch(day)
		{
		case 1:
			days="sun";
			break;
			
		case 2:
			days="mon";
			break;
		case 3:
			days="tue";
			break;
		case 4:
			days="wed";
			break;
		case 5:
			days="thu";
			break;
		case 6:
			days="fri";
			break;
		case 7:
			days="sat";
			break;
		
		}
		System.out.println("week of day.........."+days);
		 
		 //System.out.println(ctime);
	
	}

}
