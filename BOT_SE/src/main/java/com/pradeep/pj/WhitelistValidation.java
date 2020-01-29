package com.pradeep.pj;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pradeep.pj.repo.Blacklist_sitesRepository;
import com.pradeep.pj.repo.Greylist_Repository;
import com.pradeep.pj.repo.Master_crawle_urlRepository;
import com.pradeep.pj.repo.Project_infoRepository;
import com.pradeep.pj.repo.Whitelist_Repository;

@Service
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
     *
     * @param projectId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public String whitelistChecking(int projectId) throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Project id is whitelist --------------->" + projectId);
        List<Integer> objSet = projInfo.getClientByProjectId(projectId);
        if (objSet.size() > 0) {
            Object s = objSet.get(0);
            int clientId = Integer.parseInt((String) s);
			System.out.println("-----------clientId------->"+clientId);
            String[] domains = wRepo.getClientwiseWhitelist(clientId);
			System.out.println("----length---->"+domains.length);
            List<Object[]> domainObj = crawlRepo.getDomainProjectWise(projectId);
//			ArrayList<Integer> ids=new ArrayList<Integer>();
            int count = 0;
            for (Object[] res : domainObj) {
                for (int i = 0; i < domains.length; i++) {
                    //@TODO:sometimes we got "biggboss10contestants.in" and "contestants.in" as a domian_name
                    // in 'master_crawle_url' table.But if any one of them is in 'whitelist' table then both are
                    // selected if we use 'contains' method.
                    if (domains[i].contains(res[1].toString())) {
                    	System.out.println("domain : "+domains[i]);
                    	System.out.println("url : "+res[1].toString());
                        crawlRepo.updateWhitelistedDomain(Integer.parseInt(res[0].toString()));
//						ids.add(Integer.parseInt(res[0].toString()));
                        break;
                    }
                }
            }
            crawlRepo.updateOtherStatus(projectId);
            return "done";
        } else {
            return "No Client is mapped with this project";
        }
    }

    /**
     * Author : Pentation
     * This method takes project id as a parameter and validated the related domain from
     * 'master_crawl_url' table with predefined blacklisted URL in 'blacklist' table.
     * It also stored a status against a row from the 'master_crawl_url' which are validated against the blacklist table.
     *
     * @param projectId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
	/*public String blacklistChecking(int projectId) throws JsonParseException, JsonMappingException, IOException, URISyntaxException{
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
		
	}*/
    public String blacklistChecking(int projectId) throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
        String[] domains = bRepo.findAllActiveBlacklists();
//			System.out.println("---domains----->"+domains.length);
        List<Object[]> domainObj = crawlRepo.getNotWhitelistedDomain(projectId);
//			String pro_name=projInfo.getNameByProjectId(projectId);
//			String search_key=pro_name.replace(" ","-");
        int count = 0;
        for (Object[] res : domainObj) {
//            System.out.println("Response ------------------>" + res);
            boolean blackListMatch = false;
            boolean propertyMatch = false;
            String msg = "None Matched";
            for (int i = 0; i < domains.length; i++) {
                if (domains[i].contains(res[1].toString())) {
                    blackListMatch = true;
                    msg = "Blacklist Matched";
                    break;
                }
            }
            String url = res[2].toString();
            url = URLEncoder.encode(url, "UTF-8");
            URI uri = new URI(url);
            String path = uri.getPath();
            String title = res[3].toString();
            String search_key = title.replace(" ", "-");
            if (path.toLowerCase().contains(search_key.toLowerCase())) {
                propertyMatch = true;
                msg = "Property Matched";
                System.out.println("-------->" + res[2].toString());
            }
            if (blackListMatch && propertyMatch) {
                msg = "Both Matched";
            }
            crawlRepo.updateBlacklistStatus(msg, Integer.parseInt(res[0].toString()));
        }
        return "done";

    }


    /**
     * Author : Pentation
     * This method takes project id as a parameter and validated the related domain from
     * 'master_crawl_url' table with predefined greylisted URL mapped with client in 'greylist' table.
     * It also marked all the rows from the 'master_crawl_url' which are passed through the greylist validation.
     *
     * @param projectId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public String greylistChecking(int projectId) throws JsonParseException, JsonMappingException, IOException {
    	System.out.println("Project id is greylist --------------->" + projectId);
    	List<Integer> objSet = projInfo.getClientByProjectId(projectId);
        if (objSet.size() > 0) {
            Object s = objSet.get(0);
            int clientId = Integer.parseInt((String) s);
            System.out.println("-----------clientId------->" + clientId);
            String[] domains = gRepo.getClientwiseGreylist(clientId);
            System.out.println("-----length--->" + domains.length);
            List<Object[]> domainObj = crawlRepo.getNotWhitelistedDomain(projectId);
            for (Object[] res : domainObj) {
                for (int i = 0; i < domains.length; i++) {
                    //@TODO:sometimes we got "biggboss10contestants.in" and "contestants.in" as a domian_name
                    // in 'master_crawle_url' table.But if any one of them is in 'whitelist' table then both are
                    // selected if we use 'contains' method.
                    if (domains[i].contains(res[1].toString())) {
                    	System.out.println("domain : "+domains[i]);
                    	System.out.println("url : "+res[1].toString());
                        crawlRepo.updateGreylistedDomain(Integer.parseInt(res[0].toString()));
//						ids.add(Integer.parseInt(res[0].toString()));
                        break;
                    }
                }
            }
            crawlRepo.updateGreyListStatus(projectId);
            return "done";
        } else {
            return "No Client is mapped with this project";
        }
    }


}
