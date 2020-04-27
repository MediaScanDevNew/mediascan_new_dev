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

	PreparedStatement ps = null;
	ResultSet rs = null;
	PreparedStatement ps1 = null;
	ResultSet rs1 = null;
	Connection con = null;

	public ArrayList<IWLDataBean> DataProcess(int projectId) throws SQLException {

		ArrayList<IWLDataBean> iwl_list = new ArrayList<IWLDataBean>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql = " SELECT a.id,a.crawle_url2,b.keyphrase,a.user_id,a.project_id FROM master_crawle_url a,stored_project_setup1 b "
					+ "where a.stored_project_setup_id = b.id and projectId = ? AND a.status='Both Matched'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, projectId);
			rs = ps.executeQuery();
			while (rs.next()) {
				IWLDataBean bn = new IWLDataBean();
				bn.setCrawle_url2(rs.getString("crawle_url2"));
				bn.setKeyphase(rs.getString("keyphrase"));
				bn.setUser_id(rs.getInt("user_id"));
				bn.setProject_id(rs.getInt("project_id"));
				iwl_list.add(bn);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
		}

		return iwl_list;
	}

	public void UpdateRecord(int id) throws SQLException {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql = "UPDATE master_crawle_url SET iwl_flag = 1 where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
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

	public int getProjectType(int id) throws SQLException {
		int projetId = 0;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql = "select project_type from project_info where id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				projetId = rs.getInt("project_type");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (ps != null)
				ps.close();
			if (con != null)
				con.close();

		}

		return projetId;

	}

	public void UpdateBOTMachine(String SServer1IP, String port, int status) throws SQLException {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql2 = "UPDATE markscan_machine1 SET status=? WHERE ip_address=? and port=?";
			System.out.println("Updated query is ------------->"+sql2);
			ps = con.prepareStatement(sql2);
			ps.setInt(1, status);
			ps.setString(2, SServer1IP);
			ps.setString(3, port);
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

	public int getMailNotificationValue(int projectId) throws SQLException {
		int mailValue = 0;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql2 = "SELECT mail_notification FROM project_info WHERE id=?";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, projectId);
			rs = ps.executeQuery();
			if (rs.next()) {
				mailValue = rs.getInt("mail_notification");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
		}

		return mailValue;

	}

	public String getProjectName(int projectId) throws SQLException {
		String project_nm = "";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql2 = "SELECT project_name FROM project_info WHERE id=?";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, projectId);
			rs = ps.executeQuery();
			if (rs.next()) {
				project_nm = rs.getString("project_name");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();

		}

		return project_nm;

	}

	public void updateMailNotificationValue(int projectId,int val) throws SQLException {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql2 = "UPDATE project_info SET mail_notification=? WHERE id=? ";
			System.out.println("update mail notification --------------->"+sql2);
			ps = con.prepareStatement(sql2);
			ps.setInt(1, val);
			ps.setInt(2, projectId);
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

	public int checkingDataExistOrNot(String URL, int projectid, String domain_name) throws SQLException {
		int p = 0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql1 = "select id from crawle_url_duplicate where crawle_url2=? and project_id=? and domain_name=?";
			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, URL);
			ps1.setInt(2, projectid);
			ps1.setString(3, domain_name);
			rs1 = ps1.executeQuery();
			if (rs1.next()) {
				p = rs1.getInt("id");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (ps != null)
				ps.close();
			if (ps1 != null)
				ps1.close();
			if (rs1 != null)
				rs1.close();

			if (con != null)
				con.close();

		}

		return p;
	}
	
	
	public void UpdateProjectCompleteFlag(int projectid) throws SQLException {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql2 = "UPDATE manual_bot_logs SET completed_flag=? WHERE project_id=?";
			System.out.println("Updated query is ------------->"+sql2);
			ps = con.prepareStatement(sql2);
			ps.setString(1, "Y");
			ps.setInt(2, projectid);
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
	
	public void UpdateMailFlag(int id) throws SQLException {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("application");
			String connection_url = rb.getString("spring.datasource.url");
			String connection_userNm = rb.getString("spring.datasource.username");
			String connection_userPwd = rb.getString("spring.datasource.password");
			con = DriverManager.getConnection(connection_url, connection_userNm, connection_userPwd);

			String sql = "UPDATE stored_project_setup2 SET mailflag = 1 where id="+id+"";
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

}
