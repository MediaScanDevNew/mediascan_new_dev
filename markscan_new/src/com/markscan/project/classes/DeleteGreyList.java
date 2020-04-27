package com.markscan.project.classes;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.GreylistDao;
import com.markscan.project.dao.WhitelistDao;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteGreyList extends ActionSupport {
	private static final Logger logger = Logger.getLogger(DeleteGreyList.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	Object[] obj = null;
	GreylistDao dao = null;
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String msg = null;
	
	public String deleteGreyList() {
		System.out.println("\n\n------------- inside deleteGreyList---------------------------");
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
				System.out.println("---------> domain :");	
				String query="DELETE FROM Greylist where id="+id;
				System.out.println("============== query=================="+query);
				dao.customUpdateProject(query);
				System.out.println("============== Delete Done==================");
				msg = "Greylist  Deleted";

			} 
			catch (Exception e) {
				msg= "ERROR Greylist not Deleted";
				return ERROR;
			} finally {
				dao = null;
				factory = null;
				session2 = null;
			}

			System.out.println("return deleteGreyList----------------- ");
			return SUCCESS;
		}
	
	}
}
