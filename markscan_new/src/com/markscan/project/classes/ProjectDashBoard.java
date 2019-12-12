package com.markscan.project.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.google.api.client.http.HttpRequest;
import com.markscan.project.beans.Client_master;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.ProjectDashboardBean;
import com.markscan.project.beans.Tv_content_tdays;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Module_wise_email_templateDao;
import com.markscan.project.dao.Project_content_tdaysDao;
import com.markscan.project.dao.Project_infoDao;
import com.markscan.project.dao.Tv_content_tdaysDao;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectDashBoard extends ActionSupport {

	private static final Logger logger = Logger.getLogger(ProjectDashBoard.class);
	HttpSession session2 = null;
	BeanFactory factory = null;
	BeanFactory factory1 = null;
	String date = null;
	List lst = null;
	List days =null;
	Object[] obj = null;
	Object[] obj1 = null;
	Object[] obj2 = null;
	Tv_content_tdaysDao daysDao =null;
	
	String p_type=null;
	Markscan_projecttypeDao dao1 = null;
	
	private List<Markscan_projecttype> project_type_Data = null;
	Client_masterDao cmdao = null;
	private ArrayList<Client_master> client_type_Data = null; 
	Project_content_tdaysDao pdaysDao= null;
	List days1 =null;
	
	
	
	
	
	
	public List<Markscan_projecttype> getProject_type_Data() {
		return project_type_Data;
	}

	public void setProject_type_Data(List<Markscan_projecttype> project_type_Data) {
		this.project_type_Data = project_type_Data;
	}
	
	public List<Client_master> getClient_type_Data() {
		return client_type_Data;
	}

	public void setClient_type_Data(ArrayList<Client_master> client_type_Data) {
		this.client_type_Data = client_type_Data;
	}
	
	
	

	public String getP_type() {
		return p_type;
	}

	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	
	
	
	public String getdashboardDtls() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session error dashboard method... " + logger.getClass());
			return LOGIN;
		} else {
			ProjectDashboardBean bean = null;
			Tv_content_tdays tv=null;
			
			Project_infoDao dao = null;
			try {
				// Resource r = new ClassPathResource("applicationContext.xml");
				// BeanFactory factory = new XmlBeanFactory(r);
				factory = ActionPerform.getFactory();
				dao = (Project_infoDao) factory.getBean("d1");
				factory1 =ActionPerform.getFactory();
				
				if(client_name.trim().length() <1){
					lst = dao.getData("Select pi.id,pi.project_name,pi.created_on ,"
							// + "pi.start_date,pi.end_date,"
							+ "cm.client_name, mpt.name, pi.file_attach_link, pi.actual_hosted_site , pi.channel_name, pi.ttime,"
							+ "pi.language,pi.realeasingDate,pi.property_category,pi.current_value,pi.archive_value,pi.last_updated_on "
							+ " from Project_info pi, Client_master cm, Markscan_projecttype mpt "
							+ " where mpt.id="+p_type+" and cm.id = pi.client_type and mpt.id = pi.project_type and  pi.closed = ?", 0);
				}else{
					
					lst = dao.getData("Select pi.id,pi.project_name,pi.created_on ,"
							// + "pi.start_date,pi.end_date,"
							+ "cm.client_name, mpt.name, pi.file_attach_link, pi.actual_hosted_site , pi.channel_name, pi.ttime,"
							+ "pi.language,pi.realeasingDate,pi.property_category,pi.current_value,pi.archive_value,pi.last_updated_on"
							+ " from Project_info pi, Client_master cm, Markscan_projecttype mpt "
							+ " where mpt.id="+p_type+" and cm.id="+client_name+" and cm.id = pi.client_type and mpt.id = pi.project_type and  pi.closed = ?", 0);
				
					
				}
				
				logger.info("................" + lst.size());
				infos = new ArrayList<ProjectDashboardBean>();
				String d[]=null;

				for (int i = 0; i < lst.size(); i++) {
					bean = new ProjectDashboardBean();
					obj = (Object[]) lst.get(i);
					System.out.println("i value is ---------->"+i);
					bean.setId((Integer) obj[0]);
					bean.setProject_name((String) obj[1]);
					date = (String) obj[2];
					//System.out.println("***************Project Date**********"+date);
					date = date.replace(".0", "");
					// System.err.println((String)obj[2]);
					bean.setCreated_on(date);
					// bean.setStart_date((String) obj[3]);
					// bean.setEnd_date((String) obj[4]);
					bean.setClient_name((String) obj[3]);
					client_nm = ((String) obj[3]);
					bean.setName((String) obj[4]);
					bean.setFile_attach_link((String) obj[5]);
					bean.setActual_hosted_site((String) obj[6]);
					bean.setChannel_name((String) obj[7]);
					bean.setTtime((String) obj[8]);
					bean.setLanguage((String) obj[9]);
					bean.setRealeasingDate((String) obj[10]);
					bean.setProperty_category((String) obj[11]);
					bean.setArchive_value((String) obj[13]);
					bean.setCurrent_value((String) obj[12]);
					bean.setCloseFlag(0);
					
					daysDao = (Tv_content_tdaysDao) factory.getBean("d37");
					days =daysDao.viewRecord("select telecast_days from Tv_content_tdays where projectId="+bean.getId());
					String d1[] =new String[days.size()];
					
					for(int j=0; j<days.size();j++)
					{
						d1[j] =(String)days.get(j);
					}
					
					bean.setTelecast_days(d1);
					
					
					pdaysDao = (Project_content_tdaysDao) factory.getBean("d39");
					days1 =pdaysDao.viewRecord("select archive_days from Project_content_tdays where projectName='"+bean.getProject_name()+"'");
					String d2[] =new String[days1.size()];
					
					for(int j=0; j<days1.size();j++)
					{
						d2[j] =(String)days1.get(j);
					}
					bean.setArchive_days(d2);
					
					infos.add(bean);
					obj = null;
					bean = null;
					date = null;
					// break;
					days=null;
				}
				
				dao1 = (Markscan_projecttypeDao) factory.getBean("d8");
				lst = dao1.viewRecord("select id, name from Markscan_projecttype");
				project_type_Data = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					Markscan_projecttype url2 = new Markscan_projecttype();
					Object[] obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					project_type_Data.add(url2);
					//p_type = 0;
				}
				
				cmdao = (Client_masterDao) factory.getBean("d10");
				lst = cmdao.viewRecord("select id, client_name from Client_master where project_type ='" + p_type + "'");
				client_type_Data = new ArrayList<Client_master>();
				for (int k = 0; k < lst.size(); k++) {
					obj = (Object[]) lst.get(k);
					Client_master Mbn = new Client_master();
					Mbn.setId((Integer) obj[0]);
					Mbn.setClient_name((String) obj[1]);
					client_type_Data.add(Mbn);
					obj = null;
					
					Collections.sort(client_type_Data);
				}
				
				System.out.println("client_type_Data size is ------------------>"+client_type_Data.size());
				date = null;
				

			} catch (Exception e) {
				// e.printStackTrace();
				date = null;
				logger.error("project dash board error ---", e);
				// System.err.println("project dash board error ---" + e);
				return ERROR;
			} finally {
				obj = null;
				bean = null;
				date = null;
				lst = null;
				dao = null;
				factory = null;
				session2 = null;
				cmdao = null;

			}
			return SUCCESS;
		}
	}
	
	
	public String dashboard() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session error dashboard method... " + logger.getClass());
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			try{
				dao1 = (Markscan_projecttypeDao) factory.getBean("d8");
				lst = dao1.viewRecord("select id, name from Markscan_projecttype");
				project_type_Data = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					Markscan_projecttype url2 = new Markscan_projecttype();
					Object[] obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					project_type_Data.add(url2);
					//p_type = 0;
				}
				client_type_Data = new ArrayList<Client_master>();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			} finally {
				factory=null;
				session2=null;
				lst = null;
				dao1 = null;
				
			}
			
			return SUCCESS;
		}// end else part...
	}

	public String editProject() { // not working.....
		session2 = ServletActionContext.getRequest().getSession();
		System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session error editProject method... " + logger.getClass());
			return LOGIN;
		} else {
			
			// System.out.println("else========='");
			session2=null;
			return SUCCESS;
		}
	}

	public String projectUpdate() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session error projectUpdate method... " + logger.getClass());
			return LOGIN;
		} else {
			System.out.println("****************Testing************");
			Project_infoDao dao=null;
			try {
				System.out.println("****************Testing1************");
				factory = ActionPerform.getFactory();
								 
				  dao = (Project_infoDao) factory.getBean("d2");
				  daysDao = (Tv_content_tdaysDao) factory.getBean("d37");
				  Tv_content_tdays tcd= new Tv_content_tdays();
				  if(name=="TV Content")
					{
						
						
					String days[] =ServletActionContext.getRequest().getParameterValues("days");
					for(String s1:days)
					{
						System.out.println("********************project_name*************"+project_name);
						System.out.println("**********************Telecast day***********"+s1);
						System.out.println("*********************************"+4);
						tcd=new Tv_content_tdays();
						tcd.setProjectName(project_name);
						tcd.setProjecttype(4);
						tcd.setTelecast_days(s1);
						daysDao.saveData(tcd);
					}
					}

				dao.customUpdateProject("update Project_info set project_name='" + project_name
						+ "', file_attach_link='" + file_attach_link + "', actual_hosted_site='" + actual_hosted_site
						+ "' , channel_name = '" + channel_name + "',ttime='" + ttime
						+ "'  where id = " + Integer.parseInt(id.trim()));
			} catch (Exception e) {
				logger.error("project update .....  ", e);
				// e.printStackTrace();
			}
			finally{
				dao=null;
				factory=null;
				session2=null;
			}
			// System.out.println("update value ....");
			logger.info(" value .updated...");
			return SUCCESS;
		}
	}

	public String closeProject() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session error closeProject method... " + logger.getClass());
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			Project_infoDao dao = (Project_infoDao) factory.getBean("d2");
			String[] ids = id.split(",");
//			List<Integer> ii = new ArrayList<>();
			//for (String pj : ids) {
				dao.customUpdateProject("update Project_info set closed = 1 where id ="+id+"");
			//}
			//dashboard();
			getdashboardDtls();
			logger.info("== project close....");
			ids=null;
			factory=null;
			dao=null;
			session2=null;
			return SUCCESS;
		}
	}

	public String updateProject() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("session error closeProject method... " + logger.getClass());
			return LOGIN;
		} else {
			factory = ActionPerform.getFactory();
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return null;
	}
	
	public String getProjectDtlsIdWise() {
		session2 = ServletActionContext.getRequest().getSession();
		System.out.println("i value is ---************************************************------->");
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("session error dashboard method... " + logger.getClass());
			return LOGIN;
		} else {
			ProjectDashboardBean bean = null;
			Tv_content_tdays tv=null;
			
			Project_infoDao dao = null;
			try {
				factory = ActionPerform.getFactory();
				dao = (Project_infoDao) factory.getBean("d1");
				factory1 =ActionPerform.getFactory();
				
				lst = dao.getData("Select pi.id,pi.project_name,pi.created_on ,"
							// + "pi.start_date,pi.end_date,"
							+ "cm.client_name, mpt.name, pi.file_attach_link, pi.actual_hosted_site , pi.channel_name, pi.ttime,"
							+ "pi.language,pi.realeasingDate,pi.property_category,pi.current_value,pi.archive_value,"
							+ "pi.project_type,pi.client_type "
							+ " from Project_info pi, Client_master cm, Markscan_projecttype mpt "
							+ " where pi.id="+id+" and cm.id = pi.client_type and mpt.id = pi.project_type and  pi.closed = ?", 0);
				
				logger.info("........2222222222222222222........" + lst.size());
				
				String d[]=null;

				for (int i = 0; i < lst.size(); i++) {
					bean = new ProjectDashboardBean();
					obj = (Object[]) lst.get(i);
					
				    projecttype = ((int) obj[14]);
					client_type = ((String) obj[15]);
					project_name = ((String) obj[1]);
					channel_name = ((String) obj[7]);
					file_attach_link = ((String) obj[5]);
					actual_hosted_site = ((String) obj[6]);
					language = ((String) obj[9]);
					realeasingDate = ((String) obj[10]);
					property_category = ((String) obj[11]);
					current_value = ((String) obj[12]);
					archive_value =  ((String) obj[13]);
					ttime = ((String) obj[8]);
					
					System.out.println("realising time ----------1111111-------------->"+((String) obj[8]));
					
					
					
					daysDao = (Tv_content_tdaysDao) factory.getBean("d37");
					String sql ="select telecast_days from Tv_content_tdays where projectName='"+project_name+"'";
					System.out.println("Days ------------------->"+sql);
					days =daysDao.viewRecord(sql);
					String d1[] =new String[days.size()];
					
					for(int j=0; j<days.size();j++)
					{
						d1[j] =(String)days.get(j);
					}
					
					pdaysDao = (Project_content_tdaysDao) factory.getBean("d39");
					days1 =pdaysDao.viewRecord("select archive_days from Project_content_tdays where projectName='"+project_name+"'");
					String d2[] =new String[days1.size()];
					
					for(int j=0; j<days1.size();j++)
					{
						d2[j] =(String)days1.get(j);
					}
					
					HttpServletRequest request = ServletActionContext.getRequest();
					request.setAttribute("d1", d1);
					request.setAttribute("d2", d2);
					obj = null;
					bean = null;
					date = null;
					// break;
					days=null;
				}
				
				dao1 = (Markscan_projecttypeDao) factory.getBean("d8");
				lst = dao1.viewRecord("select id, name from Markscan_projecttype");
				project_type_Data = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					Markscan_projecttype url2 = new Markscan_projecttype();
					Object[] obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					project_type_Data.add(url2);
					//p_type = 0;
				}
				
				cmdao = (Client_masterDao) factory.getBean("d10");
				lst = cmdao.viewRecord("select id, client_name from Client_master where project_type =" + projecttype + "");
				client_type_Data = new ArrayList<Client_master>();
				for (int k = 0; k < lst.size(); k++) {
					obj = (Object[]) lst.get(k);
					Client_master Mbn = new Client_master();
					Mbn.setId((Integer) obj[0]);
					Mbn.setClient_name((String) obj[1]);
					client_type_Data.add(Mbn);
					obj = null;
				}
				System.out.println("client_type_Data size is ------------------>"+client_type_Data.size());
				date = null;
				

			} catch (Exception e) {
				 e.printStackTrace();
				date = null;
				logger.error("project dash board error ---", e);
				// System.err.println("project dash board error ---" + e);
				return ERROR;
			} finally {
				obj = null;
				bean = null;
				date = null;
				lst = null;
				dao = null;
				factory = null;
				session2 = null;
				cmdao = null;

			}
			System.out.println("11111111111111111111111111111111111111111111111");
			return SUCCESS;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	List<ProjectDashboardBean> infos = null;

	public List<ProjectDashboardBean> getInfos() {
		return infos;
	}

	public void setInfos(List<ProjectDashboardBean> infos) {
		this.infos = infos;
	}

	private String id, name, client_name, project_name, start_date, end_date, file_attach_link,ttime,client_type;
	private String actual_hosted_site, created_on, channel_name,language,realeasingDate,property_category,current_value,archive_value,client_nm;
	
	private int projecttype;
	
	
	
	
	public String getClient_nm() {
		return client_nm;
	}

	public void setClient_nm(String client_nm) {
		this.client_nm = client_nm;
	}

	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getFile_attach_link() {
		return file_attach_link;
	}

	public void setFile_attach_link(String file_attach_link) {
		this.file_attach_link = file_attach_link;
	}

	public String getActual_hosted_site() {
		return actual_hosted_site;
	}

	public void setActual_hosted_site(String actual_hosted_site) {
		this.actual_hosted_site = actual_hosted_site;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public int getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRealeasingDate() {
		return realeasingDate;
	}

	public void setRealeasingDate(String realeasingDate) {
		this.realeasingDate = realeasingDate;
	}

	public String getProperty_category() {
		return property_category;
	}

	public void setProperty_category(String property_category) {
		this.property_category = property_category;
	}

	public String getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(String current_value) {
		this.current_value = current_value;
	}

	public String getArchive_value() {
		return archive_value;
	}

	public void setArchive_value(String archive_value) {
		this.archive_value = archive_value;
	}
	
	
	
	
	

}
