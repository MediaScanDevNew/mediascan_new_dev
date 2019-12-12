package com.markscan.project.classes;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.GreylistDao;
import com.opensymphony.xwork2.ActionSupport;

public class EditGreyList extends ActionSupport{
	private static final Logger logger = Logger.getLogger(EditGreyList.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	Object[] obj = null;
	GreylistDao dao = null;
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

	public String domain_name;

	public String editGreyList() {
		System.out.println("\n\n------------- inside editGreyList---------------------------");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			dao = (GreylistDao) factory.getBean("d19");
			System.out.println("---------> id :"+id);
			try {
				System.out.println("---------> domain :"+domain_name);
				if (domain_name.trim().length() < 1) {
					msg = "Greylist Field never be blank";
				} else {
					
						String query="update Greylist set domain='"+domain_name+"' where id="+id;;
						System.out.println("============== query=================="+query);
						dao.customUpdateProject(query);
						System.out.println("============== Update Done==================");
						msg = "Greylist [[  " + domain_name + "  ]] Updated";
				}

			}catch (ConstraintViolationException e) {
				msg = "Greylist [[  " + domain_name + "  ]] already Added";
			} 
			catch (Exception e) {
				msg= "ERROR Greylist not added";
				return ERROR;
			} finally {
				dao = null;
				factory = null;
				session2 = null;
			}

			System.out.println("return editGreyList----------------- ");
			return SUCCESS;
		}
	
	}
}
