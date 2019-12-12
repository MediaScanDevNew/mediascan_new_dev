/**
 * 
 */
package com.markscan.project.dao;

import java.util.List;
import java.util.Set;


/**
 * @author pradeep
 *
 */
public interface DaoMethods {
	
	public List<String> viewData(Object cc, String query);
	public List<Object> viewRecord(String query, int x);
	public List<Object> viewRecordListParameter(String query, int x, String aa);
	public List<Object> viewRecord(String query);
	public void customUpdateProject(String query);
	public void customUpdateProjectList(String query, Set<Integer>i);
	public int getNumberOfUsers(String query);
	
	
	public void setTemplate(Object template);
}
