/**
 * 
 */
package com.markscan.project.classes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Module_detail;
import com.markscan.project.beans.User_module_previlege;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Markscan_machineDao;
import com.markscan.project.dao.Markscan_usersDao;
import com.markscan.project.dao.Module_detailDao;
import com.markscan.project.dao.Stored_project_setupDao;
import com.markscan.project.dao.User_module_previlegeDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author pradeep 22 09 2016
 *
 */
public class UserAndPrivilege extends ActionSupport {
	private static final Logger logger = Logger.getLogger(UserAndPrivilege.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;

	/**
	 * 
	 */
	public UserAndPrivilege() {
		// TODO Auto-generated constructor stub
	}

	public String addUser() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println(userid);
			if ((Integer) session2.getAttribute("uid") != userid) {
				return "perror";
			} else {
				return SUCCESS;
			}
		}
	}

	public String addUserPost() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			Markscan_usersDao dao = null;
			Markscan_users mu = null;
			factory = LoginAndSession.getFactory();
			try {
				if (usrid.length() < 1) {
					return ERROR;
				} else {
					// if (userid == 0) {
					// } else {

					dao = (Markscan_usersDao) factory.getBean("d9");
					mu = new Markscan_users();
					mu.setName(usrid.trim());
					mu.setPassword(passid.trim());
					mu.setIsSuperUser(0);
					mu.setUser_name(username.trim());
					mu.setEmail(email.trim());
					mu.setStatus(status);
//					mu.setUpdated_on("0000-00-00 00:00:00");
					mu.setTeam(gender);
					dao.saveData(mu);
					// }

				}
			} catch (Exception e) {
				return "error";
			} finally {
				dao = null;
				mu = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	public String edituser() {
		// System.out.println("markscan .. privilege");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			// System.out.println(userid);
			if ((Integer) session2.getAttribute("uid") != userid) {
				return "perror";
			} else {
				users();
				if (usrData.size() > 0) {
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		}
	}

	Object[] obj = null;

	public void users() {
		Markscan_usersDao dao = (Markscan_usersDao) factory.getBean("d9");
		Markscan_users url2 = null;
		try {
			lst = dao.viewRecord("select id, name from Markscan_users");
			usrData = new ArrayList<>();
			for (int i = 0; i < lst.size(); i++) {
				url2 = new Markscan_users();
				obj = (Object[]) lst.get(i);
				url2.setId((Integer) obj[0]);
				url2.setName((String) obj[1]);
				usrData.add(url2);
				obj = null;
				url2 = null;
			}
			lst = null;
		} catch (Exception e) {
		} finally {
			obj = null;
			url2 = null;
			dao = null;
			factory = null;
			session2 = null;
		}
	}

	public String updatePasswd() {
		int pj;
		// System.out.println("...status_one..." + invlink);
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			String ppj[] = invlink.trim().split(",");
			factory = LoginAndSession.getFactory();
			Markscan_usersDao dao5 = (Markscan_usersDao) factory.getBean("d9");
			try {
				pj = dao5.customUpdateProject("update Markscan_users mu set mu.password='" + ppj[1].trim() + "'  where mu.id="
						+ (int) session2.getAttribute("uid") + " and mu.password = '" + ppj[0].trim() + "'");
				// email use as old password
				// System.out.println("pj===" + pj);
				if (pj == 0) {
					usrid = "Password not updated"; // usrid use for sending
													// status of
					// query
				} else if (pj == 1) {
					usrid = "Password updated";
				}
			} catch (Exception e) {
				usrid = "error on update";
				e.printStackTrace();
			} finally {
				ppj = null;
				session2 = null;
				factory = null;
				dao5 = null;
			}
		}
		return null;
	}

	public String editUserDone() {
		String qry = null;
		// System.out.println("...status_one..." + status);
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Markscan_usersDao dao5 = (Markscan_usersDao) factory.getBean("d9");
			try {
				qry = "update Markscan_users mu set  ";
				// System.out.println("..
				// passid------------------"+passid.length());
				if (passid.length() > 0) {
					qry = qry + " mu.password='" + passid.trim() + "' , ";
				}
				if (email.length() > 0) {
					qry = qry + "  mu.email='" + email.trim() + "' , ";
				}
				if (gender != 0) {
					qry = qry + " mu.team=" + gender + " , ";
				}

				// System.out.println(".. passid------------------"+gender);
				dao5.customUpdateProject(qry + " mu.status=" + status + " where mu.id=" + userid + "");
				users();

			} catch (Exception e) {
				return ERROR;
			} finally {
				session2 = null;
				factory = null;
				dao5 = null;
				qry = null;
			}
			return SUCCESS;
		}
	}

	public String userPrivilage() {
		// System.out.println("markscan .. privilege");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println(userid);
			if ((Integer) session2.getAttribute("uid") != userid) {
				return "perror";
			} else {
				factory = LoginAndSession.getFactory();
				Markscan_usersDao dao = (Markscan_usersDao) factory.getBean("d9");
				Module_detailDao dao1 = (Module_detailDao) factory.getBean("d17");
				Markscan_users url2 = null;
				Module_detail url3 = null;
				try {
					lst = dao.viewRecord("select id, name from Markscan_users where status= 1");
					usrData = new ArrayList<>();
					for (int i = 0; i < lst.size(); i++) {
						url2 = new Markscan_users();
						obj = (Object[]) lst.get(i);
						url2.setId((Integer) obj[0]);
						url2.setName((String) obj[1]);
						usrData.add(url2);
						obj = null;
						url2 = null;
					}
					lst = null;

					lst = dao1.viewRecord("select id, module_name from Module_detail");
					usrModule = new ArrayList<>();
					for (int i = 0; i < lst.size(); i++) {
						url3 = new Module_detail();
						obj = (Object[]) lst.get(i);
						url3.setId((Integer) obj[0]);
						url3.setModule_name((String) obj[1]);
						usrModule.add(url3);
						obj = null;
						url3 = null;
					}

				} catch (Exception e) {
					return ERROR;
				} finally {
					obj = null;
					url3 = null;
					url2 = null;
					factory = null;
					session2 = null;
					lst = null;
					dao = null;
					dao1 = null;
				}
				return SUCCESS;
			}
		}
	}

	public String userPrivilage1() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {

			factory = LoginAndSession.getFactory();
			Markscan_usersDao dao = (Markscan_usersDao) factory.getBean("d9");
			Module_detailDao dao1 = (Module_detailDao) factory.getBean("d17");
			Markscan_users url2 = null;
			Module_detail url3 = null;
			try {

				lst = dao.viewRecord("select id, name from Markscan_users where status= 1");
				usrData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_users();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					usrData.add(url2);
					obj = null;
					url2 = null;
				}
				lst = null;

				lst = dao1.viewRecord("select id, module_name from Module_detail");
				usrModule = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					url3 = new Module_detail();
					obj = (Object[]) lst.get(i);
					url3.setId((Integer) obj[0]);
					url3.setModule_name((String) obj[1]);
					usrModule.add(url3);
					obj = null;
					url3 = null;
				}

			} catch (Exception e) {
				return ERROR;
			} finally {
				obj = null;
				url3 = null;
				obj = null;
				url2 = null;
				lst = null;
				factory = null;
				session2 = null;
				dao = null;
				dao1 = null;
			}
			return SUCCESS;
		}

	}

	public String userPrivilageGrant() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			User_module_previlegeDao dao = (User_module_previlegeDao) factory.getBean("d16");
			User_module_previlege ump = new User_module_previlege();
			try {

				privilege = privilege.trim();
				privilege = privilege.replace(",", "");
				String pj[] = privilege.trim().split(" ");
				for (String ppj : pj) {
					ump.setCreated_by((int) session2.getAttribute("uid"));
					ump.setUser_id(userid);
					ump.setModule_id(Integer.parseInt(ppj.trim()));
					dao.saveData(ump);

				}
				userPrivilage1();

			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				ump = null;
				dao = null;
				factory = null;
				session2 = null;
				privilege = null;
			}
			return SUCCESS;
		}
	}

	public String revokePrivilage() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			usrModule = new ArrayList<>();
			factory = LoginAndSession.getFactory();
			Markscan_usersDao dao = (Markscan_usersDao) factory.getBean("d9");
			Markscan_users url = null;
			Module_detail url2 = null;
			try {

				lst = dao.viewRecord("select id, name from Markscan_users where status= 1");
				usrData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					url = new Markscan_users();
					obj = (Object[]) lst.get(i);
					url.setId((Integer) obj[0]);
					url.setName((String) obj[1]);
					usrData.add(url);
					obj = null;
					url = null;
				}

				url2 = new Module_detail();
				url2.setId(0);
				url2.setModule_name("Module");
				usrModule.add(url2);
				userid = 0;
				usrid = "";
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				lst = null;
				url2 = null;
				url = null;
				factory = null;
				session2 = null;
				dao = null;
				obj = null;

			}

			return SUCCESS;
		}
		// }
	}

	public String revokePMid() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Module_detailDao dao = (Module_detailDao) factory.getBean("d17");
			Markscan_usersDao dao1 = (Markscan_usersDao) factory.getBean("d9");
			Module_detail url = null;
			Markscan_users url2 = null;
			try {

				lst = dao.viewRecord("select md.id,md.module_name from User_module_previlege ump, Markscan_users mu, "
						+ " Module_detail md where mu.id=ump.user_id and md.id=ump.module_id and ump.user_id ="
						+ userid);
				usrModule = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					url = new Module_detail();
					obj = (Object[]) lst.get(i);
					url.setId((Integer) obj[0]);
					url.setModule_name((String) obj[1]);
					usrModule.add(url);
					obj = null;
					url = null;
				}
				lst = null;

				lst = dao1.viewRecord("select id, name from Markscan_users where status= 1");
				usrData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_users();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					usrData.add(url2);
					obj = null;
					url2 = null;
				}
				lst = null;

			} catch (Exception e) {
				return ERROR;
			} finally {
				lst = null;
				obj = null;
				url = null;
				url2 = null;
				dao = null;
				dao1 = null;
				factory = null;
				session2 = null;

			}
			return SUCCESS;
		}
	}

	public String revokePrivilageExecute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			User_module_previlegeDao dao = (User_module_previlegeDao) factory.getBean("d16");
			try {

				privilege = privilege.trim();
				privilege = privilege.replace(",", "");
				String pj[] = privilege.trim().split(" ");
				for (String ppj : pj) {
					dao.customUpdateProject("delete from User_module_previlege where user_id=" + userid
							+ " and module_id=" + Integer.parseInt(ppj.trim()));
				}
				revokePrivilage();
				
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}finally{
				privilege=null;
				dao=null;
				factory = null;
				session2 = null;
			}return SUCCESS;
		}
	}

	public HttpSession getSession2() {
		return session2;
	}

	public void setSession2(HttpSession session2) {
		this.session2 = session2;
	}

	int userid;
	int status_one;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	private List<Markscan_users> usrData = null;
	private List<Module_detail> usrModule = null;
	String usrid = null;
	String passid = null;
	String username = null;
	String email = null;
	String privilege = null;
	int status, gender;
	String invlink = null;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getInvlink() {
		return invlink;
	}

	public void setInvlink(String invlink) {
		this.invlink = invlink;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public List<Module_detail> getUsrModule() {
		return usrModule;
	}

	public void setUsrModule(List<Module_detail> usrModule) {
		this.usrModule = usrModule;
	}

	public List<Markscan_users> getUsrData() {
		return usrData;
	}

	public void setUsrData(List<Markscan_users> usrData) {
		this.usrData = usrData;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getPassid() {
		return passid;
	}

	public void setPassid(String passid) {
		this.passid = passid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
