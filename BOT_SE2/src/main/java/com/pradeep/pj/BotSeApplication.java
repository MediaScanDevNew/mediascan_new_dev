package com.pradeep.pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/*
import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.pradeep.pj.repo.Master_crawle_urlRepository;
import com.pradeep.pj.service.Blacklist_sitesService;
import com.pradeep.pj.service.BotRunningStatusService;
import com.pradeep.pj.service.Keyword_filterService;
import com.pradeep.pj.service.Markscan_machineService;
import com.pradeep.pj.service.Markscan_pipeService;
import com.pradeep.pj.service.Markscan_usersService;
import com.pradeep.pj.service.Master_crawle_urlService;
import com.pradeep.pj.service.Project_infoService;
import com.pradeep.pj.service.Stored_project_setupService;
*/
@SpringBootApplication
public class BotSeApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(BotSeApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BotSeApplication.class);
	}

	
	
}
