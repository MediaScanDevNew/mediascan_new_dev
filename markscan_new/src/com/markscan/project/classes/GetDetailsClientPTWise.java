package com.markscan.project.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.CommanReporting;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Client_masterDao;
import com.opensymphony.xwork2.ActionSupport;

public class GetDetailsClientPTWise extends ActionSupport{
	
	
	private static final long serialVersionUID = 1L;
	HttpSession session2 = null;
	private static final Logger logger = Logger.getLogger(GetDetailsClientPTWise.class);
	BeanFactory factory = null;
	Client_masterDao dao3 = null;
	private List<CommanReporting> client_master_data = null;
	List lst = null;
	Object[] obj = null;
	
	
	public String execute() {
		System.out.println("The list size is -----111111111111111111111111111111111------->");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		}else{
		   factory = LoginAndSession.getFactory();
		try {
			
			lst = null;
			dao3 = (Client_masterDao) factory.getBean("d10");
			lst = dao3.viewRecord("select cm.id, cm.client_name, cm.client_address, cm.client_email, "
					+ " mpt.name, mwet.module, cm.created_time  from Client_master cm, Markscan_projecttype mpt, "
					+ " Module_wise_email_template mwet where mpt.id="+ptype+" and mpt.id=cm.project_type and "
					+ " mwet.id=cm.email_module order by cm.id desc");
			client_master_data = new ArrayList<>();
			for (int i = 0; i < lst.size(); i++) {
				CommanReporting url2 = new CommanReporting();
				Object[] obj = (Object[]) lst.get(i);
				url2.setId((Integer) obj[0]);
				url2.setClientName((String) obj[1]); // client name
				url2.setDomainName((String) obj[2]); // client address
				url2.setNote1((String) obj[3]); // client email
				url2.setProjectName((String) obj[4]); // project type
				url2.setLnk_typ((String) obj[5]); // email module
				url2.setDate__c((String) obj[6]); // created date
				client_master_data.add(url2);
				//System.out.println("The list size is ------------>"+client_master_data.size());
			}
			lst = null;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		} finally {
			factory=null;
			session2=null;
			lst = null;
			dao3 = null;
		}
		  return SUCCESS;
		}	
	}
	
	
	public String testMethod(){
		System.out.println("The list size is -----2222222222222222222222222222222222222222------->");
		
		return "";
	}
	
	
	private String ptype = null;
	private Map<Integer, String> stateMap = null;
	
	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	
	public List<CommanReporting> getClient_master_data() {
		return client_master_data;
	}
	
	public Map<Integer, String> getStateMap() {
		return stateMap;
	}

	public void setStateMap1(Map<Integer, String> stateMap) {
		this.stateMap = stateMap;
	}

	public void setClient_master_data(List<CommanReporting> client_master_data) {
		this.client_master_data = client_master_data;
	}

}
