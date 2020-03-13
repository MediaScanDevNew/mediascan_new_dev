/**
 * 
 */
package com.pradeep.pj.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.pradeep.pj.crawl.source.FindDomainName;
import com.pradeep.pj.crawl.source.MyClass;
import com.pradeep.pj.crawl.source.PageSource;
import com.pradeep.pj.crawl.source.SourceLinkExtracter;
import com.pradeep.pj.form.MyUploadForm;
import com.pradeep.pj.model.Crawle_url4;
import com.pradeep.pj.model.IWLDataBean;
import com.pradeep.pj.model.Infringing_source;
import com.pradeep.pj.property.AlbelaLinks;
import com.pradeep.pj.repo.Crawle_url4Repository;
import com.pradeep.pj.repo.impl.Crawle_url4RepoIMPL;
import com.pradeep.pj.repo.impl.IWLDataProcess;
import com.pradeep.pj.repo.impl.InfringingSourceRepositoryIMPL;

/**
 * @author pradeep 18dec2017
 *
 */
//@Controller
@RestController
public class CrawleController {
	private static final Logger logger = LoggerFactory.getLogger(CrawleController.class);
	HttpGet httpget = null;
	/**
	 * 
	 */
	public CrawleController() {
		// TODO Auto-generated constructor stub
	}
	
//	@Autowired
//	private Crawle_url4Repository crawleRepo;
	
	//@Autowired
	//private InfringingSourceRepositoryIMPL isri;

	@Autowired
	private AlbelaLinks propFile;

	/*@Autowired
	private FindDomainName domain_name__c;*/

	/*@Autowired
	private SourceLinkExtracter srcExtractor__c;*/
	
	/*@Autowired
	private Crawle_url4RepoIMPL cui;*/
	
	

	/**
	 * ==============================
	 */

	// GET: Show upload form page.
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String uploadOneFileHandler(Model model) {

		MyUploadForm myUploadForm = new MyUploadForm();
		model.addAttribute("myUploadForm", myUploadForm);

		return "uploadOneFile";
	}
	
	
	// POST: Do Upload
		@RequestMapping(value = "/", method = RequestMethod.POST)
		public String uploadOneFileHandlerPOST(HttpServletRequest request, 
				Model model, 
				@ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
			System.out.println		("----------------inside-----------------");
			return this.doUpload(request, model, myUploadForm);

		}*/
	
  /**
   *  New GET method API implementation by Pentation/M for automated IWL Enginee.....
   *  Fetch record from master_crawle_url for every project...
 * @throws UnknownHostException 
   * */
	
	@RequestMapping(value = "/startIWL", method = RequestMethod.GET)
	public String uploadFileHandler(@RequestParam("projectIds") String projectIds, @RequestParam("flag") int flag ) throws UnknownHostException {
		
		ArrayList<IWLDataBean> iwl_list = new ArrayList<IWLDataBean>();
		String msg = "done";
		System.out.println("project id is ------->"+projectIds);
		System.out.println("flag is ------->"+flag);
		try {
			String[] ids=projectIds.split(",");
			for( int i=0;i<ids.length;i++){
			iwl_list = new IWLDataProcess().DataProcess(Integer.parseInt(ids[i]));
			System.out.println("iwl_list:--->"+iwl_list.size());
			if(!iwl_list.isEmpty()){
				for(IWLDataBean bn : iwl_list){
					if (bn.getCrawle_url2().toLowerCase().contains(propFile.getTellyNagariLink().toLowerCase())){
						tellynagariLink(bn.getCrawle_url2(), bn.getKeyphase(), bn.getUser_id(), InetAddress.getLocalHost().getHostAddress(), bn.getProject_id());
					}else{
						linkCrowle(bn.getCrawle_url2(), bn.getKeyphase(), bn.getUser_id(),InetAddress.getLocalHost().getHostAddress(), bn.getProject_id());
					}
				}
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return msg;
		}

		return msg;
	}

	

	String uploadedFiles = null;
	String description = null;
	String uploadRootPath = null;
	String failedFiles = null;
	File uploadRootDir = null;
	MultipartFile fileDatas = null;
	String name = null;

	private String doUpload(HttpServletRequest request, Model model, 
			MyUploadForm myUploadForm) {

		logger.info(propFile.getTellyNagariLink());

		description = myUploadForm.getDescription();
		System.out.println("Description: " + description);

		// Root Directory.
		uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);

		uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			//System.out.println("11111111111111111111111111111111111" );
			uploadRootDir.mkdirs();
		}
		fileDatas = myUploadForm.getFileDatas();
		//
		// Client File Name
		name = fileDatas.getOriginalFilename();
		System.out.println("Client File Name = " + name);
		result = new ArrayList<>();
		if (name != null && name.length() > 0) {
			try {
				// Create the file at server
				serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

				stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileDatas.getBytes());
				stream.close();
				//
				uploadedFiles = serverFile.getAbsolutePath();
				System.out.println("Write file: " + serverFile);

				/**
				 * === read csv file
				 */
				report = new ArrayList<>();

				try {
					reader = new CSVReader(new FileReader(serverFile));
					//System.out.println("reader2222222222222222222222 = " + reader);
					while ((line = reader.readNext()) != null) {
						i_s = new Infringing_source();
						keyword = null;
						System.out.println(line[0] + "============= " + line[1]);
						keyword = line[1].trim();
						i_s.setInfringing_link(line[0]);
						// store data on database here projectid shows no of
						// infringing link crawl
						
						System.out.println("prop");
						
						if (line[0].toLowerCase().contains(propFile.getTellyNagariLink().toLowerCase())) {
							i_s.setProjectid(tellynagariLink(line[0].trim(), line[1].trim(), myUploadForm.getUser_id(),
									InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(line[2].trim())));
						} else {
							i_s.setProjectid(linkCrowle(line[0].trim(), line[1].trim(), myUploadForm.getUser_id(),
									InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(line[2].trim())));
						}
						i_s.setSearch_keyword(line[1]);
						i_s.setRow_in_use(source_link_size);
						// display result on screen
						result.add(i_s);
						i_s = null;
					}

				} catch (IOException e) {
					error = "either userID or projectID is missing, try again.";
					e.printStackTrace();
				} finally {
					reader.close();
				}

			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
				failedFiles = name;
				e.printStackTrace();
			} finally {
				i_s = null;
				serverFile = null;
				stream = null;
				reader = null;
				line = null;
				uploadRootPath = null;
				uploadRootDir = null;
				fileDatas = null;
				name = null;
				keyword = null;
			}

		}
		model.addAttribute("description", description);
		model.addAttribute("uploadedFiles", uploadedFiles);
		model.addAttribute("failedFiles", failedFiles);
		model.addAttribute("Error", error);
		model.addAttribute("Result", result);
		return "uploadResult";
	}
	
	String keyword = null;
	List<Infringing_source> report = null;
	List<Infringing_source> result = null;
	Infringing_source i_s = null;
	File serverFile = null;
	BufferedOutputStream stream = null;
	CSVReader reader = null;
	String[] line;
	String error = null;
	int link_size = 0;
	int source_link_size = 0;
	// String domain = null;

	public Integer linkCrowle(String URL, String keyword, int userid, String ipadd, int projectid) throws SQLException {
		sourceLink = "";
		link_size = 0;
		source_link_size = 0;
		Crawle_url4RepoIMPL cui = new Crawle_url4RepoIMPL();
		FindDomainName domain_name__c = new FindDomainName();
		SourceLinkExtracter srcExtractor__c = new SourceLinkExtracter();
		InfringingSourceRepositoryIMPL isri = new InfringingSourceRepositoryIMPL(); 
		try {
			System.out.println("url:--->"+URL);
			doc = Jsoup.parse(html2Doc(URL));

			// get all links and recursively call the processPage method
			links = doc.select("a[href]");
			// domain = findDomain(URL);
			for (Element link : links) {
				Infringing_source is = new Infringing_source();
				Crawle_url4 c4=new Crawle_url4();
				if (link.text().toLowerCase().contains(keyword.toLowerCase())) {
					//logger.info(link.text() + "---------------" + link.attr("href"));
					sourceLink = srcExtractor__c.srcLink_Extractor(link.attr("href"));

					is.setInfringing_link_by_date(URL);
					is.setInfi_time(nowTime());
					is.setInfringing_link(link.attr("href"));
					c4.setLink_logger_srclink(link.attr("href"));
					is.setDomain(domain_name__c.findDomain(URL));
					c4.setDomain_name(domain_name__c.findDomain(URL));
					is.setUserid(userid);
					c4.setUser_id(userid);
					is.setProjectid(projectid);
					c4.setProject_id(projectid);
					is.setSearch_keyword(keyword);
					c4.setNote2(keyword);
					c4.setLink_logger(1);
					if (sourceLink.length() > 7 && !sourceLink.equals(null)) {
						is.setSource_link(sourceLink);
						c4.setCrawle_url2(sourceLink);
						is.setSource_domain(domain_name__c.findDomain(sourceLink));
						is.setSource_time(nowTime());
						is.setRow_in_use(2);
						source_link_size = source_link_size + 1;
					}else{
						c4.setCrawle_url2(link.attr("href"));
					}
					
					
					isri.addData(is);
					cui.addData(c4);
					
					
					link_size = link_size + 1;
					is = null;
					c4=null;
				}else{
					int url_id=cui.getIdByURL(URL);
					System.out.println("11==========>"+url_id);
					if(url_id>0){
						cui.updateIWLErrorField(url_id);
					}else{
						System.out.println("11==========>"+domain_name__c.findDomain(URL));
						new IWLDataProcess().saveData(URL, projectid, userid, 0,  domain_name__c.findDomain(URL), 1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			int url_id=cui.getIdByURL(URL);
			if(url_id>0){
				cui.updateIWLErrorField(url_id);
			}else{
				//cui.saveData(URL, projectid, userid, 0, domain_name__c.findDomain(URL), 1);
				System.out.println("11==========>"+domain_name__c.findDomain(URL));
				new IWLDataProcess().saveData(URL, projectid, userid, 0,  domain_name__c.findDomain(URL), 1);
			}
		} finally {
			doc = null;
			links = null;
		}
		return link_size;
	}

	Document doc = null;
	Elements links = null;

	public String nowTime() {
		String time__c = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			// .println(dateFormat.format(date));
			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);
			dateFormat = null;
			date = null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("date format error ...", e);
		}
		return time__c;
	}

	private String modifiedURL = "";
	private String pageSourceCode = null;
	private String sourceLink = null;

	public int tellynagariLink(String URL, String keyword, int userid, String ipadd, int projectid) throws SQLException {
		pageSourceCode = html2Doc(URL);
		sourceLink = "";
		link_size = 0;
		source_link_size = 0;
		Crawle_url4RepoIMPL cui = new Crawle_url4RepoIMPL();
		FindDomainName domain_name__c = new FindDomainName();
		SourceLinkExtracter srcExtractor__c = new SourceLinkExtracter();
		InfringingSourceRepositoryIMPL isri = new InfringingSourceRepositoryIMPL(); 
		try {
			//System.out.println("inside tellynagariLink-----------"+pageSourceCode);
			doc = Jsoup.parse(pageSourceCode);
			// get all links and recursively call the processPage method
			links = doc.select("a[onclick]");
			System.out.println("Links in telly----->"+links.size());
			for (Element link : links) {
				Infringing_source is = new Infringing_source();
				Crawle_url4 c4=new Crawle_url4();
				if (link.text().toLowerCase().contains(keyword.toLowerCase())) {
					// modifing URL by method urlModifier()
					modifiedURL = urlModifier(pageSourceCode,
							link.attr("onclick").replace("itm", "").replace("(", "").replace(")", "").replace("'", ""));
					
					sourceLink = srcExtractor__c.srcLink_Extractor(modifiedURL.trim());
					System.out.println("sourceLink----->"+sourceLink);
					
					is.setInfringing_link_by_date(URL); //crawle_url2
					is.setInfi_time(nowTime());
					is.setInfringing_link(modifiedURL);
					c4.setLink_logger_srclink(modifiedURL);
					is.setDomain(domain_name__c.findDomain(URL));
					c4.setDomain_name(domain_name__c.findDomain(URL));
					is.setUserid(userid);
					c4.setUser_id(userid);
					is.setProjectid(projectid);
					c4.setProject_id(projectid);
					is.setSearch_keyword(keyword);
					c4.setNote2(keyword);
					c4.setLink_logger(1);
					if (sourceLink.length() > 7 && !sourceLink.equals(null)) {
						is.setSource_link(sourceLink);
						c4.setCrawle_url2(sourceLink);
						is.setSource_domain(domain_name__c.findDomain(sourceLink));			
						is.setSource_time(nowTime());
						is.setRow_in_use(2);
						source_link_size = source_link_size + 1;
					}else{
						c4.setCrawle_url2(link.attr("href"));
					}
					
					
					
					isri.addData(is);
					cui.addData(c4);
					link_size = link_size + 1;
					is = null;
					c4=null;
				}else{
					int url_id=cui.getIdByURL(URL);
					if(url_id>0){
						cui.updateIWLErrorField(url_id);
					}else{
						cui.saveData(URL, projectid, userid, 0, domain_name__c.findDomain(URL), 1);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			int url_id=cui.getIdByURL(URL);
			if(url_id>0){
				cui.updateIWLErrorField(url_id);
			}else{
				cui.saveData(URL, projectid, userid, 0, domain_name__c.findDomain(URL), 1);
			}
		} finally {
			modifiedURL = null;
			links = null;
			doc = null;
			pageSourceCode = null;
		}
		return link_size;
	}

	Matcher m = null;
	Pattern p1 = null;
	String fileString = null;
	String modifyUrl = null;
	String session_id = null;;
	String one = null, two = null;

	public String urlModifier(String pPage, String uurl) {
		// System.out.println(uurl);
		fileString = pPage;
		try {
			fileString = fileString.replaceAll("\t", "");
			fileString = fileString.replaceAll("\n", "");
			p1 = Pattern.compile("function+\\sitm[(][a-zA-Z0-9]{3}[)]+\\s[{]"
					+ "+var+\\s[a-zA-Z0-9]{3}[=]\\s+[\"][a-zA-Z0-9]{8}[\"][;]"
					+ "var+\\s[a-zA-Z]{1}+\\s[=]+\\s[a-zA-Z0-9]{3}[.]replace[(][\"][a-zA-Z0-9]{6}[\"][,][\"][a-zA-Z0-9_]{8}[\"]"
					+ "[)][;]"
					+ "+\\s\\s\\s\\s[a-zA-Z]{1}+\\s[=]+\\s[a-zA-Z]{1}[.]replace[(][\"][a-zA-Z0-9]{5}[\"][,][\"][a-zA-Z]{7}[\"][)][;]"
					+ "\\s+\\s+\\s+\\svar+\\s[a-zA-Z]{1}+\\s[=]+\\s[a-zA-Z]{1}[.]indexOf[(][\"][a-zA-Z0-9=]{8}[\"][)][;]"
					+ "var+\\s[a-zA-z]{1}+\\s[=]+\\s[a-zA-Z]{1}[.]substring[(][0-9]{1}[,][a-zA-Z]{1}[)][;]"
					+ "var+\\s[a-zA-Z]{1}+\\s[=]+\\s[a-zA-Z]{1}[+][\"][a-zA-Z=]{8}[\"][+][\"][a-zA-Z0-9]{2}[\"][+][a-zA-Z0-9]"
					+ "{3}[+][\"][a-zA-z0-9]{2}[\"][+][a-zA-Z]{1}[.]substring[(][a-zA-Z0-9]{1}[+][0-9]{1}[)][;]"
					+ "window[.]open[(][']['][,]['][_]blank['][)][.]location.href[=][a-zA-Z0-9]{1}[;]+[}]");
			m = p1.matcher(fileString);
			m.find();
			if (m.find() != true) {
				p1 = Pattern.compile("(var+\\s[a-zA-Z0-9]{3}[=]\\s+[\"][a-zA-Z0-9]{8}[\"][;])");
				m = p1.matcher(fileString);
				m.find();
				session_id = m.group();
			}
			if (m.find() != true) {
				p1 = Pattern.compile(
						"var+\\s[a-zA-Z]{1}+\\s[=]+\\s[a-zA-Z]{1}[+][\"][a-zA-Z=]{8}[\"][+][\"][a-zA-Z0-9]{2}[\"][+][a-zA-Z0-9]"
								+ "{3}[+][\"][a-zA-z0-9]{2}[\"][+][a-zA-Z]{1}[.]substring[(][a-zA-Z0-9]{1}[+][0-9]{1}[)][;]");
				m = p1.matcher(fileString);
				m.find();
				String create = m.group();
				// System.out.println("--- create - - "+create);
				String ss[] = create.split("\"");
				// System.out.println(ss.length);
				// for(String d:ss)
				// {
				// System.out.println("---->>>"+d);
				// }
				one = ss[3];
				two = ss[5];
				create = null;
				ss = null;
			}

			// System.err.println("===session id == ="+session_id);
			session_id = session_id.replace("var", "").replace("rid", "").replace("=", "").replace("\"", "")
					.replace(";", "");
			session_id = session_id.trim();
			// System.out.println(uurl);

			String d = uurl.replace("docid", "session");
			d = d.replace("session1", "docid1");
			d = d.replace("session2", "docid2");
			d = d.replace("docid1", "refer_id");
			int ii = d.indexOf("session=");
			String l = d.substring(0, ii);
			modifyUrl = l + "session=" + one + session_id + two + d.substring(ii + 8);

			l = null;
			d = null;

			p1 = null;
			m = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			one = null;
			two = null;
			session_id = null;
			fileString = null;
			m = null;
			p1 = null;
			fileString = null;
			session_id = null;
			one = null;
			two = null;
		}

		return modifyUrl;
	}
	public String html2Doc(String uurl) {
		logger.info("---- link is ---" + uurl);
		String pageSource = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			httpget = new HttpGet(uurl.trim());

			logger.info("Executing request " + httpget.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};
			pageSource = httpclient.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			pageSource = "Unexpected response status: 404";
		} catch (Exception e) {
			httpget = null;
			e.printStackTrace();

		} finally {
			httpget = null;
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}return pageSource;
	}

}
