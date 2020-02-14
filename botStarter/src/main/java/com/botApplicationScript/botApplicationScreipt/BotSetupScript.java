package com.botApplicationScript.botApplicationScreipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import com.botApplicationScript.datasource.DataSource;;

public class BotSetupScript
{
	String cdate;
	String ctime;
	Connection con=null;
    PreparedStatement psmt=null;
	ResultSet rs=null;
	int projectId;
	String project_name;
	String start_date;
	String end_date;
	String ttime;
	String bttime;
	String bttime1;
	String bttime2;
	String afttime;
	String keyphrase;
	
	Date sDate;
	Date eDate;
	Date date;
	String days="";
	
	String query ="select DISTINCT pif.id, pif.project_name, pif.start_date, pif.end_date, pif.ttime from project_info pif, tv_content_tdays tvc "
			+ "where pif.id=tvc.projectId and pif.ttime is not null";
	public BotSetupScript()
	{
		con= new DataSource().getCon();
	}
	public static void main(String q[])
	{
		BotSetupScript bss=new BotSetupScript();
		bss.current_date();
		bss.getData();
		
	}
	 
	public  void current_date()
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
	int count =0;
	public void getData()
	{
		try
		{
		 psmt = con.prepareStatement(query);
		  rs = psmt.executeQuery();
		  while(rs.next())
		  {
			  projectId= rs.getInt(1);
			  project_name =rs.getString(2);
			  start_date= rs.getString(3);
			  end_date =rs.getString(4);
			  ttime=rs.getString(5);
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  sDate =sdf.parse(start_date);
			  eDate =sdf.parse(end_date);
			  
			  SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMMM-yyyy");
			  String curdate =sdf1.format(date);
			  System.out.println("current Date............"+curdate);
			  
			  keyphrase =project_name+" episode "+curdate;
			
			 			  
			  System.out.println("Project Id............"+projectId);
			  System.out.println("Prject name............."+project_name);
			  System.out.println("start_date............."+start_date);
			  System.out.println("end_date............."+end_date);
			  System.out.println("ttime............."+ttime);
			
			  if(sDate.before(date) && eDate.after(date))
			  {
				  String query1 ="select telecast_days from  tv_content_tdays where projectId ="+projectId;
				  PreparedStatement ps1= con.prepareStatement(query1);
				  ResultSet rs1 =ps1.executeQuery();
				  while(rs1.next())
				  {
					  String day =rs1.getString(1);
					  //System.out.println("Day1............."+day);
					  if(day.equals(days))
					  {
						  System.out.println("Time............."+ttime);
						  
						  setTelecastTime(ttime);
						 // System.out.println("Day............."+day);
						  
					  }
				  }
				  rs1.close();
				  
				 // System.out.println("running.............");
			  }
			  
			  
			  
			  
			  
		  }
		
		}catch(Exception e)
		{
			
			
			System.out.println(e);
			
		}
		finally
		{
			try {
				rs.close();
			
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public void setTelecastTime(String myTime)
	{
        try
        {
        	 Date d =new Date();
        	
        if(myTime.contains("pm"))
        {
        	 //String now = new SimpleDateFormat("hh:mm").format(new java.util.Date().getTime());        	
     		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
     		//SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
     		//String myTime1 = df1.format(df.parse(now));
     		 d=df.parse(myTime);
     		 Calendar cal = Calendar.getInstance();
     		// Calendar cal1 = Calendar.getInstance();
     		 cal.setTime(d);
     		 cal.add(Calendar.HOUR, 12);
     		myTime = df.format(cal.getTime());
     		//ttime=myTime;
     		 System.out.println("**********PM Time***********"+myTime);
     		//setHoursTime(myTime);
        	
        	
        }
        
        System.out.println("**********PM Time1***********"+myTime);
        	
      //  String now = new SimpleDateFormat("hh:mm aa").format(new java.util.Date().getTime());        	
		//SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		//SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
		//String myTime1 = df1.format(df.parse(now));
		// System.out.println("*************myTime1**********"+myTime1);		
		
		// System.out.println("*************Testing1**********"+myTime);	
		// d=df.parse(myTime);
		 //System.out.println("*************Testing1**********"+myTime);	
		// System.out.println("*************date**********"+d);
		 
		// Calendar cal = Calendar.getInstance();
		// Calendar cal1 = Calendar.getInstance();
		// cal.setTime(d);
		// cal.add(Calendar.HOUR, 2);
		 
		// bttime = df.format(cal.getTime());
		/*
		 int hour =hoursConvertor(bttime);
		 if(hour>=24)
		 {
			 System.out.println("*******************Time greator 24 hrs*********");
			 setHoursTime(bttime);
			 
		 }
		 */
		 
		// cal1.add(Calendar.HOUR, 4);
		// bttime1 = df.format(cal1.getTime());
		// System.out.println("*************bttime**********"+bttime);
		// System.out.println("*************ctime**********"+ctime);
		 //System.out.println("*************bttime1**********"+bttime1);
        System.out.println("**********PM Time2***********"+myTime);
		 int cthour =hoursConvertor(ctime);
		 int cthour1= cthour+1;
		 System.out.println("**********PM Time3***********"+myTime);
		 int getHour=hoursConvertor(myTime);
		 System.out.println("**********PM Time4***********"+myTime);
		 int getHour1=getHour+2;
		 System.out.println("**********PM Time5***********"+myTime);
		 int getHour2=getHour+4;
		 System.out.println("**********PM Time6***********"+myTime);
		System.out.println("*************cthour1**********"+cthour1);
		System.out.println("*************cthour**********"+cthour);
		System.out.println("*************getHour**********"+getHour);
		System.out.println("*************getHour1**********"+getHour1);
		System.out.println("*************getHour2**********"+getHour2);
		/*
		 if((getHour>=cthour) && (getHour<=cthour1) )
		 {
			 System.out.println("*******************First Execute************");
			 setQueryToProjectSetup(projectId,keyphrase);
		 }
		 */
		
		 if(getHour1==cthour )
		 {
			 System.out.println("*******************Second Execute************");
			 setQueryToProjectSetup(projectId,keyphrase);
		 }
		 /*
		 if((getHour2==cthour) && (getHour2==cthour1) )
		 {
			 System.out.println("*******************Third Execute************");
			 setQueryToProjectSetup(projectId,keyphrase);
		 }
		 */
			// rs.close();
		 //psmt.close();
		// con.close();
		
        }
        catch(Exception e)
        {
        	
        	
        }
        finally
        {
        	 
        }
	}
	
	public int hoursConvertor(String time)
	{
		 String time1[] =time.split(":");
		 int hour=Integer.parseInt(time1[0]);
		return hour;
	}
	
	public void setHoursTime(String time)
	{
		String time1[]= time.split(":");
		int hour=Integer.parseInt(time1[0]);
		int min= Integer.parseInt(time1[1]);
		hour =hour-24;
		String time2 =hour+":"+min;
		System.out.println("*************Time2************"+time2);
		
	}
	
	public void setQueryToProjectSetup(int projectId, String pname)
	{
		try
		{
		String query2 ="insert into stored_project_setup1(keyphrase,userId,projectId) values (?,?,?)";
		 psmt = con.prepareStatement(query2);
		 psmt.setString(1, pname);
		 psmt.setInt(2, 1);
		 psmt.setInt(3, projectId);
		 psmt.execute();
		// con.close();
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		finally
		{
			//con.close();
		}
	}

}
