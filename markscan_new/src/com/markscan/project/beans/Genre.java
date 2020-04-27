package com.markscan.project.beans;

/**
 * created on 14/02/20
 * @author pentation/m
 *
 */
public class Genre {
	
	int id,project_type;
	String language,genre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProject_type() {
		return project_type;
	}
	public void setProject_type(int project_type) {
		this.project_type = project_type;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
