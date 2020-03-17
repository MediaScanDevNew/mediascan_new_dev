package com.markscan.screenShot.screenUpload_intigration.fileUpload;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionOne {

	public ConnectionOne() {
		// TODO Auto-generated constructor stub
	}

	private Connection con = null;

	private Connection conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

//			con = DriverManager.getConnection("jdbc:mysql://172.168.1.9:3306/webinforcement_demo", "root", "web@media123");
			
			con = DriverManager.getConnection("jdbc:mysql://172.168.1.6:3306/webinforcement_demo","myuser","P@$$@123++pj");
					
					

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public Connection getCon() {
		con = conn();
		return con;
	}


}
