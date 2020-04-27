package com.markscan.project.classes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.util.JDBCExceptionReporter;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.CommanReporting;
import com.markscan.project.beans.Greylist;
import com.markscan.project.beans.ProjectDashboardBean;
import com.markscan.project.beans.Whitelist;
import com.markscan.project.beans.Whitelist_instagram;
import com.markscan.project.beans.Whitelist_two;
import com.markscan.project.beans.Whitelist_yt;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.GreylistDao;
import com.markscan.project.dao.WhitelistDao;
import com.markscan.project.dao.Whitelist_instagramDao;
import com.markscan.project.dao.Whitelist_twoDao;
import com.markscan.project.dao.Whitelist_ytDao;
import com.opensymphony.xwork2.ActionSupport;

public class AdminControlGreyList extends ActionSupport {
	
	private static final Logger logger = Logger.getLogger(AdminControlGreyList.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	public AdminControlGreyList() {
		// TODO Auto-generated constructor stub
	}
	public String clientId;
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	
	public String getPlatformTypeId() {
		return platformTypeId;
	}

	public void setPlatformTypeId(String platformTypeId) {
		this.platformTypeId = platformTypeId;
	}
	public String platformTypeId;
	
	public String execute() {

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
	
	/*public String getWhiteListClientWise() {

		System.out.println("\n\n----------11111111111111111--- inside getWhiteListClientWise---------------------------");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			dao = (WhitelistDao) factory.getBean("d20");
			Whitelist wd = new Whitelist();
			try {
				wd.setClientId(clientId);
				System.out.println("clientId:--------->"+clientId);
				lst = dao.viewRecord("select wl.domain_name, mu.name, wl.created_on,cl.client_name from Whitelist wl, "
						            + "Markscan_users mu ,Client_master cl where wl.created_by = mu.id and cl.id=wl.clientId "
						            + "and wl.clientId="+clientId);
				logger.info("................" + lst.size());
				System.out.println("lst.size():--------->"+lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					CommanReporting bean = new CommanReporting();
					obj = (Object[]) lst.get(i);
					System.out.println("first value : "+obj[0].toString());
					bean.setDomainName(obj[0].toString());
					bean.setChannel_name(obj[1].toString());
					bean.setDate__c(obj[2].toString());
					bean.setClientName(obj[3].toString());
					showData.add(bean);
					obj = null;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				obj = null;
				lst = null;
				factory = null;
				dao = null;
				
			}
			System.out.println("return getWhiteListClientWise ");
			return SUCCESS;
		}
	}
*/	
	
	public String getGreylistClientWise() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			GreylistDao dao = (GreylistDao) factory.getBean("d19");
			// Greylist wd = new Greylist();
			CommanReporting bean = null;
			try {
				String query="select gl.domain, mu.name, gl.created_on,gl.id from Greylist gl, Markscan_users mu where gl.created_by = mu.id and gl.clientId="+clientId;
				System.out.println(" Query :: ------------>"+query);
				lst = dao.viewRecord(query);
				logger.info("................" + lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
					obj = (Object[]) lst.get(i);
					bean.setDomainName(obj[0].toString());
					bean.setChannel_name(obj[1].toString());
					bean.setDate__c(obj[2].toString());
					bean.setId((int) obj[3]);
					showData.add(bean);
					obj = null;
					bean = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				obj = null;
				bean = null;
				lst = null;
				factory = null;
				dao = null;
				bean = null;
			}
			
			return SUCCESS;
		}

	}

	Object[] obj = null;
	WhitelistDao dao = null;
	Whitelist_instagramDao dao1 =null;
	private List<CommanReporting> showData = null;

	public List<CommanReporting> getShowData() {
		return showData;
	}

	public void setShowData(List<CommanReporting> showData) {
		this.showData = showData;
	}

}
