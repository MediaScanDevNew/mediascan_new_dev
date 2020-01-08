package com.pradeep.pj;

import java.net.HttpURLConnection;
import java.net.Inet4Address;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.pj.repo.Master_crawle_urlRepository;
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

import org.openqa.selenium.JavascriptExecutor;

@RestController
public class BotSeApplication1 {
	BotSeApplication1() {
		System.out.println("--------------------------Constuctor of BotSeApplication1----------");
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
	private PageSource ps;
	@Autowired
	private Markscan_usersService mus;
	@Autowired
	private BotRunningStatusService brss;
	@Autowired
	private Client_MasterService cms;

	List<Object[]> data;
	List<String> data1;
	List<Object[]> udata;
	List<Object[]> pdata;
	List<Object[]> cdata;
	List<String> udata1;
	List<String> kquery;
	int count;
	int property;
	List<Integer> usrList = null;
	Set<String> uemail;
	Set<String> cemail;
	String htmlpage = "";
	boolean sendMail = false;
	int count1 = 0;

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
	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	// @RequestMapping("/check")
	@ResponseBody
	 public String botStart(@RequestHeader String Authorization) throws Exception {
	//public String botStart() throws Exception {

		 String s=Authorization;
		
		 if(!Authorization.equals(null)) {
			 System.out.println("Testing-------------------");
			 myIP = myIPaddress();
			 System.out.println("========== ip address-------" + myIP);
		try {
			// System.setProperty("webdriver.gecko.driver",
			// "/home/hduser/Videos/gckodriver/geckodriver");

			System.setProperty("webdriver.chrome.driver", "/home/hduser/Videos/Driver/chromedriver");

			// System.setProperty("webdriver.gecko.driver",
			// "D:\\geckodriver.exe");
			// System.setProperty("webdriver.firefox.marionette",
			// "D:\\geckodriver.exe");
			// System.setProperty("webdriver.firefox.marionette",
			// "D://harry/gecko_driver/geckodriver.exe");
			// driver = new FirefoxDriver();

			driver = new ChromeDriver();
			serviceDetail();
			sendMail = false;
			System.out.println("========== sendMail-------" + sendMail);
		} catch (Exception e) {
			System.out.println("================Parent Exception==========" + e);
			serviceDetail();

		}
	 }

		return "success";

	}

	public void botStart1() throws Exception {
		System.out.println("Testing-------------------");

		// TODO Auto-generated method stub

		BotSeApplication1 ba = new BotSeApplication1();
		myIPaddress();
		System.out.println("========== ip address-------" + myIP);
		System.setProperty("webdriver.firefox.marionette", "/opt/geckodriver");
		driver = new FirefoxDriver();
		serviceDetail();
		sendMail = false;
		System.out.println("========== sendMail-------" + sendMail);

	}
	// }

	public void serviceDetail() {
		try {
			usrList = new ArrayList<Integer>();
			synchronized (this) {
				data = sps.findALLCustom(); // get_data_from_stored_project
				if ((data.size() < 1) && (sendMail == false)) {
					// System.out.println("no any query for u .... '");
					try {

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

						System.out.println("no any query for u .... '");
						// System.exit(0);

					} catch (Exception e) {
						// System.exit(0);
					}

				} else {
					for (Object[] pj : data) {
						id = ((Integer) pj[0]);
						sps.allComplate(id);
						keyphrase = ((String) pj[1]);
						pipe = ((Integer) pj[2]);
						userId = ((Integer) pj[3]);
						projectId = ((Integer) pj[4]);

					}
				}

				keywordfilter = new HashSet<>();
				property_name = pis.findALLCustom(projectId);
				String pj11[] = property_name.trim().split(" ");

				for (String a : pj11) {
					keywordfilter.add(a.trim());
				}

				pipe_domain = mps.findALLCustom(pipe);
				data1 = kfs.findALLCustom(projectId);

				for (Object[] pj : data) {
					keywordfilter.add(pj[0].toString().trim());
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

					// google search==
					googleSearch(keyphrase);
					System.out.println("=====google=== links size......." + links.size());
					afterCrawl(1, keyphrase);
					links = null;

					sps.yahooStartGoogleComplate(id);

					// yahoo search==

					yahooSearch(keyphrase);
					System.out.println("=====yahoo=== links size......." + links.size());
					afterCrawl(2, keyphrase);
					links = null;

					sps.bingStartYahooComplate(id);

					// Bing search==

					bingSearch(keyphrase);
					System.out.println("=====bing=== links size......." + links.size());
					afterCrawl(3, keyphrase);
					links = null;

					// duck duck Go search======

					// ----------------- Pentation -----------------------------
					// sps.bingComplate(id);
					sps.duckduckStartBingComplete(id);
					duckduckSearch(keyphrase);
					System.out.println("===== duckduck go === links size......." + links.size());
					afterCrawl(4, keyphrase);
					links = null;
					// ------------------------------------------------------------

					// ----------------- Pentation
					// ----------------------------------
					System.out.println("id value is --------->" + id);
					sps.russiaGoStartduckduckGoComplete(id);
					russiaGoSearch(keyphrase);
					System.out.println("===== duckduck go === links size......." + links.size());
					afterCrawl(5, keyphrase);
					links = null;

					// ---------------------------------------------------------------
				} else if (pipe == 1) {
					sps.googleStart(id);

					// google search==
					googleSearch(keyphrase);
					System.out.println("=====google=== links size......." + links.size());
					afterCrawl(1, keyphrase);
					links = null;
				} else if (pipe == 2) {
					sps.yahooStartGoogleComplate(id);

					// yahoo search==

					yahooSearch(keyphrase);
					System.out.println("=====yahoo=== links size......." + links.size());
					afterCrawl(2, keyphrase);
					links = null;
				} else if (pipe == 3) {
					sps.bingStartYahooComplate(id);

					// Bing search==

					bingSearch(keyphrase);
					System.out.println("=====bing=== links size......." + links.size());
					afterCrawl(3, keyphrase);
					links = null;

					// sps.bingComplate(id);
					// ----------------- Pentation -----------------------------
				} else if (pipe == 4) {

					sps.duckduckStartBingComplete(id);
					duckduckSearch(keyphrase);
					System.out.println("===== duckduck go === links size......." + links.size());
					afterCrawl(4, keyphrase);
					links = null;

				} else if (pipe == 5) {

					sps.russiaGoStartduckduckGoComplete(id);
					russiaGoSearch(keyphrase);
					System.out.println("===== duckduck go === links size......." + links.size());
					afterCrawl(5, keyphrase);
					links = null;
					// ----------------- Pentation -----------------------------
				} else if (pipe > 5) {

					sps.googleOversiesStart(id);
					// dao.customUpdateProject("update Stored_project_setup set
					// google=2 where id=" + id);
					googleOversies(keyphrase, pipe_domain);
					System.out.println("=====google geo=== links size......." + links.size());
					afterCrawl(pipe, keyphrase);
					// afterCrawlOversies(pipe);
					links = null;
					sps.googleOversiesComplate(id);
				}

				try {
					sps.allComplate(id);
					WhitelistValidation wv = new WhitelistValidation();
					wv.whitelistChecking(projectId);
					wv.blacklistChecking(projectId);
					wv.greylistChecking(projectId);
					// mms.machineStatusFree(myIP);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// System.out.println("crawl is complete");

				// JOptionPane.showMessageDialog(null, "crawl is complete");

				// ****************** send mail to user
			}
			// }
			System.out.println("*******************************User Name**********" + userId);
			sendMailToUser(userId, projectId);
			if (sendMail == false) {
				serviceDetail();
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			try {
				driver.close();
				driver.quit();
			} catch (Exception ee) {
			}
			// JOptionPane.showMessageDialog(null, "crawl error");
			System.out.println("**********************crawl error*********");

			// factory = null;
			// HbmTemplateUtils.killMYSQLConnection();
			// System.exit(0);
		}

	}

	/*
	 * public void googleSearch(String searchKeyword) { links = new
	 * ArrayList<>(); // searchKeyword = dd; searchEngine =
	 * "https://www.google.co.in"; try {
	 * System.out.println("Start Crawle Url...................");
	 * driver.get("https://www.google.co.in/search?q=" + searchKeyword);
	 * Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
	 * getLink = driver.findElements(By.xpath("//div[@class='r']/a")); for
	 * (WebElement we : getLink) { links.add(we.getAttribute("href"));
	 * System.out.println("Crawle Url..................."+we.getAttribute("href"
	 * )); } WebElement next =
	 * driver.findElement(By.xpath("//a[@id='pnnext']"));
	 * System.out.println(next.getAttribute("href")); for (int i = 1; i <= 30;
	 * i++) { try { Thread.sleep(random_number(8000, 4000), random_number(1000,
	 * 100)); // driver.get(next.getAttribute("href")); getLink =
	 * driver.findElements(By.xpath("//div[@class='r']/a")); for (WebElement we
	 * : getLink) { links.add(we.getAttribute("href"));
	 * 
	 * }
	 * 
	 * next = driver.findElement(By.xpath("//a[@id='pnnext']")); if
	 * (next.isDisplayed() || next.isEnabled()) { next.click(); } else {
	 * System.out.println("else break"); break; }
	 * //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); }
	 * catch (Exception e) {
	 * System.out.println("This is the Last Page, Next button not available");
	 * // e.printStackTrace(); break; } }
	 * 
	 * } catch (NoSuchElementException e) {
	 * System.out.println("Element Not Found"+e.getMessage()); }
	 * 
	 * catch (Exception e) {
	 * System.out.println("Common Exception"+e.getMessage()); } }
	 */

	public void yahooSearch(String searchKeyword) {
		links = new ArrayList<>();
		// searchKeyword = dd;
		try {
			driver.get("https://in.search.yahoo.com/search?p=" + searchKeyword + "&fr=yfp-t-101");
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath("//div[@id='web']/ol/li/div/div[1]/h3/a"));
			for (WebElement we : getLink) {
				// links.add(we.getAttribute("href"));
				try {
					String xx[] = we.getAttribute("href").toString().split("/RU=");
					xx[1] = xx[1].replaceAll("%3a", ":").replaceAll("%2f", "/");
					String xx1[] = xx[1].split("/RK=0/");
					links.add(xx1[0]);
				} catch (Exception e) {
					// e.printStackTrace();
					links.add(we.getAttribute("href").toString());
				}
			}
			WebElement next = driver.findElement(By.xpath("//a[@class='next']"));
			System.out.println(next.getAttribute("href"));
			for (int i = 1; i <= 30; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath("//div[@id='web']/ol/li/div/div[1]/h3/a"));
					for (WebElement we : getLink) {
						System.out.println(we.getAttribute("href"));
						try {
							String xx[] = we.getAttribute("href").toString().split("/RU=");
							xx[1] = xx[1].replaceAll("%3a", ":").replaceAll("%2f", "/");
							String xx1[] = xx[1].split("/RK=0/");

							links.add(xx1[0]);
						} catch (Exception e) {
							// e.printStackTrace();
							links.add(we.getAttribute("href"));
						}
						// links.add(we.getAttribute("href"));
					}

					next = driver.findElement(By.xpath("//a[@class='next']"));
					if (next.isDisplayed() || next.isEnabled()) {
						System.out.println("founded");
						next.click();
					} else {
						System.out.println("else break");
						break;
					}
				} catch (Exception e) {
					System.out.println("error on yahoo search");
					e.printStackTrace();

					break;

				}
			}

		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("==== next button not available====");
		}

		catch (Exception e) {
			// browser exception will be caught here

			// e.printStackTrace();
			System.err.println("=== yahoo search error=====");
		}
	}

	public void bingSearch(String searchKeyword) {
		links = new ArrayList<>();
		// searchKeyword = dd;
		try {
			driver.get("http://www.bing.com/search?q=" + searchKeyword);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath("//li[@class='b_algo']/h2/a"));

			/*
			 * for (WebElement we : getLink) {
			 * links.add(we.getAttribute("href")); }
			 */
			WebElement next = driver.findElement(By.xpath("//a[@title='Next page']"));
			System.out.println("===bing link===" + next.getAttribute("href"));
			for (int i = 1; i <= 30; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath("//li[@class='b_algo']/h2/a"));
					for (WebElement we : getLink) {
						links.add(we.getAttribute("href"));
					}

					next = driver.findElement(By.xpath("//a[@title='Next page']"));
					if (next.isDisplayed() || next.isEnabled()) {
						System.out.println("founded");
						next.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						System.out.println("not founded");
						System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("==== next button not available====");
					break;
				} catch (Exception e) {
					System.out.println("error on bing search");
					e.printStackTrace();

					break;

				}

				// for (String we : links) {
				// System.out.println(we);
				// }
				// driver.quit();

			}
		} catch (Exception e) {
			// browser exception will be caught here

			e.printStackTrace();
			System.err.println("=== bing search error=====");
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

			for (int i = 1; i <= 30; i++) {

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
						System.out.println("founded");
						next.click();
					} else {
						System.out.println("else break");
						break;
					}
				} catch (Exception e) {
					System.out.println("error on oversies search");
					e.printStackTrace();

					break;

				}
			}

		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("==== next button not available====");
		}

		catch (Exception e) {
			System.out.println("=== google oversies error = = = =");
			e.printStackTrace();
			// driver.quit();
			// System.exit(0);
		}
	}

	public void afterCrawl(int ppipe, String searchKeyword) {
		// PageSource ps = new PageSource();
		String pagesource = null;
		List<String> searchkeywordlist = new ArrayList<>();
		System.out.println("=== links size=== " + links.size());
		if (links.size() > 0) {
			int i = 1;
			for (String lnk : links) {
				b2 = false;
				System.out.println("==========lnk===" + lnk);
				try {
					System.out
							.println("=1111111   == " + lnk.toLowerCase().contains(property_name.trim().toLowerCase()));

					if (lnk.toLowerCase().contains(property_name.trim().toLowerCase())) {
						String title = getTitleFromEachLinks(lnk);
						mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine, findDomain(lnk.trim()), title);
						System.out.println("=== running.....first. running..............");
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
							System.out.println("=== srckey__ .............." + srckey__);
							if (lnk.toLowerCase().contains(srckey__.trim().toLowerCase())) {
								String title = getTitleFromEachLinks(lnk);
								mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine, findDomain(lnk.trim()),
										title);

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

						doc = Jsoup.connect(lnk.trim()).get();
						pagesource = doc.body().text();

					} catch (Exception e) {
						// TODO: handle exception
						if (lnk.contains("youtube.com") || lnk.contains("facebook.com")) {

						} else
							try {
								pagesource = ps.getPageSource(lnk.trim());
							} catch (Exception e2) {

								// TODO: handle exception
							}
					}

					try {

						if (pagesource.toLowerCase().contains(property_name.trim().toLowerCase())) {
							System.out.println("--- first fail ===Testing4============'");
							String title = getTitleFromEachLinks(lnk);
							mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine, findDomain(lnk.trim()),
									title);
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
							System.out.println("--- pagesource value ----------->" + pagesource);
							System.out.println("--- pj value----------->" + pj);
							if (pagesource.toLowerCase().contains(pj.trim().toLowerCase())) {
								String title = getTitleFromEachLinks(lnk);
								mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine, findDomain(lnk.trim()),
										title);
								// break;
							}
						} catch (Exception e) {
							// e.printStackTrace();
						}

					}

				} // end if condition

			}
		}
	}

	public String findDomain(String ddomain) {
		String domain__c = null;
		try {

			// System.out.println("=pp=" + ddomain.lastIndexOf("/"));

			if (ddomain.startsWith("https:")) {
				domain__c = ddomain.replace("https://", "");
				domain__c = domain__c.replace("www.", "");
				// System.out.println("domain===1..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.1.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.112.." + domain__c);
				}

			} else if (ddomain.startsWith("http:")) {
				domain__c = ddomain.replace("http://", "");
				domain__c = domain__c.replace("www.", "");
				// System.out.println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.2.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("//www.")) {
				domain__c = ddomain.replace("//www.", "");
				domain__c = domain__c.replace("www.", "");
				// System.out.println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.3.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("www")) {
				// domain__c = ddomain.replace("//www.", "");
				domain__c = ddomain.replace("www.", "");
				// System.out.println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.33.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					// System.out.println("domain===4.114.." + domain__c);
				}
			}

			else if (!ddomain.startsWith("http:") || !ddomain.startsWith("htts:") || !ddomain.startsWith("www.")) {
				String pj[] = ddomain.split("/");
				domain__c = pj[0];
				// System.out.println("domain===4.114.." + domain__c);
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
			System.out.println("Current IP address : " + myIP);
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

	// @Value("${spring.mail.host}")
	String mailip;

	public void sendMailToUser(int userId, int projectId) {

		uemail = new HashSet<String>();
		cemail = new HashSet<String>();
		// Integer id =userId;
		// String pname =pis.findALLCustom(projectId);
		udata = mus.findUserDetails(userId);
		// String email="";
		String uName = "";
		String client_name = "";
		// String htmlpage="";

		for (Object[] usr : udata) {
			uemail.add((String) usr[0]);
			uName = ((String) usr[1]);
		}
		String pName = "";
		int ctype = 0;
		pdata = pis.findCustomData(projectId);
		for (Object[] prj : pdata) {
			pName = (String) prj[0];
			ctype = Integer.parseInt((String) prj[1]);

			System.out.println("**********************Client Type*********" + ctype);

		}
		System.out.println("**********************Client Type1*********" + ctype);
		cdata = cms.findCustomData(ctype);
		for (Object[] cmd : cdata) {
			client_name = (String) cmd[0];
			cemail.add((String) cmd[1]);
			// ctype=(Integer)cmd[1];
			System.out.println("**********************Client Email*********" + cemail);

		}

		// brss.botStatusStoredData(uName, email, pname, keyphrase, machine);
		htmlpage = htmlpage + "<tr><td>" + keyphrase + "</td><td>" + pName + "</td><td>" + client_name + "</td><td>"
				+ uName + " </td></tr>";

		/*
		 * htmlpage="<p>Dear "
		 * +uName+",</p><p> Bot Search Application has been completed query: "
		 * +keyphrase+" Now You can extract data"
		 * +"from mediascan application.</p><p> thanks &amp; regards:<br> Harendra Preatap Singh</p>"
		 * ;
		 * 
		 * System.out.println("**********************Email*********"+email);
		 * System.out.println("**********************UserName******"+uName);
		 * Properties props = new Properties(); props.put("mail.smtp.auth",
		 * "true"); props.put("mail.smtp.starttls.enable", "true");
		 * props.put("mail.smtp.host", mailip); props.put("mail.smtp.port",
		 * "25"); Session session = Session.getInstance(props, new
		 * javax.mail.Authenticator() { protected PasswordAuthentication
		 * getPasswordAuthentication() { return new
		 * PasswordAuthentication("hpsingh@markscan.co.in", "M@123rkscan"); }
		 * }); try {
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
		 * System.out.println("Sent message successfully...."); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */

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
		 * //System.out.println("*********ID********"+ hp[0]); String userName
		 * =((String)hp[1]); System.out.println("*********User Name********"+
		 * userName); String userEmail =((String)hp[2]); email.add(userEmail);
		 * System.out.println("*********User Email********"+ userEmail); String
		 * pName =((String)hp[3]);
		 * System.out.println("*********Project Name********"+ pName); String
		 * keywords =((String)hp[4]);
		 * System.out.println("*********Keyword********"+ keywords); htmlpage
		 * ="<tr><td>"+keywords+"</td><td>"+pName+"</td><td>"+
		 * userName+" </td></tr>";
		 * 
		 * }
		 */
		sendMail = true;
		System.out.println("*****************htmlpage**********" + htmlpage);
		htmlpage1 = "<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"
				+ "<html><head>" + "<style>table {border-collapse: collapse;} table, td, th "
				+ "{border: 1px solid black;}th{background-color: lightgray;}</style></head><body>"
				+ "<p>Hi all,</p><p> Bot Search Application has been completed query: <br> <table><tr><th>Keywords</th><th>Project Name</th><th>Client Name</th><th>Created By</th></tr> "
				+ htmlpage + "</table><br> Now You can extract data"
				+ "from mediascan application.</p><p> thanks &amp; regards:<br>MediaScan </p></body></html>";
		System.out.println("********************htmlpage1*********" + htmlpage1);
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
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("hpsingh@markscan.co.in"));
			message.setSubject("Bot Application Running Status ");

			message.setContent(htmlpage1, "text/html");

			// Send message

			Transport.send(message);
			htmlpage = null;
			htmlpage1 = null;
			// brss.mailSendUpdate();
			// message=null;
			System.out.println("Sent message successfully....");
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

	// --------------------------- duck duck Go Search page pentation
	// -------------------------------------------------------------

	public void duckduckSearch(String searchKeyword) {
		links = new ArrayList<>();
		// searchKeyword = dd;
		String xpthAnchorDuck = "//h2[@class='result__title']//a[1]";
		String xpthNextDuck = "//a[text()='More results' and @class='result--more__btn btn btn--full']";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		try {
			String duckURL = "https://duckduckgo.com/?q=" + searchKeyword;
			System.out.println("-------duckURL ---------- " + duckURL);
			driver.get(duckURL);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath(xpthAnchorDuck));
			
			// getLink = driver.findElements(By.xpath("//li[@class]/h2/a"));
			/*
			 * for (WebElement we : getLink) { // for confirm
			 * System.out.println("printurl :- "+we.getAttribute("href"));
			 * 
			 * links.add(we.getAttribute("href")); }
			 */
			
			WebElement nextDuck = driver.findElement(By.xpath(xpthNextDuck));
			System.out.println("===duckduckgo link===" + nextDuck.getAttribute("href"));
			for (int i = 1; i <= 30; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorDuck));
					for (WebElement we : getLink) {
						links.add(we.getAttribute("href"));

						// for confirm
						System.out.println("printurl - try:- " + we.getAttribute("href"));
					}
					links.stream().distinct().collect(Collectors.toList());
					// TODO Selenium for checking Button is present or not first
					// he clicked then check
					nextDuck = driver.findElement(By.xpath(xpthNextDuck));
					if (nextDuck.isDisplayed() || nextDuck.isEnabled()) {
						System.out.println("----------------" + "\n" + "founded");
						nextDuck.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						System.out.println("not founded");
						System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					System.out.println("\n" + "==== next button not available====");
					break;
				} catch (Exception e) {
					System.out.println("error on DuckDuck search");
					e.printStackTrace();
					break;
				}
			}
			System.out.println("\n" + " - Duck links-  " + links);
		} catch (Exception e) {
			// browser exception will be caught here
			e.printStackTrace();
			System.err.println("=== DuckDuck go  search error=====");
		}
	}

	// --------------------------------------------------------------------------------------------------------------------

	// --------------------------- russiaGo Search page
	// Pentation-----------------------------------------------------------------

	public void russiaGoSearch(String searchKeyword) {

		String xpthAnchorRussia = "//h3[@class='result__title']//a[1]";
		String xpthNextRussia = "//a[contains(@data-pxt,'r_one')]";

		links = new ArrayList<>();
		List<WebElement> getLink = null;

		try {
			String russiaURL = "https://go.mail.ru/";
			String russiaURLWithKeyword = "https://go.mail.ru/?q=" + searchKeyword;
			System.out.println("-------russiaGoURL ---------- " + russiaURL);
			driver.get(russiaURL);
			driver.findElement(By.xpath("//input[@id='q']")).sendKeys(searchKeyword, Keys.RETURN);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath(xpthAnchorRussia));
			// getLink = driver.findElements(By.xpath("//li[@class]/h2/a"));
			/*
			 * for (WebElement we : getLink) { // for confirm
			 * System.out.println("printurl:- "+we.getAttribute("href"));
			 * 
			 * links.add(we.getAttribute("href")); }
			 */
			WebElement nextrussia = driver.findElement(By.xpath(xpthNextRussia));
			System.out.println("===duckduckgo link===" + nextrussia.getAttribute("href"));
			for (int i = 1; i <= 30; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorRussia));
					for (WebElement we : getLink) {
						links.add(we.getAttribute("href"));

						// for confirm
						System.out.println("printurl - try:- " + we.getAttribute("href"));
					}

					// TODO Selenium for checking Button is present or not first
					// he clicked then check
					nextrussia = driver.findElement(By.xpath(xpthNextRussia));
					if (nextrussia.isDisplayed() || nextrussia.isEnabled()) {
						System.out.println("---------------\n" + "founded");
						nextrussia.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						System.out.println("not founded");
						System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					System.out.println("\n" + "==== next button not available====");
					break;
				} catch (Exception e) {
					System.out.println("error on russiaGo search");
					e.printStackTrace();
					break;
				}
			}

			System.out.println(links);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("=== russiaGo  search error=====");
		}
	}

	// ---------------------------------- get Title Pentation
	// ---------------------------------------------------------

	public String getTitleFromEachLinks(String lnk) {

		getResponsePage(lnk);
		String printTitle = "";
		// ===================== Hndl Multiple Tabs =====================

		((JavascriptExecutor) driver).executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		int getTabsCount = tabs.size();
		System.out.println("Total Tabs:- " + getTabsCount);
		driver.switchTo().window(tabs.get(1));

		try {
			driver.get(lnk);
			// Using Java script
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String titlebyJavascript = (String) jse.executeScript("return document.title");
			//System.out.println("Title of webpage by Javascript :-" + titlebyJavascript);

			if (titlebyJavascript == null || titlebyJavascript.isEmpty()) {
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//System.out.println("trying Print Title1:- " + driver.getTitle());
			}

		} catch (TimeoutException ex) {
			//System.out.println("Using Javascript-" + ex.getMessage());
			try {
				printTitle = driver.getTitle();
				//System.out.println("Print Title:- " + printTitle);

				if (printTitle == null || printTitle.isEmpty()) {
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					//System.out.println("trying Print Title1:- " + driver.getTitle());
				}

			} catch (TimeoutException TOEX) {
				//System.out.println("TOEX-" + TOEX.getMessage());
			}
		}
		driver.close(); // close the 2nd tab
		// 1st Tab
		driver.switchTo().window(tabs.get(0));
		return printTitle;
	}

	public static void getResponsePage(String linkUrl) {

		// System.out.println("-------linkUrl-------->"+linkUrl);
		int linkCount = 0;
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.connect();

			if (httpURLConnection.getResponseCode() == 200) {
				System.out.println("No: " + linkCount + " - " + linkUrl + " - " + httpURLConnection.getResponseMessage()
						+ " - " + httpURLConnection.getResponseCode());
			} else if (httpURLConnection.getResponseCode() != 200
					|| httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				// System.out.println("No: "+i + " - "+linkUrl+" -
				// "+httpURLConnect.getResponseMessage() + " - "+
				// HttpURLConnection.HTTP_NOT_FOUND);
				System.out.println("No: " + linkCount + " - " + linkUrl + " - " + httpURLConnection.getResponseCode()
						+ " - " + httpURLConnection.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	// ------------------------------------------------------------------------------------------------------

	// -------------------------------- google search modify
	// ------------------------------------------------

	public void googleSearch(String searchKeyword) {
		System.out.println("Search Keyword Google----" + searchKeyword);

		String xpthAnchorGoogle = "//div[@class='r']/a";
		String xpthNextGoogle = "//a[@id='pnnext']";
		String hitGoogleSearch = "//input[@name='q']";

		links = new ArrayList<>();
		List<WebElement> getLink = null;
		try {
			String googleURL = "https://www.google.com/";
			System.out.println("-------google ---------- " + googleURL);
			driver.get(googleURL);
			driver.findElement(By.xpath(hitGoogleSearch)).sendKeys("dabang 2 watch online", Keys.RETURN);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath(xpthAnchorGoogle));

			WebElement nextGoogle = driver.findElement(By.xpath(xpthNextGoogle));
			System.out.println("===Google link===" + nextGoogle.getAttribute("href"));
			for (int i = 1; i <= 30; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath(xpthAnchorGoogle));
					/*
					 * for (WebElement we : getLink) {
					 * links.add(we.getAttribute("href"));
					 * 
					 * //getHref = we.getAttribute("href"); // for confirm
					 * //System.out.println("printurl - try:- "+getHref);
					 * 
					 * //getTitleFromEachLinks(); // later hide }
					 */

					// Selenium for checking Button is present or not first he
					// clicked then check
					nextGoogle = driver.findElement(By.xpath(xpthNextGoogle));
					if (nextGoogle.isDisplayed() || nextGoogle.isEnabled()) {
						System.out.println("----------------" + "\n" + "founded - Next Page");
						nextGoogle.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						System.out.println("not founded");
						System.out.println("else break");
						break;
					}
				} catch (NoSuchElementException e) {
					// e.printStackTrace();
					System.out.println("\n" + "==== next button not available ====");
					break;
				} catch (Exception e) {
					System.out.println("error on Google search");
					e.printStackTrace();
					break;
				}
			} // end for loop
			System.out.println("\n" + " - Google links-  " + links);
		} catch (Exception e) {
			// browser exception will be caught here
			e.printStackTrace();
			System.err.println("=== Google go  search error=====");
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
}