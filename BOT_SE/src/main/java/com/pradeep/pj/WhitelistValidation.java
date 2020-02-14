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
import com.pradeep.pj.repo.Client_MasterRepository;
import com.pradeep.pj.repo.Crawle_url4Repository;
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
    @Autowired
    private Crawle_url4Repository url4Repo;
    @Autowired
    private Client_MasterRepository clientRepo;

    public static final String BOTH_MATCHED="Both Matched";
    public static final String NONE_MATCHED="None Matched";
    public static final String Property_MATCHED="Property Matched";
    public static final String BLACKLIST_MATCHED="Blacklist Matched";
    public static final String GREY_MATCHED="Greylist Matched";

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
//            String[] domains = wRepo.getClientwiseWhitelist(clientId);
			List<Object[]> allDomains=wRepo.getAllWhitelist();
//			System.out.println("----length---->"+domains.length);
            List<Object[]> domainObj = crawlRepo.getDomainProjectWise(projectId);
//			ArrayList<Integer> ids=new ArrayList<Integer>();
            int count = 0;
            /*
             * pentation/m on 05/02/20
             */
            for (Object[] res : domainObj) {
            	for(Object[] wRes:allDomains){
            		if(wRes[0].toString().contains(res[1].toString())){
            			if(clientId == Integer.parseInt(wRes[1].toString())){
            				crawlRepo.updateWhitelistedDomain(Integer.parseInt(res[0].toString()));
            			}else{
            				List<Object[]> clientInfo=clientRepo.AllDataCustom(Integer.parseInt(wRes[0].toString()));
            				for (Object[] client : clientInfo) {
            					crawlRepo.updateWhitelistedDomainWithStatus("Whitelist Matched with "+client[0].toString(),Integer.parseInt(res[0].toString()));
            				}
            			}
                        break;
            		}
            	}
                /*for (int i = 0; i < domains.length; i++) {
                    //@TODO:sometimes we got "biggboss10contestants.in" and "contestants.in" as a domian_name
                    // in 'master_crawle_url' table.But if any one of them is in 'whitelist' table then both are
                    // selected if we use 'contains' method.
                    if (domains[i].contains(res[1].toString())) {
                    	System.out.println("domain : "+domains[i]);
                    	System.out.println("url : "+res[1].toString());
                        crawlRepo.updateWhitelistedDomain(Integer.parseInt(res[0].toString()));
                        break;
                    }
                }*/
            	
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
//        String[] domains = bRepo.findAllActiveBlacklists();
//			System.out.println("---domains----->"+domains.length);
        List<Object[]> domainObj = crawlRepo.getNotWhitelistedDomain(projectId);
        List<Object[]> proj_details=projInfo.getNameByProjectId(projectId);
        String pro_name=null;
        int project_type=0;
        for (Object[] res1 : proj_details) {
        	pro_name=res1[0].toString();
        	project_type=Integer.parseInt(res1[1].toString());
        }
        String[] domains = bRepo.findAllActiveBlacklistsTypeWise(project_type);
//			String search_key=pro_name.replace(" ","-");
        int count = 0;
        for (Object[] res : domainObj) {
//            System.out.println("Response ------------------>" + res);
            boolean blackListMatch = false;
            boolean propertyMatch = false;
            String msg = NONE_MATCHED;
            for (int i = 0; i < domains.length; i++) {
                if (domains[i].contains(res[1].toString())) {
                    blackListMatch = true;
                    msg = BLACKLIST_MATCHED;
                    break;
                }
            }
//            String url = res[2].toString();
//            url = URLEncoder.encode(url, "UTF-8");
//            URI uri = new URI(url);
//            String path = uri.getPath();
            if(res[3] != null){
	            String title = res[3].toString();
//	            String search_key = title.replace(" ", "-");
	            if (title.toLowerCase().contains(pro_name.toLowerCase())) {
	                propertyMatch = true;
	                msg = Property_MATCHED;
	                System.out.println("-------->" + res[2].toString());
	            }
            }else{
            	System.out.println("---no title----->" + res[2].toString());
            }
            if (blackListMatch && propertyMatch) {
                msg = BOTH_MATCHED;
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
                    	/*
                    	 * pentation/m on 05/02/20
                    	 */
                        crawlRepo.updateGreylistedDomain(GREY_MATCHED,Integer.parseInt(res[0].toString()));
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
    
    /**
     * Author : Pentation/M on 06/02/20
     * This method takes project id as a parameter and get all the blacklisted urls with 
     * status "Both Matched" from master_crawl_url.Then Inserted the url in 'crawle_url4' 
     * table
     * @param projectId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public String insertIntoCrawl(int projectId) throws JsonParseException, JsonMappingException, IOException {
    	System.out.println("Insert into crawle table --------------->" + projectId);
    	List<Object[]> objSet = crawlRepo.getStatusWiseDomain(projectId,BOTH_MATCHED);
        if (objSet.size() > 0) {
        	for (Object[] res : objSet) {
        		String lnk=res[0].toString();
        		int uid= Integer.parseInt(res[1].toString());
        		int prid=Integer.parseInt(res[2].toString());
        		String domain=res[3].toString();
        		int ppid=Integer.parseInt(res[4].toString());
        		int wlist =Integer.parseInt(res[5].toString());
        		String page_no=res[6].toString();
        		String page_rank=res[7].toString();
        		url4Repo.saveData(lnk, prid, uid, wlist, ppid, domain, page_no, page_rank);
        	}
            return "done";
        } else {
        	System.out.println("....No Status Matched....");
            return "done";
        }
    }

}
