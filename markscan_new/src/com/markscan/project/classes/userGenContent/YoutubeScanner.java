package com.markscan.project.classes.userGenContent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.BeanFactory;
import org.json.simple.JSONArray;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONSerializer;

import org.json.simple.JSONObject;

import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Crawl_youtubeDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Project_infoDao;


public class YoutubeScanner extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	

	private static final Logger logger = Logger.getLogger(YoutubeScanner.class);
	String filePath = "";	
	List lst = null;
	String project_name;
	Object[] obj = null;
	private List<Crawl_youtubeDao> cyData = null;
	BeanFactory factory = null;
	HttpSession session2 = null;
	private int id;
	private int cuid;
	private String movieIds; 
	private String returntext;
	public String getReturntext() {
		return returntext;
	}


	public void setReturntext(String returntext) {
		this.returntext = returntext;
	}


	public String getMovieIds() {
		return movieIds;
	}


	public void setMovieIds(String movieIds) {
		this.movieIds = movieIds;
	}


	public int getCuid() {
		return cuid;
	}


	public void setCuid(int cuid) {
		this.cuid = cuid;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private String source;
	private String projecttype;
	private String clientname;
	private String pinfo;
	private String dtime;
	private String utime;
	private String search;
	private Integer userId;
	private List<Markscan_projecttype> listData = null; 
	private List<Client_masterDao> clientData = null;
	private List<Project_infoDao> proinfoData = null;
	
	Client_masterDao dao1 = null;
	Project_infoDao dao2 = null;
	public String getProjecttype() {
		return projecttype;
	}


	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}


	public String getClientname() {
		return clientname;
	}


	public void setClientname(String clientname) {
		this.clientname = clientname;
	}


	public String getPinfo() {
		return pinfo;
	}


	public void setPinfo(String pinfo) {
		this.pinfo = pinfo;
	}


	public String getDtime() {
		return dtime;
	}


	public void setDtime(String dtime) {
		this.dtime = dtime;
	}


	public String getUtime() {
		return utime;
	}


	public void setUtime(String utime) {
		this.utime = utime;
	}


	public String getSearch() {
		return search;
	}


	public void setSearch(String search) {
		this.search = search;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public  String youtubehit() throws IOException{
		
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
				return ERROR;
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
				lst = dao4.viewRecord("SELECT id,thumbnail_url,video_name,url_link FROM Crawl_youtube WHERE user_id="+userId+" "
									+ "AND project_type="+getProjecttype()+" AND client_id="+getClientname()+" AND keyword LIKE'%"
									+ ""+getSearch()+"%' AND analysis_done =0 AND deleted_flag =0");

				logger.info(".......crawlsizeeeeeeeeeeee........" + lst.size());
				cyData = new ArrayList<Crawl_youtubeDao>();
				
				if(lst.size()>0){
					for (int i = 0; i < lst.size(); i++) {
						url4 = new Crawl_youtubeDao();
						obj = (Object[]) lst.get(i);
						url4.setId((Integer) obj[0]);
						url4.setThumbnail_url((String) obj[1]);
						url4.setVideo_name((String) obj[2]);
						url4.setUrl_link((String) obj[3]);
						cyData.add(url4);					
						obj = null;
						url4 = null;
					}
					logger.info(".......crawldaaataaaaaaaaaaaa........" +cyData);		
					url4 = null;
					lst = null;
					dao4 = null;
					addActionMessage("All data fetched !");			
				return SUCCESS;	
				}else{
					logger.info("XXXXXXXXXXXXXX----ELSE-----------XXXXXXXXX");
					Thread t1 = new Thread(new Runnable() {
					    @Override
					    public void run() {
                            try{
            					ProcessBuilder pb = new ProcessBuilder("java", "-jar", "/var/www/eclipse/youtubeScanner.jar",project_name+" "+getSearch(),""+userId+"",getSource(),getProjecttype(),getClientname(),getPinfo(),getDtime(),getUtime(),getSearch() );       
            					Process p = pb.start();	

            					// blocked :(
            		            BufferedReader reader =
            		                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            		            String line;
            		            while ((line = reader.readLine()) != null) {
            		                System.out.println(line);
            		            }

            		            int exitCode = p.waitFor();
            		            System.out.println("\nExited with error code : " + exitCode);
                            }catch(Exception e){
                				logger.error("Thread Error ", e);
                				
                            }
					    }
					});  
					t1.start();
					System.out.println("XXXXXXXXXXXXXXXXXx execution done XXXXXXXXXXXXXXXXXXXXXx ");
					addActionMessage("Please Wait 15 min to process.You will get a mail with search param!");
					return SUCCESS;
				}
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			}	
			

		}		

		
	
}


	public String deleterowdeleterow() throws Exception {
		int pj;
		try {			
			factory = LoginAndSession.getFactory();
			Crawl_youtubeDao dao4 = (Crawl_youtubeDao) factory.getBean("d43");
			pj = dao4.customUpdateProject("UPDATE Crawl_youtube SET deleted_flag=1  WHERE id="+getCuid());
			logger.info(".......Done........");

			
			
		} catch (Exception e) {
			logger.error("get user error ", e);
			return ERROR;
		}			
		return SUCCESS;

	}	
	
	
	public String updatecyrow() throws Exception {
		int pj;
		try {	
			logger.info("MOVIEID:: "+getMovieIds());
			//String mids = getMovieIds();
			

			factory = LoginAndSession.getFactory();
			Crawl_youtubeDao dao4 = (Crawl_youtubeDao) factory.getBean("d43");
			ArrayList<String> moviedata = new ArrayList<String>();  
			
			String st = getMovieIds();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(st);
			JSONArray items = (JSONArray) obj;
			for (int i=0;i<items.size();i++){
				JSONObject videoData = (JSONObject) items.get(i);
				System.out.println("Value is ---------->"+videoData);				
				String id = videoData.get("id").toString();
				String size = videoData.get("size").toString();
				String type = videoData.get("type").toString();
				System.out.println("----------ALL DATA ---------->"+id+"---"+size+"---"+type);
				pj = dao4.customUpdateProject("UPDATE Crawl_youtube SET analysis_done=1,size='"+size+"' , type='"+type+"'  WHERE id="+id);
			}


			
			logger.info(".......Done........");
			returntext = "Please Wait 15 min to process.You will get a mail with search param!";
			
			
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


	public List<Crawl_youtubeDao> getCyData() {
		return cyData;
	}


	public void setCyData(List<Crawl_youtubeDao> cyData) {
		this.cyData = cyData;
	}


}// End Class