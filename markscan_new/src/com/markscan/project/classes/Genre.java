package com.markscan.project.classes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.dao.GenreDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * created on 13/02/20
 * @author pentation/m
 *
 */
public class Genre extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AjaxJsonAction.class);
	BeanFactory factory = null;
	List lst = null;
	Object[] obj = null;
	private Map<Integer, String> stateMap = null;
	private String ptype = null;
	private String language =null;
	
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * get genre according to project type
	 * @return client details
	 */
	public String execute() {
		stateMap = new LinkedHashMap<Integer, String>();
//		stateMap.put(0, "Select Client");
//		genreList=new ArrayList<Genre>();
		GenreDao dao = null;
		List list = null;
		try {
			factory = ActionPerform.getFactory();
			dao = (GenreDao) factory.getBean("d41");
			list = dao.viewRecord("select id,genre from Genre where project_type ='" + ptype + "' and language='"+language+"'");
			System.out.println("size:"+list.size());
//			if(list.size()>0){
//				genre = list.get(0).toString();
//			}else{
//				genre ="";
//			}
			for (int i = 0; i < list.size(); i++) {
				// Markscan_users url2 = new Markscan_users();
				obj = (Object[]) list.get(i);
				// url2.setId((Integer) obj[0]);
				// url2.setName((String) obj[1]);
				stateMap.put((Integer) obj[0], (String) obj[1]);
				obj = null;
			}
			//logger.info("site map size...Genre...." + stateMap.size());
		} catch (Exception e) {
			logger.error("get genre type.... ", e);
		} finally {
			factory = null;
			dao = null;
			obj = null;
			lst = null;
		}
		return SUCCESS;

	}
}
