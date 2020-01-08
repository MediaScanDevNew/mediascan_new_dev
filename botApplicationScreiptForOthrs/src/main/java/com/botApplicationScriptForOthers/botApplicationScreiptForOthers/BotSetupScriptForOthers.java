package com.botApplicationScriptForOthers.botApplicationScreiptForOthers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import com.botApplicationScriptForOthers.datasource.DataSource;



public class BotSetupScriptForOthers {
	
	
	String cdate;
	String ctime;
	Connection con=null;
    PreparedStatement psmt=null;
	ResultSet rs=null;
	int projectId;
	String project_name;
	int project_type; 
	String ttime;
	String bttime;
	String bttime1;
	String bttime2;
	String afttime;
	String keyphrase;
	String property_category;
	
	Date sDate;
	Date eDate;
	Date date;
	String days="";
	
	PreparedStatement psmt1=null;
	ResultSet rs1=null;
	
	
	
	
	
	String query ="select DISTINCT pif.id, pif.project_name, pif.project_type,pif.property_category from project_info pif "
			      + "where  pif.project_type=2 and pif.ttime is null ";
	
	public BotSetupScriptForOthers()
	{
		con= new DataSource().getCon();
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
		 
	}
	
	
	public void getData()
	{
		try
		{
		  System.out.println("in side get data method .............");
		  System.out.println(query);
		  
		  psmt = con.prepareStatement(query);
		  rs = psmt.executeQuery();
		  while(rs.next())
		  {
			  projectId= rs.getInt(1);
			  project_name =rs.getString(2);
			  project_type = rs.getInt(3);
			  property_category = rs.getString(4);
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMMM-yyyy");
			  date=new Date();
			  String curdate =sdf1.format(date);
			  
			  //System.out.println("current Date............"+curdate);
			  System.out.println("Project Id............"+projectId);
			  System.out.println("Prject name............."+project_name);
			  
			  if(property_category!=null && property_category.trim().equalsIgnoreCase("Current")){ 
				  psmt1 = con.prepareStatement("SELECT keyphrase FROM webinforcement_demo.keyword_filter_extension_master where projectType="+project_type);
				  rs1 = psmt1.executeQuery();
				  while(rs1.next()){
					  keyphrase =project_name+" "+rs1.getString("keyphrase");
					  setQueryToProjectSetup(projectId,keyphrase,con);
					  
				  }
			  }else{
				  
				  
			  }
			  
			 
			}
		
		  }catch(Exception e)
		   {
			
			
			e.printStackTrace();
			
		  }
		finally
		{
			try {
				if(psmt1 != null){
					psmt1.close();
				}
				if(psmt != null){
					psmt.close();
				}
				if(rs != null){
					rs.close();
				}
				if(rs1 != null){
					rs1.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void setQueryToProjectSetup(int projectId, String pname,Connection con) throws SQLException
	{
		PreparedStatement psmt2=null;
		try
		{
		String query2 ="insert into stored_project_setup1(keyphrase,userId,projectId) values (?,?,?)";
		 psmt2 = con.prepareStatement(query2);
		 psmt2.setString(1, pname);
		 psmt2.setInt(2, 1);
		 psmt2.setInt(3, projectId);
		 psmt2.execute();
		// con.close();
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		finally
		{
			if(psmt2!= null){
				psmt2.close();
			}
		}
	}

}
