package com.markscan.project.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Keyword_filter_extension_master;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.classes.session.LoginAndSession;

import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Crawl_youtubeDao;
import com.markscan.project.dao.Keyword_filter_extension_masterDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.opensymphony.xwork2.ActionSupport;

public class KeyPhrase extends ActionSupport{
	private static final long serialVersionUID = 1L;
	HttpSession session2 = null;
	Object[] obj = null;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger logger = Logger.getLogger(KeyPhrase.class);
	BeanFactory factory = null;
	List lst = null;
	private List<Markscan_projecttype> listData ; 
	private List<Keyword_filter_extension_masterDao> keywordlist ;
	private int projecttype;
	private String keyphrasename;
	private int wlid;
	private int aivalue;
	
	public int getWlid() {
		return wlid;
	}

	public void setWlid(int wlid) {
		this.wlid = wlid;
	}

	public int getAivalue() {
		return aivalue;
	}

	public void setAivalue(int aivalue) {
		this.aivalue = aivalue;
	}

	public String getKeyphrasename() {
		return keyphrasename;
	}

	public void setKeyphrasename(String keyphrasename) {
		this.keyphrasename = keyphrasename;
	}



	public int getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public List<Markscan_projecttype> getListData() {
		return listData;
	}

	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

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
	
			addActionMessage("Welcome to keyphrase!");
			return SUCCESS;
		}		
		
	}
	
	
	public String getkeyphrase() throws Exception {


			try {
				factory = LoginAndSession.getFactory();
				logger.info("---- factory---" + factory);
				Keyword_filter_extension_masterDao url3 = null;
				Keyword_filter_extension_masterDao dao1 = (Keyword_filter_extension_masterDao) factory.getBean("d4");	
				logger.info(".......LAUNCHhhhhhhhhhhhhhhhhhhhh.  KEYWORD......."+getProjecttype());
				lst = dao1.viewRecord("SELECT id,keyphrase,isactive FROM Keyword_filter_extension_master WHERE projectType="+getProjecttype()+" ORDER BY keyphrase ASC");	
				logger.info(".......KEYWORD SIZE........" + lst.size());
				keywordlist = new ArrayList<Keyword_filter_extension_masterDao>();

				for (int i = 0; i < lst.size(); i++) {
					url3 = new Keyword_filter_extension_masterDao();
					obj = (Object[]) lst.get(i);
					url3.setId((Integer) obj[0]);
					logger.info("-----------DATATATATATAT::: "+(String) obj[1]);
					if(obj[1] != null){
					  url3.setKeyphrase((String) obj[1]);
					}
					
					url3.setIsactive((Integer) obj[2]);
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
	
	public String addkeyphrasewhitelist() throws Exception {
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
	
			addActionMessage("Data added successfully");			
			
			try {	
				String pj;
				Keyword_filter_extension_master url4 = null;
				url4 = new Keyword_filter_extension_master();
				factory = LoginAndSession.getFactory();
				Keyword_filter_extension_masterDao dao2 = (Keyword_filter_extension_masterDao) factory.getBean("d4");
				url4.setProjectType((Integer) getProjecttype());
				url4.setIsactive(1);
				url4.setKeyphrase(getKeyphrasename());
				pj = dao2.saveData(url4);
				logger.info(".......Done........");

				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			}				
			return SUCCESS;
		}	
		
	}
	
	public String updateactivewhitelist() throws Exception {
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
				Keyword_filter_extension_masterDao dao2 = (Keyword_filter_extension_masterDao) factory.getBean("d4");

				pj = dao2.customUpdateProject("UPDATE Keyword_filter_extension_master SET isactive="+active+" WHERE id= "+getWlid());
				logger.info(".......Done........");

				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			}			
			return SUCCESS;
		}
	} 
	
	public String updatekeyphrase() throws Exception {
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
				Keyword_filter_extension_masterDao dao2 = (Keyword_filter_extension_masterDao) factory.getBean("d4");
				logger.info(".......keyphrase........:: "+ getKeyphrasename());
				pj = dao2.customUpdateProject("UPDATE Keyword_filter_extension_master SET keyphrase='"+getKeyphrasename()+"' WHERE id= "+getWlid());
				logger.info(".......Done........");

				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			}		
			addActionMessage("Keyphrase updated successfully!");
			return SUCCESS;
		}
	} 	

	public List<Keyword_filter_extension_masterDao> getKeywordlist() {
		return keywordlist;
	}

	public void setKeywordlist(List<Keyword_filter_extension_masterDao> keywordlist) {
		this.keywordlist = keywordlist;
	}
	
	

}
