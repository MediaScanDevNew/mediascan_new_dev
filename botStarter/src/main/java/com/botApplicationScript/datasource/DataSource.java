package com.botApplicationScript.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
	private Connection con = null;

	private Connection conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/*
			con = DriverManager.getConnection("jdbc:mysql://172.168.1.6:3306/webinforcement_demo", "myuser",
					"P@$$@123++pj");
					*/
			
			/*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo", "root",
					"root");*/

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webinforcement_demo", "testuser",
					"M@123rkscan");
			
//			con = DriverManager.getConnection("jdbc:mysql://182.73.134.27:3306/webinforcement_demo", "root",
//					"M@1234rkscan");
		
					
					

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
