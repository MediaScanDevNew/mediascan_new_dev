package com.pj.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Collection_test {

	public Collection_test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]){  
		  Hashtable<Integer,String> hm=new Hashtable<Integer,String>();  
		  hm.put(100,"");  
		  hm.put(100,"Amit");  
		  hm.put(102,"Ravi");  
		  hm.put(101,"Vijay");  
		  hm.put(103,"Rahul");  
		  
		  for(Map.Entry m:hm.entrySet()){  
		   System.out.println(m.getKey()+" "+m.getValue());  
		  }  
		 }  
}

class Blog {
	 private String title;
	 private String author;
	 private String url;
	 public String getTitle() {
	     return title;
	 }

	 public void setTitle(String title) {
	     this.title = title;
	 }

	 public String getAuthor() {
	     return author;
	 }

	 public void setAuthor(String author) {
	     this.author = author;
	 }

	 public String getUrl() {
	     return url;
	 }

	 public void setUrl(String url) {
	     this.url = url;
	 }

	 public String getDescription() {
	     return description;
	 }

	 public void setDescription(String description) {
	     this.description = description;
	 }

	 private String description;    

	 Blog(String title, String author, String url, String description)
	 {
	     this.title = title;
	     this.author = author;
	     this.url = url;
	     this.description = description; 
	 }
	 @Override
	 public boolean equals(Object obj) {
	     // TODO Auto-generated method stub
	     if(obj instanceof Blog)
	     {
	         Blog temp = (Blog) obj;
	         if(this.title == temp.title && this.author== temp.author && this.url == temp.url && this.description == temp.description)
	             return true;
	     }
	     return false;

	 }
	 @Override
	 public int hashCode() {
	     // TODO Auto-generated method stub

	     return (this.title.hashCode() + this.author.hashCode() + this.url.hashCode() + this.description.hashCode());        
	 }
}
