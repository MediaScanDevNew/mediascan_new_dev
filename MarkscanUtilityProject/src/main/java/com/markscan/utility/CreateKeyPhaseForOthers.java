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

public class CreateKeyPhaseForOthers implements Job {

	String cdate;
	String ctime;
	Connection con = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
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
	String language;

	Date sDate;
	Date eDate;
	Date date;
	String days = "";

	PreparedStatement psmt1 = null;
	ResultSet rs1 = null;

	PreparedStatement psmt2 = null;
	ResultSet rs2 = null;

	PreparedStatement psmt3 = null;
	ResultSet rs3 = null;

	PreparedStatement psmt4 = null;
	ResultSet rs4 = null;
	
	PreparedStatement psmt5 = null;
	ResultSet rs5 = null;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub

		try {
			current_date();
			Class.forName("com.mysql.jdbc.Driver");
	    	//con=DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo","root","M@1234rkscan"); 
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo?useSSL=false","testuser","M@123rkscan"); 
			
			 String query = "select DISTINCT pif.id, pif.project_name, pif.project_type,pif.property_category,pif.language from project_info pif "
					+ " where  pif.ttime is null and pif.project_complete=0 and pif.id not in "
					+ " (SELECT distinct projectId FROM stored_project_setup1 where "
					+ " date_format(created_on,'%d-%-m-%y') = date_format(now(),'%d-%-m-%y'))";
			
			 
			 //System.out.println("query ---111111111111--->" + query);
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				projectId = rs.getInt(1);
				project_name = rs.getString(2);
				project_type = rs.getInt(3);
				property_category = rs.getString(4);
				language = rs.getString(5);
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMMM-yyyy");
				date = new Date();
				//String curdate = sdf1.format(date);

				/**
				 * Add by Pentation team This if condition add for English TV
				 * Show
				 */
				int k = -1;
				String sql2 = "SELECT projectId,count(id) x FROM stored_project_setup1 where completed in (0,2) and projectId="+projectId+" group by projectId ";
				System.out.println("query1========>"+sql2 );
				psmt5 = con.prepareStatement(sql2);
				rs5 = psmt5.executeQuery();
				
				if(rs5.next()){
					k = rs5.getInt("x");
				}else{
					k=0;
				}
				System.out.println("K ========>"+k );
				System.out.println("project_type -------->"+project_type);
				System.out.println("language ------------>"+language);
				
				if(k <1 || k==0 ){
					System.out.println("K ===   in side if condition  =====>"+k );
					System.out.println("");
					System.out.println("");
					if ((project_type == 4 || project_type == 5) && language.equalsIgnoreCase("English")) {
						String sql10 = "select season_number,episodeNo,episodeNm,episode_realease_dt from imdb_content_detail where projectId="
								+ projectId;
						System.out.println("10  --------->"+sql10);
						psmt2 = con.prepareStatement(sql10);
						rs2 = psmt2.executeQuery();
						while (rs2.next()) {
							String episodeNo = rs2.getInt("episodeNo") > 9 ? "E" + rs2.getString("episodeNo")
									: "E0" + rs2.getString("episodeNo");
							String sessionNo = rs2.getInt("season_number") > 9 ? "S" + rs2.getString("season_number")
									: "S0" + rs2.getString("season_number");
							// keyphrase =project_name+" "+sessionNo+episodeNo;
							// setQueryToProjectSetup(projectId,keyphrase,con);
							String sql11 = "SELECT keyphrase FROM keyword_filter_extension_master where isactive=1 and projectType="
									+ project_type;
							System.out.println("11  --------->"+sql11);
							psmt1 = con.prepareStatement(sql11);
							rs1 = psmt1.executeQuery();
							while (rs1.next()) {
								keyphrase = project_name + " " + sessionNo + episodeNo + " " + rs1.getString("keyphrase");
								setQueryToProjectSetup(projectId, keyphrase, con);
	
							}
	
						}
					}else{
	
					/**
					 * Add by Pentation team This if condition add Others Project
					 * type except TV Show
					 */
	                
					if (property_category != null && property_category.trim().equalsIgnoreCase("Current")) {
	
						String sql1 = "SELECT id,date_format( realeasingDate,'%Y-%m-%d') realeasingDate,date_format(now(),'%Y-%m-%d') "
								+ "cuurDate,DATEDIFF( date_format( now(),'%Y-%m-%d'),date_format(realeasingDate,'%Y-%m-%d')) currDiff,current_value, "
								+ "(current_value*7) current_val FROM project_info where property_category='Current' and id ="
								+ projectId + " "
								+ "having DATEDIFF( date_format( now(),'%Y-%m-%d'),date_format(realeasingDate,'%Y-%m-%d'))<= current_value*7";
						System.out.println("2 nd query ---->" + sql1);
						psmt4 = con.prepareStatement(sql1);
						rs4 = psmt4.executeQuery();
	
						if (rs4.next()) {
							psmt1 = con.prepareStatement(
									"SELECT keyphrase FROM keyword_filter_extension_master where isactive=1 and projectType="
											+ project_type);
							rs1 = psmt1.executeQuery();
							while (rs1.next()) {
								keyphrase = project_name + " " + rs1.getString("keyphrase");
								setQueryToProjectSetup(projectId, keyphrase, con);
	
							}
						}
					} else if (property_category != null && property_category.trim().equalsIgnoreCase("Archive")) {
						Date now = new Date();
						SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
						System.out.println("Day of the week ------>" + simpleDateformat.format(now)
								+ " And Project Id ----->" + projectId);
						String week_nm = simpleDateformat.format(now);
	
						String sql = "SELECT archive_days FROM project_content_tdays where projectId=" + projectId
								+ " and archive_days='" + week_nm.toLowerCase() + "'";
						System.out.println(sql);
						psmt3 = con.prepareStatement(sql);
						rs3 = psmt3.executeQuery();
						while (rs3.next()) {
							psmt1 = con.prepareStatement(
									"SELECT keyphrase FROM keyword_filter_extension_master where isactive=1 and projectType="
											+ project_type);
							rs1 = psmt1.executeQuery();
							while (rs1.next()) {
								keyphrase = project_name + " " + rs1.getString("keyphrase");
								setQueryToProjectSetup(projectId, keyphrase, con);
	
							}
	
						}
	
					}
	              }	
			    }	

			} // First while loop end here..........

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt1 != null) {
					psmt1.close();
				}
				if (psmt != null) {
					psmt.close();
				}

				if (psmt3 != null) {
					psmt3.close();
				}

				if (rs != null) {
					rs.close();
				}
				if (rs1 != null) {
					rs1.close();
				}

				if (rs3 != null) {
					rs3.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Process done..............................................................");

	}

	private void setQueryToProjectSetup(int projectId, String pname, Connection con) throws SQLException {
		PreparedStatement psmt2 = null;
		try {
			String query2 = "insert into stored_project_setup1(keyphrase,userId,projectId,create_source) values (?,?,?,?)";
			psmt2 = con.prepareStatement(query2);
			psmt2.setString(1, pname);
			psmt2.setInt(2, 1);
			psmt2.setInt(3, projectId);
			psmt2.setString(4, "C");
			psmt2.execute();
			// con.close();
		} catch (Exception e) {
			System.out.println(e);

		} finally {
			if (psmt2 != null) {
				psmt2.close();
			}
		}
	}

	private void current_date() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		date = new Date();
		cdate = simpleDateFormat.format(date);

		System.out.println(cdate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
		LocalTime time = LocalTime.now();
		ctime = time.format(formatter);
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);

		switch (day) {
		case 1:
			days = "sun";
			break;

		case 2:
			days = "mon";
			break;
		case 3:
			days = "tue";
			break;
		case 4:
			days = "wed";
			break;
		case 5:
			days = "thu";
			break;
		case 6:
			days = "fri";
			break;
		case 7:
			days = "sat";
			break;

		}
		System.out.println("week of day.........." + days);

	}

}
