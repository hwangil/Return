package com.samsung.gilsoo.cm.scheduler;

import org.quartz.Trigger;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

public class Schedulers{
	public static void execute(Class<?> jobClass, long startDelay, long repeatInterval) throws Exception {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setJobClass(jobClass);
		jobDetail.setName(jobClass.getSimpleName());
		jobDetail.setDurability(true);
		jobDetail.afterPropertiesSet();
		SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();
		simpleTrigger.setName("SimpleTrigger");
		simpleTrigger.setJobDetail(jobDetail.getObject());
		simpleTrigger.setStartDelay(startDelay);
		simpleTrigger.setRepeatInterval(repeatInterval);
		simpleTrigger.afterPropertiesSet();
		schedulerFactory.setJobDetails(jobDetail.getObject());
		schedulerFactory.setTriggers(new Trigger[] {simpleTrigger.getObject()});
		schedulerFactory.afterPropertiesSet();
		schedulerFactory.start();
	}
}
