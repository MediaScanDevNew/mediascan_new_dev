/**
 * 
 */
package com.markscan.project.classes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.markscan.project.beans.Keyword_filter;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Keyword_filterDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author pradeep
 *
 */
public class DataCrawle extends ActionSupport {

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(DataCrawle.class);
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	HttpSession session2 = null;

	public DataCrawle() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			System.out.println("if========='");
			return LOGIN;
		} else {
			session2=null;
			return SUCCESS;
		}
	}

	public String projectSetup() {
		session2 = ServletActionContext.getRequest().getSession();
		if (session2 == null || session2.getAttribute("login") == null) {
			// System.out.println("if========='");
			logger.error("session errir");
			return LOGIN;
		} else {
			factory = LoginAndSession.getFactory();
			Keyword_filter kf=null;
			Keyword_filterDao dao = (Keyword_filterDao) factory.getBean("d3");
			List<String> keywrd = new ArrayList<>();
			keywrd.add(one.trim());
			keywrd.add(two.trim());
			keywrd.add(three.trim());
			keywrd.add(four.trim());
			keywrd.add(five.trim());
			keywrd.add(six.trim());
			keywrd.add(seven.trim());
			keywrd.add(eight.trim());
			try {
//				String query = "";
				  kf = new Keyword_filter();
				for (String pj : keywrd) {
					kf.setKeyword(pj);
					kf.setProject_id(propertyName);
					dao.saveData(kf);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				factory =null;
				kf=null;
				dao=null;
				keywrd=null;
				
			}
			return SUCCESS;
		}
	}

	

	private BeanFactory factory = null;
	private int propertyName;
	private String one = null;
	private String two = null;
	private String three = null;
	private String four = null;
	private String five = null;
	private String six = null;
	private String seven = null;
	private String eight = null;

	public int getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(int propertyName) {
		this.propertyName = propertyName;
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

}
