package com.markscan.project.hbm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sess;

	public static final SessionFactory getSessionFactory() {
		if (sess == null) {
			
			synchronized (SessionFactory.class) {
				if (sess == null)
//					sess = new Configuration().configure().buildSessionFactory();
					sess = new AnnotationConfiguration().configure().buildSessionFactory();
			}
		}
		return sess;
	}

	public static SessionFactory getSess() {
		return sess;
	}

}