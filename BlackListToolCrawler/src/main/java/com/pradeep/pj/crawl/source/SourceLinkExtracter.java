/**
 * 
 */
package com.pradeep.pj.crawl.source;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

/**
 * @author pradeep
 *
 */
@Service
@Configurable
public class SourceLinkExtracter {
	private static final Logger logger = LoggerFactory.getLogger(SourceLinkExtracter.class);
	/**
	 * 
	 */
	@Autowired
	//private PageSource pageSource__c;

	public SourceLinkExtracter() {
		// TODO Auto-generated constructor stub
	}

	String pSource = null;
	String srclnk = null; // temp_data
	String sourceLink = null;

	@SuppressWarnings("finally")
	public String srcLink_Extractor(String uurl) {
		sourceLink = "";
		try {
			PageSource pageSource__c = new PageSource();
			pSource = pageSource__c.html2Doc(uurl);

			// 1. checking for json data
			srclnk = jsonlinkExtracting(pSource);
			if (!srclnk.isEmpty()) {
				sourceLink = readJsonFile(srclnk.trim());
			}

			logger.info("====1====" + sourceLink);
			// 2. check iframe
			if (sourceLink.isEmpty() || sourceLink == "" || sourceLink == null || sourceLink.equals(null)) {
				sourceLink = iframelinkExtracting2(pSource, uurl.trim());
			}

			logger.info("====2====" + sourceLink);
			// 3. check iframe
			if (sourceLink.isEmpty() || sourceLink == "" || sourceLink == null || sourceLink.equals(null)) {
				sourceLink = iframelinkExtracting(pSource);
			}
			logger.info("====3====" + sourceLink);
			// 4. get source link from download button
			if (sourceLink.isEmpty() || sourceLink == "" || sourceLink == null || sourceLink.equals(null)) {
				sourceLink = downloadlink(pSource);
			}
			logger.info("====4====" + sourceLink);
			// 5. get source link Watch / Download Here
			if (sourceLink.isEmpty() || sourceLink == "" || sourceLink == null || sourceLink.equals(null)) {
				sourceLink = watchDownloadlink(pSource);
			}
			if (sourceLink.contains("download=")) {
				String xm[] = sourceLink.split("download=");
				sourceLink = xm[1];
				xm = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			uurl = null;
			pSource = null;
			return sourceLink;
		}
	}

	// get jsaon url from html page
	private String jsonlinkExtracting(String pSrc) {

		String jsonSRCLink = "";
		try {
			doc = Jsoup.parse(pSrc);
			links = doc.select("script[data-config]");
			for (Element element : links) {
				jsonSRCLink = element.attr("data-config");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doc = null;
			links = null;
		}

		return jsonSRCLink;
	}

	Set<String> iframeLinks = null;
	List<String> pages = null;
	String iframeSRCLink = null;;

	// get source link from iframe
	@SuppressWarnings("finally")
	private String iframelinkExtracting(String pSrc) {
		iframeSRCLink = "";
		try {
			doc = Jsoup.parse(pSrc);
			links = doc.select("iframe[src]");
			for (Element element : links) {
				if (element.attr("src").contains("ads") || element.attr("src").contains("/ad/")
						|| element.attr("src").contains("http://tv-trader.me/ad/")
						|| element.attr("src").contains("/ads/") || element.attr("src").contains("300X250.html")) {
				} else {
					// iframeLink.add(element.attr("SRC"));
					iframeSRCLink = element.attr("src");
				}

			}
			// logger.info("==== 898989==============="+iframeSRCLink);
			// logger.info("==== 898989==============="+iframeSRCLink.length());
			if (iframeSRCLink.isEmpty() || iframeSRCLink == "" || iframeSRCLink == null) {
				links = doc.select("IFRAME[SRC]");
				for (Element element : links) {
					if (element.attr("src").contains("ads") || element.attr("src").contains("/ad/")
							|| element.attr("src").contains("/ads/") || element.attr("src").contains("300X250.html")
							|| element.attr("src").contains("http://tv-trader.me/ad/")) {
					} else {
						iframeSRCLink = element.attr("src");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doc = null;
			links = null;
			return iframeSRCLink;
		}

	}

	@SuppressWarnings("finally")
	private String iframelinkExtracting2(String pSrc, String infi_link) {
		iframeSRCLink = "";
		String infiLink = infi_link.trim().replace("http://", "");

		if (infiLink.contains("?")) {
			infiLink = infiLink.substring(0, infiLink.indexOf("?"));
		}
		if (infiLink.contains("/")) {
			infiLink = infiLink.substring(0, infiLink.indexOf("/"));
		}
		// System.out.println("'joshi==============================================================="
		// + infiLink);
		iframeLinks = new HashSet<>();

		pages = new ArrayList<>();
		String iframeSRCLink = "";
		try {
			doc = Jsoup.parse(pSrc);
			links = doc.select("iframe[src]");

			if (iframeSRCLink.isEmpty() || iframeSRCLink == "") {
				links = doc.select("IFRAME[SRC]");
				for (Element element : links) {
					// checking for link contains ad
					if (element.attr("src").contains("/ads") || element.attr("src").contains("ads")
							|| element.attr("src").contains("ad") || element.attr("src").contains("/ad/")
							|| element.attr("src").contains("/ad") || element.attr("src").contains("/ads/")
							|| element.attr("src").contains("http://tv-trader.me/ad/")
							|| element.attr("src").contains("300X250.html")
							|| element.attr("src").contains("http://creative.wwwpromoter.com/")
							|| element.attr("src").contains(infiLink)) {

					} else {
						iframeLinks.add(element.attr("SRC"));
					}
				}
			}
			pages.addAll(iframeLinks);

			iframeLinks = null;
			if (pages.size() == 0) {
				iframeSRCLink = "";
			} else {
				iframeSRCLink = pages.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			infiLink = null;
			iframeLinks = null;
			doc = null;
			pages = null;
			links = null;
			return iframeSRCLink;
		}

	}

	private JSONParser parser = null;
	private JSONObject object = null;

	// reading json file for getting source link
	private String readJsonFile(String jsonURL) {
		String sourcelinkfromJSON = "", hostingid = "", gjson = "", poster = "", jsonSource = "", publisherid = "",
				videoid = "";
		// String contentId="";
		PageSource pageSource__c = new PageSource();
		try {
			logger.info("=========+" + jsonURL);
			if (jsonURL.contains("playwire.com")) {
				try {
					
					jsonSource = pageSource__c.html2Doc(jsonURL.trim());
					parser = new JSONParser();
					object = (JSONObject) parser.parse(jsonSource.trim());

					try {
						hostingid = object.get("hostingId").toString();
						publisherid = object.get("publisherId").toString();
						poster = object.get("content").toString();
						logger.info("poster==" + poster);
						object = (JSONObject) parser.parse(poster);
						logger.info("object==" + object);
						// contentId = object.get("content").toString();
						videoid = object.get("videoId").toString();
					} catch (Exception e) {
						System.err.println("hosting id 1 error..");
						e.printStackTrace();
					}
					logger.info("----hosting id-111--- " + hostingid);
					logger.info("----publisherid id--222-- " + publisherid);
					logger.info("----videoId id--222-- " + videoid);

					if (hostingid.trim().equalsIgnoreCase(publisherid.trim())) {
						jsonURL = jsonURL.replace("/zeus.json", "");
						videoid = jsonURL.substring(jsonURL.lastIndexOf("/") + 1, jsonURL.length());
						logger.info("----videoId id--2.........22-- " + videoid);
						sourcelinkfromJSON = "https://cdn.video.playwire.com/".concat(publisherid).concat("/videos/")
								.concat(videoid).concat("/video-sd.mp4?hosting_id=").concat(hostingid);
					} else {
						sourcelinkfromJSON = "https://cdn.video.playwire.com/".concat(publisherid).concat("/videos/")
								.concat(videoid).concat("/video-sd.mp4?hosting_id=").concat(hostingid);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

				if (!jsonURL.isEmpty()) {
					jsonSource = pageSource__c.html2Doc(jsonURL.trim());
					parser = new JSONParser();
					logger.info(jsonSource);
					object = (JSONObject) parser.parse(jsonSource.trim());
					try {
						poster = object.get("poster").toString();
					} catch (Exception ee) {
						logger.info("poster 1 not found");
					}
					if (poster.isEmpty() || poster == "") {
						try {
							poster = object.get("content").toString();
							object = (JSONObject) parser.parse(poster);
							poster = object.get("poster").toString();
						} catch (Exception e) {
							logger.info("poster 2 not found too");
						}
					}
					object = (JSONObject) parser.parse(jsonSource);
					try {
						hostingid = object.get("hostingId").toString();
						// publisherid = object.get("publisherId").toString();
					} catch (Exception e) {
						logger.info("hosting id 1 error..");
					}
					logger.info("----hosting id---- " + hostingid);
					logger.info("----publisherid id---- " + publisherid);

					if (hostingid.isEmpty() || hostingid == "") {
						try {
							hostingid = object.get("settings").toString();
							object = (JSONObject) parser.parse(hostingid);
							hostingid = object.get("hostingId").toString();
						} catch (Exception e) {
							logger.info("hosting id 2 error too..");
						}
					}

					gjson = poster.substring(poster.lastIndexOf("/") + 1);
					poster = poster.replace(gjson, "");
					if (!poster.isEmpty() || poster != "") {
						sourcelinkfromJSON = poster.concat("video-sd.mp4?hosting_id=").concat(hostingid);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		parser = null;
		hostingid = null;
		object = null;
		gjson = null;
		poster = null;
		jsonSource = null;
		return sourcelinkfromJSON;

	}

	// get link from download button
	@SuppressWarnings("finally")
	private String downloadlink(String psource) {
		String dwnloadLink = "";

		try {
			doc = Jsoup.parse(psource);
			links = doc.select("a[href]");
			for (Element ee : links) {
				if (ee.text().contains("Click Here to Download Video")
						|| ee.text().contains("Click Here To Visit The Link")) {
					dwnloadLink = ee.attr("href");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doc = null;
			links = null;
			return dwnloadLink;
		}

	}

	// get sourcelik from Watch / Download Here url
	private String watchDownloadlink(String psource2) {

		String dwnloadLink = "";
		try {
			psource2 = psource2.replaceAll("<h1>", "");
			psource2 = psource2.replaceAll("</h1>", "");
			psource2 = psource2.replaceAll("target=\"_blank\"", "class = \"apple\"");
			doc = Jsoup.parse(psource2);

			links = doc.select("a[href]");
			for (Element ee : links) {
				if (ee.text().contains("Watch / Download Here")) {
					dwnloadLink = ee.attr("href");
					logger.info("download link - - - - - " + dwnloadLink);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			psource2 = null;
			doc = null;
			links = null;
		}
		return dwnloadLink;
	}

	Document doc = null;
	Elements links = null;

}
