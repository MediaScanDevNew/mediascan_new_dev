package com.pradeep.pj.repo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.pradeep.pj.model.IWLDataBean;

public class IWLDataProcess {
	
	PreparedStatement ps= null;
	ResultSet rs= null;
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

}
