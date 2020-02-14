/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */

public class Keyword_filter_extension_master {

	/**
	 * 
	 */
	public Keyword_filter_extension_master() {
		// TODO Auto-generated constructor stub
	}


	int id;
	int projectType;
	String keyphrase, created_on;

	public int getId() {
		return id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public String getKeyphrase() {
		return keyphrase;
	}

	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}

}
