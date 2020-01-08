package com.botApplicationScriptForOthers.botApplicationScreiptForOthers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;




/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App implements CommandLineRunner
{
    public static void main( String[] args )
    {
    	SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
		builder.headless(false);
		
		ConfigurableApplicationContext context = builder.run(args);

		context.close();
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("getStartScript ..........");
		getStartScript();
		
	}
	
	
	public String getStartScript()
	{
		System.out.println("getStartScript ..........");
		BotSetupScriptForOthers bss=new BotSetupScriptForOthers();
		//bss.current_date();
		bss.getData();
		System.out.println("process end  ..........");    
		
		return "success";
		
	}
}
