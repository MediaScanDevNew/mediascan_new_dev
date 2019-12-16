/**
 * 
 */
package com.markscan.project.classes.saveupdate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Client_master;
import com.markscan.project.beans.CommanReporting;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Module_wise_email_template;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Module_wise_email_templateDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author pradeep
 *
 */
public class Client_type extends ActionSupport {

	/**
	 * 23 jan 2017
	 */
	public Client_type() {
		// TODO Auto-generated constructor stub
	}

	private Client_master c_master = new Client_master();
	HttpSession session2 = null;
	private BeanFactory factory = null;
	List lst = null;
	private Client_master cm = new Client_master();
	private static final Logger logger = Logger.getLogger(Client_type.class);

	public String execute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {

			factory = LoginAndSession.getFactory();
			try {
				dao1 = (Markscan_projecttypeDao) factory.getBean("d8");
				dao2 = (Module_wise_email_templateDao) factory.getBean("d14");
				dao3 = (Client_masterDao) factory.getBean("d10");
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
				lst = null;
				lst = dao2.viewRecord("select id, module from Module_wise_email_template where id>20 order by module");
				module_Data = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					Module_wise_email_template url2 = new Module_wise_email_template();
					Object[] obj = (Object[]) lst.get(i);
					url2.setId((Integer) obj[0]);
					url2.setModule((String) obj[1]);
					module_Data.add(url2);
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			} finally {
				factory=null;
				session2=null;
				lst = null;
				dao1 = null;
				dao2 = null;
				dao3 = null;
			}return SUCCESS;
		}
	}

	public String saveOrUpdateUser() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			Client_master cm = null;
			factory = LoginAndSession.getFactory();
			dao3 = (Client_masterDao) factory.getBean("d10");String query =null;
			System.out.println("--------- id========" + client_email);
			if (id == 0) {
				try {
					cm = new Client_master();
					String mail_dmain = null;
					if(client_email !=null && !client_email.isEmpty()){
						String[] mail_domain_arr = client_email.split("@");
						mail_dmain = mail_domain_arr[1];
					}
					if (client_name!=null && client_name.trim().length() < 1) {
						msg = "Error... Client name never be empty......";
					} else if (e_module == 0) {
						msg = "Error... Select Email module...";
					} else if (p_type == 0) {
						msg = "Error... Select Project Type...";
					}else if(client_address !=null && client_address.trim().length() < 1){
						msg = "Error... Client address never be empty...";
					}else if(client_email !=null && client_email.trim().length() <1){
						msg = "Error... Client email never be empty...";
					}
					else if(!isValid(client_email)) {
						msg = "Error... error on email format.......";
					}else if(!mail_dmain.equalsIgnoreCase("markscan.co.in")){
						msg = "Error... mail domain not matched....";
					}else {
						// ------------ new validation added before insert ------------------
						
						lst = dao3.viewRecord("select cm.id, cm.client_name, cm.client_address, cm.client_subject, "
								+ " mpt.name, mwet.module, cm.created_time  from Client_master cm, Markscan_projecttype mpt, "
								+ " Module_wise_email_template mwet where mpt.id="+p_type+" and cm.client_name='"+client_name+"' and "
										+ " mpt.id=cm.project_type and "
								+ " mwet.id=cm.email_module order by cm.id desc");
						
						//-------------------------------------------------------------------
						if(lst.size() > 0){
							msg = "Error... This client already mapped with this Project Type ...";
							lst = null;
						}else{
							
							String client_name_initial="";
							if(capsFlag.equalsIgnoreCase("1")){
								client_name_initial = convert(client_name);
							}else{
								client_name_initial = client_name;
							}
							System.out.println("client name is ------------------>"+client_name_initial);
							cm.setClient_name(client_name_initial);
							cm.setClient_address(client_address);
							//cm.setClient_subject(client_subject);
							cm.setEmail_module(e_module);
							cm.setProject_type(p_type);
							cm.setClient_email(client_email);
							cm.setCreated_by((int) session2.getAttribute("uid"));
							dao3.saveData(cm);
							msg = "Client Successfully Created...";
							
							
							//////////// new Additon ////////////////////////
							
							lst = null;
							dao3 = (Client_masterDao) factory.getBean("d10");
							lst = dao3.viewRecord("select cm.id, cm.client_name, cm.client_address, cm.client_email, "
									+ " mpt.name, mwet.module, cm.created_time  from Client_master cm, Markscan_projecttype mpt, "
									+ " Module_wise_email_template mwet where mpt.id="+p_type+" and mpt.id=cm.project_type and "
									+ " mwet.id=cm.email_module order by cm.id desc");
							client_master_data = new ArrayList<>();
							for (int i = 0; i < lst.size(); i++) {
								CommanReporting url2 = new CommanReporting();
								Object[] obj = (Object[]) lst.get(i);
								url2.setId((Integer) obj[0]);
								url2.setClientName((String) obj[1]); // client name
								url2.setDomainName((String) obj[2]); // client address
								url2.setNote1((String) obj[3]); // client email
								url2.setProjectName((String) obj[4]); // project type
								url2.setLnk_typ((String) obj[5]); // email module
								url2.setDate__c((String) obj[6]); // created date
								client_master_data.add(url2);
							}	
							////////////////////////////////////////////////
						}
					}
					dao3 = null;
					cm = null;
					execute();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return ERROR;
				}
				finally{
					dao3 = null;
					cm = null;
					factory=null;
					session2=null;
				}return SUCCESS;
			} else {
				
				String mail_dmain = null;
				if(client_email !=null && !client_email.isEmpty()){
					String[] mail_domain_arr = client_email.split("@");
					mail_dmain = mail_domain_arr[1];
				}

				if (client_name!=null && client_name.trim().length() < 1) {
					msg = "Error... Client name never be empty......";
				} else if (e_module == 0) {
					msg = "Error... Select Email module...";
				} else if (p_type == 0) {
					msg = "Error... Select Project Type...";
				}else if(client_address !=null && client_address.trim().length() < 1){
					msg = "Error... Client address never be empty...";
				}else if(client_email !=null && client_email.trim().length() <1){
					msg = "Error... Client email never be empty...";
				}else if(!isValid(client_email)) {
					msg = "Error... error on email format.......";
				}else if(!mail_dmain.equalsIgnoreCase("markscan.co.in")){
					msg = "Error... mail domain not matched....";
				} else {
					try {
						
						String client_name_initial="";
						if(capsFlag.equalsIgnoreCase("1")){
							client_name_initial = convert(client_name);
						}else{
							client_name_initial = client_name;
						}
						  query = "update Client_master set client_name='" + client_name_initial + "' , client_address="
								+ " '" + client_address + "' , client_subject= '" + client_subject + "',client_email='"+client_email+"'";
						if (p_type != 0) {
							query = query + " , project_type=" + p_type;
						}
						if (e_module != 0) {
							query = query + " , email_module=" + e_module;
						}
						query = query + "  where id=" + id;

						dao3.customUpdateProject(query);
						msg = "Client Update..";
						dao3 = null;
						query = null;
						
						//////////// new Additon ////////////////////////
						
						lst = null;
						dao3 = (Client_masterDao) factory.getBean("d10");
						lst = dao3.viewRecord("select cm.id, cm.client_name, cm.client_address, cm.client_email, "
								+ " mpt.name, mwet.module, cm.created_time  from Client_master cm, Markscan_projecttype mpt, "
								+ " Module_wise_email_template mwet where mpt.id="+p_type+" and mpt.id=cm.project_type and "
								+ " mwet.id=cm.email_module order by cm.id desc");
						client_master_data = new ArrayList<>();
						for (int i = 0; i < lst.size(); i++) {
							CommanReporting url2 = new CommanReporting();
							Object[] obj = (Object[]) lst.get(i);
							url2.setId((Integer) obj[0]);
							url2.setClientName((String) obj[1]); // client name
							url2.setDomainName((String) obj[2]); // client address
							url2.setNote1((String) obj[3]); // client email
							url2.setProjectName((String) obj[4]); // project type
							url2.setLnk_typ((String) obj[5]); // email module
							url2.setDate__c((String) obj[6]); // created date
							client_master_data.add(url2);
						}	
						////////////////////////////////////////////////
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return ERROR;
					}
					finally {
						dao3 = null;
						query = null;factory=null;
						session2=null;
					}
				}
				
				execute();
				factory=null;
				session2=null;
				dao3=null;query = null;
				return SUCCESS;
				
			}
		}
		// execute();
		// return SUCCESS;
	}

	public String listClient() {
		execute();

		return SUCCESS;
	}
	Object[] obj =null;
	public String edit() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			Client_master cm = null;
			factory = LoginAndSession.getFactory();
			int PTval=0,emailMdval=0;
			try {
				dao3 = (Client_masterDao) factory.getBean("d10");
				
				
				lst = dao3.viewRecord("select cm.id, cm.client_name, cm.client_address, cm.client_subject, "
						+ " mpt.id, mwet.id, cm.created_time,cm.client_email  from Client_master cm, Markscan_projecttype mpt, "
						+ " Module_wise_email_template mwet where cm.id="+id+" and "
								+ " mpt.id=cm.project_type and "
						+ " mwet.id=cm.email_module order by cm.id desc");
				
				/*lst = dao3.viewRecord("select id, client_name, client_address, "
						+ " client_subject from Client_master where id=" + id);*/
				
				// client_master_data = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					// Client_master url2 = new Client_master();
					  obj = (Object[]) lst.get(i);
					  id = ((Integer) obj[0]);
					  client_name = ((String) obj[1]); // client name
					  client_address = ((String) obj[2]); // client address
					  client_subject = ((String) obj[3]); // client subject
					  PTval =((int) obj[4]);
					  emailMdval =((int) obj[5]);
					  System.out.println("client emaiil address is ------------>"+((String) obj[7]));
					  client_email = ((String) obj[7]); //client_email
					  System.out.println("p_type value------client_emailclient_email-------->"+client_email);
					// url2.setClient_subject((String) obj[4]); // project type
					// url2.setProject_type((Integer) obj[5]); // email module
					// url2.setEmail_module((Integer)obj[6]); // created date
					// client_master_data.add(url2);
					obj=null;
				}
				
					//////////// new Additon ////////////////////////
				
							lst = null;
							dao3 = (Client_masterDao) factory.getBean("d10");
							lst = dao3.viewRecord("select cm.id, cm.client_name, cm.client_address, cm.client_email, "
									+ " mpt.name, mwet.module, cm.created_time  from Client_master cm, Markscan_projecttype mpt, "
									+ " Module_wise_email_template mwet where mpt.id="+p_type+" and mpt.id=cm.project_type and "
									+ " mwet.id=cm.email_module order by cm.id desc");
							client_master_data = new ArrayList<>();
							for (int i = 0; i < lst.size(); i++) {
								CommanReporting url2 = new CommanReporting();
								Object[] obj = (Object[]) lst.get(i);
								url2.setId((Integer) obj[0]);
								url2.setClientName((String) obj[1]); // client name
								url2.setDomainName((String) obj[2]); // client address
								url2.setNote1((String) obj[3]); // client email
								url2.setProjectName((String) obj[4]); // project type
								url2.setLnk_typ((String) obj[5]); // email module
								url2.setDate__c((String) obj[6]); // created date
								client_master_data.add(url2);
							}	
							////////////////////////////////////////////////
				execute();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			}
			finally{
				obj=null;dao3=null;lst=null;
				factory=null;session2=null;
				p_type = PTval;
				e_module = emailMdval;
			}return SUCCESS;
		}
	}

	int id, e_module, p_type;
	String client_name, module, project_type, client_address, client_subject,client_email,capsFlag;
	Markscan_projecttypeDao dao1 = null;
	Module_wise_email_templateDao dao2 = null;
	Client_masterDao dao3 = null;
	private List<Markscan_projecttype> project_type_Data = null;
	private List<Module_wise_email_template> module_Data = null;
	private List<CommanReporting> client_master_data = null;
	private String msg = null;
	
	public boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 

	/*private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;*/

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getE_module() {
		return e_module;
	}

	public void setE_module(int e_module) {
		this.e_module = e_module;
	}

	public int getP_type() {
		return p_type;
	}

	public void setP_type(int p_type) {
		this.p_type = p_type;
	}

	public List<Markscan_projecttype> getProject_type_Data() {
		return project_type_Data;
	}

	public void setProject_type_Data(List<Markscan_projecttype> project_type_Data) {
		this.project_type_Data = project_type_Data;
	}

	public List<Module_wise_email_template> getModule_Data() {
		return module_Data;
	}

	public void setModule_Data(List<Module_wise_email_template> module_Data) {
		this.module_Data = module_Data;
	}

	public List<CommanReporting> getClient_master_data() {
		return client_master_data;
	}

	public void setClient_master_data(List<CommanReporting> client_master_data) {
		this.client_master_data = client_master_data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public String getClient_subject() {
		return client_subject;
	}

	public void setClient_subject(String client_subject) {
		this.client_subject = client_subject;
	}

	public Client_master getC_master() {
		return c_master;
	}

	public void setC_master(Client_master c_master) {
		this.c_master = c_master;
	}

	public String getClient_email() {
		return client_email;
	}

	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}

	public String getCapsFlag() {
		return capsFlag;
	}

	public void setCapsFlag(String capsFlag) {
		this.capsFlag = capsFlag;
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
