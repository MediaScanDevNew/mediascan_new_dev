package com.markscan.project.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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



public class AjaxJsonAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AjaxJsonAction.class);
	BeanFactory factory = null;
	List lst = null;
	Object[] obj = null;

	public String execute() {
		// System.out.println("==== ptype----: " + ptype);
		logger.info("==== ptype----:     " + ptype);
		stateMap = new LinkedHashMap<Integer, String>();
		stateMap.put(0, "Select Client");
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		
		Client_masterDao dao = null;
		try {
			factory = ActionPerform.getFactory();
			dao = (Client_masterDao) factory.getBean("d10");
			//System.out.println("----->select id, client_name from Client_master where project_type ='" + ptype + "'");
			lst = dao.viewRecord("select id, client_name from Client_master where project_type ='" + ptype + "' order by client_name");
			for (int i = 0; i < lst.size(); i++) {
				obj = (Object[]) lst.get(i);
				stateMap.put((Integer) obj[0], (String) obj[1]);
				System.out.println("(String) obj[1]---->"+(String) obj[1]);
				obj = null;
			}
			
			//stateMap = sortHashMapByValues(hm);
			logger.info("site map size...Client_master...." + stateMap.size());
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

	public String execute1() {

		logger.info("==== property----:     " + ctype);
		logger.info("==== pp property----:     " + ptype);
		// System.out.println("==== property----: " + ctype);
		// System.out.println("==== pp property----: " + ptype);
		stateMap = new LinkedHashMap<Integer, String>();
		stateMap.put(0, "Select Property");
		Project_infoDao dao = null;
		try {

			factory = ActionPerform.getFactory();
			dao = (Project_infoDao) factory.getBean("d2");
			lst = dao.viewRecord("select pi.id, pi.project_name from Project_info as pi, Client_master as cm  "
					+ " where cm.id = pi.client_type and cm.id='" + ctype + "' and " + "pi.project_type= '"
					+ ptype.trim() + "' ");

			for (int i = 0; i < lst.size(); i++) {
				// Project_info url2 = new Project_info();
				obj = (Object[]) lst.get(i);
				// url2.setId((Integer) obj[0]);
				// url2.setProject_name((String) obj[1]);
				stateMap.put((Integer) obj[0], (String) obj[1]);
				obj = null;
			}
			logger.info("*******************Client Ajex******************");
			logger.info("site map size..Project_info....." + stateMap.size());
			lst = null;

		} catch (Exception e) {
			lst = null;
			logger.error("get project info error ...", e);
		} finally {
			factory = null;
			dao = null;
			obj = null;
			lst = null;
		}

		return SUCCESS;
	}

	
	public String execute2() {
		// System.out.println("==== 1 property----: " + ctype);
		logger.info("==== 1 property----:     " + ctype);
		stateMap = new LinkedHashMap<Integer, String>();
		stateMap.put(0, "Select Property");
		Markscan_usersDao dao = null;
		try {

			factory = ActionPerform.getFactory();
			dao = (Markscan_usersDao) factory.getBean("d9");

			lst = dao.viewRecord("select id, name from Markscan_users where status=0");

			for (int i = 0; i < lst.size(); i++) {
				// Markscan_users url2 = new Markscan_users();
				obj = (Object[]) lst.get(i);
				// url2.setId((Integer) obj[0]);
				// url2.setName((String) obj[1]);
				stateMap.put((Integer) obj[0], (String) obj[1]);
				obj = null;
			}
			logger.info("site map size..Markscan_users....." + stateMap.size());
			// System.out.println("site map size..Markscan_users....." +
			// stateMap.size());
			lst = null;

		} catch (Exception e) {
			lst = null;
			logger.error("markscan users error ... ", e);
		} finally {
			factory = null;
			dao = null;
			obj = null;
			lst = null;
		}

		return SUCCESS;
	}

	public String execute3() {
		PreStoredKeyPhraseDao dao = null;
		try {
			// System.out.println("==== property--- project name-: " + ctype);
			// System.out.println("==== pp property-- execute 3--: " + ptype);
			logger.info("==== property--- project name-:     " + ctype);
			logger.info("==== pp property-- execute 3--:     " + ptype);
			logger.info("*******************Project Ajex******************");
			logger.info("===1");
			// System.out.println("===1");
			stateMape = new LinkedHashMap<String, String>();
			
			// stateMape.put(0, "Select Property");
			logger.info("===2");

			// System.out.println("===2");
			try {

				factory = ActionPerform.getFactory();
				dao = (PreStoredKeyPhraseDao) factory.getBean("d11");

				//lst = dao.viewRecord("select psk.id,psk.keyphrase from PreStoredKeyPhrase as psk where projectType="
					//	+ Integer.parseInt(ptype.trim()));
				lst = dao.viewRecord("select psk.id,psk.keyphrase from PreStoredKeyPhrase as psk where"+ "project_type= '"
					+ ptype.trim() + "' ");


				for (int i = 0; i < lst.size(); i++) {
					// PreStoredKeyPhrase url2 = new PreStoredKeyPhrase();
					obj = (Object[]) lst.get(i);
					// url2.setId((Integer) obj[0]);
					// url2.setKeyphrase((String) obj[1]);
					stateMap.put((Integer) obj[0], (String) obj[1]);
					obj = null;
				}
				logger.info("site map size..PreStoredKeyPhrase....." + stateMap.size());
				// System.out.println("site map size..PreStoredKeyPhrase....." +
				// stateMap.size());
				lst = null;

			} catch (Exception e) {
				lst = null;
				logger.error("... pre stored keypharase error ....", e);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			factory = null;
			dao = null;
			obj = null;
			lst = null;
		}
		return SUCCESS;
	}
	
	/************** static keywords  ************/
	
	public String execute4()
	{
		logger.info("Static Keyword Ajex");
		//Keyword_filterDao dao = null;
		Keyword_filter_extension_masterDao dao =null;
		try {
			//logger.info("==== keyword property-- execute 4--:     " + ptype);
			// System.out.println("==== property--- project name-: " + ctype);
			// System.out.println("==== pp property-- execute 3--: " + ptype);
			//logger.info("==== property--- project name-:     " + ctype);
			//logger.info("==== keyword property-- execute 4--:     " + ptype);
			//logger.info("==== keyword-- execute 3--:     " + keyword1);
			logger.info("===1");
			// System.out.println("===1");
			stateMap1 = new LinkedHashMap<Integer, String>();
			//stateMap.put(0, "Select Property");
			logger.info("===2");

			// System.out.println("===2");
			
			try {
				
				factory = ActionPerform.getFactory();
				dao = (Keyword_filter_extension_masterDao) factory.getBean("d4");

				logger.info("=============Connection stablish======================="+dao);
			 /*
			 lst = dao.viewRecord("select psk.id,psk.keyword from Keyword_filter as psk where "+ "psk.project_id= '"
				+ pname.trim() + "' ");
				
				*/
				
				//lst = dao.viewRecord("select kfem.id,kfem.keyphrase from Keyword_filter_extension_master as kfem where kfem.projectType= '"+ ptype+"'");
				String sql = "select kfem.id,kfem.keyphrase from Keyword_filter_extension_master as kfem where kfem.projectType= '"+ ptype+"'";
				System.out.println(sql);
				lst= dao.viewRecord(sql);
				logger.info("=============List data======================="+ptype.trim());

				for (int i = 0; i < lst.size(); i++) {
					logger.info("==========================data retriver.............................");
					 Keyword_filter_extension_master url2 = new Keyword_filter_extension_master();
					obj = (Object[]) lst.get(i);
					logger.info("==========================Record1............................."+obj[0]);
					logger.info("==========================Record2............................."+obj[1]);
					url2.setId((Integer) obj[0]);
					//url2.setKeyword((String) obj[1]);
					url2.setKeyphrase((String) obj[1]);
					logger.info("=========================Retrival1============");
					 //url2.setId((Integer) obj[0]);
					 //url2.setKeyphrase((String) obj[1]);
					stateMap1.put((Integer)obj[0],(String)obj[1]);
					logger.info("=========================Retrival2============"+stateMap1);
					//stateMap.put(123, "HPSingh");
					obj = null;
					
				}
				
				//stateMap.put(123, "HPSingh");
				logger.info("site map size..keywords....." + stateMap1.size());
				// System.out.println("site map size..PreStoredKeyPhrase....." +
				// stateMap.size());
				lst = null;

			} catch (Exception e) {
				lst = null;
				logger.error("... Keywords fatching error ....", e);
			}
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			factory = null;
			dao = null;
			obj = null;
			lst = null;
		}
		
		return SUCCESS;
	}
	

	public Map<Integer, String> getStateMap1() {
		return stateMap1;
	}

	public void setStateMap1(Map<Integer, String> stateMap1) {
		this.stateMap1 = stateMap1;
	}

	public String executeDate() {
		Crawle_url2Dao dao = null;
		try {
			// System.out.println("==== property--- project name-: " + ctype);
			// System.out.println("==== pp property-- execute 3--: " + ptype);
			logger.info("==== property--- project name-:     " + ctype);
			// logger.info("==== pp property-- execute 3--: " + ptype);
			logger.info("===1");
			// System.out.println("===1");
			stateMape = new LinkedHashMap<String, String>();
			// stateMape.put(0, "Select Property");
			logger.info("===2");

			// System.out.println("===2");
			try {

				factory = ActionPerform.getFactory();
				dao = (Crawle_url2Dao) factory.getBean("dash");

				lst = dao.viewRecord(
						"select distinct(domain_name) from Crawle_url2 where created_on between"
						+ " '"+ctype+"' and ADDDATE('"+ctype+"',1)");

				for (int i = 0; i < lst.size(); i++) {
					// PreStoredKeyPhrase url2 = new PreStoredKeyPhrase();
//					obj = (Object[]) lst.get(i);
					// url2.setId((Integer) obj[0]);
					// url2.setKeyphrase((String) obj[1]);
					stateMape.put((String) lst.get(i).toString(), lst.get(i).toString());
//					obj = null;
				}
				//logger.info("site map size..domain size........." + stateMape.size());
				// System.out.println("site map size..PreStoredKeyPhrase....." +
				// stateMap.size());
				lst = null;

			} catch (Exception e) {
				lst = null;
				logger.error("... pre stored keypharase error ....", e);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			factory = null;
			dao = null;
			obj = null;
			lst = null;
		}
		return SUCCESS;
	}
	
	   /**
		 * get all client from system
		 * @return client details
		 */
//		public String getAllClient() {		
//			stateMap = new LinkedHashMap<Integer, String>();
//			stateMap.put(0, "Select Client");
//			Client_masterDao dao = null;
//			try {
//				factory = ActionPerform.getFactory();
//				dao = (Client_masterDao) factory.getBean("d10");
//				lst = dao.viewRecord("select id, client_name from Client_master");
//				for (int i = 0; i < lst.size(); i++) {
//					obj = (Object[]) lst.get(i);
//					// url2.setId((Integer) obj[0]);
//					// url2.setClient_name((String) obj[1]);
//					stateMap.put((Integer) obj[0], (String) obj[1]);
//					obj = null;
//				}
//				logger.info("site map size...Client_master getAllClient...." + stateMap.size());
//			} catch (Exception e) {
//				logger.error("get client type.... ", e);
//			} finally {
//				factory = null;
//				dao = null;
//				obj = null;
//				lst = null;
//			}
//			return SUCCESS;
//		}



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
	
	
	public LinkedHashMap<Integer, String> sortHashMapByValues(
	        HashMap<Integer, String> passedMap) {
	    List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<String> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<Integer, String> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<String> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        String val = valueIt.next();
	        Iterator<Integer> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            Integer key = keyIt.next();
	            String comp1 = passedMap.get(key);
	            String comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}

}
