package com.markscan.utility;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SchedulerException
    {
        System.out.println( "Markscan Utility Application Started ....." );
        
        
      /*JobDetail job2 = JobBuilder.newJob(BOTStarterJob.class)
				.withIdentity("job2", "group2").build();
		
		Trigger trigger2 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger2", "group2")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * * * ?")) //          dailyAtHourAndMinute(15,23) 
				.build();
	
		Scheduler scheduler2 = new StdSchedulerFactory().getScheduler();
		scheduler2.start();
		scheduler2.scheduleJob(job2, trigger2);
		
		
		
		JobDetail job3 = JobBuilder.newJob(CreateKeyPhaseForTV.class)
				.withIdentity("job3", "group3").build();
		
		Trigger trigger3 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger3", "group3")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * * * ?")) //  cronSchedule("0 0/15 * * * ?")         
				.build();
	
		Scheduler scheduler3 = new StdSchedulerFactory().getScheduler();
		scheduler3.start();
		scheduler3.scheduleJob(job3, trigger3);
		
		
		JobDetail job4 = JobBuilder.newJob(CreateKeyPhaseForOthers.class)
				.withIdentity("job4", "group4").build();
		
		Trigger trigger4 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger4", "group4")
				.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(07,30)) //   cronSchedule("0 0/2 * * * ?")        
				.build();
	
		Scheduler scheduler4 = new StdSchedulerFactory().getScheduler();
		scheduler4.start();
		scheduler4.scheduleJob(job4, trigger4);
		
		
		JobDetail job5 = JobBuilder.newJob(ManualBotStart.class)
				.withIdentity("job5", "group5").build();
		
		Trigger trigger5 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger5", "group5")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")) //          dailyAtHourAndMinute(15,23) 
				.build();
	
		Scheduler scheduler5 = new StdSchedulerFactory().getScheduler();
		scheduler5.start();
		scheduler5.scheduleJob(job5, trigger5);*/
		
		
		JobDetail job6 = JobBuilder.newJob(IWLProcess.class)
				.withIdentity("job6", "group6").build();
		
		Trigger trigger6 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger6", "group6")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 * * * ?")) //          dailyAtHourAndMinute(15,23) 
				.build();
	
		Scheduler scheduler6 = new StdSchedulerFactory().getScheduler();
		scheduler6.start();
		scheduler6.scheduleJob(job6, trigger6);
		
		
		
        
        
    }
}
