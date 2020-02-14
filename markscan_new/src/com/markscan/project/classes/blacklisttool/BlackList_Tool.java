package com.markscan.project.classes.blacklisttool;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.BL_infringing_source;
import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Url_email;
import com.markscan.project.classes.enforcement.Enforcement;
import com.markscan.project.classes.fileupload.FileUpload;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.BL_infringing_sourceDao;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.GreylistDao;
import com.markscan.project.dao.Markscan_usersDao;
import com.markscan.project.dao.Txn_tblDao;
import com.markscan.project.dao.Url_emailDao;
import com.markscan.project.dao.WhitelistDao;
import com.markscan.project.dao.Whitelist_instagramDao;
import com.markscan.project.dao.Whitelist_twoDao;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.research.ws.wadl.Link;

/**
 * @author pradeep
 *
 */
public class BlackList_Tool extends ActionSupport {

	/**
	 * 
	 */
	public BlackList_Tool() {
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = Logger.getLogger(BlackList_Tool.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	private BL_infringing_sourceDao d1 = null;
	List lst = null;
	Enforcement e;
	private List<BL_infringing_source> listData = null;
	private Set<BL_infringing_source> listDataSet = null;
	BL_infringing_source bis = null;
	Object[] obj = null;
	private String id = null;

	public String blackListData_QC() {

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {

			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			e = new Enforcement();
			factory = LoginAndSession.getFactory();

			try {
				d1 = (BL_infringing_sourceDao) factory.getBean("d33");
				logger.error("if=====seng====" + d1);
				lst = d1.viewRecord01(
						"select bi.id, bi.source_link, bi.source_domain, bi.infringing_link, bi.infringing_link_by_date,"
								+ " bi.domain, pi.project_name, bi.projectid from BL_infringing_source bi,"
								+ "  Project_info pi " + " where  pi.id= bi.projectid and bi.userid = "
								+ session2.getAttribute("uid") + " and date(bi.infi_time) =date('" + e.nowTime()
								+ "') and bi.data_move_s=0 and  bi.data_move_i=0",
						100);
				listData = Collections.synchronizedList(new ArrayList<BL_infringing_source>());
				for (int i = 0; i < lst.size(); i++) {
					bis = new BL_infringing_source();
					obj = (Object[]) lst.get(i);
					bis.setId(obj[0].toString());

					try {
						bis.setSource_link(obj[1].toString());
					} catch (Exception e) {
						bis.setSource_link("");
					}
					try {
						bis.setSource_domain(obj[2].toString());
					} catch (Exception e) {
						bis.setSource_domain("");
					}

					bis.setInfringing_link(obj[3].toString());
					bis.setInfringing_link_by_date(obj[4].toString());
					bis.setDomain(obj[5].toString());
					bis.setIpaddress(obj[6].toString()); // project_name
					bis.setProjectid((Integer) obj[7]);
					listData.add(bis);
					obj = null;
					bis = null;

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
				d1 = null;
				obj = null;
				bis = null;
				factory = null;
				session2 = null;
				e = null;
			}
			return SUCCESS;
		}
	}

	public String qcUpdate() {
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			String updateQuery = "update BL_infringing_source set";
			try {
				d1 = (BL_infringing_sourceDao) factory.getBean("d33");
				if (url.trim().length() > 0) {
					updateQuery = updateQuery.concat(" source_link='" + url + "'");
				}
				if (domain.trim().length() > 0) {
					updateQuery = updateQuery.concat(" source_domain='" + domain + "'");
				}
				updateQuery = updateQuery.concat(" projectid='" + proj_id + "' where id ='" + invalidQuery + "'");
				d1.customUpdateProject(updateQuery);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				factory = null;
				updateQuery = null;
				session2 = null;
				d1 = null;
			}
			return SUCCESS;
		}
	}

	public String qcInvalid() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println("invalid query...." + invalidQuery);
			factory = LoginAndSession.getFactory();
			try {
				d1 = (BL_infringing_sourceDao) factory.getBean("d33");
				d1.customUpdateProject("delate from  BL_infringing_source where id='" + invalidQuery + "'");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				factory = null;
				session2 = null;
				d1 = null;
			}
			return SUCCESS;
		}
	}

	Crawle_url2 cu = null;
	Crawle_url2Dao d2 = null;
	WhitelistDao dao_white = null;
	Whitelist_twoDao dao_white2 = null;
	GreylistDao dao_grey = null;
	Url_emailDao dao_ue = null;
	Set<String> white_list = null;
	Set<String> white_two = null;
	Set<String> grey_list = null;
	Set<String> white_list_insta = null;
	String wList = null;
	FileUpload fileUpload = null;
	String indexPath, indexPath2 = null;
	String configFile = null;
	String save_status = null;
	Properties prop = null;
	InputStream input = null;
	Txn_tblDao dao = null;
	Url_email ue = null;
	Whitelist_instagramDao dao_white_insta = null;

	@SuppressWarnings("finally")
	public String blackListData_QCperform() {
		fileUpload = new FileUpload();
		String pj1[] = id.split(",");
		String ids = "'";
		for (String pp : pj1) {
			ids = ids + pp.trim() + "','";
		}
		ids = ids.substring(0, ids.length() - 2);

		logger.info("--- id print---" + ids);

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println("invalid query...." + invalidQuery);
			factory = LoginAndSession.getFactory();

			dao = (Txn_tblDao) factory.getBean("d");
			d1 = (BL_infringing_sourceDao) factory.getBean("d33");
			d2 = (Crawle_url2Dao) factory.getBean("dash");
			dao_white = (WhitelistDao) factory.getBean("d20");
			dao_white2 = (Whitelist_twoDao) factory.getBean("d18");
			dao_grey = (GreylistDao) factory.getBean("d19");
			dao_ue = (Url_emailDao) factory.getBean("d22");
			dao_white_insta = (Whitelist_instagramDao) factory.getBean("d34");

			white_list = new HashSet<String>();
			white_two = new HashSet<String>();
			grey_list = new HashSet<String>();
			white_list_insta = new HashSet<>();

			prop = new Properties();
			configFile = "/details.properties";
			input = getClass().getResourceAsStream(configFile);
			try {
				prop.load(input);
				// filePath = prop.getProperty("filepath");
				indexPath = prop.getProperty("indexPath1");
				indexPath2 = prop.getProperty("indexPath2");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				configFile = null;
				input = null;
				prop = null;
			}

			try {

				lst = dao_white_insta.viewRecord("select taken_by_id from Whitelist_instagram");
				for (int i = 0; i < lst.size(); i++) {
					// obj = (Object[]) lst.get(i);
					white_list_insta.add(lst.get(i).toString());
				}
				logger.info("== white instagram list size====" + white_list_insta.size());
				obj = null;
				lst = null;
				dao_white_insta = null;

				lst = dao_white.viewRecord("select domain_name from Whitelist");
				for (int i = 0; i < lst.size(); i++) {
					// obj = (Object[]) lst.get(i);
					white_list.add(lst.get(i).toString());
				}
				// logger.info("== white list size====" + white_list.size());
				obj = null;
				lst = null;
				dao_white = null;

				lst = dao_white2.viewRecord("select link from Whitelist_two");
				for (int i = 0; i < lst.size(); i++) {

					white_two.add(lst.get(i).toString());
				}
				// logger.info("== white list 2 size====" + white_two.size());
				obj = null;
				lst = null;
				dao_white2 = null;

				lst = dao_grey.viewRecord("select domain from Greylist");
				for (int i = 0; i < lst.size(); i++) {

					grey_list.add(lst.get(i).toString());
				}
				// logger.info("== grey list size====" + grey_list.size());
				obj = null;
				lst = null;
				dao_grey = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				obj = null;
				lst = null;
				dao_white2 = null;
				dao_grey = null;
				dao_white = null;
			}

			try {

				lst = d1.viewRecord(
						"select bi.id, bi.source_link, bi.source_domain, bi.infringing_link, bi.infringing_link_by_date,"
								+ " bi.domain, pi.project_name, bi.projectid,bi.search_keyword from "
								+ " BL_infringing_source bi, "
								+ " Project_info pi where pi.id= bi.projectid and bi.id in(" + ids + ")");
				listDataSet = new HashSet<BL_infringing_source>();
				logger.info("------------ total data size....." + lst.size());

				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);
					try {
						bis = new BL_infringing_source();

						bis.setId(obj[0].toString());
						bis.setInfringing_link_by_date(obj[4].toString());
						bis.setDomain(obj[5].toString());
						bis.setIpaddress(obj[6].toString()); // project_name
						bis.setProjectid((Integer) obj[7]);
						bis.setSource_domain(obj[6].toString().trim()); // note1
						bis.setSearch_keyword(obj[8].toString().trim());// note2
						bis.setI_ipaddress(wList); // whiteList
						bis.setRow_in_use(greyList_Check(obj[5].toString().trim())); // greylist

						listDataSet.add(bis);
						if (obj[1].toString() != null) {

							if (obj[1].toString().trim().contains("instagram.com/p/")) {
								wList = whiteListInsta_Check(obj[1].toString().trim());
								logger.info("white list instagram status..........." + wList);
							} else {
								try {
									wList = whiteList_Check(obj[5].toString().trim());
								} catch (Exception e) {
									e.printStackTrace();
								}
								logger.info("white list status..........." + wList);
								if (wList.equalsIgnoreCase("0")) {

									wList = whiteList2_Check(obj[1].toString().trim());
								}
								logger.info("white list status...2nd time........" + wList);
							}

							if (obj[1].toString().contains("https://") || obj[1].toString().contains("http://")) {
								if (obj[1].toString().contains("\"")) {
									// errorCollection.put(aa.get(0), " Double
									// Cotes
									// \"
									// in your link..");
								} else {

									cu = new Crawle_url2();
									cu.setCrawle_url2(obj[1].toString().trim()); // craule_url2
									cu.setProject_id((Integer) obj[7]); // project_id
									cu.setNote1(obj[6].toString().trim()); // note1
									cu.setNote2(obj[8].toString().trim()); // note2
									cu.setDomain_name(obj[2].toString().trim()); // domain_name
									cu.setLink_type("7"); // linktype
									cu.setFilter_new("1"); // datatype
									cu.setUser_id((int) session2.getAttribute("uid"));
									cu.setIs_Manual("0");
									cu.setC_new("0");
									cu.setQc_new("0");
									cu.setVerified(1);

									cu.setW_list(wList); // whiteList
									cu.setSend_crawl(greyList_Check(obj[2].toString().trim())); // greyList
									cu.setLink_logger_srclink(obj[4].toString()); // infringing
									// link
									cu.setLink_logger(1);

									// daily motion check
									if (obj[5].toString().trim().equalsIgnoreCase("dailymotion.com")) {

										cu.setQc_new("1");
										cu.setC_new("1");
										cu.setW_list_perform(1);
										// indexedSearch(link,project_id,1,domain_name,indexPath)
										int pj = fileUpload.indexedSearch(
												fileUpload.linkupdate(obj[1].toString().trim()), obj[7].toString(), 1,
												obj[2].toString().trim(), indexPath);
										if (pj == 0) {

											try {
												save_status = dao.saveData(cu);

												if (!save_status.equals("pradeep__ERROR")) {

													int pj__c = fileUpload.indexedSearch_Two(
															fileUpload.linkupdate(obj[1].toString().trim()),
															obj[7].toString(), "feedback@dailymotion.com", 0,
															indexPath2);
													if (pj__c == 0) {
														ue = new Url_email();
														ue.setWid("11");
														ue.setUrl(obj[1].toString().trim());
														ue.setCrawle_url2_id(Integer.parseInt(save_status));
														ue.setDomain_name(obj[5].toString().trim());
														ue.setProject_id((Integer) obj[7]);
														ue.setRenotification_id("0");
														ue.setEmail("feedback@dailymotion.com");
														ue.setDate_time(fileUpload.nowTime());
														ue.setEmail_type("2");
														ue.setUser_id((int) session2.getAttribute("uid"));
														ue.setEmail_error_new("0");
														ue.setQc_new(1);

														dao_ue.saveData_retuenID(ue);

														ue = null;
														// return_id__c = null;
													}
												}
												d1.customUpdateProject(
														"update BL_infringing_source set qc=1, data_move_s=1 where id='"
																+ obj[0].toString().trim() + "' ");
											} catch (Exception e) {
												// TODO: handle exception
												e.printStackTrace();
											}

										}

									} else {
										cu.setQc_new("0");
										cu.setC_new("0");
										cu.setW_list_perform(0);
										logger.info("-----link------" + obj[1].toString());
										// logger.info("-------project
										// id----"+obj[7].toString());
										// logger.info("------domain
										// name-----"+obj[2].toString());
										// logger.info("------index
										// path-----"+indexPath);

										// indexedSearch(link__c,project_Id__c,linklogger__c,domin__c,path)

										logger.info("------link update-----"
												+ fileUpload.linkupdate(obj[1].toString().trim()));

										int pj = fileUpload.indexedSearch(
												fileUpload.linkupdate(obj[1].toString().trim()), obj[7].toString(), 1,
												obj[2].toString().trim(), indexPath);
										if (pj == 0) {
											dao.saveData(cu);
											d1.customUpdateProject(
													"update BL_infringing_source set qc=1, data_move_s=1 where id='"
															+ obj[0].toString().trim() + "' ");
										}

									}
								}
							}
						}
					} catch (NullPointerException e) {
						logger.info("=== null pointer exception=====");
						d1.customUpdateProject(
								"update BL_infringing_source set qc=1 where id='" + obj[0].toString().trim() + "' ");
					}
					obj = null;
					bis = null;
					cu = null;
				}

				// infringing save
				uniqINF = new HashSet<>();
				updateIDS = "";
				for (BL_infringing_source blis : listDataSet) {

					mj = null;
					mj = blis.getProjectid() + "-" + blis.getInfringing_link_by_date();

					// System.err.println("-----"+uniqINF.contains(mj));

					if (uniqINF.contains(mj) == false) {
						uniqINF.add(mj);
						cu = new Crawle_url2();

						cu.setCrawle_url2(blis.getInfringing_link_by_date()); // craule_url2
						cu.setProject_id(blis.getProjectid()); // project_id
						cu.setNote1(blis.getIpaddress()); // note1
						cu.setNote2(blis.getSearch_keyword()); // note2
						cu.setDomain_name(blis.getDomain()); // domain_name
						cu.setLink_type("7"); // linktype
						cu.setFilter_new("1"); // datatype
						cu.setUser_id((int) session2.getAttribute("uid"));
						cu.setIs_Manual("0");
						cu.setC_new("0");
						cu.setQc_new("0");
						cu.setVerified(1);

						cu.setW_list(wList); // whiteList
						cu.setSend_crawl(greyList_Check(blis.getDomain().trim())); // greyList
						// link
						cu.setLink_logger(0);
						cu.setQc_new("0");
						cu.setC_new("0");
						cu.setW_list_perform(0);

						int pj = fileUpload.indexedSearch(
								fileUpload.linkupdate(blis.getInfringing_link_by_date().trim()),
								String.valueOf(blis.getProjectid()), 0, blis.getDomain().trim(), indexPath);
						if (pj == 0) {
							dao.saveData(cu);
							d1.customUpdateProject("update BL_infringing_source set qc=1, data_move_i=1 where id='"
									+ blis.getId() + "' ");
						}
					} else {
						updateIDS = updateIDS + ",'" + blis.getId() + "'";

					}
					cu = null;

				}
				if (updateIDS.startsWith(",")) {
					updateIDS = updateIDS.substring(1, updateIDS.length());
				}
				if (updateIDS.length() > 5) {
					d1.customUpdateProject(
							"update BL_infringing_source set qc=1, data_move_i=1 where id in (" + updateIDS + ") ");
				}
				prd = 1;
				// update table_status
				blackListData_QC();
			} catch (Exception e) {

				e.printStackTrace();
				prd = 0;
			} finally {

				obj = null;
				bis = null;
				cu = null;
				save_status = null;
				cu = null;
				d2 = null;
				dao_white = null;
				dao_white2 = null;
				dao_grey = null;
				dao_ue = null;
				white_list = null;
				white_two = null;
				grey_list = null;
				wList = null;
				fileUpload = null;
				indexPath = null;
				indexPath2 = null;
				configFile = null;
				save_status = null;
				dao_white_insta = null;
				prop = null;
				input = null;
				dao = null;
				ue = null;
				uniqINF = null;
				mj = null;
				updateIDS = null;
				session2 = null;
				factory = null;

				if (prd == 1) {
					return SUCCESS;
				} else
					return ERROR;
			}

		}

	}

	Set<String> uniqINF = null;
	String mj = null;
	String updateIDS = null;
	int prd = 0;

	// public String blackListData_UPLOAD() {
	// }
	//

	List<Markscan_users> listDataUser = null;
	Markscan_users url2 = null;
	Markscan_usersDao dao_user = null;

	public String blackListData_ExportPre() {

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			try {
				dao_user = (Markscan_usersDao) factory.getBean("d9");
				lst = dao_user.viewRecord("select id, name from Markscan_users where status=1 ");
				logger.info(".......pradeep........" + lst.size());
				listDataUser = Collections.synchronizedList(new ArrayList<Markscan_users>());

				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_users();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					listDataUser.add(url2);
					url2 = null;
					obj = null;
				}
				dao_user = null;
				lst = null;
				factory = null;

			} catch (Exception e) {
				logger.error("project type data get ", e);

				lst = null;
				return ERROR;
			} finally {
				dao_user = null;
				lst = null;
				factory = null;
				url2 = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	String err_MSG = null;

	public String blackListData_Export() {
		logger.info("---- dtype-----"+d_type);
		
		String exportQuery = "select bi.id, bi.source_link, bi.source_domain, bi.infringing_link, bi.infringing_link_by_date,"
				+ " bi.domain, bi.projectid, bi.userid, bi.infi_time, bi.data_move_s, bi.data_move_i , pi.project_name"
				+ "  from  BL_infringing_source bi   , Project_info pi where pi.id= bi.projectid and  ";
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if========='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			d1 = (BL_infringing_sourceDao) factory.getBean("d33");
			try {

				if (d_type == 1) {
					exportQuery = exportQuery.concat("data_move_s= " + 0 + " and ");
				} else if (d_type == 0) {
				}

				if (startdate.length() > 3) {
					exportQuery = exportQuery.concat("date(infi_time)='" + startdate + "' and ");
				} else {
					exportQuery = exportQuery.concat("date(infi_time)='" + fileUpload.nowTime() + "' and ");
				}
				if (projecttype > 1) {
					exportQuery = exportQuery.concat("userid=" + projecttype + " ");
				} else {
					err_MSG = "user id not selected....";
					return SUCCESS;
				}

				lst = d1.viewRecord(exportQuery);
				logger.info("=== list size..." + lst.size());
				listData = Collections.synchronizedList(new ArrayList<BL_infringing_source>());
				for (int i = 0; i < lst.size(); i++) {
					bis = new BL_infringing_source();
					obj = (Object[]) lst.get(i);
					bis.setId(obj[0].toString());

					try {
						bis.setSource_link(obj[1].toString());
					} catch (Exception e) {
						bis.setSource_link("");
					}
					try {
						bis.setSource_domain(obj[2].toString());
					} catch (Exception e) {
						bis.setSource_domain("");
					}

					bis.setInfi_time(obj[8].toString());
					bis.setInfringing_link(obj[3].toString());
					bis.setInfringing_link_by_date(obj[4].toString());
					bis.setDomain(obj[5].toString());
					bis.setIpaddress(obj[6].toString()); // project_name
					
//					logger.info("---  id==="+obj[0].toString());
//					logger.info("--- source_link==="+obj[1].toString());
//					logger.info("--- source_domain id==="+obj[2].toString());
//					logger.info("--- infringing_link id==="+obj[3].toString());
//					
//					logger.info("--- infringing_link_by_date id==="+obj[4].toString());
//					logger.info("--- domain id==="+obj[5].toString());
//					logger.info("--- projectid id==="+obj[6].toString());
//					logger.info("--- userid id==="+(Integer) obj[7]);
//					logger.info("--- infi_time id==="+obj[8].toString());
//					logger.info("--- data_move_s id==="+(Integer) obj[9]);
//					logger.info("--- data_move_i id==="+(Integer) obj[10]);
//					logger.info("--- project iname==="+obj[11].toString());
					
					bis.setProjectid((Integer) obj[7]);
					bis.setUserid(projecttype);
					bis.setData_move_s((Integer) obj[9]);
					bis.setData_move_i((Integer) obj[10]);
					bis.setI_ipaddress(obj[11].toString());
					listData.add(bis);
					obj = null;
					bis = null;

				}

				blackListData_ExportPre();
				downloadCSVFileAction();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				obj = null;
				bis = null;
				factory = null;
				session2 = null;
				startdate = null;
			}
			return SUCCESS;
		}
	}

	String reportName = null;
	List<String> rows = null;
	Iterator<String> iter = null;
	String outputString = null;

	public void downloadCSVFileAction() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();

		try {
			response.setContentType("text/csv");
			response.setCharacterEncoding("UTF-8");
			reportName = "blacklist_data__file.csv";
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);

			rows = new ArrayList<String>();
			rows.add("if Source upload =1 & infringing upload =1 ");
			rows.add("\n");
			rows.add("data already upload by system itself ");
			rows.add("\n");
			rows.add("\n");
			rows.add("id,Infringing Link,Infringing Domain,2nd Infringing Link,Source Link,Source Domain,Date, User ID,"
					+ " Source Upload, Infringing Upload,Project ID, Project name");
			rows.add("\n");
			for (BL_infringing_source cr : listData) {
				rows.add("\"" + cr.getId() + "\",\"" + cr.getInfringing_link_by_date() + "\",\"" + cr.getDomain()
						+ "\",\"" + cr.getInfringing_link() + "\",\"" + cr.getSource_link() + "\",\""
						+ cr.getSource_domain() + "\",\"" + cr.getInfi_time() + "\",\"" + cr.getUserid() + "\" ,\""
						+ cr.getData_move_s() + "\",\"" + cr.getData_move_i() + "\",\"" + cr.getIpaddress() + "\",\"" + cr.getI_ipaddress() + "\" ");
				rows.add("\n");
			}

			iter = rows.iterator();
			while (iter.hasNext()) {
				outputString = iter.next();
				byte[] bytes = outputString.getBytes("UTF-8");

				response.getWriter().print(outputString);

				outputString = null;
			}

			// response.getOutputStream().flush();
			response.getWriter().flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listData = null;
			lst = null;
			response = null;
			reportName = null;
			rows = null;
			iter = null;
			outputString = null;
		}
	}

	String url, domain;
	int proj_id;
	int projecttype;
	int datatype;
	int d_type;
	String startdate = null;

	private String invalidQuery; //

	public int getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getD_type() {
		return d_type;
	}

	public void setD_type(int d_type) {
		this.d_type = d_type;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getInvalidQuery() {
		return invalidQuery;
	}

	public void setInvalidQuery(String invalidQuery) {
		this.invalidQuery = invalidQuery;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}

	public List<BL_infringing_source> getListData() {
		return listData;
	}

	public void setListData(List<BL_infringing_source> listData) {
		this.listData = listData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Markscan_users> getListDataUser() {
		return listDataUser;
	}

	public void setListDataUser(List<Markscan_users> listDataUser) {
		this.listDataUser = listDataUser;
	}

	public String whiteList_Check(String domain__) {
		if (white_list.contains(domain__.trim())) {
			return "1";
		} else
			return "0";
	}

	public String whiteListInsta_Check(String domain__) {
		String pj[] = domain__.split("taken-by=");

		if (white_list_insta.contains(pj[1].trim())) {
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

}
