package com.markscan.project.classes.userGenContent;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.markscan.project.beans.Client_master;
import com.markscan.project.beans.CommanReporting;
import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Domain_Mst;
import com.markscan.project.beans.Imdb_content_detail;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.ProjectDashboardBean;
import com.markscan.project.beans.Project_content_tdays;
import com.markscan.project.beans.Project_info;
import com.markscan.project.beans.Tv_content_tdays;
import com.markscan.project.classes.Reporting;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Crawle_url2Dao;
import com.markscan.project.dao.Domain_masterDao;
import com.markscan.project.dao.Imdb_content_detailDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Markscan_usersDao;
import com.markscan.project.dao.Project_content_tdaysDao;
import com.markscan.project.dao.Project_infoDao;
import com.markscan.project.dao.Tv_content_tdaysDao;
import com.markscan.project.dao.Url_emailDao;
import com.opensymphony.xwork2.ActionSupport;




public class UserGenContent extends ActionSupport  {	
		private static final long serialVersionUID = 1L;
		HttpSession session2 = null;
		Object[] obj = null;
		private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		private static final Logger logger = Logger.getLogger(UserGenContent.class);
		BeanFactory factory = null;
		List lst = null;
		private List<Markscan_projecttype> listData = null; 
		private List<Client_masterDao> clientData = null;
		private List<Project_infoDao> proinfoData = null;
		Client_masterDao dao1 = null;
		Project_infoDao dao2 = null;
		//------------------------------------------------------------------------	
		

		private String clientname;
		private String projecttype;
		private String returntext;


	public String getReturntext() {
			return returntext;
		}

		public void setReturntext(String returntext) {
			this.returntext = returntext;
		}

	public String getClientname() {
			return clientname;
		}

		public void setClientname(String clientname) {
			this.clientname = clientname;
		}

		public String getProjecttype() {
			return projecttype;
		}

		public void setProjecttype(String projecttype) {
			this.projecttype = projecttype;
		}

	public UserGenContent() {
		// One one = new One();
		// con = one.getCon();
		// dbq = new DbQuery();
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
				logger.info("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
				lst = dao.viewRecord("select id, name from Markscan_projecttype order by name");				
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
				Client_masterDao url3 = null;
				factory = LoginAndSession.getFactory();
				Client_masterDao dao1 = (Client_masterDao) factory.getBean("d10");
				lst = dao1.viewRecord("SELECT id,client_name FROM Client_master order by client_name asc");				
				clientData = new ArrayList<Client_masterDao>();
				
				
				for (int i = 0; i < lst.size(); i++) {
					url3 = new Client_masterDao();
					obj = (Object[]) lst.get(i);
					url3.setId((Integer) obj[0]);					
					url3.setClient_name((String) obj[1]);
					clientData.add(url3);					
					obj = null;
					url3 = null;
				}
				url3 = null;
				lst = null;
				dao1 = null;
				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			} 	
			addActionMessage("Welcome to UGC search!");
			return SUCCESS;
		}		
		
	}
	
	public String getproperty() throws Exception {

		try {
			Project_infoDao url4 = null;
			factory = LoginAndSession.getFactory();
			Project_infoDao dao2 = (Project_infoDao) factory.getBean("d2");
			lst = dao2.viewRecord("SELECT id,project_name FROM Project_info  WHERE project_type="+getProjecttype()+" AND client_type="+getClientname()+" order by project_name ASC");
			logger.info(".......lstsizeeeeee22222........" + lst.size());
			proinfoData = new ArrayList<Project_infoDao>();
			
			
			for (int i = 0; i < lst.size(); i++) {
				url4 = new Project_infoDao();
				obj = (Object[]) lst.get(i);
				url4.setId((Integer) obj[0]);
				logger.info("---------------client_name-------------------"+(String) obj[1]);
				url4.setProject_name((String) obj[1]);
				getProinfoData().add(url4);					
				obj = null;
				url4 = null;
			}
			url4 = null;
			lst = null;
			dao2 = null;
			
			
		} catch (Exception e) {
			logger.error("get user error ", e);
			return ERROR;
		}			
		return SUCCESS;

	}
	

	
	
	public List<Markscan_projecttype> getListData() {
		return listData;
	}

	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

	public List<Client_masterDao> getClientData() {
		return clientData;
	}

	public void setClientData(List<Client_masterDao> clientData) {
		this.clientData = clientData;
	}

	public List<Project_infoDao> getProinfoData() {
		return proinfoData;
	}

	public void setProinfoData(List<Project_infoDao> proinfoData) {
		this.proinfoData = proinfoData;
	}	

	

}

