/**
 * 
 */
package com.pradeep.pj.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author pradeep
 *
 */
@Component
@PropertySource(value = "albelaLinks.properties")
public class AlbelaLinks {

	/**
	 * 
	 */
	public AlbelaLinks() {
		// TODO Auto-generated constructor stub
	}

	@Value("${spring.tellynagari}")
	private String tellyNagariLink;

	public String getTellyNagariLink() {
		return tellyNagariLink;
	}

	public void setTellyNagariLink(String tellyNagariLink) {
		this.tellyNagariLink = tellyNagariLink;
	}
	
	
}
