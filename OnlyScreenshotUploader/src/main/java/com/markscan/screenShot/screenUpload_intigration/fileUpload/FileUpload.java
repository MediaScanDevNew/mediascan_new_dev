package com.markscan.screenShot.screenUpload_intigration.fileUpload;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FileUpload {
	/**
	 * started on 2016-nov-10 !! Latest Modified By $p!dY Feb 2020 !!
	 */
	private static volatile FileUpload instance;
	
	private FileUpload() {
	}
	
	public static FileUpload getInstance() {
		if(instance == null) {
			
			synchronized (FileUpload.class) {
				
				if(instance ==  null)
					instance = new FileUpload();
			}
		}
		return instance;
	}
	
	int fileUploaded 		= 0;
	
	boolean linkstatus 		= false;
	
	Connection connection	= null;
	PreparedStatement ps 	= null;
	ResultSet rs 			= null;
	
	File oldFile 			= null;
	File newFile 			= null;
	FileWriter writer 		= null;
	File file 				= null;
	FileInputStream fis 	= null;
	
	FTPClient ftpclient 	= null;
	
	FirefoxProfile profile 	= null;
	
	String error 			= "";
	String file_extension 	= null;
	String destinationName 	= null;
	String fileName 		= null;
	String ftpServerAddress = null;
	String userName 		= null;
	String password 		= null;
	String testName 		= null;
	String path 			= null;
	
	Robot robot 			= null;
	Random random 			= null;
	
	

	
	ArrayList<String> linkDetail 		= new ArrayList<String>();
	List<String> notCaptureLink 		= new ArrayList<String>();;
	List<String> userDetails 			= null;
	Map<String, String> errorCollection = null;
	Set<Integer> notCaptured 			= null;

	String urldetails 	= "select cu.id, DATE_FORMAT(cu.created_on,'%d%b%Y')as dd, cu.project_id, cu.user_id, qc.ss_file_path "
						+ " from crawle_url4 cu, qc_record qc where qc.crawl_url2_id = cu.id and qc.ss_uploader_id=0 and qc.ss_file_path "
						+ " is null and  cu.w_list!=1 and  cu.crawle_url2 = ? order by cu.id desc";

	String ftpDetailsUpdate = "update qc_record set qc_record.ss_uploader_id =?  , qc_record.ss_upload_time = ? , "
							+ " qc_record.ss_file_path=? where qc_record.crawl_url2_id = ?";

// ****************Current Date Capture****************
	public String currentTime() {
		Date date 				= new Date();
		DateFormat dateFormat 	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (dateFormat.format(date).toString()); // 2016/11/16 12:08:43
	}
	
//	**************Create Result file*******************
	public void resultCreate(String filePath, Map<String, String> result) {
		boolean append 		= true;
		boolean autoFlush 	= true;
		
		String charset 		= "UTF-8";
		
		try {
			
			file = new File(filePath + "/result.txt");
			
			if (file.createNewFile()) {
				System.out.println("Result File is created !!");
			} else {
				System.out.println("Result File already exists !!");
			}
		} catch (Exception e) {
			System.out.println("Exception Occured While Creating Result File !! : "+ e);
			e.printStackTrace();
		}

		try {
			writer = new FileWriter(file);

			for (Map.Entry<String, String> entry : result.entrySet()) {

				writer.write(entry.getKey() + "," + entry.getValue() + "");
				writer.write("\n");
			}
			writer.close();
			
		} catch (Exception e) {
			System.out.println("Exception Occured While FileWriting : "+ e);
			e.printStackTrace();
		}

	}

	// ******************* file extention generate ************************

	private static String getFileExtension(File file) {
		String extension = "";

		try {
			if (file != null && file.exists()) {
				String name = file.getName();
				extension = name.substring(name.lastIndexOf("."));
			}
		} catch (Exception e) {
			extension = "";
		}

		return extension;

	}

	
	/* public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Kamlesh\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver1 = new FirefoxDriver();
		System.out.println("Harry in run firefox......." + driver1);
		driver1.get("about:config"); // TODO Auto-generated method stub
		FileUpload flupd = new FileUpload(); // ss.screeShotToolGUI();
		String crawl_url = "https://www.youtube.com/watch?v=i_NmPv5WQ0U";
		int id = 1;
		
		ConnectionOne con = new ConnectionOne();
		
		try {			
			String sql = "select * from webinforcement_demo.crawle_url4 where (created_on between date_format(?, '%Y-%m-%d')  And current_date()) and image_path is null limit 1";
			
			SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd");
			Date fDate   			=  sdf.parse("2020-02-17 00:00:00");
//			Date tDate 				=  sdf.parse("2020-03-05 23:59:00");
			
			Timestamp fromDate 	= new Timestamp(fDate.getTime());
			Timestamp toDate	= new Timestamp(new Date().getTime());
			
			PreparedStatement ps = con.getCon().prepareStatement(sql);
									ps.setTimestamp(1, fromDate);
//									ps.setTimestamp(2, toDate);
									
			ResultSet rs 		= ps.executeQuery();
			
			while (rs.next()) {
				crawl_url = rs.getString("crawle_url2").trim().toString();
				flupd.fileUpload(id, crawl_url, driver1);
			}
		} catch (SQLException e) {
			System.out.println("Exe : "+e);
		} catch (ParseException pe) {
			System.out.println("ParseException : "+pe);
		} catch (Exception ex) {
			System.out.println("Exception : "+ ex);
		}
		
	} */

	public void mouseMove() {
		int TWO_SECONDS = 1000;
		int MAX_Y 		= 400;
		int MAX_X 		= 400;

		try {
			Robot robot 	= new Robot();
			Random random 	= new Random();
			
			int i = 0;
			int f = 30;
			
			while (true) {
				MAX_X = MAX_X + f;
				MAX_Y = MAX_Y + f;
				robot.mouseMove(MAX_X, MAX_Y);
				Thread.sleep(TWO_SECONDS);
				i++;
				if (i == 2) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured In MouseMove() : "+ e);
			e.printStackTrace();
		}
	}

	public List<String> fileUpload(int id, String crwl_url, WebDriver driver) {
		System.out.println("Inside FileUpload() !!");
		
		driver.get("about:config");

		boolean result 		= false;
		int pageWait 		= 5000;
		String fileName1 	= "";

		Robot robot 		= null;
		BufferedImage image = null;
		notCaptured 		= new HashSet<>();
		
		System.setProperty("java.awt.headless", "false");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		System.out.println("Screen Dimension Has Been Set !!");

		try {
			System.out.println("Inside Try Block !!");
			
			ConnectionOne dbCon = new ConnectionOne();
			connection			= dbCon.getCon();
			
			System.out.println("DB Connection Established !! : " + connection);
			System.out.println("Web Driver : " + driver);

			ftpServerAddress 	= "172.168.1.5";
			userName 			= "pradeep";
			password 			= "pj@123rkscan";
			
			if(linkDetail != null)
				System.out.println("Url Details From crawle_url4 Table : " + linkDetail);
			
			System.out.println("Url Details Query : " + urldetails);
			System.out.println("Url : " + crwl_url.trim());

			ps = connection.prepareStatement(urldetails);
			ps.setString(1, crwl_url.trim());
								   
			rs = ps.executeQuery();
			System.out.println("Query Executed !!");
			System.out.println("Executed ResultSets : "+ rs);

			System.out.println("Web Driver : " + driver);

			driver.get(crwl_url.trim());
			Thread.sleep(pageWait, 100);
			 System.out.println("Current URL : "+driver.getCurrentUrl() +" || Current Page Title : "+driver.getTitle());

			System.out.println("*** Capturing The Current Screen ***");
			
			try {
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				Thread.sleep(pageWait);
				
			} catch (Exception e) {
				System.err.println("Exception Occured On Page Loading : " + e);
			}
			
			while (rs.next()) {

				System.out.println("URL ID From ResultSet : " + rs.getString("id") == null ? "UrlIdNotFound" : rs.getString("id").toString());
				
				linkDetail.add(rs.getString("id") == null ? "UrlIdNotFound" : rs.getString("id").toString());
				linkDetail.add(rs.getString("dd"));
				linkDetail.add(rs.getString("project_id"));
				linkDetail.add(rs.getString("user_id"));

				System.out.println("URL_ID	: " + rs.getString("id") == null ? "UrlIdNotFound" : rs.getString("id").toString());
				System.out.println("Created_on : " + rs.getString("dd"));
				System.out.println("Project ID : " + rs.getString("project_id"));
				System.out.println("USER_ID : " + rs.getString("user_id"));
				
				if (rs.getString("ss_file_path") == null) {
					System.out.println("!! SSFilePathNotFound !!");
					linkDetail.add("SSFilePathNotFound");
				} else {
					System.out.println("Value Exists");
					linkDetail.add("valuealready");
				}
			}

		} catch (Exception ee) {
			System.out.println("Something Went Wrong In Transction With DB : " + ee);
			notCaptureLink.add(crwl_url);
			ee.printStackTrace();
		}

		if (linkDetail != null && linkDetail.size() > 0) {
			try {
				if (linkDetail.get(4).equalsIgnoreCase("SSFilePathNotFound")) {

					try {
//						***** Mark Scan FTP File Destination Path *****
						destinationName = "/opt/screenshots/";
//						destinationName = "D:\\screenshot\\";
						
						System.out.println("Link Details : " + linkDetail);
						System.out.println("File path : " + destinationName);
						
						fileName1 = linkDetail.get(1) + "_" + linkDetail.get(3) + "_" + linkDetail.get(2) + "_"
								+ linkDetail.get(3) + "_" + linkDetail.get(0);

//						***** Pointing Mouse Cursor On Video Player ******
						mouseMove();
						
//						***** screen capture *****
						robot = new Robot();
						
						System.out.println("File Name : " + fileName1);
						
						image = robot.createScreenCapture(screenRectangle);
						System.out.println("Captured Image : " + image);
						
						System.out.println("FTP File Name : "+ destinationName + fileName1 + ".png");
						
						try {
							file = new File(destinationName + fileName1 + ".png");
							boolean imageWriterResponse = ImageIO.write(image, "png", file);
							System.out.println(imageWriterResponse ? "*** Image File Successfully Uploaded ***" : " *** Failed To Upload The Image ***");
						} catch(IOException ioe) {
							System.out.println("Exception Occured While Writing Image File On Server : "+ ioe);
						} catch(Exception ex) {
							System.out.println("Exception Occured Image Writing || Creating File : "+ ex);
						}

					} catch (NumberFormatException ne) {
						System.err.println("NumberFormatException : "+ ne);
						error = "page wait is not a number";
						notCaptureLink.add(crwl_url);
						ne.printStackTrace();
						
					} catch (Exception ex) {
						// update in table file uploaded and store url of that image and image not capture
						System.err.println("Something Went Wrong !! : "+ ex);
						notCaptureLink.add(crwl_url);
						ex.printStackTrace();
					}
					
					try {
						ftpclient = new FTPClient();
						ftpclient.connect(ftpServerAddress);
						
						System.out.println("Establishing FTP Server Connection With : "+ftpServerAddress);
						
						result = ftpclient.login(userName, password);

						System.out.println(result ? "Logged in Successfully !" : "Login Fail!");
						
						ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
						ftpclient.changeWorkingDirectory("172.168.1.5" + "/");
						
					} catch(SocketException se) {
						System.err.println("SocketException Occured While Connecting With FTP Server - "+ftpServerAddress +" : "+ se);
						
					} catch(IOException io) {
						System.out.println("IOException Occured While Establishing FTP Connection : "+io);
						
					} catch(Exception ex) {
						System.err.println("Exception Occured In FTP Server : "+ ex);
					}
					
					String newFile1 = "";
					result 			= false;
					
					try {
					testName = destinationName + fileName1 + ".png";
					System.out.println("Uploading File Name : "+testName);
					
					fis 		= new FileInputStream(testName);
					newFile1 	= fileName1 + ".png";

					System.out.println("Processing To Upload File On FTP Server !!");

					result = ftpclient.storeUniqueFile(newFile1, fis);
					
					} catch (FileNotFoundException fnfe) {
						System.err.println("FileNotFoundException Occured In File Upload : "+fnfe);
					} catch (IOException ioe) {
						System.err.println("IOException Occured While Uplaoding File : "+ ioe);
					} catch (Exception ex) {
						System.err.println("Exception Occured In File Upload : "+ ex);
					}
					
					if (result == true) {
						System.out.println("File Uploaded Successfully !!");
						try {
							fileUploaded = fileUploaded + 1;
							path = "http://172.168.1.5/screenshot/" + newFile1;
							System.out.println(path);

							ps = connection.prepareStatement(ftpDetailsUpdate);
							ps.setString(1, linkDetail.get(3));
							ps.setString(2, currentTime());
							ps.setString(3, path);
							ps.setString(4, linkDetail.get(0));
							
							System.out.println("File Update Query : " + ps);
							int fileUpdateStatus = ps.executeUpdate();
							System.out.println("File Update Query Executed Status : " + fileUpdateStatus);
							
							if(!linkDetail.isEmpty())
								linkDetail.clear();
							
							if(!ps.isClosed())
								ps.close();
							
							if(!ps.isClosed())
								ps = null;
							
							if(newFile != null)
								newFile = null;
							
						} catch (Exception ex) {
							System.err.println("Exception Occured : "+ ex);
							ex.printStackTrace();
							
							if(!connection.isClosed())
								connection.close();
							
							errorCollection.put(crwl_url, "link update fail");
							notCaptureLink.add(crwl_url);
							newFile = null;
							linkDetail.clear();

						} finally {

							if(!linkDetail.isEmpty())
								linkDetail.clear();
							
							if(!ps.isClosed())
								ps.close();
							
							if(!ps.isClosed())
								ps = null;
							
							if(newFile != null)
								newFile = null;
						}
						// update in table file uploaded and store url of that image

					} else {
						String ftpLastResponse = ftpclient.getReplyString();
						System.err.println("FTP Last Response : "+ftpLastResponse);
						System.out.println("File uploading failed");

						if(!linkDetail.isEmpty())
							linkDetail.clear();
					}
					try {
						if(ftpclient != null)
							System.out.println(ftpclient.logout() ? "FTP Server Logout Successfully !!" : "FTP Sever Logout Failed !!");
					} catch(FTPConnectionClosedException fcce) {
						System.err.println("FTPConnectionClosedException : "+ fcce);
					} catch(IOException ioe) {
						System.err.println("IOException Occured While Logging Out Line - 478 !! : "+ ioe);
					} catch(Exception ex) {
						System.err.println("Exception Occurred While Logging Out Line - 480 : "+ ex);
					}
				} else {
					errorCollection.put(crwl_url, "Rename failed");
					
					if(newFile != null)
						newFile = null;
				}
				
				if(!connection.isClosed())
					connection.close();
				
			} catch (Exception ex) {
				System.err.println("Exception Occured in FILE UPLOAD : " + ex);
				errorCollection.put(crwl_url, "data not found/ archive");
				notCaptureLink.add(crwl_url);
			}

			finally {

				try {
					if(!ps.isClosed())
						ps.close();
					
					if(!rs.isClosed())
						rs.close();
					
					if(!connection.isClosed())
						connection.close();
					
				} catch (SQLException sqle) {
					System.err.println("Exception Occured While Closing Connection : "+ sqle);
					sqle.printStackTrace();
				}
				
				if(!linkDetail.isEmpty()) {
					linkDetail.clear();
				}
				
				ftpServerAddress = null;
				file 			 = null;

				try {
					if(!connection.isClosed())
						connection.close();
					
					if(ftpclient.isAvailable())
						ftpclient.disconnect();
					
				} catch (Exception ex) {
					System.out.println("Exception Occured : line 549 : " + ex);
				}
			}
		}
		
		if(!notCaptured.isEmpty())
			notCaptured.clear();

		file 				= null;
		fis 				= null;
		ftpServerAddress 	= null;
		userName 			= null;
		password 			= null;
		testName 			= null;
		path 				= null;
		
		return notCaptureLink;
	}

}
