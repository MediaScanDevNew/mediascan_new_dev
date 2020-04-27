package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Crawl_youtube;;

public class Crawl_youtubeDao {
	HibernateTemplate template;
	
	private int id;
	private Integer user_id;
	private Integer project_type;
	private Integer client_id;
	private Integer project_id;
	private Integer source_type;
	private String filter;
	private String keyword;
	private String url_link;
	private String thumbnail_url;
	private String video_id;
	private String video_name;
	private Integer  view_count ;
	private String video_duration;
	private String channel_name;
	private String channel_id;
	private Integer channel_verified;
	private Integer subscriber_count;
	private Integer like_count;
	private Integer dislike_Count;
	private Integer comment_count;
	private String upload_date;
	private Integer share_count;
	private Integer favorite_count;
	private Integer modified_by;
	private Integer analysis_done;
	private Integer whitelist_validation;
	private Integer whitelist_matched;
	private String created_on;
	private String modified_on;
	private Integer deleted_flag;
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUrl_link() {
		return url_link;
	}

	public void setUrl_link(String url_link) {
		this.url_link = url_link;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getVideo_name() {
		return video_name;
	}

	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}



	public Integer getView_count() {
		return view_count;
	}

	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}

	public String getVideo_duration() {
		return video_duration;
	}

	public void setVideo_duration(String video_duration) {
		this.video_duration = video_duration;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public Integer getChannel_verified() {
		return channel_verified;
	}

	public void setChannel_verified(Integer channel_verified) {
		this.channel_verified = channel_verified;
	}

	public Integer getSubscriber_count() {
		return subscriber_count;
	}

	public void setSubscriber_count(Integer subscriber_count) {
		this.subscriber_count = subscriber_count;
	}

	public Integer getLike_count() {
		return like_count;
	}

	public void setLike_count(Integer like_count) {
		this.like_count = like_count;
	}

	public Integer getDislike_Count() {
		return dislike_Count;
	}

	public void setDislike_Count(Integer dislike_Count) {
		this.dislike_Count = dislike_Count;
	}

	public Integer getComment_count() {
		return comment_count;
	}

	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public Integer getShare_count() {
		return share_count;
	}

	public void setShare_count(Integer share_count) {
		this.share_count = share_count;
	}

	public Integer getFavorite_count() {
		return favorite_count;
	}

	public void setFavorite_count(Integer favorite_count) {
		this.favorite_count = favorite_count;
	}

	public Integer getModified_by() {
		return modified_by;
	}

	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
	}

	public Integer getAnalysis_done() {
		return analysis_done;
	}

	public void setAnalysis_done(Integer analysis_done) {
		this.analysis_done = analysis_done;
	}

	public Integer getWhitelist_validation() {
		return whitelist_validation;
	}

	public void setWhitelist_validation(Integer whitelist_validation) {
		this.whitelist_validation = whitelist_validation;
	}

	public Integer getWhitelist_matched() {
		return whitelist_matched;
	}

	public void setWhitelist_matched(Integer whitelist_matched) {
		this.whitelist_matched = whitelist_matched;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public Integer getDeleted_flag() {
		return deleted_flag;
	}

	public void setDeleted_flag(Integer deleted_flag) {
		this.deleted_flag = deleted_flag;
	}


	
	
	
	

	
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getProject_type() {
		return project_type;
	}

	public void setProject_type(Integer project_type) {
		this.project_type = project_type;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	public Integer getSource_type() {
		return source_type;
	}

	public void setSource_type(Integer source_type) {
		this.source_type = source_type;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	
	
	
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	public List<Crawl_youtube> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Crawl_youtube> dd = new ArrayList<>();
		dd = (List<Crawl_youtube>) template.find(query);
		System.out.println("oooo  size Client_masterDao  " + dd.size());
		return dd;
	}
	public String saveData(Crawl_youtube t) {
		String pj = null;
		try {
			 template.saveOrUpdate(t);
//			pj = ss.toString();
			// System.out.println("=== ss value----"+ss.toString());
			return pj;
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
			return pj;
		}
	}
	
	public int customUpdateProject(String query) { // for qc
		return template.bulkUpdate(query);

	}
	
}
