package com.pradeep.pj;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.crawl.source.FindDomainName;
import com.pradeep.pj.crawl.source.PageSource;
import com.pradeep.pj.crawl.source.SourceLinkExtracter;
import com.pradeep.pj.model.IWLDataBean;
import com.pradeep.pj.model.Infringing_source;
import com.pradeep.pj.repo.impl.IWLDataProcess;
import com.pradeep.pj.repo.impl.InfringingSourceRepositoryIMPL;

@Service
public class IWLEngine {
	@Autowired
	private PageSource pageSource__c;
	@Autowired
	private SourceLinkExtracter srcExtractor__c;
	@Autowired
	private InfringingSourceRepositoryIMPL isri;
	@Autowired
	private FindDomainName domain_name__c;
	
	int link_size = 0;
	int source_link_size = 0;
	Matcher m = null;
	Pattern p1 = null;
	String fileString = null;
	String modifyUrl = null;
	String session_id = null;;
	String one = null, two = null;
	private String modifiedURL = "";
	private String pageSourceCode = null;
	private String sourceLink = null;
	Document doc = null;
	Elements links = null;
	
	public String iwlEngine(int projectId){
		ArrayList<IWLDataBean> iwl_list = new ArrayList<IWLDataBean>();
		String msg = "done";
		System.out.println("project id is ------->"+projectId);
		try {
			iwl_list = new IWLDataProcess().DataProcess(projectId);
			System.out.println("iwl_list:--->"+iwl_list.size());
			if(!iwl_list.isEmpty()){
				for(IWLDataBean bn : iwl_list){
				   int val = tellynagariLink(bn.getCrawle_url2(), bn.getKeyphase(), bn.getUser_id(), "1", bn.getProject_id());
				   /*if(val > 0){
				   }*/
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return msg;
		}
		return msg;
	}
	public String nowTime() {
		String time__c = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			// .println(dateFormat.format(date));
//			logger.info("date format.." + dateFormat.format(date));
			time__c = dateFormat.format(date);
			dateFormat = null;
			date = null;
		} catch (Exception e) {
			// e.printStackTrace();
//			logger.error("date format error ...", e);
		}
		return time__c;
	}
	
	
	public int tellynagariLink(String URL, String keyword, int userid, String ipadd, int projectid) {
		pageSourceCode = pageSource__c.html2Doc(URL);
		sourceLink = "";
		link_size = 0;
		source_link_size = 0;
		try {
			System.out.println("inside tellynagariLink-----------");
			doc = Jsoup.parse(pageSourceCode);
			// get all links and recursively call the processPage method
			links = doc.select("a[onclick]");
			System.out.println("Links in telly----->"+links.size());
			for (Element link : links) {
				Infringing_source is = new Infringing_source();
				if (link.text().toLowerCase().contains(keyword.toLowerCase())) {
					// modifing URL by method urlModifier()
					modifiedURL = urlModifier(pageSourceCode,
							link.attr("onclick").replace("itm", "").replace("(", "").replace(")", "").replace("'", ""));
					
					sourceLink = srcExtractor__c.srcLink_Extractor(modifiedURL.trim());
					System.out.println("sourceLink----->"+sourceLink);
					is.setInfringing_link_by_date(URL);
					is.setInfi_time(nowTime());
					is.setInfringing_link(modifiedURL);
					is.setDomain(domain_name__c.findDomain(URL));
					is.setUserid(userid);
					is.setProjectid(projectid);
					is.setSearch_keyword(keyword);

					if (sourceLink.length() > 7 && !sourceLink.equals(null)) {
						is.setSource_link(sourceLink);
						is.setSource_domain(domain_name__c.findDomain(sourceLink));
						is.setSource_time(nowTime());
						is.setRow_in_use(2);
						source_link_size = source_link_size + 1;
					}
					isri.addData(is);

					link_size = link_size + 1;
					is = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			modifiedURL = null;
			links = null;
			doc = null;
			pageSourceCode = null;
		}
		return link_size;
	}
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

}
