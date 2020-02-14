package com.markscan.project.classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.DashBoardData;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Qc_record;
import com.markscan.project.beans.Url_email_qc;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Markscan_usersDao;
import com.markscan.project.dao.Productivity_client_wiseDao;
import com.markscan.project.dao.Productivity_enfo_user_wiseDao;
import com.markscan.project.dao.Productivity_property_wiseDao;
import com.markscan.project.dao.Productivity_user_wiseDao;
import com.markscan.project.dao.Qc_recordDao;
import com.markscan.project.dao.Url_email_qcDao;
import com.opensymphony.xwork2.ActionSupport;

public class AdminDashBoard extends ActionSupport {

	public AdminDashBoard() {
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = Logger.getLogger(AdminDashBoard.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	List lst1 = null;
	List lst0 = null;
	Object[] obj = null;
	DashBoardData data = null;
	Markscan_projecttype url2 = null;
	Markscan_users url3 = null;

	public String execute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			try {
				monthlyGraph = new ArrayList<>();
				pcw = (Productivity_client_wiseDao) factory.getBean("d30");
				lst = pcw.viewRecord("SELECT  curr_date, sum(source_link)  ,  sum(infringing_link)  ,"
						+ " sum(youtube)  , sum(social_media)  ,sum(white_list)  ,sum(grey_list)  "
						+ " FROM Productivity_client_wise  where curr_date > ADDDATE('" + dayDate()
						+ "',-30) GROUP BY curr_date ");
				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);

					data = new DashBoardData();
					data.setDate(obj[0].toString());
					data.setSource(((Long) obj[1]).intValue());
					data.setInfringing(((Long) obj[2]).intValue());

					data.setYoutube(((Long) obj[3]).intValue());
					data.setSocial_media(((Long) obj[4]).intValue());

					data.setWhite_list(((Long) obj[5]).intValue());
					data.setGrey_list(((Long) obj[6]).intValue());
					monthlyGraph.add(data);
					obj = null;
					data = null;
				}
				lst = null;
				pcw = null;
			} catch (NullPointerException e) {
				return LOGIN;
			}
			
			catch (Exception e) {
				e.printStackTrace();
				logger.error("user graph error .....");
			} finally {
				obj = null;
				data = null;
				pcw = null;
				lst = null;
			}

			try {

				dao_p = (Markscan_projecttypeDao) factory.getBean("d8");

				lst = dao_p.viewRecord("select id, name from Markscan_projecttype");
				// System.out.println(".......pradeep........" + lst.size());
				logger.info(".......pradeep........" + lst.size());
				listData = new ArrayList<Markscan_projecttype>();

				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_projecttype();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					listData.add(url2);
					obj = null;
					url2 = null;
				}
				dao_p = null;
				lst = null;
			} catch (Exception e) {
				logger.error("project type data get ", e);
				return ERROR;
			} finally {
				dao_p = null;
				lst = null;
				obj = null;
				url2 = null;
			}

			try {
				 factory = LoginAndSession.getFactory();
				dao_u = (Markscan_usersDao) factory.getBean("d9");
				lst = dao_u.viewRecord("select id, name from Markscan_users where status= 1 and team =1");
				usrData = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					url3 = new Markscan_users();
					obj = (Object[]) lst.get(i);
					url3.setId((Integer) obj[0]);
					url3.setName((String) obj[1]);
					usrData.add(url3);
					url3 = null;
					obj = null;
				}
				lst = null;
				dao_u = null;
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			} finally {
				url3 = null;
				obj = null;
				data = null;
				factory = null;
				pcw = null;
				lst = null;
				dao_u = null;
				session2 = null;
			}
			session2 = null;
			factory = null;
			System.gc();
			return SUCCESS;
		}
	}

	Qc_recordDao qc_dao = null;
	Url_email_qcDao url_qc_dao = null;
	Qc_record url4 = null;

	public String executeEnforcement() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			execute();
			factory = LoginAndSession.getFactory();
			try {
				 factory = LoginAndSession.getFactory();
				dao_u = (Markscan_usersDao) factory.getBean("d9");
				lst = dao_u.viewRecord("select id, name from Markscan_users where status= 1 and team =2");
				usrData = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					url3 = new Markscan_users();
					obj = (Object[]) lst.get(i);
					url3.setId((Integer) obj[0]);
					url3.setName((String) obj[1]);
					usrData.add(url3);
					obj = null;
					url3 = null;
				}
				lst = null;
				dao_u = null;
			} catch (Exception e) {
				logger.error("get user error ", e);
				return LOGIN;
			} finally {
				lst = null;
				dao_u = null;
				obj = null;
				url3 = null;
			}

			qc_dao = (Qc_recordDao) factory.getBean("d13");
			url_qc_dao = (Url_email_qcDao) factory.getBean("d23");
			try { // pmqcPending
				lst = qc_dao
						.viewRecord("select count(qr.id), qr.created_on, qr.client_id ,cm.client_name, mp.name from "
								+ " Qc_record qr , Client_master cm, Markscan_projecttype mp where cm.id=qr.client_id  and "
								+ " mp.id = cm.project_type and qr.user_id_pm=0 and qr.is_valid=0 and qr.bypass_link=0 group by "
								+ " qr.created_on, client_id");
				pmqcPending = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					url4 = new Qc_record();
					obj = (Object[]) lst.get(i);
					url4.setId(((Long) obj[0]).intValue());
					url4.setCreated_on((String) obj[1]);
					url4.setBrowser_name((String) obj[3]);// client_name
					url4.setDel_reason((String) obj[4]);// project_type
					pmqcPending.add(url4);
					url4 = null;
					obj = null;
				}
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
				url4 = null;
				obj = null;

			}

			try { // opsqcPending
				lst = qc_dao
						.viewRecord("select count(qr.id), qr.created_on, qr.client_id ,cm.client_name, mp.name from "
								+ " Qc_record qr , Client_master cm , Markscan_projecttype mp where mp.id=cm.project_type and  "
								+ " cm.id= qr.client_id and  qr.user_id_pm !=0 and qr.user_id_ops = 0 and qr.is_valid=0 and "
								+ " qr.bypass_link=0 group by qr.created_on, client_id");
				opsqcPending = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					url4 = new Qc_record();
					obj = (Object[]) lst.get(i);
					url4.setId(((Long) obj[0]).intValue());
					url4.setCreated_on((String) obj[1]);
					url4.setBrowser_name((String) obj[3]);// client_name
					url4.setDel_reason((String) obj[4]);// project_type
					opsqcPending.add(url4);
					url4 = null;
					obj = null;
				}
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
				url4 = null;
				obj = null;
			}

			try { // masterEmailPending
				lst = qc_dao
						.viewRecord("select count(qr.id), qr.created_on, qr.client_id, cm.client_name, mp.name from "
								+ " Qc_record qr, Client_master cm, Markscan_projecttype mp where cm.id=qr.client_id  and "
								+ " mp.id = cm.project_type and qr.user_id_ops != 0 and qr.is_valid=0 and qr.bypass_link=0 and "
								+ " qr.m_id=0 and qr.link_logger =1 group by qr.created_on, client_id ");
				masterEmailPending = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					url4 = new Qc_record();
					obj = (Object[]) lst.get(i);
					url4.setId(((Long) obj[0]).intValue());
					url4.setCreated_on((String) obj[1]);
					url4.setBrowser_name((String) obj[3]);// client_name
					url4.setDel_reason((String) obj[4]);// project_type
					masterEmailPending.add(url4);
					url4 = null;
					obj = null;
				}
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
				url4 = null;
				obj = null;
			}

			try { // googleTrackerPending
				lst = qc_dao
						.viewRecord("select count(qr.id), qr.created_on, qr.client_id, cm.client_name, pm.name from "
								+ " Qc_record qr, Client_master cm, Markscan_projecttype pm where  cm.id= qr.client_id and "
								+ " pm.id=cm.project_type and qr.user_id_ops != 0 and qr.is_valid=0 and qr.bypass_link=0 and "
								+ " qr.gt_user_id=0 and qr.link_logger =0 group by qr.created_on, client_id ");
				googleTrackerPending = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					url4 = new Qc_record();
					obj = (Object[]) lst.get(i);
					url4.setId(((Long) obj[0]).intValue());
					url4.setCreated_on((String) obj[1]);
					url4.setBrowser_name((String) obj[3]);// client_name
					url4.setDel_reason((String) obj[4]);// project_type
					googleTrackerPending.add(url4);
					url4 = null;
					obj = null;
				}
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
				url4 = null;
				obj = null;
			}

			try { // emailQCPending
				lst = qc_dao.viewRecord("select count(id) from Url_email_qc where email_qc=0 and invalid = 0");

				for (int i = 0; i < lst.size(); i++) {
					mailQCPending = ((Long) lst.get(0)).intValue();
				}
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
			}

			try { // mailShootPending
				lst = qc_dao
						.viewRecord("select count(qc.id), qc.created_on, cm.client_name, mp.name from "
								+ " Url_email_qc qc, Url_email ue, Project_info pi, Client_master cm, Markscan_projecttype mp "
								+ " where ue.id=qc.url_email_id and pi.id=ue.project_id and cm.id=pi.client_type and "
								+ " mp.id=cm.project_type and qc.email_send_by=0 and qc.invalid = 0 and qc.email_qc=1"
								+ " group by qc.created_on,cm.id ");
				mailShootPending = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					url5 = new Url_email_qc();
					obj = (Object[]) lst.get(i);
					url5.setId(((Long) obj[0]).intValue());
					url5.setCreated_on((String) obj[1]);
					url5.setEdit_field((String) obj[2]);// client_name
					url5.setEdit_date((String) obj[3]);// project_type
					mailShootPending.add(url5);
					url5 = null;
					obj = null;
				}
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
				url5 = null;
				obj = null;
			}

		}
		factory = null;
		session2 = null;
		System.gc();
		return SUCCESS;
	}

	Url_email_qc url5 = null;
	Productivity_user_wiseDao dao_userWise = null;
	Productivity_property_wiseDao dao_propwise = null;
	Qc_recordDao dao_qc_record = null;
	Crawle_url2Dao cud = null;
	Object[] obj1 = null;
	Object[] obj0 = null;
	Crawle_url2 craw = null;

	// String project_ids = null;
	// String client_names = null;
	public String report() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			dao_userWise = (Productivity_user_wiseDao) factory.getBean("d27");
			dao_propwise = (Productivity_property_wiseDao) factory.getBean("d29");
			pcw = (Productivity_client_wiseDao) factory.getBean("d30");
			if (colorRadio.equalsIgnoreCase("red")) {

				// for all user
				try {
					lst = dao_userWise
							.viewRecord("select puw.curr_date, puw.source_link, puw.infringing_link, puw.youtube,"
									+ "  puw.social_media, puw.white_list, puw.grey_list, puw.duplicate , mu.name"
									+ " , puw.facebook, puw.instagram, puw.twitter, puw.vk, puw.periscope, puw.source_domain,"
									+ "  puw.infringing_domain , puw.user_id "
									+ "  from  Productivity_user_wise puw, Markscan_users mu where puw.curr_date "
									+ " between '" + date + "' and '" + date1 + "' and mu.id= puw.user_id");
					monthlyGraph = new ArrayList<>();

					cud = (Crawle_url2Dao) factory.getBean("dash");

					// lst1= cud.viewRecord("select DISTINCT count(domain_name)
					// ,date(created_on), link_logger from crawle_url3 where
					// user_id= 75 and created_on >'2017-06-01' and
					// link_logger=1 group by date(created_on)")

					for (int i = 0; i < lst.size(); i++) {
						 obj = (Object[]) lst.get(i);

						  data = new DashBoardData();

						data.setDate(obj[0].toString());
						data.setSource((Integer) obj[1]);
						data.setInfringing((Integer) obj[2]);

						data.setYoutube((Integer) obj[3]);
						data.setSocial_media((Integer) obj[4]);

						data.setWhite_list((Integer) obj[5]);
						data.setGrey_list((Integer) obj[6]);
						data.setDuplicate((Integer) obj[7]);
						data.setProj_name(obj[8].toString()); // user_name

						data.setFacebook((Integer) obj[9]);
						data.setInstagram((Integer) obj[10]);
						data.setTwitter((Integer) obj[11]);
						data.setVk((Integer) obj[12]);
						data.setPeriscope((Integer) obj[13]);
						data.setSource_domain((Integer) obj[14]);
						data.setInfringing_domain((Integer) obj[15]);
						data.setUser_id((Integer) obj[16]);
						lst1 = dao_propwise.viewRecord(
								"select DISTINCT cm.client_name , mp.name , cm.id from Productivity_property_wise ppw, "
										+ " Project_info pi, Client_master cm, Markscan_projecttype mp where "
										+ " ppw.project_id= pi.id and "
										+ "  pi.client_type= cm.id and  pi.project_type  = mp.id and ppw.user_id ="
										+ (Integer) obj[16] + " and " + " ppw.created_on = '" + obj[0].toString()
										+ "'");
						in_side = new ArrayList<>();
						for (int i1 = 0; i1 < lst1.size(); i1++) {
							  obj1 = (Object[]) lst1.get(i1);

							lst0 = dao_propwise.viewRecord("select  sum( productivi0_.source_count), "
									+ " sum( productivi0_.infringing_count),sum( productivi0_.youtube), "
									+ " sum( productivi0_.facebook), sum(productivi0_.instagram), "
									+ " sum(productivi0_.twitter),sum(productivi0_.vk), sum(productivi0_.periscope) "
									+ " from Productivity_property_wise productivi0_, Project_info project_in1_ "
									+ " where project_in1_.id=productivi0_.project_id and " + " productivi0_.user_id="
									+ (Integer) obj[16] + " and " + " productivi0_.created_on='" + obj[0].toString()
									+ "' and " + "project_in1_.client_type=" + (Integer) obj1[2]);

							for (int i0 = 0; i0 < lst0.size(); i0++) {
								  obj0 = (Object[]) lst0.get(i0);

								  craw = new Crawle_url2();
								craw.setWid(obj1[0].toString()); // client_name
								craw.setIs_valid(((Long) obj0[0]).intValue());// source
								craw.setStatus(((Long) obj0[1]).intValue());// infringing
								craw.setUser_id(((Long) obj0[2]).intValue());// youtube
								craw.setProject_id(((Long) obj0[3]).intValue());// FB
								craw.setType(((Long) obj0[4]).intValue());// instagram
								craw.setVerified(((Long) obj0[5]).intValue());// twitter
								craw.setSite_down(((Long) obj0[6]).intValue());// vk
								craw.setIs_new(((Long) obj0[7]).intValue());// pscp

								in_side.add(craw);
								obj0=null;
								craw=null;
							}
							obj1=null;
							lst0 = null;
						}
						// System.out.println(client_names);
						data.setInside(in_side);

						monthlyGraph.add(data);
						lst1 = null;
						data=null;
								obj=null;
						
					}
					lst = null;
					lst1 = null;
					lst0 = null;
					dao_userWise = null;
					dao_propwise = null;

				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
				finally{
					obj0=null;
					craw=null;
					obj1=null;
					lst0 = null;
					lst1 = null;
					data=null;
							obj=null;
							dao_userWise = null;
							dao_propwise = null;
							lst = null;
							factory=null;
							dao_userWise=null;
							dao_propwise=null;
							session2=null;
				}
				System.gc();
				return "allEmpReport";
			} else if (colorRadio.equalsIgnoreCase("green")) {
				// for single user
				try {
					lst = dao_userWise
							.viewRecord("select puw.curr_date, puw.source_link, puw.infringing_link, puw.youtube, "
									+ " puw.social_media, puw.white_list, puw.grey_list, puw.duplicate ,  puw.facebook,"
									+ "  puw.instagram, puw.twitter, puw.vk, puw.periscope, puw.source_domain, "
									+ " puw.infringing_domain from " + " Productivity_user_wise puw where puw.user_id="
									+ usertype + " and puw.curr_date between" + "  '" + date + "' and '" + date1 + "'");
					monthlyGraph = new ArrayList<>();

					for (int i = 0; i < lst.size(); i++) {
						  obj = (Object[]) lst.get(i);

						  data = new DashBoardData();
						data.setDate(obj[0].toString());
						data.setSource((Integer) obj[1]);
						data.setInfringing((Integer) obj[2]);

						data.setYoutube((Integer) obj[3]);
						data.setSocial_media((Integer) obj[4]);

						data.setWhite_list((Integer) obj[5]);
						data.setGrey_list((Integer) obj[6]);
						data.setDuplicate((Integer) obj[7]);

						data.setFacebook((Integer) obj[8]);
						data.setInstagram((Integer) obj[9]);
						data.setTwitter((Integer) obj[10]);
						data.setVk((Integer) obj[11]);
						data.setPeriscope((Integer) obj[12]);
						data.setSource_domain((Integer) obj[13]);
						data.setInfringing_domain((Integer) obj[14]);

						lst1 = dao_propwise.viewRecord(
								"select DISTINCT cm.client_name , mp.name , cm.id from Productivity_property_wise ppw, "
										+ " Project_info pi, Client_master cm, Markscan_projecttype mp where "
										+ " ppw.project_id= pi.id and "
										+ "  pi.client_type= cm.id and  pi.project_type  = mp.id and ppw.user_id ="
										+ usertype + " and " + " ppw.created_on = '" + obj[0].toString() + "'");
						in_side = new ArrayList<>();
						for (int i1 = 0; i1 < lst1.size(); i1++) {
							 obj1 = (Object[]) lst1.get(i1);

							lst0 = dao_propwise.viewRecord("select  sum( productivi0_.source_count), "
									+ " sum( productivi0_.infringing_count),sum( productivi0_.youtube), "
									+ " sum( productivi0_.facebook), sum(productivi0_.instagram), "
									+ " sum(productivi0_.twitter),sum(productivi0_.vk), sum(productivi0_.periscope) "
									+ " from Productivity_property_wise productivi0_, Project_info project_in1_ "
									+ " where project_in1_.id=productivi0_.project_id and " + " productivi0_.user_id="
									+ usertype + " and " + " productivi0_.created_on='" + obj[0].toString() + "' and "
									+ "project_in1_.client_type=" + (Integer) obj1[2]);

							for (int i0 = 0; i0 < lst0.size(); i0++) {
								  obj0 = (Object[]) lst0.get(i0);

								  craw = new Crawle_url2();
								craw.setWid(obj1[0].toString()); // client_name
								craw.setIs_valid(((Long) obj0[0]).intValue());// source
								craw.setStatus(((Long) obj0[1]).intValue());// infringing
								craw.setUser_id(((Long) obj0[2]).intValue());// youtube
								craw.setProject_id(((Long) obj0[3]).intValue());// FB
								craw.setType(((Long) obj0[4]).intValue());// instagram
								craw.setVerified(((Long) obj0[5]).intValue());// twitter
								craw.setSite_down(((Long) obj0[6]).intValue());// vk
								craw.setIs_new(((Long) obj0[7]).intValue());// pscp

								in_side.add(craw);
								obj0=null;
								craw=null;
								
							}
							obj1=null;
							lst0=null;
						}
						data.setInside(in_side);
						monthlyGraph.add(data);
						obj=null;
						data= null;
					}
					lst = null;
					lst1 = null;
					lst0 = null;
					dao_userWise = null;
					dao_propwise = null;
				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
				finally
				{
					lst = null;
					lst1 = null;
					lst0 = null;
					dao_userWise = null;
					dao_propwise = null;
					data=null;
					craw= null;
					factory=null;
					dao_userWise=null;
					dao_propwise=null;
					session2=null;
				}
				System.gc();
				return "empReport";
			} else if (colorRadio.equalsIgnoreCase("blue")) {
				// for client wise
				try {

					lst = pcw.viewRecord("select pcw.curr_date, pcw.source_link, pcw.infringing_link, pcw.youtube,"
							+ "  pcw.social_media, pcw.white_list, pcw.grey_list , pcw.facebook, pcw.instagram, pcw.twitter,"
							+ "  pcw.vk, pcw.periscope, pcw.source_domain, pcw.infringing_domain from Productivity_client_wise "
							+ " pcw where pcw.curr_date between '" + date + "' and '" + date1 + "' and pcw.client_id="
							+ clientname);
					monthlyGraph = new ArrayList<>();

					for (int i = 0; i < lst.size(); i++) {
						  obj = (Object[]) lst.get(i);

						  data = new DashBoardData();
						data.setDate(obj[0].toString());
						data.setSource((Integer) obj[1]);
						data.setInfringing((Integer) obj[2]);

						data.setYoutube((Integer) obj[3]);
						data.setSocial_media((Integer) obj[4]);

						data.setWhite_list((Integer) obj[5]);
						data.setGrey_list((Integer) obj[6]);

						data.setFacebook((Integer) obj[7]);
						data.setInstagram((Integer) obj[8]);
						data.setTwitter((Integer) obj[9]);
						data.setVk((Integer) obj[10]);
						data.setPeriscope((Integer) obj[11]);

						data.setSocial_media((Integer) obj[7] + (Integer) obj[8] + (Integer) obj[9] + (Integer) obj[10]
								+ (Integer) obj[11]);

						data.setSource_domain((Integer) obj[12]);
						data.setInfringing_domain((Integer) obj[13]);

						lst1 = dao_propwise.viewRecord(
								"select DISTINCT mu.name, ppw.created_on ,ppw.user_id from Productivity_property_wise ppw ,"
										+ "  Markscan_users mu where mu.id= ppw.user_id and ppw.client_id = "
										+ clientname + " and ppw.created_on='" + obj[0].toString() + "' ");
						in_side = new ArrayList<>();
						for (int i1 = 0; i1 < lst1.size(); i1++) {
							  obj1 = (Object[]) lst1.get(i1);

							lst0 = dao_propwise.viewRecord(" select  sum( productivi0_.source_count),"
									+ " sum(productivi0_.infringing_count),sum( productivi0_.youtube), "
									+ " sum(productivi0_.facebook),sum(productivi0_.instagram),sum(productivi0_.twitter) "
									+ " ,sum(productivi0_.vk),sum( productivi0_.periscope) from "
									+ " Productivity_property_wise productivi0_, Markscan_users markscan_u1_ where "
									+ " markscan_u1_.id=productivi0_.user_id and productivi0_.client_id= " + clientname
									+ " and productivi0_.created_on='" + obj[0].toString() + "' and "
									+ " productivi0_.user_id=" + (Integer) obj1[2]);

							for (int i0 = 0; i0 < lst0.size(); i0++) {
								 obj0 = (Object[]) lst0.get(i0);

								  craw = new Crawle_url2();
								craw.setWid(obj1[0].toString()); // client_name
								craw.setIs_valid(((Long) obj0[0]).intValue());// source
								craw.setStatus(((Long) obj0[1]).intValue());// infringing
								craw.setUser_id(((Long) obj0[2]).intValue());// youtube
								craw.setProject_id(((Long) obj0[3]).intValue());// FB
								craw.setType(((Long) obj0[4]).intValue());// instagram
								craw.setVerified(((Long) obj0[5]).intValue());// twitter
								craw.setSite_down(((Long) obj0[6]).intValue());// vk
								craw.setIs_new(((Long) obj0[7]).intValue());// pscp

								in_side.add(craw);
								obj0=null;
								craw=null;
							}
							obj1=null;
							lst0=null;
						}

						data.setInside(in_side);

						monthlyGraph.add(data);
						obj= null;
						data= null;
					}
					pcw = null;
					lst = null;
					lst1 = null;
					lst0 = null;
					dao_userWise = null;
					dao_propwise = null;

				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
				finally{
					pcw = null;
					lst = null;
					lst1 = null;
					lst0 = null;
					dao_userWise = null;
					dao_propwise = null;
					obj=null;
					data=null;
					craw=null;
					factory=null;
					dao_userWise=null;
					dao_propwise=null;
					session2=null;
				}
				System.gc();
				return "clientReport";
			}

			factory=null;
			dao_userWise=null;
			dao_propwise=null;
			session2=null;
			System.gc();
			return SUCCESS;
		}
	}

	List<Crawle_url2> in_side = null;

	Productivity_enfo_user_wiseDao pue_Dao = null;

	public String reportEnfo() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			url_qc_dao = (Url_email_qcDao) factory.getBean("d23");
			dao_qc_record = (Qc_recordDao) factory.getBean("d13");
			pue_Dao = (Productivity_enfo_user_wiseDao) factory.getBean("d31");
			if (colorRadio.equalsIgnoreCase("red")) {
				// all user
				try {
					lst = pue_Dao.viewRecord("select sum(pe.opsQC) as col_0_0_, sum(pe.masterEmail_manual) as col_1_0_,"
							+ " sum(pe.masterEmail_system) as col_2_0_, sum(pe.googleTracker) as col_3_0_, sum(pe.emailQC) "
							+ " as col_4_0_, sum(pe.mailShoot) as col_5_0_, sum(pe.mailShootLink) as col_6_0_, pe.created_on "
							+ " as col_7_0_, pe.user_id as col_8_0_, pe.client_id as col_9_0_, mu.name from "
							+ " Productivity_enfo_user_wise pe, Markscan_users mu where mu.id = pe.user_id and  "
							+ " pe.created_on between '" + date + "' and '" + date1
							+ "' group by pe.created_on , pe.user_id  ");

					monthlyGraph = new ArrayList<>();
					for (int i = 0; i < lst.size(); i++) {
						  obj = (Object[]) lst.get(i);
						  data = new DashBoardData();

						if (!obj[10].toString().trim().equalsIgnoreCase("administrator")) {
							data.setSource(((Long) obj[0]).intValue()); // opsqc
							data.setInfringing(((Long) obj[1]).intValue()); // masteremail_manual
							data.setInvalid(((Long) obj[2]).intValue()); // masteremail_system
							data.setLink_count(((Long) obj[3]).intValue()); // googletracker
							data.setWhite_list(((Long) obj[4]).intValue()); // emailQC
							data.setGrey_list(((Long) obj[5]).intValue()); // mailsoot
							data.setDeleted(((Long) obj[6]).intValue()); // totalmailshootlink

							data.setDate(obj[7].toString());
							data.setUser_id((Integer) obj[9]);
							data.setClient_name(obj[10].toString()); // user_name

							lst1 = pue_Dao
									.viewRecord("select sum(pe.opsQC)as opsqc, sum(pe.masterEmail_manual)as ME_manual, "
											+ " sum(pe.masterEmail_system)as ME_system , sum(pe.googleTracker)as GT, "
											+ " sum(pe.emailQC)as Eqc, sum(pe.mailShoot)as mailshoot, sum(pe.mailShootLink)as "
											+ " total_mailshoot, pe.created_on, cm.client_name, mp.name from "
											+ " Productivity_enfo_user_wise pe "
											+ " , Client_master cm, Markscan_projecttype mp where  cm.id= pe.client_id and "
											+ "  mp.id= cm.project_type and pe.created_on ='" + obj[7].toString()
											+ "' and " + " pe.user_id=" + (Integer) obj[8] + " group by "
											+ " pe.client_id ");
							in_side = new ArrayList<>();
							for (int i1 = 0; i1 < lst1.size(); i1++) {
								  obj1 = (Object[]) lst1.get(i1);
								  craw = new Crawle_url2();
								  craw.setIs_valid(((Long) obj1[0]).intValue());// opsqc
								  craw.setStatus(((Long) obj1[1]).intValue());// ME_manual
								  craw.setUser_id(((Long) obj1[2]).intValue());// ME_system
								  craw.setProject_id(((Long) obj1[3]).intValue());// googletracker
								  craw.setType(((Long) obj1[4]).intValue());// emailQC
								  craw.setVerified(((Long) obj1[5]).intValue()); // mailshoot
								  craw.setSite_down(((Long) obj1[6]).intValue());// mailshooturlcount

								  craw.setCreated_on(obj1[7].toString());// date
								  craw.setNote1(obj1[8].toString());// clientname
								  craw.setNote2(obj1[9].toString());// clienttype

								in_side.add(craw);
								craw=null;
								obj1=null;
							}
							data.setInside(in_side);
							monthlyGraph.add(data);
							data=null;
							in_side=null;
							lst1=null;
						}
						data=null;
						obj=null;
					}
					lst = null;
					pue_Dao = null;
				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
				finally{
					data=null;
					in_side=null;
					lst1=null;
					lst = null;
					pue_Dao = null;
					factory= null;
					url_qc_dao= null;
					dao_qc_record= null;
					pue_Dao= null;
					craw=null;
					obj1=null;
				}
				System.gc();
				return "allEmpReportEnfo";
			} else if (colorRadio.equalsIgnoreCase("green")) {
				// user wise
				try {

					lst = pue_Dao.viewRecord("select sum(pe.opsQC)as opsqc, sum(pe.masterEmail_manual)as ME_manual, "
							+ " sum(pe.masterEmail_system)as ME_system , sum(pe.googleTracker)as GT, "
							+ " sum(pe.emailQC)as Eqc, sum(pe.mailShoot)as mailshoot, sum(pe.mailShootLink)as "
							+ " total_mailshoot, pe.created_on, pe.user_id from Productivity_enfo_user_wise pe "
							+ " where pe.user_id=" + usertype + " and pe.created_on between '" + date + "' and '"
							+ date1 + "'  group by " + " pe.created_on");

					monthlyGraph = new ArrayList<>();
					for (int i = 0; i < lst.size(); i++) {
						  obj = (Object[]) lst.get(i);
						  data = new DashBoardData();

						data.setSource(((Long) obj[0]).intValue()); // opsqc
						data.setInfringing(((Long) obj[1]).intValue()); // masteremail_manual
						data.setInvalid(((Long) obj[2]).intValue()); // masteremail_system
						data.setLink_count(((Long) obj[3]).intValue()); // googletracker
						data.setWhite_list(((Long) obj[4]).intValue()); // emailQC
						data.setGrey_list(((Long) obj[5]).intValue()); // mailsoot
						data.setDeleted(((Long) obj[6]).intValue()); // totalmailshootlink

						data.setDate(obj[7].toString());
						data.setUser_id((Integer) obj[8]);

						lst1 = pue_Dao.viewRecord(
								"select sum(productivi0_.opsQC) as col_0_0_, sum(productivi0_.masterEmail_manual)"
										+ " as col_1_0_, sum(productivi0_.masterEmail_system) as col_2_0_, "
										+ " sum(productivi0_.googleTracker) as col_3_0_, sum(productivi0_.emailQC) "
										+ " as col_4_0_, sum(productivi0_.mailShoot) as col_5_0_, "
										+ " sum(productivi0_.mailShootLink) as col_6_0_, productivi0_.created_on as "
										+ " col_7_0_, productivi0_.user_id as col_8_0_, productivi0_.client_id as col_9_0_ ,"
										+ " client_mas1_.client_name, markscan_p2_.name from Productivity_enfo_user_wise "
										+ " productivi0_, Client_master client_mas1_, Markscan_projecttype markscan_p2_ "
										+ " where client_mas1_.id=productivi0_.client_id and "
										+ " markscan_p2_.id=client_mas1_.project_type and productivi0_.created_on='"
										+ obj[7].toString() + "'" + " and productivi0_.user_id=" + (Integer) obj[8]
										+ " group by productivi0_.client_id ");
						in_side = new ArrayList<>();
						for (int i1 = 0; i1 < lst1.size(); i1++) {
							 obj1 = (Object[]) lst1.get(i1);
							  craw = new Crawle_url2();
							  craw.setIs_valid(((Long) obj1[0]).intValue());// opsqc
							  craw.setStatus(((Long) obj1[1]).intValue());// ME_manual
							  craw.setUser_id(((Long) obj1[2]).intValue());// ME_system
							  craw.setProject_id(((Long) obj1[3]).intValue());// googletracker
							  craw.setType(((Long) obj1[4]).intValue());// emailQC
							  craw.setVerified(((Long) obj1[5]).intValue()); // mailshoot
							  craw.setSite_down(((Long) obj1[6]).intValue());// mailshooturlcount

							  craw.setCreated_on(obj1[7].toString());// date
							  craw.setNote1(obj1[10].toString());// clientname
							  craw.setNote2(obj1[11].toString());// clienttype

							in_side.add(craw);
							craw=null;
							obj1=null;
						}
						data.setInside(in_side);
						monthlyGraph.add(data);
						in_side=null;
						data=null;
						lst1=null;
						obj=null;
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
				finally{
					
						data=null;
						in_side=null;
						lst1=null;
						lst = null;
						pue_Dao = null;
						factory= null;
						url_qc_dao= null;
						dao_qc_record= null;
						pue_Dao= null;
						craw=null;
						obj1=null;
				}
				System.gc();
				return "empReportEnfo";

			} else if (colorRadio.equalsIgnoreCase("blue")) {
				// for client wise

				try {

					lst = pue_Dao.viewRecord("select sum(pe.opsQC)as opsqc, sum(pe.masterEmail_manual)as ME_manual, "
							+ " sum(pe.masterEmail_system)as ME_system , sum(pe.googleTracker)as GT, "
							+ " sum(pe.emailQC)as Eqc, sum(pe.mailShoot)as mailshoot, sum(pe.mailShootLink)as "
							+ " total_mailshoot, pe.created_on from Productivity_enfo_user_wise pe where "
							+ " pe.created_on  between '" + date + "' and '" + date1 + "' and pe.client_id = "
							+ clientname + " " + " group by pe.created_on");

					monthlyGraph = new ArrayList<>();
					int mailqc = 0, mailshoot = 0;
					for (int i = 0; i < lst.size(); i++) {
						  obj = (Object[]) lst.get(i);
						  data = new DashBoardData();

						data.setSource(((Long) obj[0]).intValue()); // opsqc
						data.setInfringing(((Long) obj[1]).intValue()); // masteremail_manual
						data.setInvalid(((Long) obj[2]).intValue()); // masteremail_system
						data.setLink_count(((Long) obj[3]).intValue()); // googletracker
						data.setWhite_list(((Long) obj[4]).intValue()); // emailQC
						data.setGrey_list(((Long) obj[5]).intValue()); // mailsoot
						data.setDeleted(((Long) obj[6]).intValue()); // totalmailshootlink

						data.setDate(obj[7].toString());

						lst1 = pue_Dao
								.viewRecord("select sum(pe.opsQC)as opsqc, sum(pe.masterEmail_manual)as ME_manual, "
										+ " sum(pe.masterEmail_system)as ME_system , sum(pe.googleTracker)as GT, "
										+ " sum(pe.emailQC)as Eqc, sum(pe.mailShoot)as mailshoot, sum(pe.mailShootLink)as "
										+ " total_mailshoot, pe.created_on, mu.name  from "
										+ " Productivity_enfo_user_wise pe, Markscan_users mu where mu.id= pe.user_id "
										+ " and pe.created_on  = '" + obj[7].toString() + "' and pe.client_id = "
										+ clientname + " group by pe.created_on, " + " pe.user_id");

						in_side = new ArrayList<>();
						for (int i1 = 0; i1 < lst1.size(); i1++) {
							  obj1 = (Object[]) lst1.get(i1);
							  craw = new Crawle_url2();

							if (!obj1[8].toString().trim().equalsIgnoreCase("administrator")) {
								craw.setIs_valid(((Long) obj1[0]).intValue());// opsqc
								craw.setStatus(((Long) obj1[1]).intValue());// ME_manual
								craw.setUser_id(((Long) obj1[2]).intValue());// ME_system
								craw.setProject_id(((Long) obj1[3]).intValue());// googletracker
								craw.setType(((Long) obj1[4]).intValue());// emailQC
								craw.setVerified(((Long) obj1[5]).intValue()); // mailshoot
								craw.setSite_down(((Long) obj1[6]).intValue());// mailshooturlcount

								craw.setCreated_on(obj1[7].toString());// date
								craw.setWid(obj1[8].toString());// username
								// cu.setNote2(obj1[9].toString());// clienttype

								in_side.add(craw);
								
							}
							craw=null;
							obj1=null;
						}

						data.setInside(in_side);
						monthlyGraph.add(data);
						in_side=null;
						lst1=null;
						data=null;
						obj=null;
					}
					lst1 = null;
					lst = null;
					dao_qc_record = null;
					url_qc_dao = null;
				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
				finally{
					
					data=null;
					in_side=null;
					lst1=null;
					lst = null;
					pue_Dao = null;
					factory= null;
					url_qc_dao= null;
					dao_qc_record= null;
					pue_Dao= null;
					craw=null;
					obj1=null;
			}
				System.gc();
				return "clientReportEnfo";
			}
			
			
				
				data=null;
				in_side=null;
				lst1=null;
				lst = null;
				pue_Dao = null;
				factory= null;
				url_qc_dao= null;
				dao_qc_record= null;
				pue_Dao= null;
				craw=null;
				obj1=null;
				System.gc();
			return SUCCESS;
		}
	}

	public String dayDate() {
		try {
			Calendar cal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			today = dateFormat.format(cal.getTime());
			// cal.add(Calendar.DATE, -1);
			// yesterday = dateFormat.format(cal.getTime());
			cal=null;
			dateFormat=null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("yesterday date error....", e);
		}
		
		return today;
	}

	/*
	 * input from dashboard page
	 */
	String colorRadio = null;
	int usertype, projecttype, clientname;
	String date = null;
	String date1 = null;
	/*
	 * input from dashboard page end
	 */

	String today = null;
	String yesterday = null;

	Productivity_client_wiseDao pcw = null;
	// List<DashBoardData> userGraph = null;
	List<DashBoardData> monthlyGraph = null;
	List<Qc_record> monthlyGraphEnfo = null;
	// List<DashBoardData> monthlySource__c = null;
	// List<DashBoardData> monthlyInf__c = null;
	// List<DashBoardData> clientWaie__c = null;
	private List<Markscan_projecttype> listData = null;
	private List<Markscan_users> usrData = null;
	private List<Qc_record> pmqcPending = null;
	private List<Qc_record> opsqcPending = null;
	private List<Qc_record> masterEmailPending = null;
	private List<Qc_record> googleTrackerPending = null;
	private int mailQCPending;
	private List<Url_email_qc> mailShootPending = null;
	Markscan_usersDao dao_u = null;
	Markscan_projecttypeDao dao_p = null;
	String client_name = null;
	String client_type = null;
	String user_name = null;

	public List<Qc_record> getMonthlyGraphEnfo() {
		return monthlyGraphEnfo;
	}

	public void setMonthlyGraphEnfo(List<Qc_record> monthlyGraphEnfo) {
		this.monthlyGraphEnfo = monthlyGraphEnfo;
	}

	public List<Qc_record> getMasterEmailPending() {
		return masterEmailPending;
	}

	public void setMasterEmailPending(List<Qc_record> masterEmailPending) {
		this.masterEmailPending = masterEmailPending;
	}

	public List<Qc_record> getGoogleTrackerPending() {
		return googleTrackerPending;
	}

	public void setGoogleTrackerPending(List<Qc_record> googleTrackerPending) {
		this.googleTrackerPending = googleTrackerPending;
	}

	public int getMailQCPending() {
		return mailQCPending;
	}

	public void setMailQCPending(int mailQCPending) {
		this.mailQCPending = mailQCPending;
	}

	public List<Url_email_qc> getMailShootPending() {
		return mailShootPending;
	}

	public void setMailShootPending(List<Url_email_qc> mailShootPending) {
		this.mailShootPending = mailShootPending;
	}

	public List<Qc_record> getPmqcPending() {
		return pmqcPending;
	}

	public void setPmqcPending(List<Qc_record> pmqcPending) {
		this.pmqcPending = pmqcPending;
	}

	public List<Qc_record> getOpsqcPending() {
		return opsqcPending;
	}

	public void setOpsqcPending(List<Qc_record> opsqcPending) {
		this.opsqcPending = opsqcPending;
	}

	// public List<DashBoardData> getUserGraph() {
	// return userGraph;
	// }
	//
	// public void setUserGraph(List<DashBoardData> userGraph) {
	// this.userGraph = userGraph;
	// }

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getColorRadio() {
		return colorRadio;
	}

	public void setColorRadio(String colorRadio) {
		this.colorRadio = colorRadio;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public int getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public int getClientname() {
		return clientname;
	}

	public void setClientname(int clientname) {
		this.clientname = clientname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public List<Markscan_projecttype> getListData() {
		return listData;
	}

	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

	public List<Markscan_users> getUsrData() {
		return usrData;
	}

	public void setUsrData(List<Markscan_users> usrData) {
		this.usrData = usrData;
	}

	public List<DashBoardData> getMonthlyGraph() {
		return monthlyGraph;
	}

	public void setMonthlyGraph(List<DashBoardData> monthlyGraph) {
		this.monthlyGraph = monthlyGraph;
	}

}
