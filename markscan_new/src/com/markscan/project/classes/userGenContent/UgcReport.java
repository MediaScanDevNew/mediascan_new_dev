package com.markscan.project.classes.userGenContent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Master_crawle_url;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Crawl_youtubeDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Project_infoDao;
import com.opensymphony.xwork2.ActionSupport;

public class UgcReport extends ActionSupport{
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
	
    private String source;
	private String clientname;
	private String projecttype;
	private String returntext;
	String filePath = "";	

	String project_name;

	private List<Crawl_youtubeDao> cyData = null;

	private int id;
	private int cuid;
	private String movieIds; 
	private String pinfo;
	private String dtime;
	private String utime;
	private String search;
	private Integer userId;


	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public List<Crawl_youtubeDao> getCyData() {
		return cyData;
	}

	public void setCyData(List<Crawl_youtubeDao> cyData) {
		this.cyData = cyData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCuid() {
		return cuid;
	}

	public void setCuid(int cuid) {
		this.cuid = cuid;
	}

	public String getMovieIds() {
		return movieIds;
	}

	public void setMovieIds(String movieIds) {
		this.movieIds = movieIds;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPinfo() {
		return pinfo;
	}

	public void setPinfo(String pinfo) {
		this.pinfo = pinfo;
	}

	public String getUtime() {
		return utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}






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

public UgcReport() {
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
		addActionMessage("Welcome to UGC Report!");
		return SUCCESS;
	}		
	
}

public  void reportdownload() throws Exception{
	HttpServletResponse response = ServletActionContext.getResponse();
	String outputString = null;
	ArrayList<String> rows = null;
	String reportName = null;
	Iterator<String> iter = null;

	session2 = ServletActionContext.getRequest().getSession();

		
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
			
		} 	
		try {
			Project_infoDao url4 = null;
			factory = LoginAndSession.getFactory();
			Project_infoDao dao2 = (Project_infoDao) factory.getBean("d2");
			lst = dao2.viewRecord("SELECT id,project_name FROM Project_info order by project_name ASC");
			logger.info(".......lstsizeeeeee22222........" + lst.size());
			proinfoData = new ArrayList<Project_infoDao>();
			
			
			for (int i = 0; i < lst.size(); i++) {
				url4 = new Project_infoDao();
				obj = (Object[]) lst.get(i);
				url4.setId((Integer) obj[0]);
				logger.info("---------------client_name-------------------"+(String) obj[1]);
				url4.setProject_name((String) obj[1]);
				proinfoData.add(url4);					
				obj = null;
				url4 = null;
			}
			url4 = null;
			lst = null;
			dao2 = null;
			
			
		} catch (Exception e) {
			logger.error("get user error ", e);
			
		}				
		
		
		logger.info("LOGIN INFO:: "+ (int) session2.getAttribute("uid"));
		userId = (int) session2.getAttribute("uid");
		factory = LoginAndSession.getFactory();
		Project_infoDao dao3 = (Project_infoDao) factory.getBean("d2");
		lst = dao3.viewRecord("SELECT project_name FROM Project_info WHERE id = "+getPinfo());	
				
		project_name = (String) lst.get(0);
		lst = null;
		dao3 = null;
		System.out.println("getSource:: "+getSource());
		System.out.println("getProjecttype:: "+getProjecttype());
		System.out.println("getClientname:: "+getClientname()); 
		System.out.println("getPinfo:: "+getPinfo()); 
		System.out.println("Project Name:: "+project_name); 
		System.out.println("getDtime:: "+getDtime()); 
		System.out.println("getUtime:: "+getUtime()); 
		System.out.println("getSearch:: "+getSearch());
		
		try {
			Crawl_youtubeDao url4 = null;
			factory = LoginAndSession.getFactory();
			Crawl_youtubeDao dao4 = (Crawl_youtubeDao) factory.getBean("d43");
			lst = dao4.viewRecord("SELECT url_link,video_name,view_count,video_duration,channel_name,subscriber_count,like_count FROM Crawl_youtube WHERE user_id="+userId+" "
								+ "AND project_type="+getProjecttype()+" AND client_id="+getClientname()+" AND project_id="+getPinfo()+" AND analysis_done =1 AND deleted_flag =0");

			logger.info(".......crawlsizeeeeeeeeeeee........" + lst.size());
			cyData = new ArrayList<Crawl_youtubeDao>();

		
				response.setContentType("text/csv");
				reportName = "crawl_data.csv";
				response.setHeader("Content-disposition", "attachment;filename=" + reportName);

				rows = new ArrayList<String>();
				rows.add("URL LINKS,VIDEO NAME,VIEWS,VIDEO DURATION,CHANNEL NAME,SUB COUNT,LIKE COUNT");
				rows.add("\n");			
		
				for (int i = 0; i < lst.size(); i++) {
					url4 = new Crawl_youtubeDao();
					obj = (Object[]) lst.get(i);
				
					System.out.println("\"" + obj[0] + "\",\"" + obj[1] + "\",\"" + obj[2] + "\",\""
							+ obj[3] + "\",\"" + obj[4] + "\",\"" + obj[5] + "\",\"" + obj[6] + "\",\""+ "\"");
					rows.add("\"" + obj[0] + "\",\"" + obj[1] + "\",\"" + obj[2] + "\",\""
							+ obj[3] + "\",\"" + obj[4] + "\",\"" + obj[5] + "\",\"" + obj[6] + "\",\"" + "\"");
					rows.add("\n");
	
				}
						iter = rows.iterator();
						while (iter.hasNext()) {
							outputString = (String) iter.next();
							// response.getOutputStream().print(outputString);
							response.getWriter().print(outputString);
							outputString = null;
						}

						// response.getOutputStream().flush();
						response.getWriter().flush();

				
					
			
				logger.info(".......crawldaaataaaaaaaaaaaa........" +cyData);		
				url4 = null;
				lst = null;
				dao4 = null;

			
		} catch (Exception e) {
			logger.error("get user error ", e);
			
		}finally {
			response = null;
			outputString = null;
			rows = null;
			reportName = null;
			iter = null;
		}	
		

	

	

}


public String getSearch() {
	return search;
}

public void setSearch(String search) {
	this.search = search;
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
