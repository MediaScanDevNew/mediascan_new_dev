package com.markscan.project.classes.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.markscan.project.classes.screenshot.ScreenShotDetails;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.CommanReporting;
import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Url_email;
import com.markscan.project.beans.Whitelist_instagram;
import com.markscan.project.classes.ActionPerform;
import com.markscan.project.classes.QualityCheck;
import com.markscan.project.classes.youtube.YT_data_extract;
import com.markscan.project.classes.youtube.Youtube;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.GreylistDao;
import com.markscan.project.dao.Productivity_user_wiseDao;
import com.markscan.project.dao.Project_infoDao;
import com.markscan.project.dao.Txn_tblDao;
import com.markscan.project.dao.Url_emailDao;
import com.markscan.project.dao.WhitelistDao;
import com.markscan.project.dao.Whitelist_instagramDao;
import com.markscan.project.dao.Whitelist_twoDao;
import com.markscan.project.dao.Whitelist_ytDao;
import com.opensymphony.xwork2.ActionSupport;

public class FileUpload extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FileUpload.class);
	// public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	// public static final Charset UTF_8 = Charset.forName("UTF-8");
	List <String> url2= null;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public static void main(String... str) {

	}

	String ytLink = null;
	// String ytCh_id = null;

	@SuppressWarnings("finally")
	public String execute() {
		url2 =new ArrayList<String>();
		session2 = ServletActionContext.getRequest().getSession();
		// logger.info("-- session 2==" + session2);

		if (session2 == null || session2.getAttribute("login") == null) {
			// logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			yt_data = new YT_data_extract();
			uid = (int) session2.getAttribute("uid");
			factory = ActionPerform.getFactory();
			dao_011 = (Productivity_user_wiseDao) factory.getBean("d27");
			dao_white = (WhitelistDao) factory.getBean("d20");
			dao_white2 = (Whitelist_twoDao) factory.getBean("d18");
			dao_grey = (GreylistDao) factory.getBean("d19");
			dao_white_insta = (Whitelist_instagramDao) factory.getBean("d34");
			dao_YT = (Whitelist_ytDao) factory.getBean("d36");
			prop = new Properties();
			configFile = "/details.properties";
			input = getClass().getResourceAsStream(configFile);
			System.out.println("\n\nclientId:------------->"+clientId);
			
			//---------------- Project Info ----------------------------
			Project_infoDao pidao = null;
			pidao = (Project_infoDao) factory.getBean("d1");
			
			//-----------------------------------------------------------
			
			try {
				prop.load(input);
				filePath = prop.getProperty("filepath");
				indexPath = prop.getProperty("indexPath1");
				indexPath2 = prop.getProperty("indexPath2");
			} catch (Exception e) {
			} finally {
				configFile = null;
				input = null;
				prop = null;
			}
			// get auto qc data
			prop = new Properties();
			configFile = "/SM_direct_inforcement.properties";
			input = getClass().getResourceAsStream(configFile);
			try {
				prop.load(input);
				googleTracker = prop.getProperty("directGT");
				masterEmail = prop.getProperty("directME");
				mailShoot = prop.getProperty("directMS");
			} catch (Exception e) {
			} finally {
				configFile = null;
				input = null;
				prop = null;
			}
			// logger.info("Server path:" + filePath);
			// logger.info("uploadFile path:" + uploadFile.getName());
			// logger.info("uploadFileFileName path:" + uploadFileFileName);

			directGT = new HashSet<>();
			directME = new HashSet<>();

			prj = googleTracker.split(",");
			for (String one : prj) {
				directGT.add(one.replaceAll("'", "").trim());
			}
			prj = null;
			googleTracker = null;
			prj = masterEmail.split(",");
			for (String one : prj) {
				directME.add(one.replaceAll("'", "").trim());
			}
			prj = null;
			masterEmail = null;

			dao = (Txn_tblDao) factory.getBean("d");
			dao_ue = (Url_emailDao) factory.getBean("d22");
			cu__next = new Crawle_url2();
			int file_count = 1;
			File[] list = new File(filePath).listFiles();
			if (list != null)
				for (File fil : list) {
					if (fil.getName().startsWith("userid" + uid + "___" + redirect)) {
						file_count = file_count + 1;
					}
					if (file_count > 2) {
						return "alreadyFile";
					}
				}
			list = null;
			int p = 0;
			int q = 0;
			batchupload = new ArrayList<>();
			errorCollection = new HashMap<>();
			fileToCreate = new File(filePath,
					"userid" + uid + "___" + redirect + uploadFile.getName().concat("_").concat(uploadFileFileName));
			try {
				FileUtils.copyFile(uploadFile, fileToCreate);
				logger.info("file to create..... " + fileToCreate);
				wb = WorkbookFactory.create(fileToCreate);
				mySheet = wb.getSheetAt(0);

				rowIter = mySheet.rowIterator();
				white_list = new HashSet<String>();
				white_two = new HashSet<String>();
				grey_list = new HashSet<String>();
				white_list_insta = new HashSet<>();
				white_yt = new HashSet<>();
				ContentFilterData = new ArrayList<>();
				ScreenShotDetails screenUpload=new ScreenShotDetails();

				// ========== instagram whitelist load==========
				lst = dao_white_insta.viewRecord("select taken_by_id from Whitelist_instagram where clientId="+clientId);
				for (int i = 0; i < lst.size(); i++) {
					// obj = (Object[]) lst.get(i);
					white_list_insta.add(lst.get(i).toString());
				}
				logger.info("== white instagram list size====" + white_list_insta.size());
				obj = null;
				lst = null;
				dao_white_insta = null;
				// ======= whitelist load================
				lst = dao_white.viewRecord("select domain_name from Whitelist where clientId="+clientId);
				for (int i = 0; i < lst.size(); i++) {
					// obj = (Object[]) lst.get(i);
					white_list.add(lst.get(i).toString());
				}
				// logger.info("== white list size====" + white_list.size());
				obj = null;
				lst = null;
				dao_white = null;
				// === social media whitelist load=====
				lst = dao_white2.viewRecord("select link from Whitelist_two where clientId="+clientId);
				for (int i = 0; i < lst.size(); i++) {
					white_two.add(lst.get(i).toString());
				}
				// logger.info("== white list 2 size====" + white_two.size());
				obj = null;
				lst = null;
				dao_white2 = null;
				// === grey list load==========
				lst = dao_grey.viewRecord("select domain from Greylist where clientId="+clientId);
				for (int i = 0; i < lst.size(); i++) {
					grey_list.add(lst.get(i).toString());
				}
				// logger.info("== grey list size====" + grey_list.size());
				obj = null;
				lst = null;
				dao_grey = null;
				// ===== youtubeWhitelist=======
				lst = dao_YT.viewRecord("select ch_id from Whitelist_yt where clientId="+clientId);
				for (int i = 0; i < lst.size(); i++) {
					white_yt.add(lst.get(i).toString());
				}
				logger.info("== whitelist YT====" + white_yt.size());
				obj = null;
				lst = null;
				dao_YT = null;

				duplicate = 0;
				ytID = new HashMap<>();
				for (Iterator<Row> rowIterator = mySheet.rowIterator(); rowIterator.hasNext();) {
					aa = new ArrayList<>();
					save_status = null;
					for (Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator(); cellIterator
							.hasNext();) {
						aa.add(new DataFormatter().formatCellValue(cellIterator.next()));
					}
					// === whitelist check (social media and whitelist domain
					// =====
					if (aa.get(0).contains("instagram.com/p/")) {
						// instagram whitelist
						wList = whiteListInsta_Check(aa.get(8).trim());
						logger.info("white list instagram status..........." + wList);
					} else {
						// whitelist check
						wList = whiteList_Check(aa.get(4).trim());
						logger.info("white list status..........." + wList);
						if (wList.equalsIgnoreCase("0")) {
							// whitelist 2 check
							wList = whiteList2_Check(aa.get(0).trim());
						}
						logger.info("white list status...2nd time........" + wList);
					}
					if (aa.get(4).trim().equalsIgnoreCase("youtube.com")) {
						// youtube whitelist check
						wList = whiteListYT(aa.get(8).trim().replace("https://www.youtube.com/chid=", ""));
						logger.info("white list YT status.........." + wList);
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
							// ==== finding link type===
							if (aa.get(4).trim().equalsIgnoreCase("facebook.com")
									|| aa.get(4).trim().equalsIgnoreCase("instagram.com")
									|| aa.get(4).trim().equalsIgnoreCase("vk.com")
									|| aa.get(4).trim().equalsIgnoreCase("ok.ru")
									|| aa.get(4).trim().equalsIgnoreCase("twitter.com")) {
								cu.setLink_type("3");// linktype
							} else if (aa.get(4).trim().equalsIgnoreCase("youtube.com")) {
								cu.setLink_type("6"); // linktype
							} else {
								cu.setLink_type(aa.get(5).trim()); // linktype
							}
							cu.setFilter_new(aa.get(6).trim()); // datatype
							cu.setUser_id(uid);
							cu.setIs_Manual("0");
							cu.setC_new("0");
							cu.setQc_new("0");
							cu.setVerified(1);

							cu.setW_list(wList); // whiteList
							cu.setSend_crawl(greyList_Check(aa.get(4).trim())); // greyList
							
							if (redirect.trim().equalsIgnoreCase("dm")) {
								logger.info("=====dailymotion..........");
								if (aa.get(4).trim().equalsIgnoreCase("dailymotion.com")) {
									cu.setLink_logger_srclink(aa.get(8)); // infringing_link
									cu.setLink_logger(1);
									cu.setQc_new("1");
									cu.setC_new("1");
									cu.setW_list_perform(1);
									int pj = indexedSearch(linkupdate(aa.get(0).trim()), aa.get(1).trim(), 1,
											aa.get(4).trim(), indexPath);
									// logger.info("=======Lucene logger...
									// data.... " + pj);
									if (pj == 0) {
										try {
											// return_id = dao.saveData(cu);
											save_status = dao.saveData(cu);
											
											//---------------------------------------------
											 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
											 String dt = sdf.format(timestamp);
											 String proSql = "Update Project_info set last_updated_on='"+dt+"' where id="+Integer.parseInt(aa.get(1).trim());
											 dao.customUpdateProject(proSql);		 
											//---------------------------------------------
											
											if(aa.get(4).trim().equalsIgnoreCase("youtube.com")||aa.get(4).trim().equalsIgnoreCase("facebook.com")||aa.get(4).trim().equalsIgnoreCase("dailymotion.com")||aa.get(4).trim().equalsIgnoreCase("instagram.com")||aa.get(4).trim().equalsIgnoreCase("twitter.com"))
											{
												
												logger.info("screen shot code..............");
												if(aa.get(7).trim().equals("0"))
												{
													if(url2.size()>=100)
													{
														logger.info("URL For Screenshot..................................."+url2);
														screenUpload.setUrlList(url2);
														url2.clear();
													}
													url2.add(cu.getCrawle_url2());
											//ScreenShotDetails imageup =new ScreenShotDetails();
											//imageup.getURLDetails(cu);
												}
											} 
											
											if (save_status.equals("pradeep__ERROR")) {
												duplicate = duplicate + 1;
											} else {
												if (aa.get(4).trim().equalsIgnoreCase("dailymotion.com")) {
													int pj__c = indexedSearch_Two(linkupdate(aa.get(0).trim()),
															aa.get(1).trim(), "feedback@dailymotion.com", 0,
															indexPath2);
													if (pj__c == 0) {
														ue = new Url_email();
														ue.setWid("11");
														ue.setUrl(aa.get(0).trim());
														ue.setCrawle_url2_id(Integer.parseInt(save_status));
														ue.setDomain_name(aa.get(4).trim());
														ue.setProject_id(Integer.parseInt(aa.get(1).trim()));
														ue.setRenotification_id("0");
														ue.setEmail("feedback@dailymotion.com");
														ue.setDate_time(nowTime());
														ue.setEmail_type("2");
														ue.setUser_id(uid);
														ue.setEmail_error_new("0");
														ue.setQc_new(0);

														dao_ue.saveData_retuenID(ue);
														ue = null;
														// return_id__c = null;
													}
												}
											}

										} catch (Exception e) {

											e.printStackTrace();
											// duplicate = duplicate + 1;
										}
									} else {
										duplicate = duplicate + 1;
									}
								} else {
									statusUpdate = "wrong data entry!!!";
								}
								// file export setting
								if (!aa.get(7).toString().equals("0")) {
									file_data = true;
									/**
									 * file_data indicate that file will be
									 * export.
									 */
									ContentFilterData.add(aa.get(0) + ";;" + aa.get(7));
								}

							}

							else if (redirect.trim().equalsIgnoreCase("source")) {
								logger.info("=====Source..........");

								p = p + 1;
								q = q + 1;
								try {
									if (aa.get(4).trim().equalsIgnoreCase("dailymotion.com")) {
										if (p > 3) {
											statusUpdate = "wrong DailyMotion data entry!!!";
											break;
										}
									} else {
										try {
											if (aa.get(8).contains("https://") || aa.get(8).contains("http://")) {
												if (aa.get(8).contains("\"")) {
													errorCollection.put(aa.get(8),
															" Double Cotes \" in Infring link column..");
												} else {
													cu.setLink_logger_srclink(aa.get(8));
													cu.setLink_logger(1);

													if (directME.contains(aa.get(4).trim().toLowerCase()))

													{
														if (wList.equalsIgnoreCase("1")) {
															cu.setQc_new("0");
															cu.setTrack_user_id(0);
															cu.setW_list_perform(0);
														} else {
															cu.setQc_new("1");
															cu.setTrack_user_id(1);
															cu.setW_list_perform(1);
														}
													}

													int pj = indexedSearch(linkupdate(aa.get(0).trim()),
															aa.get(1).trim(), 1, aa.get(4).trim(), indexPath);

													// logger.info("=======Lucene_logger...data...."+pj);
													if (pj == 0) {
														// batchupload.add(cu);
														if (aa.get(4).trim().equalsIgnoreCase("ok.ru")) {
															cu.setQc_new("1");
															cu.setC_new("1");
															cu.setW_list_perform(1);
															save_status = dao.saveData(cu);
															//---------------------------------------------
															 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
															 String dt = sdf.format(timestamp);
															 String proSql = "Update Project_info set last_updated_on='"+dt+"' where id="+Integer.parseInt(aa.get(1).trim());
															 dao.customUpdateProject(proSql);		 
															//---------------------------------------------
															if (!save_status.equals("pradeep__ERROR")) {
																int pj__c = indexedSearch_Two(
																		linkupdate(aa.get(0).trim()), aa.get(1).trim(),
																		"copyright.support@odnoklassniki.ru", 0,
																		indexPath2);
																if (pj__c == 0) {
																	ue = new Url_email();
																	ue.setWid("11");
																	ue.setUrl(aa.get(0).trim());
																	ue.setCrawle_url2_id(Integer.parseInt(save_status));
																	ue.setDomain_name(aa.get(4).trim());
																	ue.setProject_id(
																			Integer.parseInt(aa.get(1).trim()));
																	ue.setRenotification_id("0");
																	ue.setEmail("copyright.support@odnoklassniki.ru");
																	ue.setDate_time(nowTime());
																	ue.setEmail_type("2");
																	ue.setUser_id(uid);
																	ue.setEmail_error_new("0");
																	ue.setQc_new(1);

																	dao_ue.saveData_retuenID(ue);
																	
																	ue = null;
																	// return_id__c
																	// = null;
																}
															}

														} else if (aa.get(4).trim().equalsIgnoreCase("torrentz2.eu")) {
															cu.setQc_new("1");
															cu.setC_new("1");
															cu.setW_list_perform(1);
															save_status = dao.saveData(cu);
															//---------------------------------------------
															 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
															 String dt = sdf.format(timestamp);
															 String proSql = "Update Project_info set last_updated_on='"+dt+"' where id="+Integer.parseInt(aa.get(1).trim());
															 dao.customUpdateProject(proSql);		 
															//---------------------------------------------
															if (!save_status.equals("pradeep__ERROR")) {
																int pj__c = indexedSearch_Two(
																		linkupdate(aa.get(0).trim()), aa.get(1).trim(),
																		"remove@torrentz2.eu", 0, indexPath2);
																if (pj__c == 0) {
																	ue = new Url_email();
																	ue.setWid("11");
																	ue.setUrl(aa.get(0).trim());
																	ue.setCrawle_url2_id(Integer.parseInt(save_status));
																	ue.setDomain_name(aa.get(4).trim());
																	ue.setProject_id(
																			Integer.parseInt(aa.get(1).trim()));
																	ue.setRenotification_id("0");
																	ue.setEmail("remove@torrentz2.eu");
																	ue.setDate_time(nowTime());
																	ue.setEmail_type("2");
																	ue.setUser_id(uid);
																	ue.setEmail_error_new("0");
																	ue.setQc_new(1);

																	dao_ue.saveData_retuenID(ue);
																	ue = null;
																	// return_id__c
																	// = null;
																}
															}

														} else {

															save_status = dao.saveData(cu);
															//---------------------------------------------
															 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
															 String dt = sdf.format(timestamp);
															 String proSql = "Update Project_info set last_updated_on='"+dt+"' where id="+Integer.parseInt(aa.get(1).trim());
															 dao.customUpdateProject(proSql);		 
															//---------------------------------------------
															if(aa.get(4).trim().equalsIgnoreCase("youtube.com")||aa.get(4).trim().equalsIgnoreCase("facebook.com")||aa.get(4).trim().equalsIgnoreCase("dailymotion.com")||aa.get(4).trim().equalsIgnoreCase("instagram.com")||aa.get(4).trim().equalsIgnoreCase("twitter.com"))
															{
																logger.info("screen shot code..............");
																if(aa.get(7).trim().equals("0"))
																{ 
																	if(url2.size()>=100)
																	{
																		logger.info("URL For Screenshot..................................."+url2);
																		screenUpload.setUrlList(url2);
																		url2.clear();
																	}
																	System.out.println("*************************************URL ADD*************************");
																	url2.add(cu.getCrawle_url2());
																	System.out.println("************************** "+url2);
															//ScreenShotDetails imageup =new ScreenShotDetails();
															//imageup.getURLDetails(cu);
																}
															} 

															// youtube_data_crawl......
															if (aa.get(4).trim().equalsIgnoreCase("youtube.com")) {

																if (ytID.size() >= 50) {
																	logger.info("--------- youtube call--------------");
																	yt_data.serverURLHeaderAuth(ytID);
																	ytID.clear();
																	Thread.sleep(2000);
																}

																ytLink = aa.get(0).replace(
																		"https://www.youtube.com/watch?v=", "");
																if (!save_status.equalsIgnoreCase("pradeep__ERROR")) {
																	ytID.put(ytLink.trim(),
																			Integer.parseInt(save_status));
																}

															}

														}
														if (save_status.equals("pradeep__ERROR")) {
															duplicate = duplicate + 1;
														}

													} else {
														duplicate = duplicate + 1;
													}
												}
											} else {
												errorCollection.put(aa.get(8),
														"  Http:// or Https:// missing in Infringing link..");
											}
										} catch (Exception e) {
											e.printStackTrace();
											if (q > 3) {
												// logger.info("source catch
												// found........");
												statusUpdate = "uploading Infringing file in source section!!!";
												break;
											}
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
									statusUpdate = "trying to insert wrong file in source!!!";
								}
								// file export setting
								if (!aa.get(7).toString().equals("0")) {
									file_data = true;
									/**
									 * file_data indicate that file will be
									 * export.
									 */

									ContentFilterData.add(aa.get(0) + ";;" + aa.get(7));
								}

							} else if (redirect.trim().equalsIgnoreCase("inf")) {
								logger.info("=====infringing...data.......");
								p = p + 1;
								q = q + 1;
								try {
									if (aa.get(4).trim().equalsIgnoreCase("dailymotion.com".trim())) {
										if (p > 3) {
											statusUpdate = "wrong DailyMotion data entry!!!";
											break;
										}
									} else {
										try {
											if (aa.get(8).contains("https://") || aa.get(8).contains("http://")) {
												if (q > 3) {
													statusUpdate = "uploading source file in Infringing section!!!";
													break;
												}
											}
											if (aa.get(8).length() <= 0) {
												int z = 1 / 0;
											}
										} catch (Exception e) {
											cu.setLink_logger(0);
											int pj = indexedSearch(linkupdate(aa.get(0).trim()), aa.get(1).trim(), 0,
													aa.get(4).trim(), indexPath);
											if (pj == 0) {

												if (directGT.contains(aa.get(4).trim().toLowerCase())) {
													cu.setQc_new("1");
													cu.setW_list_perform(1);
													cu.setTrack_user_id(1);
													// save_status=dao.saveData(cu);
												}
												save_status = dao.saveData(cu);
												//---------------------------------------------
												 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
												 String dt = sdf.format(timestamp);
												 String proSql = "Update Project_info set last_updated_on='"+dt+"' where id="+Integer.parseInt(aa.get(1).trim());
												 dao.customUpdateProject(proSql);		 
												//---------------------------------------------
												if (save_status.equals("pradeep__ERROR")) {
													duplicate = duplicate + 1;
												}
											} else {
												duplicate = duplicate + 1;
											}
										}
									}
								} catch (Exception e) {
									statusUpdate = "file error....!!!";
									e.printStackTrace();
								}
								// file export setting
								if (!aa.get(7).toString().equals("0")) {
									file_data = true;
									/**
									 * file_data indicate that file will be
									 * export.
									 */
									ContentFilterData.add(aa.get(0) + ";;" + aa.get(7));
								}

							}

						}
					} else {
						errorCollection.put(aa.get(0), "  Http:// or Https:// missing from link..");
					}

				}
				logger.info("...............................ScreenShotDetails method excecute...............................");
				//ScreenShotDetails screenUpload=new ScreenShotDetails();
				//logger.info("URL For Screenshot..................................."+url2);
				//screenUpload.setUrlList(url2);
				
					logger.info("URL For Screenshot..................................."+url2);
					screenUpload.setUrlList(url2);
					url2.clear();
				

				
				logger.info("data Successfully deploy");
				logger.info("data file location==" + fileToCreate.getAbsolutePath());
				logger.info("==== f i l e    d e l e t e = = = = = = = =" + fileToCreate.delete());

			} catch (IndexOutOfBoundsException e) {
				// e.printStackTrace();
				logger.error("array Index Out Of Bounds Exception occure.........");
				logger.info("data file location==" + fileToCreate.getAbsolutePath());
				logger.info("==== f i l e    d e l e t e  in c a t c h= = = = = = = =" + fileToCreate.delete());
			}

			catch (Exception e) {
				statusUpdate = " Your file is not correct.. please check your file that should be in .xls format...!!!";
				e.printStackTrace();
			} finally {
				// === youtube data crawl===
				if (ytID.size() > 0) {
					// yt.multipleVideoDetail(ytID);
					logger.info("--------- youtube call--------------");
					yt_data.serverURLHeaderAuth(ytID);

				}
				logger.info("total duplocate================" + duplicate);
				dao_011.customUpdateProject("update Productivity_user_wise set duplicate=duplicate+" + duplicate
						+ " where curr_date='" + nowTime2() + "' and user_id=" + uid);
				// fileToCreate.delete();
				logger.info("data Successfully deploy in fianlly");
				logger.info("data file location==" + fileToCreate.getAbsolutePath());
				logger.info("==== f i l e    d e l e t e  F i n a l  l y = = = = = = = =" + fileToCreate.delete());
				fileToCreate.delete();
				obj = null;
				lst = null;
				dao_grey = null;
				dao_white2 = null;
				dao_grey = null;
				white_list = null;
				white_two = null;
				dao_white_insta = null;
				white_list_insta = null;

				grey_list = null;
				save_status = null;
				wList = null;
				dao = null;
				dao_ue = null;
				cu = null;
				cu__next = null;
				ue = null;
				duplicate_check = null;
				previous_link = null;
				return_id = null;
				dao_011 = null;
				fileToCreate = null;
				batchupload = null;
				wb = null;
				mySheet = null;
				rowIter = null;
				session2 = null;
				factory = null;
				googleTracker = null;
				masterEmail = null;
				mailShoot = null;
				directGT = null;
				directME = null;
				directMS = null;
				prj = null;
				yt_data = null;
				ytID = null;
				ytLink = null;
				dao_YT = null;
				white_yt = null;
				System.gc();

				// export file code will be here

				if (file_data) {
					try {
						downloadCSVFileAction(uid);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				url2.clear();

				return SUCCESS;
			}
		}
	}

	boolean file_data = false;
	// Youtube yt = null;
	YT_data_extract yt_data = null;

	public String youtubeUploading() {
		url2=new ArrayList<String>();
		ScreenShotDetails screenUpload=new ScreenShotDetails();
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			yt_data = new YT_data_extract();
			dao_011 = (Productivity_user_wiseDao) factory.getBean("d27");
			dao = (Txn_tblDao) factory.getBean("d");
			prop = new Properties();
			configFile = "/details.properties";
			input = getClass().getResourceAsStream(configFile);
			try {
				prop.load(input);
				filePath = prop.getProperty("filepath");
				indexPath = prop.getProperty("indexPath1");
				indexPath2 = prop.getProperty("indexPath2");
			} catch (Exception e) {
			} finally {
				configFile = null;
				input = null;
				prop = null;
			}
			duplicate = 0;
			if (uploadFileFileName.trim().endsWith(",")) {
				uploadFileFileName = uploadFileFileName.substring(0, uploadFileFileName.length() - 1);
			}
			String pjj[] = uploadFileFileName.trim().split(",");

			logger.info(pjj.length + "==== array size...........");
			ytID = new HashMap<>();
			errorCollection = new HashMap<>();
			
			try {
				
				for (String ytlink : pjj) {

					if (ytlink.contains("https://www.youtube.com/watch?v=")) {

						int pj = indexedSearch(linkupdate(ytlink.trim()), String.valueOf(propertyName), 1,
								"youtube.com", indexPath);
						if (pj == 0) {
							cu = new Crawle_url2();
							cu.setCrawle_url2(ytlink.trim());
							cu.setProject_id(propertyName);
							cu.setDomain_name("youtube.com");
							cu.setLink_logger(1);
							cu.setLink_logger_srclink("https://www.youtube.com/");
							cu.setUser_id((int) session2.getAttribute("uid"));
							cu.setIs_Manual("0");
							cu.setC_new("1");
							cu.setVerified(1);
							cu.setQc_new("1");
							cu.setLink_type("6");
							cu.setW_list("0");
							cu.setW_list_perform(1);
							cu.setWid("3");// youtube_upload
							save_status = dao.saveData(cu);
							
							//---------------------------------------------
							 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							 String dt = sdf.format(timestamp);
							 String proSql = "Update Project_info set last_updated_on='"+dt+"' where id="+propertyName;
							 dao.customUpdateProject(proSql);		 
							//---------------------------------------------
							
							if(aa.get(4).trim().equalsIgnoreCase("youtube.com")||aa.get(4).trim().equalsIgnoreCase("facebook.com")||aa.get(4).trim().equalsIgnoreCase("dailymotion.com")||aa.get(4).trim().equalsIgnoreCase("instagram.com")||aa.get(4).trim().equalsIgnoreCase("twitter.com"))
							{
								logger.info("screen shot code..............");
								if(aa.get(7).trim().equals("0"))
								{
									if(url2.size()>=100)
									{
										logger.info("URL For Screenshot..................................."+url2);
										screenUpload.setUrlList(url2);
										url2.clear();
									}
									url2.add(cu.getCrawle_url2());
							//ScreenShotDetails imageup =new ScreenShotDetails();
							//imageup.getURLDetails(cu);
								}
							} 
							

							if (ytID.size() >= 50) {
								yt_data.serverURLHeaderAuth(ytID);
								ytID.clear();
								// Thread.sleep(2000);
							}

							ytLink = ytlink.replace("https://www.youtube.com/watch?v=", "");
							if (!save_status.equalsIgnoreCase("pradeep__ERROR")) {
								ytID.put(ytLink.trim(), Integer.parseInt(save_status));
							} else {
								duplicate = duplicate + 1;
								errorCollection.put(ytlink, "Duplicate entry");
							}

						} else {
							duplicate = duplicate + 1;
							errorCollection.put(ytlink, "Duplicate entry");
						}
					} else {
						errorCollection.put(ytlink, "wrong data input");
					}
				}
				// ytLink = ytlink.replace("https://www.youtube.com/watch?v=",
				// "");
				// if (!save_status.equalsIgnoreCase("pradeep__ERROR")) {
				// ytID.put(ytLink.trim(), Integer.parseInt(save_status));
				// }
				yt_data.serverURLHeaderAuth(ytID);
				dao_011.customUpdateProject("update Productivity_user_wise set duplicate=duplicate+" + duplicate
						+ " where curr_date='" + nowTime2() + "' and user_id=" + (int) session2.getAttribute("uid"));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				uploadFileFileName = null;
			}
			statusUpdate = "data Uploded..!!";
			return SUCCESS;
		}
	}

	FTPClient ftpclient = null;
	String ftp_user = null;
	String ftp_password = null;
	String ftp_server = null;
	String localFileLocaton = null;
	String ftpDataLocation = null;
	File oldFile = null;
	File newFile = null;
	FileInputStream fis = null;
	String path = null;
	String file_extension = null;
	int user_id, project_id;

	public String ScreenShotUpload_pre() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			return LOGIN;
		} else {
			return SUCCESS;
		}
	}

	public String ScreenShotUpload() {
		session2 = ServletActionContext.getRequest().getSession();
		// logger.info("-- session 2==" + session2);

		if (session2 == null || session2.getAttribute("login") == null) {
			// logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			logger.info("file path =====" + getLocalFileLocaton());
			logger.info("xlsx file path =====" + getUploadFile());
			boolean result;
			factory = ActionPerform.getFactory();
			cu_dao = (Crawle_url2Dao) factory.getBean("dash");
			prop = new Properties();
			configFile = "/details.properties";
			input = getClass().getResourceAsStream(configFile);
			errorCollection = new HashMap<>();
			try {

				prop.load(input);
				filePath = prop.getProperty("filepath");// screenshot_filepath
				ftp_user = prop.getProperty("ftpUser");
				ftp_password = prop.getProperty("ftpPasswd");
				ftp_server = prop.getProperty("ftpServer");
				ftpDataLocation = prop.getProperty("ftpDataLocation");
			} catch (Exception e) {
			} finally {
				configFile = null;
				input = null;
				prop = null;
			}
			logger.info("file path =====" + localFileLocaton);
			logger.info("xlsx file path =====" + uploadFileFileName);
			try {
				fileToCreate = new File(filePath,
						"screenShot___" + uploadFile.getName().concat("_").concat(uploadFileFileName));
				FileUtils.copyFile(uploadFile, fileToCreate);
				logger.info("file to create..... " + fileToCreate);
				wb = WorkbookFactory.create(fileToCreate);
				mySheet = wb.getSheetAt(0);
				// rowIter = mySheet.rowIterator();
				combined_link__c = "";// Youtube_URL
				duplicate = 0; // imageno
				total_link = 0; // crawle_url_id
				return_id = ""; // return value
				ww_lst = ""; // youtube_video_id
				indexPath = ""; // date

				ftpclient = new FTPClient();
				ftpclient.connect(ftp_server);
				result = ftpclient.login(ftp_user, ftp_password);
				logger.info("====ftp login==== " + result);
				for (Iterator<Row> rowIterator = mySheet.rowIterator(); rowIterator.hasNext();) {
					aa = new ArrayList<>();
					save_status = null;
					try {
						for (Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator(); cellIterator
								.hasNext();) {
							aa.add(new DataFormatter().formatCellValue(cellIterator.next()));
						}

						logger.info("====value1==== " + aa.get(0));
						logger.info("====value2=====" + aa.get(1));

						if (aa.get(0).trim().contains("https://www.youtube.com/watch?v=")) {
							ww_lst = aa.get(0).trim().replaceAll("https://www.youtube.com/watch?v=", "");

							lst = cu_dao
									.viewRecord("select cu.id, DATE_FORMAT(cu.created_on,'%d%b%Y')as dd, cu.project_id"
											+ ", cu.user_id from " + " Crawle_url2 cu where cu.crawle_url2='"
											+ aa.get(0).trim() + "'");
							for (int i = 0; i < lst.size(); i++) {
								Object[] obj = (Object[]) lst.get(i);
								total_link = (Integer) obj[0]; // crawle_url_id
								indexPath = obj[1].toString(); // date(18mar2018)
								project_id = (Integer) obj[2];
								user_id = (Integer) obj[3];
							}
							logger.info("====crawle_url_id==== " + total_link);
							logger.info("====date=====" + indexPath);

							// ========== rename file name

							try {
								oldFile = new File(localFileLocaton + "/" + aa.get(1).trim());
								logger.info("old file name====" + oldFile);
								file_extension = FilenameUtils.getExtension(localFileLocaton + "/" + aa.get(1).trim());
								logger.info("  file Extension......====" + file_extension);

								// new file pattern ==
								// 1) date
								// 2) user_id
								// 3) project_id
								// 4) screenshot_uploader_id
								// 5) crawl_url_id
								// 6) .fileExtension

								newFile = new File(localFileLocaton + "/" + indexPath + "_" + user_id + "_" + project_id
										+ "_" + (int) session2.getAttribute("uid") + "_" + total_link + "."
										+ file_extension);
								logger.info("new file name====" + newFile);
								if (oldFile.renameTo(newFile)) {
									logger.info("Rename successful");
								} else {
									errorCollection.put(aa.get(0), "Rename failed");
									logger.error("Rename failed");
								}
							} catch (Exception e) {
								errorCollection.put(aa.get(0), "error on file rename");
								e.printStackTrace();
							} finally {
								oldFile = null;
							}

							// ============= upload file
							logger.info("server file location ......." + ftpDataLocation);
							logger.info("result is......." + result);
							if (result == true) {
								try {
									logger.info("Logged in Successfully ");
									ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
									ftpclient.changeWorkingDirectory(ftpDataLocation + "/");
									logger.info("----ftp client----" + ftpclient);
									fis = new FileInputStream(oldFile); // change
																		// new
																		// to
																		// old

									// Upload file to the ftp server

									result = ftpclient.storeUniqueFile(newFile.getName(), fis);
									logger.info("File is upload result........." + result);
									if (result == true) {
										logger.info("File is uploaded successfully.");
										try {
											path = "http://" + ftp_server + ftpDataLocation + newFile.getName();
											logger.info("server location path=====" + path);
											// ======== path update process
											// ps = con.prepareStatement("update
											// crawle_url2 set image_path='" +
											// path + "'
											// where id=" + id);
											cu_dao.customUpdateProject("update Crawle_url2 set image_path='" + path
													+ "' where id=" + total_link + " and crawle_url2= '"
													+ aa.get(0).trim() + "'");

										} catch (Exception e) {
											e.printStackTrace();
											logger.info("file path not updated.");
											errorCollection.put(aa.get(0), "file path not updated.");
										}
									} else {
										logger.info("file not uploded in else");
										errorCollection.put(aa.get(0), "file not uploded in else");
									}
								} catch (Exception e) {
									logger.info("file not uploded in try catch block");
									errorCollection.put(aa.get(0), "file not uploded in catch ");
									e.printStackTrace();
								}

							} else {
								logger.info("FTP Login fail");

								errorCollection.put(aa.get(0), "FTP Login fail");

							}

						} else {
							logger.info("not a proper Youtube link");
							errorCollection.put(aa.get(0), "not a proper Youtube link");
						}
					} catch (Exception e) {
						logger.info("====array index out of bond error ==== ");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			} finally {

			}
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

	public String getLocalFileLocaton() {
		return localFileLocaton;
	}

	public void setLocalFileLocaton(String localFileLocaton) {
		this.localFileLocaton = localFileLocaton;
	}

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
										// System.out.println("--- index
										// size..."+pj__c);
										// System.out.println("-----url--------"+aa.get(1));
										// System.out.println("-----domain--------"+aa.get(2));
										// System.out.println("-----project
										// id--------"+aa.get(3));
										// System.out.println("-----email--------"+aa.get(4));
										// System.out.println("-----renotification
										// id--------"+aa.get(6));
										if (pj__c == 0) {

											// System.out.println(aa.get(0) +
											// ",,,,,,,pj,,,,,,," +
											// aa.get(0).length()
											// + "----------"
											// );

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
									} else {
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

					// errorCollection = null;
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

					// errorCollection = null;
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
		/*
		 * try {
		 * 
		 * // .println("=pp=" + ddomain.lastIndexOf("/"));
		 * 
		 * if (ddomain.startsWith("https:")) { domain__c =
		 * ddomain.replace("https://", ""); domain__c =
		 * domain__c.replace("www.", ""); // .println("domain===1..." +
		 * domain__c); if (domain__c.contains(":")) { String pj1[] =
		 * domain__c.split(":");
		 * 
		 * String pj[] = pj1[0].split("/"); domain__c = pj[0]; pj = null; pj1 =
		 * null; // .println("domain===4.1.." + domain__c); } else { String pj[]
		 * = domain__c.split("/"); domain__c = pj[0]; pj = null; //
		 * .println("domain===4.112.." + domain__c); }
		 * 
		 * } else if (ddomain.startsWith("http:")) { domain__c =
		 * ddomain.replace("http://", ""); domain__c = domain__c.replace("www.",
		 * ""); // .println("domain===2..." + domain__c); if
		 * (domain__c.contains(":")) { String pj1[] = domain__c.split(":");
		 * 
		 * String pj[] = pj1[0].split("/"); domain__c = pj[0]; pj = null; pj1 =
		 * null; // .println("domain===4.2.." + domain__c); } else { String pj[]
		 * = domain__c.split("/"); domain__c = pj[0]; pj = null; //
		 * .println("domain===4.113.." + domain__c); } } else if
		 * (ddomain.startsWith("//www.")) { domain__c =
		 * ddomain.replace("//www.", ""); domain__c = domain__c.replace("www.",
		 * ""); // .println("domain===2..." + domain__c); if
		 * (domain__c.contains(":")) { String pj1[] = domain__c.split(":");
		 * 
		 * String pj[] = pj1[0].split("/"); pj = null; pj1 = null; domain__c =
		 * pj[0]; // .println("domain===4.3.." + domain__c); } else { String
		 * pj[] = domain__c.split("/"); domain__c = pj[0]; pj = null; //
		 * .println("domain===4.113.." + domain__c); } } else if
		 * (ddomain.startsWith("www")) { // domain__c =
		 * ddomain.replace("//www.", ""); domain__c = ddomain.replace("www.",
		 * ""); // .println("domain===2..." + domain__c); if
		 * (domain__c.contains(":")) { String pj1[] = domain__c.split(":");
		 * 
		 * String pj[] = pj1[0].split("/"); domain__c = pj[0]; pj = null; pj1 =
		 * null; // .println("domain===4.33.." + domain__c); } else { String
		 * pj[] = domain__c.split("/"); domain__c = pj[0]; pj = null; //
		 * .println("domain===4.114.." + domain__c); } }
		 * 
		 * else if (!ddomain.startsWith("http:") || !ddomain.startsWith("htts:")
		 * || !ddomain.startsWith("www.") || !ddomain.startsWith("//www.")) {
		 * String pj[] = ddomain.split("/"); domain__c = pj[0]; pj = null; //
		 * .println("domain===4.114.." + domain__c); }
		 * 
		 * } catch (Exception e) { // e.printStackTrace(); logger.error(
		 * "find domain error... ", e); }
		 */
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
	 * document); writer.close(); logger.info(
	 * "====== data indexed successfully================"); } catch (Exception
	 * e) { e.printStackTrace(); // TODO: handle exception } finally { config =
	 * null; document = null; } return j; }
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
	 * document); writer.close(); logger.info(
	 * "====== data 2 indexed successfully================"); } catch (Exception
	 * e) { e.printStackTrace(); // TODO: handle exception } finally { config =
	 * null; document = null; } return j; }
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

	List<Crawle_url2> batchupload = null;
	String fileUpload = null;

	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String whiteList_Check(String domain__) {
		if (white_list.contains(domain__.trim())) {
			return "1";
		} else
			return "0";
	}

	public String whiteListInsta_Check(String domain__) {
		String pj[] = domain__.split(".com/");

		if (white_list_insta.contains(pj[1].replace("/","").trim())) {
			pj = null;
			return "1";
		} else {
			pj = null;
			return "0";
		}
	}

	public String whiteList2_Check(String domain__) {
		String ppj = "0";
		for (String p : white_two) {
			if (domain__.contains(p.trim())) {
				ppj = "1";
				break;
			}
		}
		return ppj;
	}

	public int greyList_Check(String domain__) {
		if (grey_list.contains(domain__.trim())) {
			return 1;
		} else
			return 0;
	}

	public String whiteListYT(String ch_id) {
		if (white_yt.contains(ch_id.trim())) {
			return "1";
		} else
			return "0";
	}

	String save_status = null;

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

	public String emailupload_pre() {
		return SUCCESS;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public int getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(int propertyName) {
		this.propertyName = propertyName;
	}

	private File uploadFile = null;
	private String uploadFileContentType = null;
	private String uploadFileFileName = null;
	HttpSession session2 = null;
	BeanFactory factory = null;
	InputStream input = null;
	String configFile = null;
	String filePath = null;
	Properties prop = null;
	List lst = null;
	String return_id = null;
	String ww_lst = null;
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
	String redirect = null;
	WhitelistDao dao_white = null;
	Whitelist_instagramDao dao_white_insta = null;
	Whitelist_twoDao dao_white2 = null;
	GreylistDao dao_grey = null;
	Whitelist_ytDao dao_YT = null;
	Object[] obj = null;
	Set<String> white_list = null;
	Set<String> white_yt = null;
	Set<String> white_list_insta = null;
	Set<String> white_two = null;
	String wList = null;
	Set<String> grey_list = null;
	String googleTracker = null;
	String masterEmail = null;
	String mailShoot = null;
	Set<String> directGT = null;
	Map<String, String> directMS = null;
	Set<String> directME = null;
	String prj[] = null;
	Map<String, Integer> ytID = null;
	int propertyName;
	String clientId;
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void downloadCSVFileAction(int uid) throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		String reportName = null;
		String outputString = null;
		Iterator<String> iter = null;
		QualityCheck qualityCheck = new QualityCheck();
		try {
			response.setContentType("text/csv");
			// response.setCharacterEncoding("UTF-8");
			reportName = "youtube_userid_" + uid + "_" + qualityCheck.nowTime1() + "_file.txt";
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);

			rows = new ArrayList<String>();

			// rows.add("you are using " + browsr + " browser");
			// rows.add("\n");
			// rows.add("\n");
			// rows.add(header);
			// rows.add("\n");
			for (String cr : ContentFilterData) {
				rows.add(cr);
				rows.add( System.lineSeparator());
			}
			rows.add( System.lineSeparator());
			rows.add( System.lineSeparator());
			rows.add( System.lineSeparator());
			iter = null;
			iter = rows.iterator();
			while (iter.hasNext()) {
				outputString = (String) iter.next();
				response.getWriter().print(outputString);
//				 response.getOutputStream().print(outputString);
				/**
				 * using getWriter() for prevent below error
				 * java.io.CharConversionException: Not an ISO 8859-1 character:
				 * ? outputstream do not convert some special characters
				 */
				outputString = null;
			}
			iter = null;
			// response.getOutputStream().flush();
			response.getWriter().flush();
			ContentFilterData = null;
			rows = null;
			iter = null;
		} catch (Exception e) {
			response.getWriter().flush();
			ContentFilterData = null;
			rows = null;
			e.printStackTrace();
		} finally {
			outputString = null;
			iter = null;
			rows = null;
			reportName = null;
			response = null;
		}
	}

	ArrayList<String> rows = null;
	ArrayList<String> ContentFilterData = null;

}
