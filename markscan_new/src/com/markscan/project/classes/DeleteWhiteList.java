package com.markscan.project.classes;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.WhitelistDao;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteWhiteList extends ActionSupport{
	private static final Logger logger = Logger.getLogger(EditWhiteList.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	Object[] obj = null;
	WhitelistDao dao = null;
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlatformType() {
		return platformType;
	}
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public String msg = null,platformType;
	
	public String deleteInternetWhiteList() {
		System.out.println("\n\n------------- inside deleteInternetWhiteList---------------------------");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			dao = (WhitelistDao) factory.getBean("d20");
//			System.out.println("---------> id :"+id);
			try {
//				System.out.println("---------> domain :"+domain_name);	
				String query=null;
				if(platformType.equalsIgnoreCase("in")){
							query="DELETE FROM Whitelist where id="+id;
				}else if(platformType.equalsIgnoreCase("fb") || platformType.equalsIgnoreCase("tw")){
							query="DELETE FROM Whitelist_two  where id="+id;
				}else if(platformType.equalsIgnoreCase("insta")){
							query="DELETE FROM Whitelist_instagram where id="+id;
				}else if(platformType.equalsIgnoreCase("yt")){
							query="DELETE FROM Whitelist_yt DELETE FROM where id="+id;
				}
				System.out.println("============== query=================="+query);
				dao.customUpdateProject(query);
				System.out.println("============== Delete Done==================");
				msg = "WhiteList  Deleted";

			} 
			catch (Exception e) {
				msg= "ERROR whitelist not Deleted";
				return ERROR;
			} finally {
				dao = null;
				factory = null;
				session2 = null;
			}

			System.out.println("return deleteInternetWhiteList----------------- ");
			return SUCCESS;
		}
	
	}

}
