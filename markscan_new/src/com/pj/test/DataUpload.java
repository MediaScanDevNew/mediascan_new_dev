/**
 * 
 */
package com.pj.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

//import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.Preparable;

/**
 * @author pradeep
 *
 */
public class DataUpload {

	/**
	 * 
	 */
	public DataUpload() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("pr");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://172.168.1.2:3306/webinforcement_demo", "myuser",
					"pass@123");
//			System.out.println(conn);
			File f= new File("C:\\Users\\pradeep\\Desktop\\export\\email_temp.sql") ;
			String query= null;
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(f);
			System.out.println(scanner.hasNextLine());
//			System.out.println(scanner);
			while (scanner.hasNextLine()) {
				System.out.println("nxt'");
				query = scanner.nextLine();
				System.out.println(query);
				try {
					
					ps = (PreparedStatement) conn.prepareStatement(query);
					int x = ps.executeUpdate();
					System.err.println("data upload status --" + x);
					
				} catch (Exception e) {
					System.err.println("duplicate entry");
				}
				ps =  null;
				query = null;
			}
scanner = null;
		} catch (Exception ee) {
			ee.printStackTrace();
		} 
	}

}
