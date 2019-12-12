/**
 * 
 */
package com.markscan.project.classes.youtube;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.Jsoner;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.BeanFactory;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.VideoListResponse;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.classes.ActionPerform;
import com.markscan.project.classes.session.LoginAndSession;
import com.markscan.project.dao.Markscan_projecttypeDao;
import com.markscan.project.dao.YT_video_detailDao;

/**
 * @author pradeep created on 15feb2018
 */
public class Youtube {

	/**
	 * 
	 */
	public Youtube() {
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = Logger.getLogger(Youtube.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Youtube yt = new Youtube();
		Map<String, Integer> vv = new HashMap<>();
		vv.put("envf09nZyic", 1);
		 vv.put("vmkYKnEr6d0", 2);
		 vv.put("gKhGjAhRLno", 3);
		yt.multipleVideoDetail(vv);
		// yt.videoDetailsJSON2("vmkYKnEr6d0");
	}

	/** Map<String, String>= Map<videoid, crawlw_url2 id> **/

	YT_video_detailDao yv_dao = null;
	String insertData = null;

	public void videoDetailsJSON2(String videoID) {
		try {

			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		try {
			YouTube youtube = getYouTubeService();
			parameters = new HashMap<>();
			parameters.put("part", "snippet,contentDetails,statistics");
			parameters.put("id", videoID);

			YouTube.Videos.List videosListMultipleIdsRequest = youtube.videos().list(parameters.get("part").toString());
			if (parameters.containsKey("id") && parameters.get("id") != "") {
				videosListMultipleIdsRequest.setId(parameters.get("id").toString());
			}

			response = videosListMultipleIdsRequest.execute();
			Object jsonOutput = Jsoner.deserialize(response.toPrettyString());
			String jsonSerialized = Jsoner.serialize(jsonOutput);
			String jsonPrettyPrinted = Jsoner.prettyPrint(jsonSerialized);
			System.out.println(jsonPrettyPrinted);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}

	}

	/*
	 * === ready to work
	 */

	public void multipleVideoDetail(Map<String, Integer> videoDetails) {
		try {

			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
		}

		// map = new HashMap<String, Integer>();
		videoID = "";

		for (Map.Entry<String, Integer> videoidwithid : videoDetails.entrySet()) {
			// logger.info("Key = " + videoidwithid.getKey() + ", Value = " +
			// videoidwithid.getValue());
			videoID = videoID + "," + videoidwithid.getKey();
		}
		// logger.info("==1==>>" + videoID);
		if (videoID.startsWith(",")) {
			videoID = videoID.substring(1, videoID.length());
		}
		if (videoID.endsWith(",")) {
			videoID = videoID.substring(0, videoID.length() - 1);
		}
		// logger.info("==2==>>" + videoID);

		try {
			YouTube youtube = getYouTubeService();
			factory = ActionPerform.getFactory();
			yv_dao = (YT_video_detailDao) factory.getBean("d35");

			parameters = new HashMap<>();
			parameters.put("part", "snippet,contentDetails,statistics");
			parameters.put("id", videoID);

			YouTube.Videos.List videosListMultipleIdsRequest = youtube.videos().list(parameters.get("part").toString());
			if (parameters.containsKey("id") && parameters.get("id") != "") {
				videosListMultipleIdsRequest.setId(parameters.get("id").toString());
			}

			response = videosListMultipleIdsRequest.execute();
			// logger.info(response);

			parser = new JSONParser();
			obj = parser.parse(response.toString());
			jsonObject = (JSONObject) obj;
			// logger.info("--------"+jsonObject);
			items = (JSONArray) jsonObject.get("items");

			// logger.info("item size===" + items.size());

			for (int i = 0; i < items.size(); i++) {
				tag = "";
				insertData = "";
				kind = "";
				title = "";
				channel_title = "";
				description = "";
				videoData = (JSONObject) items.get(i);
				snippet = (JSONObject) videoData.get("snippet");
				tags = (JSONArray) snippet.get("tags");
				if (tags == null || tags.add(null) || tags.equals("null") || tags == null) {
				} else {
					for (int j = 0; j < tags.size(); j++) {
						tag = tag + tags.get(j);
					}
					if (tag.contains("'")) {
						tag = tag.replace("'", " ");
					}
					if (tag.contains("#")) {
						tag = tag.replace("#", " ");
					}
					tag = conver2String(tag);
					sb = null;
				}
				contentDetails = (JSONObject) videoData.get("contentDetails");
				statistics = (JSONObject) videoData.get("statistics");

				kind = videoData.get("kind").toString();
				if (kind.contains("'")) {
					kind = kind.replace("'", " ");
				}
				if (kind.contains("#")) {
					kind = kind.replace("#", " ");
				}
				kind = conver2String(kind);
				sb = null;

				title = snippet.get("title").toString();
				if (title.contains("'")) {
					title = title.replace("'", " ");
				}
				if (title.contains("#")) {
					title = title.replace("#", " ");
				}
				title = conver2String(title);
				sb = null;

				channel_title = snippet.get("channelTitle").toString();
				if (channel_title.contains("'")) {
					channel_title = channel_title.replace("'", " ");
				}
				if (channel_title.contains("#")) {
					channel_title = channel_title.replace("#", " ");
				}
				channel_title = conver2String(channel_title);
				sb = null;

				description = snippet.get("description").toString();
				if (description.contains("'")) {
					description = description.replace("'", " ");
				}
				if (description.contains("#")) {
					description = description.replace("#", " ");
				}
				description = conver2String(description);
				sb = null;
				// logger.info(description);

				// logger.info("==============================================");
				// logger.info("******************start***********************");
				// logger.info("==============================================");
				//
				// logger.info("defaultLanguage == " +
				// snippet.get("defaultLanguage"));
				// logger.info("publishedAt ==" + snippet.get("publishedAt"));
				// logger.info("title== " + snippet.get("title"));
				//
				// logger.info("channelId==" + snippet.get("channelId"));
				// logger.info("channelTitle==" + snippet.get("channelTitle"));
				// logger.info("liveBroadcastContent==" +
				// snippet.get("liveBroadcastContent"));
				//
				// logger.info("kind== " + videoData.get("kind"));
				// logger.info("etag==" + videoData.get("etag"));
				// logger.info("video id==" + videoData.get("id")); //
				//
				// logger.info("tag == " + tag);
				//
				// logger.info("duration==" + contentDetails.get("duration"));
				// logger.info("licensedContent==" +
				// contentDetails.get("licensedContent"));
				// logger.info("caption==" + contentDetails.get("caption"));
				// logger.info("definition==" +
				// contentDetails.get("definition"));
				// logger.info("projection==" +
				// contentDetails.get("projection"));
				// logger.info("dimension==" + contentDetails.get("dimension"));
				//
				// logger.info("dislikeCount==" +
				// statistics.get("dislikeCount"));
				// logger.info("likeCount==" + statistics.get("likeCount"));
				// logger.info("viewCount==" + statistics.get("viewCount"));
				// logger.info("commentCount==" +
				// statistics.get("commentCount"));
				// logger.info("favoriteCount==" +
				// statistics.get("favoriteCount"));
				//
				// logger.info("==============================================");
				// logger.info("**********************************************");
				// logger.info("==============================================");

				insertData = "update YT_video_detail set defaultLanguage='" + snippet.get("defaultLanguage") + "' , "
						+ " publishedAt = '" + snippet.get("publishedAt") + "' , title='" + title + "' ,"
						+ " channelId='" + snippet.get("channelId") + "', channelTitle='" + channel_title + "',"
						+ " liveBroadcastContent='" + snippet.get("liveBroadcastContent") + "', " + " kind='" + kind
						+ "' , etag='" + videoData.get("etag") + "' , " + " tag='" + tag + "' , duration='"
						+ contentDetails.get("duration") + "', " + " licensedContent='"
						+ contentDetails.get("licensedContent") + "'," + " caption='" + contentDetails.get("caption")
						+ "', definition='" + contentDetails.get("definition") + "', projection='"
						+ contentDetails.get("projection") + "', dimension='" + contentDetails.get("dimension")
						+ "', dislikeCount='" + statistics.get("dislikeCount") + "' , likeCount='"
						+ statistics.get("likeCount") + "', viewCount='" + statistics.get("viewCount")
						+ "', commentCount='" + statistics.get("commentCount") + "', favoriteCount='"
						+ statistics.get("favoriteCount") + "' " + ", description='" + description + "' "
						+ " where video_id='" + videoData.get("id") + "' and crawle_url2_id ="
						+ videoDetails.get(videoData.get("id")) + " ";

				// logger.info("===="+insertData);
				try {
					yv_dao.customUpdateProject(insertData);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				insertData = null;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sb = null;
			parameters = null;
			response = null;
			parser = null;
			obj = null;
			jsonObject = null;
			items = null;
			videoData = null;
			snippet = null;
			contentDetails = null;
			statistics = null;
			tags = null;
			tag = null;
			factory = null;
			videoDetails = null;
			insertData = null;
			yv_dao = null;
			kind = null;
			title = null;
			channel_title = null;
			description = null;
		}
	}

	public static YouTube getYouTubeService() throws Exception {
		Credential credential = authorize();
		return new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	public static Credential authorize() throws Exception {
		// Load client secrets.
		InputStream in = Quickstart.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		return credential;
	}

	/** Application name. */
	private static final String APPLICATION_NAME = "API Sample";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/youtube-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	private static final List<String> SCOPES = Arrays.asList(YouTubeScopes.YOUTUBE_READONLY);

	public String conver2String(String text) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (!Character.isHighSurrogate(ch) && !Character.isLowSurrogate(ch)) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	
	
	
	
	/**
	 * varibale declartion
	 */
	// Map<String, Integer> map = null;
	StringBuilder sb = null;
	Map<String, String> parameters = null;
	VideoListResponse response = null;
	JSONParser parser = null;
	Object obj = null;
	JSONObject jsonObject = null;
	JSONArray items = null;
	JSONObject videoData = null;
	JSONObject snippet = null;
	JSONObject contentDetails = null;
	JSONObject statistics = null;
	JSONArray tags = null;
	String tag = null;
	String videoID = null;
	BeanFactory factory = null;
	String kind = null;
	String title = null;
	String channel_title = null;
	String description = null;
}
