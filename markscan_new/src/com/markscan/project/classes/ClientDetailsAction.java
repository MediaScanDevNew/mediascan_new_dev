package com.markscan.project.classes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Client_master;
import com.markscan.project.beans.Keyword_filter;
import com.markscan.project.beans.Keyword_filter_extension_master;
import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.PreStoredKeyPhrase;
import com.markscan.project.beans.Project_info;
import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.Keyword_filterDao;
import com.markscan.project.dao.Keyword_filter_extension_masterDao;
import com.markscan.project.dao.Markscan_usersDao;
import com.markscan.project.dao.PreStoredKeyPhraseDao;
import com.markscan.project.dao.Project_infoDao;
import com.opensymphony.xwork2.ActionSupport;

public class ClientDetailsAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ClientDetailsAction.class);
	BeanFactory factory = null;
	List lst = null;
	Object[] obj = null;


	public Map<Integer, String> getStateMap1() {
		return stateMap1;
	}

	public void setStateMap1(Map<Integer, String> stateMap1) {
		this.stateMap1 = stateMap1;
	}

	
	   /**
		 * get all client from system
		 * @return client details
		 */
		public String getAllClient() {		
			stateMap = new LinkedHashMap<Integer, String>();
			stateMap.put(0, "Select Client");
			Client_masterDao dao = null;
			try {
				factory = ActionPerform.getFactory();
				dao = (Client_masterDao) factory.getBean("d10");
				lst = dao.viewRecord("select id, client_name from Client_master");
				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);
					// url2.setId((Integer) obj[0]);
					// url2.setClient_name((String) obj[1]);
					stateMap.put((Integer) obj[0], (String) obj[1]);
					obj = null;
				}
				logger.info("site map size...Client_master getAllClient...." + stateMap.size());
			} catch (Exception e) {
				logger.error("get client type.... ", e);
			} finally {
				factory = null;
				dao = null;
				obj = null;
				lst = null;
			}
			return SUCCESS;
		}



	private Map<Integer, String> stateMap = null;
	private Map<Integer, String> stateMap1 = null;
	private Map<String, String> stateMape = null;
	private Map<Integer, String> userMap = null;
	Session session = null;
	Transaction tx = null;
	private String ptype = null;
	private String ctype = null;
	private String keyword1=null;
	private String ktype =null;
	private String pname=null;
	
	
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getKtype() {
		return ktype;
	}

	public void setKtype(String ktype) {
		this.ktype = ktype;
	}

	public String getKeyword() {
		return keyword1;
	}

	public void setKeyword(String keyword1) {
		this.keyword1 = keyword1;
	}

	public Map<String, String> getStateMape() {
		return stateMape;
	}

	public void setStateMape(Map<String, String> stateMape) {
		this.stateMape = stateMape;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Map<Integer, String> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<Integer, String> stateMap) {
		this.stateMap = stateMap;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public Map<Integer, String> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<Integer, String> userMap) {
		this.userMap = userMap;
	}

}
