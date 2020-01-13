package com.markscan.project.classes;

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




public class Reporting extends ActionSupport {

	private static final Logger logger = Logger.getLogger(Reporting.class);
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	HttpSession session2 = null;
	Object[] obj = null;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String execute() { // templete configuration.....

		session2 = ServletActionContext.getRequest().getSession();
		logger.info("-- session----------" + session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println("else=====session reporting===='");
			factory = LoginAndSession.getFactory();
			logger.info("---- factory---" + factory);
			try {
				Markscan_projecttype url2 = null;
				Markscan_projecttypeDao dao = (Markscan_projecttypeDao) factory.getBean("d8");

				lst = dao.viewRecord("select id, name from Markscan_projecttype order by name");
				// System.out.println(".......pradeep........" + lst.size());
				logger.info(".......pradeep........" + lst.size());
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
				factory = LoginAndSession.getFactory();
				Markscan_usersDao dao = (Markscan_usersDao) factory.getBean("d9");
				lst = dao.viewRecord("select id, name from Markscan_users where status= 1 order by name");
				usrData = new ArrayList<>();
				Markscan_users url2 = null;
				for (int i = 0; i < lst.size(); i++) {
					url2 = new Markscan_users();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					usrData.add(url2);
					obj = null;
					url2 = null;
				}
				url2 = null;
				lst = null;
				dao = null;
				
				
			} catch (Exception e) {
				logger.error("get user error ", e);
				return ERROR;
			} finally {
				url2 = null;
				lst = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	public String getPropertyName_name() {
		return propertyName_name;
	}

	public void setPropertyName_name(String propertyName_name) {
		this.propertyName_name = propertyName_name;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
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

	public String createProject() {
		session2 = ServletActionContext.getRequest().getSession();
		
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println("else=====session reporting===='");
			factory = LoginAndSession.getFactory();
			Project_infoDao dao = null;
			Tv_content_tdaysDao tdao=null;
			Project_info pi = null;
			Tv_content_tdays tcd =null;
			Project_content_tdaysDao pdao = null;
			Domain_masterDao dmo= null;
			ArrayList<Integer> al = new ArrayList<Integer>();
			Imdb_content_detailDao imdbdao = null;
			ArrayList<Integer> al1 = new ArrayList<Integer>();
			
			System.out.println("**********************Language******************************"+language);
			try {
				dao = (Project_infoDao) factory.getBean("d1");
				pi = new Project_info();
				tdao=(Tv_content_tdaysDao)factory.getBean("d37");
				dmo = (Domain_masterDao) factory.getBean("d38");
				pdao = (Project_content_tdaysDao) factory.getBean("d39");
				
				imdbdao = (Imdb_content_detailDao) factory.getBean("d40");
				
				if (propertyName_name.trim().length() < 1) {
					msg = "Property Name cannot be blank.";
				}else if(language.trim().length()<1)
				{
					msg ="Langauage cannot be blank.";
				}else if(projecttype ==0){
					msg ="Please select Project Type.";
				}else if(clientname == 0){
					msg ="Please select Client Type.";
				}else if((projecttype ==4 || projecttype == 5) && !language.equals("English") && channel_name.trim().length() <1){ // for TV show & Sports
					msg ="Please enter channel name.";
				}else if(file_attach_link.trim().length() <1 ){
					msg ="Please enter link of LOA.";
				}else if(realeasingDate.trim().length() <1){
					msg ="Please enter releasing date.";
				}else if((projecttype !=4 && projecttype != 5) && property_category.equals("0")){
					msg ="Please select proper property category.";
				}else if(property_category.equals("Current") && current_value.trim().length() <1){
					msg ="Please enter current value.";
				}else if(property_category.equals("Archive") && ServletActionContext.getRequest().getParameterValues("archives")== null){
					  msg ="Please enter archives value.";
				}else if((projecttype ==4 || projecttype == 5) && !language.equals("English") && telecastTime.trim().length() <1 ){
					  msg ="Please enter telecast time.";
				}else if((projecttype ==4 || projecttype == 5) && !language.equals("English") && ServletActionContext.getRequest().getParameterValues("days")== null){
					  msg ="Please enter telecast days.";
				}else {
					if(actual_hosted_site.trim().length() > 1){
						lst = dmo.viewRecord("select dm.id,dm.domain_nm from Domain_Mst dm  where dm.deleteflag='0'");
						System.out.println("----dList-----"+lst);
						if(lst.size() > 0){
							boolean flag=false;
							for(int i= 0; i < lst.size();i++){
								obj = (Object[]) lst.get(i);
								String domain_nm = obj[1].toString();
								if(actual_hosted_site.contains(domain_nm) || domain_nm.contains(actual_hosted_site)){ //|| obj[1].toString().contains(actual_hosted_site)
									flag=true;
								}
							}
							if(!flag){
								msg ="Hosted site is not registered.";
								HttpServletRequest request = ServletActionContext.getRequest();
								request.setAttribute("projecttype", projecttype);
								request.setAttribute("clientname", clientname);
								if(ServletActionContext.getRequest().getParameterValues("days")!= null && ServletActionContext.getRequest().getParameterValues("days").length > 0){
								   JSONArray mJSONArray = new JSONArray(Arrays.asList(ServletActionContext.getRequest().getParameterValues("days")));	
								   request.setAttribute("mJSONArray", mJSONArray);
								}
								
								if(ServletActionContext.getRequest().getParameterValues("archives")!= null && ServletActionContext.getRequest().getParameterValues("archives").length > 0){
									   JSONArray mJSONArray1 = new JSONArray(Arrays.asList(ServletActionContext.getRequest().getParameterValues("archives")));	
									   request.setAttribute("mJSONArray1", mJSONArray1);
								}
								
								execute();
								return SUCCESS;
							}
						}
					}
					
					
					lst = null;
					lst = dao.viewRecord("select pi.id,pi.project_name from Project_info pi where pi.client_type='"+clientname+"' and pi.project_name='"+propertyName_name.replaceAll("'", "''")+"'");
					if(lst.size() > 0){
						msg ="Same property name already exist for this client.";
						HttpServletRequest request = ServletActionContext.getRequest();
						request.setAttribute("projecttype", projecttype);
						request.setAttribute("clientname", clientname);
						if(ServletActionContext.getRequest().getParameterValues("days")!= null && ServletActionContext.getRequest().getParameterValues("days").length > 0){
						   JSONArray mJSONArray = new JSONArray(Arrays.asList(ServletActionContext.getRequest().getParameterValues("days")));	
						   request.setAttribute("mJSONArray", mJSONArray);
						}
						
						if(ServletActionContext.getRequest().getParameterValues("archives")!= null && ServletActionContext.getRequest().getParameterValues("archives").length > 0){
							   JSONArray mJSONArray1 = new JSONArray(Arrays.asList(ServletActionContext.getRequest().getParameterValues("archives")));	
							   request.setAttribute("mJSONArray1", mJSONArray1);
						}
						execute();
						return SUCCESS;
					}
					
					String propertyName_name_initial="";
					if(capsFlag.equalsIgnoreCase("1")){
						propertyName_name_initial = convert(propertyName_name);
					}else{
						propertyName_name_initial = propertyName_name;
					}
					
					String channel_name_initial="";
					if(channel_name.trim().length() > 1){
						if(capsFlag1.equalsIgnoreCase("1")){
							channel_name_initial = convert(channel_name);
						}else{
							channel_name_initial = channel_name;
						}
					}
					
					propertyName_name = propertyName_name_initial;
					channel_name = channel_name_initial;
					
					String days[] =ServletActionContext.getRequest().getParameterValues("days");
					String archives[] =ServletActionContext.getRequest().getParameterValues("archives");
					
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					
					pi.setProject_name(propertyName_name_initial);
					pi.setChannel_name(channel_name_initial);
					pi.setProject_complete(0);
					// pi.setComplete_on("0000-00-00 00:00:00");
					pi.setFile_attach_link(file_attach_link);
					pi.setActual_hosted_site(actual_hosted_site);
					pi.setProject_type(projecttype);
					//pi.setStart_date(startdate);
					//pi.setEnd_date(enddate);
					pi.setProject_state_machine_wise(0);
					pi.setClosed(0);
					pi.setClient_type(Integer.toString(clientname));
					pi.setCreated_by((int) session2.getAttribute("uid"));
					pi.setLanguage(language);
					pi.setRealeasingDate(realeasingDate);
					pi.setProperty_category(property_category);
					pi.setCurrent_value(current_value);
					pi.setLast_updated_on(sdf.format(timestamp));
					
					
					
					if((projecttype==4 || projecttype==5) && (days!=null))
					{
						
					pi.setTtime(telecastTime);
					}
					if((projecttype==4 || projecttype==5) && (days!=null))
					{
						for(String s1:days)
						{
							
							tcd=new Tv_content_tdays();
							//tcd.setProjectId();
							tcd.setProjectName(propertyName_name);
							tcd.setProjecttype(projecttype);
							tcd.setTelecast_days(s1);
							tdao.saveData(tcd);
						}
					
					}
					
					if((projecttype !=4 || projecttype !=5) && (archives!=null))
					{
						//System.out.println("S1 value ------------->"+archives); 
						
						for(String s1:archives)
						{   
							System.out.println("S1 value ------------->"+s1);
							Project_content_tdays pcd = new Project_content_tdays();
							pcd.setProjectName(propertyName_name);
							pcd.setProjecttype(projecttype);
							//pcd.setProjectId(pi.getId());
							pcd.setArchive_days(s1);
							pdao.saveData(pcd);
							al.add(pcd.getId()); 
							
						}
					
					}
					
					if((projecttype==4 || projecttype==5) && (language.equalsIgnoreCase("English")))
					{
						//HashMap<String, HashMap<String, HashMap<String, String>>>
						TheMovieDb mvDb = new TheMovieDb();
						HashMap<String, HashMap<String, HashMap<String, String>>> contentList = mvDb.getEnglishTvShowDtails(propertyName_name);
						if(!contentList.isEmpty()){
							
							for (String name : contentList.keySet())  
					        { 
					            // search  for value 
								HashMap<String, HashMap<String, String>> mp = contentList.get(name); 
								for(String nm : mp.keySet()){
									HashMap<String, String> mp1= mp.get(nm);
									Imdb_content_detail imdb = new Imdb_content_detail();
									imdb.setProjectName(propertyName_name);
									imdb.setSeason_name(mp1.get("seasonName"));
									imdb.setSeason_number(Integer.parseInt(mp1.get("SeasonNumber")));
									imdb.setEpisodeId(Integer.parseInt(mp1.get("episodeId")));
									imdb.setEpisodeNo(Integer.parseInt(mp1.get("episodeNo")));
									imdb.setEpisodeNm(mp1.get("episodeName"));
									imdb.setEpisode_realease_dt(mp1.get("episodeReleaseDate"));
									imdbdao.saveData(imdb);
									al1.add(imdb.getId());
									
								}
					            
					        } 
							
						}

						
					
					}
					
					
					dao.saveData(pi);
					int save_id = pi.getId();
					if(!al.isEmpty()){
						for(Integer i : al){
							String query1="Update Project_content_tdays set projectId="+save_id+" where id="+i+"";
							pdao.customUpdateProject(query1);
						 }
					}
					
					if(!al1.isEmpty()){
						for(Integer i : al1){
							String query1="Update Imdb_content_detail set projectId="+save_id+" where id="+i+"";
							imdbdao.customUpdateProject(query1);
						 }
					}
					
					if(save_id > 0){
						file_attach_link ="";
						propertyName_name ="";
						actual_hosted_site = "";
						language = "Select Language";
						realeasingDate = "";
						clientname = 0;
						property_category = "0";
						projecttype =0;
					}
					msg = "project created successfully...";
				}
			
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("projecttype", projecttype);
				request.setAttribute("clientname", clientname);
				request.setAttribute("property_category", property_category);
				if(ServletActionContext.getRequest().getParameterValues("days") !=null && ServletActionContext.getRequest().getParameterValues("days").length > 0){
					JSONArray mJSONArray = new JSONArray(Arrays.asList(ServletActionContext.getRequest().getParameterValues("days")));	
				    request.setAttribute("mJSONArray", mJSONArray);
				}
				if(ServletActionContext.getRequest().getParameterValues("archives")!= null && ServletActionContext.getRequest().getParameterValues("archives").length > 0){
					   JSONArray mJSONArray1 = new JSONArray(Arrays.asList(ServletActionContext.getRequest().getParameterValues("archives")));	
					   request.setAttribute("mJSONArray1", mJSONArray1);
				}
				
				
				execute();
				System.out.println("in side project create page end...............");	

			} catch (Exception e) {
				e.printStackTrace();
				msg = "project not created...";
				return ERROR;
			} finally {
				pi = null;
				dao = null;
				factory = null;
				session2 = null;
				cmdao = null;
			}
			return SUCCESS;
		}
	}

	BeanFactory factory = null;
	String aa = null;
	String query = null;
	CommanReporting url2 = null;

	public String getReport() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if========='");
			return LOGIN;
		} else {
			Crawle_url2Dao dao = null;
			try {

				factory = LoginAndSession.getFactory();
				dao = (Crawle_url2Dao) factory.getBean("dash");

				if (datatype == 1 || datatype == -1) {
					notification_status = 0;
				}

				query = "select curl.id, mptyp.name,cmstr.client_name,pinf.project_name,musr.name, "
						+ " curl.crawle_url2, curl.link_logger_srclink, curl.domain_name, curl.created_on, "
						+ " curl.google_dmca_new,  curl.note1, curl.note2,curl.link_logger, pinf.channel_name "
						+ " , curl.image_path "
						+ " from Crawle_url2 curl , Markscan_projecttype mptyp , Client_master cmstr , "
						+ " Project_info pinf, Markscan_users musr "
						+ " where curl.project_id = pinf.id and cmstr.id= pinf.client_type and "
						+ " mptyp.id = cmstr.project_type and musr.id=curl.user_id " + " and mptyp.id = " + projecttype
						+ "  and curl.is_valid= 0 and curl.w_list=0 ";

				if (datatype == 0) { // 0 == only infringing link
					if (notification_status == 2) {
						query = query.concat("  and curl.link_logger=  " + datatype + "  ");
					} else
						query = query.concat("  and curl.link_logger=  " + datatype + "   and curl.google_dmca_new = "
								+ notification_status + "  ");
				}
				if (datatype == 1) { // 1== only source link
					query = query.concat("  and curl.link_logger=  " + datatype + "    ");
				}
				if (clientname != 0) {
					query = query.concat("  and cmstr.id=  " + clientname + "  ");
				}
				if (propertyName != 0) {
					query = query.concat("  and pinf.id =   " + propertyName + "  ");
				}
				if (usertype != 0) {
					query = query.concat("  and musr.id =    " + usertype + "  ");
				}
				String qq7 = " and curl.created_on BETWEEN '" + startdate + "' and '" + enddate
						+ "'  order by curl.id asc";
				query = query + qq7;
				

				lst = dao.viewRecord(query, 200000);
				// System.out.println(".......pradeep........" + lst.size());
				logger.info(".......pradeep........" + lst.size());
				infos = new ArrayList<CommanReporting>();

				for (int i = 0; i < lst.size(); i++) {
					url2 = new CommanReporting();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setProjectName((String) obj[1]);
					url2.setClientName((String) obj[2]);
					url2.setPropertName((String) obj[3]);
					url2.setUserName((String) obj[4]);
					url2.setLink((String) obj[5]);
					url2.setLink001((String) obj[6]);
					url2.setDomainName((String) obj[7]);
					aa = (String) obj[8];
					// System.out.println();
					url2.setDate__c(aa.replace(".0", ""));
					url2.setGoogle_dmca_new((Integer) obj[9]);
					url2.setNote1((String) obj[10]);
					url2.setNote2((String) obj[11]);
					if ((Integer) obj[12] == 1) {
						url2.setLnk_typ("Source");
					} else if ((Integer) obj[12] == 0) {
						url2.setLnk_typ("Infringing");
					}
					// System.out.println("----
					// length----"+obj[13].toString().length());
					try {
						url2.setChannel_name(obj[13].toString());
					} catch (Exception e) {
						url2.setChannel_name("");
					}
					try {
						url2.setImage_path(obj[14].toString());
					} catch (Exception e) {
						url2.setImage_path("");
					}

					infos.add(url2);
					url2 = null;
					aa = null;
				}
				lst = null;
				aa = null;
				execute();
				downloadCSVFileAction();
				session2 = null;
				factory = null;
				dao = null;
				query = null;
				qq7 = null;
				// return null ; because of nothing add in csv
				// after data
			} catch (Exception e) {
				lst = null;
				aa = null;
				session2 = null;
				factory = null;
				query = null;
				// e.printStackTrace();
				logger.error("session / factory error   ", e);
				return ERROR;
			} finally {
				lst = null;
				aa = null;
				session2 = null;
				factory = null;
				dao = null;
				query = null;
			}
			return SUCCESS;

		}

	}

	public String nowTime() {
		String time__c = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
			Date date = new Date();
			// System.out.println(dateFormat.format(date));
			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);
			dateFormat = null;
			date = null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("date format error ...", e);
		}
		return time__c;
	}

	/*
	 * public void downloadCSVFileAction() throws Exception {
	 * 
	 * HttpServletResponse response = ServletActionContext.getResponse(); try {
	 * response.setContentType("application/vnd.ms-excel");
	 * response.setCharacterEncoding("UTF-8"); String reportName = "data_" +
	 * clientname + "_" + nowTime() + "_file.xls";
	 * response.setHeader("Content-disposition", "attachment;filename=" +
	 * reportName);
	 * 
	 * XSSFWorkbook wb = new XSSFWorkbook(); XSSFSheet sheet = wb.createSheet(
	 * "Excel Sheet"); XSSFRow rowhead = sheet.createRow((int) 0);
	 * rowhead.createCell((short) 0).setCellValue("ID");
	 * rowhead.createCell((short) 1).setCellValue("Project Name");
	 * rowhead.createCell((short) 2).setCellValue("Client Name");
	 * rowhead.createCell((short) 3).setCellValue("Property Name");
	 * rowhead.createCell((short) 4).setCellValue("User Name");
	 * 
	 * rowhead.createCell((short) 5).setCellValue("Link Type");
	 * rowhead.createCell((short) 6).setCellValue("URL");
	 * rowhead.createCell((short) 7).setCellValue("Infringing Link");
	 * rowhead.createCell((short) 8).setCellValue("Domain Name");
	 * rowhead.createCell((short) 9).setCellValue("Date");
	 * 
	 * rowhead.createCell((short) 10).setCellValue("Google Notify");
	 * rowhead.createCell((short) 11).setCellValue("Note1");
	 * rowhead.createCell((short) 12).setCellValue("Note2");
	 * rowhead.createCell((short) 13).setCellValue("Channel Name");
	 * 
	 * int index = 1; for (CommanReporting cr : infos) {
	 * 
	 * 
	 * 
	 * XSSFRow row = sheet.createRow((int) index); row.createCell((short)
	 * 0).setCellValue(cr.getId()); row.createCell((short)
	 * 1).setCellValue(cr.getProjectName()); row.createCell((short)
	 * 2).setCellValue(cr.getClientName()); row.createCell((short)
	 * 3).setCellValue(cr.getPropertName()); row.createCell((short)
	 * 4).setCellValue(cr.getUserName()); row.createCell((short)
	 * 5).setCellValue(cr.getLnk_typ()); row.createCell((short)
	 * 6).setCellValue(cr.getLink()); row.createCell((short)
	 * 7).setCellValue(cr.getLink001()); row.createCell((short)
	 * 8).setCellValue(cr.getDomainName()); row.createCell((short)
	 * 9).setCellValue(cr.getDate__c()); row.createCell((short)
	 * 10).setCellValue(cr.getGoogle_dmca_new()); row.createCell((short)
	 * 11).setCellValue(cr.getNote1()); row.createCell((short)
	 * 12).setCellValue(cr.getNote2());
	 * 
	 * row.createCell((short) 13).setCellValue(cr.getChannel_name());
	 * 
	 * index++; }
	 * 
	 * ServletOutputStream out = response.getOutputStream();
	 * 
	 * wb.write(out); out.flush(); out.close();
	 * 
	 * // response.getOutputStream().flush(); // response.getWriter().flush();
	 * infos = null; } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	String reportName = null;
	ArrayList<String> rows = null;
	Iterator<String> iter = null;
	String outputString = null;

	public void downloadCSVFileAction() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();

		try {

			response.setContentType("text/csv");
			response.setCharacterEncoding("UTF-8");
			reportName = "data_" + clientname + "_" + nowTime() + "_file.csv";
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);

			rows = new ArrayList<String>();
			rows.add("id,Project Name,Client Name, Property Name,User Name,Link Type,URL, Infringing Link, Domain Name,"
					+ " Date, Google Notify, Note1,Note2, Channel Name, File Path");
			rows.add("\n");
			for (CommanReporting cr : infos) {
				rows.add("\"" + cr.getId() + "\",\"" + cr.getProjectName() + "\",\"" + cr.getClientName() + "\",\""
						+ cr.getPropertName() + "\",\"" + cr.getUserName() + "\",\"" + cr.getLnk_typ() + "\",\""
						+ cr.getLink() + "\",\"" + cr.getLink001() + "\",\"" + cr.getDomainName() + "\",\""
						+ cr.getDate__c() + "\",\"" + cr.getGoogle_dmca_new() + "\",\"" + cr.getNote1() + "\",\""
						+ cr.getNote2() + "\",\"" + cr.getChannel_name() + "\",\"" + cr.getImage_path() + "\"");
				rows.add("\n");
			}

			iter = rows.iterator();
			while (iter.hasNext()) {
				outputString = (String) iter.next();
				byte[] bytes = outputString.getBytes("UTF-8");

				response.getWriter().print(outputString);
				// response.getOutputStream().print(outputString);
				/**
				 * using getWriter() for prevent below error
				 * java.io.CharConversionException: Not an ISO 8859-1 character:
				 * ? outputstream do not convert some special characters
				 */
				outputString = null;
			}

			// response.getOutputStream().flush();
			response.getWriter().flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			infos = null;
			lst = null;
			response = null;
			reportName = null;
			rows = null;
			iter = null;
			outputString = null;
		}
	}

	public String dataExtracted() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session errir");
			return LOGIN;
		} else {
			// System.out.println("else========='");

			aa = null;
			// System.out.println("hello delhi");
			// Resource r = new ClassPathResource("applicationContext.xml");
			// BeanFactory factory = new XmlBeanFactory(r);
			factory = LoginAndSession.getFactory();
			// BeanFactory factory =
			// HbmTmplateBeanFactory.beanfactoryInstance();
			Crawle_url2Dao dao = (Crawle_url2Dao) factory.getBean("dash");
			try {
				query = "";

				// System.out.println(query);
				lst = dao.viewRecord(query, 200000);
				// System.out.println(".......pradeep........" + lst.size());
				infos = new ArrayList<CommanReporting>();
				for (int i = 0; i < lst.size(); i++) {
					url2 = new CommanReporting();
					obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setProjectName((String) obj[1]);
					url2.setClientName((String) obj[2]);
					url2.setPropertName((String) obj[3]);
					url2.setUserName((String) obj[4]);
					url2.setLink((String) obj[5]);
					url2.setLink001((String) obj[6]);
					url2.setDomainName((String) obj[7]);
					aa = (String) obj[8];
					// System.out.println();
					url2.setDate__c(aa.replace(".0", ""));
					url2.setGoogle_dmca_new((Integer) obj[9]);
					infos.add(url2);
					url2 = null;
					obj = null;
				}
				aa = null;
				dao = null;
				lst = null;
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error("error ", e);
			} finally {
				url2 = null;
				lst = null;
				infos = null;
				query = null;
				session2 = null;
				factory = null;
				dao = null;
			}
			execute();
			System.gc();
			return SUCCESS;
		}
	}

	/**
	 * ========== source status=========
	 */
	List<Integer> crawle_id = null;
	List<Integer> crawle_id_notExist = null;

	public String getSourceStatus() {
		session2 = ServletActionContext.getRequest().getSession(false);

		if (session2 == null || session2.getAttribute("login") == null) {
			System.out.println("if========='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Crawle_url2Dao dao = (Crawle_url2Dao) factory.getBean("dash");
			Url_emailDao dao1 = (Url_emailDao) factory.getBean("d22");
			Crawle_url2 cu = null;
			try {
				lst = dao.viewRecord(
						"select cu.id,cu.crawle_url2  from Crawle_url2 cu where  cu.created_on BETWEEN '" + startdate
								+ "' and " + " adddate('" + startdate + "', 1)  and cu.link_logger=1 and cu.is_valid=0 "
								+ "and cu.w_list=0 " + " and cu.user_id = " + (int) session2.getAttribute("uid"));
				// System.out.println("---lst.size()----------" + lst.size());
				crawle_id = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					// CommanReporting url2 = new CommanReporting();
					obj = (Object[]) lst.get(i);
					crawle_id.add((Integer) obj[0]);
					obj = null;
				}
				lst = null;
				// System.out.println("---crawle_id.size()----------" +
				// crawle_id.size());
				crawle_id_notExist = new ArrayList<>();
				for (Integer pj : crawle_id) {
					lst = dao1.viewRecord("select id from Url_email ue where ue.send =1 and ue.crawle_url2_id =" + pj);
					if (lst.size() == 0) {
						crawle_id_notExist.add(pj);
					}
					lst = null;
				}
				crawle_id = null;
				lst = null;
				link_withDetail = new ArrayList<>();

				for (Integer pj : crawle_id_notExist) {
					lst = dao1.viewRecord(
							"select cu.id, cu.crawle_url2, cu.qc_new, cu.w_list_perform, cu.c_new  from Crawle_url2 cu "
									+ "where cu.id =" + pj);
					for (int i = 0; i < lst.size(); i++) {
						cu = new Crawle_url2();
						obj = (Object[]) lst.get(i);
						cu.setId((Integer) obj[0]);
						cu.setCrawle_url2((String) obj[1]);
						cu.setQc_new((String) obj[2]);
						cu.setW_list_perform((Integer) obj[3]);
						cu.setC_new((String) obj[4]);
						link_withDetail.add(cu);
						cu = null;
						obj = null;
					}
					cu = null;
				}
				System.out.println("~~~~~~~~~~link_withDetail.size.............." + link_withDetail.size());
				lst = null;
				cu = null;
				crawle_id_notExist = null;
				crawle_id = null;

			} catch (Exception e) {
				// TODO: handle exception
				lst = null;
				crawle_id_notExist = null;
				crawle_id = null;
				e.printStackTrace();
				return ERROR;
			} finally {
				lst = null;
				cu = null;
				crawle_id_notExist = null;
				crawle_id = null;
				dao = null;
				dao1 = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;

		}
	}

	/*
	 * == variable initilization...
	 */

	List<Crawle_url2> link_withDetail = null;
	List lst = null;
	List<CommanReporting> infos = null;
	private Session session = null;
	private Transaction tx = null;
	private List<Markscan_projecttype> listData = null;
	private List<Markscan_users> usrData = null;
	private List<CommanReporting> dashboardData = null;
	// private String projecttype = null;
	// private String usertype = null;
	// private String clientname = null;
	private String propertyName_name = null;
	private String channel_name = null;

	private String file_attach_link = null;
	private String actual_hosted_site = null;

	// private String datatype = null;

	private int projecttype;
	private int usertype;
	private int clientname;
	private int propertyName;
	private int datatype;
	private int notification_status;
	private String language=null;
	private String startdate = null;
	private String enddate = null;
	private String msg = null;
	private String realeasingDate=null;
	private String telecastTime=null;
	private String property_category=null;
	private String current_value=null;
	private String archive_value=null;
	private String id;
	
	

	/*
	 * == setter getter method
	 */
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	

	public String getTelecastTime() {
		return telecastTime;
	}

	public void setTelecastTime(String telecastTime) {
		this.telecastTime = telecastTime;
	}

	public String getRealeasingDate() {
		return realeasingDate;
	}

	public void setRealeasingDate(String realeasingDate) {
		this.realeasingDate = realeasingDate;
	}

	public List<Markscan_projecttype> getListData() {
		return listData;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Crawle_url2> getLink_withDetail() {
		return link_withDetail;
	}

	public void setLink_withDetail(List<Crawle_url2> link_withDetail) {
		this.link_withDetail = link_withDetail;
	}

	public List<CommanReporting> getInfos() {
		return infos;
	}

	public void setInfos(List<CommanReporting> infos) {
		this.infos = infos;
	}

	public List<CommanReporting> getDashboardData() {
		return dashboardData;
	}

	public void setDashboardData(List<CommanReporting> dashboardData) {
		this.dashboardData = dashboardData;
	}

	public int getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public int getClientname() {
		return clientname;
	}

	public void setClientname(int clientname) {
		this.clientname = clientname;
	}

	public int getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(int propertyName) {
		this.propertyName = propertyName;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getNotification_status() {
		return notification_status;
	}

	public void setNotification_status(int notification_status) {
		this.notification_status = notification_status;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

	public List<Markscan_users> getUsrData() {
		return usrData;
	}

	public void setUsrData(List<Markscan_users> usrData) {
		this.usrData = usrData;
	}

	public String zip_data_extractJSP() {
		session2 = ServletActionContext.getRequest().getSession();
		// logger.info("-- session 2==" + session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			downloadfile_name="na";
			prop = new Properties();
			configFile = "/details.properties";
			input = getClass().getResourceAsStream(configFile);
			try {
				prop.load(input);
				// URL_Server_PREFIX = "http://172.168.1.25/zip1/index.php";

				msg = prop.getProperty("ftpServer");
			} catch (Exception e) {
			} finally {
				configFile = null;
				input = null;
				prop = null;
				// serverIP = null;
				// serverPORT = null;
			}
			return SUCCESS;
		}
	}

	public String serverURLHeaderAuthPHPTest() {
		HttpHeaders headers = new HttpHeaders();
		
		prop = new Properties();
		configFile = "/details.properties";
		input = getClass().getResourceAsStream(configFile);
		try {
			prop.load(input);
			// URL_Server_PREFIX = "http://172.168.1.25/zip1/index.php";
			// databasePort = prop.getProperty("phpDBPort");

			// database = prop.getProperty("webinforcement_demo");
			// databaseIP = prop.getProperty("phpDBip");
			// databaseusr = prop.getProperty("phpDBusr");
			// databasepasswd = prop.getProperty("phpDBPasswd");
			ftpLocation = prop.getProperty("ftpServer");
			ftpAppPath = prop.getProperty("ftpAppPath");
			ftpPort = prop.getProperty("ftpAppPort");
			ftpimageLoc = prop.getProperty("ftpDataLocation");
			zipLocation = prop.getProperty("zipLocation");
			dataLocation = prop.getProperty("dataLocation");
			URL_Server_PREFIX = "http://" + ftpLocation + ":" + ftpPort + ftpAppPath;

		} catch (Exception e) {
		} finally {
			configFile = null;
			input = null;
			prop = null;
			// serverIP = null;
			// serverPORT = null;
		}
		logger.info("=== url server_prefix......" + URL_Server_PREFIX);
		logger.info("=== ftpLocation......" + ftpLocation);
		logger.info("=== ftpAppPath......" + ftpAppPath);
		logger.info("=== ftpPort......" + ftpPort);
		logger.info("=== zipLocation......" + zipLocation);
		logger.info("=== dataLocation......" + dataLocation);

		// ftpLocation = "172.168.1.25";
		// URL_Server_PREFIX = "http://172.168.1.25:8082/zip";

		try {

			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			// Request to return JSON format
			headers.setContentType(MediaType.APPLICATION_JSON);
			// headers.set("usr", databaseusr);
			// headers.set("passwd", databasepasswd);
			// headers.set("ip", databaseIP);
			// headers.set("db", database);
			headers.set("ddate", startdate);
			logger.info("==== ddate===== "+startdate);
			headers.set("ddomain", domain);
			logger.info("==== ddomain===== "+domain);
			headers.set("sorce_loc", dataLocation+ftpimageLoc);
			logger.info("==== sorce_loc===== "+dataLocation+ftpimageLoc);
			headers.set("zipLocation", dataLocation+zipLocation);
			logger.info("==== zipLocation===== "+dataLocation+zipLocation);
			headers.set("preImg_path", "http://"+ftpLocation+ftpimageLoc);
			logger.info("==== preImg_path===== "+"http://"+ftpLocation+ftpimageLoc);
//			headers.set("preImg_path", "http://"+"172.168.1.5"+ftpimageLoc);

			// HttpEntity<String>: To get result as String.
			  entity = new HttpEntity<String>(headers);

			// RestTemplate
			  restTemplate = new RestTemplate();

			// Send request with GET method, and Headers.
			  response = restTemplate.exchange(URL_Server_PREFIX, //
					HttpMethod.GET, entity, String.class);

			downloadfile_name = response.getBody();
			if (!downloadfile_name.equalsIgnoreCase("pj_error")) {
				downloadfile_name = "http://" + ftpLocation + zipLocation+ downloadfile_name;
			}

			logger.info(downloadfile_name);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception

			return "error";
		} finally {
			  entity =null;
			  restTemplate = null;
			  response = null;
		}

		// =========== test php page open ...................

		return SUCCESS;
	}
	
//----------------------------------------------------------------------------------------------	
	
	
	public String updateProject() {
		session2 = ServletActionContext.getRequest().getSession();
		
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			// System.out.println("else=====session reporting===='");
			factory = LoginAndSession.getFactory();
			Project_infoDao dao = null;
			Tv_content_tdaysDao tdao=null;
			Project_info pi = null;
			Tv_content_tdays tcd =null;
			Project_content_tdaysDao pdao = null;
			Domain_masterDao dmo= null;
			ArrayList<Integer> al = new ArrayList<Integer>();
			System.out.println("+++++++++++++++++++++++++++++++++ Update ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");	
			try {
				dao = (Project_infoDao) factory.getBean("d1");
				tdao=(Tv_content_tdaysDao)factory.getBean("d37");
				
				dmo = (Domain_masterDao) factory.getBean("d38");
				pdao = (Project_content_tdaysDao) factory.getBean("d39");
				
				if (propertyName_name !=null && propertyName_name.trim().length() < 1) {
					msg = "Property Name cannot be blank.";
				}else if(language.trim().length()<1)
				{
					msg ="Langauage cannot be blank.";
				}else if(projecttype ==0){
					msg ="Please select Project Type.";
				}else if(clientname == 0){
					msg ="Please select Client Type.";
				}else if((projecttype ==4 || projecttype == 5) && channel_name.trim().length() <1){ // for TV show & Sports
					msg ="Please enter channel name.";
				}else if(file_attach_link.trim().length() < 1 ){
					msg ="Please enter link of LOA.";
				}else if((projecttype !=4 && projecttype != 5) && property_category.equals("0")){
					msg ="Please select proper property category.";
				}else if(property_category!=null && property_category.equals("Current") && current_value.trim().length() <1){
					msg ="Please enter current value.";
				}else if(property_category!=null && property_category.equals("Archive") && ServletActionContext.getRequest().getParameterValues("archives")== null){
					msg ="Please enter archives value.";
				}else if((projecttype ==4 || projecttype == 5) && !language.equals("English") && telecastTime.trim().length() <1 ){
					  msg ="Please enter telecast time.";
				}else if((projecttype ==4 || projecttype == 5) && !language.equals("English") && ServletActionContext.getRequest().getParameterValues("days")== null){
					  msg ="Please enter telecast days.";
				}else {
					if(actual_hosted_site.trim().length() > 1){
						lst = dmo.viewRecord("select dm.id,dm.domain_nm from Domain_Mst dm  where dm.deleteflag='0'");
						System.out.println("----dList-----"+lst);
						if(lst.size() > 0){
							boolean flag=false;
							for(int i= 0; i < lst.size();i++){
								obj = (Object[]) lst.get(i);
								String domain_nm = obj[1].toString();
//								System.out.println("The domain site is ----------->"+domain_nm);
//								System.out.println("The hosted site is ----------->"+actual_hosted_site);
//								System.out.println("Contains sequence 'ing': " + actual_hosted_site.contains(domain_nm));
								
								if(actual_hosted_site.contains(domain_nm)){  //|| obj[1].toString().contains(actual_hosted_site)
									flag=true;
								}
							}
							if(!flag){
								msg ="Hosted site is not registered.";
								getProjectDtlsIdWise();
								return SUCCESS;
							}
						}
					}	
					
					String days[] =ServletActionContext.getRequest().getParameterValues("days");
					String archives[] =ServletActionContext.getRequest().getParameterValues("archives");
					
					String propertyName_name_initial="";
					if(capsFlag.equalsIgnoreCase("1")){
						propertyName_name_initial = convert(propertyName_name);
					}else{
						propertyName_name_initial = propertyName_name;
					}
					
					String channel_name_initial="";
					if(channel_name!=null && channel_name.trim().length() > 1){
						if(capsFlag1.equalsIgnoreCase("1")){
							channel_name_initial = convert(channel_name);
						}else{
							channel_name_initial = channel_name;
						}
					}
					
					String sql ="Update Project_info set project_name='"+propertyName_name_initial+"',channel_name='"+channel_name_initial+"',"
							+ "project_complete=0,file_attach_link='"+file_attach_link+"',actual_hosted_site='"+actual_hosted_site+"',"
							+ "project_type="+projecttype+",project_state_machine_wise=0,closed=0,client_type='"+clientname+"',"
							+ "language='"+language+"',realeasingDate='"+realeasingDate+"',property_category='"+property_category+"',"
							+ "current_value='"+current_value+"' ";
					
					if((projecttype==4 || projecttype==5) && (days!=null))
					{
						
					sql = sql + ",ttime='"+telecastTime+"' ";
					}
					
					
					sql = sql + " where id='"+id+"'";
					dao.customUpdateProject(sql);
					
					
					String query1="delete from Tv_content_tdays where projectId="+id+"";
					tdao.customUpdateProject(query1);
					
					String query2="delete from Project_content_tdays where projectId="+id+"";
					pdao.customUpdateProject(query2);
					System.out.println("delete Project_content_tdays sql +++++++++++++++++-->"+query2);
					
					
					if((projecttype==4 || projecttype==5) && (days!=null))
					{
						for(String s1:days)
						{
							
							tcd=new Tv_content_tdays();
							tcd.setProjectName(propertyName_name);
							tcd.setProjecttype(projecttype);
							tcd.setTelecast_days(s1);
							tdao.saveData(tcd);
						}
					
					}
					
					
					if((projecttype !=4 || projecttype !=5) && (archives!=null))
					{
						
						System.out.println("S1 value ------------->"+archives);
						for(String s1:archives)
						{   
							System.out.println("S1 value ------------->"+s1);
							Project_content_tdays pcd = new Project_content_tdays();
							pcd.setProjectName(propertyName_name);
							pcd.setProjecttype(projecttype);
							pcd.setArchive_days(s1);
							pdao.saveData(pcd);
							al.add(pcd.getId()); 
							
						}
					
					}
					if(!al.isEmpty()){
						for(Integer i : al){
							String query3="Update Project_content_tdays set projectId="+id+" where id="+i+"";
							pdao.customUpdateProject(query3);
						 }
					}
					msg = "project data update successfully...";
					
					
				}
			  getProjectDtlsIdWise();

			} catch (Exception e) {
				System.out.println("***********111111111111111111111****************days*****111111111111111***");	
				e.printStackTrace();
				msg = "project not created...";
				return ERROR;
			} finally {
				pi = null;
				dao = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
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
					
					//System.out.println("realising time ----------1111111-------------->"+((String) obj[8]));
					
					
					
					daysDao = (Tv_content_tdaysDao) factory.getBean("d37");
					//String sql ="select telecast_days from Tv_content_tdays where projectName='"+project_name.replaceAll("'", "''")+"'";
					String sql ="select telecast_days from Tv_content_tdays where projectId="+id+"";
					//System.out.println("Days ------------------->"+sql);
					days =daysDao.viewRecord(sql);
					String d1[] =new String[days.size()];
					
					for(int j=0; j<days.size();j++)
					{
						d1[j] =(String)days.get(j);
					}
					//System.out.println("Days ------------------->"+d1.length);
					
					
					pdaysDao = (Project_content_tdaysDao) factory.getBean("d39");
					//days1 =pdaysDao.viewRecord("select archive_days from Project_content_tdays where projectName='"+project_name.replaceAll("'", "''")+"'");
					days1 =pdaysDao.viewRecord("select archive_days from Project_content_tdays where projectId="+id+"");
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
				lst = dao1.viewRecord("select id, name from Markscan_projecttype order by name");
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
	
	
	
	
	
	
	
	
	
	
	
	
	
//------------------------------------------------------------------------	
	HttpEntity<String> entity =null;
	RestTemplate restTemplate = null;
	ResponseEntity<String> response = null;
    String dataLocation=null;
	String ftpimageLoc = null;
	String downloadfile_name = null;
	String database = null;
	String databaseIP = null;
	String databasePort = null;
	String databaseusr = null;
	String databasepasswd = null;
	String ftpLocation = null;
	String zipLocation = null;
	String URL_Server_PREFIX = "http://172.168.1.3:8082/check";
	InputStream input = null;
	Properties prop = null;
	String configFile = null;
	String datepicker1 = null;
	String datepicker2 = null;
	String datepicker = null;
	String client_type=null;
	String project_name=null;
	String ttime = null;
	private String capsFlag =null;
	private String capsFlag1 =null;
	Tv_content_tdaysDao daysDao =null;
	List days =null;
	List archives =null;
	String date = null;
	BeanFactory factory1 = null;
	
	Markscan_projecttypeDao dao1 = null;
	private List<Markscan_projecttype> project_type_Data = null;
	Client_masterDao cmdao = null;
	private List<Client_master> client_type_Data = null;
	
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

	public void setClient_type_Data(List<Client_master> client_type_Data) {
		this.client_type_Data = client_type_Data;
	}
	
	
	
	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	//String startdate=null;
	public String getDatepicker2() {
		return datepicker2;
	}

	public void setDatepicker2(String datepicker2) {
		this.datepicker2 = datepicker2;
	}

	String ftpPort = "8082";
	String ftpAppPath = "/zip";

	public String getDownloadfile_name() {
		return downloadfile_name;
	}

	public void setDownloadfile_name(String downloadfile_name) {
		this.downloadfile_name = downloadfile_name;
	}

	public String getDatepicker() {
		return datepicker;
	}

	public void setDatepicker(String datepicker) {
		this.datepicker = datepicker;
	}

	public String getDatepicker1() {
		return datepicker1;
	}

	public void setDatepicker1(String datepicker1) {
		this.datepicker1 = datepicker1;
	}

	String domain = null;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCapsFlag() {
		return capsFlag;
	}

	public void setCapsFlag(String capsFlag) {
		this.capsFlag = capsFlag;
	}

	public String getCapsFlag1() {
		return capsFlag1;
	}

	public void setCapsFlag1(String capsFlag1) {
		this.capsFlag1 = capsFlag1;
	}
	
	public String convert(String str) 
    { 
  
        // Create a char array of given String 
        char ch[] = str.toCharArray(); 
        for (int i = 0; i < str.length(); i++) { 
  
            // If first character of a word is found 
            if (i == 0 && ch[i] != ' ' ||  
                ch[i] != ' ' && ch[i - 1] == ' ') { 
  
                // If it is in lower-case 
                if (ch[i] >= 'a' && ch[i] <= 'z') { 
  
                    // Convert into Upper-case 
                    ch[i] = (char)(ch[i] - 'a' + 'A'); 
                } 
            } 
  
            // If apart from first character 
            // Any one is in Upper-case 
            else if (ch[i] >= 'A' && ch[i] <= 'Z')  
  
                // Convert into Lower-Case 
                ch[i] = (char)(ch[i] + 'a' - 'A');             
        } 
  
        // Convert the char array to equivalent String 
        String st = new String(ch); 
        return st; 
    } 

}
