package com.pradeep.pj.repo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.pradeep.pj.model.Crawle_url4;
import com.pradeep.pj.model.IWLDataBean;
import com.pradeep.pj.model.Infringing_source;

public class IWLDataProcess {
	
	PreparedStatement ps= null;
	ResultSet rs= null;
	Connection con= null;
	
	PreparedStatement ps1= null;
	ResultSet rs1= null;
	
	
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
					     + "where a.stored_project_setup_id = b.id and projectId = ? ";
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
	
	
	public int getIdByURL(String url) throws SQLException {
		int val = 0;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
			String sql = "Select id from crawle_url4  where crawle_url2=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, url);
			rs = ps.executeQuery();
			if(rs.next()){
				val = rs.getInt("id");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
		}
		
		return val;
		
	}
	
	
	
	public void saveData(String URL, int projectid, int userid, int k, String domain_name, int l) throws SQLException {
		
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
			int p = 0;
			if(rs1.next()){
				p = rs1.getInt("id");
			}
			
			if(p == 0){
				String sql = "insert into crawle_url4 (crawle_url2,project_id,user_id,w_list"
							 + ",domain_name,iwl_failed) VALUES (?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, URL);
				ps.setInt(2, projectid);
				ps.setInt(3, userid);
				ps.setInt(4, k);
				ps.setString(5, domain_name);
				ps.setInt(6, l);
				ps.executeUpdate();
			}	
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
	}
	
	
	public void updateIWLErrorField(int URL_id) throws SQLException {
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  
    		ResourceBundle rb = ResourceBundle.getBundle("application");
    		String connection_url = rb.getString("spring.datasource.url");
    		String connection_userNm = rb.getString("spring.datasource.username");
    		String connection_userPwd = rb.getString("spring.datasource.password");
    		con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
    		
			String sql = "UPDATE crawle_url4 set iwl_failed=1 where id =?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, URL_id);
			ps.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}
	}
	public void addDataInfringing(Infringing_source is){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
			int id = 0;
			
			String sql1 = "select count(*) count from bl_infringing_source";
			ps1 = con.prepareStatement(sql1);
			rs1 = ps1.executeQuery();
			if(rs1.next()){
				id = rs1.getInt("count");
			}
			
			
			String sql = "insert into bl_infringing_source (infringing_link_by_date,infi_time,infringing_link,domain,userid,projectid,search_keyword,"
					 + "source_link,source_domain,source_time,row_in_use,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, is.getInfringing_link_by_date());
			ps.setString(2, is.getInfi_time());
			ps.setString(3, is.getInfringing_link());
			ps.setString(4, is.getDomain());
			ps.setInt(5, is.getUserid());
			ps.setInt(6, is.getProjectid());
			ps.setString(7, is.getSearch_keyword());
			ps.setString(8, is.getSource_link());
			ps.setString(9, is.getSource_domain());
			ps.setString(10, is.getSource_time());
			ps.setInt(11, is.getRow_in_use());
			ps.setInt(12, (id+1));
			ps.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(ps1 != null)
				try {
					ps1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(rs1 != null)
				try {
					rs1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	public void addDataCrawle4(Crawle_url4 cu){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con=DriverManager.getConnection(connection_url,connection_userNm,connection_userPwd);
			
			
			String sql1 = "select id from crawle_url_duplicate where crawle_url2=? and project_id=? and domain_name=?";
    		ps1 = con.prepareStatement(sql1);
    		ps1.setString(1, cu.getCrawle_url2());
			ps1.setInt(2, cu.getProject_id());
			ps1.setString(3, cu.getDomain_name());
			rs1 = ps1.executeQuery();
			int p = 0;
			if(rs1.next()){
				p = rs1.getInt("id");
			}
			
			
			if(p == 0){
			
				String sql = "insert into crawle_url4 (link_logger_srclink,domain_name,user_id,project_id,note2,link_logger,crawle_url2"
						 + ") VALUES (?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, cu.getLink_logger_srclink());
				ps.setString(2, cu.getDomain_name());
				ps.setInt(3, cu.getUser_id());
				ps.setInt(4, cu.getProject_id());
				ps.setString(5, cu.getNote2());
				ps.setInt(6, cu.getLink_logger());
				ps.setString(7, cu.getCrawle_url2());
				ps.executeUpdate();
			}	
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

}
