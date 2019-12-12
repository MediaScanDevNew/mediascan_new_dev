package com.markscan.project.hbm;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HbmTmplateBeanFactory {
	public HbmTmplateBeanFactory() {
	}

	public static BeanFactory beanfactoryInstance() {
		try {
			Resource r = new ClassPathResource("applicationContext.xml");
			factory = new XmlBeanFactory(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factory;
	}

	private static BeanFactory factory = null;

	public static BeanFactory getFactory() {
		return factory;
	}

}
