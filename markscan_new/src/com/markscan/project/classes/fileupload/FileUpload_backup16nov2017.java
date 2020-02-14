package com.markscan.project.classes.fileupload;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Greylist;
import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Productivity_user_wise;
import com.markscan.project.beans.Url_email;
import com.markscan.project.beans.Whitelist_two;
import com.markscan.project.classes.ActionPerform;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.GreylistDao;
import com.markscan.project.dao.Productivity_user_wiseDao;
import com.markscan.project.dao.Txn_tblDao;
import com.markscan.project.dao.Url_emailDao;
import com.markscan.project.dao.Whitelist_twoDao;
import com.opencsv.CSVReader;
import com.opensymphony.xwork2.ActionSupport;



public class FileUpload_backup16nov2017 extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FileUpload_backup16nov2017.class);
	// public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	// public static final Charset UTF_8 = Charset.forName("UTF-8");
	private File uploadFile = null;
	private String uploadFileContentType = null;
	private String uploadFileFileName = null;
	HttpSession session2 = null;

	public static void main(String... str) {
		FileUpload_backup16nov2017 fu = new FileUpload_backup16nov2017();
		List<String> dom = new ArrayList<>();
		dom.add("https://www.facebook.com/ColorsTV/playu.me/embed-pzxs20v1i57l-600x326.html");
		dom.add("https://www.facebook.com/ColorsTV/downloadz/?url=https://www.youtube.com/watch?v=GdNKsW-E_po&ftype=mp4");
		dom.add("https://www.facebook.com/Starplus/embed-s4qbgdar7m7o-600x360.html");
		dom.add("https://www.youtube.com");
		dom.add("https://www.facebook.com/hotstar/kali-2016-malayalam-full-movie-watch-online-free.html");
		dom.add("https://twitter.com/StarPlus/mp3/dheere-dheere-se-kumar-sanu.html");
		dom.add("https://twitter.com/StarPlus/mp3/jALe_wlnce/01_-_Bol_Do_Na_Zara_-_Download.htm");

		for (String pj : dom) {
			// .println(fu.findDomain(pj));
		}
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	BeanFactory factory = null;
	InputStream input = null;
	String configFile = null;
	String filePath = null;
	Properties prop = null;
	List lst = null;
	// private List<Whitelist_two> wlstData = null;
	// private List<Greylist> greyData = null;
	String return_id = null;
	String return_id__c = null;
	String ww_lst = null;

	public String emailupload_pre() {
		return SUCCESS;
	}

	String duplicate_check = null;
	String previous_link = null;
	Url_emailDao dao_ue = null;
	Txn_tblDao dao = null;
	Crawle_url2 cu = null;
	Crawle_url2 cu__next = null;
	Url_email ue = null;
	Crawle_url2Dao cu_dao = null;
	String indexPath, indexPath2 = null;
	int duplicate = 0;
	int total_link = 0;
	List<String> aa = null;
	Map<String, String> errorCollection = null;
	Productivity_user_wiseDao dao_011 = null;
	File fileToCreate = null;

	public String execute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info("-- session 2==" + session2);

		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			uid = (int) session2.getAttribute("uid");
			factory = ActionPerform.getFactory();
			dao_011 = (Productivity_user_wiseDao) factory.getBean("d27");

					try {
				prop = new Properties();
				configFile = "/details.properties";
				input = getClass().getResourceAsStream(configFile);
				prop.load(input);
				// db = prop.getProperty("database");
				filePath = prop.getProperty("filepath");
				indexPath = prop.getProperty("indexPath1");
				indexPath2 = prop.getProperty("indexPath2");
				// .println("indexpath==== " + indexPath);
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error("file read error .. ", e);
			} finally {
				configFile = null;
				input = null;
				prop = null;
			}

			try {

				// System.err.println("Server path:" + filePath); // check your
				// path in
				// console
				logger.info("Server path:" + filePath);
				logger.info("uploadFile path:" + uploadFile.getName());
				logger.info("uploadFileFileName path:" + uploadFileFileName);
				// System.err.println(uploadFile.getParentFile());
				int file_count = 1;
				File[] list = new File(filePath).listFiles();
				if (list != null)
					for (File fil : list) {

						if (fil.getName().startsWith("userid" + uid + "___")) {
							file_count = file_count + 1;

						}
						if (file_count > 2) {
							return "alreadyFile";
						}

					}
				list = null;

				fileToCreate = new File(filePath,
						"userid" + uid + "___" + uploadFile.getName().concat("_").concat(uploadFileFileName));// Create
				// file
				// name
				// temp
				// +
				// original

				FileUtils.copyFile(uploadFile, fileToCreate); // Just copy temp
																// file
																// content tos
																// this
																// file

				logger.info("file..... " + fileToCreate);
				// System.err.println(fileToCreate.getAbsolutePath());

				dao = (Txn_tblDao) factory.getBean("d");
				dao_ue = (Url_emailDao) factory.getBean("d22");

				cu__next = new Crawle_url2();

				try {
					errorCollection = new HashMap<>();
					wb = WorkbookFactory.create(fileToCreate);
					mySheet = wb.getSheetAt(0);
					rowIter = mySheet.rowIterator();
					total_link = 0;
					duplicate = 0;
					
					/**
					 * === batch excel read code in  testing mode...start....
					 * 
					 */
					
					
					
					
					
					/**
					 * === batch excel read code in  testing mode...end....
					 * 
					 */
					for (Iterator<Row> rowIterator = mySheet.rowIterator(); rowIterator.hasNext();) {
						aa = new ArrayList<>();
						for (Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator(); cellIterator
								.hasNext();) {

							aa.add(new DataFormatter().formatCellValue(cellIterator.next()));
						}

						if (aa.get(0).contains("https://") || aa.get(0).contains("http://")) {
							if (aa.get(0).contains("\"")) {
								errorCollection.put(aa.get(0), " Double Cotes \" in your link..");
							} else {
								cu = new Crawle_url2();
								cu.setCrawle_url2(aa.get(0)); // craule_url2
								cu.setProject_id(Integer.parseInt(aa.get(1).trim())); // project_id
								cu.setNote1(aa.get(2)); // note1
								cu.setNote2(aa.get(3)); // note2
								cu.setDomain_name(aa.get(4).trim()); // domain_name
								cu.setLink_type(aa.get(5).trim()); // linktype
								cu.setFilter_new(aa.get(6).trim()); // datatype
								cu.setUser_id(uid);
								cu.setIs_Manual("0");
								cu.setC_new("0");
								cu.setQc_new("0");
								cu.setVerified(1);

								try {
									if (aa.get(7).contains("https://") || aa.get(7).contains("http://")) {
										if (aa.get(7).contains("\"")) {
											errorCollection.put(aa.get(7), " Double Cotes \" in Infring link column..");
										} else {

											cu.setLink_logger_srclink(aa.get(7));
											cu.setLink_logger(1);

											int pj = indexedSearch(linkupdate(aa.get(0).trim()), aa.get(1).trim(), 1,
													aa.get(4).trim(), indexPath);
											logger.info("=======Lucene logger... data.... " + pj);
											if (pj == 0) {
												return_id = dao.saveData(cu); // source_link_save
												// total_link = total_link + 1;
												/**
												 * --------- daily motion data
												 * save.........
												 */

												if (aa.get(4).trim().equalsIgnoreCase("dailymotion.com".trim())
														&& return_id != null) {
													int pj__c = indexedSearch_Two(linkupdate(aa.get(0).trim()),
															aa.get(1).trim(), "feedback@dailymotion.com", 0,
															indexPath2);
													if (pj__c == 0) {
														try {
															ue = new Url_email();
															ue.setWid("11");
															ue.setUrl(aa.get(0).trim());
															ue.setCrawle_url2_id(Integer.parseInt(return_id));
															ue.setDomain_name(aa.get(4).trim());
															ue.setProject_id(Integer.parseInt(aa.get(1).trim()));
															ue.setRenotification_id("0");
															ue.setEmail("feedback@dailymotion.com");
															ue.setDate_time(nowTime());
															ue.setEmail_type("2");
															ue.setUser_id(uid);
															ue.setEmail_error_new("0");
															ue.setQc_new(1);
															return_id__c = dao_ue.saveData_retuenID(ue);
															ue = null;
															return_id__c = null;
														} catch (Exception e) {
															logger.error("  Dailymotion save error ...", e);
														}
													}
												}

											} else {
												// errorCollection.put(aa.get(0),
												// " Duplicate Data..");
												duplicate = duplicate + 1;
											}

										}
									} else {
										errorCollection.put(aa.get(7),
												"  Http:// or Https:// missing in Infringing link..");
									}

								} catch (Exception e) {
									logger.info("saving infringing.....");
									cu.setLink_logger(0);
									cu.setLink_logger_srclink("");
									int pj = indexedSearch(linkupdate(aa.get(0).trim()), aa.get(1).trim(), 0,
											aa.get(4).trim(), indexPath);
									if (pj == 0) {
										dao.saveData(cu);// infringing_link_save
										try {
											Thread.sleep(100);
										} catch (Exception ee) {
										}
										// total_link = total_link + 1;
									} else {
										// errorCollection.put(aa.get(0), "
										// Duplicate Data..");
										duplicate = duplicate + 1;
									}

								}

							}
						} else {
							errorCollection.put(aa.get(0), "  Http:// or Https:// missing from link..");
						}

						/*
						 * System.out.println("---------link-------" +
						 * aa.get(0));
						 * System.out.println("---------projectid-------" +
						 * aa.get(1));
						 * System.out.println("---------note1-------" +
						 * aa.get(2));
						 * System.out.println("---------note2-------" +
						 * aa.get(3));
						 * System.out.println("---------domain-------" +
						 * aa.get(4));
						 * System.out.println("---------linktype-------" +
						 * aa.get(5));
						 * System.out.println("---------data type-------" +
						 * aa.get(6)); try {
						 * System.out.println("--------infi-link-------" +
						 * aa.get(7)); } catch (Exception e) {
						 * logger.error("data not found"); }
						 * 
						 * System.out.
						 * println(" **************************************************************** "
						 * );
						 */
						total_link = total_link + 1;

						aa = null;
						cu = null;
						ue = null;
						return_id__c = null;
						return_id = null;

					}
					dao = null;
					dao_ue = null;
					cu = null;
					cu__next = null;
					ue = null;
					statusUpdate = "Data File uploaded successfully...!!!";
					logger.info("data Successfully deploy");
				} catch (Exception e) {
					//
					e.printStackTrace();
					statusUpdate = " Your file is not correct.. please check your file that should be in .xls format...!!!";
				} finally {
//					errorCollection = null;
					wb = null;
					mySheet = null;
					rowIter = null;
					dao = null;
					dao_ue = null;
					cu = null;
					cu__next = null;
					ue = null;
					prop = null;
				}

				dao_011.customUpdateProject("update Productivity_user_wise set duplicate=duplicate+" + duplicate + ", "
						+ " total_link=total_link+ " + total_link + " where curr_date='" + nowTime2() + "' and user_id="
						+ uid);

				// Thread.sleep(100);

				fileToCreate.delete(); // delete file..

				// .println("file Successfully deleted");
				logger.info("file Successfully deleted");
			} catch (Exception e) {
				// e.printStackTrace();
				addActionError(e.getMessage());
				logger.error("file upload error last catch()", e);
				return INPUT;

			} finally {
				duplicate_check = null;
				previous_link = null;
				return_id = null;
				dao_ue = null;
				dao = null;
				dao_011 = null;
				cu = null;
				cu__next = null;
				ue = null;
				fileToCreate = null;
			}
			wb = null;
			mySheet = null;
			rowIter = null;
			dao_011 = null;
			session2 = null;
			factory = null;
			System.gc();
			return SUCCESS;
		}

	}

	private Pattern pattern = null;;
	private Matcher matcher = null;;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	List<Integer> wrongEmailData = null;

	public List<Integer> getWrongEmailData() {
		return wrongEmailData;
	}

	public void setWrongEmailData(List<Integer> wrongEmailData) {
		this.wrongEmailData = wrongEmailData;
	}

	Workbook wb = null;
	Sheet mySheet = null;
	Iterator<Row> rowIter = null;

	public String emailUpload() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info("-- session 2==" + session2);

		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {

			try {
				prop = new Properties();
				configFile = "/details.properties";
				input = getClass().getResourceAsStream(configFile);
				prop.load(input);
				filePath = prop.getProperty("filePath");
				indexPath2 = prop.getProperty("indexPath2");
			} catch (Exception e) {
				logger.error("file read error .. ", e);
			} finally {
				configFile = null;
				input = null;
				prop = null;
			}

			try {
				uid = (int) session2.getAttribute("uid");
				factory = ActionPerform.getFactory();
				dao_ue = (Url_emailDao) factory.getBean("d22");

				// List<Url_email> a=dao_ue.viewRecord("select max(id) from
				// Url_email");
				// .println(a.size()+"....gap...."+a.get(0));

				logger.info("Server path:" + filePath);
				fileToCreate = new File(filePath, uploadFile.getName().concat("_").concat(uploadFileFileName));// Create
																												// file
																												// name
																												// temp
																												// +
																												// original

				FileUtils.copyFile(uploadFile, fileToCreate); // Just copy temp
																// file
																// content tos
																// this
																// file

				logger.info("file..... " + fileToCreate);
				/**
				 * read csv file then delete the file from server....
				 */

				// Txn_tbl tx = new Txn_tbl();
				cu = new Crawle_url2();

				// .println("user id = = = = = = = = =" + uid);
				logger.info("user id = = = = = = = =  =" + uid);
				wrongEmailData = new ArrayList<>();
				// pattern = Pattern.compile(IPADDRESS_PATTERN);

				try {
					errorCollection = new HashMap<>();
					wb = WorkbookFactory.create(fileToCreate);
					mySheet = wb.getSheetAt(0);
					rowIter = mySheet.rowIterator();
					for (Iterator<Row> rowIterator = mySheet.rowIterator(); rowIterator.hasNext();) {
						aa = new ArrayList<>();
						ue = new Url_email();
						for (Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator(); cellIterator
								.hasNext();) {

							// System.out.println(
							// "column index,,," + k + "........" + ((Cell)
							// cellIterator.next()).toString());
							aa.add(new DataFormatter().formatCellValue(cellIterator.next()));
						}

						try {
							pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
							matcher = pattern.matcher(aa.get(4).trim());
							// System.out.println("... link.........." +
							// aa.get(1));
							if (aa.get(1).contains("https://") || aa.get(1).contains("http://")) {
								if (aa.get(1).contains("\"")) {
									errorCollection.put(aa.get(0).trim().substring(0, (aa.get(0).trim().length() - 2)),
											" Double Cotes \" in your link..");
								} else {

									if (matcher.find()) {

										int pj__c = indexedSearch_Two(linkupdate(aa.get(1).trim()), aa.get(3).trim(),
												aa.get(4).trim(), Integer.parseInt(aa.get(6).trim()), indexPath2);
//										System.out.println("--- index size..."+pj__c);
//										System.out.println("-----url--------"+aa.get(1));
//										System.out.println("-----domain--------"+aa.get(2));
//										System.out.println("-----project id--------"+aa.get(3));
//										System.out.println("-----email--------"+aa.get(4));
//										System.out.println("-----renotification id--------"+aa.get(6));
										if (pj__c == 0) {

//											System.out.println(aa.get(0) + ",,,,,,,pj,,,,,,," + aa.get(0).length()
//													+ "----------"
//													);

											ue.setCrawle_url2_id(Integer.parseInt(aa.get(0).trim()));
											ue.setUrl(aa.get(1).trim());
											ue.setDomain_name(aa.get(2).trim());
											ue.setProject_id(Integer.parseInt(aa.get(3).trim()));
											ue.setEmail(aa.get(4).trim());
											ue.setEmail_type(aa.get(5).trim());
											ue.setRenotification_id(aa.get(6).trim());

											ue.setUser_id(uid);
											ue.setDate_time(nowTime());
											ue.setEmail_error_new("0");
											ue.setWid("2");

											try {
												ue.setIsp_ipaddress(aa.get(7));
											} catch (Exception e) {
											}
											return_id = dao_ue.saveData_retuenID(ue);

										} 
									}else {
										errorCollection.put(aa.get(0).trim(), " Error on mail id..");
									}
								}
							} else {
								errorCollection.put(aa.get(0).trim(), "  Http:// or Https:// missing from link..");
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							return_id = null;
							ue = null;
							pattern = null;
							matcher = null;
						}
						aa = null;
						return_id = null;
						ue = null;
						pattern = null;
						matcher = null;
					}
					cu = null;
					cu__next = null;
					dao = null;
					dao_ue = null;
					cu = null;
					cu__next = null;
					ue = null;
					logger.info("data Successfully deploy");
					statusUpdate = "Email File uploaded successfully...!!!";

//					errorCollection = null;
					wb = null;
					mySheet = null;
					rowIter = null;
				} catch (Exception e) {
					e.printStackTrace();
					statusUpdate = " Your file is not correct.. please check your file that should be in .xls format...!!!";
				} finally {
					cu = null;
					cu__next = null;
					dao = null;
					dao_ue = null;
					cu = null;
					cu__next = null;
					ue = null;

//					errorCollection = null;
					wb = null;
					mySheet = null;
					rowIter = null;

				}

				fileToCreate.delete(); // delete file..

				logger.info("file Successfully deleted");
				wb = null;
				mySheet = null;
				pattern = null;
				matcher = null;
				rowIter = null;
				factory = null;
				session2 = null;
				fileToCreate = null;

			} catch (Exception e) {
				logger.error("Email  file upload error !!", e);
				wb = null;
				mySheet = null;
				pattern = null;
				matcher = null;
				rowIter = null;
				factory = null;
				dao_ue = null;
				return_id = null;
				ue = null;
				pattern = null;
				matcher = null;
				fileToCreate = null;
				return ERROR;
			} finally {
				wb = null;
				return_id = null;
				ue = null;
				pattern = null;
				matcher = null;
				mySheet = null;
				pattern = null;
				matcher = null;
				rowIter = null;
				factory = null;
				dao_ue = null;
				fileToCreate = null;
			}
			factory = null;
			session2 = null;
			return SUCCESS;
		}

	}

	public String playwireUpload_pre() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			session2 = null;
			return SUCCESS;
		}
	}

	public String nowTime() {
		String time__c = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			// .println(dateFormat.format(date));
			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);
			dateFormat = null;
			date = null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("date format error ...", e);
		}
		return time__c;
	}

	public String findDomain(String ddomain) {
		String domain__c = "";
		try {

			// .println("=pp=" + ddomain.lastIndexOf("/"));

			if (ddomain.startsWith("https:")) {
				domain__c = ddomain.replace("https://", "");
				domain__c = domain__c.replace("www.", "");
				// .println("domain===1..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					pj = null;
					pj1 = null;
					// .println("domain===4.1.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null;
					// .println("domain===4.112.." + domain__c);
				}

			} else if (ddomain.startsWith("http:")) {
				domain__c = ddomain.replace("http://", "");
				domain__c = domain__c.replace("www.", "");
				// .println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					pj = null;
					pj1 = null;
					// .println("domain===4.2.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null;
					// .println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("//www.")) {
				domain__c = ddomain.replace("//www.", "");
				domain__c = domain__c.replace("www.", "");
				// .println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					pj = null;
					pj1 = null;
					domain__c = pj[0];
					// .println("domain===4.3.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null;
					// .println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("www")) {
				// domain__c = ddomain.replace("//www.", "");
				domain__c = ddomain.replace("www.", "");
				// .println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					pj = null;
					pj1 = null;
					// .println("domain===4.33.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null;
					// .println("domain===4.114.." + domain__c);
				}
			}

			else if (!ddomain.startsWith("http:") || !ddomain.startsWith("htts:") || !ddomain.startsWith("www.")
					|| !ddomain.startsWith("//www.")) {
				String pj[] = ddomain.split("/");
				domain__c = pj[0];
				pj = null;
				// .println("domain===4.114.." + domain__c);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("find domain error... ", e);
		}
		return domain__c;
	}

	public String nowTime2() {
		String time__c = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			// .println(dateFormat.format(date));
			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);
			dateFormat = null;
			date = null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("date format error ...", e);
		}
		return time__c;
	}

	private String statusUpdate = null;
	private int uid;
	private int linklogger;
	Set<String> wrongLinkType = null;

	public Set<String> getWrongLinkType() {
		return wrongLinkType;
	}

	public void setWrongLinkType(Set<String> wrongLinkType) {
		this.wrongLinkType = wrongLinkType;
	}

	public String getStatusUpdate() {
		return statusUpdate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getLinklogger() {
		return linklogger;
	}

	public void setLinklogger(int linklogger) {
		this.linklogger = linklogger;
	}

	public Map<String, String> getErrorCollection() {
		return errorCollection;
	}

	public void setErrorCollection(Map<String, String> errorCollection) {
		this.errorCollection = errorCollection;
	}

	Analyzer analyzer2 = null;
	QueryParser queryParser = null;
	Query q = null;
	int hitsPerPage = 10;
	IndexReader reader = null;
	IndexSearcher searcher = null;
	TermQuery tq = null;
	TopDocs results = null;
	Document d = null;

	public int indexedSearch(String link__c, String project_Id__c, int linklogger__c, String domin__c, String path) {
		// String path = "D:\\lucene\\index\\one";
		hit = 0;
		logger.info("---- link-----" + link__c);
		logger.info("------- project id-----" + project_Id__c);
		logger.info("------ link logger------" + linklogger__c);
		logger.info("------ domain-------" + domin__c);
		logger.info("------ path-------" + path);

		try {

			// StandardAnalyzer analyzer = new StandardAnalyzer(Version.LATEST);
			analyzer2 = new StandardAnalyzer();
			// Directory directory =
			// FSDirectory.open(Paths.get("D:\\lucene\\index"));
			// here you must specify the column you are searching as you put it
			// in the index file
			queryParser = new QueryParser("crawle_url2", analyzer2);

			q = queryParser.parse(queryParser.escape(link__c.trim()));

			// parser.parse(QueryParser.escape(queryStr));

			// .println(q);
			logger.info("Query------" + q);

			// QueryParser queryParser = new QueryParser("crawle_url2",
			// analyzer2);
			// String escapedString = queryParser.escape(query);
			// q= queryParser.parse("crawle_url2:" + escapedString);
			// .println(q);

			// IndexReader reader = DirectoryReader.open(directory);
			reader = DirectoryReader.open(FSDirectory.open(Paths.get(path)));
			searcher = new IndexSearcher(reader);

			tq = new TermQuery(new Term("crawle_url2", link__c.trim()));

			// TopScoreDocCollector collector =
			// TopScoreDocCollector.create(hitsPerPage, true);
			// searcher.search(q, collector);
			// TopDocs results = searcher.search(query, 100);

			results = searcher.search(tq, 100000);
			ScoreDoc[] hits = results.scoreDocs;
			// display result
			logger.info("Found " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				d = searcher.doc(docId);

				logger.info("Found a record id:[" + d.get("id") + "] \t crawle_url2:[" + d.get("crawle_url2")
						+ "] \t domain_name:[" + d.get("domain_name") + "] \t created_on:[" + d.get("created_on")
						+ "] \t link_logger:[" + d.get("link_logger") + "] \t link_logger_srclink:["
						+ d.get("link_logger_srclink") + "] \t project_id:[" + d.get("project_id")
						+ "] \t project_name:[" + d.get("project_name") + "] \t client_name:[" + d.get("client_name")
						+ "] \t project_type:[" + d.get("project_type") + "] \t user_id:[" + d.get("user_id")
						+ "] \t user_name:[" + d.get("user_name") + "] \t w_list:[" + d.get("w_list") + "]\t g_list:["
						+ d.get("g_list") + "] \t note1:[" + d.get("note1") + "] \t note2:[" + d.get("note2") + "] ");

				combined_link__c = d.get("crawle_url2").trim() + "-" + d.get("project_id").trim() + "-"
						+ d.get("link_logger").trim() + "-" + d.get("domain_name").trim();

				if (combined_link__c.trim().equalsIgnoreCase(
						link__c.trim() + "-" + project_Id__c + "-" + linklogger__c + "-" + domin__c.trim())) {
					hit = 1;
					logger.info("duplicate... mil gya h..... ");
					break;
				}
				d = null;
				combined_link__c = null;
			}
			// ii = hits.length;
			analyzer2 = null;
			queryParser = null;
			reader = null;
			searcher = null;
			tq = null;
			results = null;
			combined_link__c = null;
			hits = null;
			q = null;
			d = null;
		} catch (Exception e) {
			logger.error("error on data indexsearch");

		} finally {
			analyzer2 = null;
			queryParser = null;
			reader = null;
			searcher = null;
			tq = null;
			results = null;
			combined_link__c = null;
			q = null;
			d = null;

		}
		return hit;
	}

	public int indexedSearch_Two(String link__c, String project_Id__c, String email__c, int r_id_cc, String path) {

		// .println("-------link__c------------"+link__c);
		// .println("---------project_Id__c----------"+project_Id__c);
		// .println("-------email__c------------"+email__c);
		// .println("---------r_id_cc----------"+r_id_cc);
		hit = 0;
		try {

			// .println("index searching. 2....");
			analyzer2 = new StandardAnalyzer();
			queryParser = new QueryParser("url", analyzer2);

			q = queryParser.parse(queryParser.escape(link__c));
			logger.info("Query------" + q);
			reader = DirectoryReader.open(FSDirectory.open(Paths.get(path)));
			searcher = new IndexSearcher(reader);

			tq = new TermQuery(new Term("url", link__c.toString()));

			results = searcher.search(tq, 100000);
			ScoreDoc[] hits = results.scoreDocs;
			// display result
			// System.out.println("Found " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				d = searcher.doc(docId);

				logger.info("Found a record id:[" + d.get("id") + "] \t wid:[" + d.get("wid") + "] \t date_time:["
						+ d.get("date_time") + "] \t email:[" + d.get("email") + "] \t url:[" + d.get("url")
						+ "] \t crawle_url2_id:[" + d.get("crawle_url2_id") + "] \t domain_name:["
						+ d.get("domain_name") + "] \t project_id:[" + d.get("project_id") + "] \t user_id:["
						+ d.get("user_id") + "] \t renotification_id:[" + d.get("renotification_id") + "] ");

				combined_link__c = d.get("url").trim() + "-" + d.get("project_id").trim() + "-" + d.get("email").trim()
						+ "-" + d.get("renotification_id").trim();

				if (combined_link__c.trim().equalsIgnoreCase(
						link__c.trim() + "-" + project_Id__c.trim() + "-" + email__c.trim() + "-" + r_id_cc)) {
					hit = 1;
					logger.info("duplicate... mil gya h..2 se... ");
					break;
				}
				d = null;
				combined_link__c = null;
			}
			analyzer2 = null;
			queryParser = null;
			reader = null;
			searcher = null;
			tq = null;
			results = null;
			d = null;
			hits = null;
			q = null;
			// ii = hits.length;
		} catch (Exception e) {
			logger.error("error on data indexsearch", e);

		} finally {
			analyzer2 = null;
			queryParser = null;
			reader = null;
			searcher = null;
			tq = null;
			results = null;
			d = null;
			q = null;
		}
		return hit;
	}

	long j = 0;
	Document document = null;
	IndexWriterConfig config = null;

	/*
	 * public long indexedData_One(String id, String crawle_url2, String
	 * domain_name, String created_on, int link_logger, String
	 * link_logger_srclink, String project_id, String user_id, String note1,
	 * String note2, String file) {
	 * 
	 * try { config = new IndexWriterConfig(analyzer2); IndexWriter writer = new
	 * IndexWriter(FSDirectory.open(Paths.get(file)), config); document = new
	 * Document(); // add the fields to the index as you required
	 * document.add(new StringField("id", id, Field.Store.YES));
	 * 
	 * document.add(new StringField("crawle_url2", crawle_url2,
	 * Field.Store.YES)); document.add(new StringField("domain_name",
	 * domain_name, Field.Store.YES)); document.add(new
	 * StringField("created_on", created_on, Field.Store.YES)); document.add(new
	 * StringField("link_logger", String.valueOf(link_logger),
	 * Field.Store.YES)); document.add(new StringField("link_logger_srclink",
	 * link_logger_srclink == null ? "" : link_logger_srclink,
	 * Field.Store.YES)); document.add(new StringField("project_id", project_id,
	 * Field.Store.YES)); document.add(new StringField("user_id", user_id,
	 * Field.Store.YES));
	 * 
	 * document.add(new StringField("note1", note1 == null ? "" : note1,
	 * Field.Store.YES)); document.add(new StringField("note2", note2 == null ?
	 * "" : note2, Field.Store.YES));
	 * 
	 * // create the index files j = writer.updateDocument(new Term("id", id),
	 * document); writer.close();
	 * logger.info("====== data indexed successfully================"); } catch
	 * (Exception e) { e.printStackTrace(); // TODO: handle exception } finally
	 * { config = null; document = null; } return j; }
	 */

	/*
	 * public long indexedData_Two(String id, String url, String domain_name,
	 * String created_on, String project_id, String user_id, String email,
	 * String crawle_url2_id, String renotification_id, String file) {
	 * 
	 * try { config = new IndexWriterConfig(analyzer2); IndexWriter writer = new
	 * IndexWriter(FSDirectory.open(Paths.get(file)), config); document = new
	 * Document(); // add the fields to the index as you required
	 * document.add(new StringField("id", id, Field.Store.YES));
	 * 
	 * document.add(new StringField("crawle_url2", url, Field.Store.YES));
	 * document.add(new StringField("domain_name", domain_name,
	 * Field.Store.YES)); document.add(new StringField("created_on", created_on,
	 * Field.Store.YES)); document.add(new StringField("project_id", project_id,
	 * Field.Store.YES)); document.add(new StringField("user_id", user_id,
	 * Field.Store.YES)); document.add(new StringField("email", email,
	 * Field.Store.YES)); document.add(new StringField("crawle_url2_id",
	 * crawle_url2_id, Field.Store.YES)); document.add(new
	 * StringField("renotification_id", renotification_id, Field.Store.YES));
	 * 
	 * // create the index files j = writer.updateDocument(new Term("id", id),
	 * document); writer.close();
	 * logger.info("====== data 2 indexed successfully================"); }
	 * catch (Exception e) { e.printStackTrace(); // TODO: handle exception }
	 * finally { config = null; document = null; } return j; }
	 */

	int hit = 0;
	String combined_link__c = null;
	int ii = 0;

	public String linkupdate(String lnk) {
		try {
			if (lnk.contains("+"))
				lnk = lnk.replaceAll("+", "\\+");

			if (lnk.contains("-"))
				lnk = lnk.replaceAll("-", "\\-");
			if (lnk.contains("&"))
				lnk = lnk.replaceAll("&", "\\&");
			if (lnk.contains("|"))
				lnk = lnk.replaceAll("|", "\\|");
			if (lnk.contains("!"))
				lnk = lnk.replaceAll("!", "\\!");
			if (lnk.contains("("))
				lnk = lnk.replaceAll("(", "\\(");
			if (lnk.contains(")"))
				lnk = lnk.replaceAll(")", "\\)");
			if (lnk.contains("{"))
				lnk = lnk.replaceAll("{", "\\{");
			if (lnk.contains("}"))
				lnk = lnk.replaceAll("}", "\\}");
			if (lnk.contains("["))
				lnk = lnk.replaceAll("[", "\\[");
			if (lnk.contains("]"))
				lnk = lnk.replaceAll("]", "\\]");
			if (lnk.contains("^"))
				lnk = lnk.replaceAll("^", "\\^");
			if (lnk.contains("\""))
				lnk = lnk.replaceAll("\"", "\\\"");
			if (lnk.contains("~"))
				lnk = lnk.replaceAll("~", "\\~");
			if (lnk.contains("*"))
				lnk = lnk.replaceAll("*", "\\*");
			if (lnk.contains("?"))
				lnk = lnk.replaceAll("?", "\\?");
			if (lnk.contains(":"))
				lnk = lnk.replaceAll(":", "\\:");
			if (lnk.contains("\\"))
				lnk = lnk.replaceAll("\\", "\\\\");
		} catch (Exception e) {
			// e.printStackTrace();
		}
		// System.out.println("---- finally link is..............." + lnk);
		return lnk;

	}
}
