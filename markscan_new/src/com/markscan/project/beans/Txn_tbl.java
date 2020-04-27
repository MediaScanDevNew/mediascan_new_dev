package com.markscan.project.beans;

public class Txn_tbl {

	int id, PROJECT_ID;
	String INFRINGING_URL, DOMAIN_NAME;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(int pROJECT_ID) {
		PROJECT_ID = pROJECT_ID;
	}

	public String getINFRINGING_URL() {
		return INFRINGING_URL;
	}

	public void setINFRINGING_URL(String iNFRINGING_URL) {
		INFRINGING_URL = iNFRINGING_URL;
	}

	public String getDOMAIN_NAME() {
		return DOMAIN_NAME;
	}

	public void setDOMAIN_NAME(String dOMAIN_NAME) {
		DOMAIN_NAME = dOMAIN_NAME;
	}

}
