package com.markscan.project.classes.blacklisttool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.glassfish.jersey.client.ClientConfig;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.BlacklistTool_Data;
import com.markscan.project.beans.CommanReporting;
import com.markscan.project.beans.Markscan_machine;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.classes.ActionPerform;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.BlacklistTool_DataDao;
import com.markscan.project.dao.Markscan_machineDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Txn_tblDao;
import com.opensymphony.xwork2.ActionSupport;

public class BlackListToolQueryData extends ActionSupport {
	private static final Logger logger = Logger.getLogger(BlackListToolQueryData.class);

	public BlackListToolQueryData() {
		// TODO Auto-generated constructor stub
	}

	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	private List<Markscan_projecttype> listData = null;
	private List<Markscan_machine> listData_one = null;

	public String execute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			System.out.println("else running........");
			factory = LoginAndSession.getFactory();
			try {
				dao = (Markscan_projecttypeDao) factory.getBean("d8");
				lst = dao.viewRecord("select id, name from Markscan_projecttype");
				logger.info(".......pradeep........" + lst.size());
				listData = Collections.synchronizedList(new ArrayList<Markscan_projecttype>());

				for (int i = 0; i < lst.size(); i++) {
					Markscan_projecttype url2 = new Markscan_projecttype();
					Object[] obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					listData.add(url2);
				}
				lst = null;
				dao_one = (Markscan_machineDao) factory.getBean("d6");
				lst = dao.viewRecord("select id, name,ip_address,port,bot_version,bot_status from Markscan_machine");
				listData_one = Collections.synchronizedList(new ArrayList<Markscan_machine>());
				for (int i = 0; i < lst.size(); i++) {
					Markscan_machine url2 = new Markscan_machine();
					Object[] obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setName((String) obj[1]);
					url2.setIp_address((String) obj[2]);
					url2.setPort((String) obj[3]);
					url2.setBot_version((String) obj[4]);
					url2.setBot_status((Integer) obj[5]);
					listData_one.add(url2);
				}

				System.out.println("-----1-------------" + listData.size());
				System.out.println("-----2-------------" + listData_one.size());

				dao_one = null;
				lst = null;
				dao = null;
				return SUCCESS;
			} catch (Exception e) {
				logger.error("project type data get ", e);
				e.printStackTrace();
				lst = null;
				return ERROR;
			}

		}
	}

	ClientConfig config = null;
	Client client = null;
	WebTarget target = null;
	JSONObject jObj = null;
	File file = null;
	FileOutputStream fos = null;
	String app = null;
	String fpath = null;
	String ctime = null;
	List<BlacklistTool_Data> blkdata4show = null;

	public String start() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			try {
				factory = ActionPerform.getFactory();
				blkDao = (BlacklistTool_DataDao) factory.getBean("d28");
				config = new ClientConfig();
				client = ClientBuilder.newClient(config);
				target = client.target(getBaseURI());
				System.out.println(target);

				/**
				 * json creation
				 */

				jObj = new JSONObject();
				jObj.put("link", propertyUrl);
				jObj.put("pid", new Integer(propertyid));
				jObj.put("domain", "desirulez.me");
				jObj.put("show", propertyName);

				try {
					fpath = FileLocation();
					ctime = currentTime() + ".json";
					file = new File(fpath + ctime);
					System.out.println(file.getName() + "...full file path...." + file.getPath());
					// file.write(obj.toJSONString());
					fos = new FileOutputStream(file);
					fos.write(jObj.toJSONString().getBytes());

					// file.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}

				/**
				 * return data from client....
				 */

				System.out.println(".............. " + ctime);
				app = target.path("rest").path("json").path(ctime).request().accept(MediaType.TEXT_PLAIN)
						.get(String.class);
				// System.out.println(app);
				String links[] = app.split("]");

				blkdata4show = new ArrayList<>();
				int id = 0;
				for (String aa : links) {
					bltd = new BlacklistTool_Data();
					if (aa.equals("null")) {
						System.out.println("null mila h if block m ");
					} else
						System.out.println("else block se........." + aa);

					String data[] = aa.split("<pjdj>");
					try {
						bltd.setProjectid(Integer.parseInt(data[0].trim()));
						bltd.setDomain(getDoaminAddress(propertyUrl.trim()));
						bltd.setInfringing_link(data[2].trim());
						bltd.setInfringing_link_by_date(propertyUrl.trim());
						bltd.setSearch_keyword(propertyName.trim());
						bltd.setCreated_on(getCurrentTime());
						bltd.setUserid((int) session2.getAttribute("uid"));
						if (data.length > 3) {
							bltd.setSource_link(data[3].trim());
						}
						id = Integer.parseInt(blkDao.saveData(bltd));
						// System.out.println(blkDao.saveData(bltd));

						// bltd.setId(id);
						blkdata4show.add(bltd);
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			} catch (ProcessingException pe) {
				errorMessage = "forgot to select machine...";
				return "errorMsg";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				file.delete();
				file = null;
				blkDao = null;
				app = null;
				fpath = null;
				ctime = null;
				fos = null;
				config = null;
				client = null;
				target = null;
				return ERROR;
			} finally {
				System.out.println("finally....." + file);
				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}

				file = null;
				blkDao = null;
				app = null;
				fpath = null;
				ctime = null;
				fos = null;
				config = null;
				client = null;
				target = null;
			}
		}

		return SUCCESS;
	}

	private String getDoaminAddress(String source) {
		String urlE = null;
		; // urlE is a domain address
		urlE = source.replace("https://", "");
		urlE = urlE.replace("http://", "");
		urlE = urlE.replace("//www.", "");
		urlE = urlE.replace("www.", "");
		int xp = urlE.indexOf("/");
		try {
			urlE = urlE.substring(0, xp); // urlE is a
											// domain
											// address
		} catch (Exception e) {
			System.err.println("==domain error==");
		}
		return urlE;
	}

	private URI getBaseURI() {
		// here server is running on 4444 port number and project name is
		// restfuljersey
		return UriBuilder.fromUri("http://" + machine_detail + ":8083/ws").build();
	}

	private String currentTime() {
		return String.valueOf((new Date().getTime() / 1000));
		// System.out.println("Integer : " + i);
		// System.out.println("Long : "+ new Date().getTime());
	}

	private String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	private String FileLocation() {
		prop = new Properties();
		try {
			configFile = "/details.properties";
			input = getClass().getResourceAsStream(configFile);
			prop.load(input);
			directory = prop.getProperty("blacklistToolPath");
			System.out.println("..temp data..." + directory);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return directory;
	}

	public List<Markscan_projecttype> getListData() {
		return listData;
	}

	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

	public List<Markscan_machine> getListData_one() {
		return listData_one;
	}

	public void setListData_one(List<Markscan_machine> listData_one) {
		this.listData_one = listData_one;
	}

	public String getMachine_detail() {
		return machine_detail;
	}

	public void setMachine_detail(String machine_detail) {
		this.machine_detail = machine_detail;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyUrl() {
		return propertyUrl;
	}

	public void setPropertyUrl(String propertyUrl) {
		this.propertyUrl = propertyUrl;
	}

	public int getPropertyid() {
		return propertyid;
	}

	public void setPropertyid(int propertyid) {
		this.propertyid = propertyid;
	}

	public List<BlacklistTool_Data> getBlkdata4show() {
		return blkdata4show;
	}

	public void setBlkdata4show(List<BlacklistTool_Data> blkdata4show) {
		this.blkdata4show = blkdata4show;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	Markscan_machineDao dao_one = null;
	Markscan_projecttypeDao dao = null;

	String machine_detail, configFile, directory;
	String propertyName, propertyUrl;
	int propertyid;
	InputStream input = null;
	Properties prop = null;
	BlacklistTool_Data bltd = null;
	BlacklistTool_DataDao blkDao = null;
	String errorMessage = null;

	public String blackListDataExport() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			try {
				factory = ActionPerform.getFactory();
				blkDao = (BlacklistTool_DataDao) factory.getBean("d28");
				lst = blkDao
						.viewRecord01("select btd.id, btd.projectid, btd.infringing_link_by_date, btd.infringing_link,"
								+ " btd.source_link from BlacklistTool_Data btd where btd.userid=" + usertype
								+ " and btd.created_on " + "BETWEEN '" + startdate + "' and ADDDATE('" + startdate
								+ "' ,1)", 200000);

				blkdata4show = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					BlacklistTool_Data url2 = new BlacklistTool_Data();
					Object[] obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setProjectid((Integer) obj[1]);
					url2.setInfringing_link_by_date((String) obj[2]);
					url2.setInfringing_link((String) obj[3]);
					url2.setSource_link((String) obj[4]);
					blkdata4show.add(url2);
				}
				downloadCSVFileAction();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return null;
	}

	public void downloadCSVFileAction() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();

		try {
			response.setContentType("text/csv");
			// response.setCharacterEncoding("UTF-8");
			String reportName = "blacklistdata_" + (int) session2.getAttribute("uid") + "_" + currentTime()
					+ "_file.csv";
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);

			ArrayList<String> rows = new ArrayList<String>();
			rows.add("ID,Project ID,Infringing Link, Infringing Link 2,Source Link");
			rows.add("\n");
			for (BlacklistTool_Data cr : blkdata4show) {
				rows.add("\"" + cr.getId() + "\",\"" + cr.getProjectid() + "\",\"" + cr.getInfringing_link_by_date()
						+ "\",\"" + cr.getInfringing_link() + "\",\"" + cr.getSource_link() + "\"");
				rows.add("\n");
			}

			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getWriter().print(outputString);
				// response.getOutputStream().print(outputString);
				/**
				 * using getWriter() for prevent below error
				 * java.io.CharConversionException: Not an ISO 8859-1 character:
				 * ? outputstream do not convert some special characters
				 */
			}

			// response.getOutputStream().flush();
			response.getWriter().flush();
			lst = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	int usertype;
	String startdate = null;

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

}
