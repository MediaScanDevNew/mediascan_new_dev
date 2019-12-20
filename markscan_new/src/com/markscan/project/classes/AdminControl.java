package com.markscan.project.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

public class AdminControl extends ActionSupport {
	private static final Logger logger = Logger.getLogger(AdminControl.class);
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;

	public AdminControl() {
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
	
	public String addWhiteList() {
		System.out.println("========222222222222====== inside addWhiteList========2222222222222==========");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			WhitelistDao dao = (WhitelistDao) factory.getBean("d20");
			Whitelist wd = new Whitelist();
			try {
				if (wlistData.trim().length() < 1) {
					msg = "WhiteList Field never be blank";
				} else {
					wd.setDomain_name(wlistData);
					wd.setCreated_by((int) session2.getAttribute("uid"));
					wd.setClientId(clientId);
					dao.saveData(wd);
					msg = "WhiteList [[  " + wlistData + "  ]] Added";
				}

			}catch (ConstraintViolationException e) {
				msg = "WhiteList [[  " + wlistData + "  ]] already Added";
			} 
			catch (Exception e) {
				msg= "ERROR whitelist not added";
				return ERROR;
			} finally {
				dao = null;
				wd = null;
				factory = null;
				session2 = null;
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("clientId", clientId);
			request.setAttribute("platformTypeId", platformTypeId);
			request.setAttribute("msg", msg);
			
			return SUCCESS;
		}
	}

	public String msg = null;

	public String addWhiteList_two() {

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			Whitelist_twoDao dao = (Whitelist_twoDao) factory.getBean("d18");
			Whitelist_two wd = new Whitelist_two();
			try {
				if (wlistData.trim().length() < 1) {
					msg = "WhiteList Two Field never be blank";
				} else {
					wd.setClientId(clientId);
					wd.setLink(wlistData);
					wd.setCreated_by((int) session2.getAttribute("uid"));
					dao.saveData(wd);
					msg = "WhiteList Two [[  " + wlistData + "  ]] Added";
				}

			}catch (ConstraintViolationException e) {
				msg = "WhiteList Two [[  " + wlistData + "  ]] already Added";
			} 
			catch (Exception e) {
				msg= "ERROR whitelist not added";
				return ERROR;
			} finally {
				dao = null;
				wd = null;
				factory = null;
				session2 = null;
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("clientId", clientId);
			request.setAttribute("platformTypeId", platformTypeId);
			request.setAttribute("msg", msg);
			return SUCCESS;
		}
	}

	public String addGrayList() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			GreylistDao dao = (GreylistDao) factory.getBean("d19");
			Greylist wd = new Greylist();
			try {
				if (wlistData.trim().length() < 1) {
					msg = "GreyList Field never be blank";
				} else {
					wd.setClientId(clientId);
					wd.setDomain(wlistData);
					wd.setCreated_by((int) session2.getAttribute("uid"));
					dao.saveData(wd);
					msg = "GreyList [[" + wlistData + "]] Added";
				}

			} catch (ConstraintViolationException e) {
				msg = "GreyList [[  " + wlistData + "  ]] already Added";
			}

			catch (Exception e) {
				msg= "ERROR whitelist not added";
				return ERROR;
			} finally {
				dao = null;
				wd = null;
				factory = null;
				session2 = null;
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("clientId", clientId);
			//request.setAttribute("platformTypeId", platformTypeId);
			request.setAttribute("msg", msg);

			return SUCCESS;
		}
	}

	String wlistData = null;

	public String addInstaList() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			Whitelist_instagramDao dao = (Whitelist_instagramDao) factory.getBean("d34");
			Whitelist_instagram wd = new Whitelist_instagram();
			try {
				if (wlistData.trim().length() < 1) {
					msg = "Instagram Field never be blank";
				} else {
					if (!wlistData.trim().contains("www.instagram.com/p/")) {
						msg = "put Only instagram Link";
					} else {
						if (!wlistData.contains("?taken-by=")) {
							msg = "This is not a right instagram Link, taken by (uploader id) is missing";
						} else {
							String pj[] = wlistData.trim().split("taken-by=");
							wd.setClientId(clientId);
							wd.setInsta_link(wlistData);
							wd.setTaken_by_id(pj[1].trim());
							wd.setCreated_by((int) session2.getAttribute("uid"));
							dao.saveData(wd);
							msg = "Instagram link [[  " + wlistData + "  ]] Added";
						}
					}

				}

			} catch (ConstraintViolationException e) {
				msg = "Instagram [[" + wlistData + "]] already Added";
			}

			catch (Exception e) {
				msg= "ERROR whitelist not added";
				return ERROR;
			} finally {
				dao = null;
				wd = null;
				factory = null;
				session2 = null;
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("clientId", clientId);
			request.setAttribute("platformTypeId", platformTypeId);
			request.setAttribute("msg", msg);
			return SUCCESS;
		}
	}

	public String addYTList() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			Whitelist_ytDao dao = (Whitelist_ytDao) factory.getBean("d36");
			Whitelist_yt wd = new Whitelist_yt();
			try {
				if (wlistData.trim().length() < 1) {
					msg = "WhiteList Field never be blank";
				} else {
					// if (!wlistData.trim().contains("www.instagram.com/p/")) {
					// msg = "put Only instagram Link";
					// } else {
					if (wlistData.contains("http") || wlistData.contains("www")
							|| wlistData.contains("youtube.com")) {
						msg = "This is not a right Format, only uploader id/ Channal id";
					} else {
						wd.setClientId(clientId);
						wd.setCh_id(wlistData);
						wd.setCreated_by((int) session2.getAttribute("uid"));
						dao.saveData(wd);
						msg = "YouTube link [[  " + wlistData + "  ]] Added";
					}
					// }

				}

			} catch (ConstraintViolationException e) {
				msg = "YouTube link [[" + wlistData + "]] already Added";
			}

			catch (Exception e) {
				msg= "ERROR whitelist not added";
				return ERROR;
			} finally {
				dao = null;
				wd = null;
				factory = null;
				session2 = null;
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("clientId", clientId);
			request.setAttribute("platformTypeId", platformTypeId);
			request.setAttribute("msg", msg);

			return SUCCESS;
		}
	}

	public String getWlistData() {
		return wlistData;
	}

	public void setWlistData(String wlistData) {
		this.wlistData = wlistData;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
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
				String query="select gl.domain, mu.name, gl.created_on from Greylist gl, Markscan_users mu where gl.created_by = mu.id and gl.clientId="+clientId;
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

	public String greyList() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			/*factory = ActionPerform.getFactory();
			GreylistDao dao = (GreylistDao) factory.getBean("d19");
			// Greylist wd = new Greylist();
			CommanReporting bean = null;
			try {
				lst = dao.viewRecord(
						"select gl.domain, mu.name, gl.created_on from Greylist gl, Markscan_users mu where gl.created_by = mu.id");
				logger.info("................" + lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
					obj = (Object[]) lst.get(i);
					bean.setDomainName(obj[0].toString());
					bean.setChannel_name(obj[1].toString());
					bean.setDate__c(obj[2].toString());
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
*/			return SUCCESS;
		}

	}
	
	public String getSocialMediaClientWise() {
		System.out.println("\n\n------------- inside getSocialMediaClientWise---------------------------");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			WhitelistDao dao = (WhitelistDao) factory.getBean("d20");
			// Greylist wd = new Greylist();
			Whitelist wd = new Whitelist();
			CommanReporting bean = null;
			try {
				wd.setClientId(clientId);
				String query="select wl.link, mu.name, wl.created_on,cl.client_name from Whitelist_two wl, Markscan_users mu ,Client_master cl where wl.created_by = mu.id and cl.id=wl.clientId and wl.clientId="+clientId;
				System.out.println("query:--------->"+query);
				lst = dao.viewRecord(query);
				logger.info("................" + lst.size());
				System.out.println("lst.size():--------->"+lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
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
				bean = null;
				lst = null;
				factory = null;
				dao = null;
				bean = null;
			}
			System.out.println("return getSocialMediaClientWise ");
			return SUCCESS;
		}
	}
	
	public String getYTClientWise() {
		System.out.println("\n\n------------- inside getYTClientWise---------------------------");
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
//			WhitelistDao dao = (WhitelistDao) factory.getBean("d20");
			Whitelist_ytDao dao = (Whitelist_ytDao) factory.getBean("d36");
			// Greylist wd = new Greylist();
			Whitelist_yt wd = new Whitelist_yt();
			CommanReporting bean = null;
			try {
				wd.setClientId(clientId);
				System.out.println("clientId:--------->"+clientId);
				lst = dao.viewRecord(
						"select wl.ch_id, mu.name, wl.created_on,cl.client_name from Whitelist_yt wl, Markscan_users mu ,Client_master cl where wl.created_by = mu.id and cl.id=wl.clientId and wl.clientId="+clientId);
				logger.info("................" + lst.size());
				System.out.println("lst.size():--------->"+lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
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
				bean = null;
				lst = null;
				factory = null;
				dao = null;
				bean = null;
			}
			System.out.println("return getYTClientWise ");
			return SUCCESS;
		}
	}
	
	public String whiteList() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			/*factory = ActionPerform.getFactory();
			WhitelistDao dao = (WhitelistDao) factory.getBean("d20");
			// Greylist wd = new Greylist();
			CommanReporting bean = null;
			try {
				lst = dao.viewRecord(
						"select wl.domain_name, mu.name, wl.created_on from Whitelist wl, Markscan_users mu where wl.created_by = mu.id");
				logger.info("................" + lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
					obj = (Object[]) lst.get(i);
					bean.setDomainName(obj[0].toString());
					bean.setChannel_name(obj[1].toString());
					bean.setDate__c(obj[2].toString());
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
			}*/
			return SUCCESS;
		}
	}

	public String whiteList2() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			WhitelistDao dao = (WhitelistDao) factory.getBean("d20");
			// Greylist wd = new Greylist();
			CommanReporting bean = null;
			try {
				lst = dao.viewRecord(
						"select wl.link, mu.name, wl.created_on from Whitelist_two wl, Markscan_users mu where "
								+ "wl.created_by = mu.id");
				logger.info("................" + lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
					obj = (Object[]) lst.get(i);
					bean.setDomainName(obj[0].toString());
					bean.setChannel_name(obj[1].toString());
					bean.setDate__c(obj[2].toString());
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

	public String whiteList_Instagram() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			Whitelist_instagramDao dao = (Whitelist_instagramDao) factory.getBean("d34");
			// Greylist wd = new Greylist();
			CommanReporting bean = null;
			try {
				lst = dao.viewRecord("select whitelist_0_.insta_link, whitelist_0_.taken_by_id  , markscan_u1_.name "
						+ "  , whitelist_0_.created_on  from Whitelist_instagram whitelist_0_, "
						+ " Markscan_users markscan_u1_ where markscan_u1_.id=whitelist_0_.created_by");
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
					obj = (Object[]) lst.get(i);
					bean.setDomainName(obj[0].toString()); // instagram link
					bean.setChannel_name(obj[1].toString());// taken_by_id
					bean.setUserName(obj[2].toString()); // user_name
					bean.setDate__c(obj[3].toString());// created_on
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

	public String whiteList_YT()
	{
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			Whitelist_ytDao dao = (Whitelist_ytDao) factory.getBean("d36");
			
			CommanReporting bean = null;
			try {
				lst = dao.viewRecord(
						"select wyt.ch_id, mu.name, wyt.created_on from  Whitelist_yt wyt , Markscan_users mu "
						+ " where mu.id = wyt.created_by");
				logger.info("................" + lst.size());
				showData = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					bean = new CommanReporting();
					obj = (Object[]) lst.get(i);
					bean.setDomainName(obj[0].toString());//ch_id
					bean.setChannel_name(obj[1].toString());//user_name
					bean.setDate__c(obj[2].toString());//created_date
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
