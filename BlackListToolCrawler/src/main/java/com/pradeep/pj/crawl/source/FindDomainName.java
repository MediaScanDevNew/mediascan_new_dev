/**
 * 
 */
package com.pradeep.pj.crawl.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

/**
 * @author pradeep
 *
 */
@Service
@Configurable
public class FindDomainName {
	private static final Logger logger = LoggerFactory.getLogger(FindDomainName.class);
	/**
	 * 
	 */
	public FindDomainName() {
		// TODO Auto-generated constructor stub
	}

	public String findDomain(String ddomain) {
		String domain__c = "";

		try {

			if (ddomain.startsWith("https:")) {
				domain__c = ddomain.replace("https://", "");
				domain__c = domain__c.replace("www.", "");
				// .println("domain===1..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					pj = null;
					pj1 = null;
					// .println("domain===4.1.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null;
					// .println("domain===4.112.." + domain__c);
				}

			} else if (ddomain.startsWith("http:")) {
				domain__c = ddomain.replace("http://", "");
				domain__c = domain__c.replace("www.", "");
				// .println("domain===2..." + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					pj = null;
					pj1 = null; // .println("domain===4.2.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null; // .println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("//www.")) {
				domain__c = ddomain.replace("//www.", "");
				domain__c = domain__c.replace("www.", ""); // .println("domain===2..."
															// + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					
					domain__c = pj[0]; // .println("domain===4.3.." +
										// domain__c);
					pj = null;
					pj1 = null;
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null; // .println("domain===4.113.." + domain__c);
				}
			} else if (ddomain.startsWith("www")) { // domain__c =
													// ddomain.replace("//www.",
													// "");
				domain__c = ddomain.replace("www.", ""); // .println("domain===2..."
															// + domain__c);
				if (domain__c.contains(":")) {
					String pj1[] = domain__c.split(":");

					String pj[] = pj1[0].split("/");
					domain__c = pj[0];
					pj = null;
					pj1 = null; // .println("domain===4.33.." + domain__c);
				} else {
					String pj[] = domain__c.split("/");
					domain__c = pj[0];
					pj = null; // .println("domain===4.114.." + domain__c);
				}
			}

			else if (!ddomain.startsWith("http:") || !ddomain.startsWith("htts:") || !ddomain.startsWith("www.")
					|| !ddomain.startsWith("//www.")) {
				String pj[] = ddomain.split("/");
				domain__c = pj[0];
				pj = null;
				// .println("domain===4.114.." + domain__c);
			}

		} catch (Exception e) { // e.printStackTrace();
			logger.error("find domain error... ", e);
		}

		return domain__c;
	}
}
