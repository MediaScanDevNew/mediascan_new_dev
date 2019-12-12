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

public class AdminControlInstagramList extends ActionSupport {
	
	private static final Logger logger = Logger.getLogger(AdminControlInstagramList.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;

	public AdminControlInstagramList() {
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
	
	public String getInstaClientWise() {
		System.out.println("\n\n------------- inside getInstaClientWise---------------------------");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			dao1 = (Whitelist_instagramDao) factory.getBean("d34");
			Whitelist_instagram wd = new Whitelist_instagram();
			try {
				wd.setClientId(clientId);
				lst = dao1.viewRecord(
						"select wl.insta_link, mu.name, wl.created_on,cl.client_name from Whitelist_instagram wl, Markscan_users mu ,Client_master cl where wl.created_by = mu.id and cl.id=wl.clientId and wl.clientId="+clientId);
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
					bean = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				obj = null;
				lst = null;
				factory = null;
				dao1 = null;
				
			}
			System.out.println("return getInstaClientWise ");
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
