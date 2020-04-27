/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Master_crawle_url {

	/**
	 * 
	 */
	public Master_crawle_url() {
		// TODO Auto-generated constructor stub
	}

	int id, stored_project_setup_id, df_perform, is_valid, user_id, project_id, pipe_id, is_new, url_new, run_id,
			link_logger, metatags_filter_new, meta_tags_new, meta_new,w_list;
	String crawle_url2, machine, created_on, url_query, domain_name, url_status, surl_status,projectName,page_no,page_rank,
	keyphrase,status,searchEngine;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getId() {
		return id;
	}

	public int getW_list() {
		return w_list;
	}

	public void setW_list(int w_list) {
		this.w_list = w_list;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStored_project_setup_id() {
		return stored_project_setup_id;
	}

	public void setStored_project_setup_id(int stored_project_setup_id) {
		this.stored_project_setup_id = stored_project_setup_id;
	}

	public int getDf_perform() {
		return df_perform;
	}

	public void setDf_perform(int df_perform) {
		this.df_perform = df_perform;
	}

	public int getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(int is_valid) {
		this.is_valid = is_valid;
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

	public int getPipe_id() {
		return pipe_id;
	}

	public void setPipe_id(int pipe_id) {
		this.pipe_id = pipe_id;
	}

	public int getIs_new() {
		return is_new;
	}

	public void setIs_new(int is_new) {
		this.is_new = is_new;
	}

	public int getUrl_new() {
		return url_new;
	}

	public void setUrl_new(int url_new) {
		this.url_new = url_new;
	}

	public int getRun_id() {
		return run_id;
	}

	public void setRun_id(int run_id) {
		this.run_id = run_id;
	}

	public int getLink_logger() {
		return link_logger;
	}

	public void setLink_logger(int link_logger) {
		this.link_logger = link_logger;
	}

	public int getMetatags_filter_new() {
		return metatags_filter_new;
	}

	public void setMetatags_filter_new(int metatags_filter_new) {
		this.metatags_filter_new = metatags_filter_new;
	}

	public int getMeta_tags_new() {
		return meta_tags_new;
	}

	public void setMeta_tags_new(int meta_tags_new) {
		this.meta_tags_new = meta_tags_new;
	}

	public int getMeta_new() {
		return meta_new;
	}

	public void setMeta_new(int meta_new) {
		this.meta_new = meta_new;
	}

	public String getCrawle_url2() {
		return crawle_url2;
	}

	public void setCrawle_url2(String crawle_url2) {
		this.crawle_url2 = crawle_url2;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getUrl_query() {
		return url_query;
	}

	public void setUrl_query(String url_query) {
		this.url_query = url_query;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getUrl_status() {
		return url_status;
	}

	public void setUrl_status(String url_status) {
		this.url_status = url_status;
	}

	public String getSurl_status() {
		return surl_status;
	}

	public void setSurl_status(String surl_status) {
		this.surl_status = surl_status;
	}

	public String getPage_no() {
		return page_no;
	}

	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}

	public String getPage_rank() {
		return page_rank;
	}

	public void setPage_rank(String page_rank) {
		this.page_rank = page_rank;
	}

	public String getKeyphrase() {
		return keyphrase;
	}

	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}
	
	
	
	
	@Override
    public String toString() {
        return "Master_crawle_url [id=" + id + ", crawle_url2=" + crawle_url2 + ",created_on="+created_on+","
        		+ "user_id="+user_id+",projectName="+projectName+",page_no="+page_no+",page_rank="+page_rank+","
        	    + "keyphrase="+keyphrase+",searchEngine="+searchEngine+",status="+status+"]";
    }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Master_crawle_url other = (Master_crawle_url) obj;
        if (crawle_url2 == null) {
            if (other.crawle_url2 != null)
                return false;
        } else if (!crawle_url2.equals(other.crawle_url2))
            return false;
      return true;
    }
	
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((crawle_url2 == null) ? 0 : crawle_url2.hashCode());
        return result;
    }

	

}
