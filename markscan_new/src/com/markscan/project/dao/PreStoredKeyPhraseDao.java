/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import com.markscan.project.beans.PreStoredKeyPhrase;

/**
 * @author pradeep
 *
 */
public class PreStoredKeyPhraseDao {

	/**
	 * 
	 */
	public PreStoredKeyPhraseDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<PreStoredKeyPhrase> viewRecord(String query) {
		// template.setMaxResults(x);
		List<PreStoredKeyPhrase> dd = new ArrayList<>();
		dd = (List<PreStoredKeyPhrase>) template.find(query);
		System.out.println("oooo  size PreStoredKeyPhraseDao  " + dd.size());
		return dd;
	}
}
