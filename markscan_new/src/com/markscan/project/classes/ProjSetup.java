/**
 * 
 */
package com.markscan.project.classes;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Blacklist_sites;
import com.markscan.project.beans.CommanReporting;
import com.markscan.project.beans.Keyword_filter;
import com.markscan.project.beans.Keyword_filter_extension_master;
import com.markscan.project.beans.Markscan_machine;
import com.markscan.project.beans.Markscan_pipe;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Master_crawle_url;
//import com.markscan.project.beans.Stored_project_setup;
import com.markscan.project.beans.Stored_project_setup1;
import com.markscan.project.classes.bot.BotStarter;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Blacklist_sitesDao;
import com.markscan.project.dao.Client_masterDao;
import com.markscan.project.dao.Keyword_filterDao;
import com.markscan.project.dao.Keyword_filter_extension_masterDao;
import com.markscan.project.dao.Markscan_machineDao;
import com.markscan.project.dao.Markscan_pipeDao;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.Master_crawle_urlDao;
import com.markscan.project.dao.Stored_project_setup1Dao;
//import com.markscan.project.dao.Stored_project_setupDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author pradeep
 *
 */
public class ProjSetup extends ActionSupport {
	private static final Logger logger = Logger.getLogger(ProjSetup.class);

	/**
	 * 
	 */
	public ProjSetup() {
		// TODO Auto-generated constructor stub
	}

	Object[] obj = null;
	HttpSession session2 = null;
	private BeanFactory factory = null;
	String oneField = "";

	public String execute() { // templete configuration.....

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
			Markscan_projecttypeDao dao = null;
			Markscan_projecttype url2 = null;
			try {

				dao = (Markscan_projecttypeDao) factory.getBean("d8");

				lst = dao.viewRecord("select id, name from Markscan_projecttype");
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
				lst = null;

			} catch (Exception e) {
				logger.error("project type data get ", e);
				return ERROR;
			} finally {
				obj = null;
				url2 = null;
				lst = null;
				dao = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;

		}
	}

	public String pSetup() {
		// System.out.println("----- oneField========" + oneField);
		session2 = ServletActionContext.getRequest().getSession(false);
		if (session2 == null || session2.getAttribute("login") == null) {
			System.out.println("if========='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
/*
			List<String> keywrd = new ArrayList<>();
			keywrd.add(one.trim());
			keywrd.add(two.trim());
			keywrd.add(three.trim());
			keywrd.add(four.trim());
			keywrd.add(five.trim());
			keywrd.add(six.trim());
			keywrd.add(seven.trim());
			keywrd.add(eight.trim());
			
			// System.out.println(keywrd.size() + "--- keyword size");
			Keyword_filter kf = new Keyword_filter();
			try {
				Keyword_filterDao dao = (Keyword_filterDao) factory.getBean("d3");
				// String query = "";

				for (String pj : keywrd) {
					// System.out.println("====~~~~ " + pj);
					if (pj.isEmpty() || pj.equals("") || pj == null) {
					} else {
						kf.setKeyword(pj);
						kf.setProject_id(propertyName);
						dao.saveData(kf);
					}
				}
				dao = null;
			} catch (Exception e) {
				System.err.println("keyphrase store error");
				// e.printStackTrace();
			} finally {
				kf = null;
				keywrd = null;
			}
			*/

			/**
			 * === key phrase
			 */
			Keyword_filter_extension_master kfm = null;
			try {
				Keyword_filter_extension_masterDao dao = (Keyword_filter_extension_masterDao) factory.getBean("d4");
				lst = dao.viewRecord(
						"select kfem.id,kfem.keyphrase from Keyword_filter_extension_master as kfem where kfem.projectType = "
								+ projecttype);
				keyextn = new ArrayList<>();
				if (lst.size() < 1) {
					kfm = new Keyword_filter_extension_master();
					kfm.setKeyphrase(pvalue);
					keyextn.add(kfm);
					kfm = null;
				} else {
					for (int i = 0; i < lst.size(); i++) {
						obj = (Object[]) lst.get(i);
						kfm = new Keyword_filter_extension_master();
						// keyextn.add(pvalue.concat(" ").concat((String)
						// obj[1]));
						if (oneField.trim() == null || oneField.trim() == "" || oneField.trim().isEmpty()) {
							kfm.setKeyphrase(pvalue.trim() + " " + (String) obj[1]);
						} else {
							kfm.setKeyphrase(pvalue.trim() + " " + oneField.trim() + " " + (String) obj[1]);
						}
						keyextn.add(kfm);
						obj = null;
						kfm = null;
					}
				}
				/*
				 * System.out.println(keyextn.size());
				 * 
				 * for (Keyword_filter_extension_master pj : keyextn) {
				 * System.out.println(pj.getKeyphrase()); }
				 */
				dao = null;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				obj = null;
				kfm = null;
				lst = null;
			}
			/**
			 * ====machine
			 */
			Markscan_machine mm = null;
			try {
				Markscan_machineDao dao = (Markscan_machineDao) factory.getBean("d6");
				lst = dao.viewRecord("select msm.ip_address, msm.name from Markscan_machine as msm");
				machine = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);
					mm = new Markscan_machine();
					mm.setIp_address((String) obj[0]);
					mm.setName((String) obj[1]);
					machine.add(mm);
					mm = null;
					obj = null;
				}

				dao = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lst = null;
				mm = null;
				obj = null;
			}
			/**
			 * ===pipe
			 */
			//Markscan_pipe mp = null;
			/*
			try {
				Markscan_pipeDao dao = (Markscan_pipeDao) factory.getBean("d5");
				lst = dao.viewRecord("select msp.id,msp.name from Markscan_pipe as msp where msp.id>3");// google
																										// yahoo,
																										// bing
																										// will
																										// not
																										// visiable
																										// to
																										// user
				pipe = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);
					mp = new Markscan_pipe();
					mp.setId((Integer) obj[0]);
					mp.setName((String) obj[1]);
					pipe.add(mp);
					mp = null;
					obj = null;
				}
				dao = null;
				lst = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mp = null;
				obj = null;
				lst = null;
			}
			*/
			factory = null;
			session2 = null;
			return SUCCESS;
		}
		
	}

	public String addKeyphrase() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Keyword_filter_extension_masterDao dao2 = null;
			Keyword_filter_extension_master kfem = null;
			try {
				dao2 = (Keyword_filter_extension_masterDao) factory.getBean("d4");
				kfem = new Keyword_filter_extension_master();
				kfem.setProjectType(projecttype);
				kfem.setKeyphrase(keyphrase);
				dao2.saveData(kfem);

			} catch (Exception e) {
				// TODO: handle exception
				return ERROR;
			} finally {
				dao2 = null;
				kfem = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	public String addKeyphraseSave() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Keyword_filter_extension_masterDao dao2 = null;
			Keyword_filter_extension_master kfem = null;
			try {
				dao2 = (Keyword_filter_extension_masterDao) factory.getBean("d4");
				kfem = new Keyword_filter_extension_master();
				kfem.setProjectType(projecttype);
				kfem.setKeyphrase(keyphrase);
				dao2.saveData(kfem);
				execute();

			} catch (Exception e) {
				// TODO: handle exception
				return ERROR;
			} finally {
				dao2 = null;
				kfem = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;
		}
	}

	List<Keyword_filter_extension_master> keyextn = null;
	//List<Markscan_pipe> pipe = null;
	BotStarter bstr=null;
	List<Markscan_machine> machine = null;
	private String one = null;
	private String two = null;
	private String three = null;
	private String four = null;
	private String five = null;
	public int getClientname() {
		return clientname;
	}

	public void setClientname(int clientname) {
		this.clientname = clientname;
	}

	private String six = null;
	private String seven = null;
	private String eight = null;
	private int projecttype;
	private int propertyName;
	private int clientname;
	private String pvalue = null;
	private String keyphrase = null;
    
	public String getKeyphrase() {
		return keyphrase;
	}

	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}

	public List<Markscan_machine> getMachine() {
		return machine;
	}

	public void setMachine(List<Markscan_machine> machine) {
		this.machine = machine;
	}
	/*
	public List<Markscan_pipe> getPipe() {
		return pipe;
	}

	public void setPipe(List<Markscan_pipe> pipe) {
		this.pipe = pipe;
	}
*/
	public List<Keyword_filter_extension_master> getKeyextn() {
		return keyextn;
	}

	public void setKeyextn(List<Keyword_filter_extension_master> keyextn) {
		this.keyextn = keyextn;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public String getThree() {
		return three;
	}

	public void setThree(String three) {
		this.three = three;
	}

	public String getFour() {
		return four;
	}

	public void setFour(String four) {
		this.four = four;
	}

	public String getFive() {
		return five;
	}

	public void setFive(String five) {
		this.five = five;
	}

	public String getSix() {
		return six;
	}

	public void setSix(String six) {
		this.six = six;
	}

	public String getSeven() {
		return seven;
	}

	public void setSeven(String seven) {
		this.seven = seven;
	}

	public String getEight() {
		return eight;
	}

	public void setEight(String eight) {
		this.eight = eight;
	}

	public int getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public int getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(int propertyName) {
		this.propertyName = propertyName;
	}

	public String getPvalue() {
		return pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	/**
	 * === project store===
	 */

	//public void storeData(int uid, BeanFactory bf, String keyp, int pi, String mach, int preoty, int bls) {
	public void storeData(int uid, BeanFactory bf, String keyp, int pipe) {
		Stored_project_setup1Dao dao = null;
		Stored_project_setup1 sps = null;
		try {
			dao = (Stored_project_setup1Dao) bf.getBean("d7");
			// String query = "";
			sps = new Stored_project_setup1();
			sps.setKeyphrase(keyp);
			sps.setProjectId(propertyName);
			sps.setUserId(uid);
			sps.setGoogle(0);
			sps.setYahoo(0);
			sps.setBing(0);
			sps.setPipe(pipe);
			sps.setDuckduckgo(0);
			sps.setRussiaGo(0);
			//sps.setBls(bls);
			//dao.saveData(sps);
			dao.saveData(sps);
			// System.out.println("store data");
		} catch (Exception e) {
			// System.err.println("action not selected to store projectsetup");
			e.printStackTrace();
		} finally {
			dao = null;
			sps = null;
		}

	}

	@SuppressWarnings("finally")
	public String setupStore() {
		// System.out.println(projecttype + "== prop name pj..." + pid);
		session2 = ServletActionContext.getRequest().getSession(false);

		if (session2 == null || session2.getAttribute("login") == null) {
			return LOGIN;
		} else {

			factory = LoginAndSession.getFactory();
			HttpServletRequest request = ServletActionContext.getRequest();
			int count = Integer.parseInt(request.getParameter("count"));
			int count1 = 0;
			String keywords = request.getParameter("keyword1").trim();
			if (keywords.equals("Static Keyword")) {
				for (int i = 1; i <= count; i++) {
					try {
						keyph1 = request.getParameter(String.valueOf(i));
						int pipe1 = Integer.parseInt(request.getParameter(keyph1));
						if (keyph1 != null) {
							storeData((int) session2.getAttribute("uid"), factory, keyph1, pipe1);
							count1 = 1;
						}

					} catch (Exception e) {
						// keyph1=null;
						System.out.println(e);
						logger.info(e);
						// return SUCCESS;

					} finally {
						keyph1 = null;
						// return SUCCESS;

					}

				}
			}

			// **************************Dynamic Keyword
			if (keywords.equals("Dynamic Keyword")) {
				for (int i = 1; i <= count; i++) {
					try {
						String pi = "pip" + i;
						keyph1 = request.getParameter(String.valueOf(i));
						int pipe1 = Integer.parseInt(request.getParameter(pi));
						if (keyph1.length() != 0) {
							storeData((int) session2.getAttribute("uid"), factory, keyph1, pipe1);
							count1 = 1;
						}

					} catch (Exception e) {

						System.out.println(e);
						logger.info(e);

					} finally {
						keyph1 = null;
						// return SUCCESS;

					}

				}
			}
			if (count1 == 1) {
				System.out.println("********************count1*********" + count1);
				bstr = new BotStarter();
				try {
					bstr.botRun("1");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// return SUCCESS;
			factory = null;
			session2 = null;
			logger.info("*********************************done****************");
			return SUCCESS;

		}

	} // return setupStore;
	
	
	
	

	int action0, action1, action2, action3, action4, action5, action6, action7, action8, action9, preority0, preority1,
			preority2, preority3, preority4, preority5, preority6, preority7, preority8, preority9;
	int bls1, bls2, bls3, bls4, bls5, bls6, bls7, bls8, bls9, bls0;

	int pid;
	String machine0 = null, machine1 = null, machine2 = null, machine3 = null, machine4 = null, machine5 = null,
			machine6 = null, machine7 = null, machine8 = null, machine9 = null;
	// String mpipe0 = null, mpipe1 = null;
	int mpipe1, mpipe0, mpipe2, mpipe3, mpipe4, mpipe5, mpipe6, mpipe7, mpipe8, mpipe9;
	// String mpipe2 = null, mpipe3 = null, mpipe4 = null, mpipe5 = null, mpipe6
	// = null, mpipe7 = null, mpipe8 = null,
	// mpipe9 = null;

	String keyph0 = null, keyph1 = null, keyph2 = null, keyph3 = null, keyph4 = null, keyph5 = null, keyph6 = null,
			keyph7 = null, keyph8 = null, keyph9 = null;

	int uuid;

	public int getBls1() {
		return bls1;
	}

	public void setBls1(int bls1) {
		this.bls1 = bls1;
	}

	public int getBls2() {
		return bls2;
	}

	public void setBls2(int bls2) {
		this.bls2 = bls2;
	}

	public int getBls3() {
		return bls3;
	}

	public void setBls3(int bls3) {
		this.bls3 = bls3;
	}

	public int getBls4() {
		return bls4;
	}

	public void setBls4(int bls4) {
		this.bls4 = bls4;
	}

	public int getBls5() {
		return bls5;
	}

	public void setBls5(int bls5) {
		this.bls5 = bls5;
	}

	public int getBls6() {
		return bls6;
	}

	public void setBls6(int bls6) {
		this.bls6 = bls6;
	}

	public int getBls7() {
		return bls7;
	}

	public void setBls7(int bls7) {
		this.bls7 = bls7;
	}

	public int getBls8() {
		return bls8;
	}

	public void setBls8(int bls8) {
		this.bls8 = bls8;
	}

	public int getBls9() {
		return bls9;
	}

	public void setBls9(int bls9) {
		this.bls9 = bls9;
	}

	public int getBls0() {
		return bls0;
	}

	public void setBls0(int bls0) {
		this.bls0 = bls0;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public int getAction0() {
		return action0;
	}

	public void setAction0(int action0) {
		this.action0 = action0;
	}

	public int getAction1() {
		return action1;
	}

	public void setAction1(int action1) {
		this.action1 = action1;
	}

	public int getAction2() {
		return action2;
	}

	public void setAction2(int action2) {
		this.action2 = action2;
	}

	public int getAction3() {
		return action3;
	}

	public void setAction3(int action3) {
		this.action3 = action3;
	}

	public int getAction4() {
		return action4;
	}

	public void setAction4(int action4) {
		this.action4 = action4;
	}

	public int getAction5() {
		return action5;
	}

	public void setAction5(int action5) {
		this.action5 = action5;
	}

	public int getAction6() {
		return action6;
	}

	public void setAction6(int action6) {
		this.action6 = action6;
	}

	public int getAction7() {
		return action7;
	}

	public void setAction7(int action7) {
		this.action7 = action7;
	}

	public int getAction8() {
		return action8;
	}

	public void setAction8(int action8) {
		this.action8 = action8;
	}

	public int getAction9() {
		return action9;
	}

	public void setAction9(int action9) {
		this.action9 = action9;
	}

	public int getPreority0() {
		return preority0;
	}

	public void setPreority0(int preority0) {
		this.preority0 = preority0;
	}

	public int getPreority1() {
		return preority1;
	}

	public void setPreority1(int preority1) {
		this.preority1 = preority1;
	}

	public int getPreority2() {
		return preority2;
	}

	public void setPreority2(int preority2) {
		this.preority2 = preority2;
	}

	public int getPreority3() {
		return preority3;
	}

	public void setPreority3(int preority3) {
		this.preority3 = preority3;
	}

	public int getPreority4() {
		return preority4;
	}

	public void setPreority4(int preority4) {
		this.preority4 = preority4;
	}

	public int getPreority5() {
		return preority5;
	}

	public void setPreority5(int preority5) {
		this.preority5 = preority5;
	}

	public int getPreority6() {
		return preority6;
	}

	public void setPreority6(int preority6) {
		this.preority6 = preority6;
	}

	public int getPreority7() {
		return preority7;
	}

	public void setPreority7(int preority7) {
		this.preority7 = preority7;
	}

	public int getPreority8() {
		return preority8;
	}

	public void setPreority8(int preority8) {
		this.preority8 = preority8;
	}

	public int getPreority9() {
		return preority9;
	}

	public void setPreority9(int preority9) {
		this.preority9 = preority9;
	}

	public String getMachine0() {
		return machine0;
	}

	public void setMachine0(String machine0) {
		this.machine0 = machine0;
	}

	public String getMachine1() {
		return machine1;
	}

	public void setMachine1(String machine1) {
		this.machine1 = machine1;
	}

	public String getMachine2() {
		return machine2;
	}

	public void setMachine2(String machine2) {
		this.machine2 = machine2;
	}

	public String getMachine3() {
		return machine3;
	}

	public void setMachine3(String machine3) {
		this.machine3 = machine3;
	}

	public String getMachine4() {
		return machine4;
	}

	public void setMachine4(String machine4) {
		this.machine4 = machine4;
	}

	public String getMachine5() {
		return machine5;
	}

	public void setMachine5(String machine5) {
		this.machine5 = machine5;
	}

	public String getMachine6() {
		return machine6;
	}

	public void setMachine6(String machine6) {
		this.machine6 = machine6;
	}

	public String getMachine7() {
		return machine7;
	}

	public void setMachine7(String machine7) {
		this.machine7 = machine7;
	}

	public String getMachine8() {
		return machine8;
	}

	public void setMachine8(String machine8) {
		this.machine8 = machine8;
	}

	public String getMachine9() {
		return machine9;
	}

	public void setMachine9(String machine9) {
		this.machine9 = machine9;
	}

	public int getMpipe1() {
		return mpipe1;
	}

	public void setMpipe1(int mpipe1) {
		this.mpipe1 = mpipe1;
	}

	public int getMpipe0() {
		return mpipe0;
	}

	public void setMpipe0(int mpipe0) {
		this.mpipe0 = mpipe0;
	}

	public int getMpipe2() {
		return mpipe2;
	}

	public void setMpipe2(int mpipe2) {
		this.mpipe2 = mpipe2;
	}

	public int getMpipe3() {
		return mpipe3;
	}

	public void setMpipe3(int mpipe3) {
		this.mpipe3 = mpipe3;
	}

	public int getMpipe4() {
		return mpipe4;
	}

	public void setMpipe4(int mpipe4) {
		this.mpipe4 = mpipe4;
	}

	public int getMpipe5() {
		return mpipe5;
	}

	public void setMpipe5(int mpipe5) {
		this.mpipe5 = mpipe5;
	}

	public int getMpipe6() {
		return mpipe6;
	}

	public void setMpipe6(int mpipe6) {
		this.mpipe6 = mpipe6;
	}

	public int getMpipe7() {
		return mpipe7;
	}

	public void setMpipe7(int mpipe7) {
		this.mpipe7 = mpipe7;
	}

	public int getMpipe8() {
		return mpipe8;
	}

	public void setMpipe8(int mpipe8) {
		this.mpipe8 = mpipe8;
	}

	public int getMpipe9() {
		return mpipe9;
	}

	public void setMpipe9(int mpipe9) {
		this.mpipe9 = mpipe9;
	}

	public String getKeyph0() {
		return keyph0;
	}

	public void setKeyph0(String keyph0) {
		this.keyph0 = keyph0;
	}

	public String getKeyph1() {
		return keyph1;
	}

	public void setKeyph1(String keyph1) {
		this.keyph1 = keyph1;
	}

	public String getKeyph2() {
		return keyph2;
	}

	public void setKeyph2(String keyph2) {
		this.keyph2 = keyph2;
	}

	public String getKeyph3() {
		return keyph3;
	}

	public void setKeyph3(String keyph3) {
		this.keyph3 = keyph3;
	}

	public String getKeyph4() {
		return keyph4;
	}

	public void setKeyph4(String keyph4) {
		this.keyph4 = keyph4;
	}

	public String getKeyph5() {
		return keyph5;
	}

	public void setKeyph5(String keyph5) {
		this.keyph5 = keyph5;
	}

	public String getKeyph6() {
		return keyph6;
	}

	public void setKeyph6(String keyph6) {
		this.keyph6 = keyph6;
	}

	public String getKeyph7() {
		return keyph7;
	}

	public void setKeyph7(String keyph7) {
		this.keyph7 = keyph7;
	}

	public String getKeyph8() {
		return keyph8;
	}

	public void setKeyph8(String keyph8) {
		this.keyph8 = keyph8;
	}

	public String getKeyph9() {
		return keyph9;
	}

	public void setKeyph9(String keyph9) {
		this.keyph9 = keyph9;
	}

	public String getOneField() {
		return oneField;
	}

	public void setOneField(String oneField) {
		this.oneField = oneField;
	}

	/**
	 * ----- content filter==============
	 */
	public String deleteFilterPre() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			
			Markscan_projecttype url2 = null;
			Markscan_projecttypeDao dao = null;
			
			try {

				dao = (Markscan_projecttypeDao) factory.getBean("d8");

				lst = dao.viewRecord("select id, name from Markscan_projecttype");
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
				lst = null;

			} catch (Exception e) {
				logger.error("project type data get ", e);
				return ERROR;
			} finally {
				lst = null;
				obj = null;
				url2 = null;
				dao = null;
				factory = null;
				session2 = null;
			}
			return SUCCESS;

		}

	}

	public String deleteFilter() {
		// System.out.println("property name=== ++ " + propertyName);
		delFilter = new ArrayList<>();
		// System.out.println("..........1////........'" + twentyone);
		// System.out.println("..........2////........'" + twentytwo);
		// System.out.println("..........3////........'" + twentythree);
		// System.out.println("..........4////........'" + twentyfour);

		System.out.println(".........Client Name........'" + clientname);
		delFilter.add(one.trim());
		delFilter.add(two.trim());
		delFilter.add(three.trim());
		delFilter.add(four.trim());
		delFilter.add(five.trim());
		delFilter.add(six.trim());
		delFilter.add(seven.trim());
		delFilter.add(eight.trim());
		delFilter.add(nine.trim());
		delFilter.add(ten.trim());
		delFilter.add(eleven.trim());
		delFilter.add(twelve.trim());
		delFilter.add(thirteen.trim());
		delFilter.add(fourteen.trim());
		delFilter.add(fifteen.trim());
		delFilter.add(sixteen.trim());
		delFilter.add(seventeen.trim());
		delFilter.add(eighteen.trim());
		delFilter.add(nineteen.trim());
		delFilter.add(twenty.trim());
		System.out.println("*********************Start Date***************"+startdate+"abc");
		System.out.println("*********************End Date***************"+enddate+"abc");


		if (twentyone.trim().length() >= 1) {
			delFilter.add(twentyone.trim());
		}
		if (twentytwo.trim().length() >= 1) {
			delFilter.add(twentytwo.trim());
		}
		if (twentythree.trim().length() >= 1) {
			delFilter.add(twentythree.trim());
		}
		if (twentyfour.trim().length() >= 1) {
			delFilter.add(twentyfour.trim());
		}
		
		// System.out.println("--------------------------------------------------------"
		// + delFilter);

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			Master_crawle_urlDao dao = null;
			Master_crawle_url url2 = null;
			try {
				int pj = 0;
				factory = ActionPerform.getFactory();
				dao = (Master_crawle_urlDao) factory.getBean("d12");
				
				if((propertyName==0)&&(startdate.length()==0)&&(enddate.length()==0))
				{
					System.out.println("*******************Condition1*****");
					lst = dao.viewRecord(
							"select mcu.id, mcu.crawle_url2,mcu.created_on,mcu.user_id,pf.project_name from Master_crawle_url mcu, Project_info pf where mcu.df_perform=0"
									+ " and mcu.w_list=0 and mcu.project_id=pf.id and pf.client_type= "+clientname);
					
				}
				else if((propertyName==0) &&(startdate.length()!=0)&&(enddate.length()!=0) )
				{
					System.out.println("*******************Condition2*****");
					lst = dao.viewRecord(
							"select mcu.id, mcu.crawle_url2,mcu.created_on,mcu.user_id,pf.project_name from Master_crawle_url mcu, Project_info pf where mcu.df_perform=0"
									+ " and mcu.w_list=0 and mcu.project_id=pf.id and pf.client_type= "+clientname+" and ( mcu.created_on between '"+startdate+"' and '"+enddate+"' )");
					
				}
				
				else if((propertyName!=0) &&(startdate.length()!=0)&&(enddate.length()!=0) )
				{
					System.out.println("*******************Condition3*****");
					lst = dao.viewRecord(
							"select mcu.id, mcu.crawle_url2,mcu.created_on,mcu.user_id,pf.project_name from Master_crawle_url mcu, Project_info pf where mcu.df_perform=0"
								+ " and mcu.w_list=0  and  mcu.project_id =" + propertyName+" and (mcu.created_on between '"+startdate+"' and '"+enddate+"')");
					
				}
				else
				{
					System.out.println("*******************Condition4*****");
				lst = dao.viewRecord(
						"select mcu.id, mcu.crawle_url2,mcu.created_on,mcu.user_id,pf.project_name from Master_crawle_url mcu, Project_info pf where mcu.df_perform=0"
								+ " and mcu.w_list=0  and  mcu.project_id =" + propertyName);
				}
				masterCrawluurl = new ArrayList<>();
				Boolean bb = false;
				for (int i = 0; i < lst.size(); i++) {
					bb = false;
					url2 = new Master_crawle_url();
					obj = (Object[]) lst.get(i);
					
					for (String ppj : delFilter) {
						bb = false;
						pj = (Integer) obj[0];
						if (obj[1].toString().toLowerCase().trim().contains(ppj.toLowerCase().trim())) {// delete
																										// operation
																										// perform.

							bb = true;
							break;
						}
						
					}
					
					if (bb == false) {
						url2.setId(pj);
						url2.setCrawle_url2(obj[1].toString());
						url2.setCreated_on(obj[2].toString());
						url2.setUser_id((Integer) obj[3]);
						//url2.setProject_id(propertyName);
						url2.setProjectName(obj[4].toString());
						masterCrawluurl.add(url2);
						/**
						 * BOT query will be return duplicate data; we can not
						 * prevent the duplicate data due to link activation
						 * surety .
						 */

					}

					dao.customUpdateProject("update Master_crawle_url set df_perform=1, link_logger="
							+ (int) session2.getAttribute("uid") + " , surl_status='" + nowTime() + "'   where id="
							+ pj);

					obj = null;
					url2 = null;
				}

				System.out.println("----mster size two..." + masterCrawluurl.size());
				downloadCSVFileAction();
			} catch (Exception e) {
				// System.err.println("=== error ==occure====");
				e.printStackTrace();
				return ERROR;
			} finally {
				obj = null;
				url2 = null;
				dao = null;
				factory = null;
				session2 = null;
				delFilter = null;
				
				one = null;
				two = null;
				three = null;
				four = null;
				five = null;
				six = null;
				seven = null;
				eight = null;
				nine = null;
				ten = null;
				eleven = null;
				twelve = null;
				thirteen = null;
				fourteen = null;
				fifteen = null;
				sixteen = null;
				seventeen = null;
				eighteen = null;
				nineteen = null;
				twenty = null;
				

			}

			deleteFilterPre();
			return null;
		}
	}

	public void downloadCSVFileAction() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		String outputString = null;
		ArrayList<String> rows = null;
		String reportName = null;
		Iterator<String> iter = null;
		try {
			response.setContentType("text/csv");
			reportName = "crawl_data_" + propertyName + ".csv";
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);

			rows = new ArrayList<String>();
			rows.add("ID,Link,Date, User ID, Project Name");
			rows.add("\n");

			for (Master_crawle_url mcu : masterCrawluurl) {
				rows.add("\"" + mcu.getId() + "\",\"" + mcu.getCrawle_url2() + "\",\"" + mcu.getCreated_on() + "\",\""
						+ mcu.getUser_id() + "\",\"" + mcu.getProjectName() + "\"");
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response = null;
			outputString = null;
			rows = null;
			reportName = null;
			iter = null;
		}
	}

	private List<Markscan_projecttype> listData = null;
	List lst = null;

	public List<Markscan_projecttype> getListData() {
		return listData;
	}

	public void setListData(List<Markscan_projecttype> listData) {
		this.listData = listData;
	}

	public String nowTime() {
		String time__c = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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

	String nine = null;
	String ten = null;
	String eleven = null;
	String twelve = null;
	String thirteen = null;
	String fourteen = null;
	String fifteen = null;
	String sixteen = null;
	String seventeen = null;
	String eighteen = null;
	String nineteen = null;
	String twenty = null;
	String twentyone = null;
	String twentytwo = null;
	String twentythree = null;
	String twentyfour = null;
	String startdate=null;
	String enddate=null;
	

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

	public String getTwentyone() {
		return twentyone;
	}

	public void setTwentyone(String twentyone) {
		this.twentyone = twentyone;
	}

	public String getTwentytwo() {
		return twentytwo;
	}

	public void setTwentytwo(String twentytwo) {
		this.twentytwo = twentytwo;
	}

	public String getTwentythree() {
		return twentythree;
	}

	public void setTwentythree(String twentythree) {
		this.twentythree = twentythree;
	}

	public String getTwentyfour() {
		return twentyfour;
	}

	public void setTwentyfour(String twentyfour) {
		this.twentyfour = twentyfour;
	}

	List<String> delFilter = null;
	List<Master_crawle_url> masterCrawluurl = null;

	public List<Master_crawle_url> getMasterCrawluurl() {
		return masterCrawluurl;
	}

	public void setMasterCrawluurl(List<Master_crawle_url> masterCrawluurl) {
		this.masterCrawluurl = masterCrawluurl;
	}

	public String getNine() {
		return nine;
	}

	public void setNine(String nine) {
		this.nine = nine;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getEleven() {
		return eleven;
	}

	public void setEleven(String eleven) {
		this.eleven = eleven;
	}

	public String getTwelve() {
		return twelve;
	}

	public void setTwelve(String twelve) {
		this.twelve = twelve;
	}

	public String getThirteen() {
		return thirteen;
	}

	public void setThirteen(String thirteen) {
		this.thirteen = thirteen;
	}

	public String getFourteen() {
		return fourteen;
	}

	public void setFourteen(String fourteen) {
		this.fourteen = fourteen;
	}

	public String getFifteen() {
		return fifteen;
	}

	public void setFifteen(String fifteen) {
		this.fifteen = fifteen;
	}

	public String getSixteen() {
		return sixteen;
	}

	public void setSixteen(String sixteen) {
		this.sixteen = sixteen;
	}

	public String getSeventeen() {
		return seventeen;
	}

	public void setSeventeen(String seventeen) {
		this.seventeen = seventeen;
	}

	public String getEighteen() {
		return eighteen;
	}

	public void setEighteen(String eighteen) {
		this.eighteen = eighteen;
	}

	public String getNineteen() {
		return nineteen;
	}

	public void setNineteen(String nineteen) {
		this.nineteen = nineteen;
	}

	public String getTwenty() {
		return twenty;
	}

	public void setTwenty(String twenty) {
		this.twenty = twenty;
	}

	/**
	 * ============ black list site add/delete================
	 */

	Blacklist_sites bs = null;
	CommanReporting cr = null;
	List<CommanReporting> crList = null;

	@SuppressWarnings("finally")
	public String executeBLS() { // templete configuration.....

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {

			factory = LoginAndSession.getFactory();
			try {
				dao2 = (Blacklist_sitesDao) factory.getBean("d32");

				lst = dao2.viewRecord(
						"select bs.domain, mu.name, bs.created_on, bs.id from Blacklist_sites as bs, Markscan_users as mu "
						+ " where mu.id=bs.created_by");
				crList = new ArrayList<>();

				for (int i = 0; i < lst.size(); i++) {
					obj = (Object[]) lst.get(i);
					cr = new CommanReporting();
					cr.setDomainName(obj[0].toString());// domain
					cr.setUserName(obj[1].toString());// username
					cr.setDate__c(obj[2].toString()); // createddate
					cr.setLink(obj[3].toString());// id
					crList.add(cr);
					obj = null;
					cr = null;
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			} finally {
				dao2 = null;
				factory = null;
				session2 = null;
				lst = null;
				cr = null;
				return SUCCESS;
			}

		}
	}

	@SuppressWarnings("finally")
	public String saveBLS() { // templete configuration.....

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			try {
				nine = "";
				dao2 = (Blacklist_sitesDao) factory.getBean("d32");
				bs = new Blacklist_sites();
				bs.setDomain(ten.trim());
				bs.setCreated_by((int) session2.getAttribute("uid"));
				if (dao2.saveData(bs) != null) {
					nine = "Domain " + ten + " successfully added !!!";
				} else {
					nine = "Domain " + ten + " Already Exist";
				}
				dao2 = null;
				bs = null;
				factory = null;
				session2 = null;
				executeBLS();
			} catch (Exception e) {
				nine = "data save error.....";
				e.printStackTrace();
				return ERROR;

			} finally {
				dao2 = null;
				bs = null;
				factory = null;
				session2 = null;
				return SUCCESS;
			}
		}
	}
	
	Blacklist_sitesDao dao2 = null;
	public String deleteBLS()
	{
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		// System.out.println(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if=====session error reporting===='");
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			dao2 = (Blacklist_sitesDao) factory.getBean("d32");
			dao2.customUpdateProject("delete from Blacklist_sites where id ='"+four.trim()+"'");
			executeBLS();
		}
		return SUCCESS;
	}

	public List<CommanReporting> getCrList() {
		return crList;
	}

	public void setCrList(List<CommanReporting> crList) {
		this.crList = crList;
	}

}
