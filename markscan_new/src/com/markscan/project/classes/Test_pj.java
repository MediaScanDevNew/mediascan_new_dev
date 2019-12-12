/**
 * 
 */
package com.markscan.project.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.markscan.project.beans.Markscan_users;
import com.markscan.project.dao.UsersDao;
import com.markscan.project.hbm.HbmTmplateBeanFactory;


/**
 * @author pradeep
 *
 */
public class Test_pj {

	/**
	 * 
	 */
	public Test_pj() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String...ss)
	{
		List<testBean> ben= new ArrayList<>();
		for(int i=1; i<=100;i++)
		{
		testBean bb= new testBean();
		bb.setName("apple"+i);
		bb.setAddress("india"+i);
		bb.setState("delhi"+i);
		bb.setPhone(1234);
		bb.setPin(1100220);
			ben.add(bb);	
		}
		for(testBean tst:ben)
		{
			System.out.print(tst.getName() + "   |   ");
			System.out.print(tst.getAddress()+ "   |   ");
			System.out.print(tst.getState()+ "   |   ");
			System.out.print(tst.getPhone()+ "   |   ");
			System.out.print(tst.getPin()+ "   |   ");
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------");
			
		}
	}
	
	
	public String execute() {
		try {
			Properties	prop = new Properties();
		String 	configFile = "/template/pmqc.ftl";
			System.out.println(configFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
