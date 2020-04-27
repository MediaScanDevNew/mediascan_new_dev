package com.pradeep.pj.repo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.pradeep.pj.model.IWLDataBean;
import com.pradeep.pj.model.MailProceesBean;

public class IWLDataProcess {
	
	PreparedStatement ps= null;
	ResultSet rs= null;
	
	PreparedStatement ps1= null;
	ResultSet rs1= null;
	
	Connection con= null;
	
	
	public ArrayList<IWLDataBean> DataProcess(int projectId) throws SQLException {
		
		ArrayList<IWLDataBean> iwl_list = new ArrayList<IWLDataBean>();
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
			String sql = " SELECT a.id,a.crawle_url2,b.keyphrase,a.user_id,a.project_id FROM master_crawle_url a,stored_project_setup1 b "
					     + "where a.stored_project_setup_id = b.id and projectId = ? AND a.status='Both Matched'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, projectId);
			rs = ps.executeQuery();
			while(rs.next()){
				IWLDataBean bn = new IWLDataBean();
				bn.setCrawle_url2(rs.getString("crawle_url2"));
				bn.setKeyphase(rs.getString("keyphrase"));
				bn.setUser_id(rs.getInt("user_id"));
				bn.setProject_id(rs.getInt("project_id"));
				iwl_list.add(bn);
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
		}
		
		return iwl_list;
	}
	
	
	public void UpdateRecord(int id) throws SQLException {
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
			String sql = "UPDATE master_crawle_url SET iwl_flag = 1 where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
		
	}
	
	
	public int getProjectType(int id) throws SQLException {
		   int projetId=0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");  
	    		ResourceBundle rb = ResourceBundle.getBundle("application");
	    		String connection_url = rb.getString("spring.datasource.url");
	    		String connection_userNm = rb.getString("spring.datasource.username");
	    		String connection_userPwd = rb.getString("spring.datasource.password");
	    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
	    		
				String sql = "select project_type from project_info where id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()){
					projetId = rs.getInt("project_type");
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			return projetId;
			
		}
	
	
	public int getCheckCompleted(int id) throws SQLException {
		   int completeVal=0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");  
	    		ResourceBundle rb = ResourceBundle.getBundle("application");
	    		String connection_url = rb.getString("spring.datasource.url");
	    		String connection_userNm = rb.getString("spring.datasource.username");
	    		String connection_userPwd = rb.getString("spring.datasource.password");
	    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
	    		
				String sql = "select completed from stored_project_setup1 where id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()){
					completeVal = rs.getInt("completed");
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(con != null) con.close();
				
			}
			
			return completeVal;
			
		}
	
	
	public void UpdateBOTMachine(String SServer1IP,String port,int status) throws SQLException {
		   
			try{
				
				Class.forName("com.mysql.jdbc.Driver");  
	    		ResourceBundle rb = ResourceBundle.getBundle("application");
	    		String connection_url = rb.getString("spring.datasource.url");
	    		String connection_userNm = rb.getString("spring.datasource.username");
	    		String connection_userPwd = rb.getString("spring.datasource.password");
	    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
	    		
	    		String sql2 = "UPDATE markscan_machine1 SET status=? WHERE ip_address=? and port=?";
				ps = con.prepareStatement(sql2);
				ps.setInt(1, status);
				ps.setString(2, SServer1IP);
				ps.setString(3, port);
				ps.executeUpdate();
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
		}
	
	
	public int getStatusValueBOTMachine(String SServer1IP,String port) throws SQLException {
		   int statusValue = 0;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
    		String sql2 = "SELECT status FROM markscan_machine1 WHERE ip_address=? and port=?";
    		ps = con.prepareStatement(sql2);
			ps.setString(1, SServer1IP);
			ps.setString(2, port);
			rs = ps.executeQuery();
			if(rs.next()){
				statusValue = rs.getInt("status");
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
		
		return statusValue;
		
	}
	
	public ArrayList<MailProceesBean> getDataForMails(int projectid) throws SQLException {
		ArrayList<MailProceesBean> list = new ArrayList<MailProceesBean>();
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
    		/*String sql2 = " SELECT distinct ss.keyphrase as keyphrase,pi.project_name as project_name ,cm.client_name as client_name,"
    					  + "mu.name as name ,ss.id"
				          + "FROM project_info pi,stored_project_setup1 ss,markscan_users mu,client_master cm "
				          + "where pi.id in ("+projectid+") and pi.id = ss.projectId and ss.completed=1 and pi.created_by = mu.id and pi.client_type = cm.id ";*/
    		
    		
    		String sql2 = " SELECT distinct ss.keyphrase as keyphrase,pi.project_name as project_name ,cm.client_name as client_name,"
					  + " mu.name as name ,ss.id as id"
			          + " FROM project_info pi,stored_project_setup1 ss,markscan_users mu,client_master cm "
			          + " where pi.id ="+projectid+" and pi.id = ss.projectId and ss.completed=1 and pi.created_by = mu.id and pi.client_type = cm.id ";
    		
    		ps = con.prepareStatement(sql2);
			//ps.setString(1, "("+projectid+")");
			rs = ps.executeQuery();
			while(rs.next()){
				MailProceesBean bn = new MailProceesBean();
				bn.setKeyphrase(rs.getString("keyphrase"));
				bn.setProject_nm(rs.getString("project_name"));
				bn.setClient_nm(rs.getString("client_name"));
				bn.setUser_nm(rs.getString("name"));
				bn.setId(rs.getInt("id"));
				list.add(bn);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
		
		return list;
		
	}
	
	
	public int getMailNotificationValue(int projectId) throws SQLException {
		   int mailValue = 0;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
	 		ResourceBundle rb = ResourceBundle.getBundle("application");
	 		String connection_url = rb.getString("spring.datasource.url");
	 		String connection_userNm = rb.getString("spring.datasource.username");
	 		String connection_userPwd = rb.getString("spring.datasource.password");
	 		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
 		
 		String sql2 = "SELECT mail_notification FROM project_info WHERE id=?";
 		ps = con.prepareStatement(sql2);
			ps.setInt(1, projectId);
			rs = ps.executeQuery();
			if(rs.next()){
				mailValue = rs.getInt("mail_notification");
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
		}
		
		return mailValue;
		
	}
	
	public String getProjectName(int projectId) throws SQLException {
		   String project_nm = "";
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
	 		ResourceBundle rb = ResourceBundle.getBundle("application");
	 		String connection_url = rb.getString("spring.datasource.url");
	 		String connection_userNm = rb.getString("spring.datasource.username");
	 		String connection_userPwd = rb.getString("spring.datasource.password");
	 		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
		
		String sql2 = "SELECT project_name FROM project_info WHERE id=?";
		ps = con.prepareStatement(sql2);
			ps.setInt(1, projectId);
			rs = ps.executeQuery();
			if(rs.next()){
				project_nm = rs.getString("project_name");
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
			
		}
		
		return project_nm;
		
	}
	
	
	public void updateMailNotificationValue(int projectId,int i) throws SQLException {
		   
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
    		String sql2 = "UPDATE project_info SET mail_notification=? WHERE id=? ";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, i);
			ps.setInt(2, projectId);
			ps.executeUpdate();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
		
	}
	
public int checkingDataExistOrNot(String URL, int projectid, String domain_name) throws SQLException {
		int p = 0;
		try{
			System.out.println("URL ----------->"+URL);
			System.out.println("projectid ----------->"+projectid);
			System.out.println("domain_name ----------->"+domain_name);
			
			
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
    		String sql1 = "select id from crawle_url_duplicate where crawle_url2=? and project_id=? and domain_name=?";
    		ps1 = con.prepareStatement(sql1);
    		ps1.setString(1, URL);
			ps1.setInt(2, projectid);
			ps1.setString(3, domain_name);
			rs1 = ps1.executeQuery();
			if(rs1.next()){
				p = rs1.getInt("id");
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(ps1 != null) ps1.close();
			if(rs1 != null) rs1.close();
			
			if(con != null) con.close();
			
		}
		
		return p;
	}


public void UpdateMailFlag(String projectId) throws SQLException {
	   
	try{
		
		Class.forName("com.mysql.jdbc.Driver");  
		ResourceBundle rb = ResourceBundle.getBundle("application");
		String connection_url = rb.getString("spring.datasource.url");
		String connection_userNm = rb.getString("spring.datasource.username");
		String connection_userPwd = rb.getString("spring.datasource.password");
		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
		
		
		String sql2 = "UPDATE project_info SET mail_notification=0 WHERE id in ("+projectId+") ";
		System.out.println("The sql query is ------>"+sql2);
		ps = con.prepareStatement(sql2);
		ps.executeUpdate();
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		
		if(ps != null) ps.close();
		if(con != null) con.close();
		
	}
	
}


	public void UpdateMailFlag(int id) throws SQLException {
	
		try {
	
			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);
	
			String sql = "UPDATE stored_project_setup1 SET mailflag = 1 where id="+id+"";
			System.out.println("mail flag update------>"+sql);
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
	
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
	
		}
	
	  }
	
	
	
	public int chekedCompletedFlag(int projectId) throws SQLException {
		   int completeVal=0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");  
	    		ResourceBundle rb = ResourceBundle.getBundle("application");
	    		String connection_url = rb.getString("spring.datasource.url");
	    		String connection_userNm = rb.getString("spring.datasource.username");
	    		String connection_userPwd = rb.getString("spring.datasource.password");
	    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
	    		
				String sql = "SELECT count(id) as value FROM stored_project_setup1 where completed in (0,2) and projectId="+projectId+"";
				ps = con.prepareStatement(sql);
				//ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()){
					completeVal = rs.getInt("value");
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(con != null) con.close();
				
			}
			
			return completeVal;
			
		}
	
	
	public String getClientEmail(int projectId) throws SQLException {
		   String email = "";
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
	 		ResourceBundle rb = ResourceBundle.getBundle("application");
	 		String connection_url = rb.getString("spring.datasource.url");
	 		String connection_userNm = rb.getString("spring.datasource.username");
	 		String connection_userPwd = rb.getString("spring.datasource.password");
	 		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
		
		String sql2 = "select a.client_email email from client_master a,project_info b where b.id=? and a.project_type = b.project_type and a.id=b.client_type";
		ps = con.prepareStatement(sql2);
			ps.setInt(1, projectId);
			rs = ps.executeQuery();
			if(rs.next()){
				email = rs.getString("email");
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
		}
		
		return email;
		
	}
	
	
	
	public void insertCrawllog(int id,Date d1,Date d2) throws SQLException {
		   
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    		long diff = d2.getTime() - d1.getTime();
    		long diffMinutes = diff / (60 * 1000) % 60;
    		
    		String sql2 = "insert into crawle_tym_log(store_project_setup_id,google_time) values (?,?)";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, id);
			ps.setInt(2, (int)diffMinutes);
			ps.executeUpdate();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
		
	}
	
	
	public void updateCrawllog(int id,Date d1,Date d2,int val) throws SQLException {
		   
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    		long diff = d2.getTime() - d1.getTime();
    		long diffMinutes = diff / (60 * 1000) % 60;
    		
    		String sql2 ="";
    		if(val == 1){
    			sql2 = "update crawle_tym_log set yahoo_time=? where store_project_setup_id=?";
    		}else if(val == 2){
    			sql2 = "update crawle_tym_log set bing_time=? where store_project_setup_id=?";
    		}else if(val == 3){
    			sql2 = "update crawle_tym_log set duckduckgo_time=? where store_project_setup_id=?";
    		}else if(val == 4){
    			sql2 = "update crawle_tym_log set ru_time=? where store_project_setup_id=?";
    		}else if(val == 5){
    			sql2 = "update crawle_tym_log set white_time=? where store_project_setup_id=?";
    		}else if(val == 6){
    			sql2 = "update crawle_tym_log set grey_time=? where store_project_setup_id=?";
    		}else if(val == 7){
    			sql2 = "update crawle_tym_log set black_time=? where store_project_setup_id=?";
    		}
    		
    		
			ps = con.prepareStatement(sql2);
			ps.setInt(1, (int)diffMinutes);
			ps.setInt(2, id);
			ps.executeUpdate();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
		
	}




}
