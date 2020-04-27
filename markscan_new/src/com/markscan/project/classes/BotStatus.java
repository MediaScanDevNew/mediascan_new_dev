/**
 * 
 */
package com.markscan.project.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.BotQueryStatus;
import com.markscan.project.beans.Markscan_machine;
import com.markscan.project.beans.Stored_project_setup;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Markscan_machineDao;
import com.markscan.project.dao.Stored_project_setupDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author pradeep
 *
 */
public class BotStatus extends ActionSupport {
	private static final Logger logger = Logger.getLogger(BotStatus.class);

	/**
	 * 
	 */
	public BotStatus() {
		// TODO Auto-generated constructor stub
	}

	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	List lst2 = null;
	List<BotQueryStatus> ipaddressList = null;
	Object[] obj2 = null;
	Object[] obj = null;

	public String execute() {

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			BotQueryStatus bst = null;
			factory = LoginAndSession.getFactory();
			Markscan_machineDao dao = null;
			Stored_project_setupDao dao2 = null;
			Markscan_machine url2 = null;
			try {
				dao = (Markscan_machineDao) factory.getBean("d6");
				dao2 = (Stored_project_setupDao) factory.getBean("d7");
				lst = dao.viewRecord("select  name, ip_address,status from Markscan_machine order by id asc");
				listData = new ArrayList<Markscan_machine>();
				ipaddressList = new ArrayList<BotQueryStatus>();
				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_machine();
					// Stored_project_setup sps = new Stored_project_setup();
					obj = (Object[]) lst.get(i);
					url2.setName(obj[0].toString());
					url2.setIp_address(obj[1].toString());
					url2.setStatus((Integer) obj[2]);
					// ipaddress.add(obj[1].toString());
					listData.add(url2);
					lst2 = dao.viewRecord("select sps.keyphrase,mu.name, sps.machine,sps.created_on, "
							+ " sps.google,sps.yahoo,sps.bing, sps.id from Stored_project_setup  sps, Markscan_users mu "
							+ " where mu.id=sps.userId and sps.completed!=1 and machine = '" + obj[1].toString()
							+ "' and sps.deleted=0 order by sps.id desc ");

					for (int ii = 0; ii < lst2.size(); ii++) {
						bst = new BotQueryStatus();
						obj2 = (Object[]) lst2.get(ii);

						bst.setKeyphrase(obj2[0].toString());
						bst.setUserName(obj2[1].toString());
						bst.setMachine(obj2[2].toString());
						bst.setCreated_on(obj2[3].toString());
						// if ((Integer) obj2[4] == 1) {
						// bst.setDeleted("Query deleted");
						// }
						// System.out.println("=== 11====="+obj2[0]);
						// System.out.println("=== 11====="+obj2[1]);
						// System.out.println("=== 12====="+obj2[2]);
						// System.out.println("=== 13====="+obj2[3]);
						// System.out.println("=== 11====="+obj2[4]);
						// System.out.println("=== 12====="+obj2[5]);
						// System.out.println("=== 13====="+obj2[6]);

						if ((Integer) obj2[4] == 1) {
							bst.setGoogle("Complete");
						} else if ((Integer) obj2[4] == 2) {
							bst.setGoogle("Running");
						} else if ((Integer) obj2[4] == 0) {
							bst.setGoogle("Pending");
						} else {
							bst.setGoogle("No Query");
						}

						if ((Integer) obj2[5] == 1) {
							bst.setYahoo("Complete");
						} else if ((Integer) obj2[5] == 2) {
							bst.setYahoo("Running");
						} else if ((Integer) obj2[5] == 0) {
							bst.setYahoo("Pending");
						} else {
							bst.setGoogle("No Query");
						}

						if ((Integer) obj2[6] == 1) {
							bst.setBing("Complete");
						} else if ((Integer) obj2[6] == 2) {
							bst.setBing("Running");
						} else if ((Integer) obj2[6] == 0) {
							bst.setBing("Pending");
						} else {
							bst.setGoogle("No Query");
						}
						bst.setId((Integer) obj2[7]);
						ipaddressList.add(bst);
						bst = null;
						obj2 = null;
					}
					obj = null;
					url2 = null;
					lst2 = null;
				}
			} catch (Exception e) {
				e.printStackTrace();

				return ERROR;
			} finally {
				bst = null;
				obj2 = null;

				obj = null;
				url2 = null;
				lst2 = null;
				dao2 = null;
				dao = null;

				factory = null;
				session2 = null;
			}
			System.gc();
			return SUCCESS;
		}

		
	}

	public String deletePendingBOTsetupQuery() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Stored_project_setupDao dao2=null;
			try {
				  dao2 = (Stored_project_setupDao) factory.getBean("d7");

				dao2.customUpdateProject(
						"update Stored_project_setup sps set sps.deleted=1  where sps.completed !=1 and sps.deleted=0");
				execute();
				
			} catch (Exception e) {
				// TODO: handle exception
				return ERROR;
			}finally{
				dao2=null;
				factory=null;
				session2=null;
			}
			return SUCCESS;
		}
	}

	private List<Markscan_machine> listData = null;

	public List<Markscan_machine> getListData() {
		return listData;
	}

	public void setListData(List<Markscan_machine> listData) {
		this.listData = listData;
	}

	public List<BotQueryStatus> getIpaddressList() {
		return ipaddressList;
	}

	public void setIpaddressList(List<BotQueryStatus> ipaddressList) {
		this.ipaddressList = ipaddressList;
	}

}
