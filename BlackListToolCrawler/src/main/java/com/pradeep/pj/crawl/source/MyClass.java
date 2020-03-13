package com.pradeep.pj.crawl.source;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class MyClass {
	
	
	public MyClass(){}
	
	
	public String doStuff(){
		
		return "Hello World";
	}

}
