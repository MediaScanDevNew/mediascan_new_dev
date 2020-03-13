package com.pradeep.pj;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import com.pradeep.pj.repo.impl.IWLDataProcess;
import com.pradeep.pj.service.Blacklist_sitesService;
import com.pradeep.pj.service.BotRunningStatusService;
import com.pradeep.pj.service.Client_MasterService;
import com.pradeep.pj.service.Keyword_filterService;
import com.pradeep.pj.service.Markscan_machineService;
import com.pradeep.pj.service.Markscan_pipeService;
import com.pradeep.pj.service.Markscan_usersService;
import com.pradeep.pj.service.Master_crawle_urlService;
import com.pradeep.pj.service.Project_infoService;
import com.pradeep.pj.service.Stored_project_setupService;

import io.github.bonigarcia.wdm.WebDriverManager;

@RestController
public class BotSeApplication1 implements ServletContextAware {
	BotSeApplication1() {
		// System.out.println("--------------------------Constuctor of
		// BotSeApplication1----------");
	}

	@Autowired
	private Project_infoService pis;
	@Autowired
	private Keyword_filterService kfs;
	@Autowired
	private Blacklist_sitesService bss;
	@Autowired
	private Markscan_machineService mms;
	@Autowired
	private Stored_project_setupService sps;
	@Autowired
	private Markscan_pipeService mps;
	@Autowired
	private Master_crawle_urlService mcus;
	@Autowired
	private PageSourceBot ps;
	@Autowired
	private Markscan_usersService mus;
	@Autowired
	private BotRunningStatusService brss;
	@Autowired
	private Client_MasterService cms;

	@Autowired
	private WhitelistValidation wv;
	@Autowired
	private IWLEngine iwl;

	List<Object[]> data;
	List<String> data1;
	List<Object[]> udata;
	List<Object[]> pdata;
	List<Object[]> cdata;
	List<String> udata1;
	List<String> kquery;
	List<Object[]> maildata;

	int count;
	int property;
	List<Integer> usrList = null;
	Set<String> uemail;
	Set<String> cemail;
	String htmlpage = "";
	boolean sendMail = false;
	int count1 = 0;
	public static final int PAGES = 20;
	
	/**
	 * new code added by Pentation/M (22.01.2020) Update Machine 1 table after Bot Job finished.....
	 */
		private String ip = "172.168.1.15";
		private String port = "8083";
		
	    

	private ServletContext servletcontext;

	/*
	 * public static void main(String[] args) { //
	 * SpringApplication.run(BotSeApplication.class, args);
	 * SpringApplicationBuilder builder = new
	 * SpringApplicationBuilder(BotSeApplication.class);
	 * builder.headless(false); // System.setProperty("java.awt.headless",
	 * "true"); ConfigurableApplicationContext context = builder.run(args); }
	 * 
	 * //@Override
	 * 
	 */
	/*
	 * @RequestMapping(value = "/check", method = RequestMethod.GET, produces =
	 * { MediaType.APPLICATION_JSON_VALUE, // MediaType.APPLICATION_XML_VALUE })
	 */
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/check")
	// public String botStart(@RequestHeader String Authorization) throws
	// Exception {
	public String botStart() throws Exception {

		try {
			/***
			 * New Added By Pentation/M
			 * 
			 */

			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-gpu");
			options.addArguments("--headless");

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(options);
			serviceDetail();
			sendMail = false;
		} catch (Exception e) {
			// System.out.println("================Parent Exception==========" +
			// e);
			serviceDetail();

		}

		return "success";

	}

	public void serviceDetail() {
		try {
			/*
			 * String logFileName = "BOTSE_LOG.txt"; String uploadLogFile =
			 * servletcontext.getRealPath("/home/botserver3/") + logFileName;
			 * FileOutputStream fout=new FileOutputStream(uploadLogFile);
			 * PrintStream logWriter =new PrintStream(fout);
			 * logWriter.println("");
			 */
			/**
			 * new code added by Pentation/M (22.01.2020) Update Machine 1 table after Bot Job finished.....
			 */
				new IWLDataProcess().UpdateBOTMachine(ip,port,1);
			
			
			usrList = new ArrayList<Integer>();
			synchronized (this) {
				data = sps.findALLCustom(); // get_data_from_stored_project
				// logWriter.println("data size is -------->"+data.size());
				if ((data.size() < 1) && (sendMail == false)) {
					// //System.out.println("no any query for u .... '");
					try {
						// logWriter.println("data size is less then 1");
						// sendMail(machine);
						mms.machineStatusFree(myIP);
						driver.quit();
						driver.close();
						driver = null;
						usrList.clear();
						usrList.clear();
						data.clear();
						data1.clear();
						udata.clear();
						pdata.clear();
						cdata.clear();
						udata1.clear();
						kquery.clear();
						usrList.clear();
						uemail.clear();
						cemail.clear();
						keywordfilter.clear();
						blkListSites.clear();
						links.clear();
						getLink.clear();

						// System.out.println("no any query for u .... '");
						// System.exit(0);

					} catch (Exception e) {
						// System.exit(0);
					}

				} else {
					// logWriter.println("data size is equal or greater then
					// 1..");
					List<Integer> projectIdLT = new ArrayList<Integer>();
					for (Object[] pj : data) {

						id = ((Integer) pj[0]);
						int check_start = new IWLDataProcess().getCheckCompleted(id);
						if (check_start == 0) {
							// sps.allComplate(id);
							keyphrase = ((String) pj[1]);
							pipe = ((Integer) pj[2]);
							userId = ((Integer) pj[3]);
							projectId = ((Integer) pj[4]);
							System.out.println("project id is ---------------->" + projectId);
							projectIdLT.add(projectId);
							// }
							// }
							keywordfilter = new HashSet<>();
							property_name = pis.findALLCustom(projectId);
							String pj11[] = property_name.trim().split(" ");

							for (String a : pj11) {
								keywordfilter.add(a.trim());
							}

							pipe_domain = mps.findALLCustom(pipe);
							// data1 = kfs.findALLCustom(projectId);

							for (Object[] pj1 : data) {
								keywordfilter.add(pj1[0].toString().trim());
							}
							if (keyphrase.contains("&")) // http://www.w3schools.com/tags/ref_urlencode.asp
							{
								keyphrase = keyphrase.replaceAll("&", "%26");
							}
							if (keyphrase.contains("#")) {
								keyphrase = keyphrase.replaceAll("#", "%23");
							}
							if (keyphrase.contains(";")) {
								keyphrase = keyphrase.replaceAll(";", "%3B");
							}

							if (pipe == 0) {

								sps.googleStart(id);
								// logWriter.println("Google Search Started
								// .....");
								// google search==
								googleSearch(keyphrase);
								// logWriter.println("Google Search
								// Completed.links size is ....."+links.size());
								// logWriter.println("Google Data Save Start
								// .....");
								afterCrawl(1, keyphrase);
								// logWriter.println("Google Data Save Complete
								// .....");
								links = null;

								sps.yahooStartGoogleComplate(id);

								// yahoo search==
								// logWriter.println("Yahoo Search started
								// .....");
								yahooSearch(keyphrase);
								// logWriter.println("Yahoo Search
								// Completed.links size is ....."+links.size());
								// logWriter.println("Yahoo Data Save Start
								// .....");
								afterCrawl(2, keyphrase);
								// logWriter.println("Yahoo Data Save Complete
								// .....");
								links = null;

								sps.bingStartYahooComplate(id);

								// Bing search==
								// logWriter.println("Bing Search started
								// .....");
								bingSearch(keyphrase);
								// logWriter.println("Bing Search
								// Completed.links size is ....."+links.size());
								// logWriter.println("Bing Data Save Start
								// .....");
								afterCrawl(3, keyphrase);
								// logWriter.println("Bing Data Save Complete
								// .....");
								links = null;

								/*
								 * Add this by pentation team
								 * 
								 * fetch the data from duck duck go search
								 * engine and save the data in database
								 */
								sps.duckduckStartBingComplete(id);
								// logWriter.println("Duck Duck Go Search
								// started .....");
								duckduckSearch(keyphrase);
								// logWriter.println("Duck Duck Go Search
								// Completed.links size is ....."+links.size());
								// logWriter.println("Duck Duck Go Data Save
								// Start .....");
								afterCrawl(4, keyphrase);
								// logWriter.println("Duck Duck Go Data Save
								// Complete .....");
								links = null;
								// ------------------------------------------------------------

								/*
								 * Add this by pentation team
								 * 
								 * fetch the data from russia go search engine
								 * and save the data in database
								 */

								// System.out.println("id value is --------->" +
								// id);
								sps.russiaGoStartduckduckGoComplete(id);
								// logWriter.println("Russia Go Search started
								// .....");
								russiaGoSearch(keyphrase);
								// logWriter.println("Russia Go Search
								// Completed.links size is ....."+links.size());
								// logWriter.println("Russia Go Data Save Start
								// .....");
								afterCrawl(5, keyphrase);
								// logWriter.println("Russia Go Data Save
								// Complete .....");
								links = null;
								/**
								 * Added on Jan/27 to track end of Russian
								 * Search engine crawl
								 **/
								/** End of Russian Search Engine */
								sps.russiacomplete(id);

								// logWriter.println("Search Process Complete
								// .....");
								// ---------------------------------------------------------------
							} else if (pipe == 1) {
								sps.googleStart(id);
								// google search==
								googleSearch(keyphrase);
								afterCrawl(1, keyphrase);

								links = null;
							} else if (pipe == 2) {

								sps.yahooStart(id);
								yahooSearch(keyphrase);
								afterCrawl(2, keyphrase);
								links = null;
							} else if (pipe == 3) {
								sps.bingStart(id);
								bingSearch(keyphrase);
								afterCrawl(3, keyphrase);
								links = null;

								// sps.bingComplate(id);
								// ----------------- Pentation
								// -----------------------------
							} else if (pipe == 4) {

								/*
								 * Add this by pentation team
								 * 
								 * fetch the data from duck duck go search
								 * engine and save the data in database
								 */

								sps.duckduckStart(id);
								duckduckSearch(keyphrase);
								// System.out.println("===== duckduck go ===
								// links size......." + links.size());
								afterCrawl(4, keyphrase);
								links = null;

							} else if (pipe == 5) {

								/*
								 * Add this by pentation team
								 * 
								 * fetch the data from russia go search engine
								 * and save the data in database
								 */

								sps.russiaGoStart(id);
								russiaGoSearch(keyphrase);
								afterCrawl(5, keyphrase);
								links = null;

								/**
								 * Added on Jan/27 to track end of Russian
								 * Search engine crawl
								 **/
								/** End of Russian Search Engine */
								sps.russiacomplete(id);
								// ----------------- Pentation
								// -----------------------------
							} else if (pipe > 5) {

								sps.googleOversiesStart(id);
								// dao.customUpdateProject("update
								// Stored_project_setup set
								// google=2 where id=" + id);
								googleOversies(keyphrase, pipe_domain);
								// System.out.println("=====google geo=== links
								// size......." + links.size());
								afterCrawl(pipe, keyphrase);
								// afterCrawlOversies(pipe);
								links = null;
								sps.googleOversiesComplate(id);
							}

							try {

								sps.allComplate(id);

								/*
								 * Add this by pentation team
								 * 
								 * Add these three function for white list,grey
								 * list ,black list validation
								 */

								// WhitelistValidation wv = new
								// WhitelistValidation();

								// logWriter.println("Whitelist Process start
								// .....");
								wv.whitelistChecking(projectId);
								// logWriter.println("Whitelist Process
								// complete,Greylist Process Start .....");
								wv.greylistChecking(projectId);
								// logWriter.println("Greylist Process
								// complete,blacklist Process Start .....");
								wv.blacklistChecking(projectId);
								// logWriter.println("Blacklist Process complete
								// .....");
								// mms.machineStatusFree(myIP);

								wv.insertIntoCrawl(projectId);
							} catch (Exception e) {
								e.printStackTrace();
								// logWriter.println("Exception
								// ---->"+e.getMessage());
								// logWriter.close();
							}
							// //System.out.println("crawl is complete");

							// JOptionPane.showMessageDialog(null, "crawl is
							// complete");

							// ****************** send mail to user

							sendMailToUser(userId, projectId, id);
						} // end if condition

					} // end for loop part here
					
					
					//----------------------------------------------------------------------------------------------
					/**
					 * new code added by Pentation/M (22.01.2020) Update Machine 1 table after Bot Job finished.....
					 */
					new IWLDataProcess().UpdateBOTMachine(ip,port,0);
					
					//-----------------------------------------------------------------------------------------------
					// ---------------------------------------------------------------------------------------------
					/**
					 * new code added by Pentation/M (22.01.2020) call iwl
					 * enginee from BOT SE application.....
					 */

					/**
					 * new code added by Pentation/M (29.01.2020) call iwl
					 * enginee from BOT SE application.....
					 */

					// -----------------------------------------------------------------------------------------------
					// logWriter.println("IWL Enginee Process start .....");
					Set<Integer> idWithoutDuplicates = new LinkedHashSet<Integer>(projectIdLT);
					projectIdLT.clear();
					projectIdLT.addAll(idWithoutDuplicates);
					String projectIds = "";
					for (Integer i : projectIdLT) {
						try {
							// logWriter.println("IWL Enginee Project id
							// ....."+i);
							int type_val = new IWLDataProcess().getProjectType(i);
							if (type_val != 2 || type_val != 9) {
								// iwl.iwlEngine(i);
								projectIds = projectIds + i;
							}
						} catch (Exception ex) {
							// logWriter.println("Exception
							// ---->"+ex.getMessage());
							// logWriter.close();

						}
					}

					ResourceBundle rb = ResourceBundle.getBundle("application");
					String connection_url = rb.getString("spring.iwl.url");
					String otpurl = rb.getString("url") + "/startIWL?projectIds=" + projectIds;
					System.out.println(otpurl);
					URL url = new URL(otpurl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					
					

					// logWriter.println("IWL Enginee Process end .....");
					// logWriter.close();
				} // end else part here

			} // end sync this end here

			// }
			// System.out.println("*******************************User
			// Name**********" + userId);

			if (sendMail == false) {
				// serviceDetail();
			}

		} catch (Exception e) {
			/**
			 * new code added by Pentation/M (22.01.2020) Update Machine 1 table after Bot Job finished.....
			 */
			try {
				new IWLDataProcess().UpdateBOTMachine(ip,port,0);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//e.printStackTrace();

			try {
				driver.close();
				driver.quit();
			} catch (Exception ee) {
			}
			// JOptionPane.showMessageDialog(null, "crawl error");
			// System.out.println("**********************crawl error*********");

			// factory = null;
			// HbmTemplateUtils.killMYSQLConnection();
			// System.exit(0);
		}

	}

	/*
	 * public void googleSearch(String searchKeyword) { links = new
	 * ArrayList<>(); // searchKeyword = dd; searchEngine =
	 * "https://www.google.co.in"; try {
	 * //System.out.println("Start Crawle Url...................");
	 * driver.get("https://www.google.co.in/search?q=" + searchKeyword);
	 * Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
	 * getLink = driver.findElements(By.xpath("//div[@class='r']/a")); for
	 * (WebElement we : getLink) { links.add(we.getAttribute("href"));
	 * //System.out.println("Crawle Url..................."+we.getAttribute(
	 * "href" )); } WebElement next =
	 * driver.findElement(By.xpath("//a[@id='pnnext']"));
	 * //System.out.println(next.getAttribute("href")); for (int i = 1; i <= 30;
	 * i++) { try { Thread.sleep(random_number(8000, 4000), random_number(1000,
	 * 100)); // driver.get(next.getAttribute("href")); getLink =
	 * driver.findElements(By.xpath("//div[@class='r']/a")); for (WebElement we
	 * : getLink) { links.add(we.getAttribute("href"));
	 * 
	 * }
	 * 
	 * next = driver.findElement(By.xpath("//a[@id='pnnext']")); if
	 * (next.isDisplayed() || next.isEnabled()) { next.click(); } else {
	 * //System.out.println("else break"); break; }
	 * //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); }
	 * catch (Exception e) {
	 * //System.out.println("This is the Last Page, Next button not available");
	 * // e.printStackTrace(); break; } }
	 * 
	 * } catch (NoSuchElementException e) {
	 * //System.out.println("Element Not Found"+e.getMessage()); }
	 * 
	 * catch (Exception e) {
	 * //System.out.println("Common Exception"+e.getMessage()); } }
	 */

	public void yahooSearch(String searchKeyword) {
		// System.out.println("Search Keyword Yahoo----"+searchKeyword);

		String xpthAnchorYahoo = "//h3[@class='title']//a[1]";
		String xpthNextYahoo = "//a[@class='next' and text()='Next']";

		String hitSearchButton = "//*[@type='submit' and @class='sbb']";
		String hitSearchButton1 = "//button[@type='submit']";
		String hitYahooSearch = "//input[@id='uh-search-box' and @name='p']";
		String getTitleWithOutOpenThePage = "//h3[@class='title']";
		String getTitleWithOutOpenThePage1 = "//*[@class='title']";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		List<WebElement> getTitle = null;
		try {
			String yahooURL = "https://in.yahoo.com/";
			// System.out.println("-------Yahoo ---------- "+yahooURL);
			driver.get(yahooURL);
			// driver.findElement(By.xpath(hitSearchButton1)).sendKeys(searchKeyword,
			// Keys.RETURN);
			driver.findElement(By.xpath(hitYahooSearch)).sendKeys(searchKeyword, Keys.RETURN);

			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			WebElement nextYahoo = driver.findElement(By.xpath(xpthNextYahoo));
			// System.out.println("===Yahoo link===" +
			// nextYahoo.getAttribute("href"));
			for (int i = 1; i <= PAGES; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorYahoo));
					getTitle = driver.findElements(By.xpath(getTitleWithOutOpenThePage));
					int index = 0;
					for (WebElement we : getLink) {

						try {
							// getTitleFromEachLinks(); // later hide
							String getTitleWithOutOpenPage = getTitle.get(index++).getText();
							// System.out.println("getTitleWithOutOpenPage-Yahoo:-"+getTitleWithOutOpenPage);

							if (getTitleWithOutOpenPage.contains("...")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("...", "");
								// System.out.println("Replace-Yahoo:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);

							} else if (getTitleWithOutOpenPage.contains("…")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("…", "");
								// System.out.println("Replace-Yahoo:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);
							} else {
								links.add(we.getAttribute("href") + "#" + getTitleWithOutOpenPage + "#" + i + "#"
										+ index);
							}

							// System.out.println("----------------------------------");
						} catch (StaleElementReferenceException stale) {
							// System.out.println("StaleElementReferenceException:-"+stale.getMessage());
						}
					}

					// Selenium for checking Button is present or not first he
					// clicked then check
					nextYahoo = driver.findElement(By.xpath(xpthNextYahoo));
					if (nextYahoo.isDisplayed() || nextYahoo.isEnabled()) {
						// System.out.println("----------------"+"\n"+"founded -
						// Next Page");
						nextYahoo.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						// System.out.println("not founded");
						// System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					// System.out.println("\n"+"==== next button not
					// available====");
					break;
				} catch (Exception e) {
					// System.out.println("error on Yahoo search");
					e.printStackTrace();
					break;
				}
			}
			// System.out.println("\n"+" - Yahoo links- "+links); // print all
			// the link comma separated
		} catch (Exception e) {
			// browser exception will be caught here
			e.printStackTrace();
			System.err.println("=== Yahoo  search error=====");
		}
	}

	public void googleOversies(String searchKeyword, String domain) {
		links = new ArrayList<>();

		try {
			String q = domain + searchKeyword;

			driver.get(q);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath("//h3[@class='r']/a"));
			for (WebElement we : getLink) {
				links.add(new String(we.getAttribute("href").getBytes(), "UTF-16"));
			}
			WebElement next = driver.findElement(By.xpath("//a[@id='pnnext']"));
			next.click();
			for (int i = 1; i <= PAGES; i++) {

				try {
					Thread.sleep(random_number(8000, 2000), random_number(1000, 100));
					driver.get(next.getAttribute("href"));
					// next.click();
					getLink = driver.findElements(By.xpath("//h3[@class='r']/a"));
					for (WebElement we : getLink) {
						links.add(new String(we.getAttribute("href").getBytes(), "UTF-8"));
					}
					next = driver.findElement(By.xpath("//a[@id='pnnext']"));
					if (next.isDisplayed() || next.isEnabled()) {
						// System.out.println("founded");
						next.click();
					} else {
						// System.out.println("else break");
						break;
					}
				} catch (Exception e) {
					// System.out.println("error on oversies search");
					e.printStackTrace();

					break;

				}
			}

		} catch (NoSuchElementException e) {
			// TODO: handle exception
			// System.out.println("==== next button not available====");
		}

		catch (Exception e) {
			// System.out.println("=== google oversies error = = = =");
			e.printStackTrace();
			// driver.quit();
			// System.exit(0);
		}
	}

	public void afterCrawl(int ppipe, String searchKeyword) {
		// PageSource ps = new PageSource();
		String pagesource = null;
		List<String> searchkeywordlist = new ArrayList<>();
		// System.out.println("=== links size=== " + links.size());

		Set<String> linkWithDuplicates = new LinkedHashSet<String>(links);
		links.clear();
		links.addAll(linkWithDuplicates);

		if (links.size() > 0) {
			int i = 1;
			for (String lnk : links) {
				String[] arr = lnk.split("#");
				String lnk1 = "";
				String title = "";
				String page_no = "";
				String page_rank = "";
				if (arr.length == 1) {
					lnk1 = arr[0];
					title = "NA";
					page_no = "NA";
					page_rank = "NA";
				} else if (arr.length > 2) {
					lnk1 = arr[0];
					title = arr[1];
					page_no = arr[2];
					page_rank = arr[3];
				}

				if (!lnk1.equals("") && !title.equals("")) {
					b2 = false;
					// System.out.println("==========lnk===" + lnk1);
					try {

						if (lnk1.toLowerCase().contains(property_name.trim().toLowerCase())) {

							/**
							 * Add this feature by Pentation team
							 * 
							 * here we fetch tile from each individual link
							 */

							// System.out.println("The save title is
							// ------------------------------>"+title);

							mcus.storeCrawleData(lnk1, projectId, userId, id, ppipe, machine, findDomain(lnk1.trim()),
									title, page_no, page_rank);
							// System.out.println("=== running.....first.
							// running..............");
							b2 = true;
							// break;
						}
						try {
							String pj[] = searchKeyword.split(" ");
							for (String s : pj) {
								if (s != null && s.length() > 0) {
									searchkeywordlist.add(s);
								}
							}

						} catch (Exception e) {
							// TODO: handle exception
						}
						if (b2 == false) {
							for (String srckey__ : searchkeywordlist) {
								// //System.out.println("=== srckey__
								// .............." + srckey__);
								if (lnk1.toLowerCase().contains(srckey__.trim().toLowerCase())) {
									/**
									 * Add this feature by Pentation team
									 * 
									 * here we fetch tile from each individual
									 * link
									 */

									// String title =
									// getTitleFromEachLinks(lnk1);
									// System.out.println("The save title is
									// ------------------------------>"+title);
									mcus.storeCrawleData(lnk1, projectId, userId, id, ppipe, machine,
											findDomain(lnk1.trim()), title, page_no, page_rank);

									b2 = true;
									break;
								}
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						// e.printStackTrace();
					}

					if (b2 == false) {

						try {

							doc = Jsoup.connect(lnk1.trim()).get();
							pagesource = doc.body().text();

						} catch (Exception e) {
							// TODO: handle exception
							if (lnk1.contains("youtube.com") || lnk1.contains("facebook.com")) {

							} else
								try {
									pagesource = ps.getPageSource(lnk1.trim());
								} catch (Exception e2) {

									// TODO: handle exception
								}
						}

						try {

							if (pagesource.toLowerCase().contains(property_name.trim().toLowerCase())) {

								/**
								 * Add this feature by Pentation team
								 * 
								 * here we fetch tile from each individual link
								 */

								// String title = getTitleFromEachLinks(lnk1);
								// System.out.println("The save title is
								// ------------------------------>"+title);
								mcus.storeCrawleData(lnk1, projectId, userId, id, ppipe, machine,
										findDomain(lnk1.trim()), title, page_no, page_rank);
								b2 = true;
								// break;
							}

						} catch (Exception e) {
							// TODO: handle exception
							// e.printStackTrace();
						}
					}

					if (b2 == false) {
						for (String pj : keywordfilter) {
							try {
								if (pagesource.toLowerCase().contains(pj.trim().toLowerCase())) {
									/**
									 * Add this feature by Pentation team
									 * 
									 * here we fetch tile from each individual
									 * link
									 */

									// String title =
									// getTitleFromEachLinks(lnk1);
									// System.out.println("The save title is
									// ------------------------------>"+title);
									mcus.storeCrawleData(lnk1, projectId, userId, id, ppipe, machine,
											findDomain(lnk1.trim()), title, page_no, page_rank);
									// break;
								}
							} catch (Exception e) {
								// e.printStackTrace();
							}

						}

					} // end if condition
				}

			} // end for loop
		}
	}

	public String findDomain(String ddomain) {
		String domain__c = null;
		try {

			// //System.out.println("=pp=" + ddomain.lastIndexOf("/"));

			if (ddomain.startsWith("https:")) {
				domain__c = ddomain.replace("https://", "");
				domain__c = domain__c.replace("www.", "");
				// //System.out.println("domain===1..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.1.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.112.." + domain__c);
				}

			} else if (ddomain.startsWith("http:")) {
				domain__c = ddomain.replace("http://", "");
				domain__c = domain__c.replace("www.", "");
				// //System.out.println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.2.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("//www.")) {
				domain__c = ddomain.replace("//www.", "");
				domain__c = domain__c.replace("www.", "");
				// //System.out.println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.3.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("www")) {
				// domain__c = ddomain.replace("//www.", "");
				domain__c = ddomain.replace("www.", "");
				// //System.out.println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.33.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// //System.out.println("domain===4.114.." + domain__c);
				}
			}

			else if (!ddomain.startsWith("http:") || !ddomain.startsWith("htts:") || !ddomain.startsWith("www.")) {
				String pj[] = ddomain.split("/");
				domain__c = pj[0];
				// //System.out.println("domain===4.114.." + domain__c);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("find domain error... ", e);
		}
		return domain__c;
	}

	public int random_number(int max, int min) {
		return (min + (int) (Math.random() * ((max - min) + 1)));
	}

	public String myIPaddress() {
		// InetAddress ip;
		try {
			myIP = getSystemIP4Linux("eth0");
			if (myIP == null) {
				myIP = getSystemIP4Linux("eth1");
				if (myIP == null) {
					myIP = getSystemIP4Linux("eth2");
					if (myIP == null) {
						myIP = getSystemIP4Linux("usb0");
					}
				}
			}

			/*
			 * 
			 * ip = InetAddress.getLocalHost(); myIP = ip.getHostAddress();
			 * 
			 * if (!myIP.contains("172.168.1.")) { NetworkInterface ni =
			 * NetworkInterface.getByName("eth0"); Enumeration<InetAddress>
			 * inetAddresses = ni.getInetAddresses(); while
			 * (inetAddresses.hasMoreElements()) { InetAddress ia =
			 * inetAddresses.nextElement(); if (!ia.isLinkLocalAddress()) { myIP
			 * = ia.getHostAddress(); } } }
			 */
			// System.out.println("Current IP address : " + myIP);
			machine = myIP;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return myIP;
	}

	// *******************************Linux IP
	// Address*****************************/
	private static String getSystemIP4Linux(String name) {
		try {
			String ip = "";
			NetworkInterface networkInterface = NetworkInterface.getByName(name);
			Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
			InetAddress currentAddress = inetAddress.nextElement();
			while (inetAddress.hasMoreElements()) {
				currentAddress = inetAddress.nextElement();
				if (currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress()) {
					ip = currentAddress.toString();
					break;
				}
			}
			if (ip.startsWith("/")) {
				ip = ip.substring(1);
			}
			return ip;
		} catch (Exception E) {
			System.err.println("System Linux IP Exp : " + E.getMessage());
			return null;
		}
	}

	// ************************** mail sending code
	// ************************************

	/*
	 * @Value("${spring.mail.host}") String mailip; public void
	 * sendMailToUser(int userId, int projectId) {
	 * 
	 * uemail = new HashSet<String>(); cemail = new HashSet<String>(); //
	 * Integer id =userId; // String pname =pis.findALLCustom(projectId); udata
	 * = mus.findUserDetails(userId); // String email=""; String uName = "";
	 * String client_name = ""; // String htmlpage="";
	 * 
	 * for (Object[] usr : udata) { uemail.add((String) usr[0]); uName =
	 * ((String) usr[1]); } String pName = ""; int ctype = 0; pdata =
	 * pis.findCustomData(projectId); for (Object[] prj : pdata) { pName =
	 * (String) prj[0]; ctype = Integer.parseInt((String) prj[1]);
	 * 
	 * //System.out.println("**********Client Type*********" + ctype);
	 * 
	 * } //System.out.println("*********Client Type1*********" + ctype); cdata =
	 * cms.findCustomData(ctype); for (Object[] cmd : cdata) { client_name =
	 * (String) cmd[0]; cemail.add((String) cmd[1]); // ctype=(Integer)cmd[1];
	 * //System.out.println("**********************Client Email*********" +
	 * cemail);
	 * 
	 * }
	 * 
	 * //brss.botStatusStoredData(uName, email, pname, keyphrase, machine);
	 * htmlpage = htmlpage + "<tr><td>" + keyphrase + "</td><td>" + pName +
	 * "</td><td>" + client_name + "</td><td>" + uName + " </td></tr>";
	 * 
	 * 
	 * htmlpage="<p>Dear "
	 * +uName+",</p><p> Bot Search Application has been completed query: "
	 * +keyphrase+" Now You can extract data"
	 * +"from mediascan application.</p><p> thanks &amp; regards:<br> Harendra Preatap Singh</p>"
	 * ;
	 * 
	 * //System.out.println("**********************Email*********"+email);
	 * //System.out.println("**********************UserName******"+uName);
	 * Properties props = new Properties(); props.put("mail.smtp.auth", "true");
	 * props.put("mail.smtp.starttls.enable", "true");
	 * props.put("mail.smtp.host", mailip); props.put("mail.smtp.port", "25");
	 * Session session = Session.getInstance(props, new
	 * javax.mail.Authenticator() { protected PasswordAuthentication
	 * getPasswordAuthentication() { return new
	 * PasswordAuthentication("hpsingh@markscan.co.in", "M@123rkscan"); } });
	 * try {
	 * 
	 * Message message = new MimeMessage(session); message.setFrom(new
	 * InternetAddress("hpsingh@markscan.co.in")); //
	 * message.setRecipients(Message.RecipientType.TO,
	 * InternetAddress.parse("pjoshi@markscan.co.in"));
	 * message.setRecipients(Message.RecipientType.TO,
	 * InternetAddress.parse(email));
	 * 
	 * message.setRecipients(Message.RecipientType.CC,
	 * InternetAddress.parse("spandit@markscan.co.in"));
	 * message.setRecipients(Message.RecipientType.BCC,
	 * InternetAddress.parse("hpsingh@markscan.co.in"));
	 * message.setSubject("Bot Application Running Status ");
	 * 
	 * message.setContent(htmlpage, "text/html");
	 * 
	 * // Send message
	 * 
	 * Transport.send(message);
	 * //System.out.println("Sent message successfully...."); } catch (Exception
	 * e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	
	String mailip;

	public void sendMailToUser(int userId, int projectId, int id) {

		uemail = new HashSet<String>();
		cemail = new HashSet<String>();
		udata = mus.findUserDetails(userId);
		// String email="";
		String uName = "";
		String userMail = "";
		String client_name = "";
		// String htmlpage="";

		for (Object[] usr : udata) {
			uemail.add((String) usr[0]);
			// userMail = ((String) usr[0]);
			uName = ((String) usr[1]);
		}

		String pName = "";
		int ctype = 0;
		pdata = pis.findCustomData(projectId);
		for (Object[] prj : pdata) {
			pName = (String) prj[0];
			ctype = Integer.parseInt((String) prj[1]);
		}

		cdata = cms.findCustomData(ctype);
		for (Object[] cmd : cdata) {
			client_name = (String) cmd[0];
			cemail.add((String) cmd[1]);
		}

		/*
		 * Object[] arr = cemail.toArray(); if(arr.length > 0 && arr[0] !=
		 * null){ userMail = ((String) arr[0]); }else{ userMail =
		 * "smohindru@markscan.co.in"; }
		 */

		userMail = "smohindru@markscan.co.in";

		maildata = sps.getDataForMails(projectId);

		htmlpage = "";
		htmlpage = htmlpage + "Hi All,<br/>";
		htmlpage = htmlpage + "Bot Search Application has been completed successfully :<br/><br/>";
		htmlpage = htmlpage + "Query:<br/>";
		htmlpage = htmlpage
				+ "<table border='1'><tr><td><b>Keywords</b></td><td><b>Project Name</b></td><td><b>Client Name</b></td><td><b>Created By</b></td></tr>";
		for (Object[] md : maildata) {
			htmlpage = htmlpage + "<tr><td>" + (String) md[0] + "</td><td>" + (String) md[1] + "</td><td>"
					+ (String) md[2] + "</td><td>" + uName + "</td></tr>";
		}

		htmlpage = htmlpage + "</table>";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "111.118.215.222");
		props.put("mail.smtp.port", "25");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mediascan@markscan.co.in", "M@123rkscan");
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mediascan@markscan.co.in"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMail));

			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(""));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("pradipta.maitra@gmail.com"));// pradipta.maitra@gmail.com
			message.setSubject("Bot Application Running Status ");

			message.setContent(htmlpage, "text/html");

			// Send message

			Transport.send(message);
			htmlpage = null;
			maildata.clear();
			System.out.println("Mail Send Successfully -----------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// } mms.machineStatusFree(myIP);
	public void sendMail(String myIp) {
		Set<String> email = new HashSet<String>();
		// String htmlpage="";
		String htmlpage1 = "";
		/*
		 * List<Object[]> maildata =brss.findALLCustom(myIp); for(Object[]
		 * hp:maildata) {
		 * 
		 * ////System.out.println("*********ID********"+ hp[0]); String userName
		 * =((String)hp[1]); //System.out.println("*********User Name********"+
		 * userName); String userEmail =((String)hp[2]); email.add(userEmail);
		 * //System.out.println("*********User Email********"+ userEmail);
		 * String pName =((String)hp[3]);
		 * //System.out.println("*********Project Name********"+ pName); String
		 * keywords =((String)hp[4]);
		 * //System.out.println("*********Keyword********"+ keywords); htmlpage
		 * ="<tr><td>"+keywords+"</td><td>"+pName+"</td><td>"+
		 * userName+" </td></tr>";
		 * 
		 * }
		 */
		sendMail = true;
		// System.out.println("*****************htmlpage**********" + htmlpage);
		htmlpage1 = "<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"
				+ "<html><head>" + "<style>table {border-collapse: collapse;} table, td, th "
				+ "{border: 1px solid black;}th{background-color: lightgray;}</style></head><body>"
				+ "<p>Hi all,</p><p> Bot Search Application has been completed query: <br> <table><tr><th>Keywords</th><th>Project Name</th><th>Client Name</th><th>Created By</th></tr> "
				+ htmlpage + "</table><br> Now You can extract data"
				+ "from mediascan application.</p><p> thanks &amp; regards:<br>MediaScan </p></body></html>";
		// System.out.println("********************htmlpage1*********" +
		// htmlpage1);
		String to = "";
		for (String email1 : uemail) {
			to = email1 + ",";
		}
		for (String email2 : cemail) {
			to = email2 + ",";
		}
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", mailip);
		props.put("mail.smtp.port", "25");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mediascan@markscan.co.in", "M@123rkscan");
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mediascan@markscan.co.in"));
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse("pjoshi@markscan.co.in"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(""));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("pradipta.maitra@gmail.com"));
			message.setSubject("Bot Application Running Status ");

			message.setContent(htmlpage1, "text/html");

			// Send message

			Transport.send(message);
			htmlpage = null;
			htmlpage1 = null;
			// brss.mailSendUpdate();
			// message=null;
			// System.out.println("Sent message successfully....");
			usrList.clear();
			usrList.clear();
			data.clear();
			data1.clear();
			udata.clear();
			pdata.clear();
			cdata.clear();
			udata1.clear();
			kquery.clear();
			usrList.clear();
			uemail.clear();
			cemail.clear();
			keywordfilter.clear();
			blkListSites.clear();
			links.clear();
			getLink.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Search DuckDuckGo Search Engine
	 * 
	 */

	public void duckduckSearch(String searchKeyword) {
		// System.out.println("Search Keyword duckduck go----"+searchKeyword);

		String xpthAnchorDuck = "//h2[@class='result__title']//a[1]";
		String xpthNextDuck = "//a[text()='More results' and @class='result--more__btn btn btn--full']";
		String getTitleWithOutOpenThePage = "//h2[@class='result__title']";
		String getTitleWithOutOpenThePage2 = "//*[@class='result__title']";
		String getPageNumberDuckDuckGo = "//div[@class='result__pagenum  result__pagenum--side']";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		List<WebElement> getTitle = null;
		try {
			String duckURL = "https://duckduckgo.com/?q=" + searchKeyword;

			driver.get(duckURL);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath(xpthAnchorDuck));

			WebElement nextDuck = driver.findElement(By.xpath(xpthNextDuck));
			// System.out.println("===duckduckgo link===" +
			// nextDuck.getAttribute("href"));
			for (int i = 1; i <= PAGES; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorDuck));
					getTitle = driver.findElements(By.xpath(getTitleWithOutOpenThePage));
					int index = 0;
					for (WebElement we : getLink) {

						try {
							// getTitleFromEachLinks(); // later hide
							String getTitleWithOutOpenPage = getTitle.get(index++).getText();
							// System.out.println("getTitleWithOutOpenPage-DuckDuckGo:-"+getTitleWithOutOpenPage);

							if (getTitleWithOutOpenPage.contains("...")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("...", "");
								// System.out.println("Replace-DuckDuckGo:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);

							} else if (getTitleWithOutOpenPage.contains("…")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("…", "");
								// System.out.println("Replace-DuckDuckGo:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);
							} else {
								links.add(we.getAttribute("href") + "#" + getTitleWithOutOpenPage + "#" + i + "#"
										+ index);
							}

							// System.out.println("----------------------------------");
						} catch (StaleElementReferenceException stale) {
							// System.out.println("StaleElementReferenceException:-"+stale.getMessage());
						}
					}

					// Selenium for checking Button is present or not first he
					// clicked then check
					nextDuck = driver.findElement(By.xpath(xpthNextDuck));
					if (nextDuck.isDisplayed() || nextDuck.isEnabled()) {
						// System.out.println("----------------"+"\n"+"founded -
						// Next Page");
						nextDuck.click();
						// get page No
						List<WebElement> printPageCount = driver.findElements(By.xpath(getPageNumberDuckDuckGo));
						int getPageNoCount = printPageCount.size();
						// System.out.println("COUNT:-"+getPageNoCount);
						for (int co = 0; co < getPageNoCount; co++) {
							if (printPageCount.size() == 1) {
								String pageNo = printPageCount.get(co).getText();
								// System.out.println("Page No:-"+pageNo);
							} else if (printPageCount.size() == printPageCount.size()) {
								String pageNo = printPageCount.get(getPageNoCount - 1).getText();
								// System.out.println("Page No:-"+pageNo);
								break;
							}

						}
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						// System.out.println("not founded");
						// System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					// System.out.println("\n"+"==== next button not
					// available====");
					break;
				} catch (Exception e) {
					// System.out.println("error on DuckDuck search");
					e.printStackTrace();
					break;
				}
			}
			// System.out.println("\n"+" - Duck links- "+links);
		} catch (Exception e) {
			// browser exception will be caught here
			e.printStackTrace();
			System.err.println("=== DuckDuck go  search error=====");
		}
	}

	// *************28-01-2020 by pentation/m************************//
	public String getTitleFromEachLinks(String lnk) {

		getResponsePage(lnk);
		String printTitle = "";
		((JavascriptExecutor) driver).executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		int getTabsCount = tabs.size();

		// System.out.println("Total Tabs:- "+getTabsCount);
		// 2nd Tab
		driver.switchTo().window(tabs.get(1));

		try {
			// driver.get(getHref);
			driver.navigate().to(lnk);

			long startTime = System.currentTimeMillis();

			// Using Java script
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String titlebyJavascript = (String) jse.executeScript("return document.title");
			printTitle = titlebyJavascript;
			// System.out.println("Title of webpage by Javascript
			// :-"+titlebyJavascript);

			if (titlebyJavascript == null || titlebyJavascript.isEmpty()) {
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				// printTitle = driver.getTitle();
				try {
					useSpecialWaitForTitle("//title");

				} catch (Exception useSpecialWaitForTitle) {
					// System.out.println("useSpecialWaitForTitle:-
					// "+useSpecialWaitForTitle.getMessage());

				}
				String tryingPrintTitle12 = driver.getTitle();
				printTitle = tryingPrintTitle12;
				// System.out.println("trying Print Title12:-
				// "+tryingPrintTitle12);
			}

			long endTime = System.currentTimeMillis();
			long duration = (endTime - startTime); // Total execution time in
													// milli seconds
			// System.out.println("Time taking for print the Title:-"+duration+"
			// Milliseconds");

		} catch (TimeoutException ex) {
			// System.out.println("Using Javascript-"+ex.getMessage());
			long startTimeCatch = System.currentTimeMillis();
			try {
				printTitle = driver.getTitle();
				// System.out.println("Print Title:- "+printTitle);

				if (printTitle == null || printTitle.isEmpty()) {
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					try {
						useSpecialWaitForTitle("//title");
					} catch (Exception useSpecialWaitForTitle) {
						// System.out.println("useSpecialWaitForTitle:-
						// "+useSpecialWaitForTitle.getMessage());
					}
					String tryingPrintTitle1 = driver.getTitle();
					printTitle = tryingPrintTitle1;
					// System.out.println("trying Print Title1:-
					// "+tryingPrintTitle1);
				}

			} catch (TimeoutException TOEX) {
				// System.out.println("TOEX-"+TOEX.getMessage());
			}

			long endTimeCatch = System.currentTimeMillis();
			long durationCatch = (endTimeCatch - startTimeCatch); // Total
																	// execution
																	// time in
																	// milli
																	// seconds
			// System.out.println("Time taking for print the Title in
			// Catch:-"+durationCatch+" Milliseconds");
		}

		driver.close(); // close the 2nd tab
		// 1st Tab
		driver.switchTo().window(tabs.get(0));

		// ===================== Hndl Multiple Tabs =====================

		// System.out.println("--------------------------------------------");
		return printTitle;
	}

	public static WebElement useSpecialWaitForTitle(String xpathExpression) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
		} catch (Exception ex) {
			// System.out.println("Wait Exception:- "+ex.getMessage());
		}

		WebElement eleRelxpath = driver.findElement(By.xpath(xpathExpression));
		String printInnerText = eleRelxpath.getAttribute("innerText");
		// System.out.println("printInnerText:- "+printInnerText);

		if (printInnerText.isEmpty() || printInnerText == null || printInnerText == "") {
			String TryNo4 = driver.getTitle();
			// System.out.println("Try no 4:- "+TryNo4);

			if (TryNo4.isEmpty() || TryNo4 == null || TryNo4 == "") {
				List<WebElement> getAllTitle = driver.findElements(By.xpath(xpathExpression));
				if (getAllTitle.size() > 1) {
					// System.out.println("getTitleCount:-"+getAllTitle.size());
					for (int TC = 0; TC < getAllTitle.size(); TC++) {
						String tooManyTitle = getAllTitle.get(TC).getAttribute("innerText");
						// System.out.println("Title No:- "+TC+" -
						// "+tooManyTitle);
					} // for loop
				} // list checking
			} // TryNo4 checking
		} // printInnerText checking
		return eleRelxpath;
	}
	// ------------------ 25-01-2020 ----------------

	public String getTitleFromEachLinks_old(String lnk) {

		getResponsePage(lnk);
		String printTitle = "";

		((JavascriptExecutor) driver).executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		int getTabsCount = tabs.size();

		// System.out.println("Total Tabs:- "+getTabsCount);

		driver.switchTo().window(tabs.get(1));

		try {
			// driver.get(getHref);
			driver.navigate().to(lnk);

			long startTime = System.currentTimeMillis();

			// Using Java script
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String titlebyJavascript = (String) jse.executeScript("return document.title");
			printTitle = titlebyJavascript;
			// System.out.println("Title of webpage by Javascript
			// :-"+titlebyJavascript);

			if (titlebyJavascript == null || titlebyJavascript.isEmpty()) {
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				useSpecialWaitForTitle("//title");
				printTitle = driver.getTitle();

			}

			long endTime = System.currentTimeMillis();
			long duration = (endTime - startTime); // Total execution time in
													// milli seconds
			// System.out.println("Time taking for print the Title:-"+duration+"
			// Milliseconds");

		} catch (TimeoutException ex) {
			// System.out.println("Using Javascript-"+ex.getMessage());
			long startTimeCatch = System.currentTimeMillis();
			try {
				printTitle = driver.getTitle();
				//// System.out.println("Print Title:- "+printTitle);

				if (printTitle == null || printTitle.isEmpty()) {
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					useSpecialWaitForTitle("//title");
					printTitle = driver.getTitle();

				}

			} catch (TimeoutException TOEX) {
				// System.out.println("TOEX-"+TOEX.getMessage());
			}

			long endTimeCatch = System.currentTimeMillis();
			long durationCatch = (endTimeCatch - startTimeCatch); // Total
																	// execution
																	// time in
																	// milli
																	// seconds
			// System.out.println("Time taking for print the Title in
			// Catch:-"+durationCatch+" Milliseconds");
		}

		driver.close(); // close the 2nd tab
		// 1st Tab
		driver.switchTo().window(tabs.get(0));

		// ===================== Hndl Multiple Tabs =====================
		return printTitle;

	}

	public static WebElement useSpecialWaitForTitle_old(String xpathExpression) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
		WebElement sd = driver.findElement(By.xpath(xpathExpression));
		String das = sd.getText();
		// System.out.println("das:- "+das);
		if (das.isEmpty() || das == null || das == "") {
			String TryNo4 = driver.getTitle();
			// System.out.println("Try no 4:- "+TryNo4);
		}
		return sd;
	}
	// ------------------ 25-01-2020 ----------------

	public static void getResponsePage(String linkUrl) {

		// //System.out.println("-------linkUrl-------->"+linkUrl);
		int linkCount = 0;
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.connect();

			if (httpURLConnection.getResponseCode() == 200) {
				// System.out.println("No: " + linkCount + " - " + linkUrl + " -
				// " + httpURLConnection.getResponseMessage()
				// + " - " + httpURLConnection.getResponseCode());
			} else if (httpURLConnection.getResponseCode() != 200
					|| httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {

				// System.out.println("No: " + linkCount + " - " + linkUrl + " -
				// " + httpURLConnection.getResponseCode()
				// + " - " + httpURLConnection.getResponseMessage() + " - " +
				// HttpURLConnection.HTTP_NOT_FOUND);
			}

		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
		}
	}

	/*
	 * Add this by pentation team
	 * 
	 * modify google search engine
	 */

	public void googleSearch(String searchKeyword) {
		// System.out.println("Search Keyword Google----"+searchKeyword);

		String xpthAnchorGoogle = "//div[@class='r']/a";
		String xpthNextGoogle = "//a[@id='pnnext']";
		String hitGoogleSearch = "//input[@name='q']";
		String getTitleWithOutOpenThePage = "//div[@class='r']/a/h3";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		List<WebElement> getTitle = null;
		try {
			String googleURL = "https://www.google.com/";
			// System.out.println("-------google ---------- "+googleURL);
			driver.get(googleURL);
			driver.findElement(By.xpath(hitGoogleSearch)).sendKeys(searchKeyword, Keys.RETURN);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			WebElement nextGoogle = driver.findElement(By.xpath(xpthNextGoogle));
			// System.out.println("===Google link===" +
			// nextGoogle.getAttribute("href"));
			for (int i = 1; i <= PAGES; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorGoogle));
					getTitle = driver.findElements(By.xpath(getTitleWithOutOpenThePage));
					int index = 0;
					for (WebElement we : getLink) {

						try {
							// getTitleFromEachLinks(); // later hide
							String getTitleWithOutOpenPage = getTitle.get(index++).getText();
							// System.out.println("getTitleWithOutOpenPage-Google:-"+getTitleWithOutOpenPage);

							if (getTitleWithOutOpenPage.contains("...")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("...", "");
								// System.out.println("Replace-Google:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + getTitleWithOutOpenPage + "#" + i + "#"
										+ index);

							} else if (getTitleWithOutOpenPage.contains("…")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("…", "");
								// System.out.println("Replace-Google:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + getTitleWithOutOpenPage + "#" + i + "#"
										+ index);
							} else {
								links.add(we.getAttribute("href") + "#" + getTitleWithOutOpenPage + "#" + i + "#"
										+ index);
							}

							// System.out.println("----------------------------------");
						} catch (StaleElementReferenceException stale) {
							// System.out.println("StaleElementReferenceException:-"+stale.getMessage());
						}
						// }
						//
					}

					// Selenium for checking Button is present or not first he
					// clicked then check
					nextGoogle = driver.findElement(By.xpath(xpthNextGoogle));
					if (nextGoogle.isDisplayed() || nextGoogle.isEnabled()) {
						// System.out.println("----------------"+"\n"+"founded -
						// Next Page");
						nextGoogle.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						// System.out.println("not founded");
						// System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					// System.out.println("\n"+"==== next button not available
					// ====");
					break;
				} catch (Exception e) {
					// System.out.println("error on Google search");
					e.printStackTrace();
					break;
				}
			} // end for loop
				// System.out.println("\n"+" - Google links- "+links);
		} catch (Exception e) {
			// browser exception will be caught here
			e.printStackTrace();
			System.err.println("=== Google go  search error=====");
		}
	}

	public void googleSearch_old(String searchKeyword) {
		//// System.out.println("Search Keyword Google----" + searchKeyword);

		String xpthAnchorGoogle = "//div[@class='r']/a";
		String xpthNextGoogle = "//a[@id='pnnext']";
		String hitGoogleSearch = "//input[@name='q']";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		try {
			String googleURL = "https://www.google.com/";
			//// System.out.println("-------google ---------- " + googleURL);
			driver.get(googleURL);
			driver.findElement(By.xpath(hitGoogleSearch)).sendKeys(searchKeyword, Keys.RETURN);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath(xpthAnchorGoogle));

			WebElement nextGoogle = driver.findElement(By.xpath(xpthNextGoogle));
			// System.out.println("===Google link===" +
			// nextGoogle.getAttribute("href"));
			for (int i = 1; i <= PAGES; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorGoogle));

					for (WebElement we : getLink) {
						links.add(we.getAttribute("href"));
						/*
						 * //getHref = we.getAttribute("href"); // for confirm
						 * ////System.out.println("printurl - try:- "+getHref);
						 * 
						 * //getTitleFromEachLinks(); // later hide }
						 */

						// Selenium for checking Button is present or not first
						// he
						// clicked then check
					} // end loop here

					nextGoogle = driver.findElement(By.xpath(xpthNextGoogle));
					if (nextGoogle.isDisplayed() || nextGoogle.isEnabled()) {
						// System.out.println("----------------" + "\n" +
						// "founded - Next Page");
						nextGoogle.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						// System.out.println("not founded");
						// System.out.println("else break");
						break;
					}

				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					// System.out.println("\n" + "==== next button not available
					// ====");
					break;
				} catch (Exception e) {
					// System.out.println("error on Google search");
					e.printStackTrace();
					break;
				}
			} // end for loop
				// System.out.println("\n" + " - Google links- " + links);
		} catch (Exception e) {
			// browser exception will be caught here
			e.printStackTrace();
			System.err.println("=== Google go  search error=====");
		}
	}

	public void bingSearch(String searchKeyword) {

		String xpthAnchorBing = "//li[@class='b_algo']/h2/a";
		String xpthNextBing = "//a[@title='Next page']";
		String hitBingSearch = "";
		String getTitleWithOutOpenThePage = "//li[@class='b_algo']/h2";
		String getTitleWithOutOpenThePage2 = "//h2";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		List<WebElement> getTitle = null;
		// searchKeyword = dd;
		try {
			String bingURL = "http://www.bing.com/search?q=" + searchKeyword;
			driver.get(bingURL);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath("//li[@class='b_algo']/h2/a"));
			/*
			 * for (WebElement we : getLink) { // for confirm
			 * //System.out.println("printurl :- "+we.getAttribute("href"));
			 * 
			 * links.add(we.getAttribute("href"));
			 * 
			 * // getTitleFromEachLinks(); // later hide }
			 */
			// WebElement next = driver.findElement(By.xpath("//a[@title='Next
			// page']"));
			WebElement next = driver.findElement(By.xpath(xpthNextBing));
			// System.out.println("===bing link===" +
			// next.getAttribute("href"));
			for (int i = 1; i <= PAGES; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					getLink = driver.findElements(By.xpath(xpthAnchorBing));
					getTitle = driver.findElements(By.xpath(getTitleWithOutOpenThePage));
					int index = 0;
					for (WebElement we : getLink) {
						try {

							String getTitleWithOutOpenPage = getTitle.get(index++).getText();
							// System.out.println("getTitleWithOutOpenPage-Bing:-"
							// +getTitleWithOutOpenPage );

							if (getTitleWithOutOpenPage.contains("...")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("...", "");
								// System.out.println("Replace-Bing:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);

							} else if (getTitleWithOutOpenPage.contains("…")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("…", "");
								// System.out.println("Replace-Bing:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);
							} else {
								links.add(we.getAttribute("href") + "#" + getTitleWithOutOpenPage + "#" + i + "#"
										+ index);
							}

							// System.out.println("----------------------------------");
						} catch (StaleElementReferenceException stale) {
							// System.out.println("StaleElementReferenceException:-"+stale.getMessage());
						}
					}

					// next = driver.findElement(By.xpath("//a[@title='Next
					// page']"));
					next = driver.findElement(By.xpath(xpthNextBing));
					if (next.isDisplayed() || next.isEnabled()) {
						// System.out.println("---------------"+"\n"+"founded -
						// Next Page");
						next.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						// System.out.println("not founded");
						// System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// TODO: handle exception
					e.printStackTrace();
					// System.out.println("==== next button not available====");
					break;
				} catch (Exception e) {
					// System.out.println("error on bing search");
					e.printStackTrace();
					break;
				}
			}
			// System.out.println(links);
		} catch (Exception e) {
			// browser exception will be caught here

			e.printStackTrace();
			System.err.println("=== bing search error=====");
		}
	}

	public void russiaGoSearch(String searchKeyword) {
		// System.out.println("Search Keyword russia go----"+searchKeyword);

		String xpthAnchorRussia = "//h3[@class='result__title']//a[1]";
		String xpthNextRussia = "//a[contains(@data-pxt,'r_one')]";
		String getTitleWithOutOpenThePage = "//h3[@class='result__title']";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		List<WebElement> getTitle = null;

		try {
			String russiaURL = "https://go.mail.ru/";
			String russiaURLWithKeyword = "https://go.mail.ru/?q=" + searchKeyword;
			// System.out.println("-------russiaGoURL ---------- "+russiaURL);
			driver.get(russiaURL);
			driver.findElement(By.xpath("//input[@id='q']")).sendKeys(searchKeyword, Keys.RETURN);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath(xpthAnchorRussia));

			WebElement nextrussia = driver.findElement(By.xpath(xpthNextRussia));
			// System.out.println("===russiaGo link===" +
			// nextrussia.getAttribute("href"));
			for (int i = 1; i <= PAGES; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorRussia));
					getTitle = driver.findElements(By.xpath(getTitleWithOutOpenThePage));
					int index = 0;
					for (WebElement we : getLink) {
						links.add(we.getAttribute("href"));

						try {
							// getTitleFromEachLinks(); // later hide
							String getTitleWithOutOpenPage = getTitle.get(index++).getText();
							// System.out.println("getTitleWithOutOpenPage-Russia:-"
							// +getTitleWithOutOpenPage );

							if (getTitleWithOutOpenPage.contains("...")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("...", "");
								// System.out.println("Replace-Russia:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);

							} else if (getTitleWithOutOpenPage.contains("…")) {
								String replacegetTitle = getTitleWithOutOpenPage.replace("…", "");
								// System.out.println("Replace-Russia:-"
								// +replacegetTitle );
								links.add(we.getAttribute("href") + "#" + replacegetTitle + "#" + i + "#" + index);
							} else {
								links.add(we.getAttribute("href") + "#" + getTitleWithOutOpenPage + "#" + i + "#"
										+ index);
							}

							// System.out.println("----------------------------------");
						} catch (StaleElementReferenceException stale) {
							// System.out.println("StaleElementReferenceException:-"+stale.getMessage());
						}
					}

					// TODO Selenium for checking Button is present or not first
					// he clicked then check
					nextrussia = driver.findElement(By.xpath(xpthNextRussia));
					if (nextrussia.isDisplayed() || nextrussia.isEnabled()) {
						// System.out.println("---------------\n"+"founded -
						// Next Page");
						nextrussia.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						// System.out.println("not founded");
						// System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					// System.out.println("\n"+"==== next button not
					// available====");
					break;
				} catch (Exception e) {
					// System.out.println("error on russiaGo search");
					e.printStackTrace();
					break;
				}
			}
			// System.out.println(links);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("=== russiaGo  search error=====");
		}
	}
	// ------------------------------------------------------------------------------------------------------
	/*
	 * public boolean isAppActive() throws Exception { File file = new
	 * File(System.getProperty("user.home"), "FireZeMissiles1111" + ".tmp");
	 * channel = new RandomAccessFile(file, "rw").getChannel();
	 * 
	 * lock = channel.tryLock(); if (lock == null) { return true; }
	 * Runtime.getRuntime().addShutdownHook(new Thread() { public void run() {
	 * try { lock.release(); channel.close(); } catch (Exception e) {
	 * e.printStackTrace(); } } }); return false; }
	 */

	FileLock lock;
	FileChannel channel;
	String myIP = null;
	static WebDriver driver = null;
	int id, userId, projectId, bls, pipe;
	String keyphrase, machine, property_name, pipe_domain;
	String skeyword = null;
	Set<String> keywordfilter = null;
	Set<String> blkListSites = null;
	List<String> links = null;
	String searchEngine = null;
	String urlMetaData = null;
	List<WebElement> getLink = null;
	boolean b1 = false;
	boolean b2 = false;
	Document doc = null;

	@Override
	public void setServletContext(ServletContext servletcontext) {
		// TODO Auto-generated method stub
		this.servletcontext = servletcontext;

	}
}