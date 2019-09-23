package com.samsung.gilsoo.cm.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.samsung.gilsoo.cm.manager.CrawlingManager;

@Component
public class CrawlingScheduler extends QuartzJobBean{

	@Autowired
	private CrawlingManager manager = new CrawlingManager();
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			manager.managing();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
