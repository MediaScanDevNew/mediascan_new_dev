/**
 * 
 */
package com.markscan.project.classes;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.markscan.project.beans.CommanReporting;
import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Master_email;
import com.markscan.project.beans.Module_wise_email_template;
import com.markscan.project.beans.Project_info;
import com.markscan.project.beans.Url_email;
import com.markscan.project.beans.YT_video_detail;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Master_emailDao;
import com.markscan.project.dao.Module_wise_email_templateDao;
import com.markscan.project.dao.Module_wise_mail_sendDao;
import com.markscan.project.dao.Project_infoDao;
import com.markscan.project.dao.Qc_recordDao;
import com.markscan.project.dao.Url_emailDao;
import com.markscan.project.dao.YT_video_detailDao;
import com.opensymphony.xwork2.ActionSupport;
import freemarker.template.Configuration;
import freemarker.template.Template;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

/**
 * @author pradeep
 *
 */
public class QualityCheck extends ActionSupport {
	private static final Logger logger = Logger.getLogger(QualityCheck.class);

	/**
	 * 
	 */
	public QualityCheck() {
		// TODO Auto-generated constructor stub
	}

	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	private List<Markscan_projecttype> listData = null;
	private List<CommanReporting> qcData = null;
	// private List<String> socialMedia = null;
	Properties prop = null;
	String filePath = null;
	Markscan_projecttype url2 = null;
	Object[] obj = null;

	public String execute() {

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			Markscan_projecttypeDao dao = null;
			factory = LoginAndSession.getFactory();
			try {
				dao = (Markscan_projecttypeDao) factory.getBean("d8");
				lst = dao.viewRecord("select id, name from Markscan_projecttype");
				logger.info(".......pradeep........" + lst.size());
				listData = Collections.synchronizedList(new ArrayList<Markscan_projecttype>());

				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_projecttype();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					listData.add(url2);
					url2 = null;
					obj = null;
				}
				dao = null;
				lst = null;
				factory = null;

			} catch (Exception e) {
				logger.error("project type data get ", e);

				lst = null;
				return ERROR;
			} finally {
				dao = null;
				lst = null;
				factory = null;
				url2 = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}

	}

	public String youtubeUpload() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			execute();
			return SUCCESS;
		}
	}

	String countQuery = null;

	public String pmQC() {
		logger.info("======invalid reason ====" + reasn);
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			Crawle_url2Dao dao2 = null;
			String qq = null;
			try {

				countQuery = "select count(cr_uurl.id) from Crawle_url2 as cr_uurl, Project_info as p_info "
						+ " where cr_uurl.project_id= p_info.id"
						+ " and cr_uurl.qc_new = 0 and cr_uurl.is_valid = 0 and cr_uurl.w_list=0 ";
				if (propertyName == 0) {
					countQuery = countQuery + " and  p_info.client_type = " + clientname + " ";
				} else {
					countQuery = countQuery + " and cr_uurl.project_id= " + propertyName + " ";
				}
				if (datatype != -1) {
					countQuery = countQuery + " and cr_uurl.link_logger= " + datatype + " ";
				}
				if (startdate != null || startdate != "" || !startdate.isEmpty()) {
					countQuery = countQuery + " and cr_uurl.created_on between '" + startdate.trim() + "'  and  "
							+ " ADDDATE('" + startdate.trim() + "' ,1)  ";
				}

				countQuery = countQuery + "  ";
				// factory = ActionPerform.getFactory();
				dao2 = (Crawle_url2Dao) factory.getBean("dash");

				totalrowCount = dao2.getNumberOfUsers(countQuery);
				countQuery = null;
				// System.out.println("row count ... " + totalrowCount);

				qq = "select cr_uurl.id, cr_uurl.crawle_url2 ,cr_uurl.domain_name , "
						+ " cr_uurl.link_logger_srclink, m_user.name, p_info.project_name, "
						+ " cr_uurl.created_on,cr_uurl.send_crawl  from Crawle_url2 as cr_uurl,Markscan_users as m_user, Project_info as p_info "
						+ " where cr_uurl.user_id= m_user.id and cr_uurl.project_id= p_info.id"
						+ " and cr_uurl.qc_new = 0 " + " and cr_uurl.status = 0 " + " and cr_uurl.verified = 1 "
						+ " and cr_uurl.w_list=0 ";
				// System.out.println("==property name " + propertyName);
				// System.out.println("==datatype name " + datatype);
				// System.out.println("==date name " + startdate);
				if (propertyName == 0) {
					qq = qq + " and  p_info.client_type = " + clientname + " ";
				} else {
					qq = qq + " and cr_uurl.project_id= " + propertyName + " ";
				}
				if (datatype != -1) {
					qq = qq + " and cr_uurl.link_logger= " + datatype + " ";
				}
				if (startdate != null || startdate != "" || !startdate.isEmpty()) {
					qq = qq + " and cr_uurl.created_on between '" + startdate.trim() + "'  and " + " ADDDATE('"
							+ startdate.trim() + "' ,1)  ";
				}

				qq = qq + "  ";

				dataExtract(qq);
				qq = null;
				dao2 = null;
				countQuery = null;

			} catch (Exception e) {
				logger.error("project type data get ", e);
				return ERROR;
			} finally {
				qq = null;
				dao2 = null;
				countQuery = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	int getRow;

	public Integer dataExtract(String qq) {

		Crawle_url2Dao dao = (Crawle_url2Dao) factory.getBean("dash");
		CommanReporting url2 = null;
		lst = dao.viewRecord(qq);
		getRow = lst.size();
		qcData = Collections.synchronizedList(new ArrayList<CommanReporting>());
		for (int i = 0; i < lst.size(); i++) {
			url2 = new CommanReporting();
			obj = (Object[]) lst.get(i);
			url2.setId((Integer) obj[0]);
			url2.setLink((String) obj[1]);
			url2.setDomainName((String) obj[2]);
			url2.setLink001((String) obj[3]);
			url2.setUserName((String) obj[4]);
			url2.setProjectName((String) obj[5]);
			url2.setDate__c((String) obj[6]);
			url2.setGreylist((Integer) obj[7]);
			qcData.add(url2);
			obj = null;
			url2 = null;
		}
		lst = null;
		dao = null;
		obj = null;
		url2 = null;
		return getRow;
	}

	@SuppressWarnings("finally")
	public String invalidLink() {
		// System.out.println("links are===" + invlink);
		// logger.info("=== invalid link----------"+reasn);
		session2 = ServletActionContext.getRequest().getSession();
		uid = (int) session2.getAttribute("uid");
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// String idd[] = invlink.split(",");
			factory = ActionPerform.getFactory();
			dao = (Crawle_url2Dao) factory.getBean("dash");
			dao1 = (Qc_recordDao) factory.getBean("d13");
			try {
				dao.customUpdateProject("update Crawle_url2 set status = 1, is_valid = 1, qc_new = 1, track_user_id ="
						+ uid + "  where id in ( " + invlink + " )  ");
				dao1.customUpdateProject("update Qc_record set is_valid= " + uid + " ,  is_valid_time='" + nowTime2()
						+ "', is_valid_reason='" + reasn + "' where crawl_url2_id in ( " + invlink + " )");

				// System.out.println("link update invalid");
				logger.info("link update invalid");
				dummyMsg = "links mark as invalid";
			} catch (Exception e) {
				// TODO: handle exception
				dummyMsg = "error on links invalid";
			} finally {
				session2 = null;
				factory = null;
				dao = null;
				dao1 = null;
				reasn = null;
				return SUCCESS;
			}
		}
		// return null;
	}

	String dummyMsg = null;

	public String getDummyMsg() {
		return dummyMsg;
	}

	public void setDummyMsg(String dummyMsg) {
		this.dummyMsg = dummyMsg;
	}

	public String pmQCaction() {

		int mj = totalrowCount - getRow;
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			uid = (int) session2.getAttribute("uid");
			String qq = null;
			try {
				if (totalrowCount > 0) {
					uniqprojectName = uniqprojectName + "," + id.trim();

					// String idd[] = id.split(",");
					factory = ActionPerform.getFactory();
					dao = (Crawle_url2Dao) factory.getBean("dash");
					dao1 = (Qc_recordDao) factory.getBean("d13");
					dao.customUpdateProject(
							"update Crawle_url2 set qc_new = 1, track_user_id =" + uid + " where id in ( " + id + " )");
					dao1.customUpdateProject("update Qc_record set user_id_pm= " + uid + " , pmqc_time='" + nowTime2()
							+ "' where crawl_url2_id in (" + id + ")");

					// System.out.println("pm qc perform ");
					logger.info("pm qc perform ");
					totalrowCount = mj;
				}
				qq = "select cr_uurl.id, cr_uurl.crawle_url2 ,cr_uurl.domain_name , "
						+ " cr_uurl.link_logger_srclink, m_user.name, p_info.project_name, "
						+ " cr_uurl.created_on,cr_uurl.send_crawl  from Crawle_url2 as cr_uurl,Markscan_users as m_user, Project_info as p_info "
						+ " where cr_uurl.user_id= m_user.id and cr_uurl.project_id= p_info.id"
						+ " and cr_uurl.qc_new = 0 " + " and cr_uurl.status = 0 "
						+ " and cr_uurl.verified = 1  and cr_uurl.w_list=0 ";
				// System.out.println("==property name " + propertyName);
				// System.out.println("==datatype name " + datatype);
				// System.out.println("==date name " + startdate);
				if (propertyName == 0) {
					qq = qq + " and  p_info.client_type = " + clientname + " ";
				} else {
					qq = qq + " and cr_uurl.project_id= " + propertyName + " ";
				}
				if (datatype != -1) {
					qq = qq + " and cr_uurl.link_logger= " + datatype + " ";
				}
				if (startdate != null || startdate != "" || !startdate.isEmpty()) {
					qq = qq + " and cr_uurl.created_on between '" + startdate.trim() + "'  and " + " ADDDATE('"
							+ startdate.trim() + "' ,1)  ";
				}

				qq = qq + "  ";
				if (dataExtract(qq) > 0) {

					factory = null;
					session2 = null;
					dao = null;
					dao1 = null;
					System.gc();

					return SUCCESS;
				} else {
					// System.out.println("mj==== totalrowCount..else.." +
					// totalrowCount);
					mailtouper();
					// System.out.println("now mail will send");
					logger.info("now mail will send");
					execute();

					factory = null;
					session2 = null;
					dao = null;
					dao1 = null;
					System.gc();
					return "pmhome";
				}
			} catch (NullPointerException e) {
				return "nullpointer";
			} catch (Exception e) {
				return ERROR;
			}
		}

	}

	public void mailtouper() {

		session2 = ServletActionContext.getRequest().getSession();
		uid = (int) session2.getAttribute("uid");
		// System.out.println((String) session2.getAttribute("user"));
		logger.info(session2);

		factory = ActionPerform.getFactory();
		Module_wise_email_templateDao dao = (Module_wise_email_templateDao) factory.getBean("d14");
		Module_wise_mail_sendDao dao1 = (Module_wise_mail_sendDao) factory.getBean("d15");
		lst = dao.viewRecord("select m_tmplat.id,m_tmplat.template,m_tmplat.subject from "
				+ "Module_wise_email_template m_tmplat where m_tmplat.module= 'PMQC' ");
		templateData = new ArrayList<Module_wise_email_template>();
		int module_id = 0;
		String subject = null;
		String tempale_body = null;
		for (int i = 0; i < lst.size(); i++) {
			obj = (Object[]) lst.get(i);
			module_id = (Integer) obj[0];
			// tempale_body = (String) obj[1];
			subject = (String) obj[2];
			obj = null;
		}
		tempale_body = sa((String) session2.getAttribute("user"), projecttype, clientname, propertyName, datatype,
				startdate);
		lst = null;
		lst = dao.viewRecord("select  m_mail_send.email_type,m_mail_send.email_id from"
				+ " Module_wise_mail_send m_mail_send where m_mail_send.module = " + module_id);
		String to = "";
		String cc = "";
		String bcc = "";
		for (int i = 0; i < lst.size(); i++) {
			obj = (Object[]) lst.get(i);
			if ((Integer) obj[0] == 1) {
				to = to + "," + (String) obj[1];
			} else if ((Integer) obj[0] == 2) {
				cc = cc + "," + (String) obj[1];
			} else if ((Integer) obj[0] == 3) {
				bcc = bcc + "," + (String) obj[1];
			}
			obj = null;
		}

		// System.out.println("to.." + to);
		// System.out.println("cc.." + cc);
		// System.out.println("bcc.." + bcc);
		// System.out.println("subject.." + subject);
		// System.out.println("body.." + tempale_body);
		/**
		 * === adding mail pttern.. by regex...
		 */
		Pattern p1 = Pattern.compile("[a-zA-Z0-9]+@markscan.co.in");
		Matcher m = p1.matcher((String) session2.getAttribute("mail"));
		m.find();
		if (m.find() != true) {
			to = to + "," + (String) session2.getAttribute("mail");
		}

		// System.out.println("to. again." + to);

		/**
		 * === getting mail detail & configuration from property file
		 */
		configFile = "/mailconfig.properties";
		input = getClass().getResourceAsStream(configFile);
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(input);
		} catch (Exception e) {
		}
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", prop.getProperty("mailip"));
		props.put("mail.smtp.port", "25");

		/**
		 * === mail send ...
		 */
		Session mailsession = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication("pjoshi@markscan.co.in", "M@123rkscan");
			}
		});

		try {

			Message message = new MimeMessage(mailsession);
			message.setFrom(new InternetAddress((String) session2.getAttribute("mail")));
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse("pjoshi@markscan.co.in"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));

			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));

			message.setSubject(subject + " :: " + clientname_name);

			message.setContent(tempale_body, "text/html");

			// Send message

			Transport.send(message);
			// System.out.println("Sent message successfully....");
			logger.info("Sent message successfully....");
			message = null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Sent message fail....", e);
		} finally {
			props = null;
			prop = null;
			configFile = null;
			input = null;
			to = null;
			cc = null;
			bcc = null;
			subject = null;
			tempale_body = null;
			templateData = null;
			lst = null;
			System.out.println("mail send.. ");
			factory = null;
			dao = null;
			dao1 = null;
		}
	}

	InputStream input = null;
	String body = null;
	Configuration cfg = null;
	Set<String> countries = null;

	public String sa(String usr, int pname, int cname, int ptname, int dtype, String date) {
		// Freemarker configuration object
		prop = new Properties();
		/**
		 * get dynamic mail template..
		 */
		try {
			configFile = "/mailconfig.properties";
			input = getClass().getResourceAsStream(configFile);
			prop.load(input);
			directory = prop.getProperty("templetpdir");
			templatename = prop.getProperty("pmqc");
			// System.out.println(directory + "..temp data..." + templatename);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			configFile = null;
			input = null;
			prop = null;
		}
		cfg = new Configuration();
		Map<String, Object> data = null;
		Project_infoDao dao = null;
		try {

			// Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(directory));
			Template template = cfg.getTemplate(templatename);

			// Build the data-model
			data = new HashMap<String, Object>();
			data.put("curtime", nowTime2());
			data.put("performedby", usr);
			data.put("projectname", ptype_name);
			data.put("clientname", clientname_name);
			data.put("propertyname", propertyName_name);
			data.put("datatype", datatype_name);
			data.put("date", startdate);

			countries = new HashSet<String>();
			// String ppj[] = projectNamelist.split(",");
			// Set<String,String> projdetail = new HashSet<String,String>();

			// for (String pj : ppj) {
			// projname.add(pj.trim());
			// }

			factory = ActionPerform.getFactory();
			dao = (Project_infoDao) factory.getBean("d1");
			projname = new HashSet<>();
			if (uniqprojectName.trim().startsWith(",")) {
				uniqprojectName = (String) uniqprojectName.subSequence(1, uniqprojectName.length());
				// System.out.println("uniqnamewa......" + uniqprojectName);
				String aa[] = uniqprojectName.trim().split(",");

				for (String pj : aa) {
					// System.out.println("==" + pj);
					projname.add(Integer.parseInt(pj.trim()));
				}
				aa = null;
			}

			for (Integer ii : projname) {
				lst = dao.viewRecord(
						"select pi.project_name,pi.actual_hosted_site  from Project_info pi, Crawle_url2 cu "
								+ "where pi.id=cu.project_id and cu.id =" + ii);
				for (int i = 0; i < lst.size(); i++) {
					Object[] obj = (Object[]) lst.get(i);
					countries.add((String) obj[0] + "  <br>  " + (String) obj[1]);
					obj = null;
				}
				lst = null;
			}

			data.put("propname", countries);

			// ppj = null;

			stringWriter = new StringWriter();
			template.process(data, stringWriter);
			body = stringWriter.toString();
			data = null;
			projname = null;
			dao = null;
			template = null;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			configFile = null;
			input = null;
			prop = null;
			stringWriter = null;
			lst = null;
			uniqprojectName = null;
			projname = null;
			factory = null;
			countries = null;
			projname = null;
			data = null;
			cfg = null;
			prop = null;
			input = null;
			directory = null;
			templatename = null;
			configFile = null;
		}

		return body;
	}

	StringWriter stringWriter = null;

	/**
	 * ------- content replacement
	 * 
	 * @pradeep
	 */
	public String contentReplacePre() {
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

	CommanReporting cr = null;

	public String contentReplace() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			ScoreDoc[] hits = null;
			factory = LoginAndSession.getFactory();
			Document d = null;
			try {
				dao = (Crawle_url2Dao) factory.getBean("dash");
				if (apple.contains("'")) {
					apple = apple.replaceAll("'", "''");
				}
				if (apple.contains("\"")) {
					apple = apple.replaceAll("\"", "");
				}
				lst = dao
						.viewRecord("select crawle_url0_.crawle_url2 as col_0_0_, client_mas3_.client_name as col_1_0_,"
								+ " crawle_url0_.link_logger_srclink as col_2_0_, project_in2_.project_name as col_3_0_, "
								+ " markscan_u1_.name as col_4_0_, crawle_url0_.created_on as col_5_0_, "
								+ " crawle_url0_.domain_name "
								+ " as col_6_0_, crawle_url0_.id as col_7_0_, crawle_url0_.is_valid, qc.is_valid_time as "
								+ " invalid_time,qc.is_valid_reason as reason,crawle_url0_.w_list,  crawle_url0_.send_crawl, "
								+ " qc.bypass_link ,(select mu.name from Markscan_users mu where mu.id = qc.user_id_pm) as "
								+ " pmqc_name, qc.pmqc_time,(select mu.name from Markscan_users mu where "
								+ " mu.id = qc.user_id_ops) "
								+ " as opsqc_name, qc.opsqc_time,(select mu.name from Markscan_users mu where "
								+ " mu.id = qc.m_id ) "
								+ " as user_master_email, qc.m_date,case when qc.m_perform = 0 then 'pending' else "
								+ " 'Master Email Performed' end as me_perform , (select mu.name from Markscan_users "
								+ " mu where "
								+ " mu.id = qc.gt_user_id )as user_google_tracker,qc.gt_date, case when qc.gt_perform = 0 "
								+ " then 'pending' else 'Google Tracker Performed' end as gt_perform , crawle_url0_.note1, "
								+ " crawle_url0_.note2,crawle_url0_.image_path , "
								+ " (select mu.name from Markscan_users mu where mu.id = qc.is_valid ) "
								+ " as user_invalid, crawle_url0_.link_logger from Crawle_url2 crawle_url0_, Markscan_users markscan_u1_, "
								+ " Project_info project_in2_, Client_master client_mas3_, Qc_record qc " + "  where "
								+ " qc.crawl_url2_id = crawle_url0_.id and markscan_u1_.id=crawle_url0_.user_id and "
								+ " project_in2_.id=crawle_url0_.project_id and client_mas3_.id=project_in2_.client_type and "
								+ " crawle_url0_.crawle_url2='" + apple.trim() + "' order by  crawle_url0_.id desc ");

				ContentFilterData = new ArrayList<>();
				yt_Data = new ArrayList<>();
				if (lst.size() < 1) {

					prop = new Properties();
					try {
						configFile = "/details.properties";
						input = getClass().getResourceAsStream(configFile);
						prop.load(input);
						// db = prop.getProperty("database");
						filePath = prop.getProperty("filePath");
						indexPath = prop.getProperty("indexPath1");
						logger.info("indexpath==== " + indexPath);
					} catch (Exception e) {
						// e.printStackTrace();
						logger.error("file read error .. ", e);
					} finally {
						configFile = null;
						input = null;
						prop = null;
					}

					try {

						analyzer2 = new StandardAnalyzer();
						queryParser = new QueryParser("crawle_url2", analyzer2);
						q = queryParser.parse(queryParser.escape(apple.trim()));
						// System.out.println(q);
						int hitsPerPage = 10;

						reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
						searcher = new IndexSearcher(reader);

						tq = new TermQuery(new Term("crawle_url2", apple.toString()));

						results = searcher.search(tq, 100000);
						hits = results.scoreDocs;
						// display result
						logger.info("data Found " + hits.length + " hits.");
						if (hits.length < 1) {
							CommanReporting cu = new CommanReporting();
							cu.setNote1("Id");
							cu.setLink("URL");
							cu.setDomainName("Domain");
							cu.setLink001("Infringing Link");
							cu.setUserName("User Name");
							cu.setClientName("Client Name");
							cu.setPropertName("Property Name");
							cu.setDate__c("Created Date");
							ContentFilterData.add(cu);
							cu = null;
						} else {

							for (int i = 0; i < hits.length; ++i) {
								int docId = hits[i].doc;
								d = searcher.doc(docId);

								cr = new CommanReporting();
								cr.setLink(d.get("crawle_url2")); // crawle_url2
								cr.setClientName(""); // client_name
								if (d.get("link_logger_srclink").trim() == null) {
									// System.out.println("null value");
									cr.setLink001("");
								} else {
									cr.setLink001(d.get("link_logger_srclink"));// link_logger_srclink
								}

								// cr.setLink001(obj[2].toString());//
								// link_logger_srclink
								cr.setPropertName(d.get("project_id"));// propert_name
								cr.setUserName(d.get("user_id"));// user name
								cr.setDate__c(d.get("created_on"));// created on
								cr.setDomainName(d.get("domain_name"));// domain_name
								cr.setId(Integer.parseInt(d.get("id")));// crawle_url2_id
								ContentFilterData.add(cr);
								d = null;
								cr = null;
							}
							ii = hits.length;
							d = null;
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
						cr = null;
						analyzer2 = null;
						queryParser = null;
						q = null;
						reader = null;
						searcher = null;
						tq = null;
						results = null;
						prop = null;
						d = null;
					}

				} else {
					for (int i = 0; i < lst.size(); i++) {
						obj = (Object[]) lst.get(i);
						cr = new CommanReporting();

						cr.setLink(obj[0].toString()); // crawle_url2
						cr.setClientName(obj[1].toString()); // client_name
						if (obj[2] == null) {
							// System.out.println("null value");
							cr.setLink001("");
						} else {
							cr.setLink001(obj[2].toString());// link_logger_srclink
						}
						cr.setPropertName(obj[3].toString());// propert anme
						cr.setUserName(obj[4].toString());// user name
						cr.setDate__c(obj[5].toString());// created on
						cr.setDomainName(obj[6].toString());// domain name
						cr.setId((Integer) obj[7]);// crawle_url2_id
						cr.setIs_valid((Integer) obj[8]);
						if (obj[9] == null) {
							cr.setInvalid_time("");
						} else
							cr.setInvalid_time(obj[9].toString());
						if (obj[10] == null) {
							cr.setReason("");
						} else
							cr.setReason(obj[10].toString());
						cr.setWhitelist(obj[11].toString());
						cr.setGreylist((Integer) obj[12]);
						cr.setQc_mode((Integer) obj[13]);
						if (obj[14] == null) {
							cr.setUser_pmqc("");
						} else
							cr.setUser_pmqc(obj[14].toString());
						if (obj[15] == null) {
							cr.setPmqc_time("");
						} else
							cr.setPmqc_time(obj[15].toString());
						if (obj[16] == null) {
							cr.setUser_opsqc("");
						} else
							cr.setUser_opsqc(obj[16].toString());
						if (obj[17] == null) {
							cr.setOpsqc_time("");
						} else
							cr.setOpsqc_time(obj[17].toString());
						if (obj[18] == null) {
							cr.setUser_me("");
						} else
							cr.setUser_me(obj[18].toString());
						if (obj[19] == null) {
							cr.setMe_time("");
						} else
							cr.setMe_time(obj[19].toString());
						cr.setMe_perform(obj[20].toString());
						if (obj[21] == null) {
							cr.setUser_gt("");
						} else
							cr.setUser_gt(obj[21].toString());
						if (obj[22] == null) {
							cr.setGt_time("");
						} else
							cr.setGt_time(obj[22].toString());
						cr.setGt_perform(obj[23].toString());
						if (obj[24] == null) {
							cr.setNote1("");
						} else
							cr.setNote1(obj[24].toString());
						if (obj[25] == null) {
							cr.setNote2("");
						} else
							cr.setNote2(obj[25].toString());
						if (obj[26] == null) {
							cr.setImage_path("");
						} else {
							cr.setImage_path(obj[26].toString());
							fileExtension = obj[26].toString();
							cr.setFileExtension(
									fileExtension.substring(fileExtension.lastIndexOf("."), fileExtension.length()));
						}
						if (obj[27] == null) {
							cr.setUser_invalid("");
						} else
							cr.setUser_invalid(obj[27].toString());
						cr.setLink_logger((Integer) obj[28]);

						ContentFilterData.add(cr);

						cr = null;

						// =========== youtube details = = = = = = =

						try {

							yt_dao = (YT_video_detailDao) factory.getBean("d35");
							ytLst = yt_dao.viewRecord("select crawle_url2_id, video_id, channelId, channelTitle, "
									+ " duration, likeCount, viewCount, publishedAt from  YT_video_detail yt where "
									+ " yt.crawle_url2_id =" + (Integer) obj[7]);
							for (int j = 0; j < ytLst.size(); j++) {
								yt_details = new YT_video_detail();
								ytObj = (Object[]) ytLst.get(j);
								yt_details.setCrawle_url2_id((Integer) ytObj[0]);
								yt_details.setVideo_id(ytObj[1].toString());
								yt_details.setChannelId(ytObj[2].toString());
								if (ytObj[3] == null) {
									yt_details.setChannelTitle("");
								} else {
									yt_details.setChannelTitle(ytObj[3].toString());
								}
								duration = ytObj[4].toString();
								if (duration.contains("PT")) {
									duration = duration.replaceAll("PT", "");
									duration = duration.replaceAll("H", "H ");
									duration = duration.replaceAll("M", "M ");
									duration = duration.replaceAll("S", "S ");
									yt_details.setDuration(duration);
								} else {
									yt_details.setDuration(ytObj[4].toString());
								}

								yt_details.setLikeCount(ytObj[5].toString());
								yt_details.setViewCount(ytObj[6].toString());
								if (ytObj[7] == null) {
									yt_details.setPublishedAt("");
								} else {
									yt_details.setPublishedAt(ytObj[7].toString());
								}

								yt_Data.add(yt_details);
								yt_details = null;
							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							yt_dao = null;
							ytLst = null;
							ytObj = null;
							yt_details = null;
							duration = null;
						}
						obj = null;
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				lst = null;
				dao = null;
				factory = null;
				session2 = null;
				yt_dao = null;
				ytLst = null;
				cr = null;
				ytObj = null;
				yt_details = null;
				duration = null;
			}
			return SUCCESS;
		}
	}

	String duration = null;
	YT_video_detail yt_details = null;
	Object ytObj[] = null;
	List ytLst = null;
	YT_video_detailDao yt_dao = null;
	String fileExtension = null;
	String indexPath = null;

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
		return lnk;
	}

	public String contentReplaceUpdate() {

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			dao = (Crawle_url2Dao) factory.getBean("dash");
			dao.customUpdateProject(
					"update Crawle_url2 cu set cu.crawle_url2='" + apple + "',cu.domain_name='" + ptype_name + "',"
							+ " cu.link_logger_srclink='" + uniqprojectName + "' where cu.id = " + clientname);
			dao = null;
			factory = null;
			session2 = null;
			return SUCCESS;
		}
	}

	/**
	 * ---- ops qc methods....
	 * 
	 * @pradeep 10 oct 2016
	 */
	public String opsQC() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			String qq = null;
			try {
				countQuery = "select count(cr_uurl.id) from Crawle_url2 as cr_uurl, Project_info as p_info "
						+ " where  cr_uurl.project_id= p_info.id" + " and cr_uurl.qc_new = 1 and w_list_perform=0 "
						+ " and cr_uurl.w_list=0 and cr_uurl.is_valid = 0 ";
				if (propertyName == 0) {
					countQuery = countQuery + " and  p_info.client_type = " + clientname + " ";
				} else {
					countQuery = countQuery + " and cr_uurl.project_id= " + propertyName + " ";
				}
				if (datatype != -1) {
					countQuery = countQuery + " and cr_uurl.link_logger= " + datatype + " ";
				}
				if (startdate != null || startdate != "" || !startdate.isEmpty()) {
					countQuery = countQuery + " and cr_uurl.created_on between ADDDATE('" + startdate.trim()
							+ "',-1)  and  " + " ADDDATE('" + startdate.trim() + "' ,1)  ";
				}

				countQuery = countQuery + "  ";
				factory = ActionPerform.getFactory();
				dao = (Crawle_url2Dao) factory.getBean("dash");

				totalrowCount = dao.getNumberOfUsers(countQuery);
				// System.out.println("row count ... " + totalrowCount);
				countQuery = null;
				qq = "select cr_uurl.id, cr_uurl.crawle_url2 ,cr_uurl.domain_name , "
						+ " cr_uurl.link_logger_srclink, m_user.name, p_info.project_name, "
						+ " cr_uurl.created_on,cr_uurl.send_crawl from Crawle_url2 as cr_uurl,Markscan_users as m_user, Project_info as p_info "
						+ " where cr_uurl.user_id= m_user.id and cr_uurl.project_id= p_info.id"
						+ " and cr_uurl.qc_new = 1 " + " and cr_uurl.status = 0 and cr_uurl.is_valid = 0 "
						+ " and cr_uurl.verified = 1 and cr_uurl.w_list_perform=0  and cr_uurl.w_list=0  ";
				// System.out.println("==property name " + propertyName);
				// System.out.println("==datatype name " + datatype);
				// System.out.println("==date name " + startdate);
				if (propertyName == 0) {
					qq = qq + " and  p_info.client_type = " + clientname + " ";
				} else {
					qq = qq + " and cr_uurl.project_id= " + propertyName + " ";
				}
				if (datatype != -1) {
					qq = qq + " and cr_uurl.link_logger= " + datatype + " ";
				}
				if (startdate != null || startdate != "" || !startdate.isEmpty()) {
					qq = qq + " and cr_uurl.created_on between adddate( '" + startdate.trim() + "' ,-1) and "
							+ " ADDDATE('" + startdate.trim() + "' ,1)  ";
				}

				qq = qq + "  ";

				dataExtract(qq);
				qq = null;
				factory = null;
				dao = null;

			} catch (Exception e) {
				logger.error("project type data get ", e);
				return ERROR;
			} finally {
				dao = null;
				qq = null;
				factory = null;
				session2 = null;
				countQuery = null;
			}
			return SUCCESS;

		}
	}

	int notEmailpage = 0;

	public String opsQCaction() {

		int mj = totalrowCount - getRow;
		// totalrowCount = mj;

		// System.out.println("mj==== totalrowCount...." + totalrowCount);
		// System.out.println("project name = == list " + projectNamelist);
		session2 = ServletActionContext.getRequest().getSession();

		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			uid = (int) session2.getAttribute("uid");
			factory = ActionPerform.getFactory();
			if (totalrowCount > 0) {
				// System.out.println("invalid method");
				// System.out.println("---" + invlink);
				// System.out.println("-uid--" + id);
				// System.out.println("-cname--" + clientname);
				// uniqprojectName = uniqprojectName + "," + id.trim();
				// String idd[] = id.split(",");

				dao = (Crawle_url2Dao) factory.getBean("dash");
				dao1 = (Qc_recordDao) factory.getBean("d13");

				dao.customUpdateProject("update Crawle_url2 set w_list_perform = 1 where id in ( " + id + " )");
				dao1.customUpdateProject("update Qc_record set user_id_ops= " + uid + " , opsqc_time='" + nowTime2()
						+ "' where crawl_url2_id in (" + id + ")");

				// System.out.println("pm qc perform ");
				logger.info("ops qc perform ");
				totalrowCount = mj;
			}
			String qq = "select cr_uurl.id, cr_uurl.crawle_url2 ,cr_uurl.domain_name , "
					+ " cr_uurl.link_logger_srclink, m_user.name, p_info.project_name, "
					+ " cr_uurl.created_on,cr_uurl.send_crawl  from Crawle_url2 as cr_uurl,Markscan_users as m_user, Project_info as p_info "
					+ " where cr_uurl.user_id= m_user.id and cr_uurl.project_id= p_info.id" + " and cr_uurl.qc_new = 1 "
					+ " and cr_uurl.status = 0 " + " and cr_uurl.verified = 1 AND cr_uurl.w_list_perform=0  "
					+ " and cr_uurl.w_list=0 ";
			// System.out.println("==property name " + propertyName);
			// System.out.println("==datatype name " + datatype);
			// System.out.println("==date name " + startdate);
			if (propertyName == 0) {
				qq = qq + " and  p_info.client_type = " + clientname + " ";
			} else {
				qq = qq + " and cr_uurl.project_id= " + propertyName + " ";
			}
			if (datatype != -1) {
				if (datatype == 0) {
					notEmailpage = 1;
				}
				qq = qq + " and cr_uurl.link_logger= " + datatype + " ";
			}
			if (startdate != null || startdate != "" || !startdate.isEmpty()) {
				qq = qq + " and cr_uurl.created_on between '" + startdate.trim() + "'  and " + " ADDDATE('"
						+ startdate.trim() + "' ,1)  ";
			}

			qq = qq + "  ";
			if (dataExtract(qq) > 0) {
				qq = null;
				dao = null;
				dao1 = null;
				// startdate=null;
				factory = null;
				session2 = null;
				System.gc();
				return SUCCESS;
			} else {

				execute();
				// startdate=null;
				qq = null;
				dao = null;
				dao1 = null;
				factory = null;
				session2 = null;
				System.gc();
				return "pmhome";

			}
		}

	}

	Crawle_url2Dao dao = null;
	Qc_recordDao dao1 = null;
	Project_infoDao dao2 = null;
	Url_emailDao dao3 = null;
	List<Project_info> propinfo = null;
	String propertyids = null;

	/*
	 * master email and import
	 */
	Set<Integer> project_id__c = null;

	public String masterEmailchooseProject() throws InvocationTargetException {

		if (socialmedia__c.trim().equalsIgnoreCase("true")) {
			// System.out.println("social media..." + socialmedia__c);
			return masterEmailAndImport();
		} else {

			session2 = ServletActionContext.getRequest().getSession();
			logger.info(session2);
			if (session2 == null || session2.getAttribute("login") == null) {
				logger.error("if=====session error reporting===='");
				return LOGIN;
			} else {
				factory = ActionPerform.getFactory();
				project_id__c = new HashSet<>();
				Project_info pinfo = null;
				try { // get craule id
					dao = (Crawle_url2Dao) factory.getBean("dash");
					lst = dao.viewRecord(
							"select cu.id, cu.project_id, pi.id from Crawle_url2 as cu , Project_info as pi where"
									+ " cu.w_list_perform=1 and cu.is_valid=0 and "
									+ "  cu.w_list=0 and cu.c_new=0 and cu.link_logger=1 and "
									+ "  pi.id= cu.project_id and pi.project_type=" + projecttype + " and"
									+ " pi.client_type=" + clientname + " " + " and cu.created_on  between "
									+ "adddate('" + startdate.trim() + "',-1) and adddate('" + startdate.trim()
									+ "',1)",
							50000);
					uniqprojectName = "";
					for (int i = 0; i < lst.size(); i++) {

						obj = (Object[]) lst.get(i);
						uniqprojectName = uniqprojectName + "," + (Integer) obj[0];
						project_id__c.add((Integer) obj[1]);
						obj = null;
					}
					if (uniqprojectName.trim().startsWith(",")) {
						uniqprojectName = uniqprojectName.substring(1, uniqprojectName.length());
					}
					// logger.info("==========>>>>>>>>>>>>>>>>--------------" +
					// uniqprojectName);
					// logger.info("=== unique project ===" + project_id__c);

				} catch (Exception e) {
					logger.error("error on get project id for master email", e);
					// e.printStackTrace();
				} finally {
					dao = null;
					lst = null;
				}
				try {// == project id with name
					propertyids = "";
					for (Integer ii : project_id__c) {
						propertyids = propertyids + "," + ii;
					}

					if (propertyids.trim().startsWith(",")) {
						propertyids = propertyids.substring(1, propertyids.length());
					}
					// logger.info("==========>>>>>>>>>>>uniqprojectName>>>>>--------------"
					// + propertyids);
					dao2 = (Project_infoDao) factory.getBean("d1");
					lst = dao2.viewRecord("select pi.id, pi.project_name from Project_info as pi where  pi.id in ( "
							+ propertyids + " ) ");
					propinfo = new ArrayList<>();
					for (int i = 0; i < lst.size(); i++) {
						pinfo = new Project_info();
						obj = (Object[]) lst.get(i);
						pinfo.setId((int) (obj[0]));
						pinfo.setProject_name(obj[1].toString());
						propinfo.add(pinfo);
						obj = null;
						pinfo = null;
					}

				} catch (Exception e) {
					logger.error("==error @ project detail  error--" + e);
				} finally {
					propertyids = null;
					dao2 = null;
					lst = null;
					pinfo = null;
				}
				// return "chooseProject";
				project_id__c = null;
				factory = null;
				session2 = null;
				return SUCCESS;
			}
		}
	}

	Set<Integer> updateCrawle_url_id = null;
	int j = 0;

	public String masterEmailAndImport() throws InvocationTargetException {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {

			try {
				prop = new Properties();
				configFile = "/details.properties";
				input = getClass().getResourceAsStream(configFile);
				prop.load(input);
				filePath = prop.getProperty("filepath");
				indexPath2 = prop.getProperty("indexPath2");

				logger.info("masterEmailAndImport file read===================" + filePath);
				logger.info("masterEmailAndImport file read===================" + indexPath2);
				prop = null;
			} catch (Exception e) {
				logger.error("file read error .. ", e);
			} finally {
				prop = null;
				configFile = null;
				input = null;

			}

			lst = null;
			Url_email ue = null;
			updateCrawle_url_id = new HashSet<>();
			factory = ActionPerform.getFactory();
			dao = (Crawle_url2Dao) factory.getBean("dash");
			dao3 = (Url_emailDao) factory.getBean("d22");
			dao1 = (Qc_recordDao) factory.getBean("d13");
			ua = UserAgent.parseUserAgentString(ServletActionContext.getRequest().getHeader("User-Agent"));
			if (!socialmedia__c.trim().equalsIgnoreCase("true")) {
				try { // compare with email id

					lst = dao.viewRecord("select me.email, cu.crawle_url2,cu.domain_name,me.email_type, "
							+ " cu.project_id,cu.id from Crawle_url2 cu ,Master_email me,Project_info pi  where "
							+ " pi.id= cu.project_id  and me.domain_name= cu.domain_name and  cu.w_list = 0 and "
							+ "  cu.is_valid = 0 and verified = 1 and link_logger = 1  and  cu.w_list_perform=1 and "
							+ "  pi.client_type=" + clientname + " and  cu.c_new=0 and cu.project_id in(" + id
							+ ") and  " + " cu.created_on  between " + "adddate('" + startdate.trim()
							+ "',-1) and adddate('" + startdate.trim() + "',1)", 20000);
					j = 0;

					for (int i = 0; i < lst.size(); i++) {

						ue = new Url_email();
						// Crawle_url2 cu = new Crawle_url2();
						obj = (Object[]) lst.get(i);

						int pj__c = indexedSearch_Two(linkupdate(obj[1].toString()), obj[4].toString(),
								obj[0].toString(), 0, indexPath2);
						logger.info("========= pj__c==================" + pj__c);

						if (pj__c == 0) {

							ue.setDate_time(nowTime2());
							ue.setEmail(obj[0].toString()); // email id
							ue.setUrl(obj[1].toString()); // craule_url2
							ue.setDomain_name(obj[2].toString()); // domain name
							ue.setEmail_type(obj[3].toString()); // email type
							ue.setProject_id((Integer) obj[4]); // project id
							ue.setCrawle_url2_id((Integer) obj[5]); // craule_url2_id
							ue.setUser_id((Integer) session2.getAttribute("uid"));
							ue.setRenotification_id("0");
							ue.setEmail_error_new("0");
							ue.setWid("1"); // temporary column
							retuen_id = dao3.saveData_retuenID(ue); // save data
																	// @url_email

							dao1.customUpdateProject(
									"update Qc_record  set m_id=" + (Integer) session2.getAttribute("uid") + ",  "
											+ " m_date='" + nowTime2() + "', m_perform=1 , browser_name= '"
											+ ua.getBrowser().toString() + "' , browser_version = "
											+ Integer.parseInt(ua.getBrowserVersion().getMajorVersion()) + ""
											+ " where crawl_url2_id =" + (Integer) obj[5]);

							dao.customUpdateProject("update Crawle_url2 set c_new=1 where id =" + (Integer) obj[5]);

						}
						ue = null;
						obj = null;

					}

				} catch (Exception ee) {
					// ee.printStackTrace();
					logger.error("mail compair error...." + ee);
					ee.printStackTrace();
				} finally {
					ue = null;
					obj = null;

				}
			}

			/**
			 * export data from manual email id==============
			 */

			try {
				// dataforupdate = ",";
				lst = null;

				String qqury = null;
				if (socialmedia__c.trim().equalsIgnoreCase("true")) {
					// System.out.println("=========++pp1----" +
					// socialmedia__c);
					try {
						prop = new Properties();
						configFile = "/SM_direct_inforcement.properties";
						input = getClass().getResourceAsStream(configFile);
						prop.load(input);
						// db = prop.getProperty("database");
						filePath = prop.getProperty("sm");
						// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+filePath);
						qqury = "select  cu.crawle_url2,cu.domain_name,"
								+ " cu.project_id,cu.id, pi.project_name, pi.actual_hosted_site , cu.note1,cu.note2"
								+ " , cu.link_logger_srclink from Crawle_url2 cu ,"
								+ " Project_info pi  where " + " pi.id= cu.project_id  and   cu.w_list = 0 and "
								+ "  cu.is_valid = 0 and verified = 1 and link_logger = 1  and  cu.w_list_perform=1 and "
								+ "  pi.client_type=" + clientname + "  and  cu.domain_name in (" + filePath + ")  and "
								+ " cu.created_on  between " + "adddate('" + startdate.trim() + "',-1) and adddate('"
								+ startdate.trim() + "',1) and cu.c_new=0";

						prop = null;
						filePath = null;
					} catch (Exception e) {
						// e.printStackTrace();
						logger.error("file read error .. ", e);
						prop = null;
						filePath = null;
					} finally {
						prop = null;
						filePath = null;
						input = null;
						configFile = null;
					}
					// System.out.println("==== query in if "+qqury);
				} else {
					// System.out.println("=========++pp2----"+socialmedia__c);
					qqury = "select  cu.crawle_url2,cu.domain_name,"
							+ " cu.project_id,cu.id, pi.project_name,  pi.actual_hosted_site, cu.note1,cu.note2, "
							+ "cu.link_logger_srclink from Crawle_url2 cu ,"
							+ " Project_info pi  where " + " pi.id= cu.project_id  and   cu.w_list = 0 and "
							+ "  cu.is_valid = 0 and verified = 1 and link_logger = 1  and  cu.w_list_perform=1 and "
							+ "  pi.client_type=" + clientname + " and  c_new=0 and cu.project_id in(" + id + ") and  "
							+ " cu.created_on  between " + "adddate('" + startdate.trim() + "',-1) and adddate('"
							+ startdate.trim() + "',1) and cu.c_new=0";

				}

				lst = dao.viewRecord(qqury, 50000);
				ContentFilterData = new ArrayList<>();

				j = 0;
				idListforupdate = new ArrayList<>();
				dataforupdate = ",";
				for (int i = 0; i < lst.size(); i++) {

					j++;
					if (j >= 1000) {
						// System.out.println("entry in if...." + i);
						if (dataforupdate.startsWith(",")) {
							dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
						}
						// System.out.println("propertyids....11....." +
						// propertyids);
						if (dataforupdate.endsWith(",")) {
							dataforupdate = (String) dataforupdate.substring(0, dataforupdate.length() - 1);
						}
						idListforupdate.add(dataforupdate);
						j = 1;
						dataforupdate = ",";
					}

					obj = (Object[]) lst.get(i);
					// Crawle_url2 cu = new Crawle_url2();
					cr = new CommanReporting();

					cr.setLink(obj[0].toString());
					cr.setDomainName(obj[1].toString());
					cr.setGreylist((Integer) obj[2]); // project id
					cr.setId((Integer) obj[3]);
					cr.setProjectName(obj[4].toString());
					cr.setFileExtension(obj[5].toString());
					cr.setNote1(obj[6].toString());
					cr.setNote2(obj[7].toString());
					cr.setLink001(obj[8].toString());//infringing_link

					ContentFilterData.add(cr);
					dataforupdate = dataforupdate + (Integer) obj[3] + ",";
					updateCrawle_url_id.add((Integer) obj[3]);

					cr = null;
					obj = null;
				}

				downloadCSVFileAction("ID,URL,Domain Name, Project ID, Property Name, Actual Host Site, "
						+ " Note1, Note2, Infringing Link",
						ua.getBrowser().toString());
				logger.info("csv created..");

				/**
				 * === update process perform....
				 */

				if (dataforupdate.startsWith(",")) {
					dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
				}
				// System.out.println("propertyids....11....." +
				// propertyids);
				if (dataforupdate.endsWith(",")) {
					dataforupdate = (String) dataforupdate.substring(0, dataforupdate.length() - 1);
				}
				idListforupdate.add(dataforupdate);

				/*
				 * for (Integer pj : updateCrawle_url_id) {
				 * dao.customUpdateProject(
				 * "update Crawle_url2 set c_new=1 where id =" + pj);
				 * dao1.customUpdateProject("update Qc_record qr set qr.m_id=" +
				 * (Integer) session2.getAttribute("uid") + ",  " +
				 * " qr.m_date='" + nowTime2() +
				 * "', qr.m_perform=2  where qr.crawl_url2_id =" + pj); }
				 */

				for (String pj : idListforupdate) {
					dao.customUpdateProject("update Crawle_url2 set c_new=1 where id in (" + pj + ")");
					dao1.customUpdateProject("update Qc_record qr set qr.m_id=" + (Integer) session2.getAttribute("uid")
							+ ",  " + " qr.m_date='" + nowTime2() + "', qr.m_perform=2 , browser_name= '"
							+ ua.getBrowser().toString() + "' , browser_version = "
							+ Integer.parseInt(ua.getBrowserVersion().getMajorVersion())
							+ " where qr.crawl_url2_id in (" + pj + ")");
				}

				logger.info("Qc_record created. again.");
				/**
				 * === update process perform....
				 */
				lst = null;
				qqury = null;
				ContentFilterData = null;
				updateCrawle_url_id = null;
				dataforupdate = null;
				idListforupdate = null;
			} catch (Exception e) {
				apple = null;
				dao = null;
				dao1 = null;
				dao3 = null;
				dataforupdate = null;
				logger.error("error is dataforupdate (2nd) is empty  " + e);
				e.printStackTrace();
			} finally {

				factory = null;
				ua = null;
				apple = null;
				dao = null;
				dao1 = null;
				dao3 = null;
				lst = null;
				ContentFilterData = null;
				updateCrawle_url_id = null;
				dataforupdate = null;
				idListforupdate = null;
				session2 = null;
			}

			execute();

			return "masterEmail";
		}
	}

	String dataforupdate = null;
	// List<Integer> idforupdate = null;
	List<String> idListforupdate = null;

	ArrayList<String> rows = null;

	/*
	 * csv export for
	 */

	String reportName = null;
	HSSFWorkbook wb = null;
	HSSFSheet sheet = null;
	HSSFRow rowhead = null;
	HSSFRow rowheadPre = null;

	public void downloadCSVFileAction(String header, String browsr) throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		String reportName = null;
		String outputString = null;
		Iterator<String> iter = null;
		try {
			response.setContentType("text/csv");
			// response.setCharacterEncoding("UTF-8");
			reportName = "email_import_" + clientname + "_" + nowTime1() + "_file.csv";
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);

			rows = new ArrayList<String>();

			rows.add("you are using " + browsr + " browser");
			rows.add("\n");
			rows.add("\n");
			rows.add(header);
			rows.add("\n");
			for (CommanReporting cr : ContentFilterData) {
				rows.add("\"" + cr.getId() + "\",\"" + cr.getLink() + "\",\"" + cr.getDomainName() + "\",\""
						+ cr.getGreylist() + "\",\"" + cr.getProjectName() + "\",\"" + cr.getFileExtension() + "\",\""
						+ cr.getNote1() + "\",\"" + cr.getNote2() + "\",\"" + cr.getLink001() + "\"");
				rows.add("\n");
			}

			iter = rows.iterator();
			while (iter.hasNext()) {
				outputString = (String) iter.next();
				response.getWriter().print(outputString);
				// response.getOutputStream().print(outputString);
				/**
				 * using getWriter() for prevent below error
				 * java.io.CharConversionException: Not an ISO 8859-1 character:
				 * ? outputstream do not convert some special characters
				 */
				outputString = null;
			}

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

	/**
	 * csv export for google tracker
	 */

	public void downloadCSVFileAction_gt(String header, String browsr) throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		String reportName = null;
		String outputString = null;
		try {
			response.setContentType("text/csv");
			// response.setCharacterEncoding("UTF-8");
			reportName = "googleTracker_" + clientname + "_" + nowTime1() + "_file.csv";
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);

			rows = new ArrayList<String>();
			rows.add("you are using " + browsr + " browser");
			rows.add("\n");
			rows.add("\n");
			rows.add(header);
			rows.add("\n");
			for (CommanReporting cr : ContentFilterData) {
				rows.add("\"" + cr.getProjectName() + "\",\"" + cr.getDate__c() + "\",\"" + cr.getGreylist() + "\""
						+ ",\"" + cr.getId() + "\",\"" + cr.getLink() + "\",\"" + cr.getDomainName() + "\"");
				// rows.add("\"" + cr.getId() + "\",\"" + cr.getLink() + "\",\""
				// + cr.getDomainName() + "\",\""
				// + cr.getGreylist() + "\"");
				rows.add("\n");
			}

			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				outputString = (String) iter.next();
				response.getWriter().print(outputString);
				// response.getOutputStream().print(outputString);
				/**
				 * using getWriter() for prevent below error
				 * java.io.CharConversionException: Not an ISO 8859-1 character:
				 * ? outputstream do not convert some special characters
				 */
				outputString = null;
			}

			// response.getOutputStream().flush();
			response.getWriter().flush();
			ContentFilterData = null;
			rows = null;
		} catch (Exception e) {
			response.getWriter().flush();
			ContentFilterData = null;
			rows = null;
			e.printStackTrace();
		} finally {
			response.getWriter().flush();
			ContentFilterData = null;
			response = null;
			rows = null;
			reportName = null;
			outputString = null;
		}
	}

	long jj = 0;
	Document document = null;
	IndexWriterConfig config = null;

	/**
	 * google tracker data export
	 * 
	 * @return
	 */

	public String googletrackerExportData() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			ua = UserAgent.parseUserAgentString(ServletActionContext.getRequest().getHeader("User-Agent"));
			try {
				dao = (Crawle_url2Dao) factory.getBean("dash");
				dao1 = (Qc_recordDao) factory.getBean("d13");
				lst = dao.viewRecord(
						"select  pi.project_name, pi.actual_hosted_site, cu.crawle_url2, cu.id, cu.project_id "
								+ " ,  cu.domain_name from Crawle_url2 cu,"
								+ " Project_info pi where   pi.id=cu.project_id and pi.project_type = " + projecttype
								+ " and  pi.client_type = " + clientname + " and cu.link_logger = 0   "
								+ " and cu.is_valid=0 and cu.w_list = 0 and cu.google_dmca_new = 0 and  cu.w_list_perform=1 "
								+ " and cu.created_on between adddate('" + startdate.trim() + "',-1) " + "and adddate('"
								+ startdate.trim() + "',1)",
						100000);
				// getRow = lst.size();
				ContentFilterData = new ArrayList<>();
				idListforupdate = new ArrayList<>();
				// idforupdate = new ArrayList<>();
				dataforupdate = ",";
				j = 0;
				for (int i = 0; i < lst.size(); i++) {
					j++;
					if (j >= 1000) {
						// System.out.println("entry in if...." + i);
						if (dataforupdate.startsWith(",")) {
							dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
						}
						// System.out.println("propertyids....11....." +
						// propertyids);
						if (dataforupdate.endsWith(",")) {
							dataforupdate = (String) dataforupdate.substring(0, dataforupdate.length() - 1);
						}
						idListforupdate.add(dataforupdate);
						j = 1;
						dataforupdate = ",";
					}
					Object[] obj = (Object[]) lst.get(i);
					// Crawle_url2 cu = new Crawle_url2();
					cr = new CommanReporting();
					cr.setProjectName(obj[0].toString()); // project name
					cr.setDate__c(obj[1].toString()); // actual host site...
					cr.setLink(obj[2].toString()); // craule_url 2
					cr.setId((Integer) obj[3]);
					cr.setGreylist((Integer) obj[4]); // project id
					cr.setDomainName(obj[5].toString()); //
					ContentFilterData.add(cr);
					dataforupdate = dataforupdate + (Integer) obj[3] + ",";

					// idforupdate.add((Integer) obj[3]);
					// dao.customUpdateProject(
					// "update Crawle_url2 set google_dmca_new=1 , c_new=1 where
					// id= " + (Integer) obj[3]);
					// System.out.println("............." + i);
					obj = null;
					cr = null;
				}

				downloadCSVFileAction_gt("Project Name, Actual Host Link,Project ID,Crawl ID, Infringing Link, Domain ",
						ua.getBrowser().toString());

				if (dataforupdate.startsWith(",")) {
					dataforupdate = (String) dataforupdate.substring(1, dataforupdate.length());
				}
				// System.out.println("propertyids....11....." + propertyids);
				if (dataforupdate.endsWith(",")) {
					dataforupdate = (String) dataforupdate.substring(0, dataforupdate.length() - 1);
				}
				idListforupdate.add(dataforupdate);

				/**
				 * ========= user browser detail===========
				 */

				for (String ppj : idListforupdate) {
					dao.customUpdateProject(
							"update Crawle_url2 set google_dmca_new=1 , c_new=1 where id in (" + ppj + ")");

					dao1.customUpdateProject("update Qc_record set gt_user_id= "
							+ (Integer) session2.getAttribute("uid") + " " + " , gt_date= '" + nowTime2()
							+ "' , gt_perform=1 , browser_name= '" + ua.getBrowser().toString()
							+ "' , browser_version = " + Integer.parseInt(ua.getBrowserVersion().getMajorVersion())
							+ " where crawl_url2_id in (" + ppj + ")");

				}
				execute();
				idListforupdate = null;
				lst = null;
				ContentFilterData = null;

				dao = null;
				dao1 = null;
				lst = null;
				factory = null;
				session2 = null;

			} catch (Exception e) {
				e.printStackTrace();
				session2 = null;
				idListforupdate = null;
				ContentFilterData = null;
				dao = null;
				return ERROR;
			} finally {
				idListforupdate = null;
				lst = null;
				ContentFilterData = null;
				execute();
				dao = null;
				dao1 = null;
				lst = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	UserAgent ua = null;
	Version browserVersion = null;

	Set<Integer> propertyid = null;
	Set<Integer> projname = null;
	String configFile = null;
	String directory = null;
	String templatename = null;

	public List<Project_info> getPropinfo() {
		return propinfo;
	}

	public void setPropinfo(List<Project_info> propinfo) {
		this.propinfo = propinfo;
	}

	public String nowTime1() {
		String time__c = null;
		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			date = new Date();
			// System.out.println(dateFormat.format(date));
			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);
			dateFormat = null;
			date = null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("date format error ...", e);
		} finally {
			date = null;
			dateFormat = null;
		}
		return time__c;
	}

	DateFormat dateFormat = null;
	Date date = null;
	String time__c = null;

	public String nowTime2() {

		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = new Date();
			// System.out.println(dateFormat.format(date));
			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("date format error ...", e);
		} finally {
			date = null;
			dateFormat = null;
		}
		return time__c;
	}

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
			date = null;
			dateFormat = null;
		}
		return time__c;
	}

	public List<Markscan_projecttype> getListData() {
		return listData;
	}

	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

	List<Module_wise_email_template> templateData = null;
	int clientname;
	int propertyName;
	int datatype;
	int projecttype;
	String uniqprojectName = "";
	String uniqprojectType = null;
	String apple = null;
	List<CommanReporting> ContentFilterData = null;
	List<YT_video_detail> yt_Data = null;
	String socialmedia__c = "false";

	public List<YT_video_detail> getYt_Data() {
		return yt_Data;
	}

	public void setYt_Data(List<YT_video_detail> yt_Data) {
		this.yt_Data = yt_Data;
	}

	public String getSocialmedia__c() {
		return socialmedia__c;
	}

	public void setSocialmedia__c(String socialmedia__c) {
		this.socialmedia__c = socialmedia__c;
	}

	public String getApple() {
		return apple;
	}

	public void setApple(String apple) {
		this.apple = apple;
	}

	public String getUniqprojectName() {
		return uniqprojectName;
	}

	public void setUniqprojectName(String uniqprojectName) {
		this.uniqprojectName = uniqprojectName;
	}

	String startdate = null;

	public int getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public int getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(int propertyName) {
		this.propertyName = propertyName;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public int getClientname() {
		return clientname;
	}

	public void setClientname(int clientname) {
		this.clientname = clientname;
	}

	public List<CommanReporting> getQcData() {
		return qcData;
	}

	public void setQcData(List<CommanReporting> qcData) {
		this.qcData = qcData;
	}

	String id = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	int uid;
	String invlink = null;

	public String getInvlink() {
		return invlink;
	}

	public void setInvlink(String invlink) {
		this.invlink = invlink;
	}

	int totalrowCount;
	String ptype_name = null;
	String clientname_name = null;
	String propertyName_name = null;
	String datatype_name = null;
	String browser_name = null;

	public String getBrowser_name() {
		return browser_name;
	}

	public void setBrowser_name(String browser_name) {
		this.browser_name = browser_name;
	}

	public int getGetRow() {
		return getRow;
	}

	public void setGetRow(int getRow) {
		this.getRow = getRow;
	}

	public int getTotalrowCount() {
		return totalrowCount;
	}

	public void setTotalrowCount(int totalrowCount) {
		this.totalrowCount = totalrowCount;
	}

	public String getPtype_name() {
		return ptype_name;
	}

	public void setPtype_name(String ptype_name) {
		this.ptype_name = ptype_name;
	}

	public String getClientname_name() {
		return clientname_name;
	}

	public void setClientname_name(String clientname_name) {
		this.clientname_name = clientname_name;
	}

	public String getPropertyName_name() {
		return propertyName_name;
	}

	public void setPropertyName_name(String propertyName_name) {
		this.propertyName_name = propertyName_name;
	}

	public String getDatatype_name() {
		return datatype_name;
	}

	public void setDatatype_name(String datatype_name) {
		this.datatype_name = datatype_name;
	}

	public List<CommanReporting> getContentFilterData() {
		return ContentFilterData;
	}

	public void setContentFilterData(List<CommanReporting> contentFilterData) {
		ContentFilterData = contentFilterData;
	}

	public Set<Integer> getProject_id__c() {
		return project_id__c;
	}

	public void setProject_id__c(Set<Integer> project_id__c) {
		this.project_id__c = project_id__c;
	}

	public int indexedSearch_Two(String link__c, String project_Id__c, String email__c, int r_id_cc, String path) {
		try {
			// System.out.println("index searching. 2....");
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

			logger.info("Found " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);

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

			}
			analyzer2 = null;
			queryParser = null;
			reader = null;
			searcher = null;
			tq = null;
			results = null;
			hits = null;
			// ii = hits.length;
		} catch (Exception e) {
			logger.error("error on data indexsearch");

		} finally {
			analyzer2 = null;
			queryParser = null;
			q = null;
			reader = null;
			searcher = null;
			tq = null;
			results = null;
		}
		return hit;
	}

	Analyzer analyzer2 = null;
	QueryParser queryParser = null;
	Query q = null;
	int hitsPerPage = 10;
	IndexReader reader = null;
	IndexSearcher searcher = null;
	TermQuery tq = null;
	TopDocs results = null;
	int hit = 0;
	String combined_link__c = null;
	String indexPath2, retuen_id = null;

	public String invalidMark() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			return SUCCESS;
		}
	}

	String reasn = null;

	@SuppressWarnings("finally")
	public String invalid_Marked() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			invlink = invlink.trim();
			if (invlink.endsWith(",")) {
				invlink = invlink.substring(0, invlink.length() - 1);
			}
			Crawle_url2Dao dao2 = (Crawle_url2Dao) factory.getBean("dash");
			Qc_recordDao dao1 = (Qc_recordDao) factory.getBean("d13");
			try {

				dao2.customUpdateProject(
						"update Crawle_url2 set status = 1, is_valid = 1 where id in ( " + invlink + " ) ");
				dao1.customUpdateProject("update Qc_record set is_valid= " + (Integer) session2.getAttribute("uid")
						+ " ,  is_valid_time='" + nowTime2() + "' , is_valid_reason= '" + reasn
						+ "' where crawl_url2_id in ( " + invlink + " )");

				browser_name = "Links mark invalid Successfully";

			} catch (Exception e) {
				e.printStackTrace();
				browser_name = "Fail...\n Fail...\n Links mark invalid not Success.....";
			} finally {
				dao2 = null;
				dao1 = null;
				invlink = null;
				reasn = null;
				factory = null;
				session2 = null;
				return SUCCESS;
			}

		}

	}

	public String getReasn() {
		return reasn;
	}

	public void setReasn(String reasn) {
		this.reasn = reasn;
	}

	public String linktakedown() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			return SUCCESS;
		}
	}

	@SuppressWarnings("finally")
	public String linktakedownUpdate() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			invlink = invlink.trim();
			if (invlink.startsWith(",")) {
				invlink.substring(1, invlink.length());
			}
			if (invlink.endsWith(",")) {
				invlink.substring(0, invlink.length() - 1);
			}
			String count[] = invlink.split(",");

			Qc_recordDao dao1 = (Qc_recordDao) factory.getBean("d13");
			try {
				int x = dao1.customUpdateProject("update Qc_record set take_down=1 ,  take_down_time='" + nowTime2()
						+ "' , take_down_by= " + (Integer) session2.getAttribute("uid") + " where crawl_url2_id in ( "
						+ invlink + " )");

				if (x == count.length) {
					browser_name = " Success..... Links update";
				} else if (x == 0) {
					browser_name = " Error in Links update, please check your data";
				} else {
					browser_name = " Some Links not update, please check your data";
				}

			} catch (Exception e) {
				e.printStackTrace();
				browser_name = " Fail..... Links not update";
			} finally {
				count = null;
				factory = null;
				session2 = null;
				dao1 = null;
				session2 = null;
				reasn = null;
				return SUCCESS;
			}
		}
	}

}
