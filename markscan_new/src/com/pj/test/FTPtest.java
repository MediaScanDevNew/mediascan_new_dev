package com.pj.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPtest {

	public FTPtest() {
		// TODO Auto-generated constructor stub
	}

	
	/*  public static void main(String[] args) { String server = "172.168.1.222";
	  int port = 21; String user = "pradeep"; String pass = "pass@123";
	  
	  FTPClient ftpClient = new FTPClient(); try {
	  
	  ftpClient.connect(server, port); ftpClient.login(user, pass);
	  ftpClient.enterLocalPassiveMode();
	  
	  ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	  
	  // APPROACH #1: uploads first file using an InputStream File
	File  firstLocalFile = new File("D:\\screenshot\\10.png");
	  
	  String firstRemoteFile = "20Mar2018/10.png"; InputStream inputStream = new
	  FileInputStream(firstLocalFile);
	  
	  System.out.println("Start uploading first file"); boolean done =
	  ftpClient.storeFile(firstRemoteFile, inputStream); inputStream.close();
	  if (done) { System.out.println("The first file is uploaded successfully."
	  ); }else{ System.out.println("The first file is uploaded fail."); }
	  
	  // APPROACH #2: uploads second file using an OutputStream File
	File  secondLocalFile = new File("D:\\screenshot\\18.png"); String
	  secondRemoteFile = "/18.png"; inputStream = new
	  FileInputStream(secondLocalFile);
	  
	  System.out.println("Start uploading second file"); OutputStream
	  outputStream = ftpClient.storeFileStream(secondRemoteFile); byte[]
	  bytesIn = new byte[4096]; int read = 0;
	  
	  while ((read = inputStream.read(bytesIn)) != -1) {
	  outputStream.write(bytesIn, 0, read); } inputStream.close();
	  outputStream.close();
	  
	  boolean completed = ftpClient.completePendingCommand(); if (completed) {
	  System.out.println("The second file is uploaded successfully."); }
	  
	  } catch (IOException ex) { System.out.println("Error: " +
	  ex.getMessage()); ex.printStackTrace(); } finally { try { if
	  (ftpClient.isConnected()) { ftpClient.logout(); ftpClient.disconnect(); }
	  } catch (IOException ex) { ex.printStackTrace(); } } }*/
	 
	// -----------------------------------------------------------------------

	/*public static void main(String[] args) {

		FTPClient ftpClient = new FTPClient();

		FileInputStream fis = null;

		String hostname = "172.168.1.222";

		int port = 21;

		String user = "pradeep";

		String pass = "pass@123";

		String fileName = null;

		File folder = new File("D:\\screenshot"); // Directory from local

		File[] listOfFiles = folder.listFiles();

		try {

			ftpClient.connect(hostname, port);

			boolean login = ftpClient.login(user, pass);

			if (login) {

				System.out.println("Connection established…");

				for (File fileDownload : listOfFiles) {

					if (fileDownload.isFile()) {

						fileName = fileDownload.getName();

						System.out.println("Nama file : " + fileName);

						File uploadToCurrent = new File("D:/screenshot/" + fileName);

						String filePath = uploadToCurrent.getAbsolutePath();

						System.out.println("Path file : " + filePath);

						fis = new FileInputStream(filePath);

						// Path from FTP Server

						String uploadPath = "/var/www/html/" + fileName;

						System.out.println(uploadPath);

						System.out.println("Start uploading first file");

						boolean done = ftpClient.storeFile(uploadPath, fis);

						fis.close();

						if (done) {

							System.out.println("The first file is uploaded successfully.");

						}else{
							System.out.println("The first file is uploaded fail.");
						}

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println(e.getMessage());

		} finally {

			try {

				if (ftpClient.isConnected()) {

					boolean logout = ftpClient.logout();

					ftpClient.disconnect();

					if (logout) {

						System.out.println("Connection close…");

					} else {

						System.out.println("Connection fail…");

					}

				}

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}*/
	
	//===========================================================
	
FTPClient ftp = null;
	
	public void FTPUploader(String host, String user, String pwd) throws Exception{
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(user, pwd);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
	}
	public void uploadFile(String localFileFullName, String fileName, String hostDir)
			throws Exception {
		try(InputStream input = new FileInputStream(new File(localFileFullName))){
		this.ftp.storeFile(hostDir + fileName, input);
		}
	}

	public void disconnect(){
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				// do nothing as file is already saved to server
			}
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println("Start");
		FTPtest ftpUploader = new FTPtest();
				ftpUploader.FTPUploader("172.168.1.222", "ppj", "pass@123");
		//FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
		// files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
		ftpUploader.uploadFile("D:\\screenshot\\pradeep.png", "pradeep.png", "/home/ppj/pra2/");
		ftpUploader.disconnect();
		System.out.println("Done");
		
	}
}
