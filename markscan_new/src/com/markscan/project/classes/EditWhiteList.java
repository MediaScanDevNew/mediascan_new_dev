package com.markscan.project.classes;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.WhitelistDao;
import com.opensymphony.xwork2.ActionSupport;

public class EditWhiteList extends ActionSupport {
	private static final Logger logger = Logger.getLogger(EditWhiteList.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	Object[] obj = null;
	WhitelistDao dao = null;
	int id;
	public String msg = null;
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

	public String domain_name,platformType;
	
	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public String editInternetWhiteList() {
		System.out.println("\n\n------------- inside editInternetWhiteList---------------------------");
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
				if (domain_name.trim().length() < 1) {
					msg = "WhiteList Field never be blank";
				} else {
					if(platformType.equalsIgnoreCase("insta") && !domain_name.trim().contains("www.instagram.com/p/")){
						msg = "put Only instagram Link";
					}else if(platformType.equalsIgnoreCase("insta") && !domain_name.contains("?taken-by=")){
						msg = "This is not a right instagram Link, taken by (uploader id) is missing";
					}else if(platformType.equalsIgnoreCase("yt") && (domain_name.contains("http") || domain_name.contains("www")
							|| domain_name.contains("youtube.com"))){
						msg = "This is not a right Format, only uploader id/ Channal id";
					}else{
						String query=null;
						if(platformType.equalsIgnoreCase("in")){
							query="update Whitelist set domain_name='"+domain_name+"' where id="+id;
						}else if(platformType.equalsIgnoreCase("fb") || platformType.equalsIgnoreCase("tw")){
							query="update Whitelist_two set link='"+domain_name+"' where id="+id;
						}else if(platformType.equalsIgnoreCase("insta")){
							query="update Whitelist_instagram set insta_link='"+domain_name+"' where id="+id;
						}else if(platformType.equalsIgnoreCase("yt")){
							query="update Whitelist_yt set ch_id='"+domain_name+"' where id="+id;
						}
						System.out.println("============== query=================="+query);
						dao.customUpdateProject(query);
						System.out.println("============== Update Done==================");
						msg = "WhiteList [[  " + domain_name + "  ]] Updated";
					}
				}

			}catch (ConstraintViolationException e) {
				msg = "WhiteList [[  " + domain_name + "  ]] already Added";
			} 
			catch (Exception e) {
				msg= "ERROR whitelist not added";
				return ERROR;
			} finally {
				dao = null;
				factory = null;
				session2 = null;
			}

			System.out.println("return editInternetWhiteList----------------- ");
			return SUCCESS;
		}
	
	}

}
