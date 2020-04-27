/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Productivity_user_wise {

	/**
	 * 
	 */
	public Productivity_user_wise() {
		// TODO Auto-generated constructor stub
	}

	int id, user_id, total_link, infringing_link, source_link, invalid_link, duplicate, deleted, white_list, grey_list,
			youtube, social_media, facebook, instagram, twitter, vk, periscope, source_domain,infringing_domain;
	String curr_date = null;
	

	

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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTotal_link() {
		return total_link;
	}

	public void setTotal_link(int total_link) {
		this.total_link = total_link;
	}

	public int getInfringing_link() {
		return infringing_link;
	}

	public void setInfringing_link(int infringing_link) {
		this.infringing_link = infringing_link;
	}

	public int getSource_link() {
		return source_link;
	}

	public void setSource_link(int source_link) {
		this.source_link = source_link;
	}

	public int getInvalid_link() {
		return invalid_link;
	}

	public void setInvalid_link(int invalid_link) {
		this.invalid_link = invalid_link;
	}

	public int getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(int duplicate) {
		this.duplicate = duplicate;
	}

	public String getCurr_date() {
		return curr_date;
	}

	public void setCurr_date(String curr_date) {
		this.curr_date = curr_date;
	}

}
