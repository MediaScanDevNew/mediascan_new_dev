package com.markscan.project.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.markscan.project.beans.DashBoardData;
import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Productivity_user_wise;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.Productivity_user_wiseDao;
import com.markscan.project.dao.UsersDao;
//import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.ActionSupport;

public class ActionPerform extends ActionSupport {
	private static final Logger logger = Logger.getLogger(ActionPerform.class);
	HttpSession session2 = null;

	public ActionPerform() {
		// session2 = ServletActionContext.getRequest().getSession(false);
	}

	static BeanFactory factory = null;

	public static String start() {
		// System.out.println("---- factory pradeep joshi " + factory);
		if (factory == null) {
			BasicConfigurator.configure();
			Resource r = new ClassPathResource("applicationContext.xml");
			factory = new XmlBeanFactory(r);
			logger.error("== factory=in if ==" + factory);
			UsersDao dao = (UsersDao) factory.getBean("login");
			return SUCCESS;
		} else {
			logger.error("== factory=in else ==" + factory);
			return SUCCESS;
		}
	}

	public String signin() {
		HttpServletRequest request = ServletActionContext.getRequest();

		session2 = request.getSession();
		logger.info("= dash board page..." + session2);
		logger.info("== uid==" + session2.getAttribute("uid"));
		// System.out.println("= dash board page..."+session2);
		// System.out.println("== uid==" + session2.getAttribute("uid"));

		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if======login fail==='");
			// System.out.println("if======login fail==='");
			return LOGIN;
		} else {
			logger.info("else======login success==='");
			// System.out.println("else======login success==='");
			u_id = (int) session2.getAttribute("uid");
			yesterdayDate();
			aa = new ArrayList<>();
			aa2 = new ArrayList<>();
			//aa = dashboard_new(yesterdayDate(), 0);
			aa = dashboard_new(yesterdayDate(), 0);
			aa2 = dashboard_new(yesterdayDate(), -6);
			aa3 = new ArrayList<>();
			aa3 = dashboard_wheel(" cu.created_on between '" + yesterday + "' and adddate('" + yesterday + "',1) ");
			aaThree = new ArrayList<>();
			aaThree = dashboard_wheel(" cu.created_on > adddate('" + yesterday + "',1) ");

			// dashboard();
			request= null;
			
			return SUCCESS;
		}
	}

	Productivity_user_wiseDao puwd = null;
	Object[] obj=null;
	DashBoardData data=null;
	public List<DashBoardData> dashboard_new(String date_cc, int day) { // develop
																		// in
																		// spring
																		// @
		// 6-9-16
		factory = LoginAndSession.getFactory();
		try {

			puwd = (Productivity_user_wiseDao) factory.getBean("d27");

			lst = puwd.viewRecord("select productivi0_.infringing_link , productivi0_.source_link ,"
					+ "	productivi0_.invalid_link , productivi0_.white_list , productivi0_.grey_list "
					+ ", productivi0_.deleted , productivi0_.curr_date , productivi0_.youtube, "
					+ "productivi0_.social_media, productivi0_.duplicate from  Productivity_user_wise productivi0_ "
					+ "where  productivi0_.curr_date > ADDDATE('" + date_cc + "'," + day + ") "
					+ "and productivi0_.user_id=" + u_id);
			
			templist = new ArrayList<>();
			for (int i = 0; i < lst.size(); i++) {
				  obj = (Object[]) lst.get(i);

				  data = new DashBoardData();
				data.setSource((Integer) obj[1]);
				data.setInfringing((Integer) obj[0]);
				data.setInvalid((Integer) obj[2]);
				data.setWhite_list((Integer) obj[3]);
				data.setGrey_list((Integer) obj[4]);
				data.setDeleted((Integer) obj[5]);
				data.setDate(obj[6].toString());
				data.setYoutube((Integer) obj[7]);
				data.setSocial_media((Integer) obj[8]);
				data.setDuplicate((Integer) obj[9]);
				templist.add(data);
data= null;
obj= null;
			}
			puwd = null;
			lst = null;
			data=null;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			obj= null;
//			factory=null;
			puwd = null;
			lst = null;
			data= null;
		}
		
		return templist;
	}

	Crawle_url2Dao dao = null;
	List<DashBoardData> templist = null;
	public List<DashBoardData> dashboard_wheel(String date_cc) {
		factory = LoginAndSession.getFactory();
		try {
			dao = (Crawle_url2Dao) factory.getBean("dash");
			lst = dao.viewRecord("SELECT cu.project_id, count( cu.project_id ) , "
					+ " pi.project_name FROM Crawle_url2 cu, Project_info pi where "
					+ " pi.id = cu.project_id and cu.user_id = " + u_id + " and  " + date_cc
					+ "   GROUP BY cu.project_id	");
			// System.out.println("------ pradeep.........." + lst.size());
			logger.info("------ pradeep.........." + lst.size());
			templist = new ArrayList<>();
			for (int i = 0; i < lst.size(); i++) {
				 obj = (Object[]) lst.get(i);
				  data = new DashBoardData();
				data.setProj_name(obj[2].toString());
				data.setLink_count((int) (long) obj[1]);
				templist.add(data);
data= null;
obj=null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			dao= null;
			data=null;
			lst= null;
//			factory=null;
		}
		return templist;
	}

	int countdel, countdelYes;

	public void dashboard() { // using old JDBC code for dash board....
		String query=null;
		String query1=null;
		String deletedQueryToday=null;
		String deletedQueryYesterday=null;
		try {

			  query = "select sum(crawle_url2.link_logger = 1) as source, sum(crawle_url2.link_logger = 0) as Infringing,"
					+ " sum(crawle_url2.is_valid=1) as invalid , sum(crawle_url2.w_list=1) as white_list ,"
					+ " sum(crawle_url2.send_crawl=1) as grey_list " + " from crawle_url2 where user_id = " + u_id
					+ " and created_on >'" + today + "' ";
			  query1 = "select sum(crawle_url2.link_logger = 1) as source, sum(crawle_url2.link_logger = 0) as Infringing,"
					+ " sum(crawle_url2.is_valid=1) as invalid ,sum(crawle_url2.w_list=1) as white_list ,"
					+ " sum(crawle_url2.send_crawl=1) as grey_list " + " from crawle_url2 where user_id = " + u_id
					+ " and created_on between '" + yesterday + "' and '" + today + "' ";
			  deletedQueryToday = "select sum(crawle_url2_del.verified) as del from crawle_url2_del where user_id ="
					+ u_id + "" + "  and created_on > '" + today + "'";
			  deletedQueryYesterday = "select sum(crawle_url2_del.verified) as del from crawle_url2_del where user_id ="
					+ u_id + "" + "  and created_on between '" + yesterday + "' and  '" + today + "'";
			logger.info("query is ==" + deletedQueryToday);
			// System.out.println(query);
			// aa = dao.viewData(url2, query);
			/**
			 * === temp jdbc code-----
			 */
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			// aa = new ArrayList<>();
			// aa1 = new ArrayList<>();

			try { // first prepared statement -1
				conn = DriverManager.getConnection("jdbc:mysql://172.168.1.2:3306/webinforcement_demo", "myuser",
						"pass@123");

				ps = (PreparedStatement) conn.prepareStatement(deletedQueryToday);
				System.out.println("~~~~~~~~~~~~~~" + ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					countdel = rs.getInt("del");
				}
				System.out.println(countdel);
				rs = null;
				ps = null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				rs = null;
				ps = null;
			}

			try { // first prepared statement -2

				ps = (PreparedStatement) conn.prepareStatement(deletedQueryYesterday);
				System.out.println("~~~~~~~~~~~~~~" + ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					countdelYes = rs.getInt("del");
				}
				System.out.println(countdel);
				rs = null;
				ps = null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				rs = null;
				ps = null;
			}

			/*
			 * try { // first PreparedStatement
			 * 
			 * ps = (PreparedStatement) conn.prepareStatement(query); //
			 * System.out.println("today .." + ps); rs = ps.executeQuery();
			 * while (rs.next()) { DashBoardData data = new DashBoardData();
			 * data.setSource(rs.getInt("source"));
			 * data.setInfringing(rs.getInt("infringing"));
			 * data.setInvalid(rs.getInt("invalid"));
			 * data.setWhite_list(rs.getInt("white_list"));
			 * data.setGrey_list(rs.getInt("grey_list"));
			 * data.setDeleted(countdel); // aa.add(data); } //
			 * System.out.println(aa.size()); rs = null; ps = null; } catch
			 * (Exception e) { // e.printStackTrace();
			 * logger.error("dash board exception .1...", e); rs = null; ps =
			 * null; }
			 */

			List<String> days = new ArrayList<>();
			/*
			 * try { // secound PreparedStatement ps = (PreparedStatement) conn.
			 * prepareStatement("SELECT distinct( DATE_FORMAT(created_on, '%Y-%m-%d')) "
			 * +
			 * "from crawle_url2 WHERE   created_on BETWEEN NOW() - INTERVAL 7 DAY AND NOW()"
			 * ); // System.out.println("7day .." + ps); rs = ps.executeQuery();
			 * while (rs.next()) { days.add(rs.getString(1)); } //
			 * System.out.println(days.size()); rs = null; ps = null; } catch
			 * (Exception e) { // e.printStackTrace();
			 * logger.error("dash board exception .2...", e); rs = null; ps =
			 * null; }
			 */

			/*
			 * try { // third PreparedStatement String str =
			 * "select sum(crawle_url2.link_logger = 1) as source, sum(crawle_url2.link_logger = 0)"
			 * +
			 * " as Infringing, sum(crawle_url2.is_valid=1) as invalid from crawle_url2 WHERE  user_id = ? "
			 * + "and created_on like ?"; // System.out.println("str...'" +
			 * str); aa2 = new ArrayList<>(); for (String pj : days) { ps =
			 * (PreparedStatement) conn.prepareStatement(str); ps.setInt(1,
			 * u_id); ps.setString(2, "%" + pj + "%"); //
			 * System.out.println(".. date wise date....." + ps); rs =
			 * ps.executeQuery(); while (rs.next()) { DashBoardData data1 = new
			 * DashBoardData(); data1.setDate(pj);
			 * data1.setSource(rs.getInt("source"));
			 * data1.setInfringing(rs.getInt("infringing"));
			 * data1.setInvalid(rs.getInt("invalid")); aa2.add(data1); } }
			 * System.out.println("-------------- aa2 size-------------" +
			 * aa2.size()); logger.info("== aa2.size()...." + aa2.size()); days
			 * = null; rs = null; ps = null; } catch (Exception e) { //
			 * e.printStackTrace(); logger.error("dash board exception .3...",
			 * e); rs = null; ps = null; days = null; }
			 */

			try { // 4th PreparedStatement
				days = new ArrayList<>();
				ps = (PreparedStatement) conn.prepareStatement(
						"select distinct(project_id) from crawle_url2 where user_id=? and created_on between '"
								+ yesterday + "' and '" + today + "'");
				ps.setInt(1, u_id);
				// ps.setString(2, "%" + yesterday + "%");
				// System.out.println(".. date wise project....." + ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					days.add(rs.getString(1));
				}

				// System.out.println("-------------- project size-------------"
				// + days.size());
				logger.info("== project -- size..." + days.size());
				System.out.println(">>>>>>>days>>>>>>>>>" + days);
				rs = null;
				ps = null;
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error("dash board exception .4...", e);
				rs = null;
				ps = null;
			}

			

			try { // 4.1th PreparedStatement
				days = new ArrayList<>();
				ps = (PreparedStatement) conn.prepareStatement(
						"select distinct(project_id) from crawle_url2 where user_id=? and created_on >'" + today + "'");
				ps.setInt(1, u_id);
				// ps.setString(2, "%" + yesterday + "%");
				System.out.println(".. date wise project....." + ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					days.add(rs.getString(1));
				}

				// System.out.println("-------------- project size-------------"
				// + days.size());
				logger.info("== project -- size..." + days.size());
				System.out.println(">>>>>>>days>>>>>>>>>" + days);
				rs = null;
				ps = null;
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error("dash board exception .4...", e);
				rs = null;
				ps = null;
			}

			
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("dash board exception .final...", e);
		}
		finally{
			
		}

	}

	public String yesterdayDate() {
		try {
			Calendar cal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			today = dateFormat.format(cal.getTime());
			cal.add(Calendar.DATE, -1);
			yesterday = dateFormat.format(cal.getTime());
			// System.out.println(yesterday);
			cal=null;
			dateFormat=null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("yesterday date error....", e);
		}
		return yesterday;
	}

	public String dataImport() {

		session2 = ServletActionContext.getRequest().getSession();
//		System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session error data import method.." + logger.getClass());
			session2=null;
			return LOGIN;
		} else {
			// System.out.println("else========='");
			// u_id = (int) session2.getAttribute("uid");
			session2=null;
			return SUCCESS;
		}
	}

	/**
	 * *** variable *********
	 */
	List lst = null;
	String user = null;
	String passwd = null;
	List<DashBoardData> aa = null;
	List<DashBoardData> aa1 = null;
	List<DashBoardData> aa2 = null;
	List<DashBoardData> aa3 = null;
	List<DashBoardData> aaThree = null;
	int u_id = 10;
	int uid;
	String today = null;
	String yesterday = null;
	String usrid = "";

	public List<DashBoardData> getAaThree() {
		return aaThree;
	}

	public void setAaThree(List<DashBoardData> aaThree) {
		this.aaThree = aaThree;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public List<DashBoardData> getAa() {
		return aa;
	}

	public void setAa(List<DashBoardData> aa) {
		this.aa = aa;
	}

	public List<DashBoardData> getAa1() {
		return aa1;
	}

	public void setAa1(List<DashBoardData> aa1) {
		this.aa1 = aa1;
	}

	public List<DashBoardData> getAa2() {
		return aa2;
	}

	public void setAa2(List<DashBoardData> aa2) {
		this.aa2 = aa2;
	}

	public List<DashBoardData> getAa3() {
		return aa3;
	}

	public void setAa3(List<DashBoardData> aa3) {
		this.aa3 = aa3;
	}

	public static BeanFactory getFactory() {
		return factory;
	}

	public static void setFactory(BeanFactory factory) {
		ActionPerform.factory = factory;
	}

}
