/**
 * 
 */
package com.markscan.project.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Url_email;
import com.markscan.project.dao.Url_emailDao;
import com.opencsv.CSVReader;

/**
 * @author pradeep
 *
 */
public class PlayWire_Convert extends ActionPerform {
	private static final Logger logger = Logger.getLogger(PlayWire_Convert.class);

	/**
	 * 8th march 2017
	 */
	public PlayWire_Convert() {
		// TODO Auto-generated constructor stub
	}

	HttpSession session2 = null;

	public String execute() {
		return SUCCESS;
	}

	String configFile = null;
	String filePath = null;
	Properties prop = new Properties();
	InputStream input = null;
	private File uploadFile;
	private String uploadFileFileName;

	public String PlayWireLinkConvert() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			logger.info("pradeep joshi here");
			try {
				configFile = "/details.properties";
				input = getClass().getResourceAsStream(configFile);
				prop.load(input);
				filePath = prop.getProperty(filePath);
			} catch (Exception e) {
				logger.error("file read error .. ", e);
			}
			// filePath ="D:\\indexing";
			try {
//				System.err.println("Server path:" + filePath); // check your
				// path in
				// console
				logger.info("Server path:" + filePath);
				File fileToCreate = new File(filePath, uploadFile.getName().concat("_").concat(uploadFileFileName));// Create
				// file
				// name
				// temp
				// +
				// original

				FileUtils.copyFile(uploadFile, fileToCreate); // Just copy temp
				// file
				// content tos
				// this
				// file

				logger.info("file..... " + fileToCreate);
				/**
				 * read csv file then delete the file from server....
				 */
				CSVReader reader = new CSVReader(new FileReader(fileToCreate), ',');

				String[] rowData = null;
				int i = 0;

				int ik = 1;
				playwirelink = new HashMap();
				// pattern = Pattern.compile(IPADDRESS_PATTERN);
				while ((rowData = reader.readNext()) != null) {

					try {

						// pattern = Pattern.compile(EMAIL_PATTERN,
						// Pattern.CASE_INSENSITIVE);
						// matcher = pattern.matcher(rowData[4].trim());
						if (rowData[0].trim().startsWith("http://config.playwire.com")
								&& rowData[0].trim().endsWith("zeus.json")) {
							playwirelink.put(rowData[0], readJsonFile(rowData[0]));
						} else {
							// wrongEmailData.add(Integer.parseInt(rowData[0].trim()));
						}
						// cu.setW_list(whitelist_two_check(rowData[0].trim()));

					} catch (Exception e) {
						// System.err.println("source insert error !!");
						logger.error("Email upload file insert error !!", e);
						// e.printStackTrace();
					}

					// statusUpdate = "Email File uploaded successfully...!!!";
				}
				reader.close();
				// System.out.println("data Successfully deploy");
				fileToCreate.delete(); // delete file..
				// System.out.println("file Successfully deleted");
				logger.info("file Successfully deleted");

				return SUCCESS;
			} catch (Exception e) {
				logger.error("Email  file upload error !!", e);
				return ERROR;
			}
		}
	}

	private JSONObject object = null;
	private org.json.JSONObject jsonObject = null;

	public String readJsonFile(String jsonURL) // reading json file for
	// getting source link
	// ...
	{
		String sourcelinkfromJSON = "", hostingid = "", gjson = "", poster = "", jsonSource = "", publisherid = "",
				videoid = "";
		// String contentId="";
		JSONParser parser = null;
		try {
			Thread.sleep(500, 93);
//			System.out.println("=========+" + jsonURL);
			if (jsonURL.contains("playwire.com")) {
				try {
					jsonObject = readJsonFromUrl(jsonURL);
					// jsonSource = PageSource.getPageSource(jsonURL.trim());
					parser = new JSONParser();
					object = (JSONObject) parser.parse(jsonObject.toString());

					try {
						hostingid = object.get("hostingId").toString();
						publisherid = object.get("publisherId").toString();
						poster = object.get("content").toString();
//						System.out.println("poster==" + poster);
						object = (JSONObject) parser.parse(poster);
//						System.out.println("object==" + object);
						// contentId = object.get("content").toString();
						videoid = object.get("videoId").toString();
					} catch (Exception e) {
//						System.err.println("hosting id 1 error..");
						e.printStackTrace();
					}
//					System.err.println("----hosting id-111--- " + hostingid);
//					System.err.println("----publisherid id--222-- " + publisherid);
//					System.err.println("----videoId id--222-- " + videoid);

					if (hostingid.trim().equalsIgnoreCase(publisherid.trim())) {
						jsonURL = jsonURL.replace("/zeus.json", "");
						videoid = jsonURL.substring(jsonURL.lastIndexOf("/") + 1, jsonURL.length());
//						System.err.println("----videoId id--2.........22-- " + videoid);
						sourcelinkfromJSON = "https://cdn.video.playwire.com/".concat(publisherid).concat("/videos/")
								.concat(videoid).concat("/video-sd.mp4?hosting_id=").concat(hostingid);
					} else {
						sourcelinkfromJSON = "https://cdn.video.playwire.com/".concat(publisherid).concat("/videos/")
								.concat(videoid).concat("/video-sd.mp4");
					}
				} catch (Exception e) {
					e.printStackTrace();
					sourcelinkfromJSON = "error";
				}

			} else {

				if (!jsonURL.isEmpty()) {
					// jsonSource = PageSource.getPageSource(jsonURL.trim());
					parser = new JSONParser();
					// System.out.println(jsonSource);
					object = (JSONObject) parser.parse(jsonObject.toString());
					try {
						poster = object.get("poster").toString();
					} catch (Exception ee) {
						// System.err.println("poster 1 not found");
					}
					if (poster.isEmpty() || poster == "") {
						try {
							poster = object.get("content").toString();
							object = (JSONObject) parser.parse(poster);
							poster = object.get("poster").toString();
						} catch (Exception e) {
							// System.err.println("poster 2 not found too");
						}
					}
					object = (JSONObject) parser.parse(jsonSource);
					try {
						hostingid = object.get("hostingId").toString();
						// publisherid = object.get("publisherId").toString();
					} catch (Exception e) {
						// System.err.println("hosting id 1 error..");
					}
					// System.err.println("----hosting id---- " + hostingid);
					// System.err.println("----publisherid id---- " +
					// publisherid);

					if (hostingid.isEmpty() || hostingid == "") {
						try {
							hostingid = object.get("settings").toString();
							object = (JSONObject) parser.parse(hostingid);
							hostingid = object.get("hostingId").toString();
						} catch (Exception e) {
							// System.err.println("hosting id 2 error too..");
						}
					}

					gjson = poster.substring(poster.lastIndexOf("/") + 1);
					poster = poster.replace(gjson, "");
					if (!poster.isEmpty() || poster != "") {
						sourcelinkfromJSON = poster.concat("video-sd.mp4");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sourcelinkfromJSON = "error";
		}
		parser = null;
		hostingid = null;
		object = null;
		gjson = null;
		poster = null;
		jsonSource = null;
		return sourcelinkfromJSON;

	}

	public String Single_playwire_convert() {
		session2 = ServletActionContext.getRequest().getSession();
		logger.info(session2);
		if (session2 == null || session2.getAttribute("login") == null) {
			logger.error("if=====session error reporting===='");
			return LOGIN;
		} else {
			playwireSingleConvertedLink = readJsonFile(playwireSingleLink);
			return SUCCESS;
		}
	}

	private String readAll(Reader rd) throws IOException {
		sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public org.json.JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		is = new URL(url).openStream();
		try {
			rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			jsonText = readAll(rd);
			json = new org.json.JSONObject(jsonText);
			return json;
		} finally {
			is.close();
			is = null;
			rd = null;
			jsonText = null;
		}
	}

	Map<String, String> playwirelink = null;
	StringBuilder sb = null;
	InputStream is = null;
	BufferedReader rd = null;
	String jsonText = null;
	org.json.JSONObject json = null;
	String playwireSingleConvertedLink = null;
	String playwireSingleLink = null;

	public String getPlaywireSingleConvertedLink() {
		return playwireSingleConvertedLink;
	}

	public void setPlaywireSingleConvertedLink(String playwireSingleConvertedLink) {
		this.playwireSingleConvertedLink = playwireSingleConvertedLink;
	}

	public String getPlaywireSingleLink() {
		return playwireSingleLink;
	}

	public void setPlaywireSingleLink(String playwireSingleLink) {
		this.playwireSingleLink = playwireSingleLink;
	}

	public Map<String, String> getPlaywirelink() {
		return playwirelink;
	}

	public void setPlaywirelink(Map<String, String> playwirelink) {
		this.playwirelink = playwirelink;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

}
