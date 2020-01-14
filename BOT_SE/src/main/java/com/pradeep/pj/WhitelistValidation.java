package com.pradeep.pj;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.pj.repo.Blacklist_sitesRepository;
import com.pradeep.pj.repo.Greylist_Repository;
import com.pradeep.pj.repo.Master_crawle_urlRepository;
import com.pradeep.pj.repo.Project_infoRepository;
import com.pradeep.pj.repo.Whitelist_Repository;


public class WhitelistValidation {
	@Autowired
	private Whitelist_Repository wRepo;
	@Autowired
	private Project_infoRepository projInfo;
	@Autowired
	private Master_crawle_urlRepository crawlRepo;
	@Autowired
	private Blacklist_sitesRepository bRepo;
	@Autowired
	private Greylist_Repository gRepo;
	
	
	/**
	 * Author : Pentation 
	 * This method takes project id as a parameter and validated the related domain from  
	 * 'master_crawl_url' table with predefined whitelisted URL mapped with client in 'whitelist' table.
	 * It also marked all the rows from the 'master_crawl_url' which are passed through the whitelist validation.
	 * @param projectId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String whitelistChecking(int projectId) throws JsonParseException, JsonMappingException, IOException{
		List<Integer> objSet = projInfo.getClientByProjectId(projectId);
		if(objSet.size()>0){
			Object s = objSet.get(0);
			int clientId = Integer.parseInt((String)s);
//			System.out.println("-----------clientId------->"+clientId);
			String[] domains=wRepo.getClientwiseWhitelist(clientId);
//			System.out.println("-------->"+domains.length);
			List<Object[]> domainObj =crawlRepo.getDomainProjectWise(projectId);
//			ArrayList<Integer> ids=new ArrayList<Integer>();
			int count =0;
			for( Object [] res :domainObj){
				for(int i=0;i<domains.length;i++){
					//@TODO:sometimes we got "biggboss10contestants.in" and "contestants.in" as a domian_name 
					// in 'master_crawle_url' table.But if any one of them is in 'whitelist' table then both are
					// selected if we use 'contains' method.
					if(domains[i].contains(res[1].toString())){
						crawlRepo.updateWhitelistedDomain(Integer.parseInt(res[0].toString()));
//						ids.add(Integer.parseInt(res[0].toString()));
						break;
					}
				}
			}
			crawlRepo.updateOtherStatus(projectId);
			return "done";
		}else{
			return "No Client is mapped with this project";
		}
	}
	
	/**
	 * Author : Pentation 
	 * This method takes project id as a parameter and validated the related domain from  
	 * 'master_crawl_url' table with predefined blacklisted URL in 'blacklist' table.
	 * It also stored a status against a row from the 'master_crawl_url' which are validated against the blacklist table.
	 * @param projectId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String blacklistChecking(int projectId) throws JsonParseException, JsonMappingException, IOException, URISyntaxException{
		String[] domains=bRepo.findAllActiveBlacklists();
//		System.out.println("---domains----->"+domains.length);
		List<Object[]> domainObj =crawlRepo.getNotWhitelistedDomain(projectId);
		String pro_name=projInfo.getNameByProjectId(projectId);
		String search_key=pro_name.replace(" ","-");
		int count =0;
		for( Object [] res :domainObj){
			boolean blackListMatch =false;
			boolean propertyMatch =false;
			String msg="None Matched";
			for(int i=0;i<domains.length;i++){
				if(domains[i].contains(res[1].toString())){
					blackListMatch=true;
					msg="Blacklist Matched";
					break;
				}
			}
			URI uri = new URI(res[2].toString());
			String path= uri.getPath();
			if(path.toLowerCase().contains(search_key.toLowerCase())){
				propertyMatch=true;
				msg="Property Matched";
				System.out.println("-------->"+res[2].toString());
			}
			if(blackListMatch && propertyMatch){
				msg="Both Matched";
			}
			crawlRepo.updateBlacklistStatus(msg,Integer.parseInt(res[0].toString()));
		}
		return "done";
		
	}
	/**
	 * Author : Pentation 
	 * This method takes project id as a parameter and validated the related domain from  
	 * 'master_crawl_url' table with predefined greylisted URL mapped with client in 'greylist' table.
	 * It also marked all the rows from the 'master_crawl_url' which are passed through the greylist validation.
	 * @param projectId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String greylistChecking(int projectId) throws JsonParseException, JsonMappingException, IOException{
		List<Integer> objSet = projInfo.getClientByProjectId(projectId);
		if(objSet.size()>0){
			Object s = objSet.get(0);
			int clientId = Integer.parseInt((String)s);
			System.out.println("-----------clientId------->"+clientId);
			String[] domains=gRepo.getClientwiseGreylist(clientId);
			System.out.println("-------->"+domains.length);
			List<Object[]> domainObj =crawlRepo.getNotWhitelistedDomain(projectId);
			for( Object [] res :domainObj){
				for(int i=0;i<domains.length;i++){
					//@TODO:sometimes we got "biggboss10contestants.in" and "contestants.in" as a domian_name 
					// in 'master_crawle_url' table.But if any one of them is in 'whitelist' table then both are
					// selected if we use 'contains' method.
					if(domains[i].contains(res[1].toString())){
						crawlRepo.updateGreylistedDomain(Integer.parseInt(res[0].toString()));
//						ids.add(Integer.parseInt(res[0].toString()));
						break;
					}
				}
			}
			crawlRepo.updateGreyListStatus(projectId);
			return "done";
		}else{
			return "No Client is mapped with this project";
		}
	}

	

}
