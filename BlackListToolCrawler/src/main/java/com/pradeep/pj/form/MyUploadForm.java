/**
 * 
 */
package com.pradeep.pj.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author pradeep 18dec2017
 *
 */
public class MyUploadForm {

	/**
	 * 
	 */
	public MyUploadForm() {
		// TODO Auto-generated constructor stub
	}

	private String description;
	private int user_id, project_id;
	

	// Upload files.
	private MultipartFile fileDatas;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public MultipartFile getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile fileDatas) {
		this.fileDatas = fileDatas;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

}
