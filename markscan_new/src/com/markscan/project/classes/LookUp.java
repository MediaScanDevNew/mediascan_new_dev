/**
 * 
 */
package com.markscan.project.classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.whois.WhoisClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.markscan.project.beans.IpDomainLookup;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author pradeep
 *
 */
public class LookUp extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(ActionSupport.class);
	HttpSession session2 = null;

	public LookUp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String ipExecute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			return SUCCESS;
		}
	}

	public String domainExecute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			return SUCCESS;
		}

	}

	public String whoisExecute() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			return SUCCESS;
		}

	}

	String ipp[] = null;

	@SuppressWarnings({ "deprecation", "unchecked" })

	public String ipLookup() {
		logger.info("--ip addresses,.......---" + ip_add);
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			query = "";
			if (ip_add.startsWith(",")) {
				ip_add = ip_add.trim().substring(1, ip_add.length());
			}
			if (ip_add.endsWith(",")) {
				ip_add = ip_add.trim().substring(0, ip_add.length() - 1);
			}
			ip_add = ip_add.trim();

			if (ip_add.contains(",")) {
				ipp = ip_add.split(",");
				if (ipp.length > 99) {
					errorMessage = "Ip addresses are more than 100; reduce the size and try again !!!";
					ipp = null;
					return "errorMsg";
				}
			} else {
				ipp = new String[1];
				ipp[0] = ip_add.trim();
			}
			logger.info("------array size---------" + ipp.length);
			for (String pj : ipp) {

				query = query.concat("{\"query\": \"" + pj.trim() + "\"},");
				logger.info("------query--is-------" + query);
			}

			if (query.trim().endsWith(",")) {
				query = query.substring(0, query.length() - 1);
			}
			query = "[" + query + "]";
			// System.out.println(query);

			ipp = null;
			try {
				c = new DefaultHttpClient();
				p = new HttpPost("http://ip-api.com/batch");

				// p.setEntity(new
				// StringEntity("[{\"query\":\"127.0.0.1\"},{\"query\":\"212.235.245.1\"}]",
				// ContentType.create("application/json")));

				p.setEntity(new StringEntity(query, ContentType.create("application/json")));
				// System.out.println("88888-------------" + p.toString());
				r = c.execute(p);
				// System.out.println("88888-------------" + r.toString());
				rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
				line = "";
				// while ((line = rd.readLine()) != null) {

				if ((line = rd.readLine()) != null) {

					// System.out.println("----------" + line.toString());
					// Parse our JSON response
					j = new JSONParser();
					o = (Object) j.parse(line);

					jsonArr = (JSONArray) o; // Getting c
					logger.info("~~~~~~JSONArray size~~~~" + jsonArr.size());
					jsonObj = (JSONObject) jsonArr.get(0);

					logger.info("~~~~~~JSONobjsize~~~~" + jsonObj.size());
					// response = ServletActionContext.getResponse();
					rows = new ArrayList<String>();
					// rows.add("\n\n");
					lookupDetail = new ArrayList<>();
					for (int i = 0; i < jsonArr.size(); i++) {
						jsonObj1 = (JSONObject) jsonArr.get(i);
						// row = "";
						logger.info("---=header size====>>>>" + jsonObj1.size());
						// System.out.println("~~~~~~~~~~" +
						// jsonObj1.get("as"));
						keyset = jsonObj1.keySet().toString();
						keyset = keyset.trim().substring(1, keyset.length() - 1);
						// System.out.println("~~~~~ppj~~~~~" + keyset);
						ss = jsonObj1.keySet();
						logger.info("-------- set size-----------" + ss.size());
						logger.info("-------- set -----------" + ss.toString());
						ipdl = new IpDomainLookup();
						if (jsonObj1.size() == 14) {

							ipdl.setZip(jsonObj1.get("zip").toString());
							ipdl.setCountry(jsonObj1.get("country").toString());
							ipdl.setCity(jsonObj1.get("city").toString());
							ipdl.setOrg(jsonObj1.get("org").toString());
							ipdl.setTimezone(jsonObj1.get("timezone").toString());
							ipdl.setIsp(jsonObj1.get("isp").toString());
							ipdl.setQuery(jsonObj1.get("query").toString());
							ipdl.setRegionName(jsonObj1.get("regionName").toString());
							ipdl.setLon(jsonObj1.get("lon").toString());
							ipdl.setAs(jsonObj1.get("as").toString());
							ipdl.setCountryCode(jsonObj1.get("countryCode").toString());
							ipdl.setRegion(jsonObj1.get("region").toString());
							ipdl.setLat(jsonObj1.get("lat").toString());
							ipdl.setStatus(jsonObj1.get("status").toString());

						} else if (jsonObj1.size() == 3) {
							ipdl.setQuery(jsonObj1.get("query").toString());
							ipdl.setStatus(jsonObj1.get("status").toString());
							ipdl.setMessage(jsonObj1.get("message").toString());

						}
						lookupDetail.add(ipdl);

						ipdl = null;
						jsonObj1 = null;
						ss = null;
					}

				}

			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				ipdl = null;
				c = null;
				p = null;
				r = null;
				rd = null;
				line = null;
				j = null;
				o = null;
				jsonArr = null;
				jsonObj = null;
				ss = null;
				jsonObj1 = null;
				keyset = null;
				row = null;
			}
			return SUCCESS;
		}
	}

	public String findDomainLookup() {

		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			query = "";
			if (ip_add.startsWith(",")) {
				ip_add = ip_add.trim().substring(1, ip_add.length());
			}
			if (ip_add.endsWith(",")) {
				ip_add = ip_add.trim().substring(0, ip_add.length() - 1);
			}
			ip_add = ip_add.trim();

			if (ip_add.contains(",")) {
				ipp = ip_add.split(",");

				if (ipp.length > 30) {
					errorMessage = "Ip addresses are more than 30; reduce the size and try again !!!";
					return "errorMsg";
				}
			} else {
				ipp = new String[1];
				ipp[0] = ip_add.trim();
			}

			try {

				lookupDetail = new ArrayList<>();
				for (String pj : ipp) {
					ipdl = new IpDomainLookup();
					doc = Jsoup.connect("http://ip-api.com/xml/" + pj.trim() + "?fields=61439").get();

					elements = doc.getAllElements();
					logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + elements.size());
					int i = 1;
					for (Element element : elements) {
						System.out.println("---------------------------print------------" + i);
						logger.info(element.tag() + "---" + element.ownText());

						ipdl.setDomain(pj.trim());

						if (element.tag().toString().equalsIgnoreCase("zip"))
							ipdl.setZip(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("country"))
							ipdl.setCountry(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("city"))
							ipdl.setCity(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("org"))
							ipdl.setOrg(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("timezone"))
							ipdl.setTimezone(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("isp"))
							ipdl.setIsp(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("query"))
							ipdl.setQuery(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("regionName"))
							ipdl.setRegionName(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("lon"))
							ipdl.setLon(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("as"))
							ipdl.setAs(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("countryCode"))
							ipdl.setCountryCode(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("region"))
							ipdl.setRegion(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("lat"))
							ipdl.setLat(element.ownText());
						if (element.tag().toString().equalsIgnoreCase("status"))
							ipdl.setStatus(element.ownText());

						if (element.tag().toString().equalsIgnoreCase("message"))
							ipdl.setMessage(element.ownText());
						i++;
					}
					lookupDetail.add(ipdl);
					Thread.sleep(randmNo(), 964);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				elements = null;
				doc = null;
				ipdl = null;
			}
			return SUCCESS;
		}
	}

	public String whoisLookup() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			IANA_WHOIS_SERVER = "whois.iana.org";
			if (ip_add.startsWith(",")) {
				ip_add = ip_add.trim().substring(1, ip_add.length());
			}
			if (ip_add.endsWith(",")) {
				ip_add = ip_add.trim().substring(0, ip_add.length() - 1);
			}
			ip_add = ip_add.trim();
			if (ip_add.contains(",")) {
				ipp = ip_add.split(",");
				// query = "";

				if (ipp.length > 15) {
					errorMessage = "Ip addresses are more than 30; reduce the size and try again !!!";
					return "errorMsg";
				}
			} else {
				ipp = new String[1];
				ipp[0] = ip_add.trim();
			}

			try {
				lookupDetail = new ArrayList<>();

				for (String pj : ipp) {
					logger.info("-------2-----------" + pj);
					host = pj.trim();
					ipdl = new IpDomainLookup();
					// get the address of the authoritative Whois server from
					// IANA
					try {
						whoisClient = new WhoisClient();
						whoisClient.connect(IANA_WHOIS_SERVER, WHOIS_PORT);
						tmpStr = whoisClient.query(host);
						whoisClient.disconnect();
						int idx = tmpStr.indexOf("whois:");
						tmpStr = tmpStr.substring(idx + 6).trim();
						actualServer = tmpStr.substring(0, tmpStr.indexOf("\n"));

						// we need to special-case some TLDs
						tld = host.substring(host.lastIndexOf(".") + 1).trim().toLowerCase();

						// suppress Japanese characters in output
						if ("jp".equals(tld))
							host += "/e";

						// get the actual Whois data
						whoisClient.connect(actualServer, WHOIS_PORT);
						// The prefix "domain " solves the problem with spurious
						// server
						// names
						// (like for google.com, apple.com. yahoo.com,
						// microsoft.com
						// etc.)
						if ("com".equals(tld))
							tmpStr = whoisClient.query("domain " + host);
						else if ("de".equals(tld))
							// The syntax for .de is slightly different.
							tmpStr = whoisClient.query("-T dn " + host);
						else if ("dk".equals(tld))
							// show more information for .dk
							tmpStr = whoisClient.query("--show-handles " + host);
						else
							tmpStr = whoisClient.query(host);
						whoisClient.disconnect();
						// printResults(actualServer, tmpStr);

						// if there is a more specific server, query that one
						// too
						idx = tmpStr.indexOf("Whois Server:");
						if (idx != -1) {
							tmpStr = tmpStr.substring(idx + 13).trim();
							actualServer = tmpStr.substring(0, tmpStr.indexOf("\n"));
							whoisClient.connect(actualServer, WHOIS_PORT);
							tmpStr = whoisClient.query(host);
							whoisClient.disconnect();
							// printResults(actualServer, tmpStr);
						}

						// System.out.println("*****************************************************************************");
						// System.out.println(tmpStr);
						tech_street = "";
						name_server = "";
						scnr = new Scanner(tmpStr);
						// int i = 0;
						while (scnr.hasNextLine()) {
							line = scnr.nextLine();
							// System.out.println(i + "----: " + line);
							// line.contains
							// i++;
							if (line.contains("Domain Name:")) {
								// System.out.println("----finding nemo---" +
								// line.replaceAll("Domain Name:", ""));
								ipdl.setDomain_name(line.replaceAll("Domain Name:", ""));
							} else if (line.contains("Registry Domain ID:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registry Domain ID:", ""));
								ipdl.setRegistry_domain_id(line.replace("Registry Domain ID:", ""));
							}

							else if (line.contains("Registrar WHOIS Server:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrar WHOIS Server:", ""));
								ipdl.setRegistrar_whois_server(line.replace("Registrar WHOIS Server:", ""));
							} else if (line.contains("Registrar URL:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrar URL:", ""));
								ipdl.setRegistrar_url(line.replace("Registrar URL:", ""));
							} else if (line.contains("Registrar IANA ID:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrar IANA ID:", ""));
								ipdl.setRegistrar_iana_id(line.replace("Registrar IANA ID:", ""));
							} else if (line.contains("Updated Date:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Updated Date:", ""));
								ipdl.setUpdated_date(line.replace("Updated Date:", ""));
							} else if (line.contains("Creation Date:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Creation Date:", ""));
								ipdl.setCreation_date(line.replace("Creation Date:", ""));
							} else if (line.contains("Registry Expiry Date:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registry Expiry Date:", ""));
								ipdl.setRegistrar_registration_expiration_date(
										line.replace("Registry Expiry Date:", ""));
							} else if (line.contains("Registrar:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrar:", ""));
								ipdl.setRegistrar(line.replace("Registrar:", ""));
							} else if (line.contains("Registrar Abuse Contact Email:")) {
								// System.out.println(
								// "----finding nemo---" +
								// line.replace("Registrar
								// Abuse Contact Email:", ""));
								ipdl.setRegistrar_abuse_contact_email(
										line.replace("Registrar Abuse Contact Email:", ""));
							} else if (line.contains("Registrar Abuse Contact Phone:")) {
								// System.out.println(
								// "----finding nemo---" +
								// line.replace("Registrar
								// Abuse Contact Phone:", ""));
								ipdl.setRegistrar_abuse_contact_phone(
										line.replace("Registrar Abuse Contact Phone:", ""));
							}

							else if (line.contains("Domain Status:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Domain Status:", ""));
								ipdl.setDomain_status(line.replace("Domain Status:", ""));
							} else if (line.contains("Registry Registrant ID:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registry Registrant ID:", ""));
								ipdl.setRegistry_registrant_id(line.replace("Registry Registrant ID:", ""));
							} else if (line.contains("Registrant Name:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Name:", ""));
								ipdl.setRegistrant_name(line.replace("Registrant Name:", ""));
							} else if (line.contains("Registrant Organization:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Organization:",
								// ""));
								ipdl.setRegistrant_organization(line.replace("Registrant Organization:", ""));
							} else if (line.contains("Registrant Street:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Street:", ""));
								ipdl.setRegistrant_street(line.replace("Registrant Street:", ""));
							} else if (line.contains("Registrant City:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant City:", ""));
								ipdl.setRegistrant_city(line.replace("Registrant City:", ""));
							} else if (line.contains("Registrant State/Province:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant State/Province:",
								// ""));
								ipdl.setRegistrant_state_province(line.replace("Registrant State/Province:", ""));
							} else if (line.contains("Registrant Postal Code:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Postal Code:", ""));
								ipdl.setRegistrant_postal_code(line.replace("Registrant Postal Code:", ""));
							} else if (line.contains("Registrant Country:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Country:", ""));
								ipdl.setRegistrant_country(line.replace("Registrant Country:", ""));
							} else if (line.contains("Registrant Phone:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Phone:", ""));
								ipdl.setRegistrant_phone(line.replace("Registrant Phone:", ""));
							} else if (line.contains("Registrant Phone Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Phone Ext:", ""));
								ipdl.setRegistrant_phone_ext(line.replace("Registrant Phone Ext:", ""));
							} else if (line.contains("Registrant Fax:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Fax:", ""));
								ipdl.setRegistrant_fax(line.replace("Registrant Fax:", ""));
							} else if (line.contains("Registrant Fax Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Fax Ext:", ""));
								ipdl.setRegistrant_fax_ext(line.replace("Registrant Fax Ext:", ""));
							}

							else if (line.contains("Registrant Email:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registrant Email:", ""));
								ipdl.setRegistrant_email(line.replace("Registrant Email:", ""));
							} else if (line.contains("Registry Admin ID:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registry Admin ID:", ""));
								ipdl.setRegistry_admin_id(line.replace("Registry Admin ID:", ""));
							} else if (line.contains("Admin Name:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Name:", ""));
								ipdl.setAdmin_name(line.replace("Admin Name:", ""));
							} else if (line.contains("Admin Organization:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Organization:", ""));
								ipdl.setAdmin_organization(line.replace("Admin Organization:", ""));
							}

							else if (line.contains("Admin Street:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Street:", ""));
								ipdl.setAdmin_street(line.replace("Admin Street:", ""));
							} else if (line.contains("Admin City:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin City:", ""));
								ipdl.setAdmin_city(line.replace("Admin City:", ""));
							} else if (line.contains("Admin State/Province:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin State/Province:", ""));
								ipdl.setAdmin_state_province(line.replace("Admin State/Province:", ""));
							} else if (line.contains("Admin Postal Code:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Postal Code:", ""));
								ipdl.setAdmin_postal_code(line.replace("Admin Postal Code:", ""));
							} else if (line.contains("Admin Country:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Country:", ""));
								ipdl.setAdmin_country(line.replace("Admin Country:", ""));
							} else if (line.contains("Admin Phone:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Phone:", ""));
								ipdl.setAdmin_phone(line.replace("Admin Phone:", ""));
							} else if (line.contains("Admin Phone Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Phone Ext:", ""));
								ipdl.setAdmin_phone_ext(line.replace("Admin Phone Ext:", ""));
							} else if (line.contains("Admin Fax:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Fax:", ""));
								ipdl.setAdmin_fax(line.replace("Admin Fax:", ""));
							} else if (line.contains("Admin Fax Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Fax Ext:", ""));
								ipdl.setAdmin_fax_ext(line.replace("Admin Fax Ext:", ""));
							} else if (line.contains("Admin Email:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Admin Email:", ""));
								ipdl.setAdmin_email(line.replace("Admin Email:", ""));
							} else if (line.contains("Registry Tech ID:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registry Tech ID:", ""));
								ipdl.setRegistry_tech_id(line.replace("Registry Tech ID:", ""));
							} else if (line.contains("Tech Name:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Name:", ""));
								ipdl.setTech_name(line.replace("Tech Name:", ""));
							} else if (line.contains("Tech Organization:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Organization:", ""));
								ipdl.setTech_organization(line.replace("Tech Organization:", ""));
							} else if (line.contains("Tech Street:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Street:", "")); // tech
								// street
								// is
								// twice
								tech_street = tech_street + "\n " + line.replace("Tech Street:", "");

							} else if (line.contains("Tech City:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech City:", ""));
								ipdl.setTech_city(line.replace("Tech City:", ""));
							} else if (line.contains("Tech State/Province:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech State/Province:", ""));
								ipdl.setTech_state_province(line.replace("Tech State/Province:", ""));
							} else if (line.contains("Tech Postal Code:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Postal Code:", ""));
								ipdl.setTech_postal_code(line.replace("Tech Postal Code:", ""));
							}

							else if (line.contains("Tech Country:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Country:", ""));
								ipdl.setTech_city(line.replace("Tech Country:", ""));
							} else if (line.contains("Tech Phone:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Phone:", ""));
								ipdl.setTech_phone(line.replace("Tech Phone:", ""));
							} else if (line.contains("Tech Phone Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Phone Ext:", ""));
								ipdl.setTech_phone_ext(line.replace("Tech Phone Ext:", ""));
							} else if (line.contains("Tech Fax:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Fax:", ""));
								ipdl.setTech_fax(line.replace("Tech Fax:", ""));
							} else if (line.contains("Tech Fax Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Fax Ext:", ""));
								ipdl.setTech_fax_ext(line.replace("Tech Fax Ext:", ""));
							} else if (line.contains("Tech Email:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Tech Email:", ""));
								ipdl.setTech_email(line.replace("Tech Email:", ""));
							} else if (line.contains("Registry Billing ID:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Registry Billing ID:", ""));
								ipdl.setRegistry_billing_id(line.replace("Registry Billing ID:", ""));
							} else if (line.contains("Billing Name:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Name:", ""));
								ipdl.setBilling_name(line.replace("Billing Name:", ""));
							}

							else if (line.contains("Billing Organization:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Organization:", ""));
								ipdl.setBilling_organization(line.replace("Billing Organization:", ""));
							} else if (line.contains("Billing Street:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Street:", ""));
								ipdl.setBilling_street(line.replace("Billing Street:", ""));
							} else if (line.contains("Billing City:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing City:", ""));
								ipdl.setBilling_city(line.replace("Billing City:", ""));
							} else if (line.contains("Billing State/Province:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing State/Province:", ""));
								ipdl.setBilling_state_province(line.replace("Billing State/Province:", ""));
							} else if (line.contains("Billing Postal Code:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Postal Code:", ""));
								ipdl.setBilling_postal_code(line.replace("Billing Postal Code:", ""));
							} else if (line.contains("Billing Country:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Country:", ""));
								ipdl.setBilling_country(line.replace("Billing Country:", ""));
							} else if (line.contains("Billing Phone:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Phone:", ""));
								ipdl.setBilling_phone(line.replace("Billing Phone:", ""));
							} else if (line.contains("Billing Phone Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Phone Ext:", ""));
								ipdl.setBilling_phone_ext(line.replace("Billing Phone Ext:", ""));
							}

							else if (line.contains("Billing Fax:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Fax:", ""));
								ipdl.setBilling_fax(line.replace("Billing Fax:", ""));
							} else if (line.contains("Billing Fax Ext:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Fax Ext:", ""));
								ipdl.setBilling_fax_ext(line.replace("Billing Fax Ext:", ""));
							} else if (line.contains("Billing Email:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Billing Email:", ""));
								ipdl.setBilling_email(line.replace("Billing Email:", ""));
							} else if (line.contains("Name Server:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("Name Server:", "")); //
								// multiple
								// value

								name_server = name_server + "\n " + line.replace("Name Server:", "");
							} else if (line.contains("DNSSEC:")) {
								// System.out.println("----finding nemo---" +
								// line.replace("DNSSEC:", ""));
								ipdl.setDnssec(line.replace("DNSSEC:", ""));
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					ipdl.setTech_street(tech_street);
					ipdl.setName_server(name_server);
					tech_street = null;
					name_server = null;
					whoisClient = null;
					tmpStr = null;
					scnr = null;
					lookupDetail.add(ipdl);
					ipdl = null;
					Thread.sleep(randmNo(), 537);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				tech_street = null;
				name_server = null;
				whoisClient = null;
				tmpStr = null;
				scnr = null;
				ipp = null;
				IANA_WHOIS_SERVER = null;
				ip_add = null;
				tmpStr = null;
				actualServer = null;
				whoisClient = null;
				tld = null;
				host = null;
				ipdl = null;
			}
			return SUCCESS;
		}
	}

	public int randmNo() {
		rndm = new Random();
		return rndm.nextInt(High - Low) + Low;
	}

	String name_server = null;
	Scanner scnr = null;
	String tech_street = null;
	String tld = null;
	String actualServer = null;
	String tmpStr = null;
	WhoisClient whoisClient = null;
	String host = null;
	String IANA_WHOIS_SERVER = null;
	final int WHOIS_PORT = 43;
	List<IpDomainLookup> lookupDetail = null;
	IpDomainLookup ipdl = null;
	Random rndm = null;
	Document doc = null;
	HttpClient c = null;
	HttpPost p = null;
	HttpGet g = null;
	HttpResponse r = null;
	BufferedReader rd = null;
	String line = null;
	JSONParser j = null;
	Object o = null;
	JSONArray jsonArr = null;
	JSONObject jsonObj = null;
	Set<String> ss = null;
	JSONObject jsonObj1 = null;
	String keyset = null;
	String query = null;
	String ip_add = null;
	String reportName = null;
	String row = null;
	HttpServletResponse response = null;
	ArrayList<String> rows = null;
	String outputString = null;
	Elements elements = null;
	String errorMessage = null;
	int Low = 3500;
	int High = 19935;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getIp_add() {
		return ip_add;
	}

	public void setIp_add(String ip_add) {
		this.ip_add = ip_add;
	}

	public List<IpDomainLookup> getLookupDetail() {
		return lookupDetail;
	}

	public void setLookupDetail(List<IpDomainLookup> lookupDetail) {
		this.lookupDetail = lookupDetail;
	}

}
