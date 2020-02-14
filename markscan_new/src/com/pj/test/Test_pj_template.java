/**
 * 
 */
package com.pj.test;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author pradeep
 *
 */
public class Test_pj_template {

	/**
	 * 
	 */
	public Test_pj_template() {
		// TODO Auto-generated constructor stub
	}
	public static void  main(String...str) {
		Test_pj_template uu= new Test_pj_template();
		uu.execute();
	}

	public String execute() {
		Configuration cfg = new Configuration();
		String body = null;
		try {
			cfg.setDirectoryForTemplateLoading(new File("D:/ftl"));
			Template template = cfg.getTemplate("pmqc.ftl");

			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("performedby", "usr");
			data.put("projectname", "pname");
			data.put("clientname", "cname");
			data.put("propertyname", "ptname");
			data.put("datatype", "dtype");
			data.put("date", "date");

			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);
			body = stringWriter.toString();
			System.out.println(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
