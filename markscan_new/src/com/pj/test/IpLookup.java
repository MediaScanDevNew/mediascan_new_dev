package com.pj.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IpLookup {

	public IpLookup() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		IpLookup il= new IpLookup();
		il.findDomainLookup("techtend.com");
//		il.ipLookup();

	}

	HttpClient c = null;
	HttpPost p = null;
	HttpGet g=null;
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

	public void ipLookup() {
		try {
			c = new DefaultHttpClient();
			p = new HttpPost("http://ip-api.com/batch");

			p.setEntity(new StringEntity("[{\"query\":\"127.0.0.1\"},{\"query\":\"212.235.245.1\"}]",
					ContentType.create("application/json")));

			r = c.execute(p);

			rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
			line = "";
			// while ((line = rd.readLine()) != null) {

			if ((line = rd.readLine()) != null) {

				System.out.println("----------" + line.toString());
				// Parse our JSON response
				j = new JSONParser();
				o = (Object) j.parse(line);

				jsonArr = (JSONArray) o; // Getting c
				jsonObj = (JSONObject) jsonArr.get(1);

				System.out.println("~~~~~~~~~~" + jsonArr.size());

				for (int i = 0; i < jsonObj.size(); i++) {
					jsonObj1 = (JSONObject) jsonArr.get(i);

					System.out.println("---=====>>>>" + jsonObj1.size());
					System.out.println("~~~~~~~~~~" + jsonObj1.get("as"));
					keyset = jsonObj1.keySet().toString();
					keyset = keyset.trim().substring(1, keyset.length() - 1);
					System.out.println("~~~~~ppj~~~~~" + keyset);
					ss = jsonObj1.keySet();

					if (jsonObj1.get("status") == "success" || jsonObj1.get("status").equals("success")) {

						for (String pj : ss) {
							System.out.println(pj + " ---> " + jsonObj1.get(pj));
						}

					} else {
						for (String pj : ss) {
							System.out.println(pj + " -fail--> " + jsonObj1.get(pj));
						}
					}

				}

				System.out.println(jsonObj);

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		}
	}

	public void findDomainLookup(String domain) {
		try {
			Document doc= Jsoup.connect("http://ip-api.com/xml/"+domain+"?fields=61439").get();

//System.out.println(doc.html());


Elements elements = doc.getAllElements();
System.out.println(elements.size());
Map<String, String> mapping = new  HashMap<>();
for (Element element : elements) {
    System.out.println(element.tag()+"---------------"+element.ownText());
    mapping.put(element.tagName(), element.ownText());
}

System.out.println(mapping.size());

			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

		}
	}

}
