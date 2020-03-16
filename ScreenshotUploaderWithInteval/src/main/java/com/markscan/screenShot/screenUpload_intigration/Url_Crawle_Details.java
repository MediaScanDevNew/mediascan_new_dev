package com.markscan.screenShot.screenUpload_intigration;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.markscan.screenShot.screenUpload_intigration.fileUpload.*;


@RestController
public class Url_Crawle_Details {
	
	private static final Logger logger = Logger.getLogger(Url_Crawle_Details.class);
	
	WebDriver driver = null;
	FileLock lock;
	FileChannel channel;
	List<String> notCaptureImage;
	
	public Url_Crawle_Details() {
		System.out.println("**************************Constructor executed***********************");
		System.setProperty("webdriver.gecko.driver","/opt/gckodriver/geckodriver");
//		System.setProperty("webdriver.firefox.marionette", "false");
//		System.setProperty("webdriver.gecko.driver","C:\\Users\\Kamlesh\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		
		if(this.driver == null)
			driver = new FirefoxDriver();
		
	}
	
	public static Date getCurrentTime() {
		Date date = new Date();
		return date;
	}
	
	@RequestMapping(value = "/check",  method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	
	public @ResponseBody String crawl_Url(@RequestHeader String Authorization) {
		try {
			System.out.println("Now in url_crawle_details Class !!");
			
			logger.info("Authorization : "+ Authorization);
			System.out.println("Authorization : "+ Authorization);
			
			System.out.println("FireFox Web Driver : "+driver);
			
			driver.manage().deleteAllCookies();	
			System.out.println("*** All Cookies Deleted *** !!");
			
			FileUpload flupld = FileUpload.getInstance();
			System.out.println("*** ThreadSafe FileUpload Instance Created ***");
			
			String pj1[] 	= Authorization.split(",");
			int id 			= 1;
			
			for (String pp : pj1) {
				String crwl_url =pp;
				
				System.out.println("URL To Capture Screenshot : "+crwl_url);
				
				notCaptureImage	 = flupld.fileUpload(id, crwl_url,driver);
				
				System.out.println("Not Captured Images : "+ notCaptureImage + " : notCaptureImage.size() : "+ notCaptureImage.size());
				logger.info("Not Captured Images : "+ notCaptureImage + " : notCaptureImage.size() : "+ notCaptureImage.size());
				id =id+1;
			}
			pj1=null;
			
			driver.get("about:config");
			
			return "Image Not capture=====" + notCaptureImage;
		
		} catch(Exception ex) {
			System.out.println("Exception Occured in URLCrawlDetails Class : "+ ex);
			return "Image Not capture=====" + notCaptureImage;
			
		} finally {
			System.out.println("*** Rechecking Image Path ***");
			Url_Crawle_Details.reCheckImagePathForNull(driver);
		}
	}
	
	public static void reCheckImagePathForNull(WebDriver driver) {
		try {
			ConnectionOne dbConnection 	= new ConnectionOne();
			FileUpload fileUpload 		= FileUpload.getInstance();
			int id						= 0;
			
			PreparedStatement preparedStatement = null;
			ResultSet resultSet					= null;
			
			List<String> NotCapturedUrl			= new ArrayList<String>();
			List<String> failedToCaptureAgain	= new ArrayList<String>();
			
			String sql = "select * from webinforcement_demo.crawle_url4 crl where (crl.created_on BETWEEN DATE_SUB(NOW(), INTERVAL 1 DAY) AND DATE_SUB(NOW(), INTERVAL 2 HOUR)) and crl.image_path is null and (crl.domain_name = 'youtube.com' or crl.domain_name='facebook.com' or crl.domain_name='dailymotion.com' or crl.domain_name='twitter.com' or crl.domain_name='instagram.com')";
			
			preparedStatement 	= dbConnection.getCon().prepareStatement(sql);									
			resultSet			= preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("*** Found Not Captured Screenshots ***");
				String failedUrl = resultSet.getString("crawle_url2").trim().toString();
				NotCapturedUrl.add(failedUrl);
			}
			
			System.out.println("NotCapturedUrl : "+ NotCapturedUrl +" :- NotCapturedUrl.size() : "+ NotCapturedUrl.size());
			
			for(String failedLink : NotCapturedUrl) {
				System.out.println("RecheckingForNotCapturedURL : "+ failedLink);
				failedToCaptureAgain = fileUpload.fileUpload(id, failedLink, driver);
				id = id + 1;
			}
			System.out.println("Failed To Capture Again : "+ failedToCaptureAgain +" :: Size() : "+failedToCaptureAgain.size());
			
			driver.get("about:config");
		
		} catch(SQLException se) {
			System.out.println("SQLException Occured : "+ se);
		} catch(Exception ex) {
			System.out.println("Exception : "+ ex);
		}
	}
}
