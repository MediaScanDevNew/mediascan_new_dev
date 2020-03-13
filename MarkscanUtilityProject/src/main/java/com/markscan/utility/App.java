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
        
        
        JobDetail job2 = JobBuilder.newJob(BOTStarterJob.class)
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
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/15 * * * ?")) //          dailyAtHourAndMinute(15,23) 
				.build();
	
		Scheduler scheduler3 = new StdSchedulerFactory().getScheduler();
		scheduler3.start();
		scheduler3.scheduleJob(job3, trigger3);
		
		
		
		JobDetail job4 = JobBuilder.newJob(CreateKeyPhaseForOthers.class)
				.withIdentity("job4", "group4").build();
		
		Trigger trigger4 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger4", "group4")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/53 * * * ?")) //          dailyAtHourAndMinute(15,23) 
				.build();
	
		Scheduler scheduler4 = new StdSchedulerFactory().getScheduler();
		scheduler4.start();
		scheduler4.scheduleJob(job4, trigger4);
    }
}
