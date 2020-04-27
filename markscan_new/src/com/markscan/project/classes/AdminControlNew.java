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

public class AdminControlNew extends ActionSupport {
	
	private static final Logger logger = Logger.getLogger(AdminControlNew.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	Object[] obj = null;
	WhitelistDao dao = null;
	Whitelist_instagramDao dao1 =null;
	int id;
	private List<CommanReporting> showData = null;

	public List<CommanReporting> getShowData() {
		return showData;
	}

	public void setShowData(List<CommanReporting> showData) {
		this.showData = showData;
	}
	public AdminControlNew() {
		// TODO Auto-generated constructor stub
	}
	public String clientId,domain_name;
	public String getClientId() {
		return clientId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
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
	
	public String getWhiteListClientWise() {

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
				lst = dao.viewRecord("select wl.domain_name, mu.name, wl.created_on,cl.client_name,wl.id from Whitelist wl, "
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
					bean.setId((int) obj[4]);
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
	

	
	

}
