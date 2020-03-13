package com.pradeep.pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;





/*
import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.pradeep.pj.repo.Master_crawle_urlRepository;
import com.pradeep.pj.service.Blacklist_sitesService;
import com.pradeep.pj.service.BotRunningStatusService;
import com.pradeep.pj.service.Keyword_filterService;
import com.pradeep.pj.service.Markscan_machineService;
import com.pradeep.pj.service.Markscan_pipeService;
import com.pradeep.pj.service.Markscan_usersService;
import com.pradeep.pj.service.Master_crawle_urlService;
import com.pradeep.pj.service.Project_infoService;
import com.pradeep.pj.service.Stored_project_setupService;
*/
@SpringBootApplication
public class BotSeApplication  extends SpringBootServletInitializer  {
	
	public static void main(String[] args) {
		SpringApplication.run(BotSeApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BotSeApplication.class);
	}

	/*
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

	public static void main(String[] args) {
		// SpringApplication.run(BotSeApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(BotSeApplication.class);
		builder.headless(false);
	//	System.setProperty("java.awt.headless", "true");
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		BotSeApplication ba = new BotSeApplication();
		 
		if (ba.isAppActive()) {
			System.out.println("Already active, stop!");
			JOptionPane.showMessageDialog(null, "Bye. Bye. \n One thread already running.....");
			try {
				Thread.sleep(10000, 200);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.exit(1);
		} else {
			//System.out.println("========== ip address-------" + myIP);
			myIPaddress();
			System.out.println("========== ip address-------" + myIP);
			System.setProperty("webdriver.firefox.marionette", "/opt/geckodriver");
			//System.setProperty("webdriver.firefox.marionette", "D:\\geckodriver.exe");
			//System.setProperty("webdriver.firefox.marionette", "D://harry/gecko_driver/geckodriver.exe");
			driver = new FirefoxDriver();
			serviceDetail();
			
		}
	}

	List<Object[]> data;
	List<String> data1;
	List<Object[]> udata;
	List <String> udata1;
	List <String> kquery;
	int count;
	int property;
	List <Integer> usrList =null;
	Set <String> uemail;
	String htmlpage="";

	public void serviceDetail() {
		try {
			usrList =new ArrayList<Integer>();

			System.out.println("--- data==============");
			data = sps.findALLCustom(); // get_data_from_stored_project
			System.out.println(data + "--- data==============" + data.size());
			
			if (data.size() < 1) {
				// System.out.println("no any query for u .... '");
				try {
					sendMail(machine);
					driver.quit();
					driver.close();
					System.out.println("no any query for u .... '");
					System.exit(0);

				} catch (Exception e) {
					System.exit(0);
				}
				//JOptionPane.showMessageDialog(null, "no any query for u .... '");
	
				// factory = null;
				// HbmTemplateUtils.killMYSQLConnection();

				System.exit(0);
				
			} else {
				for (Object[] pj : data) {
					id = ((Integer) pj[0]);
					System.out.println("==============keywords ID==========="+id);
					keyphrase = ((String) pj[1]);
					System.out.println("==============keyphrase==========="+keyphrase);
					//machine = (String) pj[2];
					pipe = ((Integer) pj[2]);
					//preority = ((Integer) pj[4]);
					userId = ((Integer) pj[3]);
					System.out.println("==============userId==========="+userId);
					projectId =((Integer) pj[4]);
					//bls = ((Integer) pj[7]);
					// property_name = (pj[8].toString());
					 //pipe_domain = (pj[9].toString());
					System.out.println("==============projectId==========="+projectId);
				}
				
				System.out.println("'=====id............" + id);
				System.out.println("'=====keyphrase............" + keyphrase);
				//System.out.println("'=====machine............" + machine);
				System.out.println("'=====pipe............" + pipe);
				//System.out.println("'=====preority............" + preority);
				System.out.println("'=====userId............" + userId);
				System.out.println("'=====projectId............" + projectId);
				//System.out.println("'=====bls............" + bls);
				// System.out.println("'=====property name............" +
				// property_name);
				// System.out.println("'=====pipe domain............" +
				// pipe_domain);

				/**
				 * ========= add project name in keyword filter..........
				

			  //  keywordfilter = new HashSet<>();

				// data = pis.findALLCustom(projectId);
				// System.out.println("====data==="+data);
				// for(Object[]pj:data)
				// {
				// property_name = pj[0].toString();
				// }
				property_name = pis.findALLCustom(projectId);
				System.out.println("=== property name === " + property_name);
				String pj11[] = property_name.trim().split(" ");
				/*
				for (String a : pj11) {
					keywordfilter.add(a.trim());
				}
				
				System.out.println("=== keyword filter === " + keywordfilter);
				*/
				/**
				 * === get pipe detail.....
				 
				// data = mps.findALLCustom(pipe);
				// for(Object[]pj:data)
				// {
				// pipe_domain = pj[0].toString();
				// }
				pipe_domain = mps.findALLCustom(pipe);
				/**
				 * --- get keyword filter---
				 */

				//data1 = kfs.findALLCustom(projectId);
				/*

				for (Object[] pj : data) {
					keywordfilter.add(pj[0].toString().trim());
				}
				System.out.println("... keyword filter ....." + keywordfilter);

				System.out.println("... keyword filter size....." + keywordfilter.size());

				mms.machineStatusFree(myIP); // machine_engage

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

				/**
				 * ========== get black list sites============
				
				//if (bls == 1) {
					data = bss.findALLCustom();
					blkListSites = new HashSet<>();
					for (Object[] pj : data) {
						blkListSites.add(pj[0].toString().trim());
					//}

				}

				/**
				 * == calling web client to crawle the data ..
				 

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
					afterCrawl(3,keyphrase);
					links = null;

					sps.bingComplate(id);
					

				} 
				else if (pipe == 1)
				{
					sps.googleStart(id);

					// google search==
					googleSearch(keyphrase);
					System.out.println("=====google=== links size......." + links.size());
					afterCrawl(1, keyphrase);
					links = null;
				}
				else if (pipe == 2)
				{
					//sps.yahooStartGoogleComplate(id);

					// yahoo search==

					yahooSearch(keyphrase);
					System.out.println("=====yahoo=== links size......." + links.size());
					afterCrawl(2, keyphrase);
					links = null;
				}
				else if (pipe == 3)
				{
					//sps.bingStartYahooComplate(id);

					// Bing search==

					bingSearch(keyphrase);
					System.out.println("=====bing=== links size......." + links.size());
					afterCrawl(3,keyphrase);
					links = null;

					sps.bingComplate(id);
					
				}
				else if (pipe >3) {
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
					//mms.machineStatusFree(myIP);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// System.out.println("crawl is complete");

				 //JOptionPane.showMessageDialog(null, "crawl is complete");
				 
				 //****************** send mail to user
			}
			System.out.println("*******************************User Name**********"+userId);
			 sendMailToUser(userId,projectId);
			serviceDetail();
		}
			

		catch (Exception e) {
			e.printStackTrace();
			try {
				driver.close();
				driver.quit();
			} catch (Exception ee) {
			}
			JOptionPane.showMessageDialog(null, "crawl error");

			// factory = null;
			// HbmTemplateUtils.killMYSQLConnection();
			System.exit(0);
		}

	}

	public void googleSearch(String searchKeyword) {
		links = new ArrayList<>();
		// searchKeyword = dd;
		searchEngine = "https://www.google.co.in";
		try {          
			driver.get("https://www.google.co.in/search?q=" + searchKeyword);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath("//h3[@class='r']/a"));
			for (WebElement we : getLink) {
				links.add(we.getAttribute("href"));
				System.out.println("Crawle Url..................."+we.getAttribute("href"));
			}
			WebElement next = driver.findElement(By.xpath("//a[@id='pnnext']"));
			System.out.println(next.getAttribute("href"));
			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					// driver.get(next.getAttribute("href"));
					getLink = driver.findElements(By.xpath("//h3[@class='r']/a"));
					for (WebElement we : getLink) {
						links.add(we.getAttribute("href"));
						System.out.println("Crawle Url..................."+we.getAttribute("href"));
					}

					next = driver.findElement(By.xpath("//a[@id='pnnext']"));
					if (next.isDisplayed() || next.isEnabled()) {
						System.out.println("founded");
						next.click();
						Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
					} else {
						System.out.println("else break");
						break;
					}
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				} catch (Exception e) {
					System.out.println("error on google search");
					e.printStackTrace();
					break;
				}
			}
			System.out.println("=========links size....." + links.size());
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("==== next button not available====");
		}

		catch (Exception e) {
			// browser exception will be caught here

			// e.printStackTrace();
			System.err.println("=== google search error=====");
		}
	}

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
					// System.out.println(xx1[0]);

					// URLConnection con = new URL(xx1[0]).openConnection();
					// System.out.println("orignal url: " + con.getURL());
					// con.connect();
					// System.out.println("connected url: " + con.getURL());
					// InputStream is = con.getInputStream();
					// System.out.println("redirected url: " + con.getURL());
					//// lnk = con.getURL().toString();
					// is.close();

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

			// for (String we : links) {
			// try {
			// // System.out.println(ww.getAttribute("href").toString());
			// String xx[] = we.split("/RU=");
			// xx[1] = xx[1].replaceAll("%3a", ":").replaceAll("%2f", "/");
			// String xx1[] = xx[1].split("/RK=0/");
			// // System.out.println(xx1[0]);
			// // links.add(xx1[0]);
			// } catch (Exception r) {
			// }
			// }
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
			for (WebElement we : getLink) {
				links.add(we.getAttribute("href"));
			}
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
			System.out.println("=== query ===" + domain + searchKeyword);
			String q=domain + searchKeyword;
			System.out.println("=== query ==="+ q);

			driver.get(q);
			Thread.sleep(random_number(8000, 4000), random_number(1000, 100));
			getLink = driver.findElements(By.xpath("//h3[@class='r']/a"));
			for (WebElement we : getLink) {
				links.add(new String(we.getAttribute("href").getBytes(), "UTF-16"));
			}
			WebElement next = driver.findElement(By.xpath("//a[@id='pnnext']"));
			next.click();
			// System.out.println(next.getAttribute("href"));
			for (int i = 1; i <= 30; i++) {

				try {
					Thread.sleep(random_number(8000, 2000), random_number(1000, 100));
					 driver.get(next.getAttribute("href"));
					// next.click();
					getLink = driver.findElements(By.xpath("//h3[@class='r']/a"));
					System.out.println("Get Link......................."+getLink);
					for (WebElement we : getLink) {
						links.add(new String(we.getAttribute("href").getBytes(), "UTF-8"));
						System.out.println("Url Crawl............................. "+new String(we.getAttribute("href").getBytes(), "UTF-8"));
					}
					System.out.println("Links..................... "+links);

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

			// for (String we : links) {
			// System.out.println(we);
			// }
			// driver.quit();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("==== next button not available====");
		}

		catch (Exception e) {
			System.out.println("=== google oversies error = = = =");
			e.printStackTrace();
			driver.quit();
			System.exit(0);
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
					System.out.println("=== running.....first. running..............");
					if (lnk.toLowerCase().contains(property_name.trim().toLowerCase())) {
						// storeDB(lnk, projectId, userId, id, ppipe, machine,
						// findDomain(lnk.trim()), "1");
						mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine, findDomain(lnk.trim()));
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
//						skeyword = property_name.trim().replace(" ", "-");
						for (String srckey__ : searchkeywordlist) {
							if (lnk.toLowerCase().contains(srckey__.trim().toLowerCase())) {
								// storeDB(lnk, projectId, userId, id, ppipe,
								// machine, findDomain(lnk.trim()), "1");
								mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine,
										findDomain(lnk.trim()));

								b2 = true;
								 break;
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				if (b2 == false) {
					System.out.println("--- first fail ===2nd running============'");

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

						// pagesource = ps.getPageSource(lnk.trim());

						// doc = Jsoup.connect(lnk.trim()).get();
						// pagesource = doc.body().text();

						if (pagesource.toLowerCase().contains(property_name.trim().toLowerCase())) {

							// storeDB(lnk, projectId, userId, id, ppipe,
							// machine, findDomain(lnk.trim()), "1");
							mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine, findDomain(lnk.trim()));
							b2 = true;
							// break;
						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				if (b2 == false) {
					System.out.println("--- secound.... fail ====3rd running==========='");
					break;
					/*
					for (String pj : keywordfilter) {
						try {
							if (pagesource.toLowerCase().contains(pj.trim().toLowerCase())) {
								// storeDB(lnk, projectId, userId, id, ppipe,
								// machine, findDomain(lnk.trim()), "1");
								mcus.storeCrawleData(lnk, projectId, userId, id, ppipe, machine,
										findDomain(lnk.trim()));
								// break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					
				}

			}
		}
	}
*/
	
}
