package com.markscan.project.beans;

import java.util.List;

public class DashBoardData {

	int source, Infringing, invalid, link_count, white_list, grey_list, deleted, youtube, social_media, duplicate,
			facebook, instagram, twitter, vk, periscope, source_domain, infringing_domain, user_id;
	String date, proj_name, Client_name, property_ids;
	List<Crawle_url2> inside;
	

	
	public List<Crawle_url2> getInside() {
		return inside;
	}

	public void setInside(List<Crawle_url2> inside) {
		this.inside = inside;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getClient_name() {
		return Client_name;
	}

	public void setClient_name(String client_name) {
		Client_name = client_name;
	}

	public String getProperty_ids() {
		return property_ids;
	}

	public void setProperty_ids(String property_ids) {
		this.property_ids = property_ids;
	}

	public int getSource_domain() {
		return source_domain;
	}

	public void setSource_domain(int source_domain) {
		this.source_domain = source_domain;
	}

	public int getInfringing_domain() {
		return infringing_domain;
	}

	public void setInfringing_domain(int infringing_domain) {
		this.infringing_domain = infringing_domain;
	}

	public int getFacebook() {
		return facebook;
	}

	public void setFacebook(int facebook) {
		this.facebook = facebook;
	}

	public int getInstagram() {
		return instagram;
	}

	public void setInstagram(int instagram) {
		this.instagram = instagram;
	}

	public int getTwitter() {
		return twitter;
	}

	public void setTwitter(int twitter) {
		this.twitter = twitter;
	}

	public int getVk() {
		return vk;
	}

	public void setVk(int vk) {
		this.vk = vk;
	}

	public int getPeriscope() {
		return periscope;
	}

	public void setPeriscope(int periscope) {
		this.periscope = periscope;
	}

	public int getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(int duplicate) {
		this.duplicate = duplicate;
	}

	public int getYoutube() {
		return youtube;
	}

	public void setYoutube(int youtube) {
		this.youtube = youtube;
	}

	public int getSocial_media() {
		return social_media;
	}

	public void setSocial_media(int social_media) {
		this.social_media = social_media;
	}

	public int getWhite_list() {
		return white_list;
	}

	public void setWhite_list(int white_list) {
		this.white_list = white_list;
	}

	public int getGrey_list() {
		return grey_list;
	}

	public void setGrey_list(int grey_list) {
		this.grey_list = grey_list;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getInfringing() {
		return Infringing;
	}

	public void setInfringing(int infringing) {
		Infringing = infringing;
	}

	public int getInvalid() {
		return invalid;
	}

	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}

	public int getLink_count() {
		return link_count;
	}

	public void setLink_count(int link_count) {
		this.link_count = link_count;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

}
