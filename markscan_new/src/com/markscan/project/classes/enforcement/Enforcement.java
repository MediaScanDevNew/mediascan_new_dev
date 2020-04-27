/**
 * 
 */
package com.markscan.project.classes.enforcement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Enforcement_mail_id;
import com.markscan.project.beans.Mail_shoot_data;
import com.markscan.project.beans.Master_email;
import com.markscan.project.beans.Module_wise_email_template;
import com.markscan.project.beans.Url_email;
import com.markscan.project.beans.Url_email__c;
import com.markscan.project.beans.Url_email_edit;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.Enforcement_mail_idDao;
import com.markscan.project.dao.Mail_shoot_dataDao;
import com.markscan.project.dao.Master_emailDao;
import com.markscan.project.dao.Module_wise_email_templateDao;
import com.markscan.project.dao.Module_wise_mail_sendDao;
import com.markscan.project.dao.Url_emailDao;
import com.markscan.project.dao.Url_email_editDao;
import com.markscan.project.dao.Url_email_qcDao;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author pradeep 2 jan 2017
 */
public class Enforcement extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Enforcement() {
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = Logger.getLogger(Enforcement.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	List lst1 = null;
	List lst2 = null;
	Object[] obj = null;
	private List<Url_email> listData = null;
	private List<Mail_shoot_data> url_listData = null;

	public String emailQCPre() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Url_email url2 = null;
			try {
				dao = (Url_emailDao) factory.getBean("d22");
				lst = dao.viewRecord(
						"select ue.id, ue.domain_name, ue.url, ue.email , pi.project_name from  Url_email ue, Project_info pi "
								+ ", Url_email_qc ueq where pi.id= ue.project_id and ueq.url_email_id=ue.id and  ue.qc_new = 0 "
								+ " and ue.send = 0 and ueq.invalid=0 ");
				listData = Collections.synchronizedList(new ArrayList<Url_email>());
				for (int i = 0; i < lst.size(); i++) {
					url2 = new Url_email();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setDomain_name((String) obj[1]);
					url2.setUrl((String) obj[2]);
					url2.setEmail((String) obj[3]);
					url2.setEmail_error_new((String) obj[4]); // project name
					listData.add(url2);
					obj = null;
					url2 = null;
				}
				lst = null;
				dao = null;

			} catch (Exception e) {
				logger.error("error @ emailqc pre ..." + e);
				return ERROR;
			} finally {
				lst = null;
				factory = null;
				session2 = null;
				dao = null;
			}
			return SUCCESS;

		}
	}

	public String emailQCEdit() {
		String updateUrl_emal = "";
		String updatedField = "";
		Url_email_edit uee = null;
		logger.info(url + "...'" + mail + "......" + domain + ".........." + invalidQuery);
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			try {
				dao = (Url_emailDao) factory.getBean("d22");
				dao1 = (Url_email_qcDao) factory.getBean("d23");
				dao2 = (Url_email_editDao) factory.getBean("d24");
				updateUrl_emal = "update Url_email set ";
				if (url.trim().length() > 0) {
					updateUrl_emal = updateUrl_emal.concat(" url='" + url + "'");
					updatedField = updatedField.concat("url");
				}
				if (mail.trim().length() > 0) {
					updateUrl_emal = updateUrl_emal.concat(" ,email= '" + mail + "'");
					updatedField = updatedField.concat(" , mail");
				}
				if (domain.trim().length() > 0) {
					updateUrl_emal = updateUrl_emal.concat("  ,domain_name='" + domain + "'");
					updatedField = updatedField.concat(", domain");
				}

				updateUrl_emal = updateUrl_emal.concat("   where id=" + invalidQuery);
				// System.out.println("updateUrl_emal......................." +
				// updateUrl_emal);
				dao.customUpdateProject(updateUrl_emal);
				dao1.customUpdateProject("update Url_email_qc set edit=1, edit_by= "
						+ (Integer) session2.getAttribute("uid") + "" + "  , edit_field='" + updatedField
						+ "' , edit_date='" + nowTime() + "' where url_email_id=" + invalidQuery);
				uee = new Url_email_edit();
				uee.setEdit_column(updatedField);
				uee.setUrl_email_id(invalidQuery);
				uee.setUser_id((Integer) session2.getAttribute("uid"));
				uee.setEdit_value(url + "," + mail + "," + domain);
				dao2.saveData(uee);
				updateUrl_emal = null;
				updatedField = null;
				dao = null;
				dao1 = null;
				dao2 = null;
				uee = null;
			} catch (Exception e) {
				logger.error("error @ url_email update    " + e);
				return ERROR;
			} finally {
				dao = null;
				dao1 = null;
				dao2 = null;
				uee = null;
				updateUrl_emal = null;
				updatedField = null;
				factory = null;
				session2 = null;
			}

			return SUCCESS;
		}
	}

	public String emailQCinvalid() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println("invalid query...." + invalidQuery);
			factory = LoginAndSession.getFactory();
			try {
				dao = (Url_emailDao) factory.getBean("d22");
				dao1 = (Url_email_qcDao) factory.getBean("d23");
				dao.customUpdateProject("update Url_email set qc_new=9 where id=" + invalidQuery);
				dao1.customUpdateProject(
						"update Url_email_qc set invalid=1 , invalid_by= " + (Integer) session2.getAttribute("uid") + ""
								+ " , invalid_date= '" + nowTime() + "' where url_email_id= " + invalidQuery);
				// dummyMsg = "deleted..";

			} catch (Exception e) {
				logger.error("error @ invalid mail link    " + e);
				// dummyMsg = " data not deleted..";
				return ERROR;
			} finally {
				dao = null;
				dao1 = null;
				factory = null;
				session2 = null;
			}

			return SUCCESS;
		}
	}

	public String emailQC() {
		System.out.println("qc.. link......" + id);
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			try {
				dao = (Url_emailDao) factory.getBean("d22");
				dao1 = (Url_email_qcDao) factory.getBean("d23");
				dao.customUpdateProject("update Url_email set qc_new=1 where id in (" + id + ")");
				dao1.customUpdateProject(
						"update Url_email_qc set email_qc=1 , email_qc_by= " + (Integer) session2.getAttribute("uid")
								+ "" + " , email_qc_time= '" + nowTime() + "' where url_email_id in ( " + id + ")");
				dao = null;
				dao1 = null;
			} catch (Exception e) {
				logger.error("error @ email qc..." + e);
				return ERROR;
			} finally {
				dao = null;
				dao1 = null;
				factory = null;
				session2 = null;
			}
		}
		emailQCPre();
		return SUCCESS;
	}

	/*
	 * mail shoot code start....
	 */

	public String findModuleEmail() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Module_wise_email_template url2 = null;
			Enforcement_mail_id url = null;
			try {
				dao3 = (Module_wise_email_templateDao) factory.getBean("d14");
				dao5 = (Enforcement_mail_idDao) factory.getBean("d25");
				lst = dao3.viewRecord("select mwet.id, mwet.module, mwet.template, mwet.subject from "
						+ " Module_wise_email_template mwet where mwet.id>20");
				moduleListData = Collections.synchronizedList(new ArrayList<Module_wise_email_template>());
				for (int i = 0; i < lst.size(); i++) {
					url2 = new Module_wise_email_template();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setModule((String) obj[1]);
					url2.setTemplate((String) obj[2]);
					url2.setSubject((String) obj[3]);
					moduleListData.add(url2);
					obj = null;
					url2 = null;
				}
				lst = dao5.viewRecord("select emi.id, emi.mail, emi.passwd from Enforcement_mail_id emi ");
				emailListData = Collections.synchronizedList(new ArrayList<Enforcement_mail_id>());
				for (int i = 0; i < lst.size(); i++) {
					url = new Enforcement_mail_id();
					obj = (Object[]) lst.get(i);
					url.setId((Integer) obj[0]);
					url.setMail((String) obj[1]);
					url.setPasswd((String) obj[2]);
					emailListData.add(url);
					obj = null;
					url = null;
				}
			} catch (Exception e) {
				logger.error("error @ loding email and module details...." + e);
				return ERROR;
			} finally {
				dao3 = null;
				url = null;
				url2 = null;
				dao5 = null;
				factory = null;
				session2 = null;
				lst = null;
			}
		}

		return SUCCESS;
	}

	// String clientList = null;

	String query4projectid = null;
	String uniqProjectID = null;
	String uniqDomain = null;
	Set<String> uniqMail = null;
	int e_type__;

	public String findModuleEmail_details() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();

			if (module_id == 27) {
				query4projectid = "SELECT ue.id,ue.date_time,ue.email ,ue.url,ue.crawle_url2_id ,pi.file_attach_link, "
						+ " pi.actual_hosted_site, ue.domain_name,pi.project_name, cm.client_address,mpt.name, "
						+ " cm.client_subject,ue.project_id,ue.isp_ipaddress, pi.channel_name, ue.email_type,cm.client_name FROM Url_email ue, "
						+ " Project_info pi, Client_master cm , Markscan_projecttype mpt  where pi.id = ue.project_id "
						+ " and cm.id = pi.client_type and mpt.id = pi.project_type and ue.send = 0 and ue.qc_new = 1 "
						+ " and ue.email_type =1 ORDER BY  project_id,domain_name ";
			} else {
				query4projectid = "SELECT ue.id,ue.date_time,ue.email ,ue.url,ue.crawle_url2_id ,pi.file_attach_link, "
						+ " pi.actual_hosted_site, ue.domain_name,pi.project_name, cm.client_address,mpt.name, "
						+ " cm.client_subject,ue.project_id,ue.isp_ipaddress, pi.channel_name, ue.email_type,cm.client_name FROM Url_email ue, "
						+ " Project_info pi, Client_master cm , Markscan_projecttype mpt  where pi.id = ue.project_id "
						+ " and cm.id = pi.client_type and mpt.id = pi.project_type and ue.send = 0 and ue.qc_new = 1 "
						+ " and cm.email_module =" + module_id
						+ " and ue.email_type =2 ORDER BY  project_id,domain_name ";
			}

			if (adv_c_notice > 0) {
				query4projectid = "SELECT ue.id,ue.date_time,ue.email ,ue.url,ue.crawle_url2_id ,pi.file_attach_link, "
						+ " pi.actual_hosted_site, ue.domain_name,pi.project_name, cm.client_address,mpt.name, "
						+ " cm.client_subject,ue.project_id,ue.isp_ipaddress, pi.channel_name, ue.email_type,cm.client_name FROM Url_email ue, "
						+ " Project_info pi, Client_master cm , Markscan_projecttype mpt  where pi.id = ue.project_id "
						+ " and cm.id = pi.client_type and mpt.id = pi.project_type and ue.send = 0 and ue.qc_new = 1 "
						+ " and ue.email_type =3 ORDER BY  project_id,domain_name ";
			}

			/*
			 * query4projectid =
			 * "SELECT ue.id,ue.date_time,ue.email ,ue.url,ue.crawle_url2_id ,pi.file_attach_link, "
			 * +
			 * " pi.actual_hosted_site, ue.domain_name,pi.project_name,cm.client_address,mpt.name, "
			 * +
			 * " cm.client_subject,ue.project_id,ue.isp_ipaddress, pi.channel_name, ue.email_type FROM Url_email ue, "
			 * +
			 * " Project_info pi, Client_master cm , Markscan_projecttype mpt  where pi.id = ue.project_id "
			 * +
			 * " and cm.id = pi.client_type and mpt.id = pi.project_type and ue.send = 0 and ue.qc_new = 1 "
			 * + " and cm.email_module =" + module_id +
			 * " ORDER BY  project_id,domain_name ";
			 */
			try {

				// fetching data from url_email table
				dao = (Url_emailDao) factory.getBean("d22");
				lst = dao.viewRecord(query4projectid, 100000);

				try {
					url_email_data_load = new HashMap<>();

					for (int i = 0; i < lst.size(); i++) {
						obj = (Object[]) lst.get(i);
						url_email_id__c = (Integer) obj[0];
						dateTime = obj[1].toString();
						email_addrress = obj[2].toString();
						url = obj[3].toString();
						crwle_url_id = (Integer) obj[4];
						if (obj[5] == null) {
							attachmentName = "";
						} else {
							attachmentName = obj[5].toString();
						}
						if (obj[6] == null) {
							actual_hosted_site = "";
						} else {
							actual_hosted_site = obj[6].toString();
						}
						domain = obj[7].toString();
						projectName = obj[8].toString();
						if (obj[9] == null) {
							clientAddress = "";
						} else {
							clientAddress = obj[9].toString();
						}
						projectType = obj[10].toString(); // provide string (tv
															// content)
						if (obj[11] == null) {
							clientSubject = "";
						} else {
							clientSubject = obj[11].toString();
						}
						projectId = (Integer) obj[12];
						if (obj[13] == null) {
							ipAddress = "";
						} else {
							ipAddress = obj[13].toString();
						}
						if (obj[14] == null) {
							channel_name = "";
						} else {
							channel_name = obj[14].toString();
						}
						if (obj[16] == null) {
							clientName = "";
						} else {
							clientName = obj[16].toString();
						}
						e_type__ = Integer.parseInt(obj[15].toString());

						if (prevProjectId == -1 || prevProjectId != projectId) {
							url_email_data = new ArrayList<>();
							url_email_data_load.put(projectId, url_email_data);
							prevProjectId = projectId;
							projectChanged = true;
						}

						if (domain_prev == null || !domain_prev.equalsIgnoreCase(domain) || projectChanged) {
							Url_email__c bean = new Url_email__c();
							date_TimeList = new ArrayList<>();
							urlList = new ArrayList<>();
							url_email_id = new ArrayList<>();
							crawle_url_id_List = new ArrayList<>();
							domainList = new ArrayList<>();
							projectNameList = new ArrayList<>();
							clientAddressList = new ArrayList<>();
							// projectTypeList = new ArrayList<>();
							clientSubjectList = new ArrayList<>();
							ipAddressList = new ArrayList<>();
							channel_name_List = new ArrayList<>();
							// uniq_Domain= new HashSet<>(); // single doamin
							// (unique)

							url_email_id.add(url_email_id__c);
							crawle_url_id_List.add(crwle_url_id);
							domainList.add(domain);
							urlList.add(url);
							projectNameList.add(projectName);
							clientAddressList.add(clientAddress);
							projectNameList.add(projectType);
							clientSubjectList.add(clientSubject);
							ipAddressList.add(ipAddress);
							channel_name_List.add(channel_name);
							date_TimeList.add(dateTime);
							// uniq_Domain.addAll(domainList);
							bean.setUrl_email_id(url_email_id);
							bean.setSendEmail(email_addrress);
							bean.setCrawle_url_Id(crawle_url_id_List);
							bean.setDateTime(date_TimeList);
							bean.setAttachmentName(attachmentName);
							bean.setActualHostedSite(actual_hosted_site);
							bean.setUrlGroup(urlList);
							bean.setProjectName(projectName);
							bean.setClientAddress(clientAddress);
							bean.setProjectType(projectType);
							bean.setClientSubject(clientSubject);
							bean.setDomainGroup(domainList);
							bean.setIpAddress(ipAddress);
							bean.setChannel_name(channel_name);
							bean.setProjcetId(projectId);
                            bean.setClientName(clientName);
							domain_prev = domain;
							url_email_data.add(bean);
							projectChanged = false;

						} else {
							urlList.add(url);
							crawle_url_id_List.add(crwle_url_id);
							url_email_id.add(url_email_id__c);
							domainList.add(domain);

						}
						dateTime = null;
						date_TimeList = null;
						projectNameList = null;
						clientAddressList = null;
						// projectTypeList = new ArrayList<>();
						clientSubjectList = null;
						ipAddressList = null;
						channel_name_List = null;
						obj = null;
						url = null;
					}

					/*
					 * === (test) data load in new table============
					 */
					Mail_shoot_data msd = null;
					try {

						ddd01 = (Mail_shoot_dataDao) factory.getBean("d26");
						for (Map.Entry<Integer, ArrayList<Url_email__c>> entry : url_email_data_load.entrySet()) {
							// Integer key = entry.getKey();
							// ArrayList<Url_email__c> value = entry.getValue();
							for (Url_email__c aa : entry.getValue()) {
								msd = new Mail_shoot_data();
								uniq_Domain = new HashSet<>();
								msd.setActual_hosted_site(aa.getActualHostedSite());
								msd.setAttachment_name(aa.getAttachmentName());
								msd.setChannel_name(aa.getChannel_name());
								msd.setClient_address(aa.getClientAddress());
								if (e_type__ == 1) {// 1=for_android_mobile_plateform
									// msd.setEmail_module(27);
									module_id = 27;
								}
								if (e_type__ == 3) {
//									msd.setEmail_module(adv_c_notice);
									module_id = adv_c_notice;
								}
								msd.setEmail_module(module_id);

								// msd.setEmail_type( aa.getEmailType().trim());
								msd.setIp_address(aa.getIpAddress());
								msd.setProject_id(aa.getProjcetId());
								msd.setProject_name(aa.getProjectName());
								// msd.setProject_type(aa.getProjectType());
								msd.setClientSubject(aa.getClientSubject());
								msd.setEmail_address(aa.getSendEmail());
								msd.setUser_id((Integer) session2.getAttribute("uid"));
								msd.setDate_time(nowTime());
								kk = "";
								for (Integer __Crawle_url_Id : aa.getCrawle_url_Id()) {
									kk = kk + "," + __Crawle_url_Id;
								}
								kk = kk.trim().substring(1, kk.length());
								msd.setCrawle_url_Id(kk);
								uniq_Domain.addAll(aa.getDomainGroup());
								kk = "";
								for (String __uniqDomain : uniq_Domain) {
									kk = kk + "," + __uniqDomain;
								}
								kk = kk.trim().substring(1, kk.length());
								msd.setDomain(kk);
								msd.setNotice_id(aa.getUrl_email_id().get(0)); // notice
																				// id
								kk = "";
								for (Integer __Url_email_id : aa.getUrl_email_id()) {
									kk = kk + "," + __Url_email_id;
								}
								kk = kk.trim().substring(1, kk.length());
								msd.setUrl_email_id_group(kk);
								kk = "";
								for (String __UrlGroup : aa.getUrlGroup()) {
									kk = kk + " <br> " + __UrlGroup;
								}
								msd.setUrl_group(kk.trim());
								msd.setProject_type(aa.getProjectType());
								msd.setClient_name(aa.getClientName());
								kk = null;
								uniq_Domain = null;
								ddd01.saveData(msd);
								msd = null;
							}
							logger.info("one complete................................");
						}
						logger.info("outer loop complete................................");
					} catch (Exception ee) {
						ee.printStackTrace();
						logger.error("error @ upload data on mail send data table ....." + ee);
					} finally {
						ddd01 = null;
						uniq_Domain = null;
						msd = null;
						kk = null;
						url_email_data_load = null;
					}

					/*
					 * ==== data display on html from mail_shoot data
					 * table.....========
					 */
					Mail_shoot_data msd__c = null;
					try {
						mailShootData = Collections.synchronizedList(new ArrayList<Mail_shoot_data>());
						ddd01 = (Mail_shoot_dataDao) factory.getBean("d26");
						lst = dao.viewRecord(
								"select msd.id,msd.notice_id,msd.project_name,msd.project_id,msd.email_address, "
										+ " msd.domain, msd.url_group, msd.channel_name from  Mail_shoot_data msd where "
										+ " msd.email_module=" + module_id);

						for (int i = 0; i < lst.size(); i++) {
							msd__c = new Mail_shoot_data();
							obj = (Object[]) lst.get(i);
							msd__c.setId((Integer) obj[0]);
							msd__c.setNotice_id((Integer) obj[1]);
							msd__c.setProject_name(obj[2].toString());
							msd__c.setProject_id((Integer) obj[3]);
							msd__c.setEmail_address(obj[4].toString());
							msd__c.setDomain(obj[5].toString());
							if (obj[6].toString().length() > 5000) {
								msd__c.setUrl_group(obj[6].toString().substring(0, 2000).concat("<br> ...[More data]"));
							} else {
								msd__c.setUrl_group(obj[6].toString());
							}
							msd__c.setChannel_name(obj[7].toString());

							mailShootData.add(msd__c);
							obj = null;
							msd__c = null;
						}

						lst = null;
					} catch (Exception e) {
						logger.error("error @ load mail shoot data ");
					} finally {
						msd__c = null;
						lst = null;
						ddd01 = null;
						obj = null;
					}

					logger.info("finally complete..... ho  gya ...");
					// System.out.println("finally complete..... ho gya ...");
					// System.out.println("~~~~~dataProjectWise!!!!!!!!!!=" +
					// url_email_data_load);

				} catch (Exception e) {
					e.printStackTrace();
					// System.out.println("internal data load error....");
					// e.printStackTrace();
					logger.error("internal data load error...." + e);
				} finally {
					date_TimeList = null;
					urlList = null;
					url_email_id = null;
					crawle_url_id_List = null;
					domainList = null;
					projectNameList = null;
					clientAddressList = null;
					// projectTypeList = null;
					clientSubjectList = null;
					ipAddressList = null;
					channel_name_List = null;
					dateTime = null;
					email_addrress = null;
					url = null;
					attachmentName = null;
					actual_hosted_site = null;
					domain = null;
					projectName = null;
					clientAddress = null;
					projectType = null;
					clientSubject = null;
					ipAddress = null;
					channel_name = null;
					uniq_Domain = null;
					url_email_data_load = null;
				}

			} catch (Exception e) {
				// e.printStackTrace();
				logger.error("External data load error...." + e);
				return ERROR;
			} finally {
				url_email_data_load = null;
				uniq_Domain = null;
				kk = null;
				ddd01 = null;
				date_TimeList = null;
				urlList = null;
				url_email_id = null;
				crawle_url_id_List = null;
				domainList = null;
				projectNameList = null;
				clientAddressList = null;
				// projectTypeList = null;
				clientSubjectList = null;
				ipAddressList = null;
				channel_name_List = null;
				dateTime = null;
				email_addrress = null;
				url = null;
				attachmentName = null;
				actual_hosted_site = null;
				domain = null;
				projectName = null;
				clientAddress = null;
				projectType = null;
				clientSubject = null;
				clientName=null;
				ipAddress = null;
				channel_name = null;
				uniq_Domain = null;
				session2 = null;
				factory = null;
				System.gc();
			}

		}
		return SUCCESS;
	}

	String subject_line = null;

	public String email_ShootWith_details() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			String pjj[] = null;
			String mailshoot_id[] = null;
			try {
				pjj = stringresult.trim().split(",");
				// System.out.println("============ppj==============length==============="
				// + pjj.length);
				dao = (Url_emailDao) factory.getBean("d22");
				dao1 = (Url_email_qcDao) factory.getBean("d23");
				dao3 = (Module_wise_email_templateDao) factory.getBean("d14");
				dao5 = (Enforcement_mail_idDao) factory.getBean("d25");
				dao_cu = (Crawle_url2Dao) factory.getBean("dash");
				// dao6 = (Master_email_trackerDao) factory.getBean("d27");
				module_detail = new ArrayList<>();
				email_detail = new ArrayList<>();
				lst = dao3.viewRecord("select mwet.template, mwet.subject,mwet.module, mwet.template_name, mwet.id "
						+ " from  Module_wise_email_template mwet where mwet.id=" + module_id);
				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);

					module_detail.add(0, obj[0].toString()); // template path
					module_detail.add(1, obj[1].toString()); // subject line
					module_detail.add(2, obj[2].toString()); // template detail
					module_detail.add(3, obj[3].toString()); // template name
					module_detail.add(4, obj[4].toString()); // template id
					obj = null;
				}
				lst = dao5.viewRecord("select emi.mail, emi.passwd, emi.smtp, emi.port from "
						+ " Enforcement_mail_id emi where emi.id=" + email_id);
				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);

					email_detail.add(0, obj[0].toString()); // email id
					email_detail.add(1, obj[1].toString()); // email password
					email_detail.add(2, obj[2].toString()); // smtp address
					email_detail.add(3, obj[3].toString()); // port
					obj = null;
				}
				// Mail_shoot_data msd__c = null;
				for (String aa1 : pjj) {
					// System.out.println("data with is, notice id........" +
					// aa1.trim());
					mailshoot_id = aa1.split("<pj>");
					// System.out.println("--------------mailshoot_id
					// length------" + mailshoot_id.length);
					ddd01 = (Mail_shoot_dataDao) factory.getBean("d26");
					lst = ddd01.viewRecord("select msd.url_email_id_group,msd.project_name, "
							+ " msd.project_id,msd.attachment_name,msd.actual_hosted_site,msd.email_address, "
							+ " msd.clientSubject,msd.client_address,msd.ip_address,msd.channel_name,msd.domain, "
							+ " msd.url_group,msd.crawle_url_Id, msd.client_address, msd.project_type,msd.client_name from "
							+ " Mail_shoot_data msd where msd.id=" + mailshoot_id[0] + " and msd.notice_id="
							+ mailshoot_id[1] + " and msd.email_module=" + module_id);

					for (int i = 0; i < lst.size(); i++) {
						// msd__c = new Mail_shoot_data();
						obj = (Object[]) lst.get(i);
						url_email_id_group__c = obj[0].toString();
						projectName = (obj[1].toString());
						attachmentName = (obj[3].toString());
						actual_hosted_site = (obj[4].toString());
						email_address__c = (obj[5].toString());
						ip_address__c = obj[8].toString();
						channel_name = obj[9].toString();
						domain_prev = obj[10].toString();
						url_group__c = obj[11].toString();
						clientAddress = obj[13].toString();
						projectType = obj[14].toString();
						clientName =obj[15].toString();
						// crawle_url_Id__c = obj[12].toString();
						obj = null;
						// url_email_id_group__c=null;
					}
					mail_tempale_body = template_creation(projectName, url_group__c, attachmentName, actual_hosted_site,
							email_detail.get(0), module_detail.get(0), module_detail.get(3), ip_address__c,
							clientAddress, channel_name, projectType,clientName);
					if (mail_tempale_body != null) {

						subject_line = module_detail.get(1);
						subject_line = subject_line.replace("${notice_id}$", mailshoot_id[1]);
						subject_line = subject_line.replace("${domain_name}$", domain_prev);
						subject_line = subject_line.replace("${project_name}$", projectName);
						subject_line = subject_line.replace("${channel_name}$", channel_name);

						if (mail_send(email_detail.get(0), email_detail.get(1), mail_tempale_body, email_address__c,
								subject_line, url_email_id_group__c, email_detail.get(2), email_detail.get(3)))// if
						// mailsend=
						// true
						{
							// System.out.println("after mail send .....");
							String ppj[] = url_email_id_group__c.trim().split(",");

							try {

								dataforupdate = ",";
								j = 0;
								idList_URLmail = new ArrayList<>();
								for (String pj : ppj) {

									j++;
									if (j >= 1000) {
										//
										if (dataforupdate.startsWith(",")) {
											dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
										}

										if (dataforupdate.endsWith(",")) {
											dataforupdate = (String) dataforupdate.substring(0,
													dataforupdate.length() - 1);
										}
										idList_URLmail.add(dataforupdate);
										j = 1;
										dataforupdate = ",";
									}

									dataforupdate = dataforupdate + pj + ",";
								}

								if (dataforupdate.startsWith(",")) {
									dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
								}
								if (dataforupdate.endsWith(",")) {
									dataforupdate = (String) dataforupdate.substring(0, dataforupdate.length() - 1);
								}
								idList_URLmail.add(dataforupdate);

								for (String dj : idList_URLmail) {
									dao.customUpdateProject("update Url_email set send=1 , send_email_time='"
											+ nowTime() + "' where id in (" + dj + ")");

									dao1.customUpdateProject("update Url_email_qc set email_send_by_email_id="
											+ email_id + " , email_send_by=" + (Integer) session2.getAttribute("uid")
											+ " , email_send_time='" + nowTime() + "' , choose_email_template='"
											+ module_detail.get(4) + "', notice_id= "
											+ Integer.parseInt(mailshoot_id[1]) + " ," + " configure_mail_id="
											+ email_id + " , notice_date='" + nowTime() + "' where url_email_id in ("
											+ dj + ")");

								}

								idList_URLmail = null;

								ddd01.customUpdateProject("delete from Mail_shoot_data where id=" + mailshoot_id[0]
										+ " " + " and notice_id=" + mailshoot_id[1]);

							} catch (Exception e1) {
								logger.error("error on mail send ... " + e1);
								e1.printStackTrace();
							}
							ppj = null;
						} else {
							String ppj[] = url_email_id_group__c.trim().split(",");
							try {

								idList_URLmail = new ArrayList<>();
								dataforupdate = ",";
								j = 0;

								for (String pj : ppj) {
									j++;
									if (j >= 1000) {
										// System.out.println("entry in
										// if....");
										if (dataforupdate.startsWith(",")) {
											dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
										}
										// System.out.println("propertyids....11....."
										// +
										// propertyids);
										if (dataforupdate.endsWith(",")) {
											dataforupdate = (String) dataforupdate.substring(0,
													dataforupdate.length() - 1);
										}
										idList_URLmail.add(dataforupdate);
										j = 1;
										dataforupdate = ",";
									}

									dataforupdate = dataforupdate + pj + ",";
								}

								if (dataforupdate.startsWith(",")) {
									dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
								}
								if (dataforupdate.endsWith(",")) {
									dataforupdate = (String) dataforupdate.substring(0, dataforupdate.length() - 1);
								}
								idList_URLmail.add(dataforupdate);

								for (String dj : idList_URLmail) {

									dao.customUpdateProject(
											"update Url_email set email_error='internet issue' , email_error_time='"
													+ nowTime() + "' where id in (" + dj + ")");
								}

								idList_URLmail = null;

							} catch (Exception ee) {
								ee.printStackTrace();
							}
						}

					}
					idList_URLmail = null;
					// break;
					mailshoot_id = null;
				}
				// return ERROR;
			} catch (Exception e) {
				logger.error("error on mail send last catch... " + e);
				e.printStackTrace();
			} finally {
				idList_URLmail = null;
				url_email_id_group__c = null;
				projectName = null;
				attachmentName = null;
				actual_hosted_site = null;
				email_address__c = null;
				ip_address__c = null;
				channel_name = null;
				domain_prev = null;
				url_group__c = null;
				ddd01 = null;
				dao1 = null;
				dao2 = null;
				dao3 = null;
				dao4 = null;
				dao5 = null;
				// dao6 = null;
				lst = null;
				subject_line = null;
				dataforupdate = null;
				factory = null;
				session2 = null;
				pjj = null;
				mailshoot_id = null;
			}
		}
		findModuleEmail();
		return SUCCESS;
	}

	List<String> idList_URLmail = null;
	Configuration cfg = null;
	Template template = null;
	Map<String, Object> data = null;
	StringWriter stringWriter = null;
	String dataforupdate = null;
	int j = 0;

	public String template_creation(String __project_name, String __url_group, String __attachmentName,
			String __actual_hosted_site, String __email_id, String __template_path, String __module_name,
			String __ip_address, String __client_address, String __channel_name, String __project_type, String _client_name) {
		String template_page = null;
		cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File(__template_path));
			template = cfg.getTemplate(__module_name);
			data = new HashMap<String, Object>();
			data.put("project_Name", __project_name);
			data.put("urlList_Value", __url_group);
			data.put("channel_name", __channel_name);
			data.put("attachment_Name", __attachmentName);
			data.put("actual_Host_Link", __actual_hosted_site);
			data.put("shoot_mail_id", __email_id);
			data.put("isp_address", __ip_address);
			data.put("clientAddress", __client_address);
			data.put("project_Type", __project_type);
			data.put("clientName", _client_name);

			stringWriter = new StringWriter();
			template.process(data, stringWriter);
			template_page = stringWriter.toString();
		} catch (Exception e) {
			logger.error("error @ template creation error..." + e);
			e.printStackTrace();
		} finally {
			cfg = null;
			template = null;
			data = null;
			stringWriter = null;
		}

		return template_page;

	}

	Properties props = null;

	public boolean mail_send(final String mail_id, final String mail_passwrd, String mail_body_template,
			String to_email_address, String email_subject, String __url_email_id__c, String smtp__c, String port__c) {

		// System.out.println("=== smtop==="+smtp__c);
		// System.out.println("=== smtop==="+port__c);

		boolean b = false;
		try {
			props = new Properties();
			props.put("mail.smtp.host", smtp__c);
			props.put("mail.smtp.socketFactory.port", port__c);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", port__c);

			/**
			 * === mail send ...
			 */
			mailsession = Session.getInstance(props, new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(mail_id, mail_passwrd);
				}
			});

			message = new MimeMessage(mailsession);
			message.setFrom(new InternetAddress(mail_id));
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse("pjoshi@markscan.co.in"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email_address));

			// message.setRecipients(Message.RecipientType.CC,
			// InternetAddress.parse(cc));

			// message.setRecipients(Message.RecipientType.BCC,
			// InternetAddress.parse(bcc));

			message.setSubject(email_subject);

			message.setContent(mail_body_template, "text/html");

			// Send message
			if (netIsAvailable()) {
				Transport.send(message);
				logger.info("Sent message successfully....");
				b = true;
			} else {
				b = false;
			}
			// System.out.println("Sent message successfully....");

			message = null;
			props = null;
		} catch (Exception e) {
			logger.error("mail shoot error pj....");
			// e.printStackTrace();
			dao = (Url_emailDao) factory.getBean("d22");
			String ppj[] = __url_email_id__c.split(",");
			if (ppj.length < 1000) {
				dao.customUpdateProject("update Url_email set email_error='" + e + "' , email_error_time='" + nowTime()
						+ "' where id in (" + __url_email_id__c + ")");
			} else {
				for (String ii : ppj) {
					dao.customUpdateProject("update Url_email set email_error='" + e + "' , email_error_time='"
							+ nowTime() + "' where id =" + Integer.parseInt(ii.trim()));
				}
			}
			error_msg = e.getMessage();
			ppj = null;
			b = false;
		} finally {
			message = null;
			mailsession = null;
			props = null;
		}
		return b;
	}

	/*
	 * mail shoot varibale define
	 */
	Session mailsession = null;
	Message message = null;
	private String kk = null;
	List<String> module_detail = null;
	List<String> email_detail = null;
	private Map<Integer, ArrayList<Url_email__c>> url_email_data_load = null;
	private String domain_prev, email_addrress = null;
	private ArrayList<Url_email__c> url_email_data = null;
	private String dateTime, attachmentName, actual_hosted_site, projectName, clientAddress, projectType, clientSubject,
			ipAddress, channel_name, url_email_id_group__c, email_address__c, ip_address__c, url_group__c, clientName,
			mail_tempale_body = null;
	private int crwle_url_id;
	private int url_email_id__c;
	private ArrayList<String> date_TimeList = null;
	private ArrayList<String> urlList = null;
	private ArrayList<Integer> crawle_url_id_List = null;
	private ArrayList<Integer> url_email_id = null;
	private ArrayList<String> domainList = null;
	private ArrayList<String> projectNameList = null;
	private ArrayList<String> clientAddressList = null;
	// private ArrayList<String> projectTypeList = null;
	private ArrayList<String> clientSubjectList = null;
	private ArrayList<String> ipAddressList = null;
	private ArrayList<String> channel_name_List = null;
	private boolean projectChanged = false;
	private int projectId = -2;
	private int prevProjectId = -1;
	private String stringresult = null;
	private Set<String> uniq_Domain = null;
	private List<Mail_shoot_data> mailShootData = null;
	String error_msg = null;
	/*
	 * mail shoot code end....
	 */
	String time__c = null;
	DateFormat dateFormat = null;
	Date date = null;

	public String nowTime() {
		try {
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			date = new Date();
			// System.out.println(dateFormat.format(date));
			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("date format error ...", e);
		} finally {
			dateFormat = null;
			date = null;
		}
		return time__c;
	}

	private boolean netIsAvailable() {
		try {
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();

			return true;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}
	}

	public String start() {
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

	String indexPath = null;
	String configFile = null;
	String directory = null;
	String templatename = null;
	InputStream input = null;
	Analyzer analyzer2 = null;
	QueryParser queryParser = null;
	Query q = null;
	int hitsPerPage = 10;
	IndexReader reader = null;
	IndexSearcher searcher = null;
	TermQuery tq = null;
	TopDocs results = null;

	public String EnforcementContentReplacement_search() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Mail_shoot_data url2 = null;
			Properties prop = new Properties();
			try {
				dao = (Url_emailDao) factory.getBean("d22");
				if (url.contains("'")) {
					url = url.replaceAll("'", "''");
				}

				lst = dao.viewRecord("select ue.id, ue.url, ue.email, ue.send,ue.send_email_time, "
						+ " ue.domain_name, ue.email_error,ue.qc_new, pi.project_name, cm.client_name, "
						+ " mu.name, ue.renotification_id  from Url_email ue, Project_info pi, "
						+ " Client_master cm , Markscan_users mu where pi.id=ue.project_id and "
						+ " cm.id=pi.client_type and mu.id=ue.user_id and ue.url='" + url + "'");
				// System.out.println("lst .. size..."+lst.size());

				url_listData = new ArrayList<>();

				if (lst.size() < 1) {

					try {
						configFile = "/details.properties";
						input = getClass().getResourceAsStream(configFile);
						prop.load(input);
						// db = prop.getProperty("database");
						// filePath = prop.getProperty("filePath");
						indexPath = prop.getProperty("indexPath2");
						logger.info("indexpath==== " + indexPath);
					} catch (Exception e) {
						// e.printStackTrace();
						logger.error("file read error .. ", e);
					} finally {
						prop = null;
						configFile = null;
						input = null;
					}
					Document d = null;
					try {

						analyzer2 = new StandardAnalyzer();
						queryParser = new QueryParser("url", analyzer2);
						q = queryParser.parse(queryParser.escape(linkupdate(url.trim())));
						// System.out.println(q);
						int hitsPerPage = 10;

						reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
						searcher = new IndexSearcher(reader);

						tq = new TermQuery(new Term("url", linkupdate(url.trim()).toString()));

						TopDocs results = searcher.search(tq, 5);
						ScoreDoc[] hits = results.scoreDocs;
						// display result
						logger.info("data Found from url_email " + hits.length + " hits.");
						for (int i = 0; i < hits.length; ++i) {
							int docId = hits[i].doc;
							d = searcher.doc(docId);
							logger.info("Found a record id:[" + d.get("id") + "] \t crawle_url2:[" + d.get("url")
									+ "] \t domain_name:[" + d.get("domain_name") + "] \t created_on:["
									+ d.get("created_on") + "] \t   project_id:[" + d.get("project_id")
									+ "] \t  user_id:[" + d.get("user_id") + "] \t  email:[" + d.get("email")
									+ "]\t crawle_url2_id:[" + d.get("crawle_url2_id") + "] \t renotification_id:["
									+ d.get("renotification_id") + "] ");

							url2 = new Mail_shoot_data();
							url2.setId(Integer.parseInt(d.get("id").trim())); // id
							url2.setUrl_group(d.get("url")); // url
							url2.setEmail_address(d.get("email")); // email
							// url2.setEmail_module((Integer) obj[3]); //
							// send(int)
							url2.setDate_time(d.get("created_on")); // project
																	// name
							url2.setDomain(d.get("domain_name")); // domain
							// url2.setAttachment_name((String) obj[6]); //
							// email error
							// url2.setUser_id((Integer) obj[7]); // qc(int)
							url2.setProject_name(d.get("project_id")); // project
																		// name
							url2.setClient_address(""); // client name
							url2.setChannel_name(d.get("user_id")); // user name
							url2.setClientSubject(d.get("renotification_id")); // renitification
							// id(int)

							url_listData.add(url2);
							d = null;
							url2 = null;
						}
						analyzer2 = null;
						queryParser = null;
						q = null;
						reader = null;
						searcher = null;
						tq = null;
						results = null;
						prop = null;
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						d = null;
						url2 = null;
						analyzer2 = null;
						queryParser = null;
						q = null;
						reader = null;
						searcher = null;
						tq = null;
						results = null;
						prop = null;
					}

				} else {

					for (int i = 0; i < lst.size(); i++) {
						url2 = new Mail_shoot_data();
						obj = (Object[]) lst.get(i);
						url2.setId((Integer) obj[0]); // id
						url2.setUrl_group((String) obj[1]); // url
						url2.setEmail_address((String) obj[2]); // email
						url2.setEmail_module((Integer) obj[3]); // send(int)
						url2.setDate_time((String) obj[4]); // project name
						url2.setDomain((String) obj[5]); // domain
						url2.setAttachment_name((String) obj[6]); // email error
						url2.setUser_id((Integer) obj[7]); // qc(int)
						url2.setProject_name((String) obj[8]); // project name
						url2.setClient_address((String) obj[9]); // client name
						url2.setChannel_name((String) obj[10]); // user name
						url2.setClientSubject((String) obj[11]); // renitification
																	// id(int)

						url_listData.add(url2);
						obj = null;
						url2 = null;
					}
				}

				// System.out.println("url_listData................."+url_listData.size());
				dao = null;
				lst = null;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			} finally {
				dao = null;
				lst = null;
				factory = null;
				session2 = null;
				analyzer2 = null;
				queryParser = null;
				q = null;
				dao = null;
				lst = null;
				reader = null;
				searcher = null;
				tq = null;
				results = null;
				prop = null;
			}
			return SUCCESS;
		}
	}

	public String emailStatus() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			try {
				ddd01 = (Mail_shoot_dataDao) factory.getBean("d26");
				lst = ddd01.viewRecord("select id from Mail_shoot_data");
				invalidQuery = lst.size();
				lst = null;
				ddd01 = null;

			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				ddd01 = null;
				lst = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	public String emailAddUpdatePre() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Master_email url2 = null;
			try {
				dao_me = (Master_emailDao) factory.getBean("d21");
				lst = dao_me.viewRecord("select id, email,domain_name, email_type from Master_email");
				master_emailData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					url2 = new Master_email();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]); // id
					url2.setEmail((String) obj[1]); // email
					url2.setDomain_name((String) obj[2]); // domain
					url2.setEmail_type((String) obj[3]); // email type
					master_emailData.add(url2);
					url2 = null;
					obj = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				url2 = null;
				obj = null;
				lst = null;
				dao_me = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	public String editmail() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// Client_master cm = null;
			factory = LoginAndSession.getFactory();
			try {
				dao_me = (Master_emailDao) factory.getBean("d21");
				lst = dao_me.viewRecord("select id, email,domain_name, email_type from Master_email where id=" + id);
				// client_master_data = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					// Client_master url2 = new Client_master();
					obj = (Object[]) lst.get(i);
					id__c = ((Integer) obj[0]); // id
					email_address__c = ((String) obj[1]); // email id
					domain = ((String) obj[2]); // domain
					obj = null;
				}
				emailAddUpdatePre();
				dao_me = null;
				lst = null;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			} finally {
				dao_me = null;
				lst = null;
				factory = null;
				session2 = null;

			}
			return SUCCESS;
		}
	}

	public String saveOrUpdateMail() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			Master_email cm = null;
			String query = null;
			factory = LoginAndSession.getFactory();
			dao_me = (Master_emailDao) factory.getBean("d21");
			if (domain.trim().length() < 1) {
				dummyMsg = "Error...Domain field is blank";
			} else if (email_address__c.trim().length() < 1) {
				dummyMsg = "Error...Mail id field is blank";
			} else if (validate(email_address__c)) {

				if (id__c == 0) {
					try {
						cm = new Master_email();

						cm.setEmail(email_address__c);
						cm.setDomain_name(domain);
						cm.setEmail_type(String.valueOf(2));
						cm.setCreated_on(nowTime());
						cm.setUser_id((int) session2.getAttribute("uid"));
						cm.setHost_name(String.valueOf(0));
						cm.setIsp_name(String.valueOf(0));
						cm.setReg_name(String.valueOf(0));
						cm.setOther(String.valueOf(0));
						dao_me.saveData(cm);
						// return SUCCESS;
						email_address__c = null;
						domain = null;
						id__c = 0;
						dummyMsg = "email added....";
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return ERROR;
					} finally {
						dao3 = null;
						cm = null;
					}
				} else {
					query = "update Master_email set email='" + email_address__c + "' , domain_name=" + " '" + domain
							+ "' , created_on = '" + nowTime() + "' , user_id=" + (int) session2.getAttribute("uid");

					query = query + "  where id=" + id__c;

					try {
						dao_me.customUpdateProject(query);
						dummyMsg = "email updated....";
						email_address__c = null;
						domain = null;
						id__c = 0;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return ERROR;
					} finally {
						dao_me = null;
						query = null;
					}
					query = null;
				}
			} else {
				dummyMsg = "error on email format....";
			}
			emailAddUpdatePre();
			cm = null;
			email_address__c = null;
			domain = null;
			dao_me = null;
			query = null;
			email_address__c = null;
			domain = null;
			factory = null;
			session2 = null;

			return SUCCESS;
		}

	}
	
	
	public String deletemail() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			Master_email cm = null;
			String query = null;
			factory = LoginAndSession.getFactory();
			dao_me = (Master_emailDao) factory.getBean("d21");
			
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB---------->"+id);
				//if (id != 0) {
					query="delete from Master_email where id=" + id;
					try {
						dao_me.customUpdateProject(query);
						dummyMsg = "email delete successfully....";
						email_address__c = null;
						domain = null;
						id__c = 0;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return ERROR;
					} finally {
						dao_me = null;
						query = null;
					}
					query = null;
				//}
			
			emailAddUpdatePre();
			cm = null;
			email_address__c = null;
			domain = null;
			dao_me = null;
			query = null;
			email_address__c = null;
			domain = null;
			factory = null;
			session2 = null;

			return SUCCESS;
		}

	}
	
	

	public boolean validate(final String hex) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	private List<Master_email> master_emailData = null;
	private List<Module_wise_email_template> moduleListData = null;
	private List<Enforcement_mail_id> emailListData = null;

	Master_emailDao dao_me = null;
	Url_emailDao dao = null;
	Url_email_qcDao dao1 = null;
	Crawle_url2Dao dao_cu = null;
	Url_email_editDao dao2 = null;
	Module_wise_email_templateDao dao3 = null;
	Module_wise_mail_sendDao dao4 = null;
	Enforcement_mail_idDao dao5 = null;
	Mail_shoot_dataDao ddd01 = null;
	// Master_email_trackerDao dao6 = null;

	private int invalidQuery; // contain id of url_email
	private String dummyMsg = null;
	private String url = null;
	private String mail = null;
	private String domain = null;
	private String id = null;
	private int id__c;
	private int email_id, module_id, adv_c_notice;

	public int getAdv_c_notice() {
		return adv_c_notice;
	}

	public void setAdv_c_notice(int adv_c_notice) {
		this.adv_c_notice = adv_c_notice;
	}

	public String getEmail_address__c() {
		return email_address__c;
	}

	public void setEmail_address__c(String email_address__c) {
		this.email_address__c = email_address__c;
	}

	public int getId__c() {
		return id__c;
	}

	public void setId__c(int id__c) {
		this.id__c = id__c;
	}

	public List<Master_email> getMaster_emailData() {
		return master_emailData;
	}

	public void setMaster_emailData(List<Master_email> master_emailData) {
		this.master_emailData = master_emailData;
	}

	public List<Mail_shoot_data> getUrl_listData() {
		return url_listData;
	}

	public void setUrl_listData(List<Mail_shoot_data> url_listData) {
		this.url_listData = url_listData;
	}

	public List<Mail_shoot_data> getMailShootData() {
		return mailShootData;
	}

	public void setMailShootData(List<Mail_shoot_data> mailShootData) {
		this.mailShootData = mailShootData;
	}

	public String getStringresult() {
		return stringresult;
	}

	public void setStringresult(String stringresult) {
		this.stringresult = stringresult;
	}

	public Map<Integer, ArrayList<Url_email__c>> getUrl_email_data_load() {
		return url_email_data_load;
	}

	public void setUrl_email_data_load(Map<Integer, ArrayList<Url_email__c>> url_email_data_load) {
		this.url_email_data_load = url_email_data_load;
	}

	public int getEmail_id() {
		return email_id;
	}

	public void setEmail_id(int email_id) {
		this.email_id = email_id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public List<Module_wise_email_template> getModuleListData() {
		return moduleListData;
	}

	public void setModuleListData(List<Module_wise_email_template> moduleListData) {
		this.moduleListData = moduleListData;
	}

	public List<Enforcement_mail_id> getEmailListData() {
		return emailListData;
	}

	public void setEmailListData(List<Enforcement_mail_id> emailListData) {
		this.emailListData = emailListData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDummyMsg() {
		return dummyMsg;
	}

	public void setDummyMsg(String dummyMsg) {
		this.dummyMsg = dummyMsg;
	}

	public int getInvalidQuery() {
		return invalidQuery;
	}

	public void setInvalidQuery(int invalidQuery) {
		this.invalidQuery = invalidQuery;
	}

	public List<Url_email> getListData() {
		return listData;
	}

	public void setListData(List<Url_email> listData) {
		this.listData = listData;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

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
		return lnk;
	}
}
