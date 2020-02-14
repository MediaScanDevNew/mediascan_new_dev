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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
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
	   // getStartScript();
		getStartBot();
		
	
	}
	
	public String getStartScript()
	{
		int count =0;
		System.out.println("=========================Count zero before execute================="+count);
		if(count==0)
		{
			System.out.println("=========================Count zero execute================="+count);
			BotSetupScript bss=new BotSetupScript();
		    System.out.println("=========================Count zero execute1=================");
		   bss.current_date();
		    System.out.println("=========================Count zero execute2=================");
		   bss.getData();
		    System.out.println("=========================Count zero execute3=================");
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
		
		
		//BotStarter bs1=	new BotStarter();
		//BotStarter bs2=	new BotStarter();
		//BotStarter bs3=	new BotStarter();
		//BotStarter bs4=	new BotStarter();
		//BotStarter bs5=	new BotStarter();
		
		bs.start();
			System.out.println("====================Thread Running==================");
		
		//bs1.start();
		//bs1.wait(10000);
		//bs2.start();
	    //bs3.start();
		//bs4.start();
		
		//bs5.start();
		
		
		
		//System.exit(0);
		//bs1.wait(5000);
		//bs1.start();
		//bs2.wait(5000);
		//bs2.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
