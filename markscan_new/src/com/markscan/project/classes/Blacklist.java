package com.markscan.project.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Blacklist_sites;
import com.markscan.project.beans.Keyword_filter_extension_master;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Blacklist_sitesDao;
import com.markscan.project.dao.Keyword_filter_extension_masterDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.opensymphony.xwork2.ActionSupport;

public class Blacklist extends ActionSupport{
	private List<Blacklist_sitesDao> keywordlist ;
    private String domainname;
    private int id;
    private int aivalue;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAivalue() {
		return aivalue;
	}

	public void setAivalue(int aivalue) {
		this.aivalue = aivalue;
	}

	public String getDomainname() {
		return domainname;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}


	private int projecttype;
	
	
	


	private static final long serialVersionUID = 1L;
	HttpSession session2 = null;
	Object[] obj = null;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger logger = Logger.getLogger(Blacklist.class);
	BeanFactory factory = null;
	List lst = null;
	private List<Markscan_projecttype> listData ; 

	
	public  String start(){
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			return LOGIN;
		}else{
			factory = LoginAndSession.getFactory();
			logger.info("---- factory---" + factory);
			try {
				Markscan_projecttype url2 = null;
				Markscan_projecttypeDao dao = (Markscan_projecttypeDao) factory.getBean("d8");	
				logger.info(".......LAUNCHhhhhhhhhhhhhhhhhhhhh........");
				lst = dao.viewRecord("select id, name from Markscan_projecttype order by name");	
				logger.info(".......REPORT SIZE........" + lst.size());
				listData = new ArrayList<Markscan_projecttype>();

				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_projecttype();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					listData.add(url2);
					obj = null;
					url2 = null;
				}
				dao = null;
				url2 = null;
				lst = null;
			} catch (Exception e) {
				logger.error("project type data get ", e);
			}	
	
			addActionMessage("Welcome to Blacklist!");
			return SUCCESS;
		}		
		
	}
	
	public String getdomains() throws Exception {


		try {
			factory = LoginAndSession.getFactory();
			logger.info("---- factory---" + factory);
			Blacklist_sitesDao url3 = null;
			Blacklist_sitesDao dao1 = (Blacklist_sitesDao) factory.getBean("d32");	
			logger.info(".......LAUNCHhhhhhhhhhhhhhhhhhhhh.  KEYWORD......."+getProjecttype());
			lst = dao1.viewRecord("SELECT id,domain,is_active FROM Blacklist_sites WHERE project_type="+getProjecttype()+" ORDER BY domain ASC");	
			logger.info(".......KEYWORD SIZE........" + lst.size());
			keywordlist = new ArrayList<Blacklist_sitesDao>();

			for (int i = 0; i < lst.size(); i++) {
				url3 = new Blacklist_sitesDao();
				obj = (Object[]) lst.get(i);
				url3.setId((Integer) obj[0]);
				logger.info("-----------DATATATATATAT::: "+(String) obj[1]);
				url3.setDomain((String) obj[1]);
				url3.setIs_active((Integer) obj[2]);
				getKeywordlist().add(url3);
				obj = null;
				url3 = null;
			}
			logger.info("-----------keywordlist----------:: "+keywordlist);
			dao1 = null;
			url3 = null;
			lst = null;
		} catch (Exception e) {
			logger.error("project type data get ", e);
		}	

		return SUCCESS;
	
}
	
	
	public String addblacklist() throws Exception {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			return LOGIN;
		}else{
			try {
				factory = LoginAndSession.getFactory();
				Markscan_projecttype url2 = null;
				Markscan_projecttypeDao dao = (Markscan_projecttypeDao) factory.getBean("d8");	
				logger.info(".......LAUNCHhhhhhhhhhhhhhhhhhhhh........");
				lst = dao.viewRecord("select id, name from Markscan_projecttype order by name");	
				logger.info(".......REPORT SIZE........" + lst.size());
				listData = new ArrayList<Markscan_projecttype>();

				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_projecttype();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					listData.add(url2);
					obj = null;
					url2 = null;
				}
				dao = null;
				url2 = null;
				lst = null;
			} catch (Exception e) {
				logger.error("project type data get ", e);
			}	
	
						
			
			try {	
				String pj;
				Blacklist_sites url4 = null;
				url4 = new Blacklist_sites();
				factory = LoginAndSession.getFactory();
				Blacklist_sitesDao dao2 = (Blacklist_sitesDao) factory.getBean("d32");
				url4.setProject_type((Integer) getProjecttype());
				url4.setIs_active(1);
				url4.setDomain(getDomainname());
				pj = dao2.saveData(url4);
				logger.info(".......Done.PPPJJJ......."+pj);

				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			}	
			addActionMessage("Data added successfully");
			return SUCCESS;
		}	
		
	}	
	
	
	public String updateactiveblacklist() throws Exception {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			return LOGIN;
		}else{
			try {	
				int pj;
				int active ;
				if(getAivalue()==0){
					active = 1;
				}else{
					active = 0;
				}
				factory = LoginAndSession.getFactory();
				Blacklist_sitesDao dao2 = (Blacklist_sitesDao) factory.getBean("d32");

				pj = dao2.customUpdateProject("UPDATE Blacklist_sites SET is_active="+active+" WHERE id= "+getId());
				logger.info(".......Done........");

				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			}			
			return SUCCESS;
		}
	} 
	
	
	public String updateblacklist() throws Exception {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			return LOGIN;
		}else{
			factory = LoginAndSession.getFactory();
			logger.info("---- factory---" + factory);
			try {
				Markscan_projecttype url2 = null;
				Markscan_projecttypeDao dao = (Markscan_projecttypeDao) factory.getBean("d8");	
				logger.info(".......LAUNCHhhhhhhhhhhhhhhhhhhhh........");
				lst = dao.viewRecord("select id, name from Markscan_projecttype order by name");	
				logger.info(".......REPORT SIZE........" + lst.size());
				listData = new ArrayList<Markscan_projecttype>();

				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_projecttype();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					listData.add(url2);
					obj = null;
					url2 = null;
				}
				dao = null;
				url2 = null;
				lst = null;
			} catch (Exception e) {
				logger.error("project type data get ", e);
			}				
			
			try {	
				int pj;
				int active ;
				if(getAivalue()==0){
					active = 1;
				}else{
					active = 0;
				}
				factory = LoginAndSession.getFactory();
				Blacklist_sitesDao dao2 = (Blacklist_sitesDao) factory.getBean("d32");
				logger.info(".......getDomainname........:: "+ getDomainname());
				pj = dao2.customUpdateProject("UPDATE Blacklist_sites SET domain ='"+getDomainname()+"' WHERE id= "+getId());
				logger.info(".......Done........");

				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			}		
			addActionMessage("Keyphrase domain  successfully!");
			return SUCCESS;
		}
	} 	
	
	public List<Blacklist_sitesDao> getKeywordlist() {
		return keywordlist;
	}


	public void setKeywordlist(List<Blacklist_sitesDao> keywordlist) {
		this.keywordlist = keywordlist;
	}


	public List<Markscan_projecttype> getListData() {
		return listData;
	}


	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

	public int getProjecttype() {
		return projecttype;
	}


	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	
	
}
