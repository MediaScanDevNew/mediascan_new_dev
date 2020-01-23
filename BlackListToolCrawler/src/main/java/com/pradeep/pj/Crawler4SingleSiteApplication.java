package com.pradeep.pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.pradeep.pj" })
public class Crawler4SingleSiteApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Crawler4SingleSiteApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Crawler4SingleSiteApplication.class, args);
	}

}
