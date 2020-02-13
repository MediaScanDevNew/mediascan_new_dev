package com.botApplicationScript.botApplicationScreipt;

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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.botApplicationScript.botApplicationScreipt.*;


@SpringBootApplication

public class BotScript implements CommandLineRunner
{
	
	
	public static void main(String...args) {
		//SpringApplication.run(BotScript.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(BotScript.class);
		builder.headless(false);
		/*
		try {
			builder.wait(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	//	System.setProperty("java.awt.headless", "true");
		ConfigurableApplicationContext context = builder.run(args);

		context.close();
	}
	
	
	@Override
	
	public void run(String... args) throws Exception
	{
		
		//new BotStarter().botRun("1");
	    getStartScript();
		//getStartBot();
		
	
	}
	
	public String getStartScript()
	{
		int count =0;
		System.out.println("=========================Count zero before execute================="+count);
		if(count==0)
		{
			
			BotSetupScript bss=new BotSetupScript();
		    bss.current_date();
		    bss.getData();
		    
		    BotSetupScriptForOthers bss1 = new BotSetupScriptForOthers();
		    bss1.getData();
		    
		count=1;
		}
		//if(count==1)
		//{
			//getStartBot();
		//}
		return "success";
		
	}
	
	public void getStartBot()
	{ 
		
		try {
		
		BotStarter bs=	new BotStarter();
		bs.start();
		System.out.println("====================Thread Running==================");
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
