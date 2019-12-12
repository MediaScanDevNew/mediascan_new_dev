/**
 * 
 */
package com.markscan.project.classes.session;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.markscan.project.beans.Markscan_users;
import com.markscan.project.classes.ActionPerform;
import com.markscan.project.dao.User_module_previlegeDao;
import com.markscan.project.dao.UsersDao;
import com.opensymphony.xwork2.ActionSupport;
//import com.pj.db.connection.One;
//import com.pj.db.query.DbQuery;

/**
 * @author pradeep
 *
 */
public class LoginAndSession extends ActionSupport implements SessionAware {

	private static final Logger logger = Logger.getLogger(LoginAndSession.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LoginAndSession() {
		// One one = new One();
		// con = one.getCon();
		// dbq = new DbQuery();
	}

	public String sessionOut() {
		return SUCCESS;
	}

	public String display() {
		return SUCCESS;
	}

	public String loginPage() {
		return SUCCESS;
	}

	public String maintenance() {
		return SUCCESS;
	}

	public String next() {
		session2 = ServletActionContext.getRequest().getSession(false);
		// System.out.println("=====" + session2);
		logger.info("session .." + session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session error next method .." + logger.getClass());
			return LOGIN;
		} else {
			// System.out.println("else========='");
			session2 = null;
			return SUCCESS;
		}
	}

	public String logout() {
		if (sessionMap != null) {
			sessionMap.invalidate();
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map map) {
		sessionMap = (SessionMap) map;
		sessionMap.put("login", "true");
	}

	String email = null;
	InputStream input = null;
	String configFile = null;
	String filePath = null;
	Properties prop = new Properties();
	int team = 0;
	List lst = null;

	public String execute() {

		/*
		 * HttpSession session =
		 * ServletActionContext.getRequest().getSession(false); if (session ==
		 * null) { // Not created yet. Now do so yourself. System.out.println(
		 * "Not created yet. Now do so yourself"); } else { // Already created.
		 * System.out.println("Already created"); }
		 */

		int f = 0;
		// System.out.println("hello");
		// System.out.println(checkLogin());
		int id = 0;
		String name = null;
		int isSuperusr = 0;

		try {
			configFile = "/details.properties";
			input = getClass().getResourceAsStream(configFile);
			prop.load(input);
			// db = prop.getProperty("database");
			filePath = prop.getProperty("toolStatus");
			if (pj != 26) {
				if (filePath.equalsIgnoreCase("2")) {
					return "maintenance";
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("file read error .. ", e);
		} finally {
			configFile = null;
			input = null;
			prop = null;
		}

		if (checkLogin(user, passwd) == true) {
			for (Markscan_users musr : usersDetail) {
				id = musr.getId();
				name = musr.getName();
				email = musr.getEmail();
				isSuperusr = musr.getIsSuperUser();
				team = musr.getTeam();
			}
			// logger.info("f id====" + id);
			// logger.info("f name====" + name);
			// logger.info("f email====" + email);
			// logger.info("f isSuperusr====" + isSuperusr);
			// logger.info("f team====" + team);

			// System.out.println("f value====" + f); // it should be 0
			logger.info("f value====" + f);
			session2 = ServletActionContext.getRequest().getSession();
			// System.out.println(session2);
			logger.info("session .." + session2);
			if (session2 == null || session2.getAttribute("login") == null) {
				// System.out.println("if execute");
				logger.error("session error next method .." + logger.getClass());
				return LOGIN;
			} else {
				factory = ActionPerform.getFactory();
				User_module_previlegeDao dao = (User_module_previlegeDao) factory.getBean("d16");
				try {
					lst = dao.viewRecord("select module_id from User_module_previlege where user_id=" + id);
					// System.out.println(id + " .... lst-- size..." +
					// lst.size());
					moduleID = new HashSet<>();
					for (int i = 0; i < lst.size(); i++) {
						// Object[] obj = (Object[]) lst.get(i);
						moduleID.add((Integer) lst.get(i));
					}
					// System.out.println("---- >>>>>>>>----->>>>>" +
					// moduleID.size());
				} catch (Exception e) {
					logger.error(e);
					// e.printStackTrace();
				} finally {
					lst = null;
					session2 = null;
					dao = null;
				}

				// System.out.println("else execute login success");
				// System.out.println("-- one---" + id);
				// System.out.println("-- one---" + name);
				logger.info("else execute login success" + " userid===" + id + "   username====" + name);
				// sessionMap.put("login", "true");
				sessionMap.put("user", name);
				sessionMap.put("uid", id);
				sessionMap.put("mail", email.trim());
				sessionMap.put("module_id", moduleID);
				sessionMap.put("isSuperUser", isSuperusr);
				sessionMap.put("team", team);
				// com.markscan.project.classes.ActionPerform actionPerform =
				// new ActionPerform();
				return SUCCESS;
			}
		} else {
			// System.out.println("lst return");
			logger.error("session error ....");
			return LOGIN;
		}

	}

	Set<Integer> moduleID = null;

	static BeanFactory factory = null;
	HttpSession session2 = null;
	Object[] obj = null;

	public Boolean checkLogin(String uname, String passwrd) {
		boolean b = false;
		// Resource r = new ClassPathResource("applicationContext.xml");
		// factory = new XmlBeanFactory(r);
		factory = ActionPerform.getFactory();
		logger.info("== factory==one=" + factory);
		if (factory == null) {
			ActionPerform.start();
			factory = ActionPerform.getFactory();
		}
		String loginQuery = null;
		Markscan_users mu = null;
		UsersDao dao = (UsersDao) factory.getBean("login");
		try {

			// System.out.println(uname + "-uuuu--" + passwrd);
			// logger.info(uname + "-password--" + passwrd);
			loginQuery = "select id,user_name, email , isSuperUser ,team from Markscan_users where status=1 "
					+ "and name='" + uname + "' and password ='" + passwrd + "'";
			// System.out.println(loginQuery);
			lst = dao.viewRecord(loginQuery);
			if (lst.size() == 1) {
				usersDetail = new ArrayList<Markscan_users>();
				for (int i = 0; i < lst.size(); i++) {
					mu = new Markscan_users();
					obj = (Object[]) lst.get(i);
					mu.setId((Integer) obj[0]);
					mu.setName((String) obj[1]);
					mu.setEmail((String) obj[2]);
					mu.setIsSuperUser((Integer) obj[3]);
					mu.setTeam((Integer) obj[4]);
					// logger.info( "-team--" + (Integer) obj[4]);
					usersDetail.add(mu);
					obj = null;
					mu = null;
				}
				// System.out.println("-- user detail..." + usersDetail);
				logger.info("-- user detail..." + usersDetail);
				b = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("check login error .." + logger.getClass(), e);
		} finally {
			obj = null;
			mu = null;
			loginQuery = null;
			lst = null;
			dao = null;
		}
		return b;

	}

	/*
	 * public Set<Integer> getModuleID() { return moduleID; }
	 * 
	 * public void setModuleID(Set<Integer> moduleID) { this.moduleID =
	 * moduleID; }
	 */

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public static BeanFactory getFactory() {
		return factory;
	}

	public static void setFactory(BeanFactory factory) {
		LoginAndSession.factory = factory;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	private SessionMap<String, Object> sessionMap;
	private String user = null, passwd = null;
	// private Connection con = null;
	// private PreparedStatement ps = null;
	// private ResultSet rs = null;
	private int user_id;
	private List<Markscan_users> usersDetail = null;

	private int pj;

	public int getPj() {
		return pj;
	}

	public void setPj(int pj) {
		this.pj = pj;
	}

}
